////start key up
//$('#pesquisa-numero').keyup(function() {
//	//get value of input
//	var nomeFiltro = $(this).val().toLowerCase();
//	//for each in table
//	$('table tbody').find('.has-sub-item').each(function() {
//		//get value of first column
//		var conteudoCelula = $(this).find('td:first').text();
//		//compare
//		var corresponde = conteudoCelula.toLowerCase().indexOf(nomeFiltro) >= 0;
//		//button (+)(-)
//		var subItemToggleBtn = $(this).find(".sub-item-toggle");
//		//id of item was sub item
//		var subItemId = $(this).attr("data-sub-item");
//		//if true show, else hide
//		$(this).css('display', corresponde ? '' : 'none');
//		//get class active
//		var active = $(this).find(".sub-item-toggle").hasClass('active');
//		//if active = toggle in sub item and back button for (+)
//		if(active){
//			$(this).parents('table').find(".sub-item-"+subItemId+" div.details").slideToggle(500);
//			subItemToggleBtn.toggleClass("active");
//		}
//	});
//});
