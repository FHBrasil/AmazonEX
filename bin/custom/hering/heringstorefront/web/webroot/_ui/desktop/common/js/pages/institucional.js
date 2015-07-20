$(document).ready(function() {
	$('.page.termos .terms .term-title').click(function() {
		$(this).toggleClass("opened");
		$(this).siblings('.term-content').slideToggle("medium");
	});

	// fotos do instagram
	getInstagramPhotos();

	$('.institutional-links.full > ul > li > a').click(function(e) {
		e.preventDefault();

		$(this).parent().parent().find("li > div").slideUp("medium");
		$(this).siblings('div').slideToggle("medium");
	});

	// sliders de looks e dicas
	// exibe o slider selecionado no menu
	$('#looks-dicas-sliders ul li a').click(function(e){
		e.preventDefault();
		
		var selectedSlider = $(this).attr('rel');
		$('#looks-dicas-sliders ul li').removeClass('active');
		$(this).parent().addClass('active');
		// esconde slider anterior e exibe o escolhido, recarregando o slider escolhido quando aparecer
		$('#looks-dicas-sliders .product-infinite-wrapper').stop(0,0).fadeOut("slow").promise().done(function() {
			$(selectedSlider).stop(0,0).fadeIn("slow");
			window.globalSlidersObjects[selectedSlider.replace("#", "")].reloadSlider();
		});
	});

	// ação padrão de botão avise-me em section-mini
	$('.section-mini-content .avise-me').click(function(e) {
		e.preventDefault();
		$(this).parents('.product-mini').find('.esgotado-form').fadeIn("medium");
	});
});

//modal sac-online
$(document).ready(function() {
	// fancybox para lightbox e modal
	$('.sac-online').fancybox({
		maxWidth: 510,
		minWidth: 470,
		padding: 20,
		closeBtn: true,
		type: 'iframe'
	});
});