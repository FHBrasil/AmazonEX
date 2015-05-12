//var colorClicks = 0;
//var loadClicks = 0;
//
//$("#resultsList").find(".product").each(function(k){ loadClicks++; });
//if( loadClicks == 0 ){ $("body").find(".product").each(function(k){ loadClicks++; }); }

var equipamento_touch = ("ontouchstart" in window || window.DocumentTouch && document instanceof DocumentTouch);
if(equipamento_touch != true){
    equipamento_touch = false;
}
$(document).ready(function() {
	
	$('.pagination li a').click(function(){ 
		var url = $(this).attr("href");
		window.location.href = url;
	});
	
	$('#modal-order-resume').css('display','none');
	$(".tabela-medidas").remove();
	
	$('.category dl').each(function(){
		var contentHtml = $(this).html().replace(/^\s+|\s+$/g,""); 
		if(contentHtml == 'codigoCor')
		{
			$(this).parent().css('display','none');
		}

	});
	
    // validação de formularios
    //formValidation();

    // controle de quantidade em input:number
    $('input[type=number]').wrap('<div class="input-number-wrapper" />').after('<div class="input-number-control control-up" /><div class="input-number-control control-down" />')
    $('.input-number-control').click(function() {
        var inputVal = parseInt($(this).siblings('input').val(), 10);
        if($(this).hasClass('control-up')) {
            $(this).siblings('input').val(inputVal+1);
        }
        if($(this).hasClass('control-down') && inputVal > 1) {
            $(this).siblings('input').val(inputVal-1);
        }
    });

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

    // reposicionamento de submenus em interface desktop
    $("#main-menu li").hover(function(e) {
        if($(window).width() > 880) {
            var submenu = $(this).children(".sub-menu");
            if(submenu[0]) {
                submenu.css("left", 0);
                var container = ($(window).width() > 980) ? $(this).parents(".container") : $(window);
                var submenuLeftPos = (container.hasClass("container")) ? submenu.offset().left - container.offset().left : submenu.offset().left;
                // verifica se o menu está passando dos limites do container
                var submenuOutbounds = ((container.width() - submenuLeftPos) < submenu.outerWidth())?true:false; 
                if(submenuOutbounds) {
                    var leftPos = ((container.width() - submenuLeftPos) - submenu.outerWidth())*1.05;
                    submenu.css("left", leftPos);
                }
            }
        }
    });

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

            $('#main-menu li.with-sub-menu').removeClass('active');
            $(this).parent().toggleClass('active');

            subMenuRepositioning();
        });

        if(!$("#main-menu").hasClass("active")) {
            $('#main-menu li.with-sub-menu').removeClass('active');
        }
    });

    // função para tocar e pausar a rádio
    $('#top-navigation .utils .radio .play').click(function(e) {
        e.preventDefault();

        $(this).toggleClass('playing');
    });

    // CARRINHO
    // delay blur mini-carrinho
    var cartDelayTimer;
    $('#carrinho, .mini-carrinho').hover(function() {
        clearTimeout(cartDelayTimer);
        $(this).addClass("hover");
    }, function() {
        var that = $(this);
        cartDelayTimer = setTimeout(function(){ that.removeClass("hover")}, 1000);
    })

    // efeito de adicionar produto ao carrinho
   /* $(".detalhes, .product-mini .btn-add").click(function(e) {*/
      $(".product-mini .btn-add").click(function(e) {
        var miniCart = $("#carrinho, .mini-carrinho");
        miniCart.addClass("hover");

        var product = miniCart.find(".products li.added");

        product.slideDown().promise().done(function() {
            product.addClass("clear");
        });

        setTimeout(function() { miniCart.removeClass("hover") }, 3000);

        e.preventDefault();
        return false;
    });

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

    // sliders
    $('.big-slider').each(function(){
        //console.log(equipamento_touch);
        var slider = $(this).find('.bxslider').bxSlider({
            controls: false,
            pager: true,
            auto: true,
            maxSlides: 1,
            infiniteLoop: equipamento_touch,
            touchEnabled: equipamento_touch
        });
    });

    $('.content-slider').each(function(){
        var slider = $(this).bxSlider({
            controls: false,
            pager: true,
            auto: true,
            maxSlides: 1,
            infiniteLoop: equipamento_touch,
            touchEnabled: equipamento_touch
        });
    });

    window.globalSlidersObjects = new Array();
    var productSliderUniqueId = 1;
    var wrapperHasClass = new Array();
    $('.product-slider').each(function(){
        var that = $(this);
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
            infiniteLoop: equipamento_touch,
            slideWidth: slideWidth,
            responsive: true,
            slideMargin: smargin,
            useCSS: false,
            touchEnabled: equipamento_touch,
            oneToOneTouch: false
        });

        // Replica o objeto na array global globalSlidersObjects com a ID da div wrapper
        productInfiniteSliderId = $(this).parents('.product-infinite-wrapper').attr("id");
        productInfiniteSliderId = (productInfiniteSliderId) ? productInfiniteSliderId : 'product-slider-'+productSliderUniqueId;
        if(productInfiniteSliderId !== undefined) {
            window.globalSlidersObjects.push(productInfiniteSliderId);
            window.globalSlidersObjects[productInfiniteSliderId] = slider;
            $(this).attr("data-slider-unique-id", productInfiniteSliderId);
            var sliderID = $(this).data('slider-unique-id');
        }

        wrapperHasClass.push(sliderID);
        wrapperHasClass[sliderID] = { 
            noAfter : $(this).parents(".product-infinite-wrapper").hasClass("no-after"),
            noBefore :  $(this).parents(".product-infinite-wrapper").hasClass("no-before")
        };

        // esconder efeito gradiente das pontas
        $(this).parents(".product-infinite-wrapper").mousemove(function(e) {
            var thisWidth = $(this).width();
            var mouseX = e.pageX-($(this).offset().left);

            if(!wrapperHasClass[that.data("slider-unique-id")].noAfter) {
                if(mouseX >= (thisWidth-slideWidth)) {
                    $(this).addClass("no-after");
                } else {
                    $(this).removeClass("no-after");
                }
            }

            if(!wrapperHasClass[that.data("slider-unique-id")].noBefore) {
                if(mouseX <= slideWidth) {
                    $(this).addClass("no-before");
                } else {
                    $(this).removeClass("no-before");
                }
            }
        });

        $(window).resize(function() { slider.reloadSlider(); }) // fix

        productSliderUniqueId++;
    });

    $('.cores .bxslider-cores, #bar-secondary .cores ul, #section-secondary .cores ul, .product .info .cores ul,.tamanhos .bxslider-tamanhos, #bar-secondary .tamanhos ul, #section-secondary .tamanhos ul, .product .info .tamanhos ul').each(function(){
       
    	var sWidth = 30;
        var sMargin = 2;
        var sMinSlides = ($(this).data("min-slides")) ? $(this).data("min-slides") : 3;
        if($(this).parents(".cores")[0]) {
            sWidth = 30;
        } 
        else if($(this).parents(".tamanhos")[0]) {
            $(this).find('li').each(function() {
                sWidth = $(this).width() - 1;
            })
        }
        $(this).find('li').each(function() {
            $(this).css("width", sWidth);
        });
        var slider = $(this).bxSlider({
            slideWidth: sWidth,
            maxSlides: 9,
            minSlides: sMinSlides,
            controls: true,
            pager: false,
            hideControlOnEnd: false,
            auto: false,
            slideMargin: sMargin,
            infiniteLoop: false,
            touchEnabled: equipamento_touch
        });

        var bxWrapper = slider.parent().parent();
        var prevBtn = bxWrapper.find('.bx-controls-direction a.bx-prev');
        if(prevBtn.hasClass('disabled')) {
            slider.parent().parent().addClass('only-one');
        }
    });

    // guias
    $('.tabs').each(function(){
        var tcon = $(this).find('.tabs-content');
        tcon.find('.tab').hide();
        tcon.find('.tab').eq(0).show();

        $(this).find('.tabs-header').find('li').find('a').click(function(e){
            e.preventDefault();

            var index = $(this).parent().index();
            tcon.find('.tab').hide();
            tcon.find('.tab').eq(index).show();

            $(this).parent().parent().find('li').removeClass('active');
            $(this).parent().addClass('active');
        });
    });

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
        //infiniteProductScroll(); // no final do arquivo
    });

    //ícone de carregamento do infinite ajax scroll
    //$('.infinite-product-scroll').append('<div class="loading-icon"><i class="fa fa-spinner fa-spin"></i></div>');

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
            if(!responsiveCart[0]) {
                $(this).parent().append($('#carrinho .mini-carrinho').clone().addClass('responsive'));
            }
            $('.mini-carrinho.responsive').toggleClass('active');
        }
    });

    //desativa ícones de actions ao scroll/blur
    var deactActionsScrollBlur = '#search .actions li.hide-on-desktop i, #search .actions .input-group';
    $(window).scroll(function() {
        $(deactActionsScrollBlur).not(".fa-shopping-cart").removeClass('active');
    });
    $("#search > .container > .actions > ul > li").focusout(function() {
        $(deactActionsScrollBlur).not(".fa-shopping-cart").removeClass('active');
    });

    //autocomplete da busca
    $('#search #palavra, #mini-header #palavra-mini-header').blur(function(){
        $(this).parent().find('.search-autocomplete-results').hide();
    });
    
    $('#search #palavra, #mini-header #palavra-mini-header').parents("form").submit(function(e) {
        var inputSearch = $(this).find("input[type=search]");
        if(inputSearch.val() === inputSearch.attr("data-placefocus") || inputSearch.val().length < 1) {
            e.preventDefault();
        }
    });

    // estrelas de avaliação
    $(".stars .star").hover(function() {
        $(".stars .star").removeClass("active");
        $(this).prevAll().addClass('active');
        $(this).addClass('active');
        // define valor do input
        $(this).parent().find("input").val($(this).prevAll().length + 1);
    });

    // relacionados à vídeos
    $("#video .gallery.thumbs a").click(function(e) {
        e.preventDefault();
        if(!$(this).hasClass("active")) {
            var video_url = $(this).attr("href");
            var video_id = getVideoId(video_url);
            var embed_video_url = "//www.youtube.com/embed/"+video_id;

            $(this).parent().find("a").removeClass("active");
            $(this).addClass("active");
            $(this).parents("#video").find("iframe").stop(1,1).fadeOut("slow").promise().done(function() {
                $(this).attr("src", embed_video_url).load(function() {
                    $(this).stop(1,1).fadeIn("slow");
                });
            });
        }
    });


    // seleciona bandeira de cartão baseado no número do cartão de crédito
    $(".form-cc").keyup(function() {
        var cardFlagsWrapper = ($(this).parents(".guia-cartao")[0]) ?
            $(this).parents('.guia-cartao').find(".card-flags") :
            $(this).parents('section').find(".card-flags");
        var cardFlags = cardFlagsWrapper.find("ul li");
        cardFlags.addClass("disabled");
        var cc = $(this).val();
        var flag = getCreditCardLabel(cc);
        var liFlag = "li."+flag;

        if($(this).val().length > 0) {
            if(flag !== "") {
                cardFlagsWrapper.find('input[type=radio][value='+flag+']').prop('checked', true);
                cardFlags.parent().find(liFlag).removeClass("disabled");
            } else {
                cardFlagsWrapper.find('input[type=radio]').prop('checked', false);
                cardFlags.addClass("disabled");
            }
            
        } else {
            cardFlagsWrapper.find('input[type=radio]').prop('checked', false);
            cardFlags.addClass("disabled");
        }
    });

    // FIXES

    //fix de hover em mobile 
    $('#top-navigation .utils .cadastre-se > a').click(function(e) {
        e.preventDefault;
        $(this).parent().toggleClass('hovered');
    });
    $('.product, .product-recomenda').click(function(e) {
        e.preventDefault;
        $(this).toggleClass('hovered');
    });
	
	$('.cadastre-se.logged-in > a').click(function(){
    	var ww = $(window).width();
    	if(ww < '1190'){
    		return false;
    	}
    });

    var quant_mcart = $('#carrinho .bag .count').text();
    if(quant_mcart != 0)
        $('.fa.fa-fw.fa-shopping-cart').append('<span>'+quant_mcart+'</span>');

});

// FUNÇÕES

window.infiniteCount = 0;


// VALIDATION

function formValidation() {
    // desabilita validação nativa de formulário
    $("form").attr("novalidate", "");

    $("form button, form input[type=submit]").not("button[type=reset]").click(function(e) {
        // var formSection = $(this).parents('section').first();
        var formSection = $(this).closest('section, form, div');
        if(!formSection.find("input")[0]) {
            formSection = formSection.closest('form');
        }
        var hasError = new Array();


        formSection.find("input:not([type=radio],[type=checkbox],[type=search],[type=submit],[type=hidden]), textarea").not(".novalidate").each(function(i) {
            // Validação de campos vazios
            if($(this).val() === "" || $(this).val() === undefined || $(this).val() === $(this).data("placefocus") || $(this).val() === $(this).attr("placeholder")) {
                validationErrorStyle($(this), '', i);
                hasError = [i];
            } else {
                hasError.splice(i);
                var hasSpecialChar = $(this).hasClass('has-special-char');

                // Validação de campos de e-mail
                if(($(this).attr("type") == "email" || $(this).hasClass("required-email")) && !validateEmail($(this).val())) {
                    validationErrorStyle($(this), "Email inválido! Por favor, verifique se digitou o e-mail corretamente.", i);
                    hasError = [i];
                    return;
                } else {
                    hasError.splice(i);
                }

                // Campo de senha deve conter no mínimo 8 caracteres e no máximo 16
                if($(this).attr("type") == "password" && ($(this).val().length < 8 || $(this).val().length > 16)) {
                    validationErrorStyle($(this), "A senha deve conter no mínimo 8 caracteres e no máximo 16.", i);
                    hasError = [i];
                    return;
                } else {
                    hasError.splice(i);
                }

                // Validação de campos que devem conter apenas números
                if(($(this).attr("type") == "number" || $(this).hasClass("only-number")) && !validateOnlyNumber($(this).val(), hasSpecialChar)) {
                    validationErrorStyle($(this), "Apenas números são permitidos.", i);
                    hasError = [i];
                    return;
                } else {
                    hasError.splice(i);
                }

                // Validação de campos que devem conter apenas letras
                if($(this).hasClass("only-char") && !validateOnlyChar($(this).val(), hasSpecialChar)) {
                    validationErrorStyle($(this), "Apenas letras são permitidas.", i);
                    hasError = [i];
                    return;
                } else {
                    hasError.splice(i);
                }

                // Validação de CPF
                if($(this).hasClass("form-cpf") && ($(this).val().length != 14)) {
                    validationErrorStyle($(this), "Número de CPF inválido.", i);
                    hasError = [i];
                    return;
                } else {
                    hasError.splice(i);
                }

                // Validação de CNPJ
                if($(this).hasClass("form-cnpj") && ($(this).val().length != 18)) {
                    validationErrorStyle($(this), "Número de CNPJ inválido.", i);
                    hasError = [i];
                    return;
                } else {
                    hasError.splice(i);
                }

                // Validação de CEP
                if($(this).hasClass("form-cep") && ($(this).val().length != 9)) {
                    validationErrorStyle($(this), "CEP inválido.", i);
                    hasError = [i];
                    return;
                } else {
                    hasError.splice(i);
                }

                // Validação de Telefones
                if($(this).hasClass("form-phone") && ($(this).val().length < 14 || $(this).val().length > 15)) {
                    validationErrorStyle($(this), "Número de telefone inválido.", i);
                    hasError = [i];
                    return;
                } else {
                    hasError.splice(i);
                }

                // Validação de cartão de crédito
                if($(this).hasClass("form-cc") && ($(this).val().length != $(this).data("cc-length") || !getCreditCardLabel($(this).val()) ) ) {
                    validationErrorStyle($(this), "Número de cartão inválido.", i);
                    hasError = [i];
                    return;
                } else {
                    hasError.splice(i);
                }

                // Validação de datas
                var date = new Date();
                // DIA
                if( $(this).hasClass("form-data-dia") && ($(this).val().length > 2 || parseInt($(this).val()) < 1 || parseInt($(this).val()) > 31) ) {
                        validationErrorStyle($(this), "Dia inválido.", i);
                        hasError = [i];
                        return;
                } else {
                    hasError.splice(i);
                }
                // MÊS
                if( $(this).hasClass("form-data-mes") && ($(this).val().length > 2 || parseInt($(this).val()) < 1 || parseInt($(this).val()) > 12 ) ) {
                        validationErrorStyle($(this), "Mês inválido.", i);
                        hasError = [i];
                        return;
                } else {
                    hasError.splice(i);
                }
                // ANO
                if( $(this).hasClass("form-data-ano") && ($(this).val().length > 4 || $(this).val().length < 2 || parseInt($(this).val()) < (date.getFullYear()-110) || parseInt($(this).val()) > (date.getFullYear()+18)) ) {
                        validationErrorStyle($(this), "Ano inválido.", i);
                        hasError = [i];
                        return;
                } else {
                    hasError.splice(i);
                }
            }

            if($.inArray(i, hasError) == -1) {
                validationErrorStyleClear($(this), i);
            }
        })

        // radio not checked
        var radioUnchecked;
        $(this).find('input[type=radio].required').each(function() {
            if($(this).is(':checked')) {
                radioUnchecked = false;
            }
        });
        if(radioUnchecked) {
            hasError = true;
        }

        if(hasError.length > 0) {
            e.preventDefault();
            $(this).find(".validation-wrapper input").first().focus();
        }
    });
}

function validateEmail(email) {
    var emailReg = new RegExp("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    return emailReg.test(email);
}

function validateOnlyNumber(value, specialChars) {
    var specialChars = (!specialChars) ? false : specialChars;
    var regexp = (specialChars) ? "^[0-9-.,/\\()\\s]+$" : "^[0-9]+$";
    var onlyNumberReg = new RegExp(regexp);
    return onlyNumberReg.test(value);
}

function validateOnlyChar(value, specialChars) {
    var specialChars = (!specialChars) ? false : specialChars;
    var regexp = (specialChars) ? "^[a-zA-Z-.,/\\()\\s]*$" : "^[a-zA-Z]*$";
    var onlyNumberReg = new RegExp(regexp);
    return onlyNumberReg.test(value);
}

function validationErrorStyle(el, errorMsg, index) {
    var warningDesc;
    errorMsg=(errorMsg==="")?'É necessário preencher este campo.':errorMsg;

    el.addClass('validation-warning');

    var validationWrapper = el.parents(".validation-wrapper");
    if(!validationWrapper[0]) {
        var inputW = ( 100 * parseFloat(el.outerWidth()) / parseFloat(el.parent().outerWidth()) ) + '%';

        var inputM = [
            el.css("margin-top"),
            el.css("margin-right"),
            el.css("margin-bottom"),
            el.css("margin-left")
        ];

        el.wrap('<div class="validation-wrapper"></div>');
        validationWrapper = el.parents(".validation-wrapper");
        validationWrapper.css({
                "width": inputW,
                "margin": inputM.join(' ')
            })
    }

    warningDesc = validationWrapper.find('p.validation-warning-desc.input-'+index+' span');
    if(!warningDesc[0]) {
        validationWrapper.append('<p class="validation-warning-desc input-'+index+'"><span></span></p>');
        warningDesc = validationWrapper.find('p.validation-warning-desc.input-'+index+' span');
    }

    warningDesc.html(errorMsg);
}

function validationErrorStyleClear(el, index) {
    el.removeClass('validation-warning');
    el.parent().find('p.validation-warning-desc.input-'+index).remove();
}

// Digitar apenas números em campos type=number
$(document).ready(function() {
    $('input[type=number], .only-number').keydown(function(event) {
        // Permitir caracteres especiais + setas
        if (event.keyCode == 46 || event.keyCode == 8 || event.keyCode == 9 
            || event.keyCode == 27 || event.keyCode == 13 
            || (event.keyCode == 65 && event.ctrlKey === true) 
            || (event.keyCode >= 35 && event.keyCode <= 39)){
                return;
        } else {
            // Se não é um número, não fazer nada
            if (event.shiftKey || (event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105 )) {
                event.preventDefault();
                return false;
            }   
        }
    });
})

// END VALIDATION

// BEGIN FORM MASKS

$(document).ready(function(){
  // CPF
  $('.form-cpf').mask('000.000.000-00');
  // CNPJ
  $('.form-cnpj').mask('00.000.000/0000-00');
  // CEP
  $('.form-cep').mask('00000-000');
  // Moeda
  $('.form-currency').mask('000.000.000.000.000,00', {reverse: true});
  // Número de cartão
  var ccOptions =  {onKeyPress: function(cc, e, field, options){
    var ccLabel = getCreditCardLabel(cc);
    var ccMaskLength = getCreditCardNumberLength(ccLabel);

    var mask = '';
    for(i=0;i<ccMaskLength;i++) {//  field.mask(mask, ccOptions);
    field.data("cc-length", ccMaskLength);
    }
  }};
  $('.form-cc').mask('0000000000000000', ccOptions);
  // Telefone
        var phoneMask = function (val) {
        return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
        },
        phoneOptions = {onKeyPress: function(val, e, field, options) {
        field.mask(phoneMask.apply({}, arguments), options);
        }};
  $('.form-phone').mask(phoneMask, phoneOptions);
  
});

$(document).ready(function(){
//	$('.info .cores ul li:first-child').each(function(){$(this).click()});
//	$('.info .tamanhos ul li:first-child').each(function(){$(this).click()});
//	$('.info .addToCartForm #productCodePost').each(function(){
//		
//		var tempVal = $(this).val();
//		var tempThis = tempVal.split('-');
//		
//		$(this).val(tempThis[0]);
//	});
});

$(window).load(function(){
	
	if ($('#main-wrapper div').hasClass('alert')){
		var mensagem = $('div.alert').text();
		showToaster(mensagem);
	};
	
	//salva vitrine temporario
	$('.product.productDetailsHolder > .info > .cores > ').find('li').css('cursor','default').click(function(){ return false; });
//	$('.product.productDetailsHolder > .info > .tamanhos > ').find('li').css('cursor','default').click(function(){ return false; });
//	$('.detalhes > a').each(function(){
//		var link = $(this).attr('href');
//		//$(this).parent().parent().find('form').append('<a href="'+link+'" class="addToCartButton add">ver detalhes</a>').find('input[type=submit]').css({'display':'none'});
//		//$(this).parent().parent().find('input[type=submit]').css('display','none');
//		/*$(this).parent().parent().find('input[type=submit]').click(function(){
//			//console.log(link);
//			document.location.href = link;
//			return false;
//		});*/
//		//$(this).css('display','none');
//	});
	
});

// END FORM MASKS

function jumptToNextInput(element) {
    $(element).find("input").keyup(function() {
        var maxlength = $(this).attr("maxlength");
        if($(this).val().length >= maxlength) {
            $(this).next("input").focus();
        }
    });
}

function getCreditCardLabel(cardNumber, formatted){
    formatted = (!formatted) ? false : true;

    var regexHering = /^432032(?:[0-9]{10})?/;
    var regexElo = /^(636368|438935|504175|451416|636297)(?:[0-9]{10})?|(5067|4576|4011)(?:[0-9]{12})?/;
    var regexHiper = /^(38|60)(([0-9]{11})|([0-9]{14})|([0-9]{17}))?/;
    var regexVisa = /^4([0-9]{12})?(?:[0-9]{3})?/;
    var regexMaster = /^5([1-5])?([0-9]{14})?/;
    var regexAmex = /^3[47]([0-9]{13})?/;
    var regexDiners = /^3(?:0[0-5]|[68][0-9])([0-9]{11})?/;

    if(regexHering.test(cardNumber)){ return (formatted) ? 'Hering' : 'hering'; }
    if(regexElo.test(cardNumber)){ return (formatted) ? "Elo" : 'elo'; }
    if(regexHiper.test(cardNumber)){ return (formatted) ? "Hipercard" : 'hipercard'; } 
    if(regexVisa.test(cardNumber)){ return (formatted) ? "Visa" : 'visa'; } 
    if(regexMaster.test(cardNumber)){ return (formatted) ? "MasterCard" : 'mastercard'; } 
    if(regexAmex.test(cardNumber)){ return (formatted) ? "American Express" : 'amex'; } 
    if(regexDiners.test(cardNumber)){ return (formatted) ? "Dinersclub" : 'dinersclub'; } 

    return null;
}

function getCreditCardNumberLength(cardLabel) {
    switch(cardLabel) {
        case "amex":
            return 15; break;
        case "visa":
        case "mastercard": 
        case "dinersclub":
        case "elo":
        case "hering":
            return 16; break;
        case "hipercard":
            return 19; break;
        default:
            return 16; break;
    }
}

function getInstagramPhotos() {
    // script de requisição de fotos do Instagram
    var userID = "560879016";
    var accessToken = "13794908.5b9e1e6.4dcb21a1681a46cfb918650b3593b456";
    jQuery.ajax({
        type: "GET",
        dataType: "jsonp",
        cache: false,
        url: "https://api.instagram.com/v1/users/"+userID+"/media/recent/?access_token="+accessToken,
        success: function(data) {
            var instaList = $(".instagram-list li");
            for (var i = 0; i < instaList.length; i++) {
                var selectedItem = instaList.eq(i);

                var itemAnchor = selectedItem.find('a');
                var itemImage = selectedItem.find('img');
                if(!itemAnchor[0]) {
                    selectedItem.append('<a href="#" title="" target="_blank"></a>')
                    itemAnchor = selectedItem.find('a');
                }
                if(!itemImage[0]) {
                    selectedItem.find('a').append('<img src="" alt="">')
                    itemImage = selectedItem.find('img');
                }

                var dataCaption = (data.data[i].caption)?data.data[i].caption.text:"";
                
                itemAnchor.attr({
                    "href": data.data[i].link,
                    "title": dataCaption
                });
                itemImage.attr({
                    "src": data.data[i].images.low_resolution.url,
                    "width": selectedItem.css("width"),
                    "height": selectedItem.css("height"),
                    "alt": dataCaption
                });
            }
        }
    });
}

function getVideoId(url){
    if(url.indexOf('?') != -1 ) {
        var query = decodeURI(url).split('?')[1];
        var params = query.split('&');
        for(var i=0,l = params.length;i<l;i++)
            if(params[i].indexOf('v=') === 0)
                return params[i].replace('v=','');
    } else if (url.indexOf('youtu.be') != -1) {
        return decodeURI(url).split('youtu.be/')[1];
    }
    return null;
}

//function getSizeProductPerColor(data)
//{
//	var id = $(data).attr('id');
//	
//	$(data).parent().parent().find("a").removeClass("active");
//	var li = $(data).parent();
//
//	if(!li.hasClass('tabela') && !$(this).hasClass('out-of-stock')) {
//		$(data).children('a').toggleClass('active');
//	}
//	
//	$(data).parent().parent().parent().parent().parent().parent().find('input[type=hidden][class=codeProduct]').val($(data).attr('id'));
//	$(data).parent().parent().parent().parent().parent().parent().find('#productCode').val($(data).attr('id'));
//	$(data).parent().parent().parent().parent().parent().parent().find('#productCodePost').val($(data).attr('id'));
//	
//}

var toasterActive;
function showToaster(msg) {
    var toaster = $("#toaster");
    if(!toaster[0]) {
        $("body").prepend('<div id="toaster"><div class="content"></div></div>');
        toaster = $("#toaster");
    }
    clearTimeout(toasterActive);
    toaster.find(".content").text(msg);
    toaster.addClass("active").promise().done(function() {
        toasterActive = setTimeout(function() { toaster.removeClass("active") }, 4000);
    });
}

//imgMaps home
;(function(a){a.fn.rwdImageMaps=function(){var c=this;var b=function(){c.each(function(){if(typeof(a(this).attr("usemap"))=="undefined"){return}var e=this,d=a(e);a("<img />").load(function(){var g="width",m="height",n=d.attr(g),j=d.attr(m);if(!n||!j){var o=new Image();o.src=d.attr("src");if(!n){n=o.width}if(!j){j=o.height}}var f=d.width()/100,k=d.height()/100,i=d.attr("usemap").replace("#",""),l="coords";a('map[name="'+i+'"]').find("area").each(function(){var r=a(this);if(!r.data(l)){r.data(l,r.attr(l))}var q=r.data(l).split(","),p=new Array(q.length);for(var h=0;h<p.length;++h){if(h%2===0){p[h]=parseInt(((q[h]/n)*100)*f)}else{p[h]=parseInt(((q[h]/j)*100)*k)}}r.attr(l,p.toString())})}).attr("src",d.attr("src"))})};a(window).resize(b).trigger("resize");return this}})(jQuery);

$(document).ready(function(e) {
    $('img[usemap]').rwdImageMaps();
});