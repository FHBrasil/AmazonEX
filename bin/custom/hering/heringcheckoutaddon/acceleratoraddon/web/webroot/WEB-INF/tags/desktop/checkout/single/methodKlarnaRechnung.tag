<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<p>Zur Bonit&auml;tspr&uuml;fung ben&ouml;tigt Klarna folgende Informationen:</p>
<div class="form-group has-error">
	<label for="klarnabirthdate">Geburtsdatum <small>z.B. 1980-04-30</small></label>
	<input type="date" class="form-control" id="klarnabirthdate">
</div>
<div class="form-group">
	<label for="klarnaphone">Telefonnummer <small>z.B. 089123456789</small></label>
	<input type="number" class="form-control" id="klarnaphone">
</div>
<div class="form-group">
	<div class="checkbox">
		<label>
			<input type="checkbox"> Mit der &Uuml;bermittlung der f&uuml;r die Abwicklung der gew&auml;hlten Klarna Zahlungsmethode und einer Identit&auml;ts- und Bonit&auml;tspr&uuml;fung erforderlichen Daten an Klarna bin ich einverstanden. Meine <a href="https://online.klarna.com/consent_de.yaws" target="_blank">Einwilligung</a> kann ich jederzeit mit Wirkung f&uuml;r die Zukunft widerrufen. Es gelten die <a href="#">AGB von Babyartikel.de</a>. 
		</label>
	</div>
</div>