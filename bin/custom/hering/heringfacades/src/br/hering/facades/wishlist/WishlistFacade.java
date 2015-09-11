/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package br.hering.facades.wishlist;

import java.util.List;

import br.hering.facades.wishlist.data.HeringWishlistData;
import br.hering.facades.wishlist.data.HeringWishlistEntryData;

import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;

/**
 * Facade to provide simple suggestions for a customer.
 */
public interface WishlistFacade {

	void removeWishlistEntryForProduct(String productCode);

	List<HeringWishlistData> getWishlistsByCurrentUser();

	HeringWishlistEntryData getWishlistEntryForProduct(String productCode);

	HeringWishlistData getDefaultWishlist();

	void addWishlistEntry(String productCode);

	SearchPageData<HeringWishlistEntryData> getPagedWishlistEntries();

	boolean hasWishlisEntryForProduct(String productCode);

	void updateWishlist(HeringWishlistData wishlistData);

	SearchPageData<HeringWishlistEntryData> getPagedWishlistEntriesByPK(
			String pk);

	HeringWishlistData getWishlistByPK(String pk);

	String getWishlistPK();

	void addToCart(String productCode);
}