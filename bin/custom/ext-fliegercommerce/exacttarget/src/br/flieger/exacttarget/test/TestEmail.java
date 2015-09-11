/**
 * 
 */
package br.flieger.exacttarget.test;

import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.Properties;

import javax.xml.rpc.ServiceException;

import org.apache.axis.EngineConfiguration;
import org.apache.axis.configuration.FileProvider;

import br.flieger.exacttarget.wsdl.api.APIObject;
import br.flieger.exacttarget.wsdl.api.Attribute;
import br.flieger.exacttarget.wsdl.api.CreateOptions;
import br.flieger.exacttarget.wsdl.api.CreateRequest;
import br.flieger.exacttarget.wsdl.api.CreateResponse;
import br.flieger.exacttarget.wsdl.api.PartnerAPI;
import br.flieger.exacttarget.wsdl.api.PartnerAPILocator;
import br.flieger.exacttarget.wsdl.api.Soap;
import br.flieger.exacttarget.wsdl.api.Subscriber;
import br.flieger.exacttarget.wsdl.api.SubscriberStatus;


/**
 * @author flieger
 * 
 */
public class TestEmail
{

	/**
	 * @param args
	 */
	public static void main(final String[] args)
	{
		Double n = 1456.6d;
		
		DecimalFormat DECIAL_FORMAT = new DecimalFormat( "#.##0,00" );
		
		System.out.println(DECIAL_FORMAT.format(n));
		/*
		try
		{
			//new TestEmail().testAddSubscriber();
		}
		catch (final RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
	}

	public void testAddSubscriber() throws RemoteException
	{
		final Soap stub = init();
		final Subscriber subscriber = new Subscriber();

		subscriber.setEmailAddress("vinicius0386@gmail.com");
		subscriber.setSubscriberKey(subscriber.getEmailAddress()); // unique identifier
		subscriber.setStatus(SubscriberStatus.Active);
		final Attribute a1 = new Attribute();
		a1.setName("ByAPI");
		a1.setValue("Yes");
		final Attribute a2 = new Attribute();
		a2.setName("first_name");
		a2.setValue("Vinicius de Souza");

		subscriber.setAttributes(new Attribute[]
		{ a1, a2 });

		final APIObject[] apiObjects =
		{ subscriber };
		final CreateRequest createRequest = new CreateRequest(new CreateOptions(), apiObjects);
		final CreateResponse createResponse = stub.create(createRequest);
		System.out.println("Subscriber created in list ::: " + createResponse.getOverallStatus());
		System.out.println("Teste 1");
	}

	public Soap init()
	{
		final Properties prop = getProperties();

		final String clientWSDD = prop.getProperty("clientWSDD");

		//Configure Axis client with web service description file
		final EngineConfiguration config = new FileProvider(clientWSDD);

		//Create PartnerAPI stub with ExactTarget Web Service API endpoint and Axis configuration
		final PartnerAPI service = new PartnerAPILocator(config);

		try
		{
			return service.getSoap();
		}
		catch (final ServiceException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Properties getProperties()
	{
		final Properties pro = new Properties();
		try
		{
			final FileInputStream fileInputStream = new FileInputStream("axisv14-client-test-properties.xml");
			pro.loadFromXML(fileInputStream);
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		return pro;
	}
}
