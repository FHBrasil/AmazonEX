<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript">
    $BV.configure('global', {productId : "${product.code}"});
</script>
<div id="BVRRContainer">${sBvOutputReviews}</div>
