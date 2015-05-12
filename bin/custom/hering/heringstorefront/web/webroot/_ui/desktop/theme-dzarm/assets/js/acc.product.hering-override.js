// replace/override function
ACC.product.bindToAddToCartForm = function() {
	var addToCartForm = $('.addToCartForm');
	addToCartForm.ajaxForm({
		success: ACC.product.displayAddToCartPopup
	});
};

// uses class instead of id
ACC.product.enableAddToCartButton = function() {
    $('.addToCartButton').removeAttr("disabled");
};

// updates minicart without popup
ACC.product.displayAddToCartPopup = function(cartResult, statusText, xhr, formElement) {
	
	if (typeof ACC.minicart.refreshMiniCartCount == 'function') {
		ACC.minicart.getMiniCartData("");
		ACC.minicart.refreshMiniCartCount();
	}
	
	var error = $(cartResult.addToCartLayer).find('.cart_popup_error_msg');
	if(error.length && error.text() != '')
		showToaster(error.text());
	
	var legend = $(cartResult.addToCartLayer).find('.legend');
	if((legend.length && legend.text() != '') && !(error.length && error.text() != ''))
		showToaster(legend.text());
	
	if (typeof ACC.minicart.refreshMiniCartCount == 'function') {
		ACC.minicart.refreshMiniCartCount();
	}
	var productCode = $('[name=productCodePost]', formElement).val();
	var quantityField = $('[name=qty]', formElement).val();
	var quantity = 1;
	if (quantityField != undefined) {
		quantity = quantityField;
	}
	ACC.track.trackAddToCart(productCode, quantity, cartResult.cartData);
	
	setTimeout(function(){
		$(".miniCartPopup").show();
		$(".miniCartPopup div").show();
	}, 1000);

	setTimeout(function(){ $(".miniCartPopup div").hide();	}, 7000);
};

ACC.product.chaordic = function() {
	product = {
		id: "",
		name: "",
		description: "",
		url: "",
		images: new Object(),
		status: "",
		old_price: 0,
		price: "",
		categories: new Array(),
		installment: {
			count: 0,
			price: 0
		}
	};
	if ($("div.productData").length) {
		var productData = $(".productData");
		product.id = productData.find("input.productCode").val().substr(0, 4);
		product.name = productData.find("input.productName").val();
		product.description = productData.find("input.productDescription").val();
		product.url = "www.hering.com.br/store" + productData.find("input.productUrl").val();
		product.price = Number(productData.find("input.productValue").val());
		if (Number(productData.find("input.productOldPrice").val()) != 0) {
			product.old_price = Number(productData.find("input.productOldPrice").val());
		} else {
			product.old_price = product.price;
		} if (Number($(".chaordicPriceParcel").find("input.chaordicNumberParcel").val()) != 0) {
			product.installment.count = Number($(".chaordicPriceParcel").find("input.chaordicNumberParcel").val());
			product.installment.price = Number($(".chaordicPriceParcel").find("input.chaordicPriceParcel").val());
		} else {
			product.installment.count = 1;
			product.installment.price = product.price;
		}
		var cat = productData.find("input.productCategories").val().split(",");
		for (var a in cat) {
			var category = new Object();
			category.name = cat[a];
			product.categories.push(category);
		}
		product.status = "available";
		var image = {
			default: ""
		};
		image.default = $("#product-main-img").attr("data-image-source");
		product.images = image;
		window.chaordic_meta.page = {
			"name": "product",
			"timestamp": new Date(),
			"categories": product.categories
		};
		window.chaordic_meta.product = product;
	}
};