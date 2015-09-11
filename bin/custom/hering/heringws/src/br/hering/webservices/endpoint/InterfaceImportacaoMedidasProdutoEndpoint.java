/**
 *
 */
package br.hering.webservices.endpoint;

import de.hybris.platform.product.ProductService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.hering.core.model.HeringProductModel;
import br.hering.core.model.ProductMeasurementsModel;
import br.hering.core.product.dao.ProductMeasurementsDao;
import br.hering.webservices.message.product.size.ImportarMedidasProdutoRequest;
import br.hering.webservices.message.product.size.MedidaProduto;


/**
 * @author franthescollymaneira
 *
 */
@Endpoint
public class InterfaceImportacaoMedidasProdutoEndpoint extends AbstractInterfaceEndpoint<ImportarMedidasProdutoRequest>
{
	@Resource
	private ProductService productService;

	@Resource
	private ProductMeasurementsDao productMeasurementsDao;

	@Override
	@ResponsePayload
	@PayloadRoot(localPart = "importarMedidasProdutoRequest", namespace = namespaceUri)
	public void service(@RequestPayload final ImportarMedidasProdutoRequest request)
	{
		try
		{
			final List<MedidaProduto> listaMedidas = request.getMedidaProduto();

			if (CollectionUtils.isEmpty(listaMedidas))
			{
				LOG.warn("Nenhum dado para importação");
				return;
			}

			forMedida: for (final MedidaProduto medida : listaMedidas)
			{
				try
				{
					prepareCurrentSession(medida.getCodigoSite(), false);

					final String productCode = medida.getCodigoProduto();
					final String size = medida.getTamanho();
					final String type = medida.getTipo();

					final HeringProductModel product = (HeringProductModel) productService.getProductForCode(catalogVersion,
							productCode);

					final ArrayList<ProductMeasurementsModel> list = new ArrayList<>();

					list.addAll(product.getMeasurementList());

					for (final ProductMeasurementsModel m : list)
					{
						if (m.getSize().compareTo(size) == 0 && m.getType().compareTo(type) == 0)
						{
							m.setMeasurement(Double.valueOf(medida.getMedida().doubleValue()));
							product.setMeasurementList(list);
							modelService.save(product);
							LOG.info("Medida encontrada para o produto: " + product.getCode());
							continue forMedida;
						}
					}
					LOG.info("Medida não encontrada para o produto: " + product.getCode() + ", criando registro: " + type + "/" + size
							+ "/" + medida.getMedida());
					final ProductMeasurementsModel measurement = modelService.create(ProductMeasurementsModel.class);
					measurement.setSize(size);
					measurement.setType(type);
					measurement.setMeasurement(Double.valueOf(medida.getMedida().doubleValue()));
					list.add(measurement);

					product.setMeasurementList(list);
					modelService.save(product);

					//final ProductMeasurementsModel measurement = findOrCreateMeasurement(productCode, size, type);
					//measurement.setMeasurement(Double.valueOf(medida.getMedida().doubleValue()));

					//					MediaModel picture = measurement.getPicture();
					//
					//					if (picture == null)
					//					{
					//						picture = modelService.create(MediaModel.class);
					//						picture.setCode(productCode + "_" + size + "_" + type + "_" + "picture");
					//						picture.setCatalogVersion(catalogVersion);
					//
					//						measurement.setPicture(picture);
					//					}
					//
					//					picture.setLocation(medida.getImagem());
					//
					//					modelService.saveAll(picture, measurement);
				}
				catch (final Exception e)
				{
					LOG.error("error", e);
					createErrorMessageList(CHAVE_PRODUTO + "=" + medida.getCodigoProduto(), e.getMessage());
				}
			}
		}
		catch (final Exception e)
		{
			LOG.error("ERROR", e);
		}
	}

	//	/**
	//	 *
	//	 * @param productCode
	//	 * @param size
	//	 * @param type
	//	 * @return
	//	 */
	//	@SuppressWarnings("deprecation")
	//	private synchronized ProductMeasurementsModel findOrCreateMeasurement(final String productCode, final String size,
	//			final String type)
	//	{
	//		final HeringProductModel product = (HeringProductModel) productService.getProductForCode(catalogVersion, productCode);
	//
	//		if (product == null)
	//		{
	//			LOG.error("product not found: " + productCode);
	//		}
	//
	//		ProductMeasurementsModel measurement = productMeasurementsDao.findMeasurements(product, size, type);
	//
	//		if (measurement != null)
	//		{
	//			LOG.info(productCode + " - measurement encontrado size: " + measurement.getSize() + " type: " + measurement.getType());
	//			return measurement;
	//		}
	//
	//		LOG.info(productCode + " - measurement criado size: " + size + " type: " + type);
	//
	//		measurement = modelService.create(ProductMeasurementsModel.class);
	//		//measurement.setProduct(product);
	//		measurement.setSize(size);
	//		measurement.setType(type);
	//
	//		modelService.save(measurement);
	//
	//		return measurement;
	//	}
}