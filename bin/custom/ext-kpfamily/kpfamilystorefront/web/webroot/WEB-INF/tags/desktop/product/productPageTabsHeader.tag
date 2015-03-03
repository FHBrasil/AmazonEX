<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true"
        type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<ul class="nav nav-tabs margin-top" id="tab150219">
        <li class="active">
            <%-- Description --%>
        <a data-toggle="tab" href="#sectionA">
            <spring:theme code="product.tab.description" text="Beschreibung"/>
        </a>
    </li>
    <li>
        <%-- Reviews --%>
        <a data-toggle="tab" href="#sectionB">
            <spring:theme code="product.tab.reviews" text="Bewertungen"/> 
            <span class="badge">
                ${product.numberOfReviews}
            </span>
        </a>
    </li>
    <li>
        <%-- Technical Details --%>
        <a data-toggle="tab" href="#sectionC">
            <spring:theme code="product.tab.technical.details" text="Technische Details"/>
        </a>
    </li>
    <li class="dropdown">
        <%-- More --%>
        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
            <spring:theme code="product.tab.more" text="Mehr"/><b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
            <li>
                <%-- Q&A --%>
                <a data-toggle="tab" href="#dropdown1">
                    <spring:theme code="product.tab.faq" text="Fragen & Antworten"/>
                </a>
            </li>
            <li>
                <%-- Downloads --%>
                <a data-toggle="tab" href="#dropdown2">
                    <spring:theme code="product.tab.downloads" text="Downloads"/> 
                    <span class="badge">2</span> <!-- FIXME: insert number of downloads here -->
                </a>
            </li>
            <li>
                <%-- Accessories --%>
                <a data-toggle="tab" href="#dropdown3">
                    <spring:theme code="product.tab.accessories" text="Zubeh&ouml;r"/>
                    <span class="badge">12</span> <!-- FIXME: insert number of accessories here -->
                </a>
            </li>
        </ul>
    </li>
</ul>