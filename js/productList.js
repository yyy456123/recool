$(function() {
    // Add event listener to each Add to Cart button in the product list using jQuery
    $(".product button").on("click", function() {
        const product = $(this).parent();
        const productName = product.find("h2").text();
        const productPrice = parseFloat(product.find(".price").text().slice(1));
        addToCart(productName, productPrice);
    });

    // Add event listener to cart icon to show/hide the cart using jQuery
    $("#cart-icon").on("click", function() {
        if ($("#cart-list").is(":visible")) {
            $("#cart-list").hide();
        } else {
            updateCartTotal2();
            $("#cart-list").show();
        }
    });

    // Add event listener to checkout button using jQuery
    $("#checkout-button").on("click", function() {
        alert("Thank you for your purchase!");
        $(".cart-item").remove();
        updateCartTotal();
        updateCartTotal2();
        $("#cart-list").hide();
    });

    // Function to add item to cart using jQuery
    function addToCart(name, price) {
        // Create cart item
        if ($("#cart-items").length === 0) {
            $("<ul id='cart-items'></ul>").appendTo("#cart-list");
        }
        const cartItem = $("<li class='cart-item'>" +
            "<span>" + name + "</span>" +
            "<span>$" + price.toFixed(2) + "</span>" +
            "<button class='remove-button'>Remove</button>" +
            "</li>");

        // Add event listener to remove button of cart item
        cartItem.find(".remove-button").on("click", function() {
            $(this).parent().remove();
            updateCartTotal();
            updateCartTotal2();
        });

        // Add cart item to cart
        $("#cart-items").append(cartItem);

        // Update cart total
        updateCartTotal();
        updateCartTotal2();
    }

    // Function to update cart total in header using jQuery
    function updateCartTotal() {
        const cartItemElements = $(".cart-item");
        let cartTotalValue = 0;
        for (let i = 0; i < cartItemElements.length; i++) {
            const priceElement = $(cartItemElements[i]).find("span:last-of-type");
            const price = parseFloat(priceElement.text().slice(1));
            cartTotalValue += price;
        }
        $("#cart-total").text(cartTotalValue.toFixed(2));
    }

    // Function to update cart total in shopping cart using jQuery
    function updateCartTotal2() {
        const cartItemElements = $(".cart-item");
        let cartTotalValue = 0;
        for (let i = 0; i < cartItemElements.length; i++) {
            const priceElement = $(cartItemElements[i]).find("span:last-of-type");
            const price = parseFloat(priceElement.text().slice(1));
            cartTotalValue += price;
        }
        $("#cart-total2").text(cartTotalValue.toFixed(2));
    }
});
