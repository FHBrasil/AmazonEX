<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
	<c:choose>
	<c:when test="${not empty bonusSystem}">
		<c:set var="intFox" value="${fn:split(bonusSystem.points/10, '.')}" />
		<div class="col-sm-12 col-md-8">
			<div class="row">
				<div class="col-sm-12">
					<div class=" panel panel-default">
						<div class="panel-body">
							<span class="onefox big">
								<fmt:formatNumber maxFractionDigits="0" value="${bonusSystem.points}"/>
							</span>
							<c:forEach begin="1" end="${intFox[0]>390 ? 390 : intFox[0]}">
								<span class="onefox"></span>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
			<div class="panel panel-default pointlist150614">
				<div class="panel-heading"><span class="glyphicon glyphicon-list"></span> Bonuspunkte</div>
				<div class="panel-body">
					<c:if test="${fn:length(bonusSystem.entries) > 0}">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Punkte</th>
									<th>Vorgang</th>
									<th class="hidden-xs">Datum</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${bonusSystem.entries}" var="entry" begin="0" end="9">
									<tr>
										<td>
											${entry.points > 0 ? "+" : ""} 
											<fmt:formatNumber maxFractionDigits="0" value="${entry.points}"/>
										</td>
										<td><a href="/de/my-account/order/${entry.reference}">${entry.reference}</a></td>
										<td class="hidden-xs">
											<fmt:formatDate value="${entry.date}" pattern="dd.MM.yy"/>
										</td>
									</tr>
								</c:forEach>
							</tbody>
							<tbody id="toggleBonuslist" class="collapse">
								<c:set var="endList" value="${fn:length(bonusSystem.entries)}" />
								<c:forEach items="${bonusSystem.entries}" var="entry" begin="10" end="${endList}">
									<tr>
										<td>
											${entry.points > 0 ? "+" : ""} 
											<fmt:formatNumber maxFractionDigits="0" value="${entry.points}"/>
										</td>
										<td><a href="/de/my-account/order/${entry.reference}">${entry.reference}</a></td>
										<td class="hidden-xs">
											<fmt:formatDate value="${entry.date}" pattern="dd.MM.yy"/>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
				</div>
				<div class="panel-footer clearfix">
					<div class="text-center">
						<a class="btn btn-link" style="padding:0;" data-toggle="collapse" data-target="#toggleBonuslist"><span class="glyphicon glyphicon-option-horizontal" style="font-size:24px;"></span></a>
					</div>
				</div>
			</div>
			
<!-- 			<div class="panel panel-default pointlist150614 prebooked"> -->
<!-- 				<div class="panel-heading"><span class="glyphicon glyphicon-list"></span> Vorgemerkte Bonuspunkte</div> -->
<!-- 				<div class="panel-body"> -->
<!-- 					<table class="table table-striped"> -->
<!-- 						<thead> -->
<!-- 							<tr> -->
<!-- 								<th>Punkte</th> -->
<!-- 								<th>Vorgang</th> -->
<!-- 								<th class="hidden-xs">Datum</th> -->
<!-- 							</tr> -->
<!-- 						</thead> -->
<!-- 						<tbody> -->
<!-- 							<tr> -->
<!-- 								<td>+93</td> -->
<!-- 								<td>Big Bobby Car</td> -->
<!-- 								<td class="hidden-xs">24.06.2015</td> -->
<!-- 							</tr> -->
<!-- 						</tbody> -->
<!-- 						<tbody id="togglePrebooked" class="collapse"> -->
<!-- 							<tr> -->
<!-- 								<td>+93</td> -->
<!-- 								<td>Big Bobby Car</td> -->
<!-- 								<td class="hidden-xs">24.06.2015</td> -->
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td>+3</td> -->
<!-- 								<td>Euret Zahnbürste (2x)</td> -->
<!-- 								<td class="hidden-xs">24.06.2015</td> -->
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td>+12</td> -->
<!-- 								<td>Euret Ess-Lernbesteck</td> -->
<!-- 								<td class="hidden-xs">24.06.2016</td> -->
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td>+356</td> -->
<!-- 								<td><a href="index-accountOrder.html">Storno Bestellung 1424965239758</a></td> -->
<!-- 								<td class="hidden-xs">24.06.2016</td> -->
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td>-356</td> -->
<!-- 								<td><a href="index-accountOrder.html">Bestellung 1424965239758</a></td> -->
<!-- 								<td class="hidden-xs">24.06.2016</td> -->
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td>+93</td> -->
<!-- 								<td>Big Bobby Car</td> -->
<!-- 								<td class="hidden-xs">24.06.2015</td> -->
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td>+3</td> -->
<!-- 								<td>Euret Zahnbürste (2x)</td> -->
<!-- 								<td class="hidden-xs">24.06.2015</td> -->
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td>+12</td> -->
<!-- 								<td>Euret Ess-Lernbesteck</td> -->
<!-- 								<td class="hidden-xs">24.06.2016</td> -->
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td>+356</td> -->
<!-- 								<td><a href="index-accountOrder.html">Storno Bestellung 1424965239758</a></td> -->
<!-- 								<td class="hidden-xs">24.06.2016</td> -->
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td>-356</td> -->
<!-- 								<td><a href="index-accountOrder.html">Bestellung 1424965239758</a></td> -->
<!-- 								<td class="hidden-xs">24.06.2016</td> -->
<!-- 							</tr> -->
<!-- 						</tbody> -->
<!-- 					</table> -->
<!-- 				</div> -->
<!-- 				<div class="panel-footer clearfix"> -->
<!-- 					<div class="text-center"> -->
<!-- 						<a class="btn btn-link" style="padding:0;" data-toggle="collapse" data-target="#togglePrebooked"><span class="glyphicon glyphicon-option-horizontal" style="font-size:24px;"></span></a> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->




		</div>
		<div class="col-sm-12 col-md-4">
			<div class="row">
				<div class="col-xs-12">
					<div class=" panel panel-default">
						<div class="panel-heading">Punkte sammeln und sparen!
						</div>
						<div class="panel-body">
							<p>Mit jedem Einkauf bei Babyartikel.de erhalten Sie Bonuspunkte. Die Punkte können Sie beim nächsten Einkauf in Rabatte umwandeln. Achten Sie auf den roten Fuchs und werden Sie Sparweltmeister!</p>
						</div>
					</div>
				</div>
			
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<p><spring:theme code="account.bonusSystem.notMember" /></p>
	</c:otherwise>
	</c:choose>
</div>

<!-- <section id="program-info"> -->
<!-- 	<header> -->
<!-- 		<h2> -->
<%-- 			<spring:theme code="account.bonusSystem.title" /> --%>
<!-- 		</h2> -->
<!-- 	</header> -->

<%-- 	<c:choose> --%>
<%-- 	<c:when test="${not empty bonusSystem}"> --%>
<!-- 		<h3>Como funciona?</h3> -->
<!-- 		<p> -->
<!-- 			Lorem ipsum dolor sit amet, consectetur adipiscing elit. -->
<!-- 			Praesent vulputate ullamcorper tincidunt. Sed semper magna id dui -->
<!-- 			laoreet laoreet. Cras ligula nulla, sollicitudin eget arcu sed, -->
<!-- 			faucibus tincidunt ante. -->
<!-- 		</p> -->

<!-- 		<h3>Seus Pontos</h3> -->

<!-- 		<div class="seus-pontos"> -->
<!-- 			<p class="btn-group"> -->
<%-- 				<strong>${bonusSystem.points}</strong> --%>
<!-- 				<span>Total de pontos acumulados</span> -->
<!-- 			</p> -->
<%-- 			<c:if test="${fn:length(bonusSystem.entries) > 0}"> --%>
<!-- 			<p class="btn-group"> -->
<%-- 				<c:set var="lastBonus" value="0"/> --%>
<%-- 				<c:forEach items="${bonusSystem.entries}" var="entry"> --%>
<%-- 					<c:if test="${entry.points > 0}"> --%>
<%-- 						<c:set var="lastBonus" value="${entry.points}"/> --%>
<%-- 					</c:if> --%>
<%-- 				</c:forEach> --%>
<%-- 				<strong>${lastBonus > 0 ? lastBonus : 0}</strong> --%>
<%-- 				<span><spring:theme code="account.bonusSystem.lastBuy" /></span> --%>
<!-- 			</p> -->
<%-- 			</c:if> --%>
<!-- 		</div> -->

<%-- 		<c:if test="${fn:length(bonusSystem.entries) > 0}"> --%>
<%-- 			<h3><spring:theme code="account.bonusSystem.history" /></h3> --%>
<!-- 			<table> -->
<!-- 				<tbody> -->
<%-- 				<c:forEach items="${bonusSystem.entries}" var="entry"> --%>
<%-- 					<c:if test="${entry.referenceType == 'Order'}"> --%>
<!-- 					<tr> -->
<%-- 						<td><strong>Pedido:</strong> ${entry.reference}</td> --%>
<%-- 						<c:choose> --%>
<%-- 						<c:when test="${entry.points > 0}"> --%>
<%-- 						<td><strong>Pontos acumulados:</strong> ${entry.points}</td> --%>
<%-- 						</c:when> --%>
<%-- 						<c:otherwise> --%>
<%-- 						<td><strong>Pontos usados:</strong> ${-entry.points}</td> --%>
<%-- 						</c:otherwise> --%>
<%-- 						</c:choose> --%>
<%-- 						<td><strong>Validade:</strong> <fmt:formatDate value="${entry.date}" type="DATE" dateStyle="short" /></td> --%>
<!-- 					</tr> -->
<%-- 					</c:if> --%>
<%-- 				</c:forEach> --%>
<!-- 				</tbody> -->
<!-- 			</table> -->
<%-- 		</c:if> --%>

<%-- 	</c:when> --%>
<%-- 	<c:otherwise> --%>
<%-- 		<p><spring:theme code="account.bonusSystem.notMember" /></p> --%>
<%-- 	</c:otherwise> --%>
<%-- 	</c:choose> --%>
<!-- </section> -->




<%-- <p class="btn-group">
	<span class="btn btn-points">Total de pontos acumulados: ${bonusSystem.points}</span>
</p>
<c:if test="${fn:length(bonusSystem.entries) > 0}">
	<p class="btn-group">
		<span class="btn btn-points">Pontos acumulados na ï¿½ltima compra: ${bonusSystem.entries[fn:length(bonusSystem.entries)-1].points}</span>
	</p>

	<h2>Histï¿½rico de pontos acumulados:</h2>

	<table>
		<tbody>
		<c:forEach items="${bonusSystem.entries}" var="entry">
			<tr>
				<td><strong>Pedido:</strong> ${entry.reference}</td>
				<td><strong>Pontos acumulados:</strong> ${entry.points}</td>
				<td><strong>Validade:</strong> ${entry.date}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</c:if> --%>

<%--<nav class="componente-paginacao estilo-escuro">
	<a href="#">&lt;</a> <a href="#">1</a> <a class="ativo">2</a> <a
		href="#">3</a> <a href="#">4</a> <a href="#">5</a> <a href="#">&gt;</a>
</nav>--%>
