$(document).ready(function() {
    // botão para exibir tabela de sub-itens
    $(".has-sub-item").click(function(e) {
    	e.preventDefault();
    	subItemToggleBtn = $(this).find(".sub-item-toggle");
    	subItemId = $(this).attr("data-sub-item");
    	$(this).parents('table') // sobe até a tag table
    		.find(".sub-item-"+subItemId+" div.details")
    		.slideToggle(500);
    	subItemToggleBtn.toggleClass("active");

    });
    
    // validação de formulários (em script.js)
    hering.form.validateAll();

    /*$(".has-sub-item").click(function(){
        $(".sub-item div").slideToggle(500);
        return false;
    });*/

    // Ao digitar o numero máximo de caracteres no input, pula para o próximo campo
    $("#search-bar .pesquisa-data input").keyup(function() {
    	var maxlength = $(this).attr("maxlength");
    	if($(this).val().length >= maxlength) {
    		$(this).next("input").focus();
    	}
    });

    $("#search-bar form").submit(function(e) {
    	var formClass = $(this).attr('class');
        var errors = new Array();
        var fieldName;
        var radioUnchecked = true;
        var warningDesc;

    	$(this).find("input").each(function(itemIndex) {
    		if($(this).val() === "") {
                $(this).addClass('validation-warning');
                warningDesc = $(this).parent().find('p.validation-warning-desc.input-'+itemIndex+' span');
                if(!warningDesc[0]) {
                    var inputW = $(this).outerWidth(true);
                    var inputM = [
                        $(this).css("margin-top"),
                        $(this).css("margin-right"),
                        $(this).css("margin-bottom"),
                        $(this).css("margin-left")
                    ];
                    $(this)
                        .wrap('<div class="validation-wrapper"></div>')
                        .parent()
                        .css({
                            "width": inputW,
                            "margin": inputM.join(' ')
                        })
                        .append('<p class="validation-warning-desc input-'+itemIndex+'"><span></span></p>');
                    warningDesc = $(this).parent().find('p.validation-warning-desc.input-'+itemIndex+' span');
                }

                warningDesc.html('É necessário preencher este campo');
                errors = ['text'];
            } else {
                $(this).removeClass('validation-warning');
                $(this).parent().find('p.validation-warning-desc.input-'+itemIndex).remove();
            }
        })

        if(errors.length > 0 ) {
            e.preventDefault();
        }
    });
});