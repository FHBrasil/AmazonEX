/**
 * 
 */
package br.hering.core.notifyMe.dao;

import br.hering.core.model.HeringProductModel;

/**
 * @author Vinicius de Souza
 *
 */
public interface HeringNotifyMeSimilarProductDao
{
	HeringProductModel getProductForCode(String code) throws Exception;
}