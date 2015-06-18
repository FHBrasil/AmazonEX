hering.cart = {
	
	updateCartQuantity: function(){

		var entryAtual = "";
		
		 $('#cart table tbody tr, .list150608 li').hover(function(a){
			entryAtual = ($(this).attr('id'));
			
			$("#updateCartForm" + entryAtual + " .control-down, .control-up").on("click",function(){
				hering.cart.updateForm(entryAtual);
			});
			
			$("#updateCartForm" + entryAtual + " input[type='number']").on("focusout",function(){
				hering.cart.updateForm(entryAtual);
			});
		});
	},
	
	bindPostcodeFormat: function(){
		$("input[name='postalCode']").mask("99999999");
	},
	
	updateForm: function(reference){
		
		var productQuantity = $("#updateCartForm" + reference + " input[type='number']").val();
		$('#updateCartForm' + reference + ' input[name="quantity"]').val(productQuantity);
		$("#updateCartForm" + reference).submit();
	},
	
	removeProduct: function(reference){
		
		var productQuantity = $("#updateCartForm" + reference + " input[type='number']").val();
		$('#updateCartForm' + reference + ' input[name="quantity"]').val(0);
		$("#updateCartForm" + reference).submit();
	},
	
	insertMask : function(){
		
		$("input[name='cep_01']").mask("99999");
		$("input[name='cep_02']").mask("999");
		voucherValidate();		
	},
};

function cepFunction() {
	var cep = document.getElementById("cep-01").value + document.getElementById("cep-02").value;
	document.getElementById("cepCode").value = cep;
	//document.getElementById("calculateFrete").submit();
}
//verifica se não é um vale crédito
function voucherValidate(){
	$("#cupom-desconto").keyup(function() {
		var text = $(this).val();
		if (text.toLowerCase() == "vc_"
			|| text.toLowerCase().substr(0,3) == "vc_") {
			$(this).val("");
		}
	})	
}

$(document).ready(function(){
	
	hering.cart.updateCartQuantity();
	hering.cart.bindPostcodeFormat();
	hering.cart.insertMask();
});