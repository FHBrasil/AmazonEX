/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.hering.core.wishlist.impl;

import br.hering.core.model.HeringWishlistModel;
import br.hering.core.wishlist.HeringWishlistService;
import br.hering.core.wishlist.impl.daos.HeringWishlist2Dao;

import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.SystemException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.wishlist2.impl.DefaultWishlist2Service;
import de.hybris.platform.wishlist2.model.Wishlist2EntryModel;
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

    @Override
	public void updateHeringWishlist(HeringWishlistModel wishlistModel, UserModel user) {



        if (saveWishlist(user)) {
            getModelService().save(wishlistModel);
        }



    }

	@Override
	public Wishlist2EntryModel getWishlistEntryForProduct(String productCode, Wishlist2Model wishlist)
	{
		ServicesUtil.validateParameterNotNull(productCode, "Parameter 'product' was null.");
		ServicesUtil.validateParameterNotNull(wishlist, "Parameter 'wishlist' was null.");
		List entries = getWishlistDao().findWishlistEntryByProduct(productCode, wishlist);
		if (entries.isEmpty())
		{
			throw new UnknownIdentifierException("Wishlist entry with product [" + productCode + "] in wishlist [" + 
					wishlist.getName() + " ] not found.");
		}
		if (entries.size() > 1)
		{
			throw new AmbiguousIdentifierException("Wishlist entry with product [" + productCode + "] in wishlist [" + 
					wishlist.getName() + "] is not unique, " + entries.size() + " entries found.");
		}
		return (Wishlist2EntryModel)entries.iterator().next();
	}

	@Override
	public boolean hasWishlisEntryForProduct(String productCode, Wishlist2Model wishlist)
	{
		ServicesUtil.validateParameterNotNull(productCode, "Parameter 'product' was null.");
		ServicesUtil.validateParameterNotNull(wishlist, "Parameter 'wishlist' was null.");
		List entries = getWishlistDao().findWishlistEntryByProduct(productCode, wishlist);
		if (entries.isEmpty())
		{
			return false;
		}
		return true;
	}

	@Override
	public void removeWishlistEntryForProduct(String productCode, Wishlist2Model wishlist)
	{
		Wishlist2EntryModel entry = getWishlistEntryForProduct(productCode, wishlist); /* 123 */
		removeWishlistEntry(wishlist, entry);
	}

	protected HeringWishlist2Dao getWishlistDao()
	{
		return (HeringWishlist2Dao) wishlistDao;
	}
}