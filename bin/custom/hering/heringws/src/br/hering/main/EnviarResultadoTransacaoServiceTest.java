package br.hering.main;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;

import br.hering.webservices.message.transacao.Resultado;
import br.hering.webservices.message.transacao.ResultadoTransacaoRequest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/webroot/WEB-INF/heringws-test-spring.xml")
public class EnviarResultadoTransacaoServiceTest
{
	@Autowired
	private WebServiceTemplate enviarResultadoTransacaoWSTemplate;

	@Test
	public void testEnviarPedido()
	{
		System.out.println("teste 5");

		final Resultado resultado = new Resultado();
		resultado.setChave("produto");
		resultado.setMensagem("ZL44#nome vazio");
		resultado.setTransacao("Produtos");

		final ResultadoTransacaoRequest request = new ResultadoTransacaoRequest();
		request.getResultado().add(resultado);

		final Object response = enviarResultadoTransacaoWSTemplate.marshalSendAndReceive(request);

		System.out.println(response);
	}
}
