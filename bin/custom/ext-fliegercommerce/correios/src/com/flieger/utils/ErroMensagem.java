package com.flieger.utils;

import java.util.HashMap;


/**
 * classe responsavel por manipular informações da mensagem de retorno que contem o erro
 * 
 * @author Alexandre Santos
 * 
 */
public abstract class ErroMensagem
{

	private static HashMap<String, String> mensagemDoErro = new HashMap<String, String>();

	public static HashMap<String, String> getMensagemDoErro()
	{
		if (mensagemDoErro.isEmpty())
		{
			setMensagems();
		}
		return mensagemDoErro;
	}

	private static void setMensagems()
	{

		mensagemDoErro.put("0", "Processamento com Sucesso");
		mensagemDoErro.put("-1", "Código de Serviço Inválido");
		mensagemDoErro.put("-2", "CEP de Origem Inválido");
		mensagemDoErro.put("-3", "CEP de Destino Inválido");
		mensagemDoErro.put("-4", "Peso Excedido");
		mensagemDoErro.put("-5", "O Valor Declarado não deve exceder R$ 10.000,00");
		mensagemDoErro.put("-6", "Serviço indisponível para o trecho informado");
		mensagemDoErro.put("-7", "O Valor Declarado é obrigatório para este serviço");
		mensagemDoErro.put("-8", "Este serviço não aceita Mão Propria");
		mensagemDoErro.put("-9", "Este serviço não aceita Aviso de Recebimento");
		mensagemDoErro.put("-10", "Precificação indisponível para o trecho informado");
		mensagemDoErro
				.put("-11",
						"Para definição do preço deverão ser informados, também, o comprimento, a largura e altura do objeto em centímetros (cm)");
		mensagemDoErro.put("-12", "Comprimento Inválido");
		mensagemDoErro.put("-13", "Largura Inválida");
		mensagemDoErro.put("-14", "Altura Inválida");
		mensagemDoErro.put("-15", "O comprimento não pode ser maior que 105cm");
		mensagemDoErro.put("-16", "A largura não pode ser maior que 105cm");
		mensagemDoErro.put("-17", "A altura não pode ser maior que 105cm");
		mensagemDoErro.put("-18", "A altura não pode ser inferior a 2cm");
		mensagemDoErro.put("-20", "A largura não pode ser inferior a 11cm");
		mensagemDoErro.put("-22", "O comprimento não pode ser inferior a 16cm");
		mensagemDoErro.put("-23", "A soma resultante do comprimento + largura + altura não deve superar a 200cm");
		mensagemDoErro.put("-25", "Diâmetro inválido");
		mensagemDoErro.put("-26", "Informe o comprimento");
		mensagemDoErro.put("-27", "Informe o diâmetro");
		mensagemDoErro.put("-29", "O diâmetro não pode ser maior que 91cm");
		mensagemDoErro.put("-30", "O comprimento não pode ser inferior a 18cm");
		mensagemDoErro.put("-31", "O diâmetro não pode ser inferior a 5cm");
		mensagemDoErro.put("-32", "A soma resultante do comprimento + o dobro do diâmetro não pode superar a 200cm");
		mensagemDoErro.put("-33", "Sistema temporariamente fora do ar. Favor tentar mais tarde");
		mensagemDoErro.put("-34", "Código administrativo ou senha inválidos");
		mensagemDoErro.put("-35", "Senha incorreta");
		mensagemDoErro.put("-36", "Cliente não possui contrato vigente com os correios");
		mensagemDoErro.put("-37", "Cliente não possui serviço ativo em seu contrato.");
		mensagemDoErro.put("-38", "Serviço indisponível para este código administrativo.");
		mensagemDoErro.put("-39", "Peso excedido para o formato envelope");
		mensagemDoErro
				.put("-40",
						"Para definicao do preco deverao ser informados, tambem, o comprimento e a largura e altura do objeto em centimetros (cm).");
		mensagemDoErro.put("-41", "O comprimento nao pode ser maior que 60 cm.");
		mensagemDoErro.put("-42", "O comprimento nao pode ser inferior a 16 cm.");
		mensagemDoErro.put("-43", "A soma resultante do comprimento + largura nao deve superar a 120 cm.");
		mensagemDoErro.put("-44", "A largura nao pode ser inferior a 11 cm.");
		mensagemDoErro.put("-45", "A largura nao pode ser maior que 60 cm.");
		mensagemDoErro.put("-888", "Erro ao calcular a tarifa");
		mensagemDoErro.put("006", "Localidade de origem não abrange o serviço informado");
		mensagemDoErro.put("007", "Localidade de destino não abrange o serviço informado");
		mensagemDoErro.put("008", "Serviço indisponível para o trecho informado");
		mensagemDoErro.put("009", "EP inicial pertencente a Área de Risco.");
		mensagemDoErro
				.put("010",
						"CEP final pertencente a Área de Risco. A entrega será realizada, temporariamente, na agência mais próxima do endereço do destinatário.");
		mensagemDoErro.put("011", "CEP inicial e final pertencentes a Área de Risco");
		mensagemDoErro.put("7", "Serviço indisponível, tente mais tarde");
		mensagemDoErro.put("99", "Outros erros diversos do .Net");

	}
}
