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
	
	public static String getSubNameImagem(String imageUrl){
		String subEnd = imageUrl.replace("-", "");
		int end = subEnd.indexOf(".jpg");
		int endComB = subEnd.matches("[a-zA-Z].jpg") ? end - 1 : end;
		
		subEnd = subEnd.substring(endComB - 2, end);
		
		return subEnd;
	}
}
