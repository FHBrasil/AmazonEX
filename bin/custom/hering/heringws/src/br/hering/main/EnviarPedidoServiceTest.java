package br.hering.main;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/webroot/WEB-INF/heringws-test-spring.xml")
public class EnviarPedidoServiceTest
{
	@Autowired
	private WebServiceTemplate enviarPedidosWSTemplate;

	@Test
	public void testEnviarPedido()
	{
		//
	}
}
