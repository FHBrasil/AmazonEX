// init
$(document).ready(function() {
	// fancybox
	$('.fancybox').fancybox({
		maxWidth: 585,
		minHeight: 258,
		maxHeight: 260
	});

    // validação de formulários (em script.js)
	hering.form.validateAll();
	
	$('.col-2.column > div > div > section').removeClass();

	// define a maior altura para todas as colunas
	resizeColumns();
	$(window).resize(function() { resizeColumns(); });

	// Ao digitar o numero máximo de caracteres no input, pula para o próximo campo
    jumptToNextInput("#cadastro-nascimento-dia, #cadastro-nascimento-mes");

    if($('#radiopf').is(":checked"))
    {
    	$('form.pf').css('display','block');
    	$('form.pj').css('display','none');
    }
    else
    {
    	$('form.pf').css('display','none');
    	$('form.pj').css('display','block');
    }
    
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
	columns.css("min-height",0);
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