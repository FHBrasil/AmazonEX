package com.pixi.webservices;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.pixi.core.services.PixiProductService;
import com.pixi.webservices.jaxb.adapter.BooleanAdapter;
import com.pixi.webservices.jaxb.adapter.DateAdapter;
import com.pixi.webservices.jaxb.adapter.StringAdapter;
import com.pixi.webservices.jaxb.factory.MoxyJaxbContextFactoryImpl;
import com.pixi.webservices.jaxb.product.export.BMECAT;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;

@IntegrationTest
public class ExportProductsControllerTest extends ServicelayerTransactionalTest 
{
	private static final Logger LOG = Logger.getLogger(ExportProductsControllerTest.class.getName());

	private MoxyJaxbContextFactoryImpl jaxbContextFactory;
	
	private PixiProductService pixiProductService;
	
	@Resource
	private Converter<List<ProductModel>, BMECAT> pixiBMEcatConverter;
	
	@Resource
	private ModelService modelService;
	
	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		createTestEnvironment();
		mock();
		
		List<Class> typeAdapters = new ArrayList<Class>();
		typeAdapters.add(DateAdapter.class);
		typeAdapters.add(BooleanAdapter.class);
		typeAdapters.add(StringAdapter.class);
		
		jaxbContextFactory = new MoxyJaxbContextFactoryImpl();
		jaxbContextFactory.setWrapCollections(false);
		jaxbContextFactory.setTypeAdapters(typeAdapters );
	}
	
	private void mock() 
	{
		pixiProductService = Mockito.mock(PixiProductService.class);
		
		List<ProductModel> products = new ArrayList<ProductModel>();
		
		CatalogModel catalog = new CatalogModel();
		catalog.setId("apparelProductCatalog");

		CatalogVersionModel catalogVersion = new CatalogVersionModel();
		catalogVersion.setCatalog(modelService.getByExample(catalog));
		catalogVersion.setVersion("Online");

		CatalogVersionModel cv = modelService.getByExample(catalogVersion);

		ProductModel sample = new ProductModel();
		sample.setCode("300020465");
		sample.setCatalogVersion(cv);
		
		ProductModel result = Mockito.spy(modelService.getByExample(sample));
		
		Mockito.stub(result.getName()).toReturn("Sonnensegel fur Kinderwagen - Marine");
		
		products.add(result);
		
		Mockito.when(pixiProductService.findProductsToExport(cv)).thenReturn(products);
	}
	
	private void createTestEnvironment() throws ImpExException 
	{
		importCsv("/pixiwebservices/test/testProductEnvironment.csv", "windows-1252");
	}
	
	private void printXML(Object obj) throws JAXBException
	{
		final JAXBContext jaxbContext = jaxbContextFactory.createJaxbContext(obj.getClass());
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		marshaller.marshal(obj, System.out);
	}
	
	@Test
	public void testPixiwebservices() throws JAXBException
	{
		LOG.info("testando o service de produtos");
		
		CatalogModel catalog = new CatalogModel();
		catalog.setId("apparelProductCatalog");

		CatalogVersionModel catalogVersion = new CatalogVersionModel();
		catalogVersion.setCatalog(modelService.getByExample(catalog));
		catalogVersion.setVersion("Online");

		CatalogVersionModel cv = modelService.getByExample(catalogVersion);
		
		List<ProductModel> products = pixiProductService.findProductsToExport(cv);
		
		BMECAT wsBmeCat = pixiBMEcatConverter.convert(products);
		
		printXML(wsBmeCat);
		
		//TODO testar direito
		Assert.assertNotNull("retorno nulo", wsBmeCat);
	}
}