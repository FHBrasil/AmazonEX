<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<c:set value="${component.styleClass} ${dropDownLayout}" var="bannerClasses" />
<%-- validate if exist sublinks --%>
<%-- TODO: change condition in case of featured --%>
<c:set var="menuClass"
    value="
	${not empty component.navigationNode.children ? 'with-sub-menu' : ''}
	${false ? ' has-featured' : ''}" />
<li <c:if test="${not empty menuClass}">class="${menuClass.trim()}"</c:if> />
<%-- main category link --%>
<cms:component component="${component.link}" evaluateRestriction="true" />
<%-- if exist subcategory links --%>
<c:if test="${not empty component.navigationNode.children}">
    <%-- subcategory list links --%>
    <div class="sub-menu">
        <ul>
            <c:forEach items="${component.navigationNode.children}" var="child">
                <c:if test="${child.visible}">
                    <%-- li link subcatgories --%>
                    <c:forEach items="${child.links}" step="${component.wrapAfter}" varStatus="i">
                        <c:forEach items="${child.links}" var="childlink" begin="${i.index}"
                            end="${i.index + component.wrapAfter - 1}">
                            <cms:component component="${childlink}" evaluateRestriction="true"
                                element="li" />
                        </c:forEach>
        </ul>
        <ul>
            </c:forEach>
            <%-- END li link subcatgories --%>
</c:if>
</c:forEach>
</ul>
<c:choose>
    <c:when test="${component.uid == 'FemininoBarComponent'}">
        <img src="/store/_ui/desktop/theme-${themeName}/images/campanha/nav-feminino.jpg" />
    </c:when>
    <c:when test="${component.uid == 'MasculinoBarComponent'}">
        <img src="/store/_ui/desktop/theme-${themeName}/images/campanha/nav-masculino.jpg" />
    </c:when>
    <c:when test="${component.uid == 'JeansBarComponent'}">
        <img src="/store/_ui/desktop/theme-${themeName}/images/campanha/nav-jeans.jpg" />
    </c:when>
    <c:when test="${component.uid == 'AcessoriosBarComponent'}">
        <img src="/store/_ui/desktop/theme-${themeName}/images/campanha/nav-acessorios.jpg" />
    </c:when>
    <c:when test="${component.uid == 'PromocaoBarComponent'}">
        <img src="/store/_ui/desktop/theme-${themeName}/images/campanha/nav-promocao.jpg" />
    </c:when>
    <c:when test="${component.uid == 'HeringKidsBarComponent'}">
        <img src="/store/_ui/desktop/theme-${themeName}/images/campanha/nav-infantil.jpg" />
    </c:when>
</c:choose>
</div>
<%-- END subcategory list links --%>
</c:if>
<%-- END if exist subcategory links --%>
</li>
<%-- END validate if exist sublinks --%>
