/**
 * 
 */
package br.hering.core.wishlist.impl.daos;

import de.hybris.platform.wishlist2.impl.daos.Wishlist2Dao;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.List;

/**
 * @author herbert
 *
 */
public interface HeringWishlist2Dao extends Wishlist2Dao
{

	/**
	 * @param productCode
	 * @param wishlist
	 * @return
	 */
	List findWishlistEntryByProduct(String productCode, Wishlist2Model wishlist);

}