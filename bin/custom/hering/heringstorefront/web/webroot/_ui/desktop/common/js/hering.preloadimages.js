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
		
		ACC.images.bindVariantSelectorHandler();
		
		//console.log('loading');
	    var productCodes = [];
	    jQuery.each($(".productDetailsHolder").not(".visited"), function() {
	    	productCodes.push($(this).find(":hidden[name='productCode']").val());
	    	$(this).addClass('visited');
	    });
	    
	    //console.log(productCodes);
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
	    	
	    	$(".productDetailsHolder").hover(function() {
	    		var alreadyHovered = $(this).find('.InStock-1-Selected').first();
	    		if(alreadyHovered.length <= 0) {
	    			$(this).find('.styleVariantEventHandler').first().click();
	    		} else {
	    			$(this).unbind('hover');
    			}
	    	});
	    }
	},
	
	preloadImagesAgain : function(currentPage){
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
       
		//$('.sizes-box').hide();
		$('.sizes-box li').attr('class', 'OutOfStock-1');
        
		$(".styleVariantEventHandler").click(function() {
			var item = $(this).find(":hidden[name='codeData']").val();
			var codes = item.split("-");
			var code = codes[0];
			var id = codes[1];

			Product.code = code;
			Product.color = Util.rgbToHex($(this).css("background-color"));
			QuickViewController.onHover(id);
		});

		$(".sizeVariantEventHandler li").click(function() {
			if ($(this).attr('class') == "InStock-1") {
			    QuickViewController.getSizeUrl($(this));
			    LoaderUtil.getProductDetailsHolder().find("input:submit.add").show();
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

var Product = {
    code : "",
    color : "",
    baseCode : "",
    url : "",
    img : "",
    size : "",
    sizes : "",
    sku : ""
};

var LoaderUtil = {
		getProductDetailsHolder : function() {
			var parent = $(":hidden[name='productCode'][value='" + Product.code + "']").parent();
	    	return parent;
		}
};

var QuickViewController = {
    productImages : "",
    setProductImages : function(productImagesArray) {
        this.productImages = productImagesArray;
    },
    
    changeImage : function() {
    	var parent = LoaderUtil.getProductDetailsHolder();
    	$(parent).find("img").attr("src", Product.img);
    	$(parent).find("a").attr("href", ACC.config.contextPath + Product.url);
    },
   
    changeUrl : function() {
    	var parent = LoaderUtil.getProductDetailsHolder();
    	$(parent).find("a").attr("href", ACC.config.contextPath + Product.url);
    	$(parent).find("input[name='productCodePost']").val(Product.sku);
    },
    
    onHover : function(id) {
        var data = this.productImages[id];
        
        if (!data) {
            return;
        }
        Product.img = data[0];
        Product.url = data[1];
        Product.sizes = data[2];
        Product.baseCode = data[3];
        this.changeImage();
        this.loadSizes();
    },
    
    loadSizes : function() {
        var sizeArray = Product.sizes;
        var code = Product.code;
        var parent = LoaderUtil.getProductDetailsHolder();
        $(parent).find('.addToCartButtonSearch').hide();
       
        $(parent).find('.sizes-box li').attr('class', 'OutOfStock-1');
        
        if (sizeArray.length == 0) {
            return;
        }
        
        $(parent).find('.sizes-box').show();
        
        $(parent).find('.sizes-box li').each(function(j) {
            for (i = 0; i < sizeArray.length; i++) {
                if ($(this)[0].innerText == sizeArray[i] || $(this).text() == sizeArray[i]) {                	
                    $(this).attr('class', 'InStock-1');
                    $(this).click();
                    break;
                }
            }
        });
        
        
        $(parent).find('.sizes-box li.InStock-1').first().click();
    },
    
    getSizeUrl : function(item) {
        Product.size = item.text();
        
        $(item).siblings('.InStock-1-Selected').attr('class', 'InStock-1');
        
        item.attr('class', 'InStock-1-Selected');
        
        var color = Product.color.replace("#", "");
        if (!Product.code || !Product.size || !Product.baseCode || !color) {
            return;
        }
        $.ajax(
                {
                    url : ACC.config.contextPath + '/search/urlfromsize/'
                            + Product.baseCode + '/' + Product.size + '/'
                            + color,
                    context : document.body
                }).done(function(json) {
            if (json != '') {
                var data = JSON.parse(json);
                Product.url = data['url'];
                Product.sku = data['sku'];
                
                QuickViewController.changeUrl();
            }
        });
        
        LoaderUtil.getProductDetailsHolder().find('.addToCartButtonSearch').show();
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