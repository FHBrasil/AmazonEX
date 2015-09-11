var variables = {
		
	selectorMiniContent: ".section-mini-content",
	selectorBoxColor: ".bxslider-cores li",
	selectorBoxSize: " .tamanhos ul li",
	selectorInput: " input[name='crossProduct']",
	selectorListDivs: " .section-mini-content > div",
	selectorColorActive: ".bxslider-cores .active",
	selectorControl: "#control",
	
	sizesWithStock: [],
	ajaxSizeUrl: "/search/images",
	ajaxSKUUrl: '/search/urlfromsize/',
	
	inStockClass: "in-stock",
	outOfStockClass: "out-of-stock",
	
	code: 0,
	color: "",
	sku:"",
	
	clickColorMonitor: function(){
		
		variables.color = "";
		
		$(variables.selectorBoxColor).click(function(){

			code = $(this).attr("id");
			variables.color = $(this).attr("color");
			variables.getSizesWithStock(code);
			variables.validateStockSizes(code);
		});
	},
	
	clickSizeMonitor: function(){
		
		$(variables.selectorListDivs).hover(function(){
			
			$(variables.selectorControl).attr("main", $(this).attr("id"));
			$(variables.selectorControl).attr("code", $(this).find(variables.selectorColorActive).attr("id"));
			$(variables.selectorControl).attr("color", $(this).find(variables.selectorColorActive).attr("color"));
		});
		
		$(variables.selectorBoxSize).click(function(){
			
			mainCode = $("#control").attr("main"); 
			code = $("#control").attr("code");
			size = $(this).find("a").text();
			color = $(variables.selectorMiniContent + " #" + mainCode + " "+ variables.selectorColorActive).attr("color");

			variables.getSKU(code, size, color);
			$(variables.selectorMiniContent + " #" + mainCode + variables.selectorInput).val(variables.sku);
		});
	},
	
	getSizesWithStock: function(code){
		
		$.ajax({
	    	url : ACC.config.contextPath + variables.ajaxSizeUrl,
	    	data: {products: [code]},
	    	context : document.body,
	    	async : false
	    })
	    
	    .done(function(data){
	    	
	    	data = $.parseJSON(data);
	    	variables.sizesWithStock = [];

	    	$.each(data,function(k,v){
	    		
	    		if( code == v[3] ) $.each( v[2], function(r,s){
	    			variables.sizesWithStock.push(s);
	    		});
	    	});
	    });
	},
	
	validateStockSizes: function(code){
		
		mainCode = code.split("_");
		selector = variables.selectorMiniContent + " #" + mainCode[0] + variables.selectorBoxSize;

		$(selector).each( function(){
			
			textContent = $(this).find("a").text();
			if( $.inArray( textContent, variables.sizesWithStock )!==-1 ) $(this).find("a").attr("class", variables.inStockClass);
			else $(this).find("a").attr("class", variables.outOfStockClass);
		});

		firstSize = $(selector + " ." + variables.inStockClass).first().text();
		variables.getSKU(code, firstSize, variables.color);
		inputSelector = variables.selectorMiniContent + " #" + mainCode[0] + variables.selectorInput;
		
		$(selector + " ." + variables.inStockClass).first().addClass("active");
		$(inputSelector).val(variables.sku);
	},
	
	
	getSKU : function(basecode, size, color) {
        
		variables.sku = "";
		color = color.replace("#", "");
        
		if ( !size || !basecode || !color) return;
        
        $.ajax({
            url : ACC.config.contextPath + variables.ajaxSKUUrl
            + basecode + '/' + size + '/'
            + color,
            context : document.body,
            async:false
        })
        .done(function(data) {
            
        	if( !data ) return;
        	data = $.parseJSON(data);
        	variables.sku = data.sku;
        });
    }
}