package com.bazaarvoice.hybris.utils;

/**
 * Created by artlaber on 3/17/14.
 */
public abstract class BazaarVoiceUtils {
	public static final String ReplaceUnsupportedCharacters(String id) {
		StringBuilder result = new StringBuilder();
		for (int i=0; i<id.length(); i++) {
			if (Character.isLetterOrDigit(id.charAt(i))
					|| id.charAt(i) == '.'
					|| id.charAt(i) == '-'
					|| id.charAt(i) == '*'
					|| id.charAt(i) == '_'){
				result.append(id.charAt(i));
			}
			else {
				result.append('-');
			}
		}
		return result.toString();
	}
}
