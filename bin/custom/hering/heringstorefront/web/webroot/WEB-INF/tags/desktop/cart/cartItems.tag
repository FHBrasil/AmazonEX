<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.CartData" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>

<c:if test="${themeName == 'black'}">
	<div id="cartItems" class="clear">
		<div class="headline">
			<span class="cartId">
				<spring:theme code="basket.page.cartId"/>&nbsp;<span class="cartIdNr">${cartData.code}</span>
			</span>
		</div>
		
		<table class="cart">
			<thead>
				<tr>
					<th id="header2" colspan="2"><spring:theme code="basket.page.title"/></th>
					<th id="header3"><spring:theme code="basket.page.itemPrice"/></th>
					<th id="header4"><spring:theme code="basket.page.quantity"/></th>
					<c:if test="${ycommerce:checkIfPickupEnabledForStore() eq true and false}">
						<th id="header5"><spring:theme code="basket.page.shipping"/></th>
					</c:if>
					<th id="header6"><spring:theme code="basket.page.total"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cartData.entries}" var="entry">
					<c:url value="${entry.product.url}" var="productUrl"/>
					<tr class="cartItem">
						
						<td headers="header2" class="thumb">
							<a href="${productUrl}"><product:productPrimaryImage product="${entry.product}" format="thumbnail"/></a>
						</td>
						
						<td headers="header2" class="details">
							<ycommerce:testId code="cart_product_name">
								<div class="itemName"><a href="${productUrl}">${entry.product.name}</a></div>
								
								<table>
									<tr>
										<td>
											<spring:theme code="product.variants.size.show"/>:&nbsp;${entry.product.size}
										</td>
									</tr>
									<tr>
										<td>
											<spring:theme code="product.variants.color"/>:&nbsp;
											<div class="colorbox" style="background-color:${entry.product.color.RGB}; width:12px; height:15px;"></div>	
										</td>
									</tr>
									<tr>
										<td>${entry.product.code}</td>
									</tr>
										<tr> 
											<td>
												
										     
										     	<c:if test="${entry.product.fromPrice}"> <!--  Have OLD PRICE and OLD PRICE Bigger-->
											     
													<div class="price-from">	
														<spring:theme code="product.volumePrices.from" /> 
														<spring:theme code="product.currencynotation"/>
														<fmt:formatNumber value="${entry.product.oldPrice}" type="number" minFractionDigits="2" />
													</div>  
													<div class="price-to">
														<spring:theme code="product.volumePrices.to"/> <format:fromPrice priceData="${entry.basePrice}" />
													</div>
												</c:if>
																			
									</td>
								</tr>
								<tr>
									<td>
										<c:if test="${entry.product.parcelUnitPrice gt 0}">
											
												<div class="price-parcel-cart"> ${entry.product.priceParcels}<spring:theme code="product.volumePrices.parcel"/></div> 
												<div class="price-parcel-price-cart"><spring:theme code="product.currencynotation"/><fmt:formatNumber value="${entry.product.parcelUnitPrice}" type="number" minFractionDigits="2" /> </div>
										
										</c:if>
									</td>
									<td>
									</td>
							</tr>
							</table>
							</ycommerce:testId>
							
							<c:set var="entryStock" value="${entry.product.stock.stockLevelStatus.code}"/>
							
							<!-- cart data -->
							<input type="hidden" class="userFistName" value="${user.firstName}" />
							<input type="hidden" class="userLastName" value="${user.lastName}" />
							<input type="hidden" class="userCode" value="${user.displayUid}" />
							<input type="hidden" class="productPrice" value="${entry.basePrice.value}" />
							<input type="hidden" class="productSku" value="${entry.product.code}" />
							<input type="hidden" class="productQuantity" value="${entry.quantity}" />
							
							<c:set var="categories" value="" />
							<c:forEach items="${entry.product.categories }" var="cat">
								<c:choose>
									<c:when test="${categories == ''}">
										<c:set var="categories" value="${cat.name}" />
									</c:when>
									<c:when test="${categories != ''}">
										<c:set var="categories" value="${categories}, ${cat.name}" />
									</c:when>
								</c:choose>
							</c:forEach>
							<input type="hidden" class="productCategories" value="${categories}" />
							<!-- Chaordic - fim -->
							
							<c:forEach items="${entry.product.baseOptions}" var="option">
								<c:if test="${not empty option.selected and option.selected.url eq entry.product.url}">
									<c:forEach items="${option.selected.variantOptionQualifiers}" var="selectedOption" varStatus="st">
										<div class="property-${st.count }">
											<strong>${selectedOption.name}:</strong>
											<span>${selectedOption.value}</span>
										</div>
										<c:set var="entryStock" value="${option.selected.stock.stockLevelStatus.code}"/>
										<div class="clear"></div>
									</c:forEach>
								</c:if>
							</c:forEach>
							
							<c:if test="${ycommerce:doesPotentialPromotionExistForOrderEntry(cartData, entry.entryNumber)}">
								<ul class="potentialPromotions">
									<c:forEach items="${cartData.potentialProductPromotions}" var="promotion">
										<c:set var="displayed" value="false"/>
										<c:forEach items="${promotion.consumedEntries}" var="consumedEntry">
											<c:if test="${not displayed && consumedEntry.orderEntryNumber == entry.entryNumber && not empty promotion.description}">
												<c:set var="displayed" value="true"/>
												<li>
													<ycommerce:testId code="cart_potentialPromotion_label">
														${promotion.description}
													</ycommerce:testId>
												</li>
											</c:if>
										</c:forEach>
									</c:forEach>
								</ul>
							</c:if>
									
							<c:if test="${ycommerce:doesAppliedPromotionExistForOrderEntry(cartData, entry.entryNumber)}">
								<ul class="appliedPromotions">
									<c:forEach items="${cartData.appliedProductPromotions}" var="promotion">
										<c:set var="displayed" value="false"/>
										<c:forEach items="${promotion.consumedEntries}" var="consumedEntry">
											<c:if test="${not displayed && consumedEntry.orderEntryNumber == entry.entryNumber}">
												<c:set var="displayed" value="true"/>
												<li>
													<ycommerce:testId code="cart_appliedPromotion_label">
														${promotion.description}
													</ycommerce:testId>
												</li>
											</c:if>
										</c:forEach>
									</c:forEach>
								</ul>
							</c:if>
						</td>
						
						<td headers="header3" class="itemPrice">
							<format:price priceData="${entry.basePrice}" displayFreeForZero="true"/>
						</td>
	
						<!-- Remove Product -->
						<td headers="header4" class="quantity">
							<c:if test="${entry.updateable}" >
								<div style="float:left">
								<ycommerce:testId code="cart_product_removeProduct">
									<spring:theme code="text.iconCartRemove" var="iconCartRemove"/>
									<a href="#" style="margin-left: 0px!important;" id="RemoveProduct_${entry.entryNumber}" class="submitRemoveProduct" title="Remover" >${iconCartRemove}</a>
								</ycommerce:testId>
								</div>
							</c:if>
					 
								 <script type="text/javascript">
								    document.addEventListener("DOMContentLoaded", function(){
							        document.getElementById("QuantityProduct_${entry.entryNumber}_more").addEventListener("click",function(){
							        //	window.alert("+");
							        //	window.alert("quantity${entry.entryNumber}.value " + quantity${entry.entryNumber}.value );
							        //	window.alert("initialQuantity_${entry.entryNumber}" + initialQuantity_${entry.entryNumber}.value);
							        	
						        	if(quantity${entry.entryNumber}.value != initialQuantity_${entry.entryNumber}.value)
							        	{
							        		document.forms["updateCartForm${entry.entryNumber}"].submit();
							        	}
							       		 else{
									        	quantity${entry.entryNumber}.value = parseInt(quantity${entry.entryNumber}.value) + 1;
									        	document.forms["updateCartForm${entry.entryNumber}"].submit();
							       		 	 }
							        	
							       }, false);
							    }, false);
							    
							    </script>
								<script type="text/javascript">
							
							    document.addEventListener("DOMContentLoaded", function(){
							        document.getElementById("QuantityProduct_${entry.entryNumber}_less").addEventListener("click",function(){
							        	//window.alert("-");
							        	//window.alert("quantity${entry.entryNumber}.value " + quantity${entry.entryNumber}.value );
							        	//window.alert("initialQuantity_${entry.entryNumber}" + initialQuantity_${entry.entryNumber}.value);
							        	
							        	if(quantity${entry.entryNumber}.value != initialQuantity_${entry.entryNumber}.value)
							        	{
							        		document.forms["updateCartForm${entry.entryNumber}"].submit();
							        	}
							        	else{
							        			if(quantity${entry.entryNumber}.value > 0)
							        			{
													quantity${entry.entryNumber}.value = parseInt(quantity${entry.entryNumber}.value) - 1;
													document.forms["updateCartForm${entry.entryNumber}"].submit();
							        			}
											}
							       }, false);
							    }, false);
							    
							    </script>
							
							<!-- -1 + 1 button -->
							<c:url value="/cart/update" var="cartUpdateFormAction" />
							<form:form id="updateCartForm${entry.entryNumber}" action="${cartUpdateFormAction}" method="post" commandName="updateQuantityForm${entry.entryNumber}">
								<input type="hidden" name="entryNumber" value="${entry.entryNumber}"/>
								<input type="hidden" name="productCode" value="${entry.product.code}"/>
								<input type="hidden" name="initialQuantity" value="${entry.quantity}"/>
								<input type="hidden" name="initialQuantity_${entry.entryNumber}" id="initialQuantity_${entry.entryNumber}" value="${entry.quantity}"/>
								<ycommerce:testId code="cart_product_quantity">
									<form:label cssClass="skip" path="quantity" for="quantity${entry.entryNumber}"><spring:theme code="basket.page.quantity"/></form:label>
									<form:input disabled="${not entry.updateable}" type="text" size="1" id="quantity${entry.entryNumber}" class="qty" path="quantity" />
								</ycommerce:testId>
								<div class="more-less">
									<c:if test="${entry.updateable}" >
										<ycommerce:testId code="cart_product_updateQuantity">
											<a href="#" id="QuantityProduct_${entry.entryNumber}_more" class="updateQuantityProductMore1" title="Adicionar 1 item">
												<spring:theme code="basket.page.update.more1"/>
											</a>
										</ycommerce:testId>
										<ycommerce:testId code="cart_product_updateQuantity">
											<a href="#" id="QuantityProduct_${entry.entryNumber}_less"  class="updateQuantityProductLess1" title="Remover 1 item">
												<spring:theme code="basket.page.update.less1"/>								
											</a>
										</ycommerce:testId>
									</c:if>
								</div>
							</form:form>
							
						</td>
						
						<c:if test="${ycommerce:checkIfPickupEnabledForStore() eq true and false}">
							<td headers="header5" class="shipping">
								<c:url value="/store-pickup/cart/update/delivery" var="cartEntryShippingModeAction" />
								<form:form id="cartEntryShippingModeForm_${entry.product.code}${entry.entryNumber}" class="cartForm cartEntryShippingModeForm clear_fix"  action="${cartEntryShippingModeAction}" method="post">
									<input type="hidden" name="entryNumber" value="${entry.entryNumber}"/>
									<input type="hidden" name="hiddenPickupQty" value="${entry.quantity}"/>
									<c:choose>
										<c:when test="${entry.product.purchasable}">
											<c:if test="${not empty entryStock and entryStock ne 'outOfStock'}">
												<label for="shipMode${entry.entryNumber}" class="nostyle">
													<input type="radio" name="shipMode" value="ship" id="shipMode${entry.entryNumber}" class="updateToShippingSelection"
													    <c:choose>
													       <c:when test="${entry.deliveryPointOfService eq null or not entry.product.availableForPickup}">checked="checked"</c:when>
															<c:when test="${entry.product.purchasable}"></c:when>
															<c:otherwise>disabled="disabled"</c:otherwise>
													    </c:choose>
													/>
													<spring:theme code="basket.page.shipping.ship"/>
												</label>
												<br>
											</c:if>
											<c:choose>
												<c:when test="${entry.product.availableForPickup}">
													<label for="shipModePickUp${entry.entryNumber}" class="nostyle">
														<input type="radio" name="shipMode" value="pickUp" id="shipModePickUp${entry.entryNumber}" class="basket-page-shipping-ship pickupstoreSelection"
															<c:choose>
																<c:when test="${not empty entry.deliveryPointOfService.name}">checked="checked"</c:when>
																<c:when test="${not empty entry.product.availableForPickup}"></c:when>
																<c:otherwise>disabled="disabled"</c:otherwise>
															</c:choose>
														/>
														<spring:theme code="basket.page.shipping.pickup"/>
													</label>
													<div class="basket-page-shipping-pickup pointOfServiceName" >${entry.deliveryPointOfService.name}</div>
												
												<c:set var="canBePickedUp" value="${entry.product.availableForPickup and not empty entry.deliveryPointOfService.name}" />
												<c:set var="hideChangeStoreLink" value="${not canBePickedUp ? 'style=display:none' : ''}" />
												</c:when>
												<c:otherwise>
												</c:otherwise>
											</c:choose>
										</c:when>
									</c:choose>
								</form:form>
							 </td>
						</c:if>
	
						 <td headers="header6" class="total">
							 <ycommerce:testId code="cart_totalProductPrice_label">
								 <format:price priceData="${entry.totalPrice}" displayFreeForZero="true"/>
							 </ycommerce:testId>
						 </td>
					 </tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</c:if>

<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">

	<table>
		<thead>
			<tr>
				<th></th>
				<th class="talign-left"><spring:theme code="basket.page.product"/></th>
				<th><spring:theme code="basket.page.quantity"/></th>
				<th><spring:theme code="basket.page.table.unitValue"/></th>
				<th><spring:theme code="basket.page.table.totalValue"/></th>
				<th><spring:theme code="basket.page.remove"/></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${cartData.entries}" var="entry">
		<c:url value="${entry.product.url}" var="productUrl"/>
			<tr id="${entry.entryNumber}">							
				<%-- BRAND IMAGE --%>
				<td class="brand">
					<product:productBrand product="${entry.product}"/>
				</td>
				<td class="product-details talign-left">

					<%-- PRODUTO // --%>
					<%-- PRODUCT IMAGE --%>
					<a href="${productUrl}"><product:productPrimaryImage product="${entry.product}" format="cartIcon"/></a>
					
					<div class="info">
						<ul>
							<%-- NOME PRODUTO --%>
							<li class="titulo"><a href="${productUrl}">${entry.product.name}</a></li>
							
							<%-- TAMANHO E COR --%>
							<c:set value="false" var="colorIsUsed"/>
							<c:forEach items="${entry.product.baseOptions}" var="option">
								<c:forEach items="${option.selected.variantOptionQualifiers}" var="selectedOption">
									<c:if test="${selectedOption.name == 'Tamanho'}">
										<li class="tamanho">
											${selectedOption.name}:
											${selectedOption.value}
										</li>
									</c:if>
									<c:if test="${selectedOption.name == 'Cor' && colorIsUsed == false}">
										<c:set value="true" var="colorIsUsed"/>
										<li class="cor">
											${selectedOption.name}:
											<div class="cores">
												<ul>
													<li class="${selectedOption.value}" style="background-color:${entry.product.color.RGB};">
														<div class="tooltip" style="display: none;">${selectedOption.value}</div>
													</li>
												</ul>
											</div>
										</li>
									</c:if>
								</c:forEach>
							</c:forEach>
							
							<%-- CODIGO DO PRODUTO --%>
							<li class="codigo"><br>C&oacute;digo: ${entry.product.code}</li>
						</ul>
					</div>
				</td>
				
				<%--  QUANTIDADE // --%>
				<td class="quantity">
					<c:url value="/cart/update" var="cartUpdateFormAction" />
					<form:form id="updateCartForm${entry.entryNumber}" action="${cartUpdateFormAction}" method="post" commandName="updateQuantityForm${entry.entryNumber}">
						<input type="hidden" name="entryNumber" value="${entry.entryNumber}"/>
						<input type="hidden" name="productCode" value="${entry.product.code}"/>
						<input type="hidden" name="initialQuantity" value="${entry.quantity}"/>
						<input type="hidden" name="initialQuantity_${entry.entryNumber}" id="initialQuantity_${entry.entryNumber}" value="${entry.quantity}"/>
						<ycommerce:testId code="cart_product_quantity">
							<form:input disabled="${not entry.updateable}" type="number" size="1" id="quantity${entry.entryNumber}" class="qty" path="quantity" min="1" maxlength="3" required="required" step="1" />
						</ycommerce:testId>
					</form:form>
					<c:if test="${entry.product.stock.stockLevel < 4}">						
						<c:choose>
							<c:when test="${entry.product.stock.stockLevel > 1}">
								<small><spring:theme code="cart.page.qtys" arguments="${entry.product.stock.stockLevel}"/></small>
							</c:when>
							<c:otherwise>
								<small><spring:theme code="cart.page.qty" arguments="${entry.product.stock.stockLevel}"/></small>
							</c:otherwise>
						</c:choose>
					</c:if>
				</td>
				
				<%-- VALOR UNITARIO // --%>
				<td class="product-price talign-left">
				  
				  <%-- SE O PRODUTO ESTIVER COM DESCONTO --%>
				  <c:if test="${entry.product.fromPrice}">
						<div class="precos">
							<s>de: <fmt:formatNumber value="${entry.product.oldPrice}" type="number" minFractionDigits="2" /></s>
							<strong><spring:theme code="product.volumePrices.to"/> <format:fromPrice priceData="${entry.basePrice}" /><strong>
						</div>
					  	<div class="selos">
                       		<ul>
                            	<li class="desconto">produto com desconto</li>
                        	</ul>
                    	</div>
                   </c:if>
                   <c:if test="${!entry.product.fromPrice}">
                   		<div class="precos">
							<strong><format:fromPrice priceData="${entry.basePrice}" /><strong>
						</div>
                  </c:if>                  

				</td>
				
				<%-- VALOR TOTAL // --%>				
				<td class="product-price total">
					<div class="precos">
						<c:if test="${entry.product.fromPrice}">
							<strong>por: <format:price priceData="${entry.totalPrice}" displayFreeForZero="true"/></strong>
						</c:if>
						<c:if test="${!entry.product.fromPrice}">
							<strong><format:price priceData="${entry.totalPrice}" displayFreeForZero="true"/></strong>
						</c:if>
					</div>
				</td>
				
				<%-- EXCLUIR // --%>
				<c:if test="${entry.updateable}" >
					<td>
						<ycommerce:testId code="cart_product_removeProduct">
							<a href="#" class="btn-excluir-produto" id="RemoveProduct_${entry.entryNumber}" class="submitRemoveProduct" title="Remover" onClick="hering.cart.removeProduct('${entry.entryNumber}');"></a>
						</ycommerce:testId>
					</td>
				</c:if>
			</tr>
		</c:forEach>	
		</tbody>
	</table>
</c:if>
