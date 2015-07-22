var reviewShoppingExperience = {
		
    init: function() {
        this.defer(this.submitFormAjax());
    },

    defer: function(fn) {
        if (window.jQuery) fn();
        else setTimeout(function() { defer(fn) }, 50);
    },

    submitFormAjax: function() {
        $('#reviewShoppingExperience').change(function(event) {

            event.preventDefault();

            var data = $(this).serialize();
            var url = $(this).attr('action');

            $.ajax({
            	async : true,
                type: 'GET',
                url: '/my-account/review-shopping-experience',
                data: data,
                success: function(data) {
                	alert("SUBSCRIBE TO REVIEW SHOPPING EXPERIENCE");
                	// faz alguma coisa com os dados retornados
                }
            });

            return false;

        });
    }

}

reviewShoppingExperience.init();