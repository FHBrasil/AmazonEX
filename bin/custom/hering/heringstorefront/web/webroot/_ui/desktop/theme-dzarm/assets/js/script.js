$(document).ready(function(){

	$('#modal-order-resume').css('display','none');
	
	$('.category dl').each(function(){
		var contentHtml = $(this).html().replace(/^\s+|\s+$/g,""); 
		if(contentHtml == 'codigoCor')
		{
			$(this).parent().css('display','none');
		}

	});
	
	$(".tabela-medidas").remove();
	
	$(".addToCartButton").css("display","block");
	
    // Apenas numeros nos inputs tipo number
   	$("input[type=number]").keypress(function (e) {
	    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
        	return false;
	    }
   	});
   	//MENU RESPONSIVO *ISSO NÃO FUNCIONA*
//   	$("#menu-toggle").click(function(){
//     	  if($(this).hasClass("close-menu")){
//     	    $(this).removeClass("close-menu");
//          $("#main-menu").css("margin-left", "-235px");
//     	   $("#main-menu").hide();
//        }else{
//     	    $(this).addClass("close-menu");
//     	    $("#main-menu").show();
//          $("#main-menu").css("margin-left", "-20px");
//     	  }
//     	});
     	

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
        var slider = $(this).find('.bxslider').bxSlider({
            controls: false,
            pager: true,
            auto: true,
            maxSlides: 1,
            infiniteLoop: true,
            touchEnabled: true
        });
    });

    window.globalSlidersObjects = new Array();
    $('.product-slider').each(function(){
        //var nSlides = $(this).hasClass('slide-2')?2:3;
        var pager = $(this).hasClass('custom-pager');
        var hasblueStripe = $(this).hasClass('has-blue-stripe');
        var ms = '';

        var smargin = $(this).attr('data-slide-margin')?parseInt($(this).attr('data-slide-margin'), 10):1;
        var slideWidth = ($(this).hasClass('bigger-image')) ? 299 : 237;
        
        if($("body").attr("class") == "HOMEPAGE") slideWidth = 299; 
        
        var slideWidth = 299;
        var infinite = !$(this).is('#looks-dicas-sliders .product-slider');
        var slider = $(this).bxSlider({
            controls: false,
            pager: true,
            auto: false,
            maxSlides: 4,
            minSlides: 1,
            infiniteLoop: infinite,
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
            window.globalSlidersObjects.push(productInfiniteSliderId);
            window.globalSlidersObjects[productInfiniteSliderId] = slider;
        }
    });

    initCoresTamanhosSliders();

    // guias
    $('.tabs').each(function(){
        var tcon = $(this).find('.tabs-content');
        tcon.find('.tab').hide();

        $('.tabs-header').find('li').find('a').click(function(e){
            e.preventDefault();

            var index = $(this).parent().index();
            tcon.find('.tab').hide();
            tcon.find('.tab').eq(index).show();

            $(this).parent().parent().find('li').removeClass('active');
            $(this).parent().addClass('active');
        });

        tcon.find('.tab').eq(0).show();
    });

    // voltar ao topo
    $(window).scroll(function() {
        //voltar ao topo
        if($(window).width() > '880'){
            if ($(this).scrollTop() > 60) {
                $("#back-top").stop(0,1).fadeIn();
                $("#mini-header .content").slideDown('medium');
                $("#mini-header #carrinho").addClass('scrolling');
            } else {
                $("#back-top").stop(0,1).fadeOut();
                $("#mini-header .content").slideUp('medium');
                $("#mini-header #carrinho").removeClass('scrolling');
            }
        }

        // carrega mais produtos na listagem de produtos ao rolar a página
//        if ($('.infinite-product-scroll').size()){
//            infiniteProductScroll(); // no final do arquivo
//        }
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

//    $('#search #palavra, #mini-header #palavra-mini-header').keyup(function(){
//        var val = $(this).val();
//
//        var auto = $(this).parent().find('.search-autocomplete-results');
//        if(!auto[0]){
//            $(this).parent().append('<div class="search-autocomplete-results show-on-desktop"></div>');
//            auto = $(this).parent().find('.search-autocomplete-results');
//        }
//
//        if(val.length > 0) {
//            //resultados normais
//            var results = '<div><ul class="keywords-suggestions">';
//                //loop de resultados
//                results += '<li><a href="#">'+val+' manga longa</a></li>';
//                results += '<li><a href="#">'+val+' manchada</a></li>';
//                results += '<li><a href="#">'+val+'</a></li>';
//            results += '</ul></div>';
//
//            //resultados de produtos
//            results += '<div><ul class="products-suggestions">';
//                //loop de resultados
//                results += '<li>';
//                    results += '<a href="#"><img src="http://placehold.it/76x92/1cb5ac/ffffff" alt="Camiseta manga longa masculina em malha flamê"></a>';
//                    results += '<div class="info">';
//                        results += '<h2><a href="#">Camiseta manga longa masculina em malha flamê</a></h2>';
//                        results += '<div class="cores">';
//                            results += '<ul>';
//                                results += '<li class="roxo"></li>';
//                                results += '<li class="azul"></li>';
//                                results += '<li class="vermelho"></li>';
//                                results += '<li class="laranja"></li>';
//                                results += '<li class="rosa"></li>';
//                            results += '</ul>';
//                        results += '</div>';
//                        results += '<div class="tamanhos">';
//                            results += '<ul>';
//                                results += '<li><a href="#">PP</a></li>';
//                                results += '<li><a href="#">P</a></li>';
//                                results += '<li><a href="#">M</a></li>';
//                                results += '<li><a href="#">G</a></li>';
//                                results += '<li><a href="#">GG</a></li>';
//                            results += '</ul>';
//                        results += '</div>';
//                        results += '<div class="precos">';
//                            results += '<s>de: R$79,90</s>';
//                            results += '<strong>por: R$49,90</strong>';
//                        results += '</div>';
//                    results += '</div>';
//                results += '</li>';
//                results += '<li>';
//                    results += '<a href="#"><img src="http://placehold.it/76x92/1cb5ac/ffffff" alt="Camiseta manga longa masculina em malha flamê"></a>';
//                    results += '<div class="info">';
//                        results += '<h2><a href="#">Camiseta manga longa masculina em malha flamê</a></h2>';
//                        results += '<div class="cores">';
//                            results += '<ul>';
//                                results += '<li class="roxo"></li>';
//                                results += '<li class="azul"></li>';
//                                results += '<li class="vermelho"></li>';
//                                results += '<li class="laranja"></li>';
//                                results += '<li class="rosa"></li>';
//                            results += '</ul>';
//                        results += '</div>';
//                        results += '<div class="tamanhos">';
//                            results += '<ul>';
//                                results += '<li><a href="#">PP</a></li>';
//                                results += '<li><a href="#">P</a></li>';
//                                results += '<li><a href="#">M</a></li>';
//                                results += '<li><a href="#">G</a></li>';
//                                results += '<li><a href="#">GG</a></li>';
//                            results += '</ul>';
//                        results += '</div>';
//                        results += '<div class="precos">';
//                            results += '<s>de: R$79,90</s>';
//                            results += '<strong>por: R$49,90</strong>';
//                        results += '</div>';
//                    results += '</div>';
//                results += '</li>';
//                // /loop
//            results += '</ul></div>';
//            
//            auto.html(results);
//            auto.show();
//        } else {
//            auto.hide();
//        }
//    });

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

    $("#main-menu li").hover(function(e) {
        repositionDesktopSubmenu($(this));
    });
    
    // Tooltip Quickview
    var tooltipContent = $('.product-tooltip-content'),
        tooltipClose = $('.tooltip-close'),
        tooltipTrigger = $('.product-tooltip a');

    tooltipTrigger.on('click', function(e){
        tooltipContent.fadeIn('fast');
        e.preventDefault();
    });

    tooltipClose.on('click', function(e){
        tooltipContent.fadeOut('fast');
        e.preventDefault();
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
    
//	$('.info .cores ul li:first-child').each(function(){$(this).click()});
//	$('.info .tamanhos ul li:first-child').each(function(){$(this).click()});
//	$('.info .addToCartForm #productCodePost').each(function(){
//		
//		var tempVal = $(this).val();
//		var tempThis = tempVal.split('-');
//		
//		$(this).val(tempThis[0]);
//	});

    //submenu ipad abrir ja no hover
    if($(window).width() < '880'){
        $("#main-menu li").each(function(){
            $(this).hover(function(){ $(this).find("a").click(); });
        });
    }

    $('.bx-controls').css('display','table-row');

    var quant_mcart = $('#carrinho .bag .count').text();
    if(quant_mcart != 0)
        $('.fa.fa-fw.fa-shopping-cart').append('<span>'+quant_mcart+'</span>');
	
});

$(window).load(function(){
	
	if ($('#main-wrapper div').hasClass('alert')){
		var mensagem = $('div.alert').text();
		showToaster(mensagem);
	};
	
	//salva vitrine temporario
	$('.product.productDetailsHolder > .info > .cores > ').find('li').css('cursor','default').click(function(){ return false; });
	$('.product.productDetailsHolder > .info > .tamanhos > ').find('li').css('cursor','default').click(function(){ return false; });
//	$('.detalhes > a').each(function(){
//		var link = $(this).attr('href');
//		$(this).parent().parent().find('form').append('<a href="'+link+'" class="addToCartButton add">ver detalhes</a>').find('input[type=submit]').css({'display':'none'});
//		$(this).parent().parent().find('input[type=submit]').css('display','none');
//		/*$(this).parent().parent().find('input[type=submit]').click(function(){
//			//console.log(link);
//			document.location.href = link;
//			return false;
//		});*/
//		$(this).css('display','none');
//	});
	
	
	$('.product, .product-recomenda').removeClass('hovered');
	
});

// FUNÇÕES

window.infiniteCount = 0;

//function infiniteProductScroll() {
//    // scroll infinito de produtos
//    var infiniteDivWrapper = $('.infinite-product-scroll');
//    var infiniteContent = infiniteDivWrapper.find('.infinite-scroll-content');
//    var loadingIcon = infiniteDivWrapper.find('.loading-icon');
//    var infiniteDivPosition = infiniteDivWrapper.height() - $(window).height();
//    var infiniteCountLimit = ($(window).width() > 1199)?3:2;
//    var filtersBottom = $('.page .right .filters.center');
//    if ( $(window).scrollTop() >= infiniteDivPosition &&  //trigger only if at the bottom
//            window.infiniteCount < infiniteCountLimit &&  //trigger only a few times
//            (!window.inifiniteScrollPromise || (window.infiniteScrollPromise && window.infiniteScrollPromise.state() != "pending")) ){ //trigger only if NOT waiting another page to load
//        window.infiniScrollPromise = infiniteScrollLoadProducts();
//        window.infiniScrollPromise.done(function(content) {
//            
//            var $content = $(content);
//            infiniteContent.append($content);
//            initCoresTamanhosSliders($content);
//            loadingIcon.hide();
//            
//        });
//        loadingIcon.fadeIn('fast');
//
//        window.infiniteCount++;
//    } else {
//        filtersBottom.show();
//    }
//}


//*****************************************
// ATENÇÃO!! ATENÇÃO!! ATENÇÃO!! ATENÇÃO!! 
// ATENÇÃO!! ATENÇÃO!! ATENÇÃO!! ATENÇÃO!! 
// Este método está sendo sobrescrito nas 
// páginas onde o infinite scroll está
// sendo utilizado. No hybris, buscar 
// método mais elegante e implementá-lo uma 
// vez só...
// html dos ítens de produtos variam de 
// acordo com a página
//*****************************************
function infiniteScrollLoadProducts() {
    // AJAX BASE
    // 9 produtos por página
    // Contagem de carregamento: 3 para desktop e 2 para mobile
    /*
    return = $.ajax({
        url: "/wp-admin/admin-ajax.php",
        type:'POST',
        data: "action=infinite_scroll&page_no="+ pageNumber + '&loop_file=loop', 
    });
    */

    //apenas um alerta para as páginas que não estão implementando o método.
    //remover na implementação...
    // deixar apenas o return $.ajax acima.attr("class", "active
    var defer = $.Deferred();
    window.setTimeout(function(){
        alert('Método infiniteScrollLoadProducts não está implementado');
        defer.resolve('');
    }, 2000);
    return defer.promise();
}

function initCoresTamanhosSliders($context){
    var selector = '.cores .bxslider-cores, .tamanhos .bxslider-tamanhos, #bar-secondary .cores ul, #section-secondary .cores ul, .product .info .cores ul, .product .info .tamanhos ul';
    var $elements = ($context) ? $context.find(selector) : $(selector);
    $elements.each(function(){
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
}

function formValidation() {
    $("form button").click(function(e) {
        // var formSection = $(this).parents('section').first();
        var formSection = $(this).closest('section, form, div');
        if(!formSection.find("input")[0]) {
            formSection = formSection.closest('form');
        }
        var radioUnchecked;
        var errors = new Array();
        var warningDesc;

        function addErrorMsg(msg, itemIndex){
            $(this).addClass('validation-warning');
            warningDesc = $(this).parent().find('p.validation-warning-desc.input-'+itemIndex+' span');
            if(!warningDesc[0]) {
                var inputW = $(this).outerWidth();
                var inputM = [
                    $(this).css("margin-top"),
                    $(this).css("margin-right"),
                    $(this).css("margin-bottom"),
                    $(this).css("margin-left")
                ];
                $(this)
                    .wrap('<div class="validation-wrapper"></div>')
                    .parent()
                    .css({
                        "width": inputW,
                        "margin": inputM.join(' ')
                    })
                    .append('<p class="validation-warning-desc input-'+itemIndex+'"><span></span></p>');
                warningDesc = $(this).parent().find('p.validation-warning-desc.input-'+itemIndex+' span');
            }

            warningDesc.html(msg);
            errors = ['text'];
        };

        formSection.find("input:not([type=radio],[type=checkbox],[type=search])").each(function(itemIndex) {
            if($(this).val() === "" || $(this).val() === $(this).attr("data-placefocus")) {
                addErrorMsg.apply(this, ['É necessário preencher este campo', itemIndex]);
            
            } else if ( $(this).is('[type=email]') && !checkEmail($(this).val())){
                addErrorMsg.apply(this, ['É necessário informar um e-mail válido', itemIndex]);

            } else if ( $(this).is('[type=number]') && !checkNumbers(this)) {
                var min = $(this).attr('min');
                addErrorMsg.apply(this, ['É necessário informar um inteiro válido maior que '+ min, itemIndex] );

            } else {
                $(this).removeClass('validation-warning');
                $(this).parent().find('p.validation-warning-desc.input-'+itemIndex).remove();
            }
        })

        // radio not checked
        $('input[type=radio]').each(function() {
            if($(this).is(':checked')) {
                radioUnchecked = false;
            }
        });
        if(radioUnchecked) {
            // $(this).addClass('validation-warning');
            errors = ['radio'];
        } else {
           if(errors['radio']) {
                errors['radio'].splice();
            }
        }

        if(errors.length > 0) {
            e.preventDefault();
        }
    });
}

function jumptToNextInput(element) {
    $(element).find("input").keyup(function() {
        var maxlength = $(this).attr("maxlength");
        if($(this).val().length >= maxlength) {
            $(this).next("input").focus();
        }
    });
}

function checkEmail(emailAddress) {
  var sQtext = '[^\\x0d\\x22\\x5c\\x80-\\xff]';
  var sDtext = '[^\\x0d\\x5b-\\x5d\\x80-\\xff]';
  var sAtom = '[^\\x00-\\x20\\x22\\x28\\x29\\x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\xff]+';
  var sQuotedPair = '\\x5c[\\x00-\\x7f]';
  var sDomainLiteral = '\\x5b(' + sDtext + '|' + sQuotedPair + ')*\\x5d';
  var sQuotedString = '\\x22(' + sQtext + '|' + sQuotedPair + ')*\\x22';
  var sDomain_ref = sAtom;
  var sSubDomain = '(' + sDomain_ref + '|' + sDomainLiteral + ')';
  var sWord = '(' + sAtom + '|' + sQuotedString + ')';
  var sDomain = sSubDomain + '(\\x2e' + sSubDomain + ')*';
  var sLocalPart = sWord + '(\\x2e' + sWord + ')*';
  var sAddrSpec = sLocalPart + '\\x40' + sDomain; // complete RFC822 email address spec
  var sValidEmail = '^' + sAddrSpec + '$'; // as whole string

  var reValidEmail = new RegExp(sValidEmail);

  if (reValidEmail.test(emailAddress)) {
    return true;
  }

  return false;
}

function checkNumbers( element ){
    var min = window.parseInt( $(element).attr('min') );
    var val = window.parseInt( element.value );
    return (!window.isNaN( element.value ) && (val >= min));
}

function getInstagramPhotos() {
    // script de requisição de fotos do Instagram
    var userID = "185216157";
    var accessToken = "215480059.5b9e1e6.e81ec6b9fef043afb40c6f339e92de89";
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

function repositionDesktopSubmenu(el) {
    // if($(window).width() < 1199) {
        var submenu = el.children(".sub-menu");
        if(submenu[0]) {
            submenu.css("left", 0);
            var container = ($(window).width() > 980) ? el.parents(".container") : $(window);
            var submenuLeftPos = (container.hasClass("container")) ? submenu.offset().left - container.offset().left : submenu.offset().left;
            // console.log(submenuLeftPos);
            // verifica se o menu está passando dos limites do container
            var submenuOutbounds = ((container.width() - submenuLeftPos) < submenu.outerWidth())?true:false; 
            if(submenuOutbounds) {
                var leftPos = ((container.width() - submenuLeftPos) - submenu.outerWidth())*1.05;
                submenu.css("left", leftPos);
            }
        }
    // }
}

function getSizeProductPerColor(data)
{
	var id = $(data).attr('id');
	
	$(data).parent().parent().find("a").removeClass("active");
	var li = $(data).parent();

	if(!li.hasClass('tabela') && !$(this).hasClass('out-of-stock')) {
		$(data).children('a').toggleClass('active');
	}
	
	$(data).parent().parent().parent().parent().parent().parent().find('input[type=hidden][class=codeProduct]').val($(data).attr('id'));
	$(data).parent().parent().parent().parent().parent().parent().find('#productCode').val($(data).attr('id'));
	$(data).parent().parent().parent().parent().parent().parent().find('#productCodePost').val($(data).attr('id'));
	
}

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

    if($(window).width() < '880'){
    var quant_mcart = $('#carrinho .bag .count').text();
    if(quant_mcart != 0)
        $('.fa.fa-fw.fa-shopping-cart').append('<span>'+quant_mcart+'</span>');
    }
}

//habilita e desabilita menu conforme mobile
function desa_menu(){

	var tamanho = $(window).width();
	//console.log(tamanho);

	if(tamanho < 880){

		//console.log("mobile");
		$("#main-menu a").each(function(){
			$(this).off("click");
		});


	}else{

		//console.log("desktop");
		$("#main-menu a").each(function(){
			var link = $(this).attr("href");
			$(this).click(function(){
				window.location.href = link;
			});
		});
	}
};

$(window).load(function(){
	desa_menu();
});

$(window).resize(function(){
	desa_menu();
    
    if($(window).width() > '880'){
         ///location.reload();
    }
    if($(window).width() < '880'){
        $("#main-menu li").each(function(){
            $(this).hover(function(){ $(this).find("a").click(); });
        });
        $(".product-images ul li").css("display","block");
    }

});