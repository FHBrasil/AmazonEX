<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="selected" required="false" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<div class="left acc-links">
    <ul>
        <li><c:url value="/my-account/profile" var="encodedUrl" /> <ycommerce:testId
                code="myAccount_profile_navLink">
                <a href="${encodedUrl}"> <spring:theme code="text.account.profile"
                        text="Profile" />
                </a>
            </ycommerce:testId></li>
        <li><c:url value="/my-account/address-book" var="encodedUrl" /> <ycommerce:testId
                code="myAccount_addressBook_navLink">
                <a href="${encodedUrl}"> <spring:theme code="text.account.addressBook"
                        text="Address Book" />
                </a>
            </ycommerce:testId></li>
        <li><c:url value="/my-account/orders" var="encodedUrl" /> <ycommerce:testId
                code="myAccount_orders_navLink">
                <a href="${encodedUrl}"> <spring:theme code="text.account.orderHistory"
                        text="Order History" />
                </a>
            </ycommerce:testId></li>
        <%-- <li>
            	<c:url value="#" var="encodedUrl" />
            	<ycommerce:testId code="myAccount_exchangeWorth_navLink">
                	<a href="${encodedUrl}">
                    	<spring:theme code="text.account.exchangeWorth" text="Exchange Worth" />
                	</a>
            	</ycommerce:testId>
        	</li>
        	<li>
            	<c:url value="#" var="encodedUrl" />
            	<ycommerce:testId code="myAccount_creditsWorth_navLink">
                	<a href="${encodedUrl}">
                    	<spring:theme code="text.account.creditsWorth" text="Credits Worth" />
                	</a>
            	</ycommerce:testId>
        	</li> --%>
    </ul>
</div>
