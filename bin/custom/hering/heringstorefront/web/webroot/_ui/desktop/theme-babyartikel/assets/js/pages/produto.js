//init
$(document).ready(function() {
	// fancybox para lightbox e modal
	$('.fancybox.formas-pagamento, .fancybox.prazos-entrega').fancybox({
		maxWidth: 700,
		minWidth: 320,
		padding: 20
	});
	
	$('.fancybox.tabela-medidas').fancybox({
		minWidth: 320,
		padding: 20
	});

	$('.fancybox.modal-avaliacao').fancybox({
		maxWidth: 700,
		closeBtn: true,
		helpers : {
			overlay: {
				closeClick: true
			}
		}
	});

	$('.fancybox-media').fancybox({
		openEffect: 'none',
		closeEffect: 'none',
		helpers: {
			media : {}
		}
	});
	
	// IMAGEM DE PRODUTO
	var productMainImgWrapper = $('#product-main-img');
	var productMainImgWrapperSrc = productMainImgWrapper.data('image-source');
	var productZoomImgWrapperSrc = productMainImgWrapper.data('zoom-image-source');
	var imageWrapperLoaded = false;
	var zoomLoaded = false;

	// pega a url da imagem principal de produto e a define como background
	if(productMainImgWrapperSrc[0]) {
		$('#product-main-img').css({
			"background-image": 'url('+productMainImgWrapperSrc+')'
		}).promise().done(function() {
			$(this).stop(1,1).fadeIn("slow");
		});
	}

	// adiciona botÃ£o de zoom-out na imagem principal do produto
	if(!productMainImgWrapper.has('.click-to-zoom-out')[0]) {
		productMainImgWrapper.html('<div class="container"><i class="click-to-zoom-out"></i></div>');
	}
	// aÃ§Ã£o de zoom no clique da imagem
	var prodMainImgWrapOriginalHeight = productMainImgWrapper.css("height");
	$('.product-thumbnails .product-main-img-mask').click(function() {
		productMainImgWrapperSrc = productMainImgWrapper.data('image-source');
		productZoomImgWrapperSrc = productMainImgWrapper.data('zoom-image-source');

		// Define a imagem de zoom como background do container da imagem aberta
		productMainImgWrapper.stop(1,1).fadeOut("slow").promise().done(function() {
			productMainImgWrapper.css({
				"background-image": "url("+productZoomImgWrapperSrc+")"
			})
		});
		// AÃ§Ãµes de movimentaÃ§Ã£o de cursor e FX
		$('#product-main, #page-header').stop(1,1).fadeOut(function() {
			productMainImgWrapper.addClass('zoomed-in');
			$('#main-wrapper').addClass('zoom-activated');

			// define altura da image wrapper pro tamanho do navegador
			resizeZoomImgWrapper(productMainImgWrapper);
			$(window).resize(function() { resizeZoomImgWrapper(productMainImgWrapper); });

			// aÃ§Ãµes do cursor com o movimento do mouse
			$("#product-main-img.zoomed-in").mousemove(function(e) {
				if(productMainImgWrapper.hasClass('zoomed-in')) {
					// sÃ­mbolo de zoom-out
					$(".click-to-zoom-out").css({top: (e.pageY-$(this).offset().top)+'px', left: e.pageX+'px'});
					// movimenta imagem do background com o movimento do mouse
					$.easing.smoothmove = function (x, t, b, c, d) {
						return -c *(t/=d)*(t-2) + b;
					};
					var mousePosY = e.pageY-($(this).offset().top);
					var bgPosX = "center";
					var bgPosY = (mousePosY*1.5 - $(this).height()/2)*-2+"px";
					console.log(bgPosY);
					$(this).css({
						"background-position": bgPosX+' '+bgPosY
					})
				}
			});

			$('.click-to-zoom-out').stop(1,1).fadeIn("slow");
			productMainImgWrapper.stop(1,1).fadeIn("slow", function() {
				zoomLoaded = true;
			});

			if(productMainImgWrapper.hasClass("zoomed-in") && zoomLoaded) {
				$('#product-main-img.zoomed-in').click(function() {
					productMainImgWrapper.stop(1,1).fadeOut("medium").promise().done(function() {
						productMainImgWrapper.css({
							"background-image": "url("+productMainImgWrapperSrc+")"
						})

						// Redefine altura original do container
						productMainImgWrapper.css("height", prodMainImgWrapOriginalHeight);
						
						// Remove o css 'opacity' da propriedade style remanescente do fadeOut do #page-header
						$('#page-header').css("opacity", "");
					});
					
					
					productMainImgWrapper.stop(1,1).fadeIn("medium").promise().done(function() {
						$(this).removeClass('zoomed-in');
						$(this).css({'background-position': ''});
						$('#product-main, #page-header').stop(1,1).fadeIn();
						$('.click-to-zoom-out').stop(1,1).fadeOut("slow");
						$('#main-wrapper').removeClass('zoom-activated');
					});
					zoomLoaded = false;
				});
			}
		});
	});

	// aÃ§Ã£o de clique em thumbnails
	$('.product-thumbnails ul li').click(function(e) {
		e.preventDefault();
		var productImgSrc = $(this).find('a').data('image-source');
		var productZoomImgSrc = $(this).find('a').data('zoom-image-source');

		if(productImgSrc[0]) {
			$(this).siblings().each(function() { $(this).removeClass('active'); })
			$(this).addClass('active');
			productMainImgWrapper.stop(1,1).fadeOut("slow", function() {
				$(this).data("image-source", productImgSrc);
				$(this).data("zoom-image-source", productZoomImgSrc);
				$(this).css("background-image", "url("+productImgSrc+")");
				$(this).stop(1,1).fadeIn("slow");
			})
		}
	});

	// mÃ¡scara em tabela de medidas
	$('#modal-tabela-medidas input').keypress(function(e) {
		if($(this).hasClass('medida-altura') && $(this).val().length==1) {
			$(this).val($(this).val()+',');
		}
	});

	// aÃ§Ã£o padrÃ£o de botÃ£o avise-me em section-mini
	$('.section-mini-content .avise-me').click(function(e) {
		e.preventDefault();
		$(this).parents('.product-mini').find('.esgotado-form').fadeIn("medium");
	});
	
	var quant_mcart = $('#carrinho .bag .count').text();
	if(quant_mcart != 0)
		$('.fa.fa-fw.fa-shopping-cart').append('<span>'+quant_mcart+'</span>');
	
	//atualiza mini-cart responsivo
//    $('.addToCartButton').click(function(){
//    	var ww = $(window).width();
//    	$('body').scrollTop(0);
//		//window.location.reload();
//		setTimeout(function(){
//		    window.location.reload();
//		     
//		}, 1000);
//    });
    
    $('.cadastre-se.logged-in > a').click(function(){
    	var ww = $(window).width();
    	if(ww < '1190'){
    		return false;
    	}
    });
    
    $("#main-menu li").hover(function(e) {
        repositionDesktopSubmenu($(this));
    });
    
})

function disableWindowScroll(el) {
	el.hover(function() {
	    $(document).bind('mousewheel DOMMouseScroll',function(e){ 
	        if(!e){ /* IE7, IE8, Chrome, Safari */ 
		        e = window.event; 
		    }
		    if(e.preventDefault) { /* Chrome, Safari, Firefox */ 
		        e.preventDefault(); 
		    }
		    e.returnValue = false; /* IE7, IE8 */
	    });
	}, function() {
	    $(document).unbind('mousewheel DOMMouseScroll');
	});
}

function resizeZoomImgWrapper(imgWrapper) {
	var miniHeaderHeight = ($("#mini-header")[0])?$("#mini-header").outerHeight():0;
	var documentScrollTo = imgWrapper.offset().top - miniHeaderHeight;
	$(window).scrollTop(documentScrollTo).promise().done(function() {
		// disable scroll
		disableWindowScroll(imgWrapper);
		
		imgWrapperTop = imgWrapper.offset().top;
		imgWrapperNewHeight = $(window).height() - miniHeaderHeight;
		imgWrapper.css("height", imgWrapperNewHeight);
	})
}


function repositionDesktopSubmenu(el) {
    if($(window).width() > 880) {
        var submenu = el.children(".sub-menu");
        if(submenu[0]) {
            submenu.css("left", 0);
            var container = ($(window).width() > 980) ? el.parents(".container") : $(window);
            var submenuLeftPos = (container.hasClass("container")) ? submenu.offset().left - container.offset().left : submenu.offset().left;
            // verifica se o menu está passando dos limites do container
            var submenuOutbounds = ((container.width() - submenuLeftPos) < submenu.outerWidth())?true:false; 
            if(submenuOutbounds) {
                var leftPos = ((container.width() - submenuLeftPos) - submenu.outerWidth())*1.05;
                submenu.css("left", leftPos);
            }
        }
    }
}

$(document).ready(function(){
	 (function () { 
		    var _tsid = 'XA2EF864014A142CF9EDC2483FE556551'; 
		    _tsConfig = { 
		        'yOffset': '10',
		        'variant': 'reviews',
		        'trustcardDirection': 'bottomRight',
		        'customBadgeWidth': '40', 
		        'customBadgeHeight': '40', 
		        'disableResponsive': 'false', 
		        'disableTrustbadge': 'false' 
		    };
		    var _ts = document.createElement('script');
		    _ts.type = 'text/javascript'; 
		    _ts.charset = 'utf-8'; 
		    _ts.async = true; 
		    _ts.src = '//widgets.trustedshops.com/js/' + _tsid + '.js'; 
		    var __ts = document.getElementsByTagName('script')[0];
		    __ts.parentNode.insertBefore(_ts, __ts);
		    })();
});