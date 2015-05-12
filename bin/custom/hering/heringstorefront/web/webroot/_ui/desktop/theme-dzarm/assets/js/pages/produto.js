//init
$(document).ready(function() {
	
	$('.bx-controls').css('display','table-row');
	
	// fancybox para lightbox e modal
	$('.fancybox.formas-pagamento, .fancybox.prazos-entrega').fancybox({
		maxWidth: 720,
		minWidth: 320,
		padding: 20
	});
	
	$('.fancybox.tabela-medidas').fancybox({
		minWidth: 1024,
		padding: 20,
		afterShow: function(){
			$(".fancybox-opened").css("width", "auto");
		}
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
	
	$('#modal-tabela-medidas input').keypress(function(e) {
		if($(this).hasClass('medida-altura') && $(this).val().length==1) {
			$(this).val($(this).val()+',');
		}
	});

	var quant_mcart = $('#carrinho .bag .count').text();
    if(quant_mcart != 0){
        $('.fa.fa-fw.fa-shopping-cart').append('<span>'+quant_mcart+'</span>');
    }
    
    

});

isSmallDevice = (window.matchMedia && window.matchMedia("(max-width: 880px)").matches);
$prodImg = $('.product-images');
$images = $prodImg.find('.photos');
$prodMain = $('#product-main');
$bulletContainer = $(' .bx-pager').empty();
productMainImgWrapper = $('#product-main-img');
clickToZoomOut = productMainImgWrapper.find('.click-to-zoom-out');
windowDimensions = {
	width : window.innerWidth || document.body.clientWidth,
	height : window.innerHeight || document.body.clientHeight
};
windowVerticalCenter = Math.floor($(window).height() / 2);

// declare variables used constantly (especially on scrolling events)
// THAT DEPEND IMAGE SIZE (AFTER LOADED)
// for performance reasons better keep them in memory instead of
// searching for them on every event trigger.
// they are kept in global context because are used on events, which
// would lose the context.
$(window).load(
		function() {
			var $this = $(this);

			if (!window.isSmallDevice) {
				window.endOffset = $prodImg.scrollTop() + $prodImg.height()
						- $prodMain.height() - 10;
			}
			$('#product-main, .product-main-info').addClass('fixedPosition');
			$this.trigger('scroll');

			$prodImg.addClass('loaded');

			$('.section-mini-content').find('.bx-has-controls-direction').css('display','block');
			$('.section-mini-content').find('.bx-has-controls-direction').css('position','static');
			$('.section-mini-content').find('.bx-has-controls-direction').removeClass('bx-has-controls-direction');
			$('.section-mini-content').find('.bx-controls').removeClass('bx-controls');
			
		});

var ProductEventHandler = {

	initImagesSliderForSmallDevices : function() {
		// setup mobile slider
		var $sliderContainer = $('.product-images > ul');
		window.mainImagesSlider = $sliderContainer.bxSlider({
			controls : false,
			pager : true,
			auto : false,
			maxSlides : 1,
			minSlides : 1,
			infiniteLoop : false,
			slideWidth : $sliderContainer.width(),
			responsive : true,
			slideMargin : 10,
			useCSS : false,
			touchEnabled : true,
			oneToOneTouch : false
		});
	},

	/**
	 * Triggered by desktop bullets
	 */
	scrollTo : function(event) {
		var index = $(this).data('slide-index');
		var imgOffset = $images.eq(index).offset();

		$('html, body').animate({
			scrollTop : (imgOffset.top - 70) + 'px'
		});

		return false;
	},

	/**
	 * Treggered everytime user scrolls the page. Upates the bulllets and the
	 * product info positioning to keep them always visible
	 */
	onScroll : function() {
		var currentOffset = $(document).scrollTop();
		if (window.endOffset === undefined)
			window.endOffset = 0;

		$images.each(function(index) {
			var rect = this.getBoundingClientRect();
			if (rect.top <= windowVerticalCenter
					&& rect.bottom > windowVerticalCenter) {
				$bulletContainer.find('.bx-pager-link').removeClass('active')
						.eq(index).addClass('active');
				return false;
			}
		});

		// reach img top
		
		if($('.product-images > ul > li').length >= 2){
			if (!$prodMain.is('.fixedPosition') && currentOffset > 70 && currentOffset < window.endOffset) {
				$prodMain.css({
					top : 0,
					'margin-top' : '70px'
				});
				$('#product-main, .product-main-info').addClass('fixedPosition');
				$('.bx-controls').css('position','fixed');
				if (!window.isSmallDevice) {
					$prodImg.find('.bx-controls').fadeIn('fast');
				}
	
				// reach img bottom
			} else if ($prodMain.is('.fixedPosition') && (currentOffset >= window.endOffset)) {
				$('#product-main, .product-main-info').removeClass('fixedPosition');
				$('.bx-controls').css('position','absolute');
				$prodMain.css({	top : (endOffset - 55) + 'px', 'margin-top' : (endOffset - 55)
				});
	
				if (!window.isSmallDevice) {
					$prodImg.find('.bx-controls').fadeOut('fast');
				}
	
				// reach img top
			} else if ($prodMain.is('.fixedPosition') && (currentOffset <= 70)) {
				$prodMain.css({	top : '10px', 'margin-top' : 0 	});
				$('#product-main, .product-main-info').removeClass('fixedPosition');
				$('.bx-controls').css('position','absolute');
			}
		}else{
			$('#product-main, .product-main-info').removeClass('fixedPosition');
			$(".bx-controls").hide();
		}
	},
	onClickZoomOut : function() {
		var $this = $(this);
		$('.product-images, #product-main').show();
		$(document).scrollTop($this.data('latestScrollTop'));
		$this.fadeOut();
		return false;
	},

	onClickZoomIn : function() {
		var $img = $(this).find("img");
		var css = $.extend({}, windowDimensions, {
			'display' : 'block', 'background-image' : 'url(' + $img.attr('data-zoom-image-source') + ')'
		});
		var data = $.extend({}, $img.data(), {
			latestScrollTop : $(document).scrollTop()
		});
		
		//Verifica se eh mobile
		var userAgent = navigator.userAgent.toLowerCase();
		var isMobile = userAgent.search(/(android|avantgo|blackberry|bolt|boost|cricket|docomo|fone|hiptop|mini|mobi|palm|phone|pie|tablet|up\.browser|up\.link|webos|wos)/i);
		if( isMobile == -1 )
		{
			productMainImgWrapper.data(data).css(css).trigger('mousemove');
			$('.product-images, #product-main').hide();
			return false;
		}
	},
	
	onMouseMoveOverZoom : function(e) {
		if (productMainImgWrapper.is(':visible')) {
			// símbolo de zoom-out
			clickToZoomOut.css({top: e.pageY+'px', left: e.pageX+'px'});
			// movimenta imagem do background com o movimento do mouse
			var $this = $(this);
			var dimensions = $this.data();

			var bgPosX = ((e.pageX/windowDimensions.width)*100);
			var bgPosY = ((e.pageY/windowDimensions.height)*100);
			$this.css({
				"background-position": bgPosX+'% '+bgPosY+'%'
			});
		}
	},
	
	onResize : function() {
		var $window = $(window);

		isSmallDevice = (window.matchMedia && window
				.matchMedia("(max-width: 880px)").matches);
		if (!isSmallDevice && window.mainImagesSlider) {
			mainImagesSlider.destroySlider();
			mainImagesSlider = undefined;
		}

		if (isSmallDevice) {
			// //hide desktop bullets
			$('.product-images > .bx-controls').hide();

			if (!window.mainImagesSlider) {

			}

		}

		$images.each(function(index) {
			$(this).width($window.width());
		});

		$window.trigger('load');
	}



};

/** ********************************************* */
/* Finally, initialize elements and bind events */
/** ********************************************* */

// setup scrolling bullets
var bulletsHTML = '';
$images
		.each(function(index) {
			var $this = $(this);
			$this.find('img').width($(window).width());
			var videoClass = ($this.find('.responsive-video').size()) ? 'video'
					: '';
			bulletsHTML += '<div class="bx-pager-item"><a href="javascript:void(0)" data-slide-index="'
					+ index
					+ '" class="bx-pager-link '
					+ videoClass
					+ '">1</a></div>'
		});
$(bulletsHTML).find('.bx-pager-link').click(ProductEventHandler.scrollTo).end().appendTo($bulletContainer);

$(window).resize(ProductEventHandler.onResize).scroll(
		ProductEventHandler.onScroll);
// bind events for zoom interactions
$('.product-images > ul >li  > a').click(ProductEventHandler.onClickZoomIn);
productMainImgWrapper.click(ProductEventHandler.onClickZoomOut);
productMainImgWrapper.mousemove(ProductEventHandler.onMouseMoveOverZoom);

if (window.isSmallDevice) {
	// //hide desktop bullets
	$('.product-images > .bx-controls').hide();
	ProductEventHandler.initImagesSliderForSmallDevices();
}