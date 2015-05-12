/**
 *
 */
package br.hering.webservices.endpoint;

import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.hering.webservices.message.product.attribute.Atributo;
import br.hering.webservices.message.product.attribute.ImportarAtributosProdutoRequest;


/**
 * @author ghayashi
 *
 */
@Endpoint
public class InterfaceImportacaoCategoriasProdutoEndpoint extends AbstractInterfaceEndpoint<ImportarAtributosProdutoRequest>
{
	@Resource
	private UserService userService;

	@Resource
	private CategoryService categoryService;

	private final List<String> categories = Arrays.asList("GRUPO");

	@Override
	@ResponsePayload
	@PayloadRoot(localPart = "importarAtributosProdutoRequest", namespace = namespaceUri)
	public void service(@RequestPayload final ImportarAtributosProdutoRequest request)
	{
		try
		{
			LOG.info("importarAtributosProduto: ok");

			final List<Atributo> listaAtributos = request.getAtributo();

			if (CollectionUtils.isEmpty(listaAtributos))
			{
				LOG.warn("Nenhum dado para importação");
				return;
			}

			for(BaseStoreModel baseStore : baseStoreService.getAllBaseStores()){
				prepareCurrentSession(baseStore.getLinxSiteCode(), false);
			
   			for (final Atributo atributo : listaAtributos)
   			{
   				final String codigo = atributo.getCodigo();
   				final String descricao = atributo.getDescricao();
   				final String tipo = atributo.getTipo();
   
   				LOG.info("tipo: " + tipo + " codigo: " + codigo + " descricao: " + descricao);
   
   
   				if (categories.contains(tipo))
   				{
   					processCategory(codigo, descricao);
   				}
   			}
			}
		}
		catch (final Exception e)
		{
			LOG.error("ERROR", e);
		}
	}

	/**
	 * @param codigo
	 * @param descricao
	 */
	private void processCategory(final String codigo, final String descricao)
	{
		try
		{
			final CategoryModel category = findOrCreateCategoryByCode(codigo);
			category.setName(descricao);
			category.setDescription(descricao);

			addSuperCategories(category);

			addPrincipals(category);

			modelService.save(category);
		}
		catch (final Exception e)
		{
			LOG.error("error", e);
			createErrorMessageList(CHAVE_ATRIBUTO + "=" + codigo, e.getMessage());
		}
	}

	/**
	 * @param codigo
	 * @return
	 */
	private CategoryModel findOrCreateCategoryByCode(final String codigo)
	{
		try
		{
			return categoryService.getCategoryForCode(catalogVersion, codigo);
		}
		catch (final UnknownIdentifierException e)
		{
			LOG.info("Criando nova categoria: " + codigo);

			final CategoryModel category = modelService.create(CategoryModel.class);
			category.setCode(codigo);
			category.setCatalogVersion(catalogVersion);

			return category;
		}
	}

	/**
	 * @param category
	 */
	private void addSuperCategories(final CategoryModel category)
	{
		final Set<CategoryModel> superCategories = new HashSet();

		if (CollectionUtils.isNotEmpty(category.getSupercategories()))
		{
			superCategories.addAll(category.getSupercategories());
		}

		superCategories.add(categoryService.getCategoryForCode(catalogVersion, "categories"));

		category.setSupercategories(new ArrayList<CategoryModel>(superCategories));
	}

	/**
	 * @param category
	 */
	private void addPrincipals(final CategoryModel category)
	{
		final Set<PrincipalModel> principals = new HashSet();

		if (CollectionUtils.isNotEmpty(category.getAllowedPrincipals()))
		{
			principals.addAll(category.getAllowedPrincipals());
		}

		principals.add(userService.getUserGroupForUID("customergroup"));

		category.setAllowedPrincipals(new ArrayList<PrincipalModel>(principals));
	}
}