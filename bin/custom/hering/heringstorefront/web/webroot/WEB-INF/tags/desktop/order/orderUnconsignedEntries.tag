<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.OrderData" %>
<%@ attribute name="searchPageData" required="false" type="de.hybris.platform.commercefacades.order.data.OrderHistoryData"%>
<%@ attribute name="page" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:if test="${themeName == 'black'}">
<div class="orderList">
	<div class="headline"><spring:theme code="order.orderItems" text="Order Items"/></div>
	
	<table class="orderListTable">
		<thead>
			<tr>
				<th id="header2" colspan="2"><spring:theme code="text.productDetails" text="Product Details"/></th>
				<th id="header4"><spring:theme code="text.quantity" text="Quantity"/></th>
				<th id="header5"><spring:theme code="text.itemPrice" text="Item Price"/></th>
				<th id="header6"><spring:theme code="text.total" text="Total"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${order.unconsignedEntries}" var="entry">
				<c:url value="${entry.product.url}" var="productUrl"/>
				<tr class="item">
					<td headers="header2" class="thumb">
						<a href="${productUrl}">
							<product:productPrimaryImage product="${entry.product}" format="thumbnail"/>
						</a>
					</td>
					<td headers="header2" class="details">
					
							<ycommerce:testId code="orderDetails_productName_link">
								<div class="itemName"><a href="${entry.product.purchasable ? productUrl : ''}">${entry.product.name}</a></div>
							</ycommerce:testId>
						
						<c:forEach items="${entry.product.baseOptions}" var="option">
							<c:if test="${not empty option.selected and option.selected.url eq entry.product.url}">
								<c:forEach items="${option.selected.variantOptionQualifiers}" var="selectedOption">
									<dl>
										<dt>${selectedOption.name}:</dt>
										<dd>${selectedOption.value}</dd>
									</dl>
								</c:forEach>
							</c:if>
						</c:forEach>
						<c:if test="${not empty order.appliedProductPromotions}">
							<ul>
								<c:forEach items="${order.appliedProductPromotions}" var="promotion">
									<c:set var="displayed" value="false"/>
									<c:forEach items="${promotion.consumedEntries}" var="consumedEntry">
										<c:if test="${not displayed && consumedEntry.orderEntryNumber == entry.entryNumber}">
											<c:set var="displayed" value="true"/>
											<li><span>${promotion.description}</span></li>
										</c:if>
									</c:forEach>
								</c:forEach>
							</ul>
						</c:if>
					</td>
					<td headers="header4" class="quantity">
						<ycommerce:testId code="orderDetails_productQuantity_label">${entry.quantity}</ycommerce:testId>
					</td>
					<td headers="header5">
						<ycommerce:testId code="orderDetails_productItemPrice_label"><format:price priceData="${entry.basePrice}" displayFreeForZero="true"/></ycommerce:testId>
					</td>
					<td headers="header6" class="total">
						<ycommerce:testId code="orderDetails_productTotalPrice_label"><format:price priceData="${entry.totalPrice}" displayFreeForZero="true"/></ycommerce:testId>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</c:if>

<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<c:if test="${page == 'historyPage'}">
	<tr class="sub-item sub-item-${order.code}">
		<td colspan="5">
			<div class="details">
			<table>
				<tbody>
					<c:forEach items="${order.entries}" var="entry">
						<c:url value="${entry.product.url}" var="productUrl"/>												
						<tr>
							<td class="brand"><product:productBrand product="${entry.product}"/></td>
							<td class="thumb"><a href="${productUrl}"><product:productPrimaryImage product="${entry.product}" format="cartIcon"/></a></td>
							<td ${themeName == 'dzarm' ? 'style="width: 300px;"' : ''}><a href="${entry.product.purchasable ? productUrl : ''}">${entry.product.name}</a></td>
							<td>Quantidade: ${entry.quantity}</td>
							<c:set var="option" value="${entry.product.baseOptions[0]}"/>
							<c:if test="${not empty option.selected}">
								<c:forEach items="${option.selected.variantOptionQualifiers}" var="selectedOption">
									<c:if test="${selectedOption.name != 'Cor'}">
										<td>
											${selectedOption.name}:
											${selectedOption.value}
										</td>
									</c:if>
									<c:if test="${selectedOption.name == 'Cor'}">
										<td class="cor">
											${selectedOption.name}:
											<div class="cores">
												<ul>
													<li  class="${selectedOption.value}" style="background-color:${entry.product.color.RGB};"></li>
												</ul>
											</div>
										</td>
									</c:if>
								</c:forEach>
							</c:if>
							<td class="value"><strong><format:price priceData="${entry.basePrice}" displayFreeForZero="true"/></strong></td>
						</tr>					
					</c:forEach>
					<tr class="tr-btn-group">
						<td colspan="5" class="btn-group">
							<a href="<c:url value="/my-account/order/${order.code}"/>" class="btn">Ver Detalhes</a>
							<c:if test="${searchPageData.paymentMode == 'Boleto' && !searchPageData.boletoExpired}">							
								<c:url value="/boleto/${order.code}" var="boletoOrder"/>
       	 						<a target="_blank" class="btn" href="${boletoOrder}" >Reimprimir Boleto</a>						
							</c:if>
							<ycommerce:testId code="orderHistory_orderNumber_link">
								<c:if test="${not empty searchPageData.chaveNfe}">
									<a target="_blank" class="btn" href="http://www.nfe.fazenda.gov.br/portal/consulta.aspx?tipoConsulta=completa&tipoConteudo=XbSeqxE8pl8=">
										Imprimir Nota Fiscal
									</a>
								</c:if>
							</ycommerce:testId>
							<%-- <a href="#" class="btn">Solicitar Troca ou Devolução</a> --%>
						</td>
					</tr>
					</tbody>
				</table>
				</div>
			</td>
		</tr>
	</c:if>
</c:if>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<c:if test="${page == 'orderPage'}">
		<section id="order-products" class="section-block">
			<header>
				<h2>Itens do pedido</h2>
			</header>
			<table>
				<thead>
					<tr>
						<th></th>
						<th class="talign-left">Produto</th>
						<th>Qtd.</th>
						<th>Valor Unitário</th>
						<th>Valor Total</th>
					</tr>
				</thead>
				<c:forEach items="${order.unconsignedEntries}" var="entry">
					<c:url value="${entry.product.url}" var="productUrl"/>		
						<tbody>
							<tr>
								<td class="brand">
									<product:productBrand product="${entry.product}"/>								
								</td>
										<td class="product-details talign-left">
											<a href="${productUrl}"><product:productPrimaryImage product="${entry.product}" format="thumbnail"/></a>
											<div class="info">
												<ul>
													<li class="titulo"><a href="${entry.product.purchasable ? productUrl : ''}">${entry.product.name}</a></li>
													<c:forEach items="${entry.product.baseOptions}" var="option">
														<c:if test="${not empty option.selected and option.selected.url eq entry.product.url}">
															<c:forEach items="${option.selected.variantOptionQualifiers}" var="selectedOption">
																<c:if test="${selectedOption.name != 'Cor'}">																	
																	<li>
																		${selectedOption.name}:
																		${selectedOption.value}
																	</li>															
																</c:if>
																<c:if test="${selectedOption.name == 'Cor'}">
																	<li class="cor">
																		${selectedOption.name}:	
																	<div class="cores">
																		<ul>																		
																			<li class="${selectedOption.value}" style="background-color:${entry.product.color.RGB};"></li>
																		</ul>
																	</div>																	
																</c:if>
															</c:forEach>
														</c:if>
													</c:forEach>
													<li class="codigo">Código: ${entry.product.code}</li>
												</ul>
											</div>
										</td>
										<td class="quantity">
											<c:if test="${entry.quantity gt 1}">
												<b>${entry.quantity}</b> unidades
											</c:if>
											<c:if test="${entry.quantity lt 2}">
												<b>${entry.quantity}</b> unidade
											</c:if>
										</td>
										<td class="product-price talign-left">
											<div class="precos">
												<c:if test="${not empty order.appliedProductPromotions}">
													<s>de: R$ <fmt:formatNumber value="${entry.product.oldPrice}" type="number" minFractionDigits="2" /></s>
													<strong>por: <format:fromPrice priceData="${entry.basePrice}" /></strong>
												</c:if>
												<c:if test="${empty order.appliedProductPromotions}">
													<strong><format:fromPrice priceData="${entry.basePrice}" /></strong>
												</c:if>
											</div>

			                                <div class="selos">			              
			                                	<c:if test="${not empty order.appliedProductPromotions}">
													<ul>
														<c:forEach items="${order.appliedProductPromotions}" var="promotion">
															<c:set var="displayed" value="false"/>
															<c:forEach items="${promotion.consumedEntries}" var="consumedEntry">
																<c:if test="${not displayed && consumedEntry.orderEntryNumber == entry.entryNumber}">
																	<c:set var="displayed" value="true"/>
																	<li class="desconto">${promotion.description}</li>
																</c:if>
															</c:forEach>
														</c:forEach>
													</ul>
												</c:if>			                                   
			                                </div>
										</td>
										<td class="product-price total">
											<div class="precos">
												<c:if test="${not empty order.appliedProductPromotions}">
													<strong><span>por:</span> <format:price priceData="${entry.totalPrice}" displayFreeForZero="true"/></strong>
												</c:if>
												<c:if test="${empty order.appliedProductPromotions}">
													<strong><format:price priceData="${entry.totalPrice}" displayFreeForZero="true"/></strong>
												</c:if>
											</div>
										</td>
									</tr>
					</tbody>
				</c:forEach>
			</table>
		</section>
	</c:if>
</c:if>
