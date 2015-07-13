package br.hering.test.poc.order.split;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.hering.core.strategies.CheckSplitOrderEntriesEnabledStrategy;
import br.hering.core.strategies.ShippingEstimatesStrategy;
import br.hering.facades.populators.order.SplitDeliveryOrderEntryGroupPopulator;

import com.flieger.facades.order.data.ImmediateDeliveryOrderEntryGroupData;
import com.flieger.facades.order.data.LateDeliveryOrderEntryGroupData;
import com.flieger.facades.order.data.SplitDeliveryOrderEntryGroupData;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.acceleratorfacades.order.data.DeliveryOrderEntryGroupData;
import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.store.services.BaseStoreService;


@UnitTest
public class SplitDeliveryOrderEntryGroupTest 
{
	private final static Logger LOG = Logger.getLogger(SplitDeliveryOrderEntryGroupTest.class);
	
	private static final Long ENTRY1_QTY = Long.valueOf(1);
	private static final Long ENTRY2_QTY = Long.valueOf(2);
	private static final Long ENTRY3_QTY = Long.valueOf(3);
	private static final Long ENTRY4_QTY = Long.valueOf(4);

	@Spy
	private final SplitDeliveryOrderEntryGroupPopulator populator = new SplitDeliveryOrderEntryGroupPopulator();

	@Mock
	private AbstractOrderModel abstractOrderModel;
	
	@Mock
	private PriceDataFactory priceDataFactory;
	
	@Mock
	BaseStoreService baseStoreService;
	
	@Mock
	CheckSplitOrderEntriesEnabledStrategy checkStrategy;
	
	@Mock
	ShippingEstimatesStrategy shippingEstimatesStrategy;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		populator.setPriceDataFactory(priceDataFactory);
	}

	@Test
	public void testPopulate()
	{
		given(populator.getCheckSplitOrderEntriesEnabledStrategy()).willReturn(checkStrategy);
		given(populator.getShippingEstimatesStrategy()).willReturn(shippingEstimatesStrategy);
		
		final AbstractOrderEntryModel entryModel1 = mock(AbstractOrderEntryModel.class);
		final AbstractOrderEntryModel entryModel2 = mock(AbstractOrderEntryModel.class);
		final AbstractOrderEntryModel entryModel3 = mock(AbstractOrderEntryModel.class);
		final AbstractOrderEntryModel entryModel4 = mock(AbstractOrderEntryModel.class);

		given(entryModel1.getOrder()).willReturn(abstractOrderModel);
		given(entryModel2.getOrder()).willReturn(abstractOrderModel);
		given(entryModel3.getOrder()).willReturn(abstractOrderModel);
		given(entryModel4.getOrder()).willReturn(abstractOrderModel);

		given(entryModel1.getQuantity()).willReturn(ENTRY1_QTY);
		given(entryModel2.getQuantity()).willReturn(ENTRY2_QTY);
		given(entryModel3.getQuantity()).willReturn(ENTRY3_QTY);
		given(entryModel4.getQuantity()).willReturn(ENTRY4_QTY);
		
		given(entryModel1.getEntryNumber()).willReturn(0);
		given(entryModel2.getEntryNumber()).willReturn(1);
		given(entryModel3.getEntryNumber()).willReturn(2);
		given(entryModel4.getEntryNumber()).willReturn(3);
		
		given(checkStrategy.isEnabled()).willReturn(true);
		
		given(shippingEstimatesStrategy.isImmediateShipping(entryModel1)).willReturn(true);
		given(shippingEstimatesStrategy.isImmediateShipping(entryModel2)).willReturn(true);
		given(shippingEstimatesStrategy.isImmediateShipping(entryModel3)).willReturn(false);
		given(shippingEstimatesStrategy.isImmediateShipping(entryModel4)).willReturn(false);
		
		final List<AbstractOrderEntryModel> entries = new ArrayList<AbstractOrderEntryModel>();
		entries.add(entryModel1);
		entries.add(entryModel2);
		entries.add(entryModel3);
		entries.add(entryModel4);
		given(abstractOrderModel.getEntries()).willReturn(entries);
		
		final OrderEntryData entryData1 = mock(OrderEntryData.class);
		final OrderEntryData entryData2 = mock(OrderEntryData.class);
		final OrderEntryData entryData3 = mock(OrderEntryData.class);
		final OrderEntryData entryData4 = mock(OrderEntryData.class);
		
		given(entryData1.getNamedDeliveryDate()).willReturn(getNamedDeliveryDate(0));
		given(entryData2.getNamedDeliveryDate()).willReturn(getNamedDeliveryDate(1));
		given(entryData3.getNamedDeliveryDate()).willReturn(getNamedDeliveryDate(3));
		given(entryData4.getNamedDeliveryDate()).willReturn(getNamedDeliveryDate(2));
		
		given(entryData1.getEntryNumber()).willReturn(0);
		given(entryData2.getEntryNumber()).willReturn(1);
		given(entryData3.getEntryNumber()).willReturn(2);
		given(entryData4.getEntryNumber()).willReturn(3);
		
		final List<OrderEntryData> entryDatas = new ArrayList<OrderEntryData>();
		entryDatas.add(entryData1);
		entryDatas.add(entryData2);
		entryDatas.add(entryData3);
		entryDatas.add(entryData4);

		final AbstractOrderData abstractOrderData = new AbstractOrderData();
		abstractOrderData.setEntries(entryDatas);
		
		//Populates
		populator.populate(abstractOrderModel, abstractOrderData);

		logGroups(abstractOrderData.getDeliveryOrderGroups());

		//valida os grupos de delivery
		Assert.assertNotNull(abstractOrderData.getDeliveryOrderGroups());
		Assert.assertEquals(2, abstractOrderData.getDeliveryOrderGroups().size());
	
		//valida as entries dos split groups
		Assert.assertEquals(2, abstractOrderData.getDeliveryOrderGroups().get(0).getEntries().size());
		Assert.assertEquals(2, abstractOrderData.getDeliveryOrderGroups().get(1).getEntries().size());
		
		//Valida ordenacao
		Assert.assertTrue(abstractOrderData.getDeliveryOrderGroups().get(0) instanceof ImmediateDeliveryOrderEntryGroupData);
		Assert.assertTrue(abstractOrderData.getDeliveryOrderGroups().get(1) instanceof LateDeliveryOrderEntryGroupData);

		//valida entries
		SplitDeliveryOrderEntryGroupData immediateGroup = (SplitDeliveryOrderEntryGroupData) abstractOrderData.getDeliveryOrderGroups().get(0);
		SplitDeliveryOrderEntryGroupData lateGroup = (SplitDeliveryOrderEntryGroupData) abstractOrderData.getDeliveryOrderGroups().get(1);

		Assert.assertEquals(entryData1, CollectionUtils.get(immediateGroup.getEntries(), 0));
		Assert.assertEquals(entryData2, CollectionUtils.get(immediateGroup.getEntries(), 1));
		
		Assert.assertEquals(entryData4, CollectionUtils.get(lateGroup.getEntries(), 0));
		Assert.assertEquals(entryData3, CollectionUtils.get(lateGroup.getEntries(), 1));
		
		//valida named delivery date
		Assert.assertEquals(entryData2.getNamedDeliveryDate(), immediateGroup.getNamedDeliveryDate());
		Assert.assertEquals(entryData3.getNamedDeliveryDate(), lateGroup.getNamedDeliveryDate());
		
		Assert.assertEquals(Long.valueOf(10), abstractOrderData.getDeliveryItemsQuantity());
	}

	private void logGroups(final List<DeliveryOrderEntryGroupData> groups)
	{
		for(DeliveryOrderEntryGroupData group : groups)
		{
			Date date = null;
			
			if(group instanceof SplitDeliveryOrderEntryGroupData)
			{
				date = ((SplitDeliveryOrderEntryGroupData) group).getNamedDeliveryDate();
			}
			
			LOG.debug(String.format("type: %s entries: %s named delivery date: %s", 
					group.getClass().getSimpleName(), group.getEntries().size(), date));
			
			for(OrderEntryData entry : group.getEntries())
			{
				LOG.debug(String.format("product: %s date: %s", 
						entry.hashCode(), entry.getNamedDeliveryDate()));
			}
		}
	}
	
	private Date getNamedDeliveryDate(int days) 
	{
		return DateUtils.addDays(new Date(), days);
	}
}
