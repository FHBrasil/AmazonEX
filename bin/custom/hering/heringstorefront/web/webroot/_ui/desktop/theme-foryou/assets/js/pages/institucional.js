//Lookbook
(function(e,t,n){function i(e){return e}function s(e){return decodeURIComponent(e.replace(r," "))}var r=/\+/g;var o=e.cookie=function(r,u,a){if(u!==n){a=e.extend({},o.defaults,a);if(u===null){a.expires=-1}if(typeof a.expires==="number"){var f=a.expires,l=a.expires=new Date;l.setDate(l.getDate()+f)}u=o.json?JSON.stringify(u):String(u);return t.cookie=[encodeURIComponent(r),"=",o.raw?u:encodeURIComponent(u),a.expires?"; expires="+a.expires.toUTCString():"",a.path?"; path="+a.path:"",a.domain?"; domain="+a.domain:"",a.secure?"; secure":""].join("")}var c=o.raw?i:s;var h=t.cookie.split("; ");for(var p=0,d=h.length;p<d;p++){var v=h[p].split("=");if(c(v.shift())===r){var m=c(v.join("="));return o.json?JSON.parse(m):m}}return null};o.defaults={};e.removeCookie=function(t,n){if(e.cookie(t)!==null){e.cookie(t,null,n);return true}return false}})(jQuery,document)
removeDuplicated = function(a, s){
    var p, i, j;
    if(s) for(i = a.length; i > 1;){
        if(a[--i] === a[i - 1]){
            for(p = i - 1; p-- && a[i] === a[p];);
            i -= a.splice(p + 1, i - p - 1).length;
        }
    }
    else for(i = a.length; i;){
        for(p = --i; p > 0;)
            if(a[i] === a[--p]){
                for(j = p; p-- && a[i] === a[p];);
                i -= a.splice(p + 1, j - p).length;
            }
    }
    return a;
};


var lookbooks = "";
function atualizaLookbook() {
	var lookbook_cookies = "" + $.cookie("lookbook");
	lookbook_cookies = lookbook_cookies.split(",");
	lookbook_cookies = removeDuplicated(lookbook_cookies);

	$("#looks_looks_selecionados").html(" ");

	for (t = 0; t < lookbook_cookies.length - 1; t++) {
		var div_lookbook_cookies = "[data-cookie-lookbook='"+lookbook_cookies[t]+"']";
		var img_lookbook_cookies = $(div_lookbook_cookies+" > img").attr("src");
		$("#looks_looks_selecionados").append('<div class="product only-link" data-cookie-lookbook="'+lookbook_cookies[t]+'"><img src="'+img_lookbook_cookies+'" width="296" height="396"><i class="close-lookbook" onclick="fechaLookbook('+lookbook_cookies[t]+')"></i></div>');
	}

}
function fechaLookbook(id_lookbook){

	id_lookbook = id_lookbook.toString();

	lookbook_cookies = $.cookie("lookbook").split(",");
		delete lookbook_cookies[lookbook_cookies.indexOf(id_lookbook)];

		$.cookie("lookbook", lookbook_cookies, {
			expires: 3600*30
		});


		atualizaLookbook();
}
$(document).ready(function() {

	atualizaLookbook();

	/*$('.page.termos .terms .term-title').click(function() {
		$(this).toggleClass("opened");
		$(this).siblings('.term-content').slideToggle("medium");
	});*/

	// fotos do instagram
	getInstagramPhotos();

	$('.institutional-links.full > ul > li > a').click(function(e) {
		e.preventDefault();

		$(this).parent().parent().find("li > div").slideUp("medium");
		$(this).siblings('div').slideToggle("medium");
	});

	// sliders de looks e dicas
	// exibe o slider selecionado no menu
	$('#looks-dicas-sliders ul li a').click(function(e){
		e.preventDefault();
		
		var selectedSlider = $(this).attr('rel');
		$('#looks-dicas-sliders ul li').removeClass('active');
		$(this).parent().addClass('active');
		// esconde slider anterior e exibe o escolhido, recarregando o slider escolhido quando aparecer
		$('#looks-dicas-sliders .product-infinite-wrapper').stop(0,0).fadeOut("slow").promise().done(function() {
			$(selectedSlider).stop(0,0).fadeIn("slow");
			window.globalSlidersObjects[selectedSlider.replace("#", "")].reloadSlider();
		});
	});

	// ação padrão de botão avise-me em section-mini
	$('.section-mini-content .avise-me').click(function(e) {
		e.preventDefault();
		$(this).parents('.product-mini').find('.esgotado-form').fadeIn("medium");
	});

	// Ação para marcar/desmarcar todos os itens
	$(".section-mini-content .uncheck-all").on("change", function(){
		$(".product-mini.with-checkbox input[type='checkbox']").prop("checked",$(this).prop("checked"));
	});
	// Ação que verifica se precisa marcar ou desmarcar itens
	$(".product-mini.with-checkbox input[type='checkbox'], .uncheck-all").on("change", function(){
		if($(".product-mini.with-checkbox input[type='checkbox']").is(":checked")){
			// Ainda existem produtos selecionados
			$(".check-uncheck span").text("Desmarcar todos");
			$(".uncheck-all").prop("checked",true);
		}
		else {
			$(".check-uncheck span").text("Marcar todos");
			$(".uncheck-all").prop("checked",false);
		}
	});
	$(".product-mini.with-checkbox img").first().click(function(){
		//Clica no input também	
	});


	//Adicionar ao lookbook

	$(".add_looks").click(function (){
		if($.cookie("lookbook") != "" && $.cookie("lookbook") != null){
			if($.inArray($(this).parents("[data-cookie-lookbook]").attr("data-cookie-lookbook"), $.cookie("lookbook").split(",")) == -1 && $.cookie("lookbook").split(",").length > 0){
				lookbooks = $.cookie("lookbook");
				lookbooks += $(this).parents("[data-cookie-lookbook]").attr("data-cookie-lookbook") + ",";

				$.cookie("lookbook", lookbooks, {
					expires: 3600*30
				});

				atualizaLookbook();
			}
		}
		else {
			lookbooks = $.cookie("lookbook");
			lookbooks = $(this).parents("[data-cookie-lookbook]").attr("data-cookie-lookbook") + ",";

			$.cookie("lookbook", lookbooks, {
				expires: 3600*30
			});

			atualizaLookbook();
		}
	});

	
});