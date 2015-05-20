ACC.heringautocomplete = {
    
    bindAll : function() {
        this.bindHeringSearchAutocomplete();
    },
    
    bindHeringSearchAutocomplete : function() {
        $search = $(".searchInput");
        
        $($search).each(function() {
        
        	var option = $(this).data("options");
        	var cache = {};
        	var appendToElem = $(this).closest('.siteSearch').find(".search-autocomplete-results");
        	
        	$(this).autocomplete(
        			{
        				minLength : option.minCharactersBeforeRequest,
        				delay : option.waitTimeBeforeRequest,
        				appendTo : appendToElem,
        				source : function(request, response) {
        				
        					var term = request.term.toLowerCase();
        					if (term in cache) {
        						return response(cache[term]);
        					}
        					
        					$.getJSON(option.autocompleteUrl, { term : request.term }, function(data) {
        						
        						var autoSearchData = [];
        						if (data.suggestions != null) {

        							$.each(data.suggestions, function(i, obj) {
        								autoSearchData.push({
        									value : obj.term,
        									url : ACC.config.contextPath + "/search?text=" + obj.term,
        									type : "autoSuggestion"
        								});
        							});
        						}
        						
        						if (data.products != null) {
        							
        							$.each(data.products, function(i, obj) {
        								autoSearchData.push({
        									value : obj.name,
        									code : obj.code,
        									desc : obj.description,
        									manufacturer : obj.manufacturer,
        									url : ACC.config.contextPath + obj.url,
        									price : obj.price.formattedValue,
        									pricevalue : obj.price.value,
        									oldprice : obj.oldPrice,
        									type : "productResult",
        									size : obj.size,
        									image : (obj.images && obj.images.length > 0) ? obj.images[0].url : '',
        									sizes : obj.sizes,
        									colors: obj.colors
        								});
        							});
        						}
        						
        						cache[term] = autoSearchData;
        						return response(autoSearchData);
        					});
        				},
        				
        				focus : function(event, ui) {
        					return false;
        				},
        				
        				select : function(event, ui) {
        					window.location.href = ui.item.url;
        				}
        				
        			}).data("autocomplete")._renderItem = function(ul, item) {
        		
		        		if (item.type == "autoSuggestion") {
		        		
		        			ul.addClass('keywords-suggestions');
		        			var renderHtml = "<a href='" + item.url + "' class='clearfix'>" + item.value + "</a>";
		        			return $("<li>").data("item.autocomplete", item).append(renderHtml).appendTo(ul);
		        		}
		        		
		        		if (item.type == "productResult") {
		        			
		        			var renderHtml = "";
		        			if (option.displayProductImages && item.image != null) {
		        				renderHtml += '<a href="' + ACC.config.contextPath + item.url + '">';
		        				renderHtml += ' <img src="' + item.image + '" alt="' + item.value + '">';
		        				renderHtml +='</a>';
		        			}
		        			
		        			renderHtml += '<div class="info">';
		        			renderHtml += '<h2><a href="' + ACC.config.contextPath + item.url + '">' + item.value + '</a></h2>';
		        			
		        			if(item.colors != null) {
		        				//var colorsHTML = item.colors.replace('li', 'div');
		        				var colorsHTML = item.colors;
		        				colorsHTML = colorsHTML.replace('ul', 'ol');
		        				renderHtml += '<div class="cores">' + colorsHTML + '</div>';
		        			}
		        			
		        			if(item.sizes != null) {
		        				renderHtml += '<div class="tamanhos"><ol>';
		        				if( item.sizes.length > 0 ){
		        					$.each( item.sizes, function( key2, size ) {
		        						renderHtml += '<li style="color: #474747;">' + size + '</li>&nbsp;';	
		        					});
		        				}
		        				renderHtml += '</ol></div>';
		        			}
		        			
		        			renderHtml += '<div class="precos">';
		        			if (item.oldprice != null && item.pricevalue < item.oldprice) {
		        				renderHtml += '<s>de: R$' + item.oldprice + '</s>';
		        			}
		        			renderHtml += '<strong>por: ' + item.price + '</strong>';
		        			renderHtml += '</div>';
		        			
		        			renderHtml += '</div>';
		        			
		        			ul.addClass('products-suggestions');
		        			return $("<li>").data("item.autocomplete", item).append(renderHtml).appendTo(ul);
		        		}
        	};
        });
        
    }
};

$(document).ready(function() {
    ACC.heringautocomplete.bindAll();
});

//$('#search #palavra, #mini-header #palavra-mini-header').keyup(function(){
//
//	var val = $(this).val();
//	if( val.length < 3 ) return;
//    var ajaxReturn;
//
//    var auto = $(this).parent().find('.search-autocomplete-results');
//    if(!auto[0]){
//    	
//        $(this).parent().append('<div class="search-autocomplete-results show-on-desktop"></div>');
//        auto = $(this).parent().find('.search-autocomplete-results');
//    }
//    
//	$.getJSON( "/store/pt/search/autocomplete/SearchBox", { term : val }, function( data ) {
//	    
//	    if( data.suggestions.length > 0 || data.products.length > 0 ) {
//
//	    	results = "";
//	    	
//	    	if( data.suggestions.length > 0 ){
//	    		
//	    		AC.suggest = "";
//	    		AC.processSuggest( data.suggestions );
//	    		results += AC.suggest;
//	    	}
//	    	
//	    	if( data.products.length > 0 ){
//	    		
//	    		AC.results = "";
//	    		AC.processProducts( data.products );
//	    		results += AC.results;
//	    	}
//	        
//	        auto.html( results );
//	        auto.show();
//	    } 
//	    
//	    else auto.hide();
//	 });
//});
//
//var AC = {
//	
//	suggest: "",
//	results: "",
//		
//	processProducts : function( products )
//	{
//        AC.results += '<div><ul class="products-suggestions">';
//        
//        $.each( products, function( key, product ){
//
//        	AC.results += '<li>';
//                AC.results += '<a href="' + product.url + '"><img src="' + product.images[0]['url'] + '" alt="' + product.name + '"></a>';
//                AC.results += '<div class="info">';
//                    AC.results += '<h2><a href="' + product.url + '">' + product.name + '</a></h2>';
//                    AC.results += '<div class="cores">';
//                    AC.results += product.colors.replace("width:12px; height:15px;"); 
//                    AC.results += '</div>';
//                    AC.results += '<div class="tamanhos">';
//                        AC.results += '<ul>';
//
//                        if( product.sizes.length > 0 ){
//                        	$.each( product.sizes, function( key2, size ){
//                        		AC.results += '<li><a href="#">' + size + '</a></li>';	
//                        	});
//                    	}
//                        
//                        AC.results += '</ul>';
//                    AC.results += '</div>';
//                    AC.results += '<div class="precos">';
//                    	/*AC.results += '<s>de: R$99,99</s>'; */
//                        AC.results += '<strong>por: ' + product.price.formattedValue + '</strong>';
//                    AC.results += '</div>';
//                AC.results += '</div>';
//            AC.results += '</li>';
//    	});	
//            
//        AC.results += '</ul></div>';
//	},
//
//	processSuggest: function( suggests )
//	{
//		AC.suggest += '<div><ul class="keywords-suggestions">';
//
//		$.each( suggests, function( key, suggest ){
//			AC.suggest += '<li><a href="/store/search?text=' + suggest.term + '">' + suggest.term + '</a></li>';
//		});
//		
//		AC.suggest += '</ul></div>';
//	}
//}