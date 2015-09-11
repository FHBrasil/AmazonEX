<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="/cms2lib/cmstags/cmstags.tld"%>

<spring:theme code="text.header.connect" text="Menu" var="menu"/>
<div id="connect-popup"
	data-role="content"
	class="top-nav-bar-layer accmob-hotlineBox menu-container"
	style="display:none;"
	data-theme="f"
	data-content-theme="f"
	data-options="{&quot;mode&quot;:&quot;blank&quot;,&quot;headerText&quot;:&quot;${menu}&quot;,&quot;headerClose&quot;:true,&quot;blankContent&quot;:true,&quot;themeDialog&quot;:&quot;f&quot;}">
	<ul data-role="listview" data-inset="true">
		<cms:slot var="feature" contentSlot="${slots['NavContent']}">
			<cms:component component="${feature}" />
		</cms:slot>
	</ul>
</div>
