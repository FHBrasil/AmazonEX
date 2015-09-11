/**
 *
 */
package br.hering.webservices.endpoint;

import de.hybris.platform.basecommerce.enums.StockLevelUpdateType;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.jalo.CatalogManager;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.user.UserManager;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.stock.model.StockLevelHistoryEntryModel;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.util.Config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;

import br.hering.webservices.constants.TransactionsEnum;
import br.hering.webservices.message.transacao.Resultado;
import br.hering.webservices.message.transacao.ResultadoTransacaoRequest;


/**
 * @author franthescollymaneira
 * 
 */
public abstract class AbstractInterfaceEndpoint<T>
{
	public static final String DF_ERP_TO_HYBRIS = "yyyyMMdd HH:mm:ss";

	public static final String DF_HYBRIS_TO_ERP = "yyyy-MM-dd HH:mm:ss";

	public static final String CHAVE_PRODUTO = "PRODUTO";

	public static final String CHAVE_FRETE = "CODIGO_TAB_FRETE";

	public static final String CHAVE_SKU = "SKU";

	public static final String CHAVE_SITE = "CODIGO_SITE";

	public static final String CHAVE_ID = "ID";

	public static final String CHAVE_ATRIBUTO = "COD_ATRIBUTO";

	public static final String CHAVE_CLIENTE = "CODIGO_CLIENTE";

	public static final String CHAVE_PEDIDO = "PEDIDO";

	public static final String CHAVE_PK_CLIENTE = "PK_CLIENTE_HYBRIS";

	public static final String MENSAGEM = "MENSAGEM";

	protected static final String namespaceUri = "http://hering.fliegersoftware.de/interface/soap/";

	protected final Logger LOG = Logger.getLogger(this.getClass());

	protected List<Resultado> errorList;

	protected CatalogVersionModel catalogVersion;

	protected String currentStoreId;

	@Resource
	protected BaseStoreService baseStoreService;

	@Resource
	protected CommonI18NService commonI18NService;

	@Resource
	protected CatalogVersionService catalogVersionService;

	@Resource
	protected ModelService modelService;

	@Autowired
	private WebServiceTemplate enviarResultadoTransacaoWSTemplate;

	//TODO TEMP solution to linx2hybris map
	//
	private Map<Class<? extends AbstractInterfaceEndpoint>, TransactionsEnum> mapping;
	private Map<Integer, String> storeCodeMapping;

	{
		mapping = new HashMap<Class<? extends AbstractInterfaceEndpoint>, TransactionsEnum>();
		mapping.put(InterfaceImportacaoMedidasProdutoEndpoint.class, TransactionsEnum.MEDIDA_HYBRIS);
		mapping.put(InterfaceImportacaoPrecoProdutoEndpoint.class, TransactionsEnum.PRECO_HYBRIS);
		mapping.put(InterfaceImportacaoFaturaPedidoEndpoint.class, TransactionsEnum.EVENTO_HYBRIS);
		mapping.put(InterfaceImportacaoEstoqueProdutoEndpoint.class, TransactionsEnum.ESTOQUEMOVIMENTO_HYBRIS);
		mapping.put(InterfaceImportacaoEstoqueSaldoProdutoEndpoint.class, TransactionsEnum.ESTOQUESALDO_HYBRIS);
		mapping.put(InterfaceImportacaoCategoriasProdutoEndpoint.class, TransactionsEnum.ATRIBUTO_HYBRIS);
		mapping.put(InterfaceImportacaoProdutosEndpoint.class, TransactionsEnum.PRODUTO_HYBRIS);
		mapping.put(InterfaceImportacaoClienteEndpoint.class, TransactionsEnum.CLIENTE_HYBRIS);
		mapping.put(InterfaceImportacaoServicosTransporteEndpoint.class, TransactionsEnum.FRETE_HYBRIS);

		storeCodeMapping = new HashMap<Integer, String>();
		storeCodeMapping.put(Integer.valueOf(1), "hering");
		storeCodeMapping.put(Integer.valueOf(2), "dzarm");
		storeCodeMapping.put(Integer.valueOf(3), "puc");
		storeCodeMapping.put(Integer.valueOf(4), "kids");
		storeCodeMapping.put(Integer.valueOf(5), "foryou");
	}

	/**
	 * 
	 * @param request
	 */
	abstract void service(T request);

	protected void prepareCurrentSession(final String siteId, final boolean onlineCV)
	{
		final Collection<CatalogVersionModel> sessionCVs = catalogVersionService.getSessionCatalogVersions();

		if (CollectionUtils.isEmpty(sessionCVs) || !StringUtils.equals(siteId, currentStoreId))
		{
			this.currentStoreId = siteId;

			final CatalogVersionModel cv = getCatalogVersionByStore(siteId, onlineCV);
			prepareCurrentSession(cv);
		}
	}

	protected void prepareCurrentSession(final CatalogVersionModel catalogVersion)
	{
		final Collection<CatalogVersionModel> sessionCVs = catalogVersionService.getSessionCatalogVersions();

		if (CollectionUtils.isEmpty(sessionCVs) || ObjectUtils.notEqual(this.catalogVersion, catalogVersion))
		{
			JaloSession.getCurrentSession().setUser(UserManager.getInstance().getAdminEmployee());

			commonI18NService.setCurrentLanguage(commonI18NService.getLanguage("pt"));
			commonI18NService.setCurrentCurrency(commonI18NService.getCurrency("BRL"));

			LOG.info("SessionID: " + JaloSession.getCurrentSession().getSessionID() + " hashcode: " + this.hashCode() + " "
					+ catalogVersion.getCatalog().getName() + " / " + catalogVersion.getVersion() + " - "
					+ commonI18NService.getCurrentLanguage().getName() + " " + commonI18NService.getCurrentCurrency().getName());

			this.catalogVersion = catalogVersion;
			catalogVersionService.setSessionCatalogVersions(Arrays.asList(catalogVersion));
		}
	}

	protected CatalogVersionModel getCatalogVersionByStore(final String storeCode, final boolean onlineCV)
	{
		final Integer storeCodeAsNumber = Integer.valueOf(storeCode);
		if (!storeCodeMapping.containsKey(storeCodeAsNumber))
		{
			throw new IllegalArgumentException("Unknown store with code: string: " + storeCode + " int: " + storeCodeAsNumber);
		}

		final String hybrisCode = storeCodeMapping.get(storeCodeAsNumber);
		final String catalogId = hybrisCode + "ProductCatalog";
		final String cvv = onlineCV ? CatalogManager.ONLINE_VERSION : CatalogManager.OFFLINE_VERSION;
		final CatalogVersionModel cv = catalogVersionService.getCatalogVersion(catalogId, cvv);

		return cv;
	}

	protected BaseStoreModel getCurrentBaseStore()
	{
		if (StringUtils.isBlank(currentStoreId))
		{
			return null;
		}

		final Integer storeCodeAsNumber = Integer.valueOf(currentStoreId);
		if (!storeCodeMapping.containsKey(storeCodeAsNumber))
		{
			throw new IllegalArgumentException("Unknown store with code: string: " + currentStoreId + " int: " + storeCodeAsNumber);
		}

		final BaseStoreModel store = baseStoreService.getBaseStoreForUid(storeCodeMapping.get(storeCodeAsNumber));

		return store;
	}

	protected synchronized void createErrorMessageList(final String chave, final String mensagem)
	{

		if (errorList == null)
		{
			errorList = new ArrayList<Resultado>();
		}

		final Resultado resultado = new Resultado();

		resultado.setChave(chave);
		resultado.setMensagem(mensagem);
		resultado.setTransacao(mapping.get(this.getClass()).toString());

		final ResultadoTransacaoRequest request = new ResultadoTransacaoRequest();
		request.getResultado().add(resultado);

		LOG.info("EVIANDO MENSAGEM - INICIO");
		LOG.info("CHAVE: " + resultado.getChave() + " TRANSACAO:" + resultado.getTransacao() + " MENSAGEM: "
				+ resultado.getMensagem());
		enviarResultadoTransacaoWSTemplate.setDefaultUri(Config.getParameter("enviar.resultado.transacao.ws.template"));
		enviarResultadoTransacaoWSTemplate.marshalSendAndReceive(request);
		LOG.info("EVIANDO MENSAGEM - FIM");

	}

	protected String formatDate(final Date date)
	{
		LOG.info("formating date: " + date);
		return new SimpleDateFormat(DF_HYBRIS_TO_ERP).format(date);
	}

	protected Date parseDate(final String dateString) throws ParseException
	{
		LOG.info("parsing string: " + dateString);
		return new SimpleDateFormat(DF_ERP_TO_HYBRIS).parse(dateString);
	}

	/**
	 * @return the storeCodeMapping
	 */
	public Map<Integer, String> getStoreCodeMapping()
	{
		return storeCodeMapping;
	}

	public StockLevelHistoryEntryModel createStockLevelHistoryEntry(final StockLevelModel stockLevel,
			final StockLevelUpdateType updateType, final int reserved, final String comment)
	{
		final StockLevelHistoryEntryModel historyEntry = (StockLevelHistoryEntryModel) modelService
				.create(StockLevelHistoryEntryModel.class);
		historyEntry.setStockLevel(stockLevel);
		historyEntry.setActual(stockLevel.getAvailable());
		historyEntry.setReserved(reserved);
		historyEntry.setUpdateType(updateType);
		if (comment != null)
		{
			historyEntry.setComment(comment);
		}
		historyEntry.setUpdateDate(new Date());
		modelService.save(historyEntry);
		return historyEntry;
	}
}