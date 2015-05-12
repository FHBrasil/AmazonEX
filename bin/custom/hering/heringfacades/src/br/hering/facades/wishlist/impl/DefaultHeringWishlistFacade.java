/**
 *
 */
package br.hering.facades.wishlist.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.wishlist2.enums.Wishlist2EntryPriority;
import de.hybris.platform.wishlist2.model.Wishlist2EntryModel;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.ArrayList;
import java.util.List;

import br.hering.core.model.HeringWishlistModel;
import br.hering.core.wishlist.HeringWishlistService;
import br.hering.facades.wishlist.WishlistFacade;
import br.hering.facades.wishlist.data.HeringWishlistData;
import br.hering.facades.wishlist.data.HeringWishlistEntryData;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.PK;
import java.util.Arrays;

/**
 * @author flieger
 *
 */
public class DefaultHeringWishlistFacade implements WishlistFacade {

    private static final String WLDEFAULT = "default";
    private HeringWishlistService heringWishlistService;
    private ProductService productService;
    private ProductFacade productFacade;
    private UserService userService;
    private Converter<Wishlist2Model, HeringWishlistData> heringWishlistConverter;
    private Converter<Wishlist2EntryModel, HeringWishlistEntryData> heringWishlistEntryConverter;

    /*
     * (non-Javadoc)
     * 
     * @see br.hering.facades.suggestion.WishlistFacade#removeWishlistEntryForProduct(java.lang.String)
     */
    @Override
    public void removeWishlistEntryForProduct(final String productCode) {

        if (hasDefaultWishlist() && getDefaultWishlist().getEntries().size() > 0) {

            final ProductModel product = getProductService().getProductForCode(productCode);
            getHeringWishlistService().removeWishlistEntryForProduct(product,
                    getHeringWishlistService().getDefaultWishlist(getUserService().getCurrentUser()));

        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * br.hering.facades.suggestion.WishlistFacade#hasDefaultWishlist(de.hybris.platform.core.model.user.UserModel)
     */
    private boolean hasDefaultWishlist() {

        return getHeringWishlistService().hasDefaultWishlist(getUserService().getCurrentUser());

    }

    /*
     * (non-Javadoc)
     * 
     * @see br.hering.facades.suggestion.WishlistFacade#getWishlists(de.hybris.platform.core.model.user.UserModel)
     */
    @Override
    public List<HeringWishlistData> getWishlistsByCurrentUser() {

        final List<Wishlist2Model> wishlists = getHeringWishlistService().getWishlists(getUserService().getCurrentUser());
        final List<HeringWishlistData> heringWishlistsData = new ArrayList<HeringWishlistData>();
        //testar com cast caso nao funcione
        for (final Wishlist2Model wishlist2Model : wishlists) {
            heringWishlistsData.add(getHeringWishlistConverter().convert(wishlist2Model));
        }

        return heringWishlistsData;

    }

    /*
     * (non-Javadoc)
     * 
     * @see br.hering.facades.suggestion.WishlistFacade#getWishlistEntryForProduct(java.lang.String)
     */
    @Override
    public HeringWishlistEntryData getWishlistEntryForProduct(final String productCode) {

        Wishlist2EntryModel wishlistEntry = null;

        if (hasDefaultWishlist() && getDefaultWishlist().getEntries().size() > 0) {

            final ProductModel product = getProductService().getProductForCode(productCode);
            wishlistEntry = getHeringWishlistService().getWishlistEntryForProduct(product, getDefaultWishlistModel());

            return getHeringWishlistEntryConverter().convert(wishlistEntry);

        }


        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * br.hering.facades.suggestion.WishlistFacade#getDefaultWishlist(de.hybris.platform.core.model.user.UserModel)
     */
    @Override
    public HeringWishlistData getDefaultWishlist() {


        final HeringWishlistData heringWishlistData = getHeringWishlistConverter().convert(getDefaultWishlistModel());
        return heringWishlistData;

    }

    /*
     * (non-Javadoc)
     * 
     * @see br.hering.facades.suggestion.WishlistFacade#createWishlist(de.hybris.platform.core.model.user.UserModel)
     */
    private HeringWishlistModel getDefaultWishlistModel() {

        HeringWishlistModel wishlistModel = null;
        if (hasDefaultWishlist()) {
            wishlistModel = (HeringWishlistModel) getHeringWishlistService().getDefaultWishlist(
                    getUserService().getCurrentUser());
        } else {
            wishlistModel = createDefaultWishlist();
        }
        return wishlistModel;

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * br.hering.facades.suggestion.WishlistFacade#createDefaultWishlist(de.hybris.platform.core.model.user.UserModel)
     */
    private HeringWishlistModel createDefaultWishlist() {

        HeringWishlistModel wishlist = getHeringWishlistService().createDefaultHeringWishlist(getUserService().getCurrentUser(),
                WLDEFAULT, "Minha Lista de desejos", "Lista do meu evento");
        return wishlist;

    }

    /*
     * (non-Javadoc)
     * 
     * @see br.hering.facades.suggestion.WishlistFacade#addWishlistEntry(java.lang.String)
     */
    @Override
    public void addWishlistEntry(final String productCode) {

        final ProductModel product = getProductService().getProductForCode(productCode);
        getHeringWishlistService().addWishlistEntry(getDefaultWishlistModel(), product, Integer.valueOf(1),
                Wishlist2EntryPriority.MEDIUM, "good");

    }

    @Override
    public SearchPageData<HeringWishlistEntryData> getPagedWishlistEntries() {

        HeringWishlistModel wishlist = getDefaultWishlistModel();
        return getPagedWishlistEntries(wishlist);

    }

    @Override
    public boolean hasWishlisEntryForProduct(String productCode) {
        
        if(hasDefaultWishlist()){
        ProductModel product = getProductService().getProductForCode(productCode);
        return getHeringWishlistService().hasWishlisEntryForProduct(product, getDefaultWishlistModel());
        } else{
            return false;
        }
    }
    
    @Override
    public void updateWishlist(HeringWishlistData wishlistData) {
        
        HeringWishlistModel wishModel = prepareToUpdate(wishlistData);
        getHeringWishlistService().updateHeringWishlist(wishModel, getUserService().getCurrentUser());
        
    }
    
    @Override
    public SearchPageData<HeringWishlistEntryData> getPagedWishlistEntriesByPK(String pk) {
        
        HeringWishlistModel wishlist = getHeringWishlistService().getHeringWishlistByPK(PK.parse(pk));
        return getPagedWishlistEntries(wishlist);
        
    }
    
    @Override
    public HeringWishlistData getWishlistByPK(String pk){
    
        return getHeringWishlistConverter().convert(getHeringWishlistService().getHeringWishlistByPK(PK.parse(pk)));
        
    }
    
    @Override
    public String getWishlistPK() {
        
        return getDefaultWishlistModel().getPk().toString();
        
    }
    
    private SearchPageData<HeringWishlistEntryData> getPagedWishlistEntries(HeringWishlistModel wishlist) {
    
        final SearchPageData<HeringWishlistEntryData> entriesResults = new SearchPageData<>();

        if (wishlist.getEntries() != null) {

            /*verify*/
            List<Wishlist2EntryModel> entries = wishlist.getEntries();
            List<HeringWishlistEntryData> entriesData = new ArrayList<>();
            for (Wishlist2EntryModel wishlist2EntryModel : entries) {

                HeringWishlistEntryData entryData = getHeringWishlistEntryConverter().convert(wishlist2EntryModel);
                entryData.setProduct(productFacade.getProductForOptions(wishlist2EntryModel.getProduct(), Arrays.asList(ProductOption.PRICE)));
                entriesData.add(entryData);
            }
            /*end*/

            entriesResults.setResults(entriesData);

        }


        return entriesResults;
    
    }
    
    private HeringWishlistModel prepareToUpdate(HeringWishlistData wishData){
    
        HeringWishlistModel wishModel = getDefaultWishlistModel();
        wishModel.setDescription(wishData.getDescription());
        wishModel.setName(wishData.getName());
        wishModel.setPublicName(wishData.getPublicName());
        List<Wishlist2EntryModel> entriesModel = new ArrayList<>();
        
        for (int i = 0; i < wishModel.getEntries().size(); i++) {
            
            Wishlist2EntryModel entry = new Wishlist2EntryModel();
            entry.setAddedDate(wishModel.getEntries().get(i).getAddedDate());
            entry.setComment(wishData.getEntries().get(i).getComment());
            entry.setDesired(wishData.getEntries().get(i).getDesired());
            entry.setPriority(wishModel.getEntries().get(i).getPriority());
            entry.setProduct(wishModel.getEntries().get(i).getProduct());
            entry.setReceived(wishModel.getEntries().get(i).getReceived());
            entry.setWishlist(wishModel);
            
            //removeWishlistEntryForProduct(wishModel.getEntries().get(i).getProduct().getCode());
            
            entriesModel.add(entry);
            
        }       
        
        wishModel.setEntries(entriesModel);
        return wishModel;
    
    }
    /*
     * (non-Javadoc)
     * 
     * @see br.hering.facades.suggestion.WishlistFacade#addWishlistEntryForWishlistPublicName(java.lang.String,
     * java.lang.String)
     */
    /**
     * @return the productService
     */
    public ProductService getProductService() {
        return productService;
    }

    public HeringWishlistService getHeringWishlistService() {
        return heringWishlistService;
    }

    public void setHeringWishlistService(HeringWishlistService heringWishlistService) {
        this.heringWishlistService = heringWishlistService;
    }

    /**
     * @param productService the productService to set
     */
    public void setProductService(final ProductService productService) {
        this.productService = productService;
    }

    /**
     * @return the userService
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * @param userService the userService to set
     */
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    /**
     * @return the heringWishlistConverter
     */
    public Converter<Wishlist2Model, HeringWishlistData> getHeringWishlistConverter() {
        return heringWishlistConverter;
    }

    /**
     * @param heringWishlistConverter the heringWishlistConverter to set
     */
    public void setHeringWishlistConverter(final Converter<Wishlist2Model, HeringWishlistData> heringWishlistConverter) {
        this.heringWishlistConverter = heringWishlistConverter;
    }

    /**
     * @return the heringWishlistEntryConverter
     */
    public Converter<Wishlist2EntryModel, HeringWishlistEntryData> getHeringWishlistEntryConverter() {
        return heringWishlistEntryConverter;
    }

    /**
     * @param heringWishlistEntryConverter the heringWishlistEntryConverter to set
     */
    public void setHeringWishlistEntryConverter(final Converter<Wishlist2EntryModel, HeringWishlistEntryData> heringWishlistEntryConverter) {
        this.heringWishlistEntryConverter = heringWishlistEntryConverter;
    }
    

    /*
     * (non-Javadoc)
     * 
     * @see br.hering.facades.wishlist.WishlistFacade#removeDefaultWishlist()
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * br.hering.facades.suggestion.WishlistFacade#createHeringWishlist(br.hering.facades.wishlist.data.HeringWishlistData)
     */

    public ProductFacade getProductFacade() {
        return productFacade;
    }

    public void setProductFacade(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    

    
}
