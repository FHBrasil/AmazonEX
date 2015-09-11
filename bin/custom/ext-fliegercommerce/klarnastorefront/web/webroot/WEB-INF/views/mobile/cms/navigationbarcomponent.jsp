<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>

<c:if test="${empty component.navigationNode.children}">
    <c:set value="${component.styleClass} ${dropDownLayout}" var="bannerClasses"/>
        <li class="La ${bannerClasses} ">
            <component:cmsLinkComponent component="${component.link}"/>
        </li>
</c:if>
<c:if test="${not empty component.navigationNode.children}">
    <li class="La" data-theme="e">
        <div data-role="collapsiblelistview" class="navigationNodeParent">
            <h3 class="ui-li-heading"><component:cmsLinkComponent component="${component.link}"/></h3>
            <a data-role="button" data-icon="plus" href="#"></a>

            <ul data-role="listview" data-theme="d">
                <c:forEach items="${component.navigationNode.children}" var="child">
                    <c:forEach items="${child.links}" var="childlink">
                        <li class="navigationNodeChild">
                            <component:cmsLinkComponent component="${childlink}"/>
                        </li>
                    </c:forEach>
                </c:forEach>
            </ul>
        </div>
    </li>
</c:if>
