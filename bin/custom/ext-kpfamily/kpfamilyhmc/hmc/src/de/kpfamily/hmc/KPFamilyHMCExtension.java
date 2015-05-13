package de.kpfamily.hmc;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import de.hybris.platform.hmc.AbstractEditorMenuChip;
import de.hybris.platform.hmc.AbstractExplorerMenuTreeNodeChip;
import de.hybris.platform.hmc.EditorTabChip;
import de.hybris.platform.hmc.extension.HMCExtension;
import de.hybris.platform.hmc.extension.MenuEntrySlotEntry;
import de.hybris.platform.hmc.generic.ClipChip;
import de.hybris.platform.hmc.generic.ToolbarActionChip;
import de.hybris.platform.hmc.webchips.Chip;
import de.hybris.platform.hmc.webchips.DisplayState;

/**
 * 
 * @author jfelipe
 *
 */
public class KPFamilyHMCExtension extends HMCExtension {

    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(KPFamilyHMCExtension.class.getName());
    public final static String RESOURCE_PATH = "de.kpfamily.hmc.locales";


    /**
     * @see HMCExtension#getTreeNodeChips(de.hybris.platform.hmc.webchips.DisplayState,
     *      de.hybris.platform.hmc.webchips.Chip)
     * 
     */
    @Override
    public List<AbstractExplorerMenuTreeNodeChip> getTreeNodeChips(final DisplayState displayState,
            final Chip parent) {
        return Collections.EMPTY_LIST;
    }


    /**
     * @see HMCExtension#getMenuEntrySlotEntries(de.hybris.platform.hmc.webchips.DisplayState,
     *      de.hybris.platform.hmc.webchips.Chip)
     * 
     */
    @Override
    public List<MenuEntrySlotEntry> getMenuEntrySlotEntries(final DisplayState displayState,
            final Chip parent) {
        return Collections.EMPTY_LIST;
    }


    /**
     * @see HMCExtension#getSectionChips(de.hybris.platform.hmc.webchips.DisplayState,
     *      de.hybris.platform.hmc.generic.ClipChip)
     * 
     */
    @Override
    public List<ClipChip> getSectionChips(final DisplayState displayState, final ClipChip parent) {
        return Collections.EMPTY_LIST;
    }


    /**
     * 
     * 
     */
    @Override
    public List<EditorTabChip> getEditorTabChips(final DisplayState displayState,
            final AbstractEditorMenuChip parent) {
        return Collections.EMPTY_LIST;
    }


    /**
     * @see HMCExtension#getToolbarActionChips(de.hybris.platform.hmc.webchips.DisplayState,
     *      de.hybris.platform.hmc.webchips.Chip)
     * 
     */
    @Override
    public List<ToolbarActionChip> getToolbarActionChips(final DisplayState displayState,
            final Chip parent) {
        return Collections.EMPTY_LIST;
    }


    /**
     * 
     * 
     */
    @Override
    public ResourceBundle getLocalizeResourceBundle(final Locale locale) {
        return null;
    }


    /**
     * 
     * @author jfelipe
     * 
     */
//    @Override
//    public ActionResult beforeSave(Item item, DisplayState displayState, Map currentValues,
//            Map initialValues) {
//        BeforeSaveAction<Item> beforeSaveAction = (BeforeSaveAction<Item>) ActionFactory
//                .getBeforeSaveAction(item.getClass());
//        if (beforeSaveAction != null) {
//            try {
//                beforeSaveAction.execute(item, currentValues, initialValues);
//                return super.beforeSave(item, displayState, currentValues, initialValues);
//            } catch (Throwable e) {
//                return new ActionResult(ActionResult.FAILED, e.getMessage(), false);
//            }
//        }
//        return new ActionResult(ActionResult.CANCELLED, "No Action to perform", false);
//    }


    /**
     * 
     * 
     */
    @Override
    public String getResourcePath() {
        return RESOURCE_PATH;
    }
}