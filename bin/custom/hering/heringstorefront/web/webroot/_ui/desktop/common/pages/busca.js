//init
$(document).ready(function(){
	// definição de layout grid/list
	var listModePreviousActivated = $("#results").hasClass("list-mode");
	disableListMode();
	$(window).resize(disableListMode);
	function disableListMode() {
		if($(window).width() <= 880) {
			$("#results").removeClass("list-mode");
		} else {
			if(listModePreviousActivated) {
				$("#results").addClass("list-mode");
			}
		}
	}

	// fancybox para lightbox e modal
	$('.fancybox.formas-pagamento, .fancybox.tabela-medidas, .fancybox.prazos-entrega').fancybox({
		maxWidth: 700,
		minWidth: 320,
		padding: 20
	});

	//maximizar e minimizar as categorias
	$('.categories .category dl, .categories .category h3').click(function(){
		var ul = $(this).parent().find('.cat-links').eq(0);
		var searchBox = $(this).parent().find('.search-box');
		ul.slideToggle('fast', function(){
			$(this).parent().toggleClass('closed');
		});
		searchBox.slideToggle('fast');
	});

	//"exibir todos" da sidebar
	/*$('.categories .show-all').click(function(e){
		e.preventDefault();
		//$(this).toggleClass('minus');
		$(this).fadeOut('fast', function() {
			$(this).parent().find('ul:last-child').slideToggle('fast');
		});
	});*/

	//caixa de subcategoria com busca e limite de 8 visíveis
	$('.categories .with-search-box ul').each(function() {
		var limit = 8;
		if($(this).find('li').length > limit) {
			var sizeOfItems = $(this).find('li').height() * limit;
			$(this).addClass('scroller-box');
			$(this).css("height", sizeOfItems + "px");
	 		$(this).jScrollPane();
	 		$(this).parents(".cat-links").find(".search-box").show();
		} else {
			$(this).css("height", "auto");
		}
	});

	// formulário de busca dinâmica
	$('.categories .with-search-box .search-box').keyup(function() {
		var typed = $(this).val();
		var list = $(this).parents(".with-search-box").find('ul');
		var listItems = list.find('li');

		if(typed.length > 0) {
			listItems.each(function() {
				$(this).show();
				var val = $(this).text().trim();
				regExp = new RegExp(typed, 'i');
				if(val.match(regExp) === null) {
					$(this).hide();
				}
			});
		} else {
			listItems.each(function() { $(this).show(); })
		}
	});
	
	$('.categories .with-search-box .search-box').blur(function() {
		var typed = $(this).val();
		var list = $(this).parents(".with-search-box").find('ul');
		var listItems = list.find('li');
		if(typed.length == 0) {
			listItems.each(function() { $(this).show(); })
		}
	});
});

/**************************
*	Funções 
***************************/


//carregar produtos
function loadProducts(filters){
	var time = ($('body')[0].scrollTop > 200)?600:0;

	$("body,html").animate({
        scrollTop: 0
    }, time, function(){

		if(!$('#products-loader')[0]){
			$('#results .products').before('<div id="products-loader" class="loader" style="display:none"></div>');
		}

		$('#results .products').fadeOut('fast', function(){
			$('#products-loader').fadeIn();
		});

		//pega os produtos por ajax
		setTimeout(function(){
			$('#products-loader').fadeOut('fast', function(){
				$('#results .products').fadeIn();
			});
		}, 1000);

    });

	return false;
}