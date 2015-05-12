package com.flieger.enums;

/***
 * Enum responsavel por manipular informações do formato da encomenda
 * 
 * @author Alexandre Santos
 * 
 */
public enum FormatoDaEncomenda
{

	FORMATO_CAIXA_PACOTE("1"), FORMATO_ROLO_PRISMA("2"), ENVELOPE("3");

	private final String codigo;

	FormatoDaEncomenda(final String formato)
	{
		codigo = formato;
	}

	public String getValor()
	{
		return codigo;
	}
}
