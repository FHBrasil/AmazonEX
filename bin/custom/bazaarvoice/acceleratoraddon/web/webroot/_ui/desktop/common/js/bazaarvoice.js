$(document).ready(function() {
	$BV.configure('global', { productId : "${product.code}" });
	$BV.ui('rr', 'show_reviews', { function() {} });
});