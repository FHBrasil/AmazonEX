var scheduledNewsletter = {
		
    init: function() {
        this.defer(this.submitFormAjax());
    },

    defer: function(fn) {
        if (window.jQuery) fn();
        else setTimeout(function() { defer(fn) }, 50);
    },

    submitFormAjax: function() {
        $('#scheduledNewsletter').change(function(event) {

            event.preventDefault();

            var data = $(this).serialize();
            var url = $(this).attr('action');

            $.ajax({
            	async : true,
                type: 'GET',
                url: '/my-account/scheduled-newsletter',
                data: data,
                success: function(data) {
                	alert("SUBSCRIBE TO SCHEDULED NEWSLETTER");
                	// faz alguma coisa com os dados retornados
                }
            });

            return false;

        });
    }

}

scheduledNewsletter.init();