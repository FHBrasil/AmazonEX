// init
$(document).ready(function() {
    // Ao digitar o numero máximo de caracteres no input, pula para o próximo campo
    jumptToNextInput(".calculate-freight");

    $(".calculate-freight").submit(function() {
    	if($("#cep", this).val().length < 9){
    		$("#cep", this).addClass("validation-warning");
    		return false;
    	}

        // Apagar ao implementar 
        $(this).html("<h3>Calcule o Frete</h3><div>Frete Grátis - Entrega em até <strong>4 dias úteis</strong><br><br><span class='btn' onclick='retornaCalculadorFrete()' style='cursor: pointer'>Trocar CEP</span></div>");
        return false;     

    });

});

//Apagar na implementação
function retornaCalculadorFrete(){
    $(".calculate-freight").html('<h3>Calcule o Frete</h3><div><label>Insira seu CEP:</label><input type="text" maxlength="9" id="cep" class="only-number has-special-char form-cep" name="cep"><button class="btn-ok btn">ok</button></div>');
}