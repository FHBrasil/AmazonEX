<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<c:set value="${component.styleClass} ${dropDownLayout}" var="bannerClasses"/>

<%-- NAVIGATION BAR FOR DZARM STORE --%>
<c:if test="${themeName == 'black'}">
	<li class="La ${bannerClasses} <c:if test="${not empty component.navigationNode.children}"> parent</c:if>">
		<cms:component component="${component.link}" evaluateRestriction="true"/>
		<c:if test="${not empty component.navigationNode.children}">
			<ul class="Lb">
				<c:forEach items="${component.navigationNode.children}" var="child">
					<c:if test="${child.visible}">
						<li class="Lb">
							<span class="nav-submenu-title">${child.title}</span>
							<c:forEach items="${child.links}" step="${component.wrapAfter}" varStatus="i">
								<ul class="Lc ${i.count < 2 ? 'left_col' : 'right_col'}">
									<c:forEach items="${child.links}" var="childlink" begin="${i.index}" end="${i.index + component.wrapAfter - 1}">
										<cms:component component="${childlink}" evaluateRestriction="true" element="li" class="Lc ${i.count < 2 ? 'left_col' : 'right_col'}"/>
									</c:forEach>
								</ul>
							</c:forEach>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</c:if>
	</li>
</c:if>

<%-- NAVIGATION BAR FOR HERING STORE --%>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	
	<%-- validate if exist sublinks --%>
	<%-- TODO: change condition in case of featured --%>
	<c:set var="menuClass" value="
	${not empty component.navigationNode.children ? 'with-sub-menu' : ''}
	${false ? ' has-featured' : ''}
	${themeName == 'foryou' ? ' '.concat(component.link.linkName.toLowerCase()) : ''}" />
	<li <c:if test="${not empty menuClass}">class="${menuClass.trim()}"</c:if> />
	
		<%-- main category link --%>
		<cms:component component="${component.link}" evaluateRestriction="true"/>	
		
		<%-- if exist subcategory links --%>
		<c:if test="${not empty component.navigationNode.children}">
		
			<%-- subcategory list links --%>
			<div class="sub-menu">
				
				<ul>
				
					<c:forEach items="${component.navigationNode.children}" var="child">
						<c:if test="${child.visible}">
						
							<%-- li link subcatgories --%>
							<c:forEach items="${child.links}" step="${component.wrapAfter}" varStatus="i">
								
								<c:forEach items="${child.links}" var="childlink" begin="${i.index}" end="${i.index + component.wrapAfter - 1}">
									<cms:component component="${childlink}" evaluateRestriction="true" element="li" />
								</c:forEach>
								
								</ul><ul>
								
							</c:forEach>
							<%-- END li link subcatgories --%>
							
						</c:if>
					</c:forEach>
					
				</ul>
				<c:choose>
					<c:when test="${themeName == 'dzarm'}">
						<c:choose>
							<c:when test="${component.uid == 'FemininoBarComponent'}">
								<img src="/store/_ui/desktop/theme-dzarm/images/campanha/banner-menu-feminino.jpg"/>
							</c:when>
							
							<c:when test="${component.uid == 'JeansBarComponent'}">
								<img src="/store/_ui/desktop/theme-dzarm/images/campanha/banner-menu-JEANS.jpg"/>
							</c:when>
							
							<c:when test="${component.uid == 'AccessoriesBarComponent'}">
								<img src="/store/_ui/desktop/theme-dzarm/images/campanha/banner-menu-acessories.jpg"/>
							</c:when>
							
							<c:when test="${component.uid == 'SaleBarComponent'}">
								<img src="/store/_ui/desktop/theme-dzarm/images/campanha/banner-menu-sale.jpg"/>
							</c:when>
						</c:choose>
					</c:when>
					<c:when test="${themeName == 'foryou'}">
						<c:choose>
							<c:when test="${component.uid == 'ActivewearBarComponent'}">
								<img src="/store/_ui/desktop/theme-foryou/images/campanha/Banner-menu-active.jpg"/>
							</c:when>
							
							<c:when test="${component.uid == 'BeachwearBarComponent'}">
								<img src="/store/_ui/desktop/theme-foryou/images/campanha/Banner-menu-beach.jpg"/>
							</c:when>
							
							<c:when test="${component.uid == 'UnderwearBarComponent'}">
								<img src="/store/_ui/desktop/theme-foryou/images/campanha/Banner-menu-under.jpg"/>
							</c:when>
							
							<c:when test="${component.uid == 'LoungewearBarComponent'}">
								<img src="/store/_ui/desktop/theme-foryou/images/campanha/Banner-menu-lounge.jpg"/>
							</c:when>
							
							<c:when test="${component.uid == 'SleepwearBarComponent'}">
								<img src="/store/_ui/desktop/theme-foryou/images/campanha/Banner-menu-sleep.jpg"/>
							</c:when>

							<c:when test="${component.uid == 'PromocaoBarComponent'}">
								<img src="/store/_ui/desktop/theme-foryou/images/campanha/Banner-menu-promocao.jpg"/>
							</c:when>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${component.uid == 'FemininoBarComponent'}">
								<img src="/store/_ui/desktop/theme-hering/images/campanha/nav-feminino.jpg"/>
							</c:when>

							<c:when test="${component.uid == 'MasculinoBarComponent'}">
								<img src="/store/_ui/desktop/theme-hering/images/campanha/nav-masculino.jpg"/>
							</c:when>

							<c:when test="${component.uid == 'JeansBarComponent'}">
								<img src="/store/_ui/desktop/theme-hering/images/campanha/nav-jeans.jpg"/>
							</c:when>

							<c:when test="${component.uid == 'AcessoriosBarComponent'}">
								<img src="/store/_ui/desktop/theme-hering/images/campanha/nav-acessorios.jpg"/>
							</c:when>

							<c:when test="${component.uid == 'PromocaoBarComponent'}">
								<img src="/store/_ui/desktop/theme-hering/images/campanha/nav-promocao.jpg"/>
							</c:when>

							<c:when test="${component.uid == 'HeringKidsBarComponent'}">
								<img src="/store/_ui/desktop/theme-hering/images/campanha/nav-infantil.jpg"/>
							</c:when>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</div>
			<%-- END subcategory list links --%>
			
		</c:if>
		<%-- END if exist subcategory links --%>
		
	</li>
	<%-- END validate if exist sublinks --%>
	
</c:if>