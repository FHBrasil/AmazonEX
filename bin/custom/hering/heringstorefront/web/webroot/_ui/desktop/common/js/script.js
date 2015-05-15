//var colorClicks = 0;
//var loadClicks = 0;
//
//$("#resultsList").find(".product").each(function(k){ loadClicks++; });
//if( loadClicks == 0 ){ $("body").find(".product").each(function(k){ loadClicks++; }); }

$(document).ready(function(){
$('#modal-order-resume').css('display','none');
	
	$('.category dl').each(function(){
		var contentHtml = $(this).html().replace(/^\s+|\s+$/g,""); 
		if(contentHtml == 'codigoCor')
		{
			$(this).parent().css('display','none');
		}

	});
	
    // validação de formularios
	// ormValidation();
	
	//classe no banner voltar a ser responsivo
	$('.cms_disp-img_slot > .cmsimage').find('img').addClass('banner-department');
	
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
        var slideWidth = ($(this).hasClass('bigger-image')) ? 300 : 296;
        var slider = $(this).bxSlider({
            controls: false,
            pager: true,
            auto: false,
            maxSlides: 4,
            minSlides: 1,
            infiniteLoop: false,
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

    $('.cores .bxslider-cores, #bar-secondary .cores ul, #section-secondary .cores ul, .product .info .cores ul, .tamanhos .bxslider-cores').each(function(){
        var sWidth = 30;
        if($(this).parents(".cores")[0]) {
            sWidth = 30;
        } else if($(this).parents(".tamanhos")[0]) {
            $(this).find('li').each(function() {
                sWidth = ($(this).outerWidth() > sWidth) ? $(this).outerWidth() : sWidth;
            })
        }
        $(this).find('li').each(function() {
            $(this).css("width", sWidth);
        });
        var slider = $(this).bxSlider({
            slideWidth: sWidth,
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
//    $('.infinite-product-scroll').append('<div class="loading-icon"><i class="fa fa-spinner fa-spin"></i></div>');

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
            var responsiveCart = $('.mini-carrinho.responsive');
            if(!responsiveCart[0]) {
                $(this).parent().append($('#miniCartLayer').clone().find(".mini-carrinho").addClass('responsive'));
            }
            $('.mini-carrinho.responsive').toggleClass('active');
        }
    });

    //desativa ícones de actions ao scroll/blur
    /*var deactActionsScrollBlur = '.mini-carrinho.responsive, #search .actions li.hide-on-desktop i, #search .actions .input-group';
    $(window).scroll(function() {
        $(deactActionsScrollBlur).removeClass('active');
    });
    $("#search > .container > .actions > ul > li").focusout(function() {
        $(deactActionsScrollBlur).removeClass('active');
    });*/

    //autocomplete da busca
    $('#search #palavra, #mini-header #palavra-mini-header').blur(function(){ return;
        $(this).parent().find('.search-autocomplete-results').hide();
    });

    $('#search #palavra, #mini-header #palavra-mini-header').keyup(function(){ return;
        var val = $(this).val();

        var auto = $(this).parent().find('.search-autocomplete-results');
        if(!auto[0]){
            $(this).parent().append('<div class="search-autocomplete-results show-on-desktop"></div>');
            auto = $(this).parent().find('.search-autocomplete-results');
        }

        if(val.length > 0) {
            //resultados normais
            var results = '<div><ul class="keywords-suggestions">';
                //loop de resultados
                results += '<li><a href="#">'+val+' manga longa</a></li>';
                results += '<li><a href="#">'+val+' manchada</a></li>';
                results += '<li><a href="#">'+val+'</a></li>';
            results += '</ul></div>';

            //resultados de produtos
            results += '<div><ul class="products-suggestions">';
                //loop de resultados
                results += '<li>';
                    results += '<a href="#"><img src="http://placehold.it/76x92/1cb5ac/ffffff" alt="Camiseta manga longa masculina em malha flamê"></a>';
                    results += '<div class="info">';
                        results += '<h2><a href="#">Camiseta manga longa masculina em malha flamê</a></h2>';
                        results += '<div class="cores">';
                            results += '<ul>';
                                results += '<li class="roxo"></li>';
                                results += '<li class="azul"></li>';
                                results += '<li class="vermelho"></li>';
                                results += '<li class="laranja"></li>';
                                results += '<li class="rosa"></li>';
                            results += '</ul>';
                        results += '</div>';
                        results += '<div class="tamanhos">';
                            results += '<ul>';
                                results += '<li><a href="#">PP</a></li>';
                                results += '<li><a href="#">P</a></li>';
                                results += '<li><a href="#">M</a></li>';
                                results += '<li><a href="#">G</a></li>';
                                results += '<li><a href="#">GG</a></li>';
                            results += '</ul>';
                        results += '</div>';
                        results += '<div class="precos">';
                            results += '<s>de: R$79,90</s>';
                            results += '<strong>por: R$49,90</strong>';
                        results += '</div>';
                    results += '</div>';
                results += '</li>';
                results += '<li>';
                    results += '<a href="#"><img src="http://placehold.it/76x92/1cb5ac/ffffff" alt="Camiseta manga longa masculina em malha flamê"></a>';
                    results += '<div class="info">';
                        results += '<h2><a href="#">Camiseta manga longa masculina em malha flamê</a></h2>';
                        results += '<div class="cores">';
                            results += '<ul>';
                                results += '<li class="roxo"></li>';
                                results += '<li class="azul"></li>';
                                results += '<li class="vermelho"></li>';
                                results += '<li class="laranja"></li>';
                                results += '<li class="rosa"></li>';
                            results += '</ul>';
                        results += '</div>';
                        results += '<div class="tamanhos">';
                            results += '<ul>';
                                results += '<li><a href="#">PP</a></li>';
                                results += '<li><a href="#">P</a></li>';
                                results += '<li><a href="#">M</a></li>';
                                results += '<li><a href="#">G</a></li>';
                                results += '<li><a href="#">GG</a></li>';
                            results += '</ul>';
                        results += '</div>';
                        results += '<div class="precos">';
                            results += '<s>de: R$79,90</s>';
                            results += '<strong>por: R$49,90</strong>';
                        results += '</div>';
                    results += '</div>';
                results += '</li>';
                // /loop
            results += '</ul></div>';
            
            auto.html(results);
            auto.show();
        } else {
            auto.hide();
        }
    });

    $('#search #palavra, #mini-header #palavra-mini-header').parents("form").submit(function(e) {
        var inputSearch = $(this).find("input[type=search]");
        if(inputSearch.val() === inputSearch.attr("data-placefocus") || inputSearch.val().length < 1) {
            e.preventDefault();
        }
    });
    
    // ativação de tamanho ao clicar
    $('.tamanhos ul li').click(function(e) {
        $(this).parent().parent().find("a").removeClass("active");
        var li = $(this).parent();
        if(!li.hasClass('tabela') && !$(this).hasClass('out-of-stock')) {
            $(this).toggleClass('active');
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
    
    //prod
    //$(".zoneA .content").eq(1).find(".container > .right").html($(".zoneA .product-infinite-wrapper").eq(0));
    
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
    
    $('.zoneA > .content').eq(2).addClass('mask');
    $(".zoneA .content").eq(2).find(".container > .right").html($(".zoneA .product-infinite-wrapper").eq(0));    
    
	var quant_mcart = $('#carrinho .bag .count').text();
	if(quant_mcart != 0)
		$('.fa.fa-fw.fa-shopping-cart').append('<span>'+quant_mcart+'</span>');
    
    //atualiza mini-cart responsivo
//    $('.addToCartButton').click(function(){
//    	var ww = $(window).width();
//    	if(ww < '1190'){
//    		$('body').scrollTop(0);
//    		//window.location.reload();
//    		setTimeout(function(){
//    		    window.location.reload();
//    		     
//    		}, 1000);
//    	}
//    })
	
    $('.cadastre-se.logged-in > a').click(function(){
    	var ww = $(window).width();
    	if(ww < '1190'){
    		return false;
    	}
    });
    
    //abrir boleto automatico
    var link = $('a.rb').attr('href');
    if(link)
    	window.open(link);
    
    //valida login email
    $('#j_username').removeClass('required').addClass('required-email');
    
    //tira transparencia no canto esquerdo exceto na home
    var caminho = window.location.pathname;
    if(caminho!="/store/"){
	    $('.product-infinite-wrapper').each(function(){
	    	$(this).removeClass('no-before');
	    });
    }
    
//    $('.info .cores ul li:first-child').each(function(){$(this).click()});
//    $('.info .tamanhos ul li:first-child').each(function(){$(this).click()});
//	$('.info .addToCartForm #productCodePost').each(function(){
//		
//		var tempVal = $(this).val();
//		var tempThis = tempVal.split('-');
//		
//		$(this).val(tempThis[0]);
//	});
    
});

// FUNÇÕES

window.infiniteCount = 0;

function infiniteProductScroll() {
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
}

function infiniteScrollLoadProducts() {
    var result;
    // AJAX BASE
    // 9 produtos por página
    // Contagem de carregamento: 3 para desktop e 2 para mobile
    /*
    $.ajax({
        url: "/wp-admin/admin-ajax.php",
        type:'POST',
        data: "action=infinite_scroll&page_no="+ pageNumber + '&loop_file=loop', 
        success: function(html){
            result = html;    // This will be the div where our content will be loaded
        }
    });
    */

    //-- EXEMPLO (apagar na implementação)
//    result = '<div class="product"> <img src="images/produto-02.jpg" width="296" height="396" alt="Camisa Jeans Manga Longa masculina com Lavagem Diferenciada"> <div class="info"> <h2>Camisa Jeans Manga Longa masculina com Lavagem Diferenciada</h2> <div class="selos"> <ul> <li class="frete">Frete Grátis</li> <li class="lancamento">Lançamento</li> <li class="promocao">Promoção</li> </ul> </div> <div class="cores"> <h3>Cores:</h3> <ul> <li class="laranja"></li> <li class="verde"></li> <li class="azul"></li> <li class="roxo"></li> <li class="rosa"></li> <li class="marrom"></li> <li class="laranja"></li> <li class="verde"></li> </ul> </div> <div class="tamanhos"> <h3>Tamanhos:</h3> <ul> <li><a href="#">P</a></li> <li><a href="#">M</a></li> <li><a href="#">G</a></li> <li><a href="#">XG</a></li> </ul> </div> <div class="precos"> <s>De: R$229,99</s> <strong>Por: R$199,99</strong> <p>5x de <b>R$39,99</b></p> </div> <a href="#" class="add">Adicionar a sacola</a> <span class="detalhes">Veja mais detalhes do produto</span> </div> </div><div class="product"> <img src="images/produto-02.jpg" width="296" height="396" alt="Camisa Jeans Manga Longa masculina com Lavagem Diferenciada"> <div class="info"> <h2>Camisa Jeans Manga Longa masculina com Lavagem Diferenciada</h2> <div class="selos"> <ul> <li class="frete">Frete Grátis</li> <li class="lancamento">Lançamento</li> <li class="promocao">Promoção</li> </ul> </div> <div class="cores"> <h3>Cores:</h3> <ul> <li class="laranja"></li> <li class="verde"></li> <li class="azul"></li> <li class="roxo"></li> <li class="rosa"></li> <li class="marrom"></li> <li class="laranja"></li> <li class="verde"></li> </ul> </div> <div class="tamanhos"> <h3>Tamanhos:</h3> <ul> <li><a href="#">P</a></li> <li><a href="#">M</a></li> <li><a href="#">G</a></li> <li><a href="#">XG</a></li> </ul> </div> <div class="precos"> <s>De: R$229,99</s> <strong>Por: R$199,99</strong> <p>5x de <b>R$39,99</b></p> </div> <a href="#" class="add">Adicionar a sacola</a> <span class="detalhes">Veja mais detalhes do produto</span> </div> </div><div class="product"> <img src="images/produto-02.jpg" width="296" height="396" alt="Camisa Jeans Manga Longa masculina com Lavagem Diferenciada"> <div class="info"> <h2>Camisa Jeans Manga Longa masculina com Lavagem Diferenciada</h2> <div class="selos"> <ul> <li class="frete">Frete Grátis</li> <li class="lancamento">Lançamento</li> <li class="promocao">Promoção</li> </ul> </div> <div class="cores"> <h3>Cores:</h3> <ul> <li class="laranja"></li> <li class="verde"></li> <li class="azul"></li> <li class="roxo"></li> <li class="rosa"></li> <li class="marrom"></li> <li class="laranja"></li> <li class="verde"></li> </ul> </div> <div class="tamanhos"> <h3>Tamanhos:</h3> <ul> <li><a href="#">P</a></li> <li><a href="#">M</a></li> <li><a href="#">G</a></li> <li><a href="#">XG</a></li> </ul> </div> <div class="precos"> <s>De: R$229,99</s> <strong>Por: R$199,99</strong> <p>5x de <b>R$39,99</b></p> </div> <a href="#" class="add">Adicionar a sacola</a> <span class="detalhes">Veja mais detalhes do produto</span> </div> </div><div class="product"> <img src="images/produto-02.jpg" width="296" height="396" alt="Camisa Jeans Manga Longa masculina com Lavagem Diferenciada"> <div class="info"> <h2>Camisa Jeans Manga Longa masculina com Lavagem Diferenciada</h2> <div class="selos"> <ul> <li class="frete">Frete Grátis</li> <li class="lancamento">Lançamento</li> <li class="promocao">Promoção</li> </ul> </div> <div class="cores"> <h3>Cores:</h3> <ul> <li class="laranja"></li> <li class="verde"></li> <li class="azul"></li> <li class="roxo"></li> <li class="rosa"></li> <li class="marrom"></li> <li class="laranja"></li> <li class="verde"></li> </ul> </div> <div class="tamanhos"> <h3>Tamanhos:</h3> <ul> <li><a href="#">P</a></li> <li><a href="#">M</a></li> <li><a href="#">G</a></li> <li><a href="#">XG</a></li> </ul> </div> <div class="precos"> <s>De: R$229,99</s> <strong>Por: R$199,99</strong> <p>5x de <b>R$39,99</b></p> </div> <a href="#" class="add">Adicionar a sacola</a> <span class="detalhes">Veja mais detalhes do produto</span> </div> </div><div class="product"> <img src="images/produto-02.jpg" width="296" height="396" alt="Camisa Jeans Manga Longa masculina com Lavagem Diferenciada"> <div class="info"> <h2>Camisa Jeans Manga Longa masculina com Lavagem Diferenciada</h2> <div class="selos"> <ul> <li class="frete">Frete Grátis</li> <li class="lancamento">Lançamento</li> <li class="promocao">Promoção</li> </ul> </div> <div class="cores"> <h3>Cores:</h3> <ul> <li class="laranja"></li> <li class="verde"></li> <li class="azul"></li> <li class="roxo"></li> <li class="rosa"></li> <li class="marrom"></li> <li class="laranja"></li> <li class="verde"></li> </ul> </div> <div class="tamanhos"> <h3>Tamanhos:</h3> <ul> <li><a href="#">P</a></li> <li><a href="#">M</a></li> <li><a href="#">G</a></li> <li><a href="#">XG</a></li> </ul> </div> <div class="precos"> <s>De: R$229,99</s> <strong>Por: R$199,99</strong> <p>5x de <b>R$39,99</b></p> </div> <a href="#" class="add">Adicionar a sacola</a> <span class="detalhes">Veja mais detalhes do produto</span> </div> </div><div class="product"> <img src="images/produto-02.jpg" width="296" height="396" alt="Camisa Jeans Manga Longa masculina com Lavagem Diferenciada"> <div class="info"> <h2>Camisa Jeans Manga Longa masculina com Lavagem Diferenciada</h2> <div class="selos"> <ul> <li class="frete">Frete Grátis</li> <li class="lancamento">Lançamento</li> <li class="promocao">Promoção</li> </ul> </div> <div class="cores"> <h3>Cores:</h3> <ul> <li class="laranja"></li> <li class="verde"></li> <li class="azul"></li> <li class="roxo"></li> <li class="rosa"></li> <li class="marrom"></li> <li class="laranja"></li> <li class="verde"></li> </ul> </div> <div class="tamanhos"> <h3>Tamanhos:</h3> <ul> <li><a href="#">P</a></li> <li><a href="#">M</a></li> <li><a href="#">G</a></li> <li><a href="#">XG</a></li> </ul> </div> <div class="precos"> <s>De: R$229,99</s> <strong>Por: R$199,99</strong> <p>5x de <b>R$39,99</b></p> </div> <a href="#" class="add">Adicionar a sacola</a> <span class="detalhes">Veja mais detalhes do produto</span> </div> </div><div class="product"> <img src="images/produto-02.jpg" width="296" height="396" alt="Camisa Jeans Manga Longa masculina com Lavagem Diferenciada"> <div class="info"> <h2>Camisa Jeans Manga Longa masculina com Lavagem Diferenciada</h2> <div class="selos"> <ul> <li class="frete">Frete Grátis</li> <li class="lancamento">Lançamento</li> <li class="promocao">Promoção</li> </ul> </div> <div class="cores"> <h3>Cores:</h3> <ul> <li class="laranja"></li> <li class="verde"></li> <li class="azul"></li> <li class="roxo"></li> <li class="rosa"></li> <li class="marrom"></li> <li class="laranja"></li> <li class="verde"></li> </ul> </div> <div class="tamanhos"> <h3>Tamanhos:</h3> <ul> <li><a href="#">P</a></li> <li><a href="#">M</a></li> <li><a href="#">G</a></li> <li><a href="#">XG</a></li> </ul> </div> <div class="precos"> <s>De: R$229,99</s> <strong>Por: R$199,99</strong> <p>5x de <b>R$39,99</b></p> </div> <a href="#" class="add">Adicionar a sacola</a> <span class="detalhes">Veja mais detalhes do produto</span> </div> </div><div class="product"> <img src="images/produto-02.jpg" width="296" height="396" alt="Camisa Jeans Manga Longa masculina com Lavagem Diferenciada"> <div class="info"> <h2>Camisa Jeans Manga Longa masculina com Lavagem Diferenciada</h2> <div class="selos"> <ul> <li class="frete">Frete Grátis</li> <li class="lancamento">Lançamento</li> <li class="promocao">Promoção</li> </ul> </div> <div class="cores"> <h3>Cores:</h3> <ul> <li class="laranja"></li> <li class="verde"></li> <li class="azul"></li> <li class="roxo"></li> <li class="rosa"></li> <li class="marrom"></li> <li class="laranja"></li> <li class="verde"></li> </ul> </div> <div class="tamanhos"> <h3>Tamanhos:</h3> <ul> <li><a href="#">P</a></li> <li><a href="#">M</a></li> <li><a href="#">G</a></li> <li><a href="#">XG</a></li> </ul> </div> <div class="precos"> <s>De: R$229,99</s> <strong>Por: R$199,99</strong> <p>5x de <b>R$39,99</b></p> </div> <a href="#" class="add">Adicionar a sacola</a> <span class="detalhes">Veja mais detalhes do produto</span> </div> </div><div class="product"> <img src="images/produto-02.jpg" width="296" height="396" alt="Camisa Jeans Manga Longa masculina com Lavagem Diferenciada"> <div class="info"> <h2>Camisa Jeans Manga Longa masculina com Lavagem Diferenciada</h2> <div class="selos"> <ul> <li class="frete">Frete Grátis</li> <li class="lancamento">Lançamento</li> <li class="promocao">Promoção</li> </ul> </div> <div class="cores"> <h3>Cores:</h3> <ul> <li class="laranja"></li> <li class="verde"></li> <li class="azul"></li> <li class="roxo"></li> <li class="rosa"></li> <li class="marrom"></li> <li class="laranja"></li> <li class="verde"></li> </ul> </div> <div class="tamanhos"> <h3>Tamanhos:</h3> <ul> <li><a href="#">P</a></li> <li><a href="#">M</a></li> <li><a href="#">G</a></li> <li><a href="#">XG</a></li> </ul> </div> <div class="precos"> <s>De: R$229,99</s> <strong>Por: R$199,99</strong> <p>5x de <b>R$39,99</b></p> </div> <a href="#" class="add">Adicionar a sacola</a> <span class="detalhes">Veja mais detalhes do produto</span> </div> </div>';
    //-- EXEMPLO

//    return result;
}

//function formValidation() {
//    $("form button").click(function(e) {
//        // var formSection = $(this).parents('section').first();
//        var formSection = $(this).closest('section, form, div');
//        if(!formSection.find("input")[0]) {
//            formSection = formSection.closest('form');
//        }
//        var radioUnchecked;
//        var errors = new Array();
//        var warningDesc;
//
//        formSection.find("input:not([type=radio],[type=checkbox],[type=search])").each(function(itemIndex) {
//            if($(this).val() === "" || $(this).val() === $(this).attr("data-placefocus")) {
//                $(this).addClass('validation-warning');
//                warningDesc = $(this).parent().find('p.validation-warning-desc.input-'+itemIndex+' span');
//                if(!warningDesc[0]) {
//                    var inputW = $(this).outerWidth();
//                    var inputM = [
//                        $(this).css("margin-top"),
//                        $(this).css("margin-right"),
//                        $(this).css("margin-bottom"),
//                        $(this).css("margin-left")
//                    ];
//                    $(this)
//                        .wrap('<div class="validation-wrapper"></div>')
//                        .parent()
//                        .css({
//                            "width": inputW,
//                            "margin": inputM.join(' ')
//                        })
//                        .append('<p class="validation-warning-desc input-'+itemIndex+'"><span></span></p>');
//                    warningDesc = $(this).parent().find('p.validation-warning-desc.input-'+itemIndex+' span');
//                }
//
//                warningDesc.html('É necessário preencher este campo');
//                errors = ['text'];
//            } else {
//                $(this).removeClass('validation-warning');
//                $(this).parent().find('p.validation-warning-desc.input-'+itemIndex).remove();
//            }
//        })
//
//        // radio not checked
//        $('input[type=radio]').each(function() {
//            if($(this).is(':checked')) {
//                radioUnchecked = false;
//            }
//        });
//        if(radioUnchecked) {
//            // $(this).addClass('validation-warning');
//            errors = ['radio'];
//        } else {
//           if(errors['radio']) {
//                errors['radio'].splice();
//            }
//        }
//
//        if(errors.length > 0) {
//            e.preventDefault();
//        }
//    });
//}

function jumptToNextInput(element) {
    $(element).find("input").keyup(function() {
        var maxlength = $(this).attr("maxlength");
        if($(this).val().length >= maxlength) {
            $(this).next("input").focus();
        }
    });
}

function getInstagramPhotos() {
    // script de requisição de fotos do Instagram
    var userID = "215480059";
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
        toasterActive = setTimeout(function() { toaster.removeClass("active") }, 10000);
    });
}

//toaster_auto
//boa_sorte
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

//function getSizeProductPerColor(data)
//{
//	var id = $(data).attr('id');
//	
//	$(data).parent().parent().find("a").removeClass("active");
//	var li = $(data).parent();
//
//	if(!li.hasClass('tabela') && !$(this).hasClass('out-of-stock')) {
//		$("#"+id+" a").toggleClass('active');
//	}
//	
//	$(data).parent().parent().parent().parent().parent().parent().find('input[type=hidden][class=codeProduct]').val($(data).attr('id'));
//	$(data).parent().parent().parent().parent().parent().parent().find('#productCode').val($(data).attr('id'));
//	$(data).parent().parent().parent().parent().parent().parent().find('#productCodePost').val($(data).attr('id'));
//	
//	$(data).parent().parent().parent().parent().find('input[type=hidden][class=codeProduct]').val($(data).attr('id'));
//	$(data).parent().parent().parent().parent().find('#productCode').val($(data).attr('id'));
//	$(data).parent().parent().parent().parent().find('#productCodePost').val($(data).attr('id'));
//}


function repositionDesktopSubmenu(el) {
    if($(window).width() > 880) {
        var submenu = el.children(".sub-menu");
        if(submenu[0]) {
            submenu.css("left", 0);
            var container = ($(window).width() > 980) ? el.parents(".container") : $(window);
            var submenuLeftPos = (container.hasClass("container")) ? submenu.offset().left - container.offset().left : submenu.offset().left;
            //console.log(submenuLeftPos);
            // verifica se o menu está passando dos limites do container
            var submenuOutbounds = ((container.width() - submenuLeftPos) < submenu.outerWidth())?true:false; 
            if(submenuOutbounds) {
                var leftPos = ((container.width() - submenuLeftPos) - submenu.outerWidth())*1.05;
                submenu.css("left", leftPos);
            }
        }
    }
}

