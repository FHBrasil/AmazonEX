ACC.productlist = {
	bindAddToCartButton : function() {
		//atualiza mini-cart responsivo
		$('.addToCartButton').click(function(){
			$(this).parents('form:first').submit();
//			setTimeout(function(){
//				$(".miniCartPopup").show();
//				$(".miniCartPopup div").show();
//			}, 1000);

			return false;
		});
	}
};

$(document).ready(function() {
	ACC.productlist.bindAddToCartButton();
});