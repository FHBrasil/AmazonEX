<%@ attribute name="bonusSystem" required="true"
    type="com.flieger.bonussystem.data.BonusSystemData"%>
<%@ attribute name="type" required="true" type="java.lang.String"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<c:set var="entries" value="${type == 'bonuslist' ? bonusSystem.entries : bonusSystem.bookedEntries }" />
<c:if test="${fn:length(entries) > 0}">
	<div class="panel panel-default pointlist150614 ${type == 'prebooked' ? 'prebooked' : '' }">
		<div class="panel-heading"><span class="glyphicon glyphicon-list"></span>
			${type == "bonuslist" ? "Bonuspunkte" : "Vorgemerkte Bonuspunkte" } 
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Punkte</th>
						<th>Vorgang</th>
						<th class="hidden-xs">Datum</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${entries}" var="entry" begin="0" end="9">
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
				<tbody id="toggle${type}" class="collapse">
					<c:set var="endList" value="${fn:length(entries)}" />
					<c:forEach items="${entries}" var="entry" begin="10" end="${endList}">
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
		</div>
		<div class="panel-footer clearfix">
			<div class="text-center">
				<a class="btn btn-link" style="padding:0;" data-toggle="collapse" data-target="#toggle${type}"><span class="glyphicon glyphicon-option-horizontal" style="font-size:24px;"></span></a>
			</div>
		</div>
	</div>
</c:if>
