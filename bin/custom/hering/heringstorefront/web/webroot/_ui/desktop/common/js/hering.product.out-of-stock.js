hering.stockOut = {
		
	thread: 0,
	maxThreads: 3,
		
	requestMonitor: function(){
		
		$(".esgotado-form").submit(function(e){
			
			e.preventDefault();
			$(".esgotado-form button").hide();
			hering.stockOut.submitForm();
		});
	},
	
	submitForm: function(){
		
		$.ajax({
			url: "/store/pt/notifyme/notifyme-register",
			type: "POST",
			dataType: "json",
			data: $(".esgotado-form").serialize()
		})
		.done(function(data){ hering.stockOut.displayReturnMessage(data.message); })
		.error(function(){ hering.stockOut.failControl() });
		
		hering.stockOut.thread++;
	},
	
	failControl: function(){
		
		if( hering.stockOut.maxThreads >= hering.stockOut.thread ) 
			hering.stockOut.displaySubmitErrorMessage();
		
		else hering.stockOut.submitForm();
	},
	
	displayReturnMessage: function(message){
		
		$(".esgotado-form input").attr("value","");
		showToaster(message);
		hering.stockOut.thread = 0;
		$(".esgotado-form button").show();
	},
	
	displaySubmitErrorMessage: function(){
		
		showToaster("Ops, não conseguimos processar o seu registro agora, tente mais tarde novamente");
		hering.stockOut.thread = 0;
		$(".esgotado-form button").show();
	}
}

$(document).ready(function(){ hering.stockOut.requestMonitor(); });