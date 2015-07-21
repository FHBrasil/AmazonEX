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
            var id = '#tipsNewsletterCheckbox';
            var selector = 'input' + id;

            $.ajax({
            	async : true,
                type: 'GET',
                url: '/my-account/tips-newsletter',
                data: data,
                success: function(data) {
                	if ($('#tipsNewsletterCheckbox').checked) {
                		sessionStorage.setItem(selector, true);
                		$(id).prop('disabled', true);
                		console.log("aqui");
                		//$('#tipsNewsletterCheckbox').prop('disabled', true);
                	}
                	else {
                		sessionStorage.setItem(selector, false);
                		$(id).prop('disabled', false);
                		console.log("oi");
                		//$('#tipsNewsletterCheckbox').prop('disabled', false);
                	}
                }
            
            });

            return false;

        });
    }

}

tipsNewsletter.init();
