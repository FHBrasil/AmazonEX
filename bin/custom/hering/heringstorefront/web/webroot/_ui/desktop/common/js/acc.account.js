ACC.account = {

	init : function() {
		ACC.account.addresPostCode_onFocus_onBlur();
	},

	addresPostCode_onFocus_onBlur : function() {
		$(document).on('focus', 'input[id="address.postcode"]',
				function() {
					onlyNumbers($(this));
					$(this).attr('maxlength', '8');
				});
		$(document).on('blur','input[id="address.postcode"]', function() {
			var postcode = $(this).val();
			$('#loadingAddress').show();
			$.ajax({
				url : '/store/my-account/get-by-zipcode',
				type : 'GET',
				data : 'zipcode=' + postcode,
				dataType : 'json',
				success : function(data) {
					$('#loadingAddress').hide();
					if (data != null) {
						var obj = jQuery.parseJSON(data);
						if (obj) {
							var street = obj.logradouro;
							var city = obj.cidade;
							var state = "BR-"+obj.estado;
							var district = obj.bairro;
							$('input[id="address.district"]').val(district);
							$('input[id="address.neighborhood"]').val(district);
							$('input[id="address.line1"]').val(street);
							$('select[id="address.state"]').val(state);
							$('select[id="address.regionIso"]').val(state);
							$('input[id="address.townCity"]').val(city);
						}
					}
				},
				error : function() {
					$('#loadingAddress').hide();
				}
			});
		});
	}
}

$(document).ready(function() {

	ACC.account.init();
});