<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section id="program-info">
	<header>
		<h2>
			<spring:theme code="account.bonusSystem.title" />
		</h2>
	</header>

	<c:choose>
	<c:when test="${not empty bonusSystem}">
		<h3>Como funciona?</h3>
		<p>
			Lorem ipsum dolor sit amet, consectetur adipiscing elit.
			Praesent vulputate ullamcorper tincidunt. Sed semper magna id dui
			laoreet laoreet. Cras ligula nulla, sollicitudin eget arcu sed,
			faucibus tincidunt ante.
		</p>

		<h3>Seus Pontos</h3>

		<div class="seus-pontos">
			<p class="btn-group">
				<strong>${bonusSystem.points}</strong>
				<span>Total de pontos acumulados</span>
			</p>
			<c:if test="${fn:length(bonusSystem.entries) > 0}">
			<p class="btn-group">
				<c:set var="lastBonus" value="0"/>
				<c:forEach items="${bonusSystem.entries}" var="entry">
					<c:if test="${entry.points > 0}">
						<c:set var="lastBonus" value="${entry.points}"/>
					</c:if>
				</c:forEach>
				<strong>${lastBonus > 0 ? lastBonus : 0}</strong>
				<span><spring:theme code="account.bonusSystem.lastBuy" /></span>
			</p>
			</c:if>
		</div>

		<c:if test="${fn:length(bonusSystem.entries) > 0}">
			<h3><spring:theme code="account.bonusSystem.history" /></h3>
			<table>
				<tbody>
				<c:forEach items="${bonusSystem.entries}" var="entry">
					<c:if test="${entry.referenceType == 'Order'}">
					<tr>
						<td><strong>Pedido:</strong> ${entry.reference}</td>
						<c:choose>
						<c:when test="${entry.points > 0}">
						<td><strong>Pontos acumulados:</strong> ${entry.points}</td>
						</c:when>
						<c:otherwise>
						<td><strong>Pontos usados:</strong> ${-entry.points}</td>
						</c:otherwise>
						</c:choose>
						<%--<td><strong>Validade:</strong> <fmt:formatDate value="${entry.date}" type="DATE" dateStyle="short" /></td>--%>
					</tr>
					</c:if>
				</c:forEach>
				</tbody>
			</table>
		</c:if>

	</c:when>
	<c:otherwise>
		<p><spring:theme code="account.bonusSystem.notMember" /></p>
	</c:otherwise>
	</c:choose>
</section>




<%-- <p class="btn-group">
	<span class="btn btn-points">Total de pontos acumulados: ${bonusSystem.points}</span>
</p>
<c:if test="${fn:length(bonusSystem.entries) > 0}">
	<p class="btn-group">
		<span class="btn btn-points">Pontos acumulados na �ltima compra: ${bonusSystem.entries[fn:length(bonusSystem.entries)-1].points}</span>
	</p>

	<h2>Hist�rico de pontos acumulados:</h2>

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
