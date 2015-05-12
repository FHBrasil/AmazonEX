<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/desktop/address"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="single-checkout-hering" tagdir="/WEB-INF/tags/addons/heringcheckoutaddon/desktop/checkout/single"%>

 
<div class="addressList" style="display: ${empty selectedDeliveryAddress ? 'block' : 'none'}">
	    <c:forEach items="${deliveryAddresses}" var="deliveryAddress" varStatus="status">
	        <div>
	            
	            <input type="hidden" name="selectedAddressCode" value="${deliveryAddress.id}"/>
	            <button type="button" class="adressButtonThisAddress" tabindex="${status.count + 21}" style="text-decoration: underline; background: none !important; color: #000;">${deliveryAddress.type.code}</button>
	            
	            <ul class="ulDeliveryAddress" style="display: none;">
	                <li>
	                    <spring:theme code="checkout.single.address"/>: 
	                    ${deliveryAddress.line1},&nbsp;
	                    ${deliveryAddress.number}
	                    <br/>
	                    ${deliveryAddress.town}
	                    <c:if test="${not empty deliveryAddress.region.name}">
	                        - ${deliveryAddress.region.name}
	                    </c:if>
	                    <br/>
	                    ${deliveryAddress.postalCode}
	                </li>
	                <li>
	                    <spring:theme code="checkout.single.address.type"/>: ${deliveryAddress.type.code}
	                </li>
	                <c:if test="${not empty deliveryAddress.reference}">
	                    <li>
	                        <spring:theme code="checkout.single.reference"/> - 
	                        ${deliveryAddress.reference}
	                    </li>
	                </c:if>
	            </ul>
	        </div>
	        <br/>
		</c:forEach>
	    <br/><br/>
	    <button type="button" class="buttonAddNewAddress">
	        <spring:theme code="checkout.single.address.addNewAddress"/>
	    </button>
	    <br/>
	</div>