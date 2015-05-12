<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ attribute name="deliveryMethod" required="true"
    type="de.hybris.platform.commercefacades.order.data.DeliveryModeData"%>
<%@ attribute name="isSelected" required="false" type="java.lang.Boolean"%>
<%-- SELECTOR FOR DZARM STORE --%>
<c:if test="${themeName == 'black'}">
    <li class="delivery_method_item"><input type="radio"
        name="delivery_method" id="${deliveryMethod.code}"
        value="${deliveryMethod.code}" ${isSelected ? 'checked="checked"' : ''} />
        <label for="${deliveryMethod.code}">
            ${deliveryMethod.name}&nbsp;-&nbsp;${deliveryMethod.description}
            &nbsp;-&nbsp;${deliveryMethod.deliveryCost.formattedValue} </label></li>
</c:if> 
<%-- SELECTOR FOR HERING STORE --%>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
    <label for="${deliveryMethod.code}"> <input type="radio"
            name="delivery_method" id="${deliveryMethod.code}"
            value="${deliveryMethod.code}" ${isSelected ? 'checked="checked"' : ''}>
        <div class="desc">
            <h3>
                ${deliveryMethod.name}
                (${deliveryMethod.description}) 
                <strong>
                    <c:choose>
                        <c:when test="${isVoucherFreeShipping && not empty cheaperDeliveryMode && deliveryMethod.code eq cheaperDeliveryMode.code}">
                            <spring:theme code="order.free" text="FREE" />
                        </c:when>
                        <c:otherwise>
                            <format:price priceData="${deliveryMethod.deliveryCost}"
                                    displayFreeForZero="TRUE"/>
                        </c:otherwise>
                    </c:choose>
                </strong>
            </h3>
        </div>
    </label>
</c:if>