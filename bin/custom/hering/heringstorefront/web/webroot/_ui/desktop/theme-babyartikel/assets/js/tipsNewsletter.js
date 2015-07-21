var tipsNewsletter = {
		
    init: function() {
        this.defer(this.submitFormAjax());
    },

    defer: function(fn) {
        if (window.jQuery) fn();
        else setTimeout(function() { defer(fn) }, 50);
    },

    submitFormAjax: function() {
        $('#tipsNewsletter').change(function(event) {

            event.preventDefault();

            var data = $(this).serialize();
            var url = $(this).attr('action');

            $.ajax({
            	async : true,
                type: 'GET',
                url: '/my-account/tips-newsletter',
                data: data,
                success: function(data) {
                	// faz alguma coisa com os dados retornados
                }
            });

            return false;

        });
    }

}

tipsNewsletter.init();