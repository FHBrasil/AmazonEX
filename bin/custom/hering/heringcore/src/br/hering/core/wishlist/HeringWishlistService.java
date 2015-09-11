/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.hering.core.wishlist;

import br.hering.core.model.HeringWishlistModel;

import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.wishlist2.Wishlist2Service;
import de.hybris.platform.wishlist2.model.Wishlist2EntryModel;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

/**
 *
 * @author flieger
 */
public interface HeringWishlistService extends Wishlist2Service {

    HeringWishlistModel createDefaultHeringWishlist(UserModel user, String name, String description, String publicName);

    HeringWishlistModel createHeringWishlist(UserModel user, String name, String description, String publicName);

    boolean hasWishlisEntryForProduct(String productCode, Wishlist2Model wishlist);
    
    void updateHeringWishlist(HeringWishlistModel wishlistModel, UserModel user);
    
    HeringWishlistModel getHeringWishlistByPK(PK pk);

	void removeWishlistEntryForProduct(String productCode, Wishlist2Model defaultWishlist);

	Wishlist2EntryModel getWishlistEntryForProduct(String productCode, Wishlist2Model wishlist);
}