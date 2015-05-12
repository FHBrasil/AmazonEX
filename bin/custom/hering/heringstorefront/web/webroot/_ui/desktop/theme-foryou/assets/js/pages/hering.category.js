//init
$(document).ready(function(){
	
	//"exibir todos" da sidebar
	$('.categories .show-all').click(function(e){
		e.preventDefault();
		//$(this).toggleClass('minus');
		$(this).fadeOut('fast', function(){
			$(this).parent().find('ul:last-child').slideToggle('fast');
		});
		var eParent = $(this).parents(".cat-links");
        eParent.find(".topFacetValues").hide();
	});
	
	//CARROSSEL DOS TAMANHOS
	$(".tamanhos ul").each(function(index){
		if($(this).children("li").length > 5){
			  $(this).bxSlider({
          infiniteLoop: false,          
					controls: true,
					pager: false,
					hideControlOnEnd: false,
					auto: false
				});
		}
	});
	
	//Para os links de mais categoria ou mais cores, 	
	//Ira dar show do componente a baixo.
	$("a.show-all").click(function(){
		$(this).prev("ul").hide();
		$(this).next("ul").show();
	});
	
});