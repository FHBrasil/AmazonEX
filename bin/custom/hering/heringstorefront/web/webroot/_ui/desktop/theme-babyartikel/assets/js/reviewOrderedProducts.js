var reviewOrderedProducts = {
		
    init: function() {
        this.defer(this.submitFormAjax());
    },

    defer: function(fn) {
        if (window.jQuery) fn();
        else setTimeout(function() { defer(fn) }, 50);
    },

    submitFormAjax: function() {
        $('#reviewOrderedProducts').change(function(event) {

            event.preventDefault();

            var data = $(this).serialize();
            var url = $(this).attr('action');

            $.ajax({
            	async : true,
                type: 'GET',
                url: '/my-account/review-ordered-products',
                data: data,
                success: function(data) {
                	alert("SUBSCRIBE TO REVIEW ORDERED PRODUCTS");
                	// faz alguma coisa com os dados retornados
                }
            });

            return false;

        });
    }

}

reviewOrderedProducts.init();