ACC.cart = {
    product : {
        id : "",
        sku : "",
        price : 0
    },
    item : {
        product : null,
        quantity : 0,
        tags : new Array()
    },
    user : {
        id : "",
        fullName : ""
    },
    init : function() {
        ACC.cart.tableIterate();
        ACC.cart.nextPerformance();
    },
    tableIterate : function() {
        items = new Array();
        cartID = 0;
        if ($(".cartData").length) {
            if ($("tr.cartItem").length) {
                $("tr.cartItem")
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
                                            if (tag != undefined) {
                                                var tag = tags[a];
                                                item.tags.push(tag);
                                            }
                                        }
                                    }
                                    item.product = product;
                                    items.push(item);
                                });
            }
            
        }
    },
    
    nextPerformance : function() {
        if ($(".cartData").length
                && ($(".userData").find("input.userCode").val() != "" && $(
                        ".userData").find("input.userCode").val() != "anonymous")) {
            var productSkus = "";
            var iterations = 0;
            $("tr.cartItem").find("input.productSku").each(
                    function() {
                        productSkus += $(this).val();
                        productSkus += ++iterations < $("tr.cartItem").find(
                                "input.productSku").length ? "," : "";
                    });
            $("div#cartItems").after(
                    "<script type='text/javascript' "
                            + "src='https://nxtck.com/act.php?tag=40873&pid="
                            + productSkus + "'><" + "/script>");
        }
    }
};
$(document).ready(function() {
    ACC.cart.init();
});