package com.flieger.webservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.flieger.data.CorreiosPropertiesData;
import com.flieger.utils.ErroMensagem;


public class CalculoFreteCorreio
{

	public CorreiosPropertiesData calcular(final CorreiosPropertiesData obj)
	{

		//URL do webservice correio para calculo de frete  
		String urlString = "http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx";

		// os parametros a serem enviados  
		final Properties parameters = new Properties();

		parameters.setProperty("nCdEmpresa", obj.getNCdEmpresa());
		parameters.setProperty("sDsSenha", obj.getSDsSenha());
		parameters.setProperty("nCdServico", obj.getNCdServico());
		parameters.setProperty("sCepOrigem", obj.getSCepOrigem());
		parameters.setProperty("sCepDestino", obj.getSCepDestino());
		parameters.setProperty("nVlPeso", obj.getNVlPeso());
		parameters.setProperty("nCdFormato", obj.getNCdFormato());
		parameters.setProperty("nVlComprimento", obj.getNVlComprimento());
		parameters.setProperty("nVlAltura", obj.getNVlAltura());
		parameters.setProperty("nVlLargura", obj.getNVlLargura());
		parameters.setProperty("nVlDiametro", obj.getNVlDiametro());
		parameters.setProperty("sCdMaoPropria", obj.getSCdMaoPropria());
		parameters.setProperty("nVlValorDeclarado", obj.getNVlValorDeclarado());
		parameters.setProperty("sCdAvisoRecebimento", obj.getSCdAvisoRecebimento());
		parameters.setProperty("strRetorno", "xml");

		// o iterador, para criar a URL  
		final Iterator i = parameters.keySet().iterator();
		// o contador  
		int counter = 0;

		// enquanto ainda existir parametros  
		while (i.hasNext())
		{

			// pega o nome  
			final String name = (String) i.next();
			// pega o valor  
			final String value = parameters.getProperty(name);

			// adiciona com um conector (? ou &)  
			// o primeiro é ?, depois são &  
			urlString += (++counter == 1 ? "?" : "&") + name + "=" + value;

		}

		try
		{
			// cria o objeto url  
			final URL url = new URL(urlString);

			// cria o objeto httpurlconnection  
			final HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// seta um connect timeout
			connection.setConnectTimeout(2000);

			// seta um read timeout
			connection.setReadTimeout(2000);

			// seta o metodo  
			connection.setRequestProperty("Request-Method", "GET");

			// seta a variavel para ler o resultado  
			connection.setDoInput(true);
			connection.setDoOutput(false);

			// conecta com a url destino  
			connection.connect();

			// abre a conexão pra input  
			final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			// le ate o final  
			final StringBuffer newData = new StringBuffer();
			String s = "";
			while (null != ((s = br.readLine())))
			{
				newData.append(s);
			}
			br.close();

			//Prepara o XML que está em string para executar leitura por nodes  
			final DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			final InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(newData.toString()));
			final Document doc = db.parse(is);
			final NodeList nodes = doc.getElementsByTagName("cServico");

			//Faz a leitura dos nodes  
			for (int j = 0; j < nodes.getLength(); j++)
			{
				final Element element = (Element) nodes.item(j);
				Element lineCodigo;
				Element lineValor;
				Element linePrazoEntrega;
				Element lineValorMaoPropria;
				Element lineValorAvisoRecebimento;
				Element lineValorValorDeclarado;
				Element lineEntregaDomiciliar;
				Element lineEntregaSabado;
				Element lineErro;

				final NodeList codigo = element.getElementsByTagName("Codigo");
				final NodeList valor = element.getElementsByTagName("Valor");
				final NodeList prazoEntrega = element.getElementsByTagName("PrazoEntrega");
				final NodeList valorMaoPropria = element.getElementsByTagName("ValorMaoPropria");
				final NodeList valorAvisoRecebimento = element.getElementsByTagName("ValorAvisoRecebimento");
				final NodeList valorValorDeclarado = element.getElementsByTagName("ValorValorDeclarado");
				final NodeList entregaDomiciliar = element.getElementsByTagName("EntregaDomiciliar");
				final NodeList entregaSabado = element.getElementsByTagName("EntregaSabado");
				final NodeList erro = element.getElementsByTagName("Erro");

				lineCodigo = (Element) codigo.item(j);
				lineValor = (Element) valor.item(j);
				linePrazoEntrega = (Element) prazoEntrega.item(j);
				lineValorMaoPropria = (Element) valorMaoPropria.item(j);
				lineValorAvisoRecebimento = (Element) valorAvisoRecebimento.item(j);
				lineValorValorDeclarado = (Element) valorValorDeclarado.item(j);
				lineEntregaDomiciliar = (Element) entregaDomiciliar.item(j);
				lineEntregaSabado = (Element) entregaSabado.item(j);
				lineErro = (Element) erro.item(j);

				if (obj.getStrRetorno() == null)
				{
					obj.setStrRetorno(new HashMap<String, String>());
				}
				obj.getStrRetorno().put("Codigo", getCharacterDataFromElement(lineCodigo));
				obj.getStrRetorno().put("Valor", getCharacterDataFromElement(lineValor));
				obj.getStrRetorno().put("PrazoEntrega", getCharacterDataFromElement(linePrazoEntrega));
				obj.getStrRetorno().put("ValorMaoPropria", getCharacterDataFromElement(lineValorMaoPropria));
				obj.getStrRetorno().put("ValorAvisoRecebimento", getCharacterDataFromElement(lineValorAvisoRecebimento));
				obj.getStrRetorno().put("ValorValorDeclarado", getCharacterDataFromElement(lineValorValorDeclarado));
				obj.getStrRetorno().put("EntregaDomiciliar", getCharacterDataFromElement(lineEntregaDomiciliar));
				obj.getStrRetorno().put("EntregaSabado", getCharacterDataFromElement(lineEntregaSabado));
				obj.getStrRetorno().put("Erro", ErroMensagem.getMensagemDoErro().get(getCharacterDataFromElement(lineErro)));
			}

		}
		catch (final SocketTimeoutException e)
		{
			new SocketTimeoutException(e.getMessage() + " tempo excedido");
		}
		catch (final Exception e)
		{
			new Exception(e.getMessage());
		}

		return obj;
	}

	public static String getCharacterDataFromElement(final Element e)
	{
		final Node child = e.getFirstChild();
		if (child instanceof CharacterData)
		{
			final CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}
}
