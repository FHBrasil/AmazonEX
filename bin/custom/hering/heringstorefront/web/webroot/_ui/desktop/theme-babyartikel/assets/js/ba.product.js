<%-- slider productpage imgs --%>
$(document).ready(function() {
$("#mainCarousel").swiperight(function() {
$(this).carousel('prev');
});
$("#mainCarousel").swipeleft(function() {
$(this).carousel('next');
});
});
</script> 
<script type="text/javascript">
$(document).ready(function(){
    $('[data-toggle="popover"]').popover({
        placement : 'bottom',
        html: 'true'
    });
});