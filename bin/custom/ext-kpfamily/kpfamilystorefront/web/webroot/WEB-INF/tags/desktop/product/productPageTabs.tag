<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>


<div class="container">
    <ul class="nav nav-tabs margin-top" id="tab150219">
        <li class="active">
            <!-- description -->
            <a data-toggle="tab" href="#sectionA">
                Beschreibung
            </a>
        </li>
        <li>
            <!-- reviews -->
            <a data-toggle="tab" href="#sectionB">
                Bewertungen 
                <span class="badge">17</span>
            </a>
        </li>
        <li>
            <!-- Technical Details -->
            <a data-toggle="tab" href="#sectionC">
                Technische Details
            </a>
        </li>
        <li class="dropdown">
            <!-- More -->
            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                Mehr<b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li>
                    <!--  Q&A -->
                    <a data-toggle="tab" href="#dropdown1">
                        Fragen & Antworten
                    </a>
                </li>
                <li>
                    <a data-toggle="tab" href="#dropdown2">
                        Downloads 
                        <span class="badge">2</span>
                    </a>
                </li>
                <li>
                    <!-- Accessories -->
                    <a data-toggle="tab" href="#dropdown3">
                        Zubeh&ouml;r
                        <span class="badge">12</span>
                    </a>
                </li>
            </ul>
        </li>
    </ul>
    
    <div class="col-md-6 col-sm-8 tab150119">    
        <div class="tab-content">
            <div id="sectionA" class="tab-pane fade in active">
                <p>
                    ${product.description}
                    Product Description
                </p>
            </div>
            <div id="sectionB" class="tab-pane fade">
                <p>
                    <!-- Reviews -->
                    Reviews
                </p>
            </div>
            <div id="sectionC" class="tab-pane fade">
                <p>
                    <!-- Technical Details -->
                    Technical Details
                </p>
            </div>
            <div id="dropdown1" class="tab-pane fade">
                <p>
                    <!-- Q&A -->
                    FAQ
                </p>
            </div>
            <div id="dropdown2" class="tab-pane fade">
                <p>
                    <!-- Downloads -->
                    Downloads
                </p>
            </div>
            <div id="dropdown3" class="tab-pane fade">
                <p>
                    <!-- Accessories -->
                    Accessories
                </p>
            </div>
        </div>
    </div>
</div>
