$(document).ready(function() {
    // fancybox
    $(".fancybox.alterar-foto").fancybox({
        maxWidth: 400
    });

    // Ao digitar o numero máximo de caracteres no input, pula para o próximo campo
    jumptToNextInput("#acc-address-form .cep, #search-bar .pesquisa-data, #acc-dados-form .form-data");

    // define a maior altura para todas as caixas da listagem de endereços
    resizeAddrBoxes();
    $(window).resize(function() { resizeAddrBoxes(); });

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

    // formulário de busca dinâmica
    $('#pesquisa-numero').keyup(function() {
        var typed = $(this).val();
        var list = $("#orders-list > table").find('tr');
        var listItems = list.find('td:first strong');

        $(".sub-item-toggle.active").removeClass("active");
        $(".details").hide();

        if(typed.length > 0) {
            listItems.each(function() {
                $(this).parents("tr").not("tr.sub-item, .details tr").show();
                var val = $(this).text().trim();
                regExp = new RegExp(typed, 'i');
                if(val.match(regExp) === null) {
                    $(this).parents("tr").not("tr.sub-item, .details tr").hide();
                }
            });
        } else {
            listItems.each(function() { $(this).parents("tr").not("tr.sub-item, .details tr").show(); })
        }

    }); 

});

function resizeAddrBoxes() {
    var boxes = $('.page.account-edit-addresses #account-addresses ul li');
    boxes.css("min-height", 1);
    var higherHeight = 0;
    if($(window).width() >= 680) { 
        boxes.each(function() {
            if($(this).outerHeight() > higherHeight) {
                higherHeight = $(this).outerHeight();
            }
        });
        boxes.css("min-height", higherHeight);
    } else {
        boxes.css("min-height", 1);
    }
}