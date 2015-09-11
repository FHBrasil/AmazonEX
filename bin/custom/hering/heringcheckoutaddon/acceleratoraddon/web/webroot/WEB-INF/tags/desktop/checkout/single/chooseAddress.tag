<%@ attribute name="type" required="true" type="java.lang.String"%>
<%@ attribute name="address" required="true" type="de.hybris.platform.commercefacades.user.data.AddressData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div id="deliveryAddressModal${type}" class="modal fade">
   	<div class="modal-dialog">
    	<div class="modal-content">
        	<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title"><spring:theme code="checkout.single.address.chooseAddress"/></h4>
		    </div>
		    <div class="modal-body">
		       	<div class="text-right">
					<a href="#editAddressModal" data-toggle="modal"  data-dismiss="modal" class="btn btn-default"><span class="glyphicon glyphicon-plus"></span> <spring:theme code="checkout.single.address.addNewAddress"/></a>
				</div>
                <form id="selectAddress" class="${type}">
                	<input type="hidden" name="deliveryOrBilling" value="${type}"/>
                	<c:forEach items="${addressData}" var="deliveryAddress" varStatus="status">
                		<div class="radio">
							<input type="radio" name="chooseDeliveryAddress${type}" value="${deliveryAddress.id}" id="${type}${deliveryAddress.id}" ${deliveryAddress.id == address.id ? 'checked' : ''}>
							<label class="btn btn-default btn-address050609" for="${type}${deliveryAddress.id}">
								<small>${deliveryAddress.receiver}<br /></small>
								<small><c:if test="${not empty deliveryAddress.reference}">
									<spring:theme code="packstation.postNumber"/> ${deliveryAddress.reference}<br />
								</c:if></small>
								<small><c:if test="${not empty deliveryAddress.complement}">
									${deliveryAddress.complement}<br />
								</c:if></small>
								<b>${deliveryAddress.line1}, ${deliveryAddress.number}<br /></b>
								<small>${deliveryAddress.postalCode} ${deliveryAddress.town}, ${deliveryAddress.country.name}</small>
							</label>
						</div>                		
                    </c:forEach>
                    <div class="modal-footer">
                    	<button type="button" class="btn btn-default" data-dismiss="modal"><spring:theme code="checkout.single.address.abort"/></button>
                    	<button type="button" class="btn btn-primary"><spring:theme code="checkout.single.address.acceptAddress"/></button>
                	</div>
                </form>
            </div>
        </div>                    		
    </div>
</div>