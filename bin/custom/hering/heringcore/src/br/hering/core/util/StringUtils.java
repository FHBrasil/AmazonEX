/**
 * 
 */
package br.hering.core.util;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import br.hering.facades.product.impl.HeringProductImagePopulatorHelper;

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
	final static Pattern p = Pattern.compile("[a-zA-Z]");
	
	private static final Logger LOG = Logger.getLogger(StringUtils.class);

	
	public static String getSubNameImagem(String imageUrl){
		String subEnd = imageUrl.replace("-", "");
		int end = subEnd.indexOf(".jpg");
		int endComB = subEnd.matches("[a-zA-Z].jpg") ? end - 1 : end;
		
		subEnd = subEnd.substring(endComB - 2, end);
		
		return subEnd;
	}
}
