/**
 * 
 */
package br.flieger.exacttarget.wsdl;

import de.hybris.platform.util.Config;

import java.io.IOException;

import javax.xml.rpc.ServiceException;

import org.apache.axis.EngineConfiguration;
import org.apache.axis.configuration.FileProvider;

import br.flieger.exacttarget.wsdl.api.PartnerAPI;
import br.flieger.exacttarget.wsdl.api.PartnerAPILocator;
import br.flieger.exacttarget.wsdl.api.Soap;

/**
 * Classe facilitador de acesso comum.
 * 
 * @author Vinicius de Souza
 *
 */
public class Util
{
	
	/**
	 * Inicio do objeto de acesso a API Exacttarget.
	 * 
	 * @return Retorna objeto <code>Soap</code> da API.
	 * @throws IOException
	 *            Lançado quando falha o carregamento do arquivo.
	 * @throws ServiceException
	 *            Lançado quando ocorrer um problema de acesso a API.
	 */
	public static Soap initSoap() throws IOException, ServiceException
	{
		final String clientWSDD = Config.getParameter("exacttarget.clientWSDD");

		//Configure Axis client with web service description file
		final EngineConfiguration config = new FileProvider(clientWSDD);

		//Create PartnerAPI stub with ExactTarget Web Service API endpoint and Axis configuration
		final PartnerAPI service = new PartnerAPILocator(config);

		return service.getSoap();
	}

}