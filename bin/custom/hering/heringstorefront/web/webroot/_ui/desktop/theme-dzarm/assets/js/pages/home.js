// init
$(document).ready(function(){
    // validação de formulários (em script.js)
    formValidation();
    hering.form.validate();

    
    $('.fancybox[href*="modal-"]').fancybox({maxWidth: 700, minWidth: 320, padding: 20 });

//    $("div.newsletters").find("form").find("button").click(function(){ 
//        var newsletterForm = $(this).closest("form");
//        var name = $(newsletterForm).find("input[name=name]").val();
//        var email = $(newsletterForm).find("input[name=email]").val();
//        var opcaoSexo = $(newsletterForm).find("input[name=opcaoSexo]").val();
//        
//        if(!name || !email || !checkEmail(email)) {
//            return false;
//        } else {
//            $.fancybox(
//                '<h1>Newsletter</h1><p>Cadastrado com sucesso!</p>'
//            );
//            return false;
//        }
//        
//        return false;
//    });

    // script de requisição de fotos do Instagram
    jQuery.ajax({
        type: "GET",
        dataType: "jsonp",
        cache: false,
        url: "https://api.instagram.com/v1/users/185216157/media/recent/?access_token=215480059.5b9e1e6.e81ec6b9fef043afb40c6f339e92de89",
        success: function(data) {
            var homeList = $("#section-social .instagram-list li");
            for (var i = 0; i < homeList.length; i++) {
                var selectedItem = homeList.eq(i);
                var itemAnchor = selectedItem.find('a');
                var itemImage = selectedItem.find('img');

                itemAnchor.attr({
                    "href": data.data[i].link,
                    "title": data.data[i].caption.text
                });
                itemImage.attr({
                    "src": data.data[i].images.low_resolution.url,
                    "width": selectedItem.css("width"),
                    "height": selectedItem.css("height"),
                    "alt": data.data[i].caption.text
                });
            }
        }
    });
    
    /* titulos no hero */
    //$("#main-slider").prepend('<h1 class="absolute-title"><img src="_ui/desktop/theme-dzarm/assets/images/title-hero-absolute.png"></h1><ul class="absolute-cta"><li><a href="/store/pt/search?q=:relevance:gender:FEMALE"><img src="_ui/desktop/theme-dzarm/assets/images/link-fem.png"></a></li><li><a href="/store/pt/search?q=:relevance:gender:MALE"><img src="_ui/desktop/theme-dzarm/assets/images/link-masc.png"></a></li></ul>');
	
	$("#main-wrapper .section1").eq(2).find(".container > .right").html($("#main-wrapper .section1").eq(3));
    
});