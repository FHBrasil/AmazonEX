<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<product:productAddToCartPanel product="${product}"
    allowAddToCart="${empty showAddToCart ? true : showAddToCart}" isMain="true" />
