<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>

<div class="col-sm-4 col-md-6 col-lg-5 col-lg-offset-1 recs150126 text-center">
    <p class="h4 text-left">
        <spring:theme code="product.bookmark.and.share" text="Dieses Produkt teilen"/>:
    </p>
    <div class="social150126 text-left">
        <a data-toggle="popover" title="Social Plugins" tabindex="0" data-trigger="focus"
                data-content="Bitte klicken Sie auf den Button um Inhalte zu teilen. Die Plugins senden erst Daten an die sozialen Netzwerke, wenn Sie es wirklich wollen.<br /><br /><button type='button' class='btn btn-default btn-sm'>Social Plugins aktivieren</button>">
            <span class="socicon socicon-facebook"> </span>
            <span class="socicon socicon-google"> </span>
            <span class="socicon socicon-twitter"> </span>
            <span class="socicon socicon-pinterest"> </span>
        </a>
    </div>
    <p class="h4 text-left">
        <spring:theme code="product.might.also.like" text="Das k&ouml;nnte Dir auch gefallen"/>:
    </p>
    <div class="text-center panel150120">
        <div class="box150102">
            <div class="thumbnail">
                <a href="#">
                    <img src="products/201400009307_kombikinderwagen-enzo-navy-2015_hoco_detail.jpg" alt="125x125">
                </a>
            </div>
            <a href="#">
                <b>Hoco</b>Kombikinderwagen Enzo Navy 2015
            </a>
            <span class="glyphicon glyphicon-stop text-onstock"></span> 369,00 &euro;
            <br />
            <span class="glyphicon stars">&#57350;&#57350;&#57350;&#57350;&#57350;
                <span style="width:90%">&#57350;&#57350;&#57350;&#57350;&#57350;</span>
            </span>
        </div>
    </div>
</div>