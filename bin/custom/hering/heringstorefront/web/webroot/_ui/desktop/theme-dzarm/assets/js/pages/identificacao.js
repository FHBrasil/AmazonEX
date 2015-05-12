// init
$(document).ready(function() {
	// fancybox
	$('.fancybox').fancybox({
		maxWidth: 600
	});

    // validação de formulários (em script.js)
    //formValidation();
	hering.form.validateAll();

	// define a maior altura para todas as colunas
	resizeColumns();
	$(window).resize(function() { resizeColumns(); });

	// Ao digitar o numero máximo de caracteres no input, pula para o próximo campo
    $("#cadastro-nascimento-dia, #cadastro-nascimento-mes").keyup(function() {
    	var maxlength = $(this).attr("maxlength");
    	if($(this).val().length >= maxlength) {
    		$(this).next("input").focus();
    	}
    });

    $('#radiopf').click(function(){
    	$('form.pf').css('display','block');
    	$('form.pj').css('display','none');
    });

    $('#radiopj').click(function(){
    	$('form.pf').css('display','none');
    	$('form.pj').css('display','block');
    });

})

function resizeColumns() {
	var columns = $('#main-wrapper .column');
	var higherHeight = 0;
	if($(window).width() >= 880) { 
		columns.each(function() {
			if($(this).outerHeight() > higherHeight) {
				higherHeight = $(this).outerHeight();
			}
		});
		columns.css("min-height", higherHeight);
	} else {
		columns.css("min-height", 0);
	}
}