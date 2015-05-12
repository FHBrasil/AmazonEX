/**
 * 
 */
package br.hering.core.util;

/**
 * CLASSE RESPONSAVEL
 * POR
 * TODAS LOGICAS 
 * REFERENTES A STRING DO PROJETO
 * @author HERING
 *
 */
public class StringUtils
{
	private static final String IMAGE_EXTENSAO = ".jpg";
	private static final String IMAGE_EXTENSAO_B = "B.jpg";

	public static String getSubNameImagem(String imageUrl){
		String subEnd = imageUrl.replace("-", "");
		int end = subEnd.indexOf(IMAGE_EXTENSAO);
		int endComB = subEnd.contains(IMAGE_EXTENSAO_B) ? end - 1 : end;
		
		subEnd = subEnd.substring(endComB - 2, end);
		
		return subEnd;
	}
}
