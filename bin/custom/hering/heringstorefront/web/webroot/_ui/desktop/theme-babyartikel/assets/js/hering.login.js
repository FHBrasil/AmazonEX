hering.login = {
		
	insertMask : function(){
		
		if($("input[id=radiopf]").is(":checked")) {
			$("input[name='cpfcnpj']").mask("99999999999");
		} else {
			$("input[name='cpfcnpj']").mask("99999999999999");
		}
		$("input[name='birthday']").mask("99/99/9999");
	},
}

$(document).ready(function(){
	hering.login.insertMask();
	hering.form.validateAll();
});