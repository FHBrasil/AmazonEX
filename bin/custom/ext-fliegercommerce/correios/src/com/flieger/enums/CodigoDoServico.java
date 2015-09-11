package com.flieger.enums;

/***
 * Enum responsavel por manipular informações do código do serviço
 * 
 * @author Alexandre Santos
 * 
 */
public enum CodigoDoServico
{

	SEDEX_VAREJO("40010"), SEDEX_A_COBRAR_VAREJO("40045"), SEDEX_10_VAREJO("40215"), SEDEX_HOJE_VAREJO("40290"), PAC_VAREJO(
			"41106");

	private final String codigo;

	CodigoDoServico(final String codigo)
	{
		this.codigo = codigo;
	}

	public String getValor()
	{
		return this.codigo;
	}
}
