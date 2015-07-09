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


function fbashare() {
    window.open('http://www.facebook.com/sharer/sharer.php?u=${productUrl}&title=${pageTitle}', 'popup', 'width=600,height=260');
    return false;
}

function gbashare() {
    window.open('http://plus.google.com/share?url=${productUrl}', 'popup', 'width=600,height=360');
    return false;
}

function tbashare() {
    window.open('http://twitter.com/intent/tweet?status=${productUrl}', 'popup', 'width=600,height=450');
    return false;
}

function pbashare() {
    window.open('http://www.pinterest.com/pin/create/link/?url=${productUrl}&media=${productZoomImagesUrl}&description=Pinterest', 'popup', 'width=800,height=400');
    return false;
}