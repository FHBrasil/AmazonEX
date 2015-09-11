/**
 * 
 */
package br.hering.core.jobs;

import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.hering.core.model.jobs.FixTelephoneDDDMigrationJobModel;


/**
 * Modifies telephone information from migration.
 * <ul>
 * <li>If phone1 data is missing, fill from cellphone</li>
 * <li>If phone1 has data, but dddPhone is missing, guess from region + town</li>
 * </ul>
 * 
 * @author herbert
 *
 */
public class FixTelephoneDDDMigrationJobPerformable extends AbstractJobPerformable<FixTelephoneDDDMigrationJobModel>
{
	private static final Logger LOG = Logger.getLogger(FixTelephoneDDDMigrationJobPerformable.class);
	private static final Locale PT = Locale.forLanguageTag("pt");

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.CronJobModel
	 * )
	 */
	@Override
	public PerformResult perform(FixTelephoneDDDMigrationJobModel job)
	{
		LOG.info("CronJob Fix Telephone DDD Migration has started...");

		CronJobResult result = CronJobResult.SUCCESS;

		List<AddressModel> addressList = findAddressesWithProblem();

		if (CollectionUtils.isEmpty(addressList))
		{
			LOG.info("No addresses with telephones/cellphones to be fixed");
			return new PerformResult(result, CronJobStatus.FINISHED);
		}

		LOG.info("Found " + addressList.size() + " addresses with telephones/cellphones to fix");

		for (final AddressModel address : addressList)
		{
			try
			{
				String phone1 = address.getPhone1();
				String ddd = address.getDddPhone();
				RegionModel region = address.getRegion();// == null ? "" : address.getRegion().getIsocodeShort();
				String town = address.getTown();
				
				if (StringUtils.isNotBlank(address.getCellphone()))
				{
					phone1 = StringUtils.isBlank(phone1) ? address.getCellphone() : phone1;
					ddd = address.getDddCellPhone();
				}
				else if (StringUtils.isNotBlank(address.getPhone2()))
				{
					phone1 = StringUtils.isBlank(phone1) ? address.getPhone2() : phone1;
					ddd = address.getDddPhone();
				}
				else if (address.getOwner() != null && address.getOwner() instanceof UserModel)
				{
					final UserModel user = (UserModel) address.getOwner();
					for (final AddressModel a2 : user.getAddresses())
					{
						if (StringUtils.isNotBlank(a2.getPhone1()))
						{
							phone1 = StringUtils.isBlank(phone1) ? a2.getPhone1() : phone1;
							ddd = a2.getDddPhone();
						}
						else if (StringUtils.isNotBlank(a2.getPhone2()))
						{
							phone1 = StringUtils.isBlank(phone1) ? a2.getPhone2() : phone1;
							ddd = a2.getDddPhone();
						}
						else if (StringUtils.isNotBlank(a2.getCellphone()))
						{
							phone1 = StringUtils.isBlank(phone1) ? a2.getCellphone() : phone1;
							ddd = a2.getDddCellPhone();
						}

						region = a2.getRegion() == null ? region : a2.getRegion();
						town = a2.getTown();

						if (StringUtils.isNotBlank(phone1)
								&& StringUtils.isNotBlank(ddd)
								&& region != null
								&& StringUtils.isNotBlank(town))
						{
							break;
						}
					}
				}

				//neste ponto devemos ter o melhor match para phone1
				if (StringUtils.isBlank(phone1))
				{
					phone1 = "000000000";
				}
				if (StringUtils.isBlank(ddd))
				{
					ddd = getDddFromCity(region == null ? null : region.getIsocodeShort(),
							town);
				}

				address.setPhone1(phone1);
				address.setDddPhone(ddd);
				if( address.getRegion() == null ){
					address.setRegion(region);
				}

				LOG.info("Updating address pk:" + address.getPk());
				modelService.save(address);
				modelService.refresh(address);
			}
			catch (Exception e)
			{
				LOG.error("Impossível corrigir " + address.getPk() + " devido a " + e);
				result = CronJobResult.ERROR;
			}
		}
		return new PerformResult(result, CronJobStatus.FINISHED);
	}

	private List<AddressModel> findAddressesWithProblem()
	{
		try
		{
			StringBuilder sql = new StringBuilder("SELECT {PK} from {Address} ");
			// transfer cellphone to phone1 in case phone1 is null
			sql.append(" WHERE {phone1} is null OR {dddPhone} is null");
			Map<String, Object> params = new HashMap<String, Object>();
			return flexibleSearchService.<AddressModel> search(sql.toString(), params).getResult();
		}
		catch (Exception e)
		{
			LOG.error("Error findingAdressesWithProblem:" + e);
			return Collections.EMPTY_LIST;
		}
	}

	/**
	 * Obter o DDD de uma determinada cidade. fonte:
	 * https://code.google.com/p/alfredlibrary/source/browse/trunk/alfred/src
	 * /main/java/org/alfredlibrary/utilitarios/telefone/DDD.java
	 *
	 * @param region
	 *           Estado onde se encontra a cidade.
	 * @param town
	 *           Cidade.
	 * @return DDD da cidade.
	 */
	private String getDddFromCity(String region, String town)
	{
		try
		{
			if (StringUtils.isEmpty(region))
			{
				return "00";
			}
			if (StringUtils.isEmpty(town))
			{
				return "00";
			}
			town = trocarCaracteresAcentuados(town);
			String url = "http://wwp.brasilcenter.com.br/pr5e/WWWConsulta.asp";
			Map<String, String> parametros = new HashMap<String, String>();
			parametros.put("estado", region);
			parametros.put("cidade", town);
			parametros.put("idioma", "p");
			parametros.put("tc", "3");
			String conteudo = obterConteudoSite(url, "ISO-8859-1", parametros);
			if (conteudo == null || conteudo.indexOf("Localidade não encontrada") > -1)
			{
				return "00";
			}
			Pattern padrao = Pattern.compile(town.toUpperCase() + "(\\.)*" + region.toUpperCase() + "(\\.)*(\\d){2}");
			Matcher pesquisa = padrao.matcher(conteudo);
			while (pesquisa.find())
			{
				String linha = pesquisa.group();
				return linha.substring(linha.length() - 2);
			}
			return "00";
		}
		catch (Exception e)
		{
			LOG.error("Error searching ddd for " + region + ", " + town);
			return "00";
		}
	}

	/**
	 * Trocar os caracteres acentuados por seus equivalentes sem acentuação.
	 *
	 * @param frase
	 *           Frase que terá os caracteres trocados.
	 * @return Frase com os caracteres trocados.
	 */
	public String trocarCaracteresAcentuados(String frase)
	{
		if (frase == null)
			return frase;
		return frase.replaceAll("[ãâàáä]", "a").replaceAll("[êèéë]", "e").replaceAll("[îìíïĩ]", "i").replaceAll("[õôòóö]", "o")
				.replaceAll("[ûúùüũ]", "u").replaceAll("[ÃÂÀÁÄ]", "A").replaceAll("[ÊÈÉË]", "E").replaceAll("[ÎÌÍĨÏ]", "I")
				.replaceAll("[ÕÔÒÓÖ]", "O").replaceAll("[ÛÙÚŨÜ]", "U").replace('ç', 'c').replace('Ç', 'C').replace('ñ', 'n')
				.replace('Ñ', 'N');
	}

	/**
	 * Obter o conteúdo de um site.
	 *
	 * @param u
	 *           URL do Site.
	 * @return String contendo todo o conteúdo do site em HTML.
	 */
	private String obterConteudoSite(String u, String encode, Map<String, String> parametros)
	{
		URL url;
		try
		{
			StringBuilder strParams = new StringBuilder();
			if (parametros != null)
			{
				for (String chave : parametros.keySet())
				{
					strParams.append(URLEncoder.encode(chave, "UTF-8"));
					strParams.append("=");
					strParams.append(URLEncoder.encode(parametros.get(chave), encode));
					strParams.append("&");
				}
			}
			url = new URL(u);
			URLConnection conn = null;
			conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(strParams.toString());
			wr.flush();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charset.forName(encode)));
			String line;
			StringBuilder resultado = new StringBuilder();
			while ((line = rd.readLine()) != null)
			{
				resultado.append(line);
			}
			wr.close();
			rd.close();
			return resultado.toString();
		}
		catch (IOException e)
		{
			return null;
		}
	}

}
