var showcase = {};
showcase.colors = 
{
	clickOnColor: function()
	{
		$(".showcase #box-color li").click(function(){
			colorCode = $(this).data("code");
			productBox = $(this).closest(".product");
			showcase.colors.checkThisColor($(this));
			showcase.sizes.changeActiveSizeList(colorCode, productBox);
			showcase.images.chageActiveProductImage(colorCode, productBox);
			showcase.url.changeUrlToTheFirstSize(productBox);
			showcase.sizes.checkTheFirstSize(productBox);
		});
	},
	hoverOnColor: function()
	{
		$(".showcase .cores li").hover(function(e){
			tooltip = $(this).data('tooltip').toUpperCase();
			$(this).append('<div class="tooltip">'+tooltip+'</div>');
            tooltip = $(this).find('.tooltip');                
			sliderContainer = $(this).parent().parent();
	        sliderWidthHalf = sliderContainer.outerWidth()/2;
	        mousePosX = e.pageX - sliderContainer.offset().left;
	        if(sliderWidthHalf > 30 && mousePosX >= sliderWidthHalf) {
	            tooltip.addClass('right');
	        }
	        tooltip.show();
		}, function() {
	        $(this).find('.tooltip').hide();
	    });
	},
	checkThisColor: function(colorElement)
	{
		if($(colorElement).parents('.bx-wrapper').length > 0) {
            $(colorElement).parent().find("li").removeClass("active");
            $(colorElement).toggleClass('active');
        }
	}
};
showcase.sizes = 
{
	changeActiveSizeList: function(colorCode,productBox)
	{
		$(productBox).find(".tamanhos ul").each(function(k,v){

			existSizeWrapper = $(v).parent().attr("class").
				indexOf("bx-viewport") >= 0 ? true : false; 
				
			if( $(v).attr("id") == colorCode )
			{
				$(v).attr("class","show");
				if(existSizeWrapper){
					$(v).parent().parent().removeClass("hide");
					$(v).parent().parent().addClass("show");
				}
			}
			else{
				$(v).attr("class","hide");
				if(existSizeWrapper){
					$(v).parent().parent().removeClass("show");
					$(v).parent().parent().addClass("hide");
				}
			}
		});
	},
	clickOnSize: function()
	{
		$(".showcase .tamanhos li").click(function(){
			productBox = $(this).closest(".product");
			showcase.url.changeUrlToThisCode(productBox, $(this).attr("id") );
			showcase.sizes.checkThisSize($(this));
		});
	},
	checkThisSize: function(sizeElement)
	{
		if($(sizeElement).parents('.tamanhos').length > 0) {
            $(sizeElement).parent().find("li").removeClass("active");
            $(sizeElement).toggleClass('active');
        }
	},
	checkTheFirstSize: function(productBox)
	{
		firstProductCode = $(productBox).find(".tamanhos .show li").first();
		showcase.sizes.checkThisSize(firstProductCode);
	},
	setupSizesWithWrapper: function()
	{
		$(".showcase").find(".product").each(function(k,product){
			isFirstSize = true;
			$(product).find(".tamanhos > div").each(function(k2,size){
				if( isFirstSize ){ $(size).addClass("show"); isFirstSize = false; }
				else $(size).addClass("hide");
			});
		});
	}
};
showcase.images = 
{
	chageActiveProductImage : function(colorCode,productBox)
	{
		$(productBox).find(".photo a img").fadeOut();
		
		if( productBox.attr("class").indexOf("product-info") >= 0 ){
			
			$(productBox).find(".precos").hide();
			$(productBox).find("h2").hide();
		}

		imgList = $(productBox).find(".photo a > span");
    	imgCode = colorCode.substring(4,80);
		
    	showcase.images.loadImageAndDisplay(imgCode,imgList,productBox);
	},
	

	loadImageAndDisplay: function(imgCode, imgList, productBox)
	{
		existImage = false;
		imgList.each(function(k,v){
			
			if( $(v).data("img").indexOf(imgCode) >= 0 )
			{ 
				existImage = true;
				selectedImage = new Image();
				selectedImage.src = $(v).data("img");
				selectedImage.onload = function ()
				{
					$(productBox).find(".photo a img").attr("src", $(v).data("img"));
					$(productBox).find(".photo a img").fadeIn();
					$(productBox).find(".precos").show();
					$(productBox).find("h2").show();
		        };
			}
    	});
		
		if(!existImage)
		{
			$(productBox).find(".photo a img").fadeIn();
			$(productBox).find(".precos").show();
			$(productBox).find("h2").show();
		}
	},
};
showcase.url = 
{
	changeUrlToTheFirstSize: function(productBox)
	{
		productCode = $(productBox).find(".tamanhos > .show li").first().attr("id");
		showcase.url.changeUrlToThisCode(productBox, productCode);
	},
	changeUrlToThisCode: function(productBox,productCode)
	{
		$(productBox).find(".productMainLink").each(function(k,v){
			url = $(v).attr("href").split("/");
			url.pop(); url.push(productCode);
			$(v).attr("href", url.join("/"));
		});
	},
	getLastElementFromUrl: function(productBox)
	{
		productUrl = $(productBox).find(".detalhes a").attr("href");
		return productUrl.split("/").pop();
	}
};
showcase.cart = 
{
	clickToBuy: function()
	{
		$(".showcase .addToCartButton").click(function(){
			productBox = $(this).closest(".product");
			productCode = showcase.url.getLastElementFromUrl(productBox);
			showcase.cart.addThisProductToCart(productCode);
		});
	},
	addThisProductToCart: function(productCode)
	{
		contextData = {
			_method	: "fpost",
			productCodePost : productCode,
			CSRFToken : ACC.config.CSRFToken
		};
		
		$.ajax({
			url: "/store/pt/cart/add",
			context: document.body,
			type: "POST",
			data: contextData
		})
		.done(function(formElement, cartResult,y) {
			statusText = y.statusText;
			ACC.product.displayAddToCartPopup(cartResult, statusText, "", formElement);
			message = $(formElement.addToCartLayer).find(".legend").text();
			errorMessage = $(formElement.addToCartLayer).find(".cart_popup_error_msg").text();
			if(errorMessage != "") message = errorMessage;
			showToaster(message);
		});
	}
};
showcase.control = 
{
	initializeLoad:function()
	{
		showcase.colors.clickOnColor();
		showcase.colors.hoverOnColor();
		showcase.sizes.clickOnSize();
		showcase.sizes.setupSizesWithWrapper();
		showcase.cart.clickToBuy();
	},
	initialzePosLoad:function()
	{
	}
};
$(document).ready(function(){ showcase.control.initializeLoad(); });
$(window).load(function(){ showcase.control.initialzePosLoad(); });
