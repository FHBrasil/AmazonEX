<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%-- FIXME: this javascript call is here just for testing. Remove this before sending to hom/prod envs --%>
<script type="text/javascript" src="//display.ugc.bazaarvoice.com/static/kp-family/de_DE/bvapi.js"></script>
<!-- <div class="bv-cleanslate" style="width: 86px !important; float: left !important"> -->
<!--     <div class="bv-core-container-164"> -->
<!--         <div class="bv-primarySummary-rating-container"> -->
<!--             <div -->
<!--                 class="bv-summary-bar bv-summary-bar-minimalist bv-summary-bar-minimalist-horizontal"> -->
<!--                 <dl class="bv-stars-container"> -->
<!--                     <dd class="bv-rating-ratio"> -->
<!--                         <a onclick="javascript:scrollToReviewsTab('Bewertungen');" -->
<%--                             href="${product.url}#ReviewHeader" --%>
<!--                             class="bv-rating-stars-container bv-focusable" -->
<%--                             title="${product.manufacturer} ${product.name} - ${product.averageRating} von 5 Sternchen (${product.reviewsTotalCount} Kundenbewertungen)" --%>
<!--                             style=""> <span class="bv-rating-stars bv-rating-stars-off" -->
<!--                             style="font-size: 20px !important;"> &#9734;&#9734;&#9734;&#9734;&#9734; </span> <span -->
<!--                             class="bv-rating-stars bv-rating-stars-on" -->
<%--                             style="font-size:20px !important;width:${product.averageRating/5*100}%!important;"> --%>
<!--                                 &#9734;&#9734;&#9734;&#9734;&#9734; </span> -->
<!--                         </a> -->
<!--                     </dd> -->
<!--                 </dl> -->
<!--             </div> -->
<!--         </div> -->
<!--     </div> -->
<!-- </div> -->
<p>
    <span class="glyphicon stars">
       &#57350;&#57350;&#57350;&#57350;&#57350;
        <span style="width:${product.averageRating/5*100}% !important;">
           &#57350;&#57350;&#57350;&#57350;&#57350;
        </span>
    </span>
    ${product.averageRating} (${product.reviewsTotalCount})
    <a href="#">
        <spring:theme code="review.write.review" text="Bewertung schreiben" />
    </a>
</p>