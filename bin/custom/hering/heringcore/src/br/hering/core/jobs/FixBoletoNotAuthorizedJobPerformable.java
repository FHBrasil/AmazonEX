package br.hering.core.jobs;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.commerceservices.strategies.GenerateMerchantTransactionCodeStrategy;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.util.Config;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import br.hering.core.model.jobs.FixBoletoNotAuthorizedJobModel;
import br.hering.fulfilmentprocess.actions.order.SendOrderPlacedNotificationAction;

import com.adyen.services.payment.AdyenBoletoAuthorizationRequest;
import com.adyen.services.payment.AdyenBoletoAuthorizationResult;
import com.adyen.services.payment.AdyenPaymentService;
import com.adyen.services.payment.impl.AdyenBoletoPaymentService;
import com.flieger.main.Credentials;
import com.flieger.payment.model.BoletoPaymentInfoModel;


public class FixBoletoNotAuthorizedJobPerformable extends AbstractJobPerformable<FixBoletoNotAuthorizedJobModel>
{
	private Logger LOG = Logger.getLogger(FixBoletoNotAuthorizedJobPerformable.class);
	
	private static final String BOLETO_GENERATOR_COMMAND = "/HYBRIS/skripte/gen_pdf_to_html.sh";

	private static final Pattern ORDER_CODE_PATTERN = Pattern.compile("[0-9]{8}");
	
	private List<OrderModel> thisJobList = new ArrayList<OrderModel>();
	
	@Resource
	private SendOrderPlacedNotificationAction sendOrderPlacedNotificationAction;

	@Resource
	private I18NService i18nService;

	@Resource
	private BaseStoreService baseStoreService;

	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Resource
	private GenerateMerchantTransactionCodeStrategy generateMerchantTransactionCodeStrategy;

	@Resource
	private AdyenPaymentService paymentService;

	@Resource
	private AdyenBoletoPaymentService boletoPaymentService;

	@Resource
	private CommonI18NService commonI18NService;

	@Override
	public PerformResult perform(final FixBoletoNotAuthorizedJobModel job)
	{
		CronJobResult cronJobResult = CronJobResult.SUCCESS;

		try
		{
			prepare();
		}
		catch (Exception e)
		{
			LOG.debug("Error while preparing execution: " + e);
			cronJobResult = CronJobResult.ERROR;
		}

		for (OrderModel order : thisJobList)
		{
			try
			{
				refazCheckout(order);
				reGerarBoleto(order);
				if(order.getOrderProcess() != null && 
						!order.getOrderProcess().isEmpty()){
					sendOrderPlacedNotificationAction.execute(order.getOrderProcess().iterator().next());
				}
			}
			catch (Exception e)
			{
				LOG.debug("Erro fazendo checkout: " + e + ", orderid: " + order.getCode());
				cronJobResult = CronJobResult.ERROR;
			}
		}
		thisJobList.clear();
		return new PerformResult(cronJobResult, CronJobStatus.FINISHED);
	}

	/**
	 * @param order
	 */
	private void reGerarBoleto(final OrderModel order)
	{
      PaymentTransactionModel paymenTtransaction = order.getPaymentTransactions().get(0);
      PaymentTransactionEntryModel transactionEntry = paymenTtransaction.getEntries().get(0);
      String boletoURL = transactionEntry.getAdyenBoletoUrl();
      
      Process process = null;
		try
		{
			LOG.info("Executing boleto generator commmand...");

			Runtime runtime = Runtime.getRuntime();

			process = runtime.exec(BOLETO_GENERATOR_COMMAND + " " + order.getCode() + " " + boletoURL);
		}
		catch (IOException e)
		{
			LOG.info("Error generator command: " + BOLETO_GENERATOR_COMMAND, e);
		}

		try
		{
			int commandReturnCode = process.waitFor();

			if (commandReturnCode == 0)
			{
				LOG.info("Boleto Generator command successfully executed");
			}
			else
			{
				LOG.info("Couldn't execute the boleto generator command, return code: " + commandReturnCode);
			}
		}
		catch (InterruptedException e)
		{
			LOG.info("Error getting process return code", e);
		}
	}

	/**
	 * Read the newly added csv file and add all the entries to the map with
	 * 
	 * the employees entries
	 * 
	 * */
	private void prepare() throws Exception
	{
		Iterator<String> iterator = null;
		try
		{
			iterator = getOrderIdList();
			if (!iterator.hasNext())
			{
				LOG.info("There's order id to load, finished job execution");
				throw new Exception("No order id to import");
			}
		}
		catch (Exception e)
		{
			LOG.error("error", e);
			throw new Exception("Unexpected error" + e);
		}

		while (iterator.hasNext())
		{
			final String orderId = iterator.next();
			try
			{
				Matcher matcher = ORDER_CODE_PATTERN.matcher(orderId);
				if (!matcher.matches())
				{
					LOG.info("OrderId inválido " + orderId);
					continue;
				}

				final List<OrderModel> orderList = findOrdersByCode(orderId);

				if (orderList.isEmpty())
				{
					LOG.info("Order not found for code: " + orderId);
				}
				else
				{
					thisJobList.addAll(orderList);
				}
			}
			catch (Exception e)
			{
				LOG.error("Unexpected error with:" + orderId);
			}
		}
	}
	
	private void refazCheckout(final OrderModel order
			) throws Exception
	{
		Assert.notNull(order, "Order não pode ser nula");
		
		if(order.getStore() == null 
				|| StringUtils.isBlank(order.getStore().getUid())){
			throw new Exception("Order has no store configured. Cannot go on: " + order.getCode());
		}
		
		if(order.getPaymentTransactions() == null
				|| order.getPaymentTransactions().isEmpty()){
			throw new Exception("There is no PaymentTransaction on this order. Cannot go on: " + order.getCode());
		}
		
		final PaymentInfoModel paymentInfo = order.getPaymentInfo();
		
		final PaymentTransactionModel transaction = order.getPaymentTransactions().get(0);
		
		if (paymentInfo !=null && paymentInfo instanceof BoletoPaymentInfoModel)
		{
			PaymentTransactionEntryModel entry = null;
			
			if(transaction.getEntries() != null ){
				for(PaymentTransactionEntryModel temp : transaction.getEntries()){
					if(PaymentTransactionType.AUTHORIZATION.equals(temp.getType())){
						entry = temp;
						break;
					}
				}
			} else {
				transaction.setEntries(new ArrayList<PaymentTransactionEntryModel>());
			}
			
			if(entry == null){
				entry = modelService.create(PaymentTransactionEntryModel.class);
				entry.setCode(getNewEntryCode(transaction));
			}
			
			authorize(entry, transaction, order);
				
		} else {
			throw new Exception("Order paymentInfo is not Boleto. Cannot go on: " + order.getCode());
		}
	}

	private void authorize(final PaymentTransactionEntryModel entry,
			final PaymentTransactionModel transaction, 
			final OrderModel order) throws AdapterException
	{
		//recreate the cart just to pass as parameter, since it is mandatory for the payment service
		CartModel cartModel = modelService.create(CartModel.class);
		cartModel.setUser(order.getUser());
		cartModel.setTotalPrice(order.getTotalPrice());
		
		final String paymentProvider = getPaymentProvider(order.getStore().getUid());
		
		final Currency currency = this.i18nService.getBestMatchingJavaCurrency(
				order.getCurrency().getIsocode());

		final String merchantAccount = Config.getParameter(
				Credentials.MERCHANT_ACCOUNT + "." + order.getStore().getUid());
		
		AdyenBoletoAuthorizationRequest authorizationRequest = 
				new AdyenBoletoAuthorizationRequest(paymentProvider, entry.getCode(),
						order.getCode(), order.getPaymentInfo().getBillingAddress(),
						cartModel, ((CustomerModel) cartModel.getUser()).getCpfcnpj(),
						currency, merchantAccount);
		
		AdyenBoletoAuthorizationResult authorizationResult = boletoPaymentService.authorise(
				authorizationRequest);

		transaction.setRequestId(authorizationResult.getRequestId());
		transaction.setRequestToken(authorizationResult.getRequestToken());
		transaction.setPaymentProvider(authorizationResult.getPaymentProvider());
		
//		this.modelService.save(transaction);

		entry.setAmount(authorizationResult.getTotalAmount());

		if (TransactionStatusDetails.SUCCESFULL.equals(authorizationResult.getTransactionStatusDetails()))
		{
			if (authorizationResult.getCurrency() != null)
			{
				entry.setCurrency(this.commonI18NService.getCurrency(authorizationResult.getCurrency().getCurrencyCode()));
			}
			entry.setType(PaymentTransactionType.AUTHORIZATION);
			entry.setTime(authorizationResult.getAuthorizationTime() == null ? new Date() : authorizationResult.getAuthorizationTime());
			entry.setPaymentTransaction(transaction);
			entry.setRequestId(authorizationResult.getRequestId());
			entry.setRequestToken(authorizationResult.getRequestToken());
			entry.setTransactionStatus(authorizationResult.getTransactionStatus().toString());
			entry.setTransactionStatusDetails(authorizationResult.getTransactionStatusDetails().toString());
			entry.setAdyenReference(authorizationResult.getAdyenReference());
			entry.setAdyenMerchantReference(authorizationResult.getAdyenUniqueCode());
			entry.setAdyenBoletoUrl(authorizationResult.getBoletoBancarioUrl());
			entry.setAdyenBoletoNossoNumero(authorizationResult.getBoletoBancarioNossoNumero());
			entry.setAdyenBoletoExpirationDate(authorizationResult.getBoletoBancarioExpirationDate());
		}
		else
		{
			LOG.info("Status desconhecido ao autorizar o boleto pelo Adyen. Status retornado -->"
					+ authorizationResult.getTransactionStatusDetails());
		}

		this.modelService.save(entry);
		this.modelService.refresh(transaction);
	}

	private String getNewEntryCode(PaymentTransactionModel transaction)
	{
		if (transaction.getEntries() == null)
		{
			return transaction.getCode() + "-1";
		}
		return transaction.getCode() + "-" + (transaction.getEntries().size() + 1);
	}

	private Iterator<String> getOrderIdList() throws IOException, URISyntaxException, Exception
	{
		final File directory = new File("/HYBRIS/medias/csv");
		final Pattern pattern = Pattern.compile("^order_id_job[^.]+\\.csv", Pattern.CASE_INSENSITIVE);

		if (directory.exists() && directory.isDirectory())
		{
			FilenameFilter filenameFilter = new FilenameFilter()
			{
				@Override
				public boolean accept(File dir, String name)
				{
					Matcher matcher = pattern.matcher(name);
					if (matcher.matches())
					{
						return true;
					}
					return false;
				}
			};

			File[] files = directory.listFiles(filenameFilter);

			Arrays.sort(files, new Comparator<File>()
			{
				public int compare(File f1, File f2)
				{
					return Long.compare(f1.lastModified(), f2.lastModified());
				}
			});

			if (files.length > 0)
			{
				return FileUtils.lineIterator(files[0]);
			}
			else
			{
				throw new Exception("Error opening csv file");
			}
		}
		throw new Exception("No file found");
	}
	
	public List<OrderModel> findOrdersByCode(final String code)
	{
		validateParameterNotNull(code, "code must not be null!");
		final Map values = new HashMap();
		values.put("code", code);

		final StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT {").append(OrderModel.PK).append("} ").append("FROM {Order} WHERE {")
				.append(OrderModel.CODE).append("} = ?code");
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString(), values);
		query.setResultClassList(Collections.singletonList(OrderModel.class));
		final SearchResult<OrderModel> res = flexibleSearchService.search(query);
		final List<OrderModel> result = res.getResult();
		return result == null ? Collections.EMPTY_LIST : result;
	}

	public String getPaymentProvider(final String baseStoreUid)
	{
		BaseStoreModel currentBaseStore = baseStoreService.getBaseStoreForUid(baseStoreUid);
		Assert.notNull(currentBaseStore, "Não foi possível obter a base store para o uid: " + baseStoreUid);
		String paymentProvider = currentBaseStore.getPaymentProvider();
		Assert.notNull(paymentProvider, "Payment provider should be set on the BaseStore");
		return paymentProvider;
	}
}
