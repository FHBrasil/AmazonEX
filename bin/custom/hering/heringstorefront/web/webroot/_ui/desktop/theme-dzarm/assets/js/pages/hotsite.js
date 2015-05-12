$(document).ready(function(){
	// adapta o width do menu principal para a resolução atual
	if($(".kids.interna")[0]) {
		productNavWidthResize();
		$(window).resize(function() {
			productNavWidthResize();
		})
	}

	// esconde todos os sliders, menos o primeiro
	$("#surf-based-suggestions .product-infinite-wrapper").not(":eq(0)").fadeOut("medium");
	// exibe o slider selecionado no menu
	$('#surf-based-suggestions ul li a').click(function(e){
		e.preventDefault();
		
		var selectedSlider = $(this).attr('rel');
		$('#surf-based-suggestions ul li').removeClass('active');
		$(this).parent().addClass('active');
		$('#surf-based-suggestions .product-infinite-wrapper').fadeOut("slow").promise().done(function() {
			$(selectedSlider).fadeIn("slow");
		});
	});
});

function productNavWidthResize() {
	var barNavRight = $("#bar-secondary.product-nav .right");
	if($(window).width() > 1199) {
		barNavRight.css({
			"width": ($("#bar-secondary.product-nav").width() - $("#bar-secondary.product-nav ul").offset().left)
		});
	} else {
		barNavRight.css("width", "100%");
	}
}