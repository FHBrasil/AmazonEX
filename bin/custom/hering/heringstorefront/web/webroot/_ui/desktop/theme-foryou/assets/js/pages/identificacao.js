// init
$(document).ready(function() {
	// fancybox
	$('.fancybox').fancybox({
		maxWidth: 600,
		minHeight: 240,
		maxHeight: 250
	});

	// define a maior altura para todas as colunas
	resizeColumns();
	$(window).resize(function() { resizeColumns(); });

	// Ao digitar o numero máximo de caracteres no input, pula para o próximo campo
    jumptToNextInput(".f-row.nascimento, .f-row.fundacao");

    // Ativação de telas de resposta
    $(".modal .active form").submit(function(e) {
    	$(this).parents('.modal').find('section').stop(0,1).fadeOut('slow').promise().done(function() {
    		$(this).parent().find('section.result').stop(0,1).fadeIn('slow');
    	});

   		$(".modal .result .btn").click(function(e) {
   			$.fancybox.close(true);
   			e.preventDefault;
   			return false;
   		})

    	e.preventDefault();
    	return false;
    })

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