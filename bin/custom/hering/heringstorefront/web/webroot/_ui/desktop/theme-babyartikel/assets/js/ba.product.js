//slider productpage imgs
$(document).ready(function() {
	$("#mainCarousel").swiperight(function() {
		$(this).carousel('prev');
	});
	$("#mainCarousel").swipeleft(function() {
		$(this).carousel('next');
	});
	
	$('[data-toggle="popover"]').popover({
        placement : 'bottom',
        html: 'true'
    });
});