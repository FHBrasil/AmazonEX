<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="productName" required="true" type="java.lang.String" %>
<%@ attribute name="productUrl" required="true" type="java.lang.String" %>

<a href="${productUrl}" 
	title="${productName}" 
	class="productMainLink">
	<h2><b>[BRAND]</b> ${productName}</h2>
</a>