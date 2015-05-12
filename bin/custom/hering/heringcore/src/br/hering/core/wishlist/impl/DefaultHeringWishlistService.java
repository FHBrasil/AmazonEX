/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.hering.core.wishlist.impl;

import br.hering.core.model.HeringWishlistModel;
import br.hering.core.wishlist.HeringWishlistService;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.exceptions.SystemException;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.wishlist2.impl.DefaultWishlist2Service;
import de.hybris.platform.wishlist2.model.Wishlist2Model;
import java.util.List;

/**
 *
 * @author flieger
 */
public class DefaultHeringWishlistService extends DefaultWishlist2Service implements HeringWishlistService {

    @Override
    public HeringWishlistModel createDefaultHeringWishlist(UserModel user, String name, String description, String publicName) {
        if (hasDefaultWishlist()) {
            throw new SystemException("An default wishlist for the user <" + user.getName() + "> already exists");
        }
        return createHeringWishlist(user, name, description, publicName, Boolean.TRUE);
    }

    @Override
    public HeringWishlistModel createHeringWishlist(UserModel user, String name, String description, String publicName) {
        return createHeringWishlist(user, name, description, publicName, Boolean.FALSE);
    }

    @Override
    public HeringWishlistModel getHeringWishlistByPK(PK pk) {
        
        
        return getModelService().get(pk);
        
    }

    private HeringWishlistModel createHeringWishlist(UserModel user, String name, String description, String publicName, Boolean defaultWL) {


        HeringWishlistModel wishlist = new HeringWishlistModel();
        wishlist.setName(name);
        wishlist.setDescription(description);
        wishlist.setDefault(defaultWL);
        wishlist.setUser(user);
        wishlist.setPublicName(publicName);
        if (saveWishlist(user)) {
            getModelService().save(wishlist);
        }

        return wishlist;

    }

    public void updateHeringWishlist(HeringWishlistModel wishlistModel, UserModel user) {



        if (saveWishlist(user)) {
            getModelService().save(wishlistModel);
        }



    }

    @Override
    public boolean hasWishlisEntryForProduct(ProductModel product, Wishlist2Model wishlist) {

        ServicesUtil.validateParameterNotNull(product, "Parameter 'product' was null.");
        ServicesUtil.validateParameterNotNull(wishlist, "Parameter 'wishlist' was null.");
        List entries = this.wishlistDao.findWishlistEntryByProduct(product, wishlist);
        if (entries.isEmpty()) {
            return false;
        }

        return true;

    }
}
