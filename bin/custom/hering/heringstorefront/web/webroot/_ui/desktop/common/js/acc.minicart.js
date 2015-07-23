ACC.minicart = {
    
    $layer : $('#miniCartLayer'),
    
    bindMiniCart : function() {
    	$(document).on('mouseenter', '.miniCart', this.showMiniCart);
    	$(document).on('mouseleave', '.miniCart', this.hideMiniCart);
    	$(document).on('mouseenter', '.fa-shopping-cart', this.showMiniCart);
    	/*$(document).on('click', '.fa-shopping-cart', this.refreshMiniCartCount);*/
    },
    
    showMiniCart : function() {
    	
    	if (ACC.minicart.$layer.data("hover")) {
            return;
        }
        
        if (ACC.minicart.$layer.data("needRefresh") != false) {
            ACC.minicart.getMiniCartData(function() {
                ACC.minicart.$layer.fadeIn(function() {
                    ACC.minicart.$layer.data("hover", true);
                    ACC.minicart.$layer.data("needRefresh", false);
                });
            })
        }
        
        ACC.minicart.$layer.fadeIn(function() {
            ACC.minicart.$layer.data("hover", true);
        });
    },
    
    hideMiniCart : function() {
        ACC.minicart.$layer.fadeOut(function() {
            ACC.minicart.$layer.data("hover", false);
        });
    },
    
    getMiniCartData : function(callback) {
        $.ajax({
            url : ACC.minicart.$layer.attr("data-rolloverPopupUrl"),
            cache : false,
            type : 'GET',
            success : function(result) {
                ACC.minicart.$layer.html(result);
                if($.isFunction(callback)) callback();
            }
        });
    },
    
    	var qtd_cart = $(".yCmsComponent .active span.count150519").html();
    	var qtd_add = $(".addToCartForm input[name='qty']").val(); 
    	
    	var total = parseInt(qtd_cart)+parseInt(qtd_add);
    	
    	
//        $.ajax({
//            dataType : "json",
//            url : ACC.minicart.$layer.attr("data-refreshMiniCartUrl")
//                    + Math.floor(Math.random() * 101) * (new Date().getTime()),
//            success : function(data) {
    	
    	$.ajax({
            dataType : "json",
            url : "/cart/miniCart/?"
                    + Math.floor(Math.random() * 101) * (new Date().getTime()),
            success : function(data) {
            	
            	$(".yCmsComponent span.count150519").html(data.cartCount);
            }
        });
    }
};

$(document).ready(function() {
	ACC.minicart.bindMiniCart();
	ACC.minicart.showMiniCart();
	/*$('.fa-shopping-cart').click(function(){
		ACC.minicart.refreshMiniCartCount();
	});*/
});
