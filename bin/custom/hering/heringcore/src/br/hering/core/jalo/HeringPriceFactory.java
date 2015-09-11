/**
 * 
 */
package br.hering.core.jalo;

import de.hybris.platform.catalog.jalo.CatalogAwareEurope1PriceFactory;
import de.hybris.platform.commercefacades.customergroups.CustomerGroupFacade;
import de.hybris.platform.commercefacades.user.data.UserGroupData;
import de.hybris.platform.commercefacades.voucher.VoucherFacade;
import de.hybris.platform.commercefacades.voucher.data.VoucherData;
import de.hybris.platform.commercefacades.voucher.exceptions.VoucherOperationException;
import de.hybris.platform.core.CoreAlgorithms;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.AbstractOrderEntry;
import de.hybris.platform.jalo.order.price.JaloPriceFactoryException;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.DiscountValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.hering.core.customer.impl.KPCustomerAccountService;

import com.google.common.base.Strings;

/**
 * @author franthescollymaneira
 * @author ezequiel
 * 
 * Classe que calcula descontos para funcionarios da hering. Os funcionarios são divididos em dois grupos
 * admin - tem um limite de R$ 500,00 para gastar por mes
 * normais - tem um limite de R$ 300,00 para gastar por mes
 * 
 * os dois tem um desconto de 30%
 *
 */
public class HeringPriceFactory extends CatalogAwareEurope1PriceFactory
{
	private static final Logger LOG = Logger.getLogger(HeringPriceFactory.class);

	private static final BigDecimal THREE_HUNDRED = new BigDecimal(300);
	private static final BigDecimal FIVE_HUNDRED = new BigDecimal(500);
	private static final OrderStatus[] VALID_STATUSES = new OrderStatus[]{
		OrderStatus.CAPTURE_PAYMENT,
		OrderStatus.CHECKED_VALID,
		OrderStatus.COMPLETED,
		OrderStatus.CREATED,
		OrderStatus.DELIVERED,
		OrderStatus.DISPATCHED,
		OrderStatus.FRAUD_CHECKED,
		OrderStatus.INVOICE,
		OrderStatus.ON_VALIDATION,
		OrderStatus.ORDER_SPLIT,
		OrderStatus.PAYMENT_AMOUNT_NOT_RESERVED,
		OrderStatus.PAYMENT_AMOUNT_RESERVED,
		OrderStatus.PAYMENT_AUTHORIZED,
		OrderStatus.PAYMENT_CAPTURED,
		OrderStatus.PAYMENT_NOT_AUTHORIZED,
		OrderStatus.PAYMENT_NOT_CAPTURED,
		OrderStatus.PENDING_APPROVAL,
		OrderStatus.PROCESSING_ERROR,
		OrderStatus.RESERVED,
		OrderStatus.SUSPENDED,
		OrderStatus.WAIT_FRAUD_MANUAL_CHECK,
		OrderStatus.WAITING_PAYMENT_NOTIFICATION};

	private static final Calendar FIRST_DAY = Calendar.getInstance();
	private static final Calendar LAST_DAY = Calendar.getInstance();
	static{
		FIRST_DAY.set(Calendar.DAY_OF_MONTH, 1);
		FIRST_DAY.set(Calendar.HOUR_OF_DAY, 0);
		FIRST_DAY.set(Calendar.MINUTE, 0);
		FIRST_DAY.set(Calendar.SECOND, 0);
		FIRST_DAY.set(Calendar.MILLISECOND, 0);
	
		LAST_DAY.set(Calendar.HOUR_OF_DAY, 23);
		LAST_DAY.set(Calendar.MINUTE, 59);
		LAST_DAY.set(Calendar.SECOND, 59);
		LAST_DAY.set(Calendar.MILLISECOND, 999);	
	}
	
	@Resource(name = "customerAccountService")
	private KPCustomerAccountService customerAccountService;
	
	@Resource(name = "customerGroupFacade")
	private CustomerGroupFacade groupFacade;
	
	@Resource(name = "userService")
	private UserService userService;	
	
	@Resource(name = "voucherFacade")
	protected VoucherFacade voucherFacade;

	
	private static final String VALE_CREDITO_RESERV = "vc_reserv";
	private static final String VALE_CREDITO = "vc_";
	
	

	@Override
	public List<? extends DiscountValue> getDiscountValues(AbstractOrder order) throws JaloPriceFactoryException 
	{	
		List<DiscountValue> result = new ArrayList<DiscountValue>();
		try
		{
			//discovers the discount configured to the employee in hmc
			DiscountValue discount = getEmployeeDiscountValue(order);
			
			if(discount == null ){
				return result;
			}
			
			final String voucherCode = appliedValeCredito();
			//Caso possua o vale de reserva n�o aplica o desconto de funcionario
			if(!Strings.isNullOrEmpty(voucherCode) && isVoucherReserved(voucherCode)){
				return new ArrayList<DiscountValue>();
			}

			//discovers the limit configured in hmc for the employee
			BigDecimal employeeLimit = getEmployeeLimitByGroup();
			if(employeeLimit == null){
				return result;
			}

			BigDecimal totalCompradoComDescontoNoMes = new BigDecimal(getTotalCompradoComDescontoNoMes());

			if( totalCompradoComDescontoNoMes.compareTo(employeeLimit) >= 0 )
			{
				return result;
			}

			BigDecimal limiteDestaCompra = employeeLimit.subtract(totalCompradoComDescontoNoMes);

			List<ItemPedido> entradasOndeVaiSerDadoDesconto = new ArrayList<ItemPedido>();

			double total = 0D;
			double totalComDescontos = 0D;

			for (final AbstractOrderEntry entry : order.getEntries())
			{
				total += entry.getTotalPriceAsPrimitive();//preco por
				
				final BigDecimal oldPrice = getOldPrice(entry.getProduct());//preco de
				final Long qty = entry.getQuantity();
				double precoDe = oldPrice.doubleValue() * qty.doubleValue();

				double oldPricePercentage = calculatePercentage(precoDe, entry.getTotalPriceAsPrimitive());//CoreAlgorithms.round(, 2);
				
				//se tem desconto pra dar em um item da compra, qual é este desconto?
				if(oldPricePercentage < discount.getValue()){
					double novoDesconto = discount.getValue() - oldPricePercentage;
					
					BigDecimal precoDesejado;
					
					if(entry.getTotalPriceAsPrimitive() < precoDe){
						double descontoEmDinheiro = CoreAlgorithms.round(precoDe * novoDesconto / 100D, 2);
						precoDesejado = new BigDecimal(entry.getTotalPriceAsPrimitive() - descontoEmDinheiro);
					} else {
						precoDesejado = new BigDecimal(precoDe * (1 - (novoDesconto / 100.0D)));	
					}
					
					if (limiteDestaCompra.compareTo(BigDecimal.ZERO) <= 0){
						totalComDescontos += entry.getTotalPriceAsPrimitive();
						continue;
					} else if(precoDesejado.compareTo(limiteDestaCompra)  <= 0){
						limiteDestaCompra = limiteDestaCompra.subtract(precoDesejado);//simplesmente tira do limite o preço deste produto
						//novoDesconto = calculatePercentage(precoDe, precoDesejado.doubleValue());//novo desconto deveria ser bem menor
					} else {
						final double valorComDesconto = CoreAlgorithms.round(limiteDestaCompra.doubleValue() / (1 - (novoDesconto/100D)), 2);
						final double valorSemDesconto = precoDe - valorComDesconto;
						final double currPrice = limiteDestaCompra.doubleValue() + valorSemDesconto; 
						novoDesconto = calculatePercentage(precoDe, currPrice);//valorComDesconto
						limiteDestaCompra = BigDecimal.ZERO;
					}

					ItemPedido pedido = new ItemPedido(entry, precoDe, novoDesconto);
					entradasOndeVaiSerDadoDesconto.add(pedido);	
				} else {
					totalComDescontos += entry.getTotalPriceAsPrimitive();
				}
			}

			for (ItemPedido pedido : entradasOndeVaiSerDadoDesconto)
			{
				double precoDesejado;
				if(pedido.entry.getTotalPriceAsPrimitive() < pedido.precoDe){
					double descontoEmDinheiro = CoreAlgorithms.round(pedido.precoDe * pedido.desconto / 100D, 2);
					precoDesejado = pedido.entry.getTotalPriceAsPrimitive() - descontoEmDinheiro;
				} else {
					precoDesejado = pedido.precoDe * (1 - (pedido.desconto / 100.0D));	
				}
				totalComDescontos += CoreAlgorithms.round(precoDesejado, 2);
			}

			double finalDiscountValue = calculatePercentage(total, totalComDescontos);

			final DiscountValue discountValueBean = new DiscountValue(	discount.getCode(),
																							CoreAlgorithms.round(finalDiscountValue, 2), 
																							false,
																							discount.getCurrencyIsoCode());
			result.add(discountValueBean);
		}
		catch (Exception e)
		{
			LOG.debug("Unexpected Exception while calculating employee discounts: " + e);
		}

		return result;
	}
	
		

	/**
	 * @param customerData
	 */
	private Double getTotalCompradoComDescontoNoMes()
	{
		Double valorComprado = 0D;

		//refresh the date of the static fields
		final Calendar aux = Calendar.getInstance();
		int month = aux.get(Calendar.MONTH);
		int lastDay = aux.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		FIRST_DAY.set(Calendar.MONTH, month);
		LAST_DAY.set(Calendar.MONTH, month);
		LAST_DAY.set(Calendar.DAY_OF_MONTH, lastDay);
		
		List<OrderModel> orders = customerAccountService.getOrderListByCustomerAndDateAndStatus(getUserService().getCurrentUser(), 
				FIRST_DAY.getTime(), LAST_DAY.getTime(), VALID_STATUSES);
		
		for (OrderModel order : orders)
		{
			valorComprado += (order.getSubtotal() - order.getTotalDiscounts());
		}
		return valorComprado;
	}

	/**
	 * @param product
	 * @return
	 */
	private BigDecimal getOldPrice(Product product)
	{
		try
		{
			Object attribute = product.getAttribute("oldPrice");
			if(attribute != null)
			{
				return (BigDecimal) attribute;
			}
		}
		catch (Exception e)
		{
			LOG.error("error", e);
		}
		return null;
	}

	/**
	 * 
	 */
	private double calculatePercentage(final double fromPrice, final double currPrice)
	{
		if (fromPrice <= currPrice)
		{
			return 0d;
		}

		return ((fromPrice - currPrice) / fromPrice) * 100;
	}

	private BigDecimal getEmployeeLimitByGroup(){
		List<UserGroupData> customerGroupsForCurrentUser = groupFacade.getCustomerGroupsForCurrentUser();
		for (UserGroupData userGroupData : customerGroupsForCurrentUser)
		{	
			if("ADMINISTRATIVO".equalsIgnoreCase(userGroupData.getUid())){
				return FIVE_HUNDRED;
			}

			if("PRODUTIVO".equalsIgnoreCase(userGroupData.getUid())){
				return THREE_HUNDRED;
			}
		}
		return null;
	}
	
	private DiscountValue getEmployeeDiscountValue(final AbstractOrder order) throws JaloPriceFactoryException
	{
		List<DiscountValue> discountList = new ArrayList<DiscountValue>(super.getDiscountValues(order));
		
		DiscountValue result = null;
		
		for (DiscountValue discount : discountList)
		{
			if(	(StringUtils.equalsIgnoreCase("30p", discount.getCode()) || StringUtils.equalsIgnoreCase("30porcentodesconto", discount.getCode()))
					&& discount.getValue() == 30D){
				result = discount;
				break;
			}
		}
		return result;
	}

	class ItemPedido{
		public ItemPedido(AbstractOrderEntry entry, double precoDe, double desconto)
		{
			this.entry = entry;
			this.precoDe = precoDe;
			this.desconto = desconto;
		}
		AbstractOrderEntry entry;
		double precoDe;
		double desconto;
	}
	
	private boolean isVoucherReserved(final String voucherCode){
		
		if (!voucherCode.toLowerCase().startsWith(VALE_CREDITO_RESERV))
		{
			return false;
		}
		try
		{
			VoucherData voucherData = voucherFacade.getVoucher(voucherCode);
			if (!voucherData.getValueString().startsWith("100.0%"))
			{
				return false;
			}
		}
		catch (VoucherOperationException voe)
		{
			LOG.error("error: ", voe);
			return false;
		}
		return true;
	}	
	
	private String appliedValeCredito()
	{
		final List<VoucherData> vouchersData = voucherFacade.getVouchersForCart();
		if (CollectionUtils.isNotEmpty( vouchersData ))
		{
			for (final VoucherData v : vouchersData)
			{
				if (v.getVoucherCode().startsWith(VALE_CREDITO))
				{
					return v.getVoucherCode();
				}
			}
		}
		return "";
	}
	
	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}	
}
