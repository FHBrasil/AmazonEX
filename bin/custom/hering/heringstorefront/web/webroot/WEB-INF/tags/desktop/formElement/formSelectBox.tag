<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="idKey" required="true" type="java.lang.String"%>
<%@ attribute name="labelKey" required="false" type="java.lang.String"%>
<%@ attribute name="path" required="true" type="java.lang.String"%>
<%@ attribute name="items" required="true" type="java.util.Collection"%>
<%@ attribute name="itemValue" required="false" type="java.lang.String"%>
<%@ attribute name="itemLabel" required="false" type="java.lang.String"%>
<%@ attribute name="mandatory" required="false" type="java.lang.Boolean"%>
<%@ attribute name="labelCSS" required="false" type="java.lang.String"%>
<%@ attribute name="selectCSSClass" required="false" type="java.lang.String"%>
<%@ attribute name="skipBlank" required="false" type="java.lang.Boolean"%>
<%@ attribute name="skipBlankMessageKey" required="false"
    type="java.lang.String"%>
<%@ attribute name="selectedValue" required="false" type="java.lang.String"%>
<%@ attribute name="tabindex" required="false" rtexprvalue="true"%>
<%@ attribute name="allowEmpty" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<c:if test="${themeName == 'black'}">
    <template:errorSpanField path="${path}">
        <ycommerce:testId code="LoginPage_Item_${idKey}">
            <c:if test="${not empty labelKey}">
                <label class="control-label ${labelCSS}" for="${idKey}">
                    <spring:theme code="${labelKey}" />
                    <c:if test="${mandatory != null && mandatory == true}">
                        <span class="mandatory"> * </span>
                    </c:if>
                    <span class="skip">
                        <form:errors path="${path}" />
                    </span>
                </label>
            </c:if>
            <div class="controls">
                <form:select id="${idKey}" path="${path}" 
                        cssClass="${selectCSSClass} ${(mandatory ? 'required':'')}"
                        tabindex="${tabindex}">
                    <c:if test="${skipBlank == null || skipBlank == false}">
                        <option value="" ${empty allowEmpty ? 'disabled="disabled"' : ''}
                                ${empty selectedValue ? 'selected="selected"' : ''}>
                            <spring:theme code='${skipBlankMessageKey}' />
                        </option>
                    </c:if>
                    <form:options items="${items}"
                            itemValue="${not empty itemValue ? itemValue :'code'}"
                            itemLabel="${not empty itemLabel ? itemLabel :'name'}" />
                </form:select>
            </div>
        </ycommerce:testId>
    </template:errorSpanField>
</c:if>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
    <template:errorSpanField path="${path}">
        <ycommerce:testId code="LoginPage_Item_${idKey}">
            <c:if test="${not empty labelKey}">
                <label for="${idKey}">
                    <spring:theme code="${labelKey}" />
                    <c:if test="${mandatory != null && mandatory == true}">
                        <span class="mandatory"> *: </span>
                    </c:if>
                </label>
            </c:if>
            <form:select id="${idKey}" path="${path}"
                    cssClass="${selectCSSClass} ${(mandatory ? 'required':'')}"
                    tabindex="${tabindex}" >
                <c:if test="${skipBlank == null || skipBlank == false}">
                    <option value="" ${!allowEmpty ? 'disabled="disabled"' : ''}
                            ${empty selectedValue ? 'selected="selected"' : ''}>
                        <spring:theme code='${skipBlankMessageKey}' />
                    </option>
                </c:if>
                <form:options items="${items}"
                        itemValue="${not empty itemValue ? itemValue :'code'}"
                        itemLabel="${not empty itemLabel ? itemLabel :'name'}" />
            </form:select>
            <span class="skip" style="display:none;">
            	<form:errors path="${path}" />
            </span>
        </ycommerce:testId>
    </template:errorSpanField>
</c:if>
