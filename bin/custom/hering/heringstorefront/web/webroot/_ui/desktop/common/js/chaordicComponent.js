function informacoesUsuario(){
	
	if ($(".userDataInfo").find("input.userCode").val() != ''
        && $(".userDataInfo").find("input.userCode").val() != 'anonymous') {
    window.chaordic_meta.user = {
        "id" : $(".userDataInfo").find("input.userCode").val(),
        "name" : $(".userDataInfo").find("input.userFistName").val() + " "
                + $(".userDataInfo").find("input.userLastName").val(),
        "email" : $(".userDataInfo").find("input.userCode").val(),
        "allow_mail_marketing": ($(".userDataInfo").find("input.allow_mail_marketing").val()==="true")

    }
	}
}

function home(){
	
	if ($(".homePageData").length) {
        window.chaordic_meta.page = {
            "name" : "home",
            "timestamp" : new Date()
        }
    }
}

function checkout(){
	if ($(".checkoutPage").length) {
		window.chaordic_meta.page = {
			"name" : "checkout",
			"timestamp" : new Date()
		}
	}
	
}

function getSignatureTransaction(){
	
	if($(location).attr('host').toLowerCase().search('foryou') >= 0){
		return "+qVxwxEIxum7LKUKzELHXQ==";
	}		
	if($(location).attr('host').toLowerCase().search('dzarm') >= 0){
		return "7ApqEuY8F4MT8unkjZv3IQ==";
	}	
	if($(location).attr('host').toLowerCase().search('hering') >= 0){		
		return "sP1OFhFy4wU4Ad8RueI9SQ==";
	}
}

function orderConfirmation() {
	if ($("div.orderConfirmationDataInfo").length) {
        var transaction = {
            id : "",
            items : new Array(),
            signature : ""
        };
        var product = {
            id : "",
            sku : "",
            price : 0
        };
        var item = {
            product : product,
            quantity : 0
        };
        var signature = "";        
        transaction.id = $("div.orderConfirmationDataInfo").find(
                "input.orderID").val();
        
        $("div.orderConfirmationInfoProductDetailData")
                .each(
                        function() {
                            $this = $(this);
                            product = new Object();
                            product.id = $this.find("input.productSku").val().substr(0, 4);
                            product.price = Number($this.find("input.productPrice").val());
                            product.sku = $this.find("input.productSku").val();
                            
                            item.product = product;
                            item.quantity = Number($this.find("input.productQuantity").val());
                            transaction.items.push(item);                            

                            if (signature == "") {
                        		signature = transaction.id + ":"
                                + getSignatureTransaction() + ":"
                                + product.id + "," + product.sku
                                + "," + product.price + ","
                                + item.quantity;
                            } else {
                                signature = ":" + product.id + ","
                                        + product.sku + "," + product.price
                                        + "," + item.quantity;
                            }
                            item = new Object();
                            
                        });
        
        transaction.signature = CryptoJS.MD5(signature).toString();
        window.chaordic_meta.page = {
            "name" : "confirmation",
            "timestamp" : new Date()
        };
        window.chaordic_meta.transaction = transaction;        
    }
}

function productDetail() {

	product = {
		id: "",
		name: "",
		description: "",
		url: "",
		images: new Object(),
		status: "",
		old_price: 0,
		price: "",
		categories: new Array(),
		specs: new Array(),
		extra_info:{
			lancamento:"",
			frete_gratis: "",
			promocao: ""
		},
		installment: {
			count: 0,
			price: 0
		}
	};
	
	 
	if ($("div.productDataInfo").length) {
		
		
		var productData = $(".productDataInfo");
		//sku
		product.id = productData.find("input.productCode").val().substr(0, 4);
		var sku= productData.find("input.productCode").val();
		product.name = productData.find("input.productName").val();
		product.description = productData.find("input.productDescription").val().replace(/(<([^>]+)>)/ig,"");		
		product.url = $(location).attr('host') +  $(location).attr('pathname'); 		
		product.price = Number(productData.find("input.productValue").val());
		
		product.extra_info.lancamento = productData.find("input.productLancamento").val();
		product.extra_info.frete_gratis = productData.find("input.productFreteGratis").val();
		product.extra_info.promocao = productData.find("input.productPromocao").val();
			
		if (Number(productData.find("input.productOldPrice").val()) != 0) {
			product.old_price = Number(productData.find("input.productOldPrice").val());
		} else {
			product.old_price = product.price;
		} if (Number($(".priceParcelInfo").find("input.numberParcelInfo").val()) != 0) {
			product.installment.count = Number($(".priceParcelInfo").find("input.numberParcelInfo").val());
			product.installment.price = Number($(".priceParcelInfo").find("input.priceParcelInfo").val());
		} else {
			product.installment.count = 1;
			product.installment.price = product.price;
		}
		var cat = productData.find("input.productCategories").val().split(",");
		for (var a in cat) {
			var category = new Object();
			category.name = cat[a];
			if(category.name !==""){
				product.categories.push(category);
			}
			
		}
		
		product.status = "available";
		var image = {
			default: ""
		};
		if (!typeof $(".productDataInfo").find("input.productImg").val() == 'undefined'){
			image.default = $(".productDataInfo").find("input.productImg").val().replace(/http(s)*:\/\//,"");
		}
		
		product.images = image;
		window.chaordic_meta.page = {
			"name": "product",
			"timestamp": new Date(),
			"categories": product.categories
		};
		window.chaordic_meta.product = product;
		var cartID = Number($("input.cartCodeInfo").val() );
		
		$('.addToCartButton').click(function(){			
			chaordic.push(['updateCart', {"id": cartID,"items": [{"product": {"id": product.id,"sku": sku,"price": product.price}, "quantity": isNaN(Number($("input[name=initialQuantity]").val()))==true ? 0 : Number($("input[name=initialQuantity]").val())  }]}])
	    })	    
		
	}
}
	
  function productSearch() {
        var query = "";
        if ($(".searchCategoryInfo").val() != '') {
            query = $(".searchCategoryInfo").val();
        }
        if ($("div.searchDataInfo").length) {
            var product = {
                id : "",
                price : ""
            };
            var items = new Array();
            var mainCategory = "";
            $(".searchProductInfoDetails").each(
                    function() {
                        $this = $(this);
                        product = new Object();
                        product.id = $this.find("input.codeProduct").val()
                                .substr(0, 4);
                        product.price = Number($this.find("input.priceProduct")
                                .val());
                        items.push(product);
                    });
            
            window.chaordic_meta.page = {
                "name" : "search",
                "timestamp" : new Date()
            };
            var result = $(".chaordicSearchCategory").val();
            if(result === ""){
            	result = $(".breadcrumb li").last().text().trim();
            }
            window.chaordic_meta.search = {
                query : query != undefined ? query : result,
                items : items
            }            
            
        }
} 
  
  function categorySearch()  {
      if ($("div.categoryDataInfo").length) {
         var categoryName = $(".categoryDataInfo").find("input.category").val();
         var categoryGender = $(".categoryDataInfo").find("input.categoryGender")
                 .val();
         var categories = new Array();         
         var parents = new Array(categoryGender);
         if(categoryGender !== "") {
        	 categories.push({"id" : categoryName, "name" : categoryName, "parents" : parents});        	 
             categories.push({"id" : categoryGender, "name" : categoryGender});             
         }else{
        	 categories.push({"id" : categoryName, "name" : categoryName});
         }         
         
         window.chaordic_meta.page = {
             "name" : "category",
             "timestamp" : new Date(),
             "categories" : categories
              
         }         
     }
    
 }
  
 
  function cartPageData(){	    
      
	  if ($(".cartDataItems").length) {
		  var product = {
	            id : "",
	            sku : "",
	            price : ""
	        };
	        var item = {
	            product : product,
	            quantity : 0,
	            tags : new Array()
	            	        
	        };  
	        items = new Array();	        
                $(".cartDataItems")
                    .each(
				          function() {
				              $this = $(this);
				              var item = new Object();
				              var product = new Object();
				              product.id = $this.find("input.productSku")
				                      .val().substr(0, 4);
				              product.sku = $this
				                      .find("input.productSku").val();
				              product.price = Number($this.find(
				                      "input.productPrice").val());
				              item.quantity = Number($this.find(
				                      "input.productQuantity").val());
				              if ($this.find("input.productCategories")
				                      .val() != undefined) {
				                  var tags = $this.find(
				                          "input.productCategories")
				                          .val().split(",");
				                  for ( var a in tags) {
				                      if (tag != undefined && tag != "") {
				                          var tag = tags[a];
				                          item.tags.push(tag);
				                      }
				                  }
				              }
				              item.product = product;
				              items.push(item);
				          });
            window.chaordic_meta.cart = {
            	"id" : cartID = $("input.cartCodeInfo").val(),
            	"items" : items
            };
            window.chaordic_meta.page = {
                "name" : "cart",
                "timestamp" : new Date()
            };
        }
}

$(document).ready(function() {	
	orderConfirmation();
	productDetail();
	productSearch();
	informacoesUsuario();
	home();
	checkout();
	categorySearch();
	cartPageData();
	
});