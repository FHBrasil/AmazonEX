babyartikel.checkoutAmazon = {
	
	updateCartQuantity: function(){

		var entryAtual = "";
		
		 $('.list150608 li').hover(function(a){
			entryAtual = ($(this).attr('id'));
			
			$("#updateCartForm" + entryAtual + " .control-down, .control-up").click(function(){
				babyartikel.checkoutAmazon.updateForm(entryAtual);
			});
			
			$("#updateCartForm" + entryAtual + " input[type='number']").focusout(function(){
				babyartikel.checkoutAmazon.updateForm(entryAtual);
			});
		});
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
	
};

$(document).ready(function(){	
	babyartikel.checkoutAmazon.updateCartQuantity();
});