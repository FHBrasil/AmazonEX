var newsletter = {

	gender : "FEMALE"

}

$("div.newsletters").find("form").find("button").click(function(){ 
	var newsletterForm = $(this).closest("form");
	var name = $(newsletterForm).find("input[name=name]").val();
	var email = $(newsletterForm).find("input[name=email]").val();
	var opcaoSexo = $(newsletterForm).find("input[name=opcaoSexo]").val();
	var baseStore = $(newsletterForm).find("input[name=baseStore]").val();
	
	if(!name || !email || !validateEmail(email)) {
		/*bingFancyboxOnNewsletter('Por favor preencha as informações corretamente.');*/
		return false;
	}
	
	$.ajax({
		async : true,
		url : ACC.config.contextPath + '/pt/newsletter/newsletter-register',
		data : {
			name : name,
			email : email,
			opcaoSexo : newsletter.gender,
			baseStore : baseStore
		},
		dataType : 'json',
		type : 'POST',
		success : function(data) {
			var html = '<h1>Newsletter</h1><p>'+data+'</p>';
			bingFancyboxOnNewsletter(html);
		},
		error : function(data) {
			var html = '<h1>Newsletter</h1><p>E-mail já cadastrado</p>';
			bingFancyboxOnNewsletter(html);
		}
	});
	
	return false;
});

function validateEmail(email) { 
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

function bingFancyboxOnNewsletter(data) {
	$.fancybox({
		maxWidth: 700,
		minWidth: 320,
		padding: 20,
		content: data
	});
}

$("div.newsletters").find("form").on( "click", "input[type=radio]", function() {
	selectRadio();
});

function selectRadio(){
	newsletter.gender = $( "input:checked" ).val();
}