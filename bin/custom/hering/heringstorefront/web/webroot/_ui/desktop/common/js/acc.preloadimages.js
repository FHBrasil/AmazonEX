ACC.preloadimages = {

};

/*! O método $(document).ready deste javaScript ACC.preloadimages.js está sendo executado dentro do método 
 * $(document).ready dos arquivos ACC.search.js e ACC.category.js, para evitar mais de uma execução do 
 * ACC.preloadimages.js, a fim de evitar mais de uma requisição GET no servidor para a url "/search/images" . */
$(document).ready(function() {
	
/*!	ACC.images.loadImages(); */
	
});

var pagination = 0;

ACC.images = {
	jsonAppended : "",
	isFirstPageReloaded : false,

	loadImages : function(){
		
		if(true) {
			return;
		}
		
		ACC.images.bindVariantSelectorHandler();
		
	    var productCodes = [];
	    jQuery.each($(".productDetailsHolder").not(".visited"), function() {
	    	productCodes.push($(this).find(":hidden[name='productCode']").val());
	    	$(this).addClass('visited');
	    });
	    
	    if (productCodes.length > 0) 
	    {
    	    $.ajax({
    	    	url : ACC.config.contextPath + '/search/images',
    	    	data: {products: productCodes}
    	    })
    	    .done(function(json) {
    	    	Preload.appendJson(json);
    	    	
    	    	if (ACC.images.jsonAppended == "" && ACC.images.isFirstPageReloaded == false) 
    	    	{
    	    		window.location.reload(true);
    	    		ACC.images.isFirstPageReloaded = true;
    	    	}
    	    });
	    	
	    	$(".productDetailsHolder").hover(function() {
	    		var alreadyHovered = $(this).find('.InStock-1-Selected').first();
	    		if(alreadyHovered.length <= 0) {
	    			$(this).find('.styleVariantEventHandler').first().click();
	    		}
	    		else {
	    			$(this).unbind('hover');
    			}
	    	});
	    }
	},
	
	preloadImagesAgain : function(currentPage){
		
		if(true) {
			return;
		}
		
		var url = this.getUrl();
		if (url.indexOf("?q") < 0 ) {
			var urlText = url.slice(url.indexOf("text=") + 5);
			
			$.ajax({
		        url : ACC.config.contextPath + '/search	',
		        data : { "page" : currentPage,
		        		 "text" : urlText
		        }
			
		    }).done(function() {
		    		ACC.images.loadImages();
		    });
			
		} else {
			var searchQuery = url.slice(url.indexOf("q=") + 2);
			if (url.indexOf("/search") < 0 ) {
				var path = this.getPath();
				var code = path.slice(path.indexOf("/c/") + 3);
				$.ajax({
			        url : ACC.config.contextPath + '/c/' + code + '?q=' + searchQuery + '&page=' + currentPage,
			    }).done (function() {
			    		ACC.images.loadImages();
	    		});
				
			} else {
				$.ajax({
			        url : ACC.config.contextPath + '/search?q=' + searchQuery + '&page=' + currentPage,
			        
			    }).done (function() {
			    		ACC.images.loadImages();
	    		});
			}
		}
	},
	
	bindVariantSelectorHandler : function() {
       
		$('.sizes-box li').attr('class', 'OutOfStock-1');
        
		$(".styleVariantEventHandler").click(function() {
			var codeData = $(this).find(":hidden[name='codeData']");
			var codes = codeData.val().split("-");
			var parent = codeData.closest('.productDetailsHolder');
	        
			$(this).siblings().removeClass('selected');
			$(this).addClass("selected");
			
			QuickViewController.onHover(parent, codes[1]);
		});

		$(".sizeVariantEventHandler li").click(function() {
			if ($(this).attr('class') == "InStock-1") {
				var parent = $(this).closest('.productDetailsHolder');
				var baseCode = parent.find('.styleVariantEventHandler.selected').attr('data-variantcode');
				var size = $(this).text();
				var background = parent.find('.styleVariantEventHandler.selected').css("background-color");
		        var color = Util.rgbToHex(background);
				$(this).siblings('.InStock-1-Selected').attr('class', 'InStock-1');
				$(this).attr('class', 'InStock-1-Selected');
				
		        console.log('basecode:' + baseCode);
			    QuickViewController.getSizeUrl(parent, size, baseCode, color);
			}
		});
	},
	
	getUrl : function(){
		var url = window.location.href;
		var newUrl;
		var id;
		if( url.indexOf('&page=') > -1 ){
			id = url.indexOf('&page=');
			newUrl = url.substring(0, id);
		} 
		else if( url.indexOf('&amp;page=') > -1 ) {
			id = url.indexOf('&amp;page=');
			newUrl = url.substring(0, id);
		} 
		else {
			newUrl = url;
		}
		
		return newUrl;
	},
	
	getPath : function(){
		var path = window.location.pathname; 
		var newPath;
		var id;
		if( path.indexOf('&page=') > -1 ){
			id = path.indexOf('&page=');
			newPath = path.substring(0, id);
		} 
		else if( path.indexOf('&amp;page=') > -1 ) {
			id = path.indexOf('&amp;page=');
			newPath = path.substring(0, id);
		} 
		else {
			newPath = path;
		}
		
		return newPath;
	}
};

var Preload = {
    
	imageList : "",
    
    setImageList : function(arrayImage) {
        this.imageList = arrayImage;
    },
    
    appendJson : function(json) {
    	if(json != "") {
	    	if( ACC.images.jsonAppended != "" )
			{
	    		ACC.images.jsonAppended = ACC.images.jsonAppended.replace( "}", "" );
	    		json = json.replace( "{", "" );
	    		ACC.images.jsonAppended += "," + json;
			}
	    	else
			{
    			ACC.images.jsonAppended = json;
			}
	
	        if (ACC.images.jsonAppended != "") {
	        	var imageList = JSON.parse(ACC.images.jsonAppended);
		        Preload.setImageList(imageList);
		        QuickViewController.setProductImages(imageList);
	        } else {
	        	ACC.images.jsonAppended = json;
	        }
    	}
    }
};

var Util = {
    rgbToHex : function(rgb) {
        var rgbvals = /rgb\((.+),(.+),(.+)\)/i.exec(rgb);
        var rval = parseInt(rgbvals[1]);
        var gval = parseInt(rgbvals[2]);
        var bval = parseInt(rgbvals[3]);
        return '#'
                + (this.lpad(rval.toString(16)) + this.lpad(gval.toString(16)) + this
                        .lpad(bval.toString(16))).toUpperCase();
    },
    
    lpad : function(value) {
        return ("00" + value).slice(-2);
    }
};

var QuickViewController = {
    productImages : "",
    setProductImages : function(productImagesArray) {
        this.productImages = productImagesArray;
    },
    
    changeImage : function(parent, img, url) {
    	$(parent).find("img").attr("src", img);
    	$(parent).find("a").attr("href", ACC.config.contextPath + url);
    },
   
    changeUrl : function(parent, url, sku) {
    	console.log('productCodePost val: ' + sku);
    	$(parent).find("a").attr("href", ACC.config.contextPath + url);
    	$(parent).find("input[name='productCodePost']").val(sku);
    },
    
    onHover : function(parent, id) {
        var data = this.productImages[id];
        
        if (!data) {
        	console.log('onHover break: ' + id);
            return;
        }
        this.changeImage(parent, data[0], data[1]);
        this.loadSizes(parent, data[2]);
    },
    
    loadSizes : function(parent, sizeArray) {
        $(parent).find('.addToCartButtonSearch, .addToCartButton').hide();
        $(parent).find('.sizes-box li').attr('class', 'OutOfStock-1');
        
        if (sizeArray.length == 0) {
        	console.log('loadSizes break');
            return;
        }
        
        $(parent).find('.sizes-box').show();
        
        $(parent).find('.sizes-box li').each(function(j) {
            for (i = 0; i < sizeArray.length; i++) {
                if ($(this)[0].innerText == sizeArray[i] || $(this).text() == sizeArray[i]) {                	
                    $(this).attr('class', 'InStock-1');
                    break;
                }
            }
        });
        
        $(parent).find('.sizes-box li.InStock-1').first().click();
    },
    
    getSizeUrl : function(parent, size, baseCode, color) {
        $(parent).find('.addToCartButtonSearch, .addToCartButton').hide();
        
        color = color.replace("#", "");
        
        console.log('getSizeUrl ' + baseCode + ' - ' + size + ' - ' + color);
        $.ajax({
        	url : ACC.config.contextPath + '/search/urlfromsize/' + baseCode + '/' + size + '/' + color
        }).done(function(json) {
            if (json != '') {
                var data = JSON.parse(json);
                
                QuickViewController.changeUrl(parent, data['url'], data['sku']);
                
            	$(parent).find("input:submit.add").show();
            } else {
            	console.log('getSizeUrl vazio');
            }
        });
    }
};

// jquery plugin to preload images, and invoke a callback when ready
//
// Usage:
// $(['img1.jpg','img2.jpg','img3.jpg']).preload(function(){alert('loaded')});
$.fn.preload = function(callback) {
	var imageComponents = [];
    this.each(function(){
		var img = $('<img/>')[0];
		imageComponents.push(img);
		img.src = this;
	});
	$(imageComponents).load(callback);
};