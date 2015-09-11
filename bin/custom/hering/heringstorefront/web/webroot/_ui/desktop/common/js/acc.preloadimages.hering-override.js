ACC.images.loadImages = function() {
	var productCodes = [];
	jQuery.each($(".productDetailsHolder").not(".visited"), function() {
		productCodes.push($(this).find(":hidden[name='productCode']").val());
		$(this).addClass('visited');
	});
	
	if (productCodes.length > 0) 
	{
		$.ajaxSetup({
			cache: false
		});
		
		var i, j, temparray, chunk = 6;
		for (i = 0, j = productCodes.length; i < j; i += chunk) 
		{
			temparray = productCodes.slice(i, i + chunk);
			
			$.ajax({
				url : ACC.config.contextPath + '/search/images',
				data: {products: temparray},
				context : document.body
			})
			.done(function(json) {
				Preload.appendJson(json);
				
				if (ACC.images.jsonAppended == "" && ACC.images.isFirstPageReloaded == false) 
				{
					window.location.reload(true);
					ACC.images.isFirstPageReloaded = true;
				}
			});
		}
	}
};
QuickViewController.changeImage = function() {
	try {
		var parent = LoaderUtil.getProductDetailsHolder();
		var img = $(parent).find("img");
	
		var loadingImage = $("#loading");
		if(!loadingImage.length) {
			loadingImage = $('<img id="loading" />');
			loadingImage.css('position', 'absolute');
			loadingImage.css('left', Math.floor(img.width() / 2) - 16);
			loadingImage.css('top', Math.floor(img.height() / 2) - 16);
			loadingImage.css('width', '32px');
			loadingImage.css('height', '32px');
		}
		loadingImage.attr('src', '/store/_ui/desktop/common/images/ajax-loader.gif');
		img.after(loadingImage);
		loadingImage.show();
		$([Product.img]).preload(function(){
			$("#loading").remove();
			img.fadeOut(function(){
				img.attr("src", Product.img).fadeIn();
			});
		});
	} catch(err) {
	}
};
QuickViewController.loadSizes = function() {
	var sizeArray = Product.sizes;
	var code = Product.code;
	var parent = LoaderUtil.getProductDetailsHolder();

	if (sizeArray.length == 0) {
		return;
	}
	$(parent).find('.tamanhos');
	$(parent).find('.tamanhos li a').each(function(j) {
		$(this).attr('class', 'out-of-stock');
		for (i = 0; i < sizeArray.length; i++) {
			if ($(this)[0].innerText == sizeArray[i] || $(this).text() == sizeArray[i]) {
				$(this).attr('class', '');
				break;
			}
		}
	});
	$(parent).find('.tamanhos li a:not(.out-of-stock)').first().click();
}