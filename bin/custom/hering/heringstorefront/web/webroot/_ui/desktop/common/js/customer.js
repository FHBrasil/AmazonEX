$(document).on("click", ":checkbox", 
		function ()
		{
			var element = $(this);
			var bases = $("#baseStore");
			
			if(element.attr("checked") == "checked") {
				if(bases.val().search(element.attr("id")) < 0)
				{
					bases.val(bases.val()+","+element.attr("id"));
				}
			}
			else {
				bases.val(bases.val().replace(","+element.attr("id"), ""));
			}
		});

$(document).ready(function() {
	var bases = $("#baseStore");	 
	if(!bases || (typeof(bases.val()) === "undefined") ){
		return;
	}
	var arrayBases = bases.val().split(",");
	
	var elements = $(":checkbox");
	
	for(var i = 0; i < elements.length; i++) {
		$('input[id='+elements[i].id+']').attr('checked', false);
	}
	
	for(var i = 0; i < arrayBases.length; i++) {
		if(arrayBases[i] != "") {
			for(var j = 0; j < elements.length; j++) {
				if(arrayBases[i] == elements[j].id) {
					$('input[id='+elements[j].id+']').attr('checked', true);
				}
			}
		}		
	}
});