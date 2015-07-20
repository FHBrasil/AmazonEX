$(document).ready(function(){
	$(".hotsite-blackfriday-2014 form").submit(function(e){
		
		e.preventDefault();
		
		$.ajax({ 
		 url: "//" + location.host + "/store/pt/newsletter/newsletter-register" , 
		 dataType: "text",
		 type: "POST",
		 data : $(".hotsite-blackfriday-2014 form").serialize()
		})
		.done(function(data) {
			
//			var content = '<div class="lightbox_colorbox"><div class="lightbox-overflow"></div><div class="popup-newsletter lightbox-box"><div class="lightbox-wrap"><div class="lightbox-content"><div class="lightbox-image"><img width="478px" height="270px" src="/store/_ui/desktop/common/images/popup-newsletter.jpg"></div></div></div></div></div>';
	            
	        $("#cboxLoadedContent").html(content);
			
	        $(".hotsite-blackfriday-2014 form input").attr("value","");
			
			$.colorbox({inline:true, href:".lightbox_colorbox", width:"545px",height:"270px"});
		})
	});
});