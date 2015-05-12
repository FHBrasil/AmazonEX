crossSeling = {
	
	selectorInputCheck: "input[name='crossProduct']",
	selectorListSelected: ".section-mini-content input[name='crossProduct']",
	selectorCrossBuild: ".comprar-junto",
	selectorPrice: ".pro-info .precos strong",
	selectorShowTotalPrice: ".comprar-junto #totalPrice",
	selectorShowQuantity: ".comprar-junto #quantity",
	selectorShowInstallment: ".comprar-junto #installmentPrice",
	selectorBuyButton: ".comprar",
	selectorCartButton: "button.addToCartButton",
	selectorMainSection: ".section-mini-content",
	selectorSucessMessage: "#sucess-cross",
	
	cartUrl: "/store/pt/cart/add",
	
	sucessMessage : "<div id='sucess-cross' style='display:none'><h1>Adicionamos ao seu carrinho!</h1></div>",
	
	productPrice: 0,	
	totalProductsSelected : 0,
	totalPrice: 0,
	totalPriceinstallment: 0,
	installment: 0,
	mainProductAddtoCart: false,
	
	initialize: function(){
		
		var codes = [];
		var displayBuyBox = false;
		
		crossSeling.totalPrice = 0;
		crossSeling.totalProductsSelected = 0;
		
		$(crossSeling.selectorInputCheck).each(function(){
			
			code = $(this).val();
			if( !( $.inArray( code, codes )!==-1 ) && ( $(this).attr("checked") == "checked" ) ){
				codes.push(code);
			}
		});
		
		$.each(codes,function(k,v){
			
			crossSeling.totalPrice += crossSeling.getProductCrossPrice( v );
			crossSeling.totalProductsSelected++;
			displayBuyBox = true;
		})
		
		if( displayBuyBox == true ){
			
			crossSeling.showTotalQuantity();
			crossSeling.showTotalPrice();
			crossSeling.showinstallmentPrice();
			
			$(crossSeling.selectorCrossBuild).show("slow");
		}
		
		else{
			
			crossSeling.totalProductsSelected = 0;
			$(crossSeling.selectorCrossBuild).hide("slow");
		}
	},
	
	
	getProductInformations: function(){
		
		crossSeling.productPrice = $(crossSeling.selectorPrice).text();
		crossSeling.productPrice = crossSeling.convertHumanValueToFloat(crossSeling.productPrice);
	},
	
	convertHumanValueToFloat: function(value){

		value = value.replace(",",".").replace("R$","").replace("Por:","");
		value = parseFloat(value);
		return value;
	},
	
	convertFloatToHumanPrice: function(value){
		
		value = value.toString().replace(".",",");
		return ( "R$ " + value );
	},
	
	showTotalQuantity: function(){
		
		$(crossSeling.selectorShowQuantity)
		.html( (crossSeling.totalProductsSelected + 1) + " itens" );
	},
	
	showTotalPrice: function(){
		
		crossSeling.totalPrice = crossSeling.totalPrice + crossSeling.productPrice;
		totalPrice = crossSeling.convertFloatToHumanPrice(crossSeling.totalPrice);
		$(crossSeling.selectorShowTotalPrice).html(totalPrice);
	},
	
	showinstallmentPrice: function(){
		
		crossSeling.installmentCalc();
		
		installmentPrice = crossSeling.installment + "x R$ ";
		installmentPrice += crossSeling.totalPriceinstallment;
		
		$(crossSeling.selectorShowInstallment).html( installmentPrice );
	},
	
	getProductCrossPrice: function(code){
		
		code = code.substring(0,4);
		value = $("#" + code + " .precos strong").html();
		return crossSeling.convertHumanValueToFloat(value);
	},
	
	installmentCalc: function(){
		
		price = crossSeling.totalPrice;
		value = 0;
		
		if (price < 60) value = 1;
		else if (price >= 60.00 && price < 90.00) value = 2;
		else if (price >= 90.00 && price < 120.00) value = 3;
		else if (price >= 120.00 && price < 150.00) value = 4;
		else value = 5;
		
		crossSeling.installment = value;
		crossSeling.totalPriceinstallment = ( price / value );
		crossSeling.totalPriceinstallment = crossSeling.totalPriceinstallment.toFixed(2);
		crossSeling.totalPriceinstallment = crossSeling.totalPriceinstallment.toString().replace(".",",");
	},
	
	addToCart: function(){
		
		$(crossSeling.selectorBuyButton).click(function(){
			
			codes = [];
			
			$(crossSeling.selectorInputCheck).each(function(){
				
				code = $(this).val();
				if( !( $.inArray( code, codes )!==-1 ) && 
					( $(this).attr("checked") == "checked" ) ) codes.push(code);
			});
			
			$.each(codes,function(k,value){

				dataCart = {
					productCodePost: value,
					CSRFToken: ACC.config.CSRFToken,
					_method:"fpost"
				};
				
				$.post( crossSeling.cartUrl, dataCart ).done(function(data){});
			});

			$(crossSeling.selectorCartButton).click();
			$(crossSeling.selectorMainSection).html(crossSeling.sucessMessage);
			$(crossSeling.selectorSucessMessage).show("slow");
		});
	}
}