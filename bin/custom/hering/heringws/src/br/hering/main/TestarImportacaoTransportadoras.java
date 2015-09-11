/**
 * 
 */
package br.hering.main;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;

import br.hering.webservices.message.delivery.Frete;
import br.hering.webservices.message.delivery.ImportarServicosTransporteRequest;
import br.hering.webservices.message.delivery.ServicoTransporte;


/**
 * @author franthescollymaneira
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/webroot/WEB-INF/heringws-test-spring.xml")
public class TestarImportacaoTransportadoras
{
	@Autowired
	private WebServiceTemplate testarImportacaoTransportadoraWSTemplate;

	public static final String DF_ERP_TO_HYBRIS = "yyyyMMdd HH:mm:ss";

	@Test
	public void testar()
	{
		for (int i = 0; i < 10; i++)
		{
			final String codigoTeste = i + "";

			System.out.println("teste " + codigoTeste);

			final ImportarServicosTransporteRequest request = new ImportarServicosTransporteRequest();

			final ServicoTransporte servico = new ServicoTransporte();

			servico.setCodigoSite("002");
			servico.setCodigoTransportadora("codigo_teste_" + codigoTeste);
			servico.setNomeTransportadora("nome_teste_" + codigoTeste);
			servico.setFormaEnvio("aereo");
			servico.setDataFinalVigencia("20140101 15:30:30");
			servico.setDataInicialVigencia("20141201 15:30:30");

			final Frete frete1 = new Frete();
			frete1.setCepInicial("08500000");
			frete1.setCepFinal("19999999");
			frete1.setFaixaPesoInicial(0);
			frete1.setFaixaPesoFinal(10);
			frete1.setCidade("Curitiba");
			frete1.setUf("PR");
			frete1.setPrazoEntrega(20);

			frete1.setValorPedagio(7.50D);
			frete1.setValorPercentual(0.10D);
			frete1.setValorPeso(10D);

			final Frete frete2 = new Frete();
			frete2.setCepInicial("08000000");
			frete2.setCepFinal("08499999");
			frete2.setFaixaPesoInicial(0);
			frete2.setFaixaPesoFinal(20);
			frete2.setCidade("Curitiba");
			frete2.setUf("PR");
			frete2.setPrazoEntrega(50);

			frete2.setValorPedagio(7.50D);
			frete2.setValorPercentual(0.10D);
			frete2.setValorPeso(20D);

			final List<Frete> fretes = new ArrayList<Frete>();
			fretes.add(frete1);
			fretes.add(frete2);

			request.setServicoTransporte(servico);
			request.getFrete().addAll(fretes);

			testarImportacaoTransportadoraWSTemplate.marshalSendAndReceive(request);
		}

	}
}
