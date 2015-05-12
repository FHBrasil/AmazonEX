/*! O método $(document).ready deste javaScript home-hover-produtos.js está sendo executado dentro do método 
 * $(document).ready dos arquivos ACC.search.js e ACC.category.js. */
$(document).ready( function(){
	
/*! homeHoverProdutos.insertCarousel(); */
            
});


homeHoverProdutos = {
	
		insertCarousel : function() {
            
            $('.jcarousel-item').hover(function() {
                $(this).find('.detalhes-grupo').slideDown("fast");
            }, function() {
                $(this).find('.detalhes-grupo').slideUp("fast");
            });
            
            $('.productGridItem ').hover(
                    function() {
                        $(this).find('.SuperSearchDesignColorsPriceBuy')
                                .slideDown("fast");
                    },
                    function() {
                        $(this).find('.SuperSearchDesignColorsPriceBuy')
                                .slideUp("fast");
                    });
            
            if ($('body').hasClass('page-productGrid')
                    || $('body').hasClass('page-search')) {
            	$('.searchFloatingColors ul').each(function() {
                    if ($(this).find('li').length >= 6) {
                        $(this).jcarousel({
                            animation : {
                                duration : 800,
                                easing : 'linear'
                            },
                            itemFallbackDimension: 300
                        });
                    }
                    
                });
            }
            
        }
};