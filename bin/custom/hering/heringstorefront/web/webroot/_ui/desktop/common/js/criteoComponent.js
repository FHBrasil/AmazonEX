
function getAcount(){
	
	if($(location).attr('host').toLowerCase().search('dzarm') >= 0){
		return 13822;
	}
	
	if($(location).attr('host').toLowerCase().search('foryou') >= 0){		
		return 19475;
	}
	
	if($(location).attr('host').toLowerCase().search('hering') >= 0){		
		return 12840;
	}
	
}

function getUserEmail(){
	
	var userEmail = $(".userDataInfo").find("input.userCode").val();
	return userEmail === "anonymous" ? "" : userEmail;  
}

function cartData(){

    if ( $(".cartDataItems").length ) {
        var cartItems = [];
        var items = $(".cartDataItems");
        items.each(function() {
            cartItems.push({
                id : $(this).find("input.productSku").val(),
                price : $(this).find("input.productPrice").val(),
                quantity : $(this).find("input.productQuantity").val()
            });
        });
        window.criteo_q = window.criteo_q || [];
        window.criteo_q.push({
            event : "setAccount",
            account : getAcount()
        }, {
            event : "setEmail",
            email : getUserEmail()
        }, {
            event : "setSiteType",
            type : uiExperienceLevel.substring(0, 1).toLowerCase()
        }, {
            event : "viewBasket",
            item : cartItems
        });
    }
}

function categoryData() {

	    if ($("div.categoryDataInfo").length) {
		var productsIds = [];
		$(".searchProductInfoDetails:lt(3)").each(function() {
			productsIds.push($(this).find("input.codeProduct").val());
		});
		window.criteo_q = window.criteo_q || [];
		window.criteo_q.push({
			event : "setAccount",
			account : getAcount()
		}, {
			event : "setSiteType",
			type : uiExperienceLevel.substring(0, 1).toLowerCase()
		}, {
			event : "setEmail",
			email : getUserEmail()
		}, {
			event : "viewList",
			item : productsIds
		});
	}
}


function homeData() {

	if ( $(".homePageData").length ) {
		
		window.criteo_q = window.criteo_q || [];
		window.criteo_q.push({
			event : "setAccount",
			account : getAcount()
		}, {
			event : "setEmail",
			email : getUserEmail()
		}, {
			event : "setSiteType",
			type : uiExperienceLevel.substring(0, 1).toLowerCase()
		}, {
			event : "viewHome"
		});
	}
}

function orderConfirmationData() {
    if ( $("div.orderConfirmationDataInfo").length ) {
        var items = [];
        $(".orderConfirmationInfoProductDetailData")
                .each(
                        function() {
                            $this = $(this);
                            items
                                    .push({
                                        id : $this
                                                .find(
                                                        "input.productSku")
                                                .val(),
                                        price : $this
                                                .find(
                                                        "input.productPrice")
                                                .val(),
                                        quantity : $this
                                                .find(
                                                        "input.productQuantity")
                                                .val()
                                    });
                        });
        window.criteo_q = window.criteo_q || [];
        window.criteo_q.push({
            event : "setAccount",
            account : getAcount()
        }, {
            event : "setEmail",
            email : getUserEmail()
        }, {
            event : "setSiteType",
            type : uiExperienceLevel.substring(0, 1).toLowerCase()
        }, {
            event : "trackTransaction",
            id : $(".orderConfirmationDataInfo").find("input.orderID").val(),
            item : items
        });
    }
}

function productDataInfo() {
	
    if ($(".productDataInfo").length ) {
        window.criteo_q = window.criteo_q || [];
        window.criteo_q.push({
            event: "setAccount",
            account: getAcount()
        }, {
            event: "setEmail",
            email: getUserEmail()
        }, {
            event: "setSiteType",
            type: uiExperienceLevel.substring(0, 1).toLowerCase()
        }, {
            event: "viewItem",
            item: $(".productDataInfo").find("input.productCode").val()
        });
    }
}

function searchData() {
    if ($("div.searchDataInfo").length) {
    	
        var productsIds = [];
        $(".searchProductInfoDetails:lt(3)").each(function() {
            productsIds.push($(this).find("input.codeProduct").val());
        });
        window.criteo_q = window.criteo_q || [];
        window.criteo_q.push({
            event : "setAccount",
            account : getAcount()
        }, {
            event : "setEmail",
            email : getUserEmail()
        }, {
            event : "setSiteType",
            type : uiExperienceLevel.substring(0, 1).toLowerCase()
        }, {
            event : "viewList",
            item : productsIds
        });
    }
}


$(document).ready(function() {
	cartData();		
	categoryData();
	homeData();
	orderConfirmationData();
	productDataInfo();
	searchData();	
});