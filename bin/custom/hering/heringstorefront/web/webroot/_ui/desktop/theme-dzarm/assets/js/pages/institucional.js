(function($){
	$(document).ready(function() {
		$(".fancybox").fancybox({
			openEffect	: 'none',
			closeEffect	: 'none'
		});

		// sliders de looks e dicas
		// exibe o slider selecionado no menu
		$('.pager-blue ul li a').click(function(e){
			e.preventDefault();
			
			var selectedSlider = $(this).attr('rel');
			$('.pager-blue ul li a').removeClass('active');
			$(this).addClass('active');

			// esconde slider anterior e exibe o escolhido, recarregando o slider escolhido quando aparecer
			$('#looks-dicas-sliders .product-infinite-wrapper').stop(0,0).fadeOut("slow").promise().done(function() {
				$(selectedSlider).stop(0,0).fadeIn("slow");
				window.globalSlidersObjects[selectedSlider.replace("#", "")].reloadSlider();
			});
		});

		function updateCheckAllLabel(){
			if ( $('.check-all').is(':checked')){
				$('.check-all-container span').html('Desmarcar todos');
			} else {
				$('.check-all-container span').html('Marcar todos');
			}
		};

		function enableDisableCheckBoxAndBuyButton(){
			//verify if there's any other item clicked
			var oneOrMoreChecked = false;
			var allChecked = true;
			var noneChecked = true;
			$('.check-item').each(function(){
				if (this.checked) {
					oneOrMoreChecked = true;
					noneChecked = false;
				} else {
					allChecked = false;
				}
			});

			if (oneOrMoreChecked){
				$('.btn.comprar').removeClass('disabled');
			} else {
				$('.btn.comprar').addClass('disabled');
			}

			if ((noneChecked || oneOrMoreChecked) && !allChecked){
				$('.check-all').attr('checked', false).prop('checked', false);
			} else if (allChecked){
				$('.check-all').attr('checked', true).prop('checked', true);
			}

			updateCheckAllLabel();
		}

		$('.check-all').click(function(){
			$('.check-item').prop('checked', this.checked).attr('checked', this.checked);
			enableDisableCheckBoxAndBuyButton();
		});

		$('.check-item').click(enableDisableCheckBoxAndBuyButton);
		enableDisableCheckBoxAndBuyButton(); //run on init

		$('.btn.comprar').click(function(){
			return !($(this).is('.disabled'));
		})

		// script de requisição de fotos do Instagram
	    jQuery.ajax({
	        type: "GET",
	        dataType: "jsonp",
	        cache: false,
	        url: "https://api.instagram.com/v1/users/187043677/media/recent/?access_token=215480059.5b9e1e6.e81ec6b9fef043afb40c6f339e92de89",
	        success: function(data) {
	            var photoList = $(".instagram .instagram-list li");
	            for (var i = 0; i < photoList.length; i++) {
	                var selectedItem = photoList.eq(i);
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

	});		

})(jQuery);
