// init
$(document).ready(function() {
    // Ao digitar o numero máximo de caracteres no input, pula para o próximo campo
    $(".calculate-freight input").keyup(function() {
    	var maxlength = $(this).attr("maxlength");
    	if($(this).val().length >= maxlength) {
    		$(this).next("input").focus();
    	}
    });
});