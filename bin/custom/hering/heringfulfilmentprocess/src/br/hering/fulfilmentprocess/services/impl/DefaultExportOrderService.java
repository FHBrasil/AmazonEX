package br.hering.fulfilmentprocess.services.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;

import br.hering.core.enums.TipoDeEndereco;
import br.hering.core.model.HeringSizeVariantProductModel;
import br.hering.core.model.order.payment.VoucherPaymentInfoModel;
import br.hering.fulfilmentprocess.message.order.Cliente;
import br.hering.fulfilmentprocess.message.order.DadosPedido;
import br.hering.fulfilmentprocess.message.order.Endereco;
import br.hering.fulfilmentprocess.message.order.ImportarPedidoRequest;
import br.hering.fulfilmentprocess.message.order.Itens;
import br.hering.fulfilmentprocess.message.order.Pagamento;
import br.hering.fulfilmentprocess.message.order.Pedido;

import com.flieger.data.NewsletterSubscriberData;
import com.flieger.facades.NewsletterSubscriberFacade;
import com.flieger.payment.model.BoletoPaymentInfoModel;

import de.hybris.platform.commerceservices.constants.GeneratedCommerceServicesConstants.Enumerations.CustomerType;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.DiscountValue;
import de.hybris.platform.voucher.VoucherService;
import de.hybris.platform.voucher.model.VoucherModel;

/**
 * @author ghayashi
 * @author perin
 * @author franthescollymaneira
 * @author ezequiel
 * 
 */
public class DefaultExportOrderService extends AbstractHeringOrderService {
	protected final Logger LOG = Logger.getLogger(this.getClass());

	private static final String CLEARSALE = "ClearSale";

	private static final int APROVADO_CLEARSALE = 1;

	private static final String CARTAO = "A";

	private static final String BOLETO = "J";
	
	private static final String VALE_CREDITO = "&";

	private static final String VOUCHER_VALIDATE = "vc_";

	final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");
	
	private Map<String, String> storeCodeMapping;
	{
		storeCodeMapping = new HashMap<String,String>();
		storeCodeMapping.put("hering", "001");
		storeCodeMapping.put("dzarm", "002");
		storeCodeMapping.put("puc", "003");
		storeCodeMapping.put("hering-kids", "004");
		storeCodeMapping.put("foryou", "005");
	}
	
	@Autowired
	private WebServiceTemplate enviarPedidosWSTemplate;

	@Resource(name = "newsletterSubscriptionFacade")
	private NewsletterSubscriberFacade newsletterSubscriptionFacade;
	
	@Resource
	private VoucherService voucherService;

	public boolean exportOrder(final OrderModel order) {		
		
		LOG.info("Iniciando envio do pedido numero:" + order.getCode());

		final DadosPedido dadosPedido = new DadosPedido();
		boolean success = this.populateDadosPedido(order, dadosPedido);
		
		final ImportarPedidoRequest request = new ImportarPedidoRequest();
		request.setDadosPedido(dadosPedido);
				
		enviarPedidosWSTemplate.setDefaultUri(Config.getParameter("enviar.pedidos.ws.template"));
		enviarPedidosWSTemplate.marshalSendAndReceive(request);

		LOG.info("Envio do pedido numero:" + order.getCode() + " Finalizado" 
		+ (success ? " sem" : " com") + " erros.");
		
		return success;
	}
		
	private boolean populateDadosPedido(final OrderModel order, final DadosPedido dadosPedido) 
	{
		final String siteId = getSiteId(order.getSite().getUid());

		boolean success = buildCustomerData(order, dadosPedido, siteId);

		success &= buildOrderData(order, dadosPedido, siteId);

		buildCarrierData(order, dadosPedido);

		success &= buildPaymentData(order, dadosPedido, siteId);

		buildOrderEntriesData(order, dadosPedido, siteId);
		
		buildPaymentVoucher(order, dadosPedido, siteId);

		success &= validateDadosPedido(dadosPedido);
		
		return success;
	}
	
	private boolean validateDadosPedido(final DadosPedido dadosPedido){
		return !dadosPedido.getPagamento().isEmpty();
	}
	
	private String getSiteId(String site){
		if (storeCodeMapping.containsKey(site))
		{
			return storeCodeMapping.get(site);
		}
		return "";
	}

	/**
	 * @param order
	 * @param dadosPedido
	 */
	private boolean buildCustomerData(final OrderModel order,
			final DadosPedido dadosPedido, final String siteId) 
	{
		boolean success = true;
		try
		{
			final CustomerModel customer = (CustomerModel) order.getUser();
   		final Cliente cliente = new Cliente();
   		cliente.setCodigoSite(siteId);
   		cliente.setCodigoCliente(customer.getPk().toString());
   		
   		if(customer.getType() != null && customer.getType().equals(CustomerType.GUEST))
   		{
   			String name = ""; 
   			if(order.getDeliveryAddress() != null
   					&& order.getDeliveryAddress().getOriginal() != null)
   			{
   				name += StringUtils.isBlank(order.getDeliveryAddress().getOriginal().getFirstname()) ? "" : 
   							order.getDeliveryAddress().getOriginal().getFirstname();
   				name += " " + (StringUtils.isBlank(order.getDeliveryAddress().getOriginal().getLastname()) ? "" : 
   					order.getDeliveryAddress().getOriginal().getLastname());
   			}
   			cliente.setNome(name.trim());
   		}else{
   			cliente.setNome(customer.getName());
   		}
   		
   		cliente.setPfPj(customer.getCpfcnpj().length() == 11 ? true : false);
   		cliente.setRgIe(customer.getRgIe());
   		cliente.setCpfCgc(customer.getCpfcnpj());
   		
   		if(customer.getBirthday() != null){
   			cliente.setAniversario(dateFormat.format(customer.getBirthday()));
   		}
   		
   		cliente.setDataCadastramento(dateFormat.format(customer
   				.getCreationtime()));
   		
   		if(customer.getGender() != null){
   			cliente.setSexo(customer.getGender().getCode().charAt(0) + "");
   		}else{
   			cliente.setSexo(Gender.UNDEFINED.getCode().charAt(0) + "");
   		}
   		
   		if(customer.getDefaultShipmentAddress() != null ){
   			if(customer.getDefaultShipmentAddress().getDddPhone() != null){
   				cliente.setDdd(customer.getDefaultShipmentAddress().getDddPhone());
   			}
   			if(customer.getDefaultShipmentAddress().getPhone1() != null){
   				cliente.setTelefone(customer.getDefaultShipmentAddress().getPhone1());
   			}
   		}
   		
   		cliente.setEmail(customer.getUid().replaceAll("[a-z0-9\\-]*\\|", ""));
   
   		try {
   			NewsletterSubscriberData newsLetter = newsletterSubscriptionFacade
   					.findByEmail(customer.getUid().replaceAll("[a-z0-9\\-]*\\|", ""));
   			cliente.setNewsletter(newsLetter != null 
   					&& Boolean.TRUE.equals(newsLetter.getReceive()) );
   		} catch (Exception e) {
   			LOG.error("Not FATAL error", e);
   		}
   
   		dadosPedido.setCliente(cliente);
   
   		success = buildCustomerAddressData(dadosPedido, customer, order);
   		
   	} catch (Exception e) {
   		success = false;
			LOG.info("Error creating customerData for Order: " + order.getCode() 
					+ "; Exception: " + e);
		}
		return success;
	}

	/**
	 * @param dadosPedido
	 * @param customer
	 */
	private boolean buildCustomerAddressData(final DadosPedido dadosPedido,
			final CustomerModel customer, final OrderModel order) {

		boolean success = true;
//		
//		for(final AddressModel address : customer.getAddresses()){
//			final Endereco customerAddress = 
//					buildAddress(address, customer.getPk().toString());
//			if(customerAddress == null){
//				success = false;
//			} else if (!dadosPedido.getEndereco().contains(customerAddress)){
//				dadosPedido.getEndereco().add(customerAddress);
//			}
//		}
		
		final Endereco deliveryAddress = 
				buildAddress(order.getDeliveryAddress(), customer.getPk().toString());

		if(deliveryAddress == null){
			success = false;
			LOG.info("Error creating deliveryAddress for order: " + order.getCode());
		} else if(!dadosPedido.getEndereco().contains(deliveryAddress)){
			dadosPedido.getEndereco().add(deliveryAddress);				
		}
		
//		final Endereco paymentAddress = 
//				buildAddress(order.getPaymentAddress(), customer.getPk().toString());

//		if(paymentAddress == null){
//			success = false;
//			LOG.info("Error creating paymentAddress for order: " + order.getCode());
//		} else if(!dadosPedido.getEndereco().contains(paymentAddress)){
//			dadosPedido.getEndereco().add(paymentAddress);				
//		}
		
//		final Endereco billingAddress = 
//				buildAddress(order.getPaymentInfo().getBillingAddress(), customer.getPk().toString());

//		if(billingAddress == null){
//			success = false;
//			LOG.info("Error creating billingAddress for order: " + order.getCode());
//		} else if(!dadosPedido.getEndereco().contains(billingAddress)){
//			dadosPedido.getEndereco().add(billingAddress);				
//		}
		return success;
	}
	
	private Endereco buildAddress(final AddressModel add, String codigoCliente){
		try
		{
			final Endereco endereco = new Endereco();
			
			if(add.getOriginal() != null){
				endereco.setCodigoEndereco(add.getOriginal().getPk().getLongValueAsString());
			}else{
				endereco.setCodigoEndereco(add.getPk().getLongValueAsString());
			}
			
			endereco.setDescricaoEndereco(add.getFirstname() + " " + add.getLastname());
			endereco.setPais(add.getCountry().getIsocode());
			endereco.setCep(add.getPostalcode());
			endereco.setBairro(add.getDistrict());
			endereco.setCidade(add.getTown());
			endereco.setComplemento(add.getComplemento());
			endereco.setNumero(add.getStreetnumber());
			endereco.setLogradouro(add.getStreetname());
			endereco.setUf(add.getRegion().getIsocode().substring(3));
			endereco.setEnderecoPrincipal(true);
			endereco.setTipoEndereco(getAddressType(add));
			endereco.setDdd(add.getDddPhone());
			endereco.setTelefone(add.getPhone1());
			endereco.setDddCelular(add.getDddCellPhone());
			endereco.setCelular(add.getCellphone());
			endereco.setCodigoCliente(codigoCliente);
			
			return endereco;			
		}
		catch (Exception e)
		{
			LOG.info("Error creating Address for order export. Customer: "
		+ codigoCliente + "; address: " + add.getPk().toString() + "; Exception: " + e);
		}
		return null;
	}

	/**
	 * @param order
	 * @param dateFormat
	 * @param dadosPedido
	 */
	private boolean buildOrderData(final OrderModel order,
			final DadosPedido dadosPedido, String siteId) 
	{
		boolean success = true;
		
		try
		{
   		final Pedido pedido = new Pedido();
   
   		pedido.setCodigoSite(siteId);
   
   		pedido.setCodigoPedido(order.getCode());
   		pedido.setData(dateFormat.format(order.getCreationtime()));
   		pedido.setDataHoraFinalizacaoPedido(dateFormat.format(order.getDate()));
   
   		pedido.setTipo("1");
   		pedido.setValorTotal(BigDecimal.valueOf(order.getTotalPrice()
   				.doubleValue()));
   		/*
   		 * Quando h��� um voucher de vale cr���dito o valor do desconto ��� zerado 
   		 * e ��� recalculado o valor total
   		*/
   		pedido.setDesconto(BigDecimal.valueOf(order.getTotalDiscounts()));
   		if (CollectionUtils.isNotEmpty(order.getDiscounts())) {
   			for(String voucherCode: voucherService.getAppliedVoucherCodes(order)){
   				if(StringUtils.isNotBlank(voucherCode) && !voucherCode.startsWith(VOUCHER_VALIDATE)){										
   					pedido.setCodigoDesconto(voucherCode);
   					break;
   				}
   			}
   		}
   
   		pedido.setCodigoCliente(order.getUser().getPk().toString());
   		
   		//setting deliveryAddress
   		AddressModel deliveryAddress = order.getDeliveryAddress();
   		
   		if(deliveryAddress.getOriginal() != null){
   			deliveryAddress = deliveryAddress.getOriginal();
   		} 
   		Endereco aux = new Endereco();
   		aux.setCodigoEndereco(deliveryAddress.getPk().getLongValueAsString());
   		if(!dadosPedido.getEndereco().contains(aux)){
   			success = false;
   			LOG.info("FATAL ERROR. Building OrderData for Order: " + order.getCode() 
   					+ " failed. Customer does not contain the DeliveryAddress assingned for this order. "
   					+ "The order will be sent to PI, but it will fail to integrate to Linx due to this mismatch");
   		}
   		pedido.setCodigoEnderecoEntrega(aux.getCodigoEndereco());
   		
   		//setting paymentAddress
   		//Retirado pois o Linx não precisa do endereço de cobrança
   		//Passou a enviar o endereço de entrega pois o PI valida null.
//   		AddressModel paymentAddress = order.getPaymentAddress();
//   		if(paymentAddress.getOriginal() != null){
//   			paymentAddress = paymentAddress.getOriginal();
//   		}
//   		aux.setCodigoEndereco(paymentAddress.getPk().getLongValueAsString());   		
//   		if(!dadosPedido.getEndereco().contains(aux)){
//   			success = false;
//   			LOG.info("FATAL ERROR. Building OrderData for Order: " + order.getCode() 
//   					+ " failed. Customer does not contain the PaymentAddress assingned for this order. The order will be sent to PI, but it will fail to integrate to Linx due to this mismatch");
//   		}
   		pedido.setCodigoEnderecoCobranca(aux.getCodigoEndereco());
   		
   		dadosPedido.setPedido(pedido);
		} catch (Exception e){
			success = false;
			LOG.info("Error building OrderData for order: " + order.getCode() + ". Exception: " + e);
		}
		return success;
	}	
	
	//Calcula o valor de desconto quando o valor do frete ��� em porcentagem
	private BigDecimal calculateVoucherAbsoluteValue(final BigDecimal desconto, final BigDecimal total){
	
		if(desconto != BigDecimal.ZERO){
			final BigDecimal percent = desconto.divide(BigDecimal.valueOf(100));
			final BigDecimal absoluteValue = percent.multiply(total);
			return BigDecimal.valueOf(absoluteValue.floatValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return BigDecimal.ZERO; 
	}

	/**
	 * @param order
	 * @param dadosPedido
	 * @param pedido
	 */
	private void buildOrderEntriesData(final OrderModel order,
			final DadosPedido dadosPedido, String siteId) {
		int count = 0;
		int idx = 1;

		for (final AbstractOrderEntryModel entry : order.getEntries()) {
			count = count + entry.getQuantity().intValue();

			HeringSizeVariantProductModel heringProduto = (HeringSizeVariantProductModel) entry
					.getProduct();

			final Itens item = new Itens();
			item.setCodigoSite(siteId);
			item.setCodigoPedido(order.getCode());
			item.setCodigoProduto(heringProduto.getBaseProduct().getCode()
					.split("_")[0]);
			item.setCorProduto(heringProduto.getBaseProduct().getCode()
					.split("_")[1]);
			item.setItem(String.valueOf(idx));
			item.setSku(entry.getProduct().getCode());
			item.setQuantidade(entry.getQuantity().intValue());
			item.setPrecoLiquido(BigDecimal.valueOf(entry.getBasePrice()
					.doubleValue() - calculateDiscount(entry
							.getDiscountValues())));
			
			item.setDesconto(BigDecimal.valueOf(this.calculateDiscount(entry
					.getDiscountValues())));
			item.setStatus(0);

			dadosPedido.getItens().add(item);
			idx += 1;
		}

		dadosPedido.getPedido().setQuantidadeTotal(count);
	}

	/**
	 * @param order
	 * @param pedido
	 */
	//XXX remo��o de dependencia com extension carrier
	private void buildCarrierData(final OrderModel order,
			final DadosPedido dadosPedido) {
//		Pedido pedido = dadosPedido.getPedido();
//		CarrierZoneDeliveryModeModel deliveryMode = (CarrierZoneDeliveryModeModel) order.getDeliveryMode();
//		pedido.setFormaEntrega(deliveryMode.getTransportMode());
//		pedido.setTransportadora(deliveryMode.getCode());
//		pedido.setPeso(BigDecimal.valueOf(deliveryService.calculateTotalWeight(order)));
//		pedido.setValorFrete(BigDecimal.valueOf(order.getDeliveryCost().doubleValue()));
//		pedido.setPrazoMaximoEntrega(order.getEstimatedDeliveryDays());
	}

	/**
	 * @param order
	 * @param dadosPedido
	 * @param dadosPedido
	 */
	private boolean buildPaymentData(final OrderModel order,
			final DadosPedido dadosPedido, final String siteId) 
	{
		boolean success = true;
		
		final Pagamento pagamento = new Pagamento();
		pagamento.setCodigoSite(siteId);
		pagamento.setCodigoPedido(order.getCode());
		pagamento.setMoeda(order.getCurrency().getSymbol());
		pagamento.setCapturado(APROVADO_CLEARSALE);
		pagamento.setCodigoAntifraude(CLEARSALE);
		
		pagamento.setValor( BigDecimal.valueOf(order.getTotalPrice()) );
		pagamento.setValorMoeda( BigDecimal.valueOf(order.getTotalPrice()) );
		pagamento.setCotacao(BigDecimal.valueOf(1));
	
		PaymentTransactionEntryModel auth = null;
		if( !CollectionUtils.isEmpty(order.getPaymentTransactions()) ) {	
			for (PaymentTransactionEntryModel model : order.getPaymentTransactions().get(0).getEntries())
			{
				if (model.getType().equals(PaymentTransactionType.AUTHORIZATION))
				{
					auth = model;
					break;
				}
			}
		}
		
		if (		order.getPaymentInfo() instanceof CreditCardPaymentInfoModel
				&& auth != null) 
		{
			CreditCardPaymentInfoModel cc = 
					(CreditCardPaymentInfoModel) order.getPaymentInfo();
			
			pagamento.setTipoPagamento(CARTAO);
			pagamento.setNumeroTitulo(cc.getNumber());
			pagamento.setParcelasCartao(cc.getInstallment());
			
			pagamento.setCodigoAdministradora(
					getCodigoAdminstradoraCartao(cc.getType().getCode()));
			if(pagamento.getCodigoAdministradora() == 0){
				success = false;
			}
			
			pagamento.setVencimento(dateFormat.format(order.getDate()));
			// Numero de referencia do adyen
			pagamento.setNumeroAprovacaoCartao(auth.getAdyenAuthorizationCode());	
		}
		else if(		order.getPaymentInfo() instanceof BoletoPaymentInfoModel
					&& auth != null)
		{
			pagamento.setTipoPagamento(BOLETO);
			pagamento.setNumeroTitulo(auth.getAdyenBoletoNossoNumero());
			pagamento.setParcelasCartao(1);
			// Para boleto o codigo eh ZERO
			pagamento.setCodigoAdministradora(0);
			pagamento.setVencimento(auth.getAdyenBoletoExpirationDate());			
			// Campo obrigatorio, fixo numero unico do boleto
			pagamento.setNumeroAprovacaoCartao(auth.getAdyenBoletoNossoNumero());
		}
		else if( order.getPaymentInfo() instanceof VoucherPaymentInfoModel) 
		{
			pagamento.setTipoPagamento(VALE_CREDITO);
			pagamento.setParcelasCartao(1);
			pagamento.setCodigoAdministradora(0);
			pagamento.setVencimento(dateFormat.format(order.getDate()));
			//caso o voucher for igual ao  valor da mercadoria n���o ��� adicionado o metodo de pagamento aqui e sim na buildPaymentVoucher 
			if((new Double(0)).equals(order.getTotalPrice())){
				LOG.info("Payment is Voucher and the order totalPrice is equal to 0. "
						+ "The payments will be constructed based on the vouchers used by this order");
				return (success && true);
			}
		}
		/*
		 * FIXO - utilizado somente quando for utilizado dois tipos de
		 * pagamentos diferente para a mesma compra
		 */
		pagamento.setParcela(1);		
		dadosPedido.getPagamento().add(pagamento);
		return success;
	}
	
	private void buildPaymentVoucher(final OrderModel order, DadosPedido dadosPedido, String siteId)
	{			
		final Pagamento pagamento = new Pagamento();

//apenas um voucher promocional por pedido, mais um voucher tratado pelo buildPayment
		for(final String voucherCode: voucherService.getAppliedVoucherCodes(order))
		{
			LOG.info("Encontrado voucher: " + voucherCode);
			if(StringUtils.isNotBlank(voucherCode) && voucherCode.toLowerCase().startsWith(VOUCHER_VALIDATE))
			{	
				final VoucherModel voucher = voucherService.getVoucher(voucherCode);
				
				if(voucher == null){
					LOG.info("Voucher eh invalido. Skipping...");
					continue;
				} else {
					LOG.info("Voucher eh valido. Criando pagamento...");					
				}
				
				BigDecimal valorDoVoucher = BigDecimal.valueOf(voucher.getValue());

				if(!voucher.getAbsolute()){
					valorDoVoucher = calculateVoucherAbsoluteValue(valorDoVoucher, BigDecimal.valueOf(order.getSubtotal() ));
				}
				
				LOG.info("Valor do voucher: " + valorDoVoucher);
				
				pagamento.setCodigoSite(siteId);
				pagamento.setCodigoPedido(order.getCode());
				pagamento.setMoeda(order.getCurrency().getSymbol());
				pagamento.setCapturado(APROVADO_CLEARSALE);
				pagamento.setCodigoAntifraude(CLEARSALE);	
				pagamento.setValor(valorDoVoucher);
				pagamento.setValorMoeda(valorDoVoucher);
				
				pagamento.setCotacao(BigDecimal.valueOf(1));
				pagamento.setTipoPagamento(VALE_CREDITO);
				pagamento.setParcelasCartao(1);
				pagamento.setCodigoAdministradora(0);
				pagamento.setVencimento(dateFormat.format(order.getDate()));
				pagamento.setParcela(0);
				pagamento.setNumeroTitulo(voucherCode);					
				
				//como o vale cr���dito n���o ��� desconto ��� realculado o valor de desconto	
				BigDecimal outrosDescontos = 
						BigDecimal.valueOf(order.getTotalDiscounts());
				
				outrosDescontos = 
						outrosDescontos.subtract(
								BigDecimal.valueOf(
										Math.min(outrosDescontos.doubleValue(), 
												valorDoVoucher.doubleValue())));
				
				LOG.info("Outros descontos: " + outrosDescontos);
				
				//totaldiscounts: 59,9 - voucher: 54,99 = 4,91 no caso do voucher, o totaldiscounts est�� englobando o valor do voucher. Se eu tirar o valor 
				// do voucher, tenho o valor que efetivamente foi dado como desconto
				if( voucher.getFreeShipping() ){
					dadosPedido.getPedido().setValorFrete(BigDecimal.ZERO);
					outrosDescontos = 
							outrosDescontos.subtract( 
									BigDecimal.valueOf(
											Math.min(outrosDescontos.doubleValue(), order.getDeliveryCost())) );
					LOG.info("Voucher eh freeShipping. Outros descontos ajustados: " + outrosDescontos);
				}
				
				final BigDecimal totalPrice = BigDecimal.valueOf(order.getTotalPrice()).add(valorDoVoucher);
				
				LOG.info("Preco total: " + totalPrice);
				
				dadosPedido.getPedido().setValorTotal(totalPrice);
				dadosPedido.getPedido().setDesconto( outrosDescontos );					
				dadosPedido.getPagamento().add(pagamento);
				break;
			}
		}
	}	
	
	private String getAddressType(AddressModel add) {
		String home = "2";
		String company = "3";

		if (add.getTipoDeEndereco() == null) {
			return home;
		}

		if (add.getTipoDeEndereco().getCode()
				.equals(TipoDeEndereco.RESIDENCIAL)) {
			return home;
		}

		return company;
	}

	private int getCodigoAdminstradoraCartao(String brand) {
		if(brand == null)
		{
			return 0;
		}
		if (brand.equalsIgnoreCase("VISA"))
		{
			return 1;
		}
		if (brand.equalsIgnoreCase("MASTER"))
		{
			return 2;
		}
		if (brand.equalsIgnoreCase("DINERS"))
		{
			return 3;
		}
		if (brand.equalsIgnoreCase("AMEX"))
		{
			return 4;
		}
		if (brand.equalsIgnoreCase("HIPERCARD"))
		{
			return 5;
		}
		if (brand.equalsIgnoreCase("ELO"))
		{
			return 6;
		}
		if (brand.equalsIgnoreCase("HERING"))
		{
			return 7;
		}
		return 0;		
	}
	
	private long calculateDiscount(final List<DiscountValue> list) {
		long total = 0;
		for (final DiscountValue discount : list) {
			total += discount.getValue();
		}
		return total;
	}

}