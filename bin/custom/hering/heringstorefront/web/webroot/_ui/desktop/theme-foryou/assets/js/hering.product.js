if(!hering) hering = {};
hering.product = {
	bindVariantSelectorHandler: function() {
		$('.product-main-info .variantSelectorHandler').click(function(e) {
			var link = $(this).find('a');
			var styleClass = link.attr('class');
			var inStock = styleClass ? styleClass.indexOf("out-of-stock") < 0 : true;
			
			if(inStock) {
				location = link.attr('href');
			}
		});
	},
	bindReferenceForm: function() {
		var addListToCartForm = $('.addListToCartForm');
		addListToCartForm.ajaxForm({
			success: ACC.product.displayAddToCartPopup
		});
		$('.productDetailsHolder').productPreview({
			updateSizes: function(e, data) {
				var sizes = [];
				if(data.sizes.length > 0 && data.sizes[0].size) {
					for(var index = 0; index < data.sizes.length; index++) {
						sizes.push(data.sizes[index].size);
					}
				} else {
					sizes = data.sizes;
				}
				$(this).find('.tamanhos li a').each(function(j) {
					$(this).attr('class', 'out-of-stock');
					for (i = 0; i < sizes.length; i++) {
						if ($(this)[0].innerText == sizes[i] || $(this).text() == sizes[i]) {
							$(this).attr('class', '');
							break;
						}
					}
				});
				$(this).find('.tamanhos li a:not(.out-of-stock)').first().click();
			},
			variantChanged: function(e, data) {
				$(this).find('input[name="crossProduct"]').val(data.code);
				$(this).find('h2 a').attr('href', ACC.config.contextPath + data.url);
			}
		});
	},
	bindCalculateShipping: function() {
		$('#formCalculateDelivery').ajaxForm({
			success:hering.product.updateShipping
		});
        $('#formCalculateDelivery button').removeAttr("disabled");
	},
	updateShipping: function(shippingResult, statusText, xhr, formElement) {
		$('#modal-prazos-entrega .response').html($(shippingResult).find("#globalMessages div").html());
	},
	showReviewsAction: function(s) {
		$.get($("#reviews").data(s), function(result) {
			$('#reviews').html(result);
		});
	},
	bindAll: function() {
		this.bindVariantSelectorHandler();
		this.showReviewsAction("allreviews");
		this.bindReferenceForm();
		this.bindCalculateShipping();
	},
	
	getClickCrossSeling: function() {
		
		$(crossSeling.selectorInputCheck).click(function(){
			crossSeling.initialize();
		});
	}
};


$(document).ready(function(){
	
	hering.form.validate();
	hering.commons.bindAll();
	hering.product.bindAll();
	
//	variables.clickColorMonitor();
//	variables.clickSizeMonitor();
//
	crossSeling.initialize();
	crossSeling.addToCart();
	crossSeling.getProductInformations();
	
	hering.product.getClickCrossSeling();
});