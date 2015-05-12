package com.flieger.enums;

/***
 * Enum responsavel por manipular informações do serviço mão propria, ou serviço de aviso de recebimento
 * 
 * @author Alexandre Santos
 * 
 */
public enum MaoPropriaAvisoRecebimento
{

	SIM("S"), NAO("N");

	private final String opcao;

	MaoPropriaAvisoRecebimento(final String opcao)
	{
		this.opcao = opcao;
	}

	public String getCodigo()
	{
		return opcao;
	}
}
