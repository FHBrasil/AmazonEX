KP.action = {
    
    addToCart : function() {
        $("#addToCartButton").click(function() {
            var productCode = $(this).closest("form")
                    .children("input[type=hidden][name=productCodePost]").val();
            var quantity = $(this).closest("form").find("input[name=qty]").val();
            $.ajax({
                type : "POST",
                url : "/cart/add",
                data : {
                    productCodePost : productCode,
                    qty: quantity
                },
                success : function(data, statusText, xhr) {
                },
                error : function(xhr, statusText, error) {
                },
                complete : function(xhr, statusText) {
                } 
            });
        });
    }
        
}

$(document).ready(function() {
    with(KP.action) {
        addToCart();
    }
});