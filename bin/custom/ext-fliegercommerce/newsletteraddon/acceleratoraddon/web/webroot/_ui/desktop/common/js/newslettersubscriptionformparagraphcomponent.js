var manageFormSubmit = {

    init: function() {
        this.defer(this.submitFormAjax());
    },

    defer: function(fn) {
        if (window.jQuery) fn();
        else setTimeout(function() { defer(fn) }, 50);
    },

    submitFormAjax: function() {
        $('#newsletterSubscriptionFormComponent').submit(function(event) {

            event.preventDefault();

            var returnMessage = $('<div id="customMsg" class="row"> <div class="alert alert-danger"> <a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Error!</strong>&nbsp;</div> </div>');
            var data = $(this).serialize();
            var url = $(this).attr('action');
            var orderConfirmation = $(this).hasClass("orderConfirmation");

            $.ajax({
                type: 'POST',
                url: url,
                data: data,
                success: function(data) {
                	//algum retorno
                	if(!orderConfirmation){
                		alert(data);
                	}
                	
                	//for register newsletter in orderConfirmation
                	if(orderConfirmation){
                		$('#sectionFormNewsletter').slideUp(250, function(){ 
    						$(this).remove();
    					});
    					setTimeout(function(){
    						$('#newsletterSubscriptionSucessfully').slideDown(250, function(){
    							//
    						});
    					}, 500);
                	}               	
                },
                error : function(data) {	
                	//algum retorno
                	if(!orderConfirmation){
                		alert(data);
                	}
                	
                	//for register newsletter in orderConfirmation
                	if(orderConfirmation){
	    				$('.prehead141211').append(returnMessageCustom);	
	    				$('#customMsg .alert-danger').append(data);
	    				setTimeout(function(){
	    					$('#customMsg').slideUp(250, function(){
	    						$(this.remove());
	    					});
	    				}, 5000);
                	}
    			}
            });

            return false;

        });
    }

}

manageFormSubmit.init();