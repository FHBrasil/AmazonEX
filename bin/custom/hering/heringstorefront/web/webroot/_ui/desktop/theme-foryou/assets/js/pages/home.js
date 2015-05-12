// init
$(document).ready(function(){
    // validação de formulários (em script.js)
    //formValidation();
    hering.form.validate();
    
    getInstagramPhotos(); // em script.js

    $("#section-secondary #collection .options a").click(function(e) {
    	e.preventDefault();

    	if($(this).hasClass("view-info")) {
    		$("#section-secondary #collection .product-infinite-wrapper .product").toggleClass("product-info");
            $(this).toggleClass("active");
    	}
		
		window.globalSlidersObjects["collection-slider"].reloadSlider(); // recarrega slider
    });

    $("#section-video-player").click(function() {
        var videoContainer = $(this).find(".the-video");
        var videoUrl = videoContainer.data("video-url");
        var videoIframe = videoContainer.find("iframe");

        $(this).toggleClass("video-active");

        if($(this).hasClass("video-active")) {
            if(!videoIframe[0]) {
                videoContainer.append('<iframe width="807" height="454" src="'+videoUrl+'/modestbranding=1&autohide=1&showinfo=0&controls=0&autoplay=1" frameborder="0" allowfullscreen></iframe>');
                videoIframe = videoContainer.find("iframe");
                videoIframe.stop(0,1).fadeIn("slow");
            }
        } else {
            videoIframe.remove();
        }
    })

    $("#bar-secondary li").fancybox({
        afterLoad   : function() {
            this.content = $("#modal-beneficio").html();
        }
    });

    $("#form-text-newsletter").submit(function(){
        $.fancybox.open({
            content: $("#text-newsletter").html()
        });        
        return false;
    });

    $("#collection > .container > div").eq(1).html($(".yCmsContentSlot.span-24").find("#collection-slider"));

});