/**
 * 
 */
package br.hering.webservices.endpoint;

import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.strategies.CustomerNameStrategy;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.user.AddressService;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.hering.core.enums.TipoDeEndereco;
import br.hering.webservices.message.client.Cliente;
import br.hering.webservices.message.client.Endereco;
import br.hering.webservices.message.client.ImportacaoClienteRequest;


/**
 * @author ghayashi
 * 
 */
@Endpoint
public class InterfaceImportacaoClienteEndpoint extends AbstractInterfaceEndpoint<ImportacaoClienteRequest>
{
	private String passwordEncoding;

	@Resource
	private AddressService addressService;

	@Resource
	private CustomerAccountService customerAccountService;

	@Resource
	private CustomerNameStrategy customerNameStrategy;

	@Resource
	private CommonI18NService commonI18NService;


	@Resource
	private UserService userService;

	private final Map<String, Gender> genderMapping;

	{
		genderMapping = new HashMap<String, Gender>();

		genderMapping.put("I", Gender.UNDEFINED);
		genderMapping.put("M", Gender.MALE);
		genderMapping.put("F", Gender.FEMALE);
	}


	@Override
	@ResponsePayload
	@PayloadRoot(localPart = "importacaoClienteRequest", namespace = namespaceUri)
	public void service(@RequestPayload final ImportacaoClienteRequest request)
	{
		LOG.info("importarCliente: ok");

		CustomerModel customerModel = null;

		final Cliente customerRequest = request.getCliente();

		try
		{
			LOG.info("EMAIL CLIENTE: " + customerRequest.getEmail() + " ID CLIENTE: " + customerRequest.getCodigoCliente());
			if (request.getCliente() == null)
			{
				throw new Exception("Cliente vazio.");
			}

			final List<Endereco> listaEndereco = request.getEndereco();

			if (CollectionUtils.isEmpty(listaEndereco))
			{
				LOG.info("There's no address to be imported");
				throw new Exception("Lista de endereços vazia.");
			}

			prepareCurrentSession(customerRequest.getCodigoSite(), false);

			if (userService.isUserExisting(customerRequest.getEmail()))
			{
				LOG.info("CLIENTE - ATUALIZAÇÃO: " + customerRequest.getEmail());
				customerModel = (CustomerModel) userService.getUserForUID(customerRequest.getEmail());
			}
			else
			{
				LOG.info("CLIENTE - NOVO: " + customerRequest.getEmail());
				customerModel = modelService.create(CustomerModel.class);
				customerModel.setCustomerID(UUID.randomUUID().toString());
				customerModel.setUid(customerRequest.getEmail());
			}

			customerModel.setTitle(getTitle(customerRequest));
			customerModel.setCpfcnpj(customerRequest.getCpfCgc());
			customerModel.setRgIe(customerRequest.getRgIe());
			customerModel.setGender(genderMapping.get(customerRequest.getSexo()));

			if (StringUtils.isNotBlank(customerRequest.getNome()))
			{
				customerModel.setName(customerNameStrategy.getName(customerRequest.getNome(), ""));
			}

			
			customerModel.setSessionLanguage(commonI18NService.getCurrentLanguage());
			customerModel.setSessionCurrency(commonI18NService.getCurrentCurrency());
			customerModel.setBirthday(parseDate(customerRequest.getAniversario()));
			customerModel.setRegistrationDate(parseDate(customerRequest.getDataCadastramento()));

			userService.setPassword(customerModel, customerRequest.getSenha(), getPasswordEncoding());//(customerModel, customerRequest.getSenha());
			modelService.save(customerModel);
			modelService.refresh(customerModel);

			for (final Endereco addressRequest : listaEndereco)
			{
				final AddressModel addressModel = modelService.create(AddressModel.class);
				addressModel.setOwner(customerModel);
				addressModel.setDddPhone(addressRequest.getDdd());
				
				if(addressRequest.getTelefone() != null){
					addressModel.setPhone1(addressRequest.getTelefone().replaceAll("[^\\d.]", ""));
				}
				
				addressModel.setDddCellPhone(addressRequest.getDddCelular());
				
				if(addressRequest.getCelular() != null){
					addressModel.setCellphone(addressRequest.getCelular().replaceAll("[^\\d.]", ""));
				}
				
				addressModel.setShippingAddress(Boolean.TRUE);
				addressModel.setTitle(customerModel.getTitle());

				if (StringUtils.isNotBlank(customerModel.getName()))
				{
					final String[] splitName = customerNameStrategy.splitName(customerModel.getName());

					if (splitName != null && splitName.length == 2)
					{
						final String first = splitName[0];
						final String last = splitName[1];

						addressModel.setFirstname(first);
						addressModel.setLastname(last);

						addressModel.setReceiver(customerNameStrategy.getName(first, last));
					}
				}

				final String tipoEndereco = addressRequest.getTipoEndereco();

				if ("1".equals(tipoEndereco) || "2".equals(tipoEndereco))
				{
					addressModel.setTipoDeEndereco(TipoDeEndereco.RESIDENCIAL);
				}
				else
				{
					addressModel.setTipoDeEndereco(TipoDeEndereco.COMERCIAL);
				}

				if (addressRequest.getPais().equalsIgnoreCase("BRASIL"))
				{
					final CountryModel country = commonI18NService.getCountry("BR");
					addressModel.setCountry(country);
					addressModel.setRegion(commonI18NService.getRegion(country, "BR-" + addressRequest.getUf()));
				}
				else
				{
					throw new Exception("País inválido.");
				}

				addressModel.setTown(addressRequest.getCidade());
				addressModel.setDistrict(addressRequest.getBairro());
				addressModel.setComplemento(addressRequest.getComplemento());

				addressModel.setPostalcode(addressRequest.getCep());
				addressModel.setStreetname(addressRequest.getLogradouro());
				addressModel.setStreetnumber(addressRequest.getNumero());

				//TODO nao vamos consumir agora
				addressRequest.getDescricaoEndereco();
				addressRequest.getEstadoExterior();

				if (addressRequest.isEnderecoPrincipal())
				{
					addressModel.setDddPhone(customerRequest.getDdd());
					addressModel.setPhone1(customerRequest.getTelefone());
					customerModel.setDefaultPaymentAddress(addressModel);
					customerModel.setDefaultShipmentAddress(addressModel);
				}
				customerAccountService.saveAddressEntry(customerModel, addressModel);
			}
			createErrorMessageList(CHAVE_CLIENTE + "=" + customerRequest.getCodigoCliente(),
					CHAVE_PK_CLIENTE + "=" + customerModel.getPk());
		}
		catch (final Exception e)
		{
			LOG.error("error", e);
			if (customerModel != null && !customerModel.getAddresses().isEmpty())
			{
				modelService.removeAll(customerModel.getAddresses());
			}
			if (customerModel != null)
			{
				modelService.remove(customerModel);
			}
			createErrorMessageList(CHAVE_CLIENTE + "=" + customerRequest.getCodigoCliente(), MENSAGEM + "=" + e.getMessage());
		}
	}

	/**
	 * @param customerRequest
	 * @return
	 */
	private TitleModel getTitle(final Cliente customerRequest)
	{
		TitleModel title = null;

		if (genderMapping.get(customerRequest.getSexo()) == Gender.FEMALE)
		{
			title = userService.getTitleForCode("ms");
		}
		else
		{
			title = userService.getTitleForCode("mr");
		}
		return title;
	}

	/**
	 * Initializes a customer with given registerData
	 */
	protected void setUidForRegister(final Cliente customerRequest, final CustomerModel customer)
	{
		customer.setUid(customerRequest.getEmail());
		customer.setOriginalUid(customerRequest.getEmail());
	}

	/**
	 * @return the passwordEncoding
	 */
	public String getPasswordEncoding()
	{
		return passwordEncoding;
	}

	/**
	 * @param passwordEncoding
	 *           the passwordEncoding to set
	 */
	public void setPasswordEncoding(final String passwordEncoding)
	{
		this.passwordEncoding = passwordEncoding;
	}
}