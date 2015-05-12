var hering = {commons: {}, form: {} };
hering.commons = {
	
	init : function() {
		hering.commons.menuFix();
		hering.commons.addressPostCode_onFocus_onBlur();
	},
	
	menuFix: function () {
		$('#main-menu > li:eq(1)').addClass('activewear');
		$('#main-menu > li:eq(2)').addClass('beachwear');
		$('#main-menu > li:eq(3)').addClass('underwear');
		$('#main-menu > li:eq(4)').addClass('loungewear');
		$('#main-menu > li:eq(5)').addClass('sleepwear');		
	},
	
	addressPostCode_onFocus_onBlur : function() {
		$(document).on('focus', 'input[id="address.postcode"]',
			function() {
				onlyNumbers($(this));
				$(this).attr('maxlength', '8');
			});
		$(document).on('blur', 'input[id="address.postcode"]', 
			function() {
			var parentForm = $(this).closest('form') ? 'form#' + $(this)
					.closest('form').prop('id') 
					: '';
				var postcode = $(this).val();
				$('#loadingAddress').show();
				$.ajax({
					url : '/store/my-account/get-by-zipcode',
					type : 'GET',
					data : 'zipcode=' + postcode,
					dataType : 'json',
					success : function(data) {
						$('#loadingAddress').hide();
						if (data != null && data != '') {
							var obj = jQuery.parseJSON(data);
							if (obj) {
								var street = obj.logradouro;
								var city = obj.cidade;
								var state = obj.estado;
								var district = obj.bairro;
								$(parentForm + ' input[id="address.district"]')
										.val(district);
								$(parentForm + 'input[id="address.neighborhood"]')
										.val(district);
			            		$(parentForm + ' input[id="address.line1"]')
			            				.val(street);
			            		$(parentForm + ' select[id="address.state"]')
			            				.val("BR-"+state);
			            		$(parentForm + ' select[id="address.regionIso"]')
			            				.val("BR-"+state);
			            		$(parentForm + ' input[id="address.townCity"]')
			            				.val(city);
							}
						}
					},
					error: function() {
						$('#loadingAddress').hide();
					}
				});
			});
	},

	// define common initializations
	bindNumberInput: function() { // original
		// controle de quantidade em input:number
		$('input[type=number]').wrap('<div class="input-number-wrapper" />').after('<div class="input-number-control control-up" /><div class="input-number-control control-down" />')
		$('.input-number-control').click(function() {
			var inputVal = parseInt($(this).siblings('input').val(), 10);
			var limite = parseInt($('.productStock').attr('value'))
			if($(this).hasClass('control-up') && inputVal < limite) {
				$(this).siblings('input').val(inputVal+1);
			}
			if($(this).hasClass('control-down') && inputVal > 1) {
				$(this).siblings('input').val(inputVal-1);
			}
		});
	},
	bindSearchInput: function() { // original
	    // Botão de limpar busca em navegadores que não suportam input=search
	    $('input[type=search]').keyup(function() {
	        var inputSearch = $(this);
	        if($(this).val().length > 0 && $(this).val() !== $(this).attr("data-placefocus")) {
	            var wrap = $(this).parents(".input-search-wrap");
	            var wrapCancelBtn = $(this).siblings(".input-search-cancel-btn")
	            if(!wrap[0]) {
	                $(this).wrap("<div class='input-search-wrap'></div>");
	                wrap = $(this).parents(".input-search-wrap");
	                wrap.css({
	                    width: $(this).outerWidth(),
	                    height: $(this).outerHeight(),
	                    marginTop: $(this).css('margin-top'),
	                    marginRight: $(this).css('margin-right'),
	                    marginBottom: $(this).css('margin-bottom'),
	                    marginTop: $(this).css('margin-left')
	                });
	            }
	            if(!wrapCancelBtn[0]) {
	                wrap.append("<i class='fa fa-times-circle input-search-cancel-btn'></i>");
	            }
	            $(this).focus();

	            $(this).siblings(".input-search-cancel-btn").click(function() {
	                inputSearch.val('').focus();
	                $(this).remove();
	            });
	        } else {
	            $(this).siblings(".input-search-cancel-btn").remove();
	            $(this).val('').focus();
	        }
	    })
	    //emula um placeholder
	    $('[data-placefocus]').each(function(){
	        var placeholder = $(this).attr('data-placefocus');
	        $(this).val(placeholder);
	        $(this).css('color','#474747');

	        $(this).focus(function(){
	            if($(this).val()==placeholder){
	                $(this).val('');
	                $(this).css('color','');
	            }
	        });
	        
	        $(this).blur(function(){
	            if($(this).val()==''){
	                $(this).val(placeholder);
	                $(this).css('color','#474747');
	            }
	        });
	    });
	},
	bindMobileMenu: function() { // modified
	    // ativação de menu em mobile
	    $('#menu-toggle').click(function(e){
	        e.preventDefault();

		    $(this).toggleClass("close-menu");
	        $('#main-menu').toggleClass('active');
	        $('#main-menu.active > li.with-sub-menu').removeClass('active');

	        // fix the posicionamento
	        $(window).scroll(function() { subMenuRepositioning() });
	        $(window).resize(function() { subMenuRepositioning() });
	        function subMenuRepositioning() {
	            documentScrollPos = $(document).scrollTop();
	            mainMenuPosY = $("#main-menu.active").offset().top;
	            activeSubMenu = $("#main-menu.active > li.with-sub-menu.active .sub-menu");

	            if($("#main-menu > li.with-sub-menu").hasClass("active") && $(window).width() < 880) {
	                submenuNewPos = mainMenuPosY - documentScrollPos;
	                $("#main-menu.active > li.with-sub-menu.active .sub-menu").css("top", submenuNewPos);
	            } else {
	                $("#main-menu > li .sub-menu").css("top", "auto");
	            }
	        }

	        $('#main-menu > li.with-sub-menu > a').click(function(e){
	            e.preventDefault();

	            $(this).parent().parent().find('li.with-sub-menu').removeClass('active');
	            $(this).parent().toggleClass('active');

	            subMenuRepositioning();
	        });
	    });
	},
	bindRadioControls: function() { // original
	    // função para tocar e pausar a rádio
	    $('#top-navigation .utils .radio .play').click(function(e) {
	        e.preventDefault();

	        $(this).toggleClass('playing');
	    });
	},
	bindMiniCartControls: function() { // original
	    // botão de remover no mini carrinho da header
	    $('#carrinho .mini-carrinho .btn-remove').click(function(e) {
	        e.preventDefault();

	        $(this).parent().stop(0,1).slideUp().promise().done(function() {
	            var productsUL = $(this).parent();
	            $(this).remove();
	            if(productsUL.children("li").length == 0) {
	                productsUL.parent().addClass("empty").prepend("<p><i class='fa fa-meh-o'></i> Carrinho vazio.</p>");
	            }
	        });
	    });
	},
	// rotatingimagescomponent.jsp
	bindBigSlider: function() { // original
	    // sliders
	    $('.big-slider').each(function(){
	        var slider = $(this).find('.bxslider').bxSlider({
	            controls: false,
	            pager: true,
	            auto: true,
	            maxSlides: 1,
	            infiniteLoop: true,
	            touchEnabled: true
	        });
	    });
	},
	// Quem viu este produto também comprou
	bindGlobalSliders: function() { // modified
	    $('.product-slider').each(function(){
	        //var nSlides = $(this).hasClass('slide-2')?2:3;
	        var pager = $(this).hasClass('custom-pager');
	        var hasblueStripe = $(this).hasClass('has-blue-stripe');
	        var ms = '';

	        var smargin = $(this).attr('data-slide-margin')?parseInt($(this).attr('data-slide-margin'), 10):1;
	        var slideWidth = ($(this).hasClass('bigger-image')) ? 300 : 296;
	        var slider = $(this).bxSlider({
	            controls: false,
	            pager: true,
	            auto: false,
	            maxSlides: 4,
	            minSlides: 1,
	            infiniteLoop: true,
	            slideWidth: slideWidth,
	            responsive: true,
	            slideMargin: smargin,
	            useCSS: false,
	            touchEnabled: true,
	            oneToOneTouch: false
	        });

	        // Replica o objeto na array global globalSlidersObjects com a ID da div wrapper
	        productInfiniteSliderId = $(this).parents('.product-infinite-wrapper').attr("id");
	        if(productInfiniteSliderId !== "") {
	        	if(!window.globalSlidersObjects) {
	        	    window.globalSlidersObjects = new Array();
	        	}
	            window.globalSlidersObjects.push(productInfiniteSliderId);
	            window.globalSlidersObjects[productInfiniteSliderId] = slider;
	        }
	    });
	},
	bindColorSliders: function() { // original
	    $('.cores .bxslider-cores, #bar-secondary .cores ul, #section-secondary .cores ul, .product .info .cores ul').each(function(){
	        var slider = $(this).bxSlider({
	            slideWidth: 30,
	            maxSlides: 9,
	            minSlides: 3,
	            controls: true,
	            pager: false,
	            hideControlOnEnd: false,
	            auto: false,
	            slideMargin: 2,
	            infiniteLoop: false,
	            touchEnabled: true
	        });

	        var bxWrapper = slider.parent().parent();
	        var prevBtn = bxWrapper.find('.bx-controls-direction a.bx-prev');
	        if(prevBtn.hasClass('disabled')) {
	            slider.parent().parent().addClass('only-one');
	        }
	    });
	},
	bindTabs: function() { // modified
	    $('.tabs').each(function(){
	        var tcon = $(this).find('.tabs-content');
	        tcon.find('.tab').hide();
	        tcon.find('.tab').eq(0).show();

	    	// Due to how Javascript scope works, tcon is shared between
	        // calls and gets overwriten, creating undesired behavior.
	    	// Creating workaround using closures
	    	function createFunctionScope(tcon) {
	    		return function(e){
		            e.preventDefault();

		            var index = $(this).parent().index();
		            tcon.find('.tab').hide();
		            tcon.find('.tab').eq(index).show();

		            $(this).parent().parent().find('li').removeClass('active');
		            $(this).parent().addClass('active');
		        }
	    	}
	        $(this).find('.tabs-header').find('li').find('a').click(createFunctionScope(tcon));
	    });
	},
	bindHeader: function() { // modified
		var infiniteProductScroll = function() {
	        // scroll infinito de produtos
	        var infiniteDivWrapper = $('.infinite-product-scroll');
	        var infiniteContent = infiniteDivWrapper.find('.infinite-scroll-content');
	        var loadingIcon = infiniteDivWrapper.find('.loading-icon');
	        var infiniteDivPosition = infiniteDivWrapper.height() - $(window).height();
	        var infiniteCountLimit = ($(window).width() > 1199)?3:2;
	        var filtersBottom = $('.page .right .filters.center');
	        if ($(window).scrollTop() >= infiniteDivPosition && window.infiniteCount < infiniteCountLimit){
	            loadingIcon.fadeIn('fast');
	            infiniteContent.append(infiniteScrollLoadProducts()).promise().done(function() {
	                loadingIcon.hide();
	            });
	            window.infiniteCount++;
	        } else {
	            filtersBottom.show();
	        }
		};
	    // voltar ao topo
	    $(window).scroll(function() {
	        //voltar ao topo
	        if ($(this).scrollTop() > 60) {
	            $("#back-top").stop(0,1).fadeIn();
	            $("#mini-header .content").slideDown('medium');
	            $("#mini-header #carrinho").addClass('scrolling');
	        } else {
	            $("#back-top").stop(0,1).fadeOut();
	            $("#mini-header .content").slideUp('medium');
	            $("#mini-header #carrinho").removeClass('scrolling');
	        }

	        // carrega mais produtos na listagem de produtos ao rolar a página
//	        infiniteProductScroll(); // no final do arquivo
	    });
	},
	bindSearchComponents: function() { // original
	    //ativação de ícones de actions na header
	    $('#search .actions li.hide-on-desktop i').click(function(e) {
	        // remove classe active dos irmãos
	        $(this).parent().siblings().each(function() {
	            $(this).find('i').removeClass('active');
	        });
	        // desativa busca
	        $('#search .actions .input-group, .mini-carrinho.responsive').removeClass('active');
	        // liga/desliga classe active
	        $(this).toggleClass('active');
	        // ativa ação de busca
	        if( $(this).hasClass('fa-search') && $(this).hasClass('active') ) {
	            $('#search .actions .input-group').toggleClass('active');
	        }
	        if( $(this).hasClass('fa-shopping-cart') && $(this).hasClass('active') ) {
	            var responsiveCart = $(this).siblings('.mini-carrinho.responsive');
	            if(responsiveCart[0]) {
	            	responsiveCart[0].remove();
	            }
	            $(this).parent().append($('#carrinho .mini-carrinho').clone().addClass('responsive'));
	            $('.mini-carrinho.responsive').toggleClass('active');
	            $('.mini-carrinho.responsive').css('display', 'block');
	        }
	        if( $(this).hasClass('fa-shopping-cart') && !$(this).hasClass('active') ) {
	        	$('.mini-carrinho.responsive').css('display', 'none');
	        }
	    });
	},
	
	bindTooltips: function() { // modified
		// inicializa tooltips
		$('.cores li').hover(function(e) {
			var tooltip = $(this).find('.tooltip');
			if(!tooltip.length) {
				var text = $(this).attr('class')
				.replace('active', '')
				.replace('bx-clone', '')
				.replace('colorbox', '')
				.replace('styleVariantEventHandler', '')
				.replace('sizeVariantEventHandler', '')
				.replace('variantSelectorHandler', '')
				.toLowerCase().trim();
				text = text.charAt(0).toUpperCase() + text.slice(1);
				$(this).append('<div class="tooltip">'+text+'</div>');
				tooltip = $(this).find('.tooltip');

				// hover de cores
				$('.cores li').hover(function(e) {
					// tooltip
					var tooltip = $(this).find('.tooltip');
					// exibir tooltip para a esquerda, caso a cor estiver em mais da metade do slider
					var sliderContainer = $(this).parent().parent();
					var sliderWidthHalf = sliderContainer.outerWidth()/2;
					var mousePosX = e.pageX - sliderContainer.offset().left;
					if(sliderWidthHalf > 30 && mousePosX >= sliderWidthHalf) {
						tooltip.addClass('right');
					}
					tooltip.show();
				}, function() {
					$(this).find('.tooltip').hide();
				});
			}
			// depois de inicializado, nao precisa mais responder ao evento
			$(this).unbind(e);
		});
	},
	bindColorSelector: function() { // original
		// clique de cor
		$('.cores li').click(function(e) {
			// remove classe active de todas as cores
			if($(this).parents('.bx-wrapper').length > 0) {
				$(this).parent().find("li").removeClass("active");
				$(this).toggleClass('active');
			}
		})
	},
	bindSizeSelector: function() { // modified
		// ativação de tamanho ao clicar
		$('.tamanhos ul li a').click(function(e) {
			e.preventDefault();
			var li = $(this).parent();
			if(!li.hasClass('tabela') && !$(this).hasClass('out-of-stock')) {
				// remove classe active de todos os tamanhos
				$(this).parent().parent().find("a").removeClass("active");
				$(this).toggleClass('active');
			}
		});
	},
	bindStarRating: function() { // original
		// estrelas de avaliação
		$(".stars .star").hover(function() {
			$(".stars .star").removeClass("active");
			$(this).prevAll().addClass('active');
			$(this).addClass('active');
			// define valor do input
			$(this).parent().find("input").val($(this).prevAll().length + 1);
		});
	},
	bindToaster: function() { // modified
		window.toasterActive = undefined;
		window.showToaster = function(msg) {
			var toaster = $("#toaster");
			if(!toaster[0]) {
				$("body").prepend('<div id="toaster"><div class="content"></div></div>');
				toaster = $("#toaster");
			}
			clearTimeout(toasterActive);
			toaster.find(".content").text(msg);
			toaster.addClass("active").promise().done(function() {
				toasterActive = setTimeout(function() { toaster.removeClass("active") }, 10000);
			});
		}
	},
	bindAutoToaster: function() { // custom
		$(window).load(function(){
			if ($('#main-wrapper div').hasClass('alert')){
				var mensagem = $('div.alert').text();
				showToaster(mensagem);
			};
		});
	},
	bindAll: function() {
		this.bindNumberInput();
		this.bindSearchInput();
		this.bindMobileMenu();
		this.bindRadioControls();
		this.bindMiniCartControls();
		this.bindBigSlider();
		this.bindGlobalSliders();
		this.bindColorSliders();
		this.bindTabs();
		this.bindHeader();
		this.bindSearchComponents();
		//this.bindAutoCompleteSearch();
		this.bindTooltips();
		this.bindColorSelector();
		this.bindSizeSelector();
		this.bindStarRating();
		this.bindToaster();
		this.bindAutoToaster();
	}
};

$(document).ready(function() {
	hering.commons.init();
});