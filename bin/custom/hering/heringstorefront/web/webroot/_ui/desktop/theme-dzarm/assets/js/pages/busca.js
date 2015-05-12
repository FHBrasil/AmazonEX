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

	//maximizar e minimizar as categorias
	$('.categories .category dl').click(function(){
		var ul = $(this).parent().find('.cat-links').eq(0);
		var searchBox = $(this).parent().find('.search-box');
		ul.slideToggle('fast', function(){
			$(this).parent().toggleClass('closed');
		});
		searchBox.slideToggle('fast');
	});

	//caixa de subcategoria com busca e limite de 8 visíveis
	$('.categories .with-search-box ul').each(function() {
		var limit = 8;
		if($(this).find('li').length > limit) {
			var sizeOf8 = $(this).find('li').height() * limit;
			$(this).addClass('scroller-box');
			$(window).resize(function() {
				$(this).css("height", sizeOf8);
			});
		} else {
			$(this).css("height", "auto");
		}
	});

	// formulário de busca dinâmica
	$('.categories .with-search-box .search-box').keyup(function() {
		var typed = $(this).val();
		var list = $(this).parent().find('ul');
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

		$(this).blur(function() {
			$(this).val('');
			listItems.each(function() {
				$(this).show();
			})
		})
	});

	//filtros da sidebar
	$('.categories ul li a').click(function(e){
		makeFilters(this);
		loadProducts($(this).attr('href'));
	});

	//refreshSortBar($('#results .sort:first-child'));

	if (window.matchMedia("(max-width: 1199px)").matches){
		console.log('hoho');
		$('.category:not(.closed)').addClass('closed');
	}
});

/**************************
*	Funções 
***************************/

//atualiza a barra de paginacao
function refreshSortBar(el) {
	var bar = el.clone();

	if(el.is(':first-child')){
		$('#results .sort:last-child').remove();
		$('#results').append(bar);
	} else {
		$('#results .sort:first-child').remove();
		$('#results').prepend(bar);
	}

	//paginacao
	$('.sort .pagination li a').off('click');
	$('.sort .pagination li a').click(function(e){
		e.preventDefault();

		var as = $(this).parent().parent().find('a');
		var selected = $(this).parent().parent().find('.selected').parent();
		var change = 0;

		if($(this).hasClass('next')){
			var el = selected.next('li').find('a');

			if(!el.hasClass('next')){
				el.addClass('selected');
				change = 1;
			} else {
				var last = selected.parent().find('li:last-child');
				var num = parseInt(last.prev().find('a').text(), 10)+1;

				selected.parent().find('li').eq(1).remove();
				last.before('<li><a class="selected" href="#">'+num+'</a></li>');
				change = 1;
			}

		} else if($(this).hasClass('prev')){
			var el = selected.prev('li').find('a');
			if(!el.hasClass('prev')){
				el.addClass('selected');
				change = 1;
			} else {
				var first = selected.parent().find('li:first-child');
				var num = parseInt(first.next().find('a').text(), 10)-1;

				if(num>0){
					selected.parent().find('li:last-child').prev().remove();
					first.next().before('<li><a class="selected" href="#">'+num+'</a></li>');
					change = 1;
				}
			}

		} else {
			$(this).addClass('selected');
			change = 1;
		}

		if(change==1){
			selected.find('a').removeClass('selected');
			refreshSortBar($(this).parents('.sort'));
			loadProducts($(this).attr('href'));
		}
	});

	//dropdown mostrar e ordernar
	$('.sort .dropdown-menu li a').off('click');
	$('.sort .dropdown-menu li a').click(function(e){
		e.preventDefault();

		var cat = changeDropValue(this);
		refreshSortBar($(this).parents('.sort'));
		loadProducts(cat);
	});
}

//adiciona na sessão de filtros
function makeFilters(v) {
	
	var el = $(v).parent();
	var clone = el.clone();

	if(!el.parents('.category').hasClass('with-search-box')) {
		el.hide();
	}

	// verifica se existe box de filtros e, caso negativo, a cria
	if(!$("#product-filters")[0]){
		$('.categories .category').eq(0).before('<div id="product-filters" class="category"><div class="cat-links"><ul></ul></div></div>');
	}

	var nomeCategoria = $(v).parents('.category').find('h3').text();
	var filterContent;
	// verifica se o campo possui search-box para definir o conteúdo da tag de filtro e evitar
	// selecionar mais de uma vez o mesmo filtro
	if(el.parents('.category').hasClass('with-search-box')) {
		if($(v).find("input").is(":checked")) {
			return false;
		} else {
			$(v).find("input").attr("checked", "checked");
			filterContent = $(v).text();
		}
	} else if(el.parents('.category').hasClass("cores")) {
		filterContent = clone.find('div.tooltip').text();
		clone.find('div.tooltip').hide();
	} else {
		filterContent = $(v).html();
	}

	clone.find('a').html('<b class="close">&times;</b> <span>'+nomeCategoria+': '+ filterContent +'</span>');

	//$('#product-filters .cat-links ul').append(clone);

	clone.click(function(){
		$(this).remove();
		el.show();

		// remove check de checkboxes
		if(el.parents(".with-search-box")[0]) {
			el.find("input").removeAttr("checked");
		}

		if($('#product-filters .cat-links ul li').length==0){
			$('#product-filters').remove();
		}

		loadProducts();
	});
}

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



//*****************************************
// ATENÇÃO!! ATENÇÃO!! ATENÇÃO!! ATENÇÃO!! 
// ATENÇÃO!! ATENÇÃO!! ATENÇÃO!! ATENÇÃO!! 
// Ver comentário no método de mesmo nome
// em scripts.js
// Este método sobrescreve o HTML do 
// infinite scroll para a página de busca.
// Cada página tem um HTML diferente.
// Utilizar método $.ajax() conforme 
// exemplificado no script.js e não este
// deferred.
//*****************************************
function infiniteScrollLoadProducts() {
    var defer = $.Deferred();
    window.setTimeout(function(){
    	var pageHTML = '';
    	// EH-655 - Retirando scroll infinito de produtos teste
//    	for (var i = 0; i < 8; i++){
//    		pageHTML+= '<div class="product"><div class="photo"><img src="images/produto-0'+((i%3)+1)+'.jpg" width="237" height="357" alt="Camisa Jeans Manga Longa masculina com Lavagem Diferenciada"></div><div class="info"><h2>Camisa Jeans Manga Longa masculina com Lavagem Diferenciada</h2><div class="selos half"><ul><li class="frete">Frete Grátis</li><li class="promocao">Promoção</li></ul></div><div class="cores"><h3>Cores:</h3><ul class="bxslider-cores"><li class="laranja"></li><li class="verde"></li><li class="azul"></li><li class="roxo"></li><li class="rosa"></li><li class="marrom"></li><li class="laranja"></li><li class="verde"></li><li class="rosa"></li><li class="marrom"></li><li class="laranja"></li><li class="verde"></li></ul></div><div class="tamanhos"><h3>Tamanhos:</h3><ul class="bxslider-tamanhos"><li><a href="#">P</a></li><li><a href="#">M</a></li><li><a href="#">G</a></li><li><a href="#">XG</a></li><li><a href="#">P</a></li><li><a href="#">M</a></li><li><a href="#">G</a></li><li><a href="#">XG</a></li><li><a href="#">P</a></li><li><a href="#">M</a></li><li><a href="#">G</a></li><li><a href="#">XG</a></li></ul></div><div class="precos"><s>De: R$229,99</s><strong>Por: R$199,99</strong><p>5x de <b>R$39,99</b></p></div><hr class="clear"><a href="#" class="add btn btn-cta">Adicionar a sacola</a><span class="detalhes">Veja mais detalhes do produto</span></div></div>';
//    	}
        defer.resolve(pageHTML);
    }, 2000);
    return defer.promise();

}