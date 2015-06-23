<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>

<div>
        <product:productPageTabs />
    <div class="col-sm-4 col-md-6 col-lg-5 col-lg-offset-1 recs150126 text-center">
    </div>
</div>




<%-- code original
<div class="container half">
    <div class="right">
        <section class="section-mini right-margin">
            <cms:pageSlot position="CrossSelling" var="comp" element="div" class="span-24">
                <cms:component component="${comp}" />
            </cms:pageSlot>
        </section>
    </div>
    <div chaordic="top"></div>
    <div class="caracteristicas<c:if test='${not empty product.productReferences}'> left</c:if>">
        <product:productPageTabs />
    </div>
    <div chaordic="middle"></div>
    <div chaordic="bottom"></div>
</div>
<cms:pageSlot position="Section3" var="feature" element="div"
    class="span-20 section3 cms_disp-img_slot">
    <cms:component component="${feature}" />
</cms:pageSlot>
<cms:pageSlot position="UpSelling" var="comp" element="div" class="span-24">
    <cms:component component="${comp}" />
</cms:pageSlot>--%>
