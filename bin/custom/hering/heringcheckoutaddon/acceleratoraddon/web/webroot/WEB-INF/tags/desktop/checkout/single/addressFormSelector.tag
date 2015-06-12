<%@ attribute name="regions" required="true" type="java.util.List"%>
<%@ attribute name="country" required="false" type="java.lang.String"%>
<%@ attribute name="page" required="false" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/desktop/address"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="single-checkout-hering"
    tagdir="/WEB-INF/tags/addons/heringcheckoutaddon/desktop/checkout/single"%>    

<div id="savedAddressListHolder" style="display: block;" class="clear">
	<input type="hidden" class="contextPath" value="${request.contextPath}" />
    <%--input type="hidden" class="selectedDeliveryAddressCodeId" value="${selectedDeliveryAddress.id}" /--%>
    <header>
    	<h2><spring:theme code="checkout.single.address.title"/></h2>
    </header>
    <div class="section-container">
    	<section id="selected-address">
        	<c:if test="${not empty selectedDeliveryAddress.id}">
                <div class="address-info">
                    <dl>
                    	<dt><span id="e-tipo">${selectedDeliveryAddress.type.code}</span></dt>
                    	<dd>
                    		&nbsp;&nbsp;
                    		<div class="btn-group">
	                    		<a href="#" class="btn-editar">
	                    			<small>
	                    				<span class="glyphicon glyphicon-pencil"></span><spring:theme code="checkout.single.address.edit"/>
	                    			</small>
	                    		</a>
	                    		&nbsp;
	                    		<a href="#" class="btn-excluir">
	                    			<small>
	                    				<span class="glyphicon glyphicon-remove"></span><spring:theme code="checkout.single.address.delete"/>
	                    			</small>                        			
                        		</a>
                    		</div>
                    	</dd>                    	
                        <dd>
                        	<br />
                            <input type="hidden" class="e-code" value="${selectedDeliveryAddress.id}"/>
                            <span class="e-receiver">${selectedDeliveryAddress.receiver}</span><br />
                            <span class="e-addr">${selectedDeliveryAddress.line1}</span>,
                            <span class="e-numero">${selectedDeliveryAddress.number}</span><br />
                            <c:if test="${not empty selectedDeliveryAddress.complement}">
                            	<span class="e-complemento">${selectedDeliveryAddress.complement}</span><br />
                            </c:if>
                            <c:if test="${not empty selectedDeliveryAddress.reference}">
                            	<span class="e-ref">${selectedDeliveryAddress.reference}</span><br />
                            </c:if>
                            <span class="e-cep">${selectedDeliveryAddress.postalCode}</span>&nbsp;<span class="e-cidade">${selectedDeliveryAddress.town}</span><br />                            
                            <span class="e-estado">${selectedDeliveryAddress.region.name}</span><br />
                            <span class="e-pais">${selectedDeliveryAddress.country.name}</span><br /><br />
                            <spring:theme code="checkout.single.address.phone"/>
                            <span class="e-phone">:&nbsp;${selectedDeliveryAddress.dddPhone}&nbsp;${selectedDeliveryAddress.phone}</span><br />
                            <spring:theme code="checkout.single.address.email"/><span class="e-email">:&nbsp;${selectedDeliveryAddress.email}</span><br />                  	
                        </dd>
                    </dl>
                    <div class="btn-group">
                        <a href="#" class="btn btn-primary btn-sm btn-outro-endereco" ${themeName == 'dzarm' ? "style='font-size: 12px;'" : ""}>
                        	<spring:theme code="checkout.single.address.useAnotherAddress"/>
                        </a>
                    </div><br />
                    <div class="checkbox">
	                    <label>
	                        <input type="checkbox" name="billing" id="differentAddress" checked="checked"/>
	                    	<spring:theme code="checkout.single.address.useDeliveryAddressAsBillingAddress"/>
	                    </label> 
                    </div>                   
                </div>
			</c:if>
            <div class="address-prompt" style="display: none;">
            	<p><b><spring:theme code="checkout.single.address.chooseAddress"/>:</b></p>
                <ul>
                	<c:forEach items="${deliveryAddresses}" var="deliveryAddress" varStatus="status">
                		<li>
                        	<a class="btn-select-address" href="${request.contextPath}/checkout/single/select-delivery-address/${deliveryAddress.id}">
                            	${deliveryAddress.type.code}
                            	<small>- ${deliveryAddress.postalCode}</small>
                            </a>
                            <dl class="dlDeliveryAddress" style="display: none;">
                            	<dt><spring:theme code="checkout.single.address"/>:</dt>
                                <dd>
                                	<input type="hidden" class="e-code" value="${deliveryAddress.id}" >
                                    <span class="e-addr">${deliveryAddress.line1}</span>,
                                    <span class="e-numero">${deliveryAddress.number}</span>
                                    - <span class="e-complemento">${deliveryAddress.complement}</span>
                                    - <span class="e-cidade">${deliveryAddress.town}</span>
                                    - <span class="e-estado">${deliveryAddress.region.name}</span>
                                    <br> <spring:theme code="checkout.single.address.postalCode"/>
                                    <span id="e-cep">${deliveryAddress.postalCode}</span>
                                </dd>
                                <dt><spring:theme code="checkout.single.address.type"/>:</dt>
                                <dd>
                                	<span id="e-nome">${deliveryAddress.type.code}</span>
                                </dd>
                                <dt><spring:theme code="checkout.single.address.reference"/>:</dt>
                                <dd>${deliveryAddress.reference}</dd>
                            </dl>
                        </li>
                    </c:forEach>
                </ul>
                <a href="#" class="btn btn-primary btn-sm btn-novo-endereco">
                	<spring:theme code="checkout.single.address.addNewAddress"/>
                </a>
            </div>
        </section>
        <single-checkout-hering:newAddress regions="${regions}" country="${country}" page="${page}" suggestedAddresses="${suggestedAddresses}" />
	</div>
</div>

