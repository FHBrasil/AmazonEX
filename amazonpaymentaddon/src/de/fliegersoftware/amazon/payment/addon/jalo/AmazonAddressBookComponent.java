package de.fliegersoftware.amazon.payment.addon.jalo;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.ComposedType;
import org.slf4j.Logger;  import org.slf4j.LoggerFactory;

public class AmazonAddressBookComponent extends GeneratedAmazonAddressBookComponent
{
	@SuppressWarnings("unused")
	private final static Logger LOG = LoggerFactory.getLogger( AmazonAddressBookComponent.class.getName() );
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		// business code placed here will be executed before the item is created
		// then create the item
		final Item item = super.createItem( ctx, type, allAttributes );
		// business code placed here will be executed after the item was created
		// and return the item
		return item;
	}
	
}
