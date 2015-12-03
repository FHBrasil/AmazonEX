<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.CartData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<ol class="list150608">
	<c:set var="sharp" value='#'/>	
	<c:forEach items="${cartData.entries}" var="entry">
    	<li id="${entry.product.code}" class="item150608">
			<div class="row">
				<div class="col-xxs-4 col-xs-12 col-md-4 text-right">
                	<a href="${request.contextPath}${entry.product.url}" class="image150608"><product:productPrimaryImage product="${entry.product}" format="cartIcon" /></a>
                </div>
                <div class="col-xxs-8 col-xs-12 col-md-8">
                	<a href="${request.contextPath}${entry.product.url}">${entry.product.name}</a>
                	<br /><small>${sharp}${entry.product.code}</small><br />
                </div>
            </div>
            <div class="row itemdata150608">
                <div class="col-xs-4">
                	<c:url value="/checkout/amazon/update" var="cartUpdateFormAction" />
		            <form:form id="updateCartForm${entry.product.code}" action="${cartUpdateFormAction}" method="post" commandName="updateQuantityForm${entry.product.code}" onkeypress="if(event.keyCode==13) return false">
		                <input type="hidden" name="productCode" value="${entry.product.code}" />
		                <input type="hidden" name="initialQuantity" value="${entry.quantity}" />
		                <input type="hidden" name="initialQuantity_${entry.product.code}" id="initialQuantity_${entry.product.code}" value="${entry.quantity}" />
		               	<ycommerce:testId code="cart_product_quantity">
		                	<form:input disabled="${not entry.updateable}" type="number" size="1" id="quantity${entry.product.code}" class="form-control qty" path="quantity"
		                    	min="1" maxlength="3" required="required" step="1" onchange="changeQuantityOrRemove('${entry.product.code}', false);"/>
		                </ycommerce:testId>
		           	</form:form>
		        </div> 
		        <div class="col-xs-4">
	             	<small>               			
	                    <format:price priceData="${entry.basePrice}" displayFreeForZero="true" />
	                </small>
                </div>
                <div class="col-xs-4 text-right">
                	<div id="entryTotalPrice${entry.product.code}">	                		
	            		<format:price priceData="${entry.totalPrice}" displayFreeForZero="true" />
	            	</div>
                </div>                                           	 
			</div>	
			<c:if test="${entry.updateable}">
           		<ycommerce:testId code="cart_product_removeProduct">
                	<a href="javascript:void(0)" class="delete150618 btn-excluir-produto"
                    	id="RemoveProduct_${entry.product.code}" class="submitRemoveProduct"
                        title="Remover"
                        onClick="changeQuantityOrRemove('${entry.product.code}', true);"><span class="glyphicon glyphicon-remove-sign"></span></a>
                </ycommerce:testId>
            </c:if>		
		</li>
	</c:forEach>
</ol>