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
                	//
                }
            
            });

            return false;

        });
        
        $('#tipsNewsletterCheckbox').change(function() {
    		
        	if (document.getElementById('tipsNewsletterCheckbox').checked) {
        		
        		$('#tipsNewsletterDate').prop('required', true);
        	}
        	else {
        		$('#tipsNewsletterDate').prop('required', false);
        	}
        	
            if($('#tipsNewsletterDate:required').val() === '') {
                $('#tipsNewsletterDate:required').after('<div class="alert alert-warning alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button> Pflichtfeld. </div>');
            }


        		
        });
        
    }

}

tipsNewsletter.init();
