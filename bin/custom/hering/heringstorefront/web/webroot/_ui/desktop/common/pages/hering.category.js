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

});