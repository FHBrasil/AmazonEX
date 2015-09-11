$(document).ready(function(){
	$('.fancybox').click(function(){
		$.fancybox({ 
			content : $( $(this).attr("href") ).html(),
			maxWidth: 900,
			minWidth: 320,
			padding: 20
		});
		return false;
	});
});