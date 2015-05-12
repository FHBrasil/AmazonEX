<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	function prepareColorNameFilter() {
		var q = $('#q').val();
		var t = $('#t').val();
		var n = $('#n').val();
		var i = q.indexOf(":");

		q = i >= 0 ? q.substring(i + 1) : q;
		n = (t.length > 0 ? (t + ' ' + n) : n) + ':' +  q;
		
		$('#q').val(n);
	}
</script>

<c:if test="${themeName == 'black'}">
	<div class="facet search-by-colors">
		<div class="facetHead">Busca cor por nome</div>
		<div class="facetValues" >		
			<form action="#" method="get" name="formSearchColor" onsubmit="prepareColorNameFilter()">
				<input type="hidden" name="q" id="q" value="${param['q']}"/>
				<input type="hidden" name="text" id="t" value="${param['text']}"/>
				<input type="text" id="n" placeholder="azul" style="padding: 5px 5px;float:left;margin:0.7em 0.5em 0.7em 0;width:120px;font-size:11px"/>
				<button type="button" class="positive">OK</button>
			</form>
		</div>
	</div>
</c:if>
