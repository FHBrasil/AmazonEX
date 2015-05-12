hering.form = {
		
	REQUIRED 			: "required",
	REQUIRED_EMAIL 		: "required-email",
	REQUIRED_PAYMENT 	: "required-payment",
	REQUIRED_VOUCHER 	: "required-voucher",
	REQUIRED_NUMBERS 	: "required-numbers",
	REQUIRED_LETTERS 	: "required-letters",
	
	REGEX_EMAIL		: "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
	REGEX_NUMBERS	: /[0-9]*/gi,
	REGEX_LETTERS	: /[a-z\s ]*/gi,

	validateAll : function() {
		hering.form.validate();
	    hering.form.validateOnlyNumbers();
	    hering.form.validateOnlyLetters();
	},
	
	validate : function() {
	    $("form button, form input[type=submit]").click(function(event) {
	        // var formSection = $(this).parents('section').first();
	        var formSection = $(this).closest('section, form, div');
	        if(!formSection.find("input")[0]) {
	            formSection = formSection.closest('form');
	        }
	        var radioUnchecked;
	        var errors = new Array();
	        var warningDesc;
	        
	        formSection.find("input:not([type=radio],[type=checkbox],[type=search],[type=submit],[type=hidden], textarea)").each(function(itemIndex) {
	        	var inputW = ( 100 * parseFloat($(this).outerWidth(true)) / parseFloat($(this).parent().outerWidth(true)) ) + '%';
	            var inputM = [ $(this).css("margin-top"), 
	                           $(this).css("margin-right"),
	                           $(this).css("margin-bottom"),
	                           $(this).css("margin-left")
	            ];
	        	var hasError = false;
	        	var errorMessage = 'É necessário preencher este campo';
	            if($(this).is('[class*="'+hering.form.REQUIRED+'"]')) {
	            	/*! Standard input validation */
	            	if(!$(this).is('[class*="'+hering.form.REQUIRED+'-"]')) {
		            	if($(this).val() === "" || $(this).val() === $(this)
		            			.attr("data-placefocus")) {
		            		hasError = true;
			            }
	            	} else {
	            		/*! Email input validation */
		            	if($(this).is('[class*="'+hering.form.REQUIRED_EMAIL+'"]')) {
		            		var email = $(this).val();
		            		if(!isEmailValid(email)) {
		            			hasError = true;
		            			errorMessage = 'Email inválido! Por favor, verifique se '
		                			+ 'digitou o e-mail corretamente';
		            		}
		            	}
		            	
		            	/*! Checkout Payment Details Form custom validation */
		            	if($(this).is('[class*="'+hering.form.REQUIRED_PAYMENT+'"]')) {

		            		var element = $('input[type=radio][name=paymentMode]'
		            				+ '[value=CreditCard]');
		            		if(element && $(element).is(':checked')) {
		            			if(($(this).val() === ""
		            					|| $(this).val() === $(this)
		            						.attr("data-placefocus"))) {
		        				hasError = true;
			            		}
				            	if($(this).is('[class*="'+hering.form.REQUIRED_VOUCHER+'"]')) {
				            		var element = $('input[type=checkbox]'
				            				+ '[name=isVoucherSelected]');
				            		if(element && $(element).is(':checked')
				            				&& ($(this).val() === ""
				            					|| $(this).val() === $(this)
				            						.attr("data-placefocus"))) {
				        				hasError = true;
				            		}
				            	}
		            		}
		            	} else {
		            		if($(this).is('[class*="'+hering.form.REQUIRED_LETTERS+'"]')) {
			            		var element = $('input[type=checkbox]'
			            				+ '[name=isVoucherSelected]');
			            		if($(this).val() === ""
			            				|| $(this).val() === $(this)
			            						.attr("data-placefocus")) {
			        				hasError = true;
			            		}
			            	}
		            		if($(this).is('[class*="'+hering.form.REQUIRED_NUMBERS+'"]')) {
			            		var element = $('input[type=checkbox]'
			            				+ '[name=isVoucherSelected]');
			            		if($(this).val() === ""
		            					|| $(this).val() === $(this)
			            						.attr("data-placefocus")) {
			        				hasError = true;
			            		}
			            	}
		            	}
	            	}
	            	if(hasError) {
	            		$(this).addClass('validation-warning');
		                warningDesc = $(this).parent()
		                		.find('p.validation-warning-desc.input-'
		                				+ itemIndex + ' span');
		                if(!warningDesc[0]) {
		                    $(this)
		                        .wrap('<div class="validation-wrapper"></div>')
		                        .parent()
		                        .css({ "width": inputW, "margin":
		                        	inputM.join(' ')})
		                        .append('<p class="validation-warning-desc input-'
		                        		+ itemIndex + '"><span></span></p>');
		                    warningDesc = $(this).parent()
		                    		.find('p.validation-warning-desc.input-'
	                    				+ itemIndex+' span');
		                }
		                warningDesc.html(errorMessage);
		                errors = ['text'];
	            	} else {
	            		$(this).removeClass('validation-warning');
	                	$(this).parent().find('p.validation-warning-desc.input-'
	                			+ itemIndex).remove();
	            	}
	            }
	        });
	
	        // radio not checked
	        $('input[type=radio]').each(function() {
	            if($(this).is(':checked')) {
	                radioUnchecked = false;
	            }
	        });
	        if(radioUnchecked) {
	            // $(this).addClass('validation-warning');
	            errors = ['radio'];
	        } else {
	           if(errors['radio']) {
	                errors['radio'].splice();
	            }
	        }
	
	        if(errors.length > 0) {
	            event.preventDefault();
	            return false;
	        }
	    });
	},
	
	
	validateOnlyNumbers : function() {
		$('form input[class*="'+hering.form.REQUIRED_NUMBERS+'"]')
		.each(function() {
			$(this).keyup(function(event) {
				/*! Allow: backspace, delete, tab, escape, enter, . keys */
				/*! Allow: Ctrl+A shortcut */
				/*! Allow: home, end, left, right keys */
				if (isKeyAllowed(event)) {
		        	return;
		        }
				$(this).val($(this).val().replace(hering.form.REGEX_LETTERS,''));
			});
		});
	},
	
	
	validateOnlyLetters : function() {
		$('form input[class*="'+hering.form.REQUIRED_LETTERS+'"]')
		.each(function() {
			$(this).keyup(function(event) {
				if (isKeyAllowed(event)) {
		        	return;
				}
				$(this).val($(this).val().replace(hering.form.REGEX_NUMBERS,''));
			});
		});
	}
};


function isEmailValid(email) {
	var emailRegex = new RegExp(hering.form.REGEX_EMAIL);
    return emailRegex.test(email);
}


function isKeyAllowed(event) {
	/*!
	 * Allow: backspace, delete, tab, escape, enter, . (dot) keys
	 * Allow: Ctrl+A shortcut
	 * Allow: home, end, left, right keys
	 */
	return $.inArray(event.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1
			|| (event.keyCode == 65 && event.ctrlKey === true)
			|| (event.keyCode >= 35 && event.keyCode <= 39);
}

//function validationErrorStyle(el, errorMsg, index) {
//    var warningDesc;
//    errorMsg=(errorMsg==="")?'É necessário preencher este campo.':errorMsg;
//
//    el.addClass('validation-warning');
//
//    var validationWrapper = el.parents(".validation-wrapper");
//    if(!validationWrapper[0]) {
//        var inputW = ( 100 * parseFloat(el.outerWidth(true)) / parseFloat(el.parent().outerWidth(true)) ) + '%';
//        var inputM = [
//            el.css("margin-top"),
//            el.css("margin-right"),
//            el.css("margin-bottom"),
//            el.css("margin-left")
//        ];
//
//        el.wrap('<div class="validation-wrapper"></div>');
//        validationWrapper = el.parents(".validation-wrapper");
//        validationWrapper.css({
//                "width": inputW,
//                "margin": inputM.join(' ')
//            })
//    }
//
//    warningDesc = validationWrapper.find('p.validation-warning-desc.input-'+index+' span');
//    if(!warningDesc[0]) {
//        validationWrapper.append('<p class="validation-warning-desc input-'+index+'"><span></span></p>');
//        warningDesc = validationWrapper.find('p.validation-warning-desc.input-'+index+' span');
//    }
//
//    warningDesc.html(errorMsg);
//}

//function validationErrorStyleClear(el, index) {
//    el.removeClass('validation-warning');
//    el.parent().find('p.validation-warning-desc.input-'+index).remove();
//}

