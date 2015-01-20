package com.pixi.webservices.controller.order;

import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pixi.webservices.controller.AbstractPixiSecuredController;
import com.pixi.webservices.jaxb.orderstatus.request.ImportOrderStatusRequest;
import com.pixi.webservices.jaxb.response.GlobalResponse;

import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;

@Controller
public class ImportOrderStatusController extends AbstractPixiSecuredController
{
	private static final String ACTION = "import_order_status";

	@RequestMapping(method = RequestMethod.GET, produces = "text/xml", params="action=" + ACTION)
	public @ResponseBody GlobalResponse handle(@RequestParam String data, @RequestParam final String session) throws JAXBException
	{
		ImportOrderStatusRequest request = convert(ImportOrderStatusRequest.class, data);
		
		OrderEntryModel entry = getOrderEntry(request);
		
		if(entry == null)
		{
			return createGlobalResponse(false, "Entry not found for PK: " + request.getLINEITEMID());
		}
		
		updateOrderEntryStatus(request, entry);

		updateOrderStatus(entry.getOrder());
		
		updateBonusSystem(request, entry);
		
		return createGlobalResponse(true, null);
	}
	
	private OrderEntryModel getOrderEntry(ImportOrderStatusRequest request) 
	{
		PK entryPK = PK.parse(request.getLINEITEMID());
		
		return getModelService().get(entryPK);
	}

	private void updateOrderStatus(OrderModel orderModel) 
	{
		//TODO falta model de entry status
		//TODO chamar service que atualiza status o pedido geral
	}

	private void updateOrderEntryStatus(ImportOrderStatusRequest request, OrderEntryModel entry) 
	{
		//TODO falta model de entry status
		//TODO obter o status do entry com match entre kp e pixi
		//TODO setar status no entry
	}
	
	private void updateBonusSystem(ImportOrderStatusRequest request, OrderEntryModel entry) 
	{
		//TODO falta model de BS
		//TODO calcular BS do pedido
	}
	
//	private String actionImportOrderStatus(String data, String sessionUrl) {
//		PixiTransFlexOrderImportBean orderImport = parseImportOrderStatusData(data);
//		OrderService orderService = Webfoundation.getInstance().getServices().getOrderService();
//		try {
//			Long itemID = null;
//			try {
//				itemID = Long.parseLong(orderImport.getLine_item_id());				
//			}  catch (NumberFormatException e) {
//				log.debug("Error trying to get the item ID from pixi, the following id was passed: " + orderImport.getLine_item_id());
//				return null;
//			}
//			
//			OrderEntry _orderEntry = JaloSession.getCurrentSession().getItem(PK.fromLong(new Long(itemID)));
//					
//			double basePrice = _orderEntry.getBasePrice();
//			Integer orderPoints = ((int) Math.round(basePrice) * 3);
//			String description = null;
//			User user = _orderEntry.getOrder().getUser();
//						
//			EnumerationValue actualStatus = (EnumerationValue) _orderEntry.getAttribute(Attributes.OrderEntry.KPSTATUS);
//			EnumerationValue statusByCode = orderService.getKPOrderEntryStatusByExternalCode(orderImport.getStatus());
//			
//			if(statusByCode != null) {
//				_orderEntry.setAttribute(Attributes.OrderEntry.KPSTATUS, statusByCode);
//			} else {
//				log.error("Unknown external code: [" + orderImport.getStatus() + "]");
//				return null;
//			}
//			
//			if (actualStatus != null) {
//				
//				boolean shouldAddBP = shouldAddBonusPoints(actualStatus);
//				boolean shouldCancelBP = shouldCancelBonusPoints(orderImport);
//				
//				BonusSystem bs = (BonusSystem)user.getAttribute("bonusSystem");	
//				Date date = new Date(System.currentTimeMillis());
//				
//				if (shouldAddBP && !shouldCancelBP) {			
//					description = "orderCode: " + _orderEntry.getPK().toString();				
//					BonusSystemLog bsLog = this.createBonusSystemLog(KPConstants.BONUS_SYSTEM_TYPES.ORDER_POINTS, 
//							description, orderPoints, date, _orderEntry.getPK().toString());
//					bs.getLog().add(bsLog);
//				} else if (!shouldAddBP && shouldCancelBP) {
//					orderPoints *= -1;
//					description = "canceled bonus points for the customer orderline";													
//					BonusSystemLog bsLog = this.createBonusSystemLog(KPConstants.BONUS_SYSTEM_TYPES.ORDERLINE_CHARGED_BACK, 
//							description, orderPoints, date, _orderEntry.getPK().toString());
//					bs.getLog().add(bsLog);			
//				}
//				
//				//recalculate
//				List<BonusSystemLog> logs = bs.getLog();
//				Integer i = 0;
//				for (BonusSystemLog bonusSystemLog : logs) {
//					i += bonusSystemLog.getPoints();
//				}	
//				
//				bs.setPoints(i);
//				
//				try {
//					AbstractOrder order = _orderEntry.getOrder();
//					orderService.updateStatus(order);
//				} catch(Exception e) {
//					log.error(e);
//				}
//			}
//		} catch (Exception e) {
//			log.error(e);
//		}
//		
//		return generateStockImportConfirmAnswer(null);
//	}
//	private boolean shouldCancelBonusPoints(PixiTransFlexOrderImportBean orderImport) {
//		
//		if(orderImport == null || StringUtils.isBlank(orderImport.getStatus())) {
//			return false;
//		}
//		
//		boolean isOIReturned = orderImport.getStatus().equals("RET");
//		boolean isOIChargeback = orderImport.getStatus().equals("STO");
//		boolean isOINotAvailable = orderImport.getStatus().equals("NLB");
//	
//		return isOIReturned || isOIChargeback || isOINotAvailable;
//	}
//
//	private boolean shouldAddBonusPoints(EnumerationValue actualStatus) {
//		
//		if(actualStatus == null || StringUtils.isBlank(actualStatus.getCode())) {
//			return false;
//		}
//		
//		boolean isChargeback = actualStatus.getCode().equals(KPOrderEntryStatus.CHARGEBACK.getCode());
//		boolean isReturned = actualStatus.getCode().equals(KPOrderEntryStatus.RETURNED.getCode());
//		boolean isNotAvailable = actualStatus.getCode().equals(KPOrderEntryStatus.NOT_AVAILABLE.getCode());
//
//		return isReturned || isChargeback || isNotAvailable;
//	}
}