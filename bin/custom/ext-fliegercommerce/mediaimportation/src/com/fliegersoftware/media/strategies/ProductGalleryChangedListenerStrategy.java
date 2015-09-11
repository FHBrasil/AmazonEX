/**
 *
 */
package com.fliegersoftware.media.strategies;

import java.util.List;
import java.util.Map;

import com.fliegersoftware.media.events.GalleryChangedEvent;


/**
 * @author franthescollymaneira
 *
 */
public interface ProductGalleryChangedListenerStrategy
{
	void execute(final GalleryChangedEvent event);

	Map<String, String> getMediasFromMainContainerFormatMapping();

	void setMediasFromMainContainerFormatMapping(final Map<String, String> mediasFromMainContainerFormatMapping);

	Map<String, List<String>> getMediasFromAllContainersFormatMapping();

	void setMediasFromAllContainersFormatMapping(final Map<String, List<String>> mediasFromAllContainersFormatMapping);
}