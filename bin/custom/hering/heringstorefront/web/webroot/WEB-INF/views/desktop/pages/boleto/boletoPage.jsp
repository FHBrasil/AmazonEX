<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<img style="margin-top: -500px;" src="/boleto-img/jpg/${urlBoleto}" />
	
	<c:if test="${urlBoleto ne ''}">
		<br />
		<button type="button" onclick="window.print()" >Imprimir</button>
	</c:if>