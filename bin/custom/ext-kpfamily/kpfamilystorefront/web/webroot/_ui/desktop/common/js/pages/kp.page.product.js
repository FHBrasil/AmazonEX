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
    
    /*!  */
    setSelectedVariant : function() {
        $(".btn-selectVariant").click(function() {
            var productCode = $(this).prop("id");
            $.ajax({
                type : "POST",
                url : "/p/"+ productCode +"/chooseVariant",
                success : function(data) {
                    $("form[name=addToCartForm]").find("input[type=hidden][id=maxStock]")
                            .val(data.variantStock);
                    $("form[name=addToCartForm]").find("input[type=hidden][name=productCodePost]")
                            .val(data.variantCode);
                    $("#productDetailImage").prop("src", data.variantPrimaryImageUrl);
                    $("#productDetailImage").prop("title", data.variantImageTitle);
                    $("#productDetailImage").prop("alt", data.variantImageAltText);
                },
                error : function(xhr, statusText, error) {
                    
                },
                complete : function(xhr, statusText) {
                    
                }
            });
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
