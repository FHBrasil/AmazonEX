<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bonus" tagdir="/WEB-INF/tags/addons/bonussystemaddon/desktop/bonussystem"%>

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
<%-- 			<bonus:tableInfoHistory type="bonuslist" bonusSystem="${bonusSystem}" /> --%>
<%-- 			<bonus:tableInfoHistory type="prebooked" bonusSystem="${bonusSystem.bookEntries}" /> --%>
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

