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
		/*bingFancyboxOnNewsletter('Por favor preencha as informações corretamente.'); */
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
		beforeSend: function(){
			hideFeedback();
		},
		success : function(data) {
			/*showFeedback(data);*/
			var html = '<div class="fancybox-content" style="display: block;"><header><h1>Newsletter</h1></header><div><p>'+data+'</p></div></div>';
			bingFancyboxOnNewsletter(html);
			/*E-mail já cadastrado*/
		},
		error : function(data) {
			/*bingFancyboxOnNewsletter('Por favor valide as informações abaixo:');*/
		}
	});
	
	return false;
});

function validateEmail(email) { 
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
} 

function hideFeedback() {
	var parentForm = $("div.newsletters").find("form");
	var feedbackContainer = $(parentForm).find('#newsletterFeedback');
	feedbackContainer.hide();
}

function showFeedback(data) {
	var parentForm = $("div.newsletters").find("form");
	var feedbackContainer = $(parentForm).find('#newsletterFeedback');
	
	if(!feedbackContainer.length) {
		feedbackContainer = $('<div id="newsletterFeedback" />');
	}
	feedbackContainer.html(data);
	
	parentForm.prepend(feedbackContainer);
	feedbackContainer.show();
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

$(document).ready(function() {
	hering.form.validate();
});