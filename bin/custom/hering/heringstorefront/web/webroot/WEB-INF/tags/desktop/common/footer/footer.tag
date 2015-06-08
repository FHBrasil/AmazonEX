<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="newsletter" tagdir="/WEB-INF/tags/desktop/newsletter"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div id="footer">
    <cms:pageSlot position="Footer" var="feature" element="div">
        <cms:component component="${feature}" />
    </cms:pageSlot>
</div>
