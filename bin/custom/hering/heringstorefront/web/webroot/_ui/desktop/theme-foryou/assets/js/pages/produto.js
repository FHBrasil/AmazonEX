var equipamento_touch = ("ontouchstart" in window || window.DocumentTouch && document instanceof DocumentTouch);
if(equipamento_touch != true){
    equipamento_touch = false;
}
//init
$(document).ready(function() {

	var quant_mcart = $('#carrinho .bag .count').text();
    if(quant_mcart != 0){
        $('.fa.fa-fw.fa-shopping-cart').append('<span>'+quant_mcart+'</span>');
    }
	
	//StockLevel
	var stockLevel =$("#hdStockLevel").val();
	if(stockLevel < 0){
		$('.tamanhos ul').bxSlider({
		  minSlides: '1',
		  maxSlides: '2',
		  scrolling: 'no'
		});
		if( $("ul.bxslider-cores li").length > 2){
			$('ul.bxslider-cores').bxSlider({
				  minSlides: '1',
				  maxSlides: '2',
				  scrolling: 'no'
			});
		}
	}
	
	// fancybox para lightbox e modal
	$('.fancybox.formas-pagamento, .fancybox.prazos-entrega').fancybox({
		maxWidth : '100%',
        autoSize : true,
        fitToView: false,
        scrolling: 'no',
		padding: 20,
	    fitToView   : false,
	});
	$('.fancybox.tabela-medidas').fancybox({
        maxWidth: '100%',
        autoSize: true,
        fitToView: false,
        scrolling: 'no',
		padding: 20
	});

	$('.fancybox.modal-avaliacao').fancybox({
        maxWidth: '100%',
		closeBtn: true,
	    fitToView   : false,
	    helpers : {
	        overlay : {
	            opacity: 0.4,
	            locked: false
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

	// slider
    $('.product-main-slider').each(function(){
        var slider = $(this).find('.bxslider').bxSlider({
            controls: false,
            pager: true,
            auto: false,
            autoHover: true,
            maxSlides: 1,
            infiniteLoop: equipamento_touch,
            touchEnabled: equipamento_touch
        });
    });

	// máscara em tabela de medidas
	$('#modal-tabela-medidas input').keypress(function(e) {
		if($(this).hasClass('medida-altura') && $(this).val().length==1) {
			$(this).val($(this).val()+',');
		}
	});

	// ação padrão de botão avise-me em section-mini
	$('.section-mini-content .avise-me').click(function(e) {
		e.preventDefault();
		$(this).parents('.product-mini').find('.esgotado-form').fadeIn("medium");
	});
	
	// IMAGEM DE PRODUTO

	// background principal de produto
	var productMainImgWrapper = $('#product-main-img');
	if(!productMainImgWrapper[0]) {
		$("#main-wrapper").prepend('<div id="product-main-img"><div class="blurred-img"></div></div>');
		productMainImgWrapper = $("#product-main-img");
	}
	var useBlur = (productMainImgWrapper.data("use-blur")) ? productMainImgWrapper.data("use-blur") : false;
	var blurredImg = productMainImgWrapper.find('.blurred-img');
	if(useBlur) {
		blurMainImgBg();
    	blurredImg.fadeIn("slow");
	}

	var productMainImgWrapperSrc = productMainImgWrapper.data('image-source');
	var productZoomImgWrapperSrc = productMainImgWrapper.data('zoom-image-source');
	var imageWrapperLoaded = false;
	var zoomLoaded = false;
	var affectedElements = $('#product-main, #page-header, #product-main-img .blurred-img, .institucional-looks-interna');

	// pega a url da imagem principal de produto e a define como background
	// if(productMainImgWrapperSrc[0]) {
	// 	$('#product-main-img').css({
	// 		"background-image": 'url('+productMainImgWrapperSrc+')'
	// 	}).promise().done(function() {
	// 		$(this).stop(1,1).fadeIn("slow");
	// 	});
	// }

	// adiciona botão de zoom-out na imagem principal do produto
	if(!productMainImgWrapper.has('.click-to-zoom-out')[0]) {
		productMainImgWrapper.append('<div class="container"><i class="click-to-zoom-out"></i></div>');
	}
	// ação de zoom no clique da imagem
	var prodMainImgWrapOriginalHeight = productMainImgWrapper.css("height");
	$('.product-main-slider li a, .look-main-image').click(function(e) {

		//Verifica tamanho da tela
		if($(window).width() > 880){		
			//Desktop

			// Define a imagem escolhida para o Zoom
			var productZoomImgSrc = $(this).data('zoom-image-source');
			if(productZoomImgSrc) {
				$(this).addClass('active');
				productMainImgWrapper.stop(1,1).fadeOut("slow", function() {
					$(this).data("image-source", productZoomImgSrc);
					$(this).data("zoom-image-source", productZoomImgSrc);
					$(this).css("background-image", "url("+productZoomImgSrc+")");
					$(this).stop(1,1).fadeIn("slow");
				})
			}

			productMainImgWrapperSrc = productMainImgWrapper.data('image-source');
			productZoomImgWrapperSrc = productMainImgWrapper.data('zoom-image-source');

			// Ações de movimentação de cursor e FX
			affectedElements.stop(1,1).fadeOut('medium', function() {
				$('.click-to-zoom-out').stop(1,1).fadeIn("slow");
				productMainImgWrapper.stop(1,1).fadeIn("slow", function() {
					zoomLoaded = true;
					productMainImgWrapper.addClass('zoomed-in');
					$('#main-wrapper').addClass('zoom-activated');

					// define altura da image wrapper pro tamanho do navegador
					resizeZoomImgWrapper(productMainImgWrapper);
				});

				// ações do cursor com o movimento do mouse
				$("#product-main-img.zoomed-in").mousemove(function(e) {
					if(productMainImgWrapper.hasClass('zoomed-in')) {
						// símbolo de zoom-out
						$(".click-to-zoom-out").css({top: (e.pageY-$(this).offset().top)+'px', left: e.pageX+'px'});
						// movimenta imagem do background com o movimento do mouse
						var mousePosY = e.pageY-($(this).offset().top);
						var bgPosX = "center";
						var bgPosY = (mousePosY*1.5 - $(this).height()/2)*-2+"px";
						$(this).css({
							"background-position": bgPosX+' '+bgPosY
						})
					}
				});

				if(productMainImgWrapper.hasClass("zoomed-in") && zoomLoaded) {
					$('#product-main-img.zoomed-in').click(function() {
						productMainImgWrapper.stop(1,1).fadeOut("medium", function() {
							$(this).css({
								"background-image": ""
							});

							// Redefine altura original do container
							$(this).css("height", prodMainImgWrapOriginalHeight);
							// Restaura scroll da janela
							enableWindowScroll($(this));

							$(this).stop(1,1).fadeIn("medium").promise().done(function() {
								$(this).removeClass('zoomed-in');
								$(this).css({'background-position': ''});
								affectedElements.stop(1,1).fadeIn('medium');
								$('.click-to-zoom-out').stop(1,1).fadeOut("slow");
								$('#main-wrapper').removeClass('zoom-activated');
							});
							zoomLoaded = false;
						});
					});
				}
			});
		}
		
		else {
			//Mobile/Tablet
			var productZoomImgSrc = $(this).data('zoom-image-source');
			
			if(productZoomImgSrc){
				if($(".superzoom-mobile-overflow").index() < 0){
					$("body").append("<div class='superzoom-mobile-overflow'><a title='Close' class='fancybox-close' style='position: fixed'></a><div class='superzoom-mobile-content'><img></div></div>");
				}
				else {
					//Reseta
					$(".superzoom-mobile-overflow").html("<div class='superzoom-mobile-content'><img></div>");	
				}
				$(".superzoom-mobile-content img").attr("src",productZoomImgSrc).load(function(){
					$(".superzoom-mobile-content").width($(".superzoom-mobile-content img").width());
					$(".superzoom-mobile-content").height($(".superzoom-mobile-content img").height());
				});

				$(".superzoom-mobile-overflow").click(function(){
					$(this).fadeOut(200, function(){
						$(this).remove();
					});
				});
			}
		}

		e.preventDefault();
		return false;
	});

	 $('.product-main-slider li a').hover(function() {
		var that = $(this);
		var productMainSlider = $('.product-main-slider');
		var productImgSrc = $(this).find('img').attr('src');
		var productZoomImgSrc = $(this).data('zoom-image-source');
		
		// Coloca blur na imagem e a coloca como plano de fundo
		if(productImgSrc && productMainImgWrapper.data('image-source') != productImgSrc) {
			productMainImgWrapper.find('.blurred-img').stop(0,1).fadeOut("medium", function() {
				$(this).parent().data("image-source", productImgSrc);
				if(useBlur) {
					blurMainImgBg();
				}
				$(this).stop(0,1).fadeIn("medium");
			})
		}

		// Mini-zoom
		if($(this).parent().is('[class^=template]') 
				&& !$(this).parent().is('[class=template-0]') && $(window).width() > 880) {
			var miniZoomWrapper = $(this).parent().find('.mini-zoom-wrapper');
			if(!miniZoomWrapper[0]) {
				$(this).parent().prepend('<div class="mini-zoom-wrapper"><img src=""><i class="fa fa-spinner fa-spin"></i><div>');
				miniZoomWrapper = $(this).parent().find('.mini-zoom-wrapper');
			}

			// Define a imagem do mini-zoom e abre o wrapper
			var miniZoomImg = miniZoomWrapper.find('img');
			// Verifica se o mouse está na direita ou esquerda do quadro
			//if(($(this).offset().left-$(this).parent().offset().left) > ($(this).parents(".product-main-slider").width()/2)) {
			if($(this).hasClass("imgClassDireita")){
				miniZoomWrapper.css({
					left: 0,
					right: 'auto'
				})
			} else {
				miniZoomWrapper.css({
					left: 'auto',
					right: 0
				})
			}
			miniZoomWrapper.addClass("active").stop(0,1).fadeIn("fast");
			miniZoomImg.attr('src', productZoomImgSrc).load(function() {
				miniZoomWrapper.addClass("loaded");
				miniZoomImg.stop(0,1).fadeIn("slow");

				// ações de movimento de mouse em cima da thumbnail
				that.mousemove(function(e) {
					if(miniZoomWrapper.hasClass('active')) {
						var imagePosX = e.pageX - $(this).offset().left;
						var imagePosY = e.pageY - $(this).offset().top;
						var rx = parseFloat(imagePosX/$(this).width()*(miniZoomImg.width()/2))*-1;
						var ry = parseFloat(imagePosY/$(this).height()*(miniZoomImg.height()/2))*-1;
						miniZoomImg.css({
							left: rx,
							top: ry
						})
					}
				});
			});
		}
	}, function() {
		var miniZoomWrapper = $(this).parent().find('.mini-zoom-wrapper');
		miniZoomWrapper.fadeOut('fast');
	});
});

function blurMainImgBg() {
	var mainImgWrapper = $("#product-main-img");
	var blurredImg = mainImgWrapper.data("image-source");
	mainImgWrapper.find(".blurred-img").css("background-image", "url("+blurredImg+")").blurjs({
        source: '#product-main-img div',
        radius: 30,
        overlay: 'rgba(255, 255, 255, 0.5)'
    })
}

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

function enableWindowScroll(el) {
	el.unbind('mouseenter mouseleave');
	$(document).unbind('mousewheel DOMMouseScroll');
}

function resizeZoomImgWrapper(imgWrapper) {
	var miniHeaderHeight = 0;
	var documentScrollTo = imgWrapper.offset().top - miniHeaderHeight;
	// disable scroll
	disableWindowScroll(imgWrapper);
	
	imgWrapperTop = imgWrapper.offset().top;
	imgWrapperNewHeight = $(window).height() - miniHeaderHeight;
	imgWrapper.css("height", imgWrapperNewHeight);

	$('html, body').animate({ scrollTop: documentScrollTo }, 500);
}