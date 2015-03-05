KP.page.product = {
        
    init_trustedShops: function() {
        var _tsid = 'XA2EF864014A142CF9EDC2483FE556551';
        _tsConfig = {
            'yOffset' : '30', /* offset from page bottom */
            'variant' : 'reviews' /* text, default, small, reviews */
        };
        var _ts = document.createElement('script');
        _ts.type = 'text/javascript';
        _ts.async = true;
        _ts.charset = 'utf-8';
        _ts.src = '//widgets.trustedshops.com/js/' + _tsid + '.js';
        var __ts = document.getElementsByTagName('script')[0];
        __ts.parentNode.insertBefore(_ts, __ts);
    },
    
    setSelectedVariant : function() {
        $(".btn-selectVariant").click(function() {
        });
    }

}

$(document).ready(function() {
    with(KP.page.product) {
        setSelectedVariant();
    };
    $("#mainCarousel").swiperight(function() {
        $(this).carousel('prev');
    });
    $("#mainCarousel").swipeleft(function() {
        $(this).carousel('next');
    });
    $('[data-toggle="popover"]').popover({
        placement : 'bottom',
        html : 'true'
    });
});
