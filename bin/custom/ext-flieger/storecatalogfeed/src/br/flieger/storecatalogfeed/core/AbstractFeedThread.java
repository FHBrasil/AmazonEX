/**
 * 
 */
package br.flieger.storecatalogfeed.core;

import static br.flieger.storecatalogfeed.constants.StorecatalogfeedConstants.FILE_PATH;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.user.UserManager;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Arrays;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import br.flieger.storecatalogfeed.dao.DefaultStoreFeedDao;
import br.flieger.storecatalogfeed.xml.template.XMLTemplate;

/**
 * @author franthescollymaneira
 *
 */
public abstract class AbstractFeedThread extends Thread {

    protected final Logger LOG = Logger.getLogger(this.getClass());
    private String xmlTemplate;
    private CatalogVersionModel catalogVersion;
    @Resource
    private DefaultStoreFeedDao storeFeedDao;
    @Resource
    private CatalogVersionService catalogVersionService;
    @Resource
    protected CommonI18NService commonI18NService;


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        if (!Registry.hasCurrentTenant()) {
            Registry.activateMasterTenant();
        }
        getCatalogVersionService().setSessionCatalogVersions(Arrays.asList(getCatalogVersion()));
        JaloSession.getCurrentSession().setUser(UserManager.getInstance().getAdminEmployee());
        commonI18NService.setCurrentLanguage(commonI18NService.getLanguage("pt"));
        commonI18NService.setCurrentCurrency(commonI18NService.getCurrency("BRL"));
    }


    protected XMLTemplate<ProductModel> createTemplate(final String templateBeanName) {
        final ApplicationContext appCtx = Registry.getApplicationContext();
        final XMLTemplate<ProductModel> templateBean = appCtx.getBean(
                templateBeanName + "Template", XMLTemplate.class);
        return templateBean;
    }


    protected String getExportFilePath() {
        StringBuilder path = new StringBuilder().append(getPathByStore()).append(getXmlTemplate())
                .append("/");
        return path.toString();
    }


    protected String getPathByStore() {
        final BaseStoreModel store = catalogVersion.getCatalog().getBaseStores().iterator().next();
        final String path = String.format(FILE_PATH, store.getUid());
        return path + "/";
    }


    /**
     * @return the catalogVersionService
     */
    public CatalogVersionService getCatalogVersionService() {
        return catalogVersionService;
    }


    /**
     * @param catalogVersionService
     *            the catalogVersionService to set
     */
    public void setCatalogVersionService(CatalogVersionService catalogVersionService) {
        this.catalogVersionService = catalogVersionService;
    }


    /**
     * @return the storeFeedDao
     */
    public DefaultStoreFeedDao getStoreFeedDao() {
        return storeFeedDao;
    }


    /**
     * @param storeFeedDao
     *            the storeFeedDao to set
     */
    public void setStoreFeedDao(DefaultStoreFeedDao storeFeedDao) {
        this.storeFeedDao = storeFeedDao;
    }


    /**
     * @return the catalogVersion
     */
    public CatalogVersionModel getCatalogVersion() {
        return catalogVersion;
    }


    /**
     * @param catalogVersion
     *            the catalogVersion to set
     */
    public void setCatalogVersion(CatalogVersionModel catalogVersion) {
        this.catalogVersion = catalogVersion;
    }


    /**
     * @return the xmlTemplate
     */
    public String getXmlTemplate() {
        return xmlTemplate;
    }


    /**
     * @param xmlTemplate
     *            the xmlTemplate to set
     */
    public void setXmlTemplate(String xmlTemplate) {
        this.xmlTemplate = xmlTemplate;
    }
}