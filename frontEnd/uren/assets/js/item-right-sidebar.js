$(document).ready(function () {
    //loadCart();
    $.ajax({
        url: 'http://localhost:8082/api/v1/item/all',
        type: 'GET',
        success: function (response) {
            console.log(response.data);
            let productContainer = $('.shop-product-wrap'); // Targeting the product container

            response.data.forEach(function (item) {
                console.log("Item Image: "+item.itemImage);
                $(".item-name").text(item.itemName);
                let oldPrice = 2000 + item.itemPrice;
                $("#old-item-price").text("Rs : " +oldPrice);
                $("#item-price").text("Rs : " + item.itemPrice);
                $("#item-description").text(item.itemDescription);
                $("#item-image").attr("src", "../" + item.itemImage);
                $("#item-name").text(item.itemName);
                $("#fuel-type").text(item.fuelType);
                $("#vehicle-model").text(item.vehicleModel);
                $("#item-id").text(item.itemId);

                if (item.itemQty > 0) {
                    $("#availability").text("In Stock");
                } else {
                    $("#availability").text("Out of Stock");
                }
                let productHTML = `
                    <div class="col-lg-4">
                                <div class="product-slide_item">
                                    <div class="inner-slide">
                                        <div class="single-product">
                                            <div class="product-img">
                                                <a href="../../../pages/single-product-sale.html">
                                                    <img class="primary-img" src="../${item.itemImage}" alt="${item.itemName}">
                                                </a>
                                                <div class="sticker">
                                                    <span class="sticker">New</span>
                                                </div>
                                                <div class="add-actions">
                                                    <ul>
                                                        <li><a class="uren-add_cart" href="javascript:void(0)" data-toggle="tooltip" id="add-to-cart-btn" data-placement="top" title="Add To Cart" data-id="${item.itemId}" data-name="${item.itemName}" data-desc="${item.itemDescription}"
                                                    data-model="${item.vehicleModel}" data-fuel="${item.fuelType}" data-price="${item.itemPrice}"
                                                    data-qty="${item.itemQty}" data-shopid="${item.shopId}" data-image="${item.itemImage}"><i
                                                            class="ion-bag"></i></a>
                                                        </li>
                                                        <li><a class="uren-wishlist" href="wishlist.html" data-toggle="tooltip" data-placement="top" title="Add To Wishlist"><i
                                                            class="ion-android-favorite-outline"></i></a>
                                                        </li>
                                                        
                                                        <li class="quick-view-btn" data-toggle="modal" data-target="#exampleModalCenter"><a href="javascript:void(0)" data-toggle="tooltip" data-placement="top" title="Quick View"><i
                                                            class="ion-android-open"></i></a></li>
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="product-content">
                                                <div class="product-desc_info">
                                                    <h6><a class="product-name" href="../../../pages/single-product-sale.html">${item.itemName}</a></h6>
                                                    <div class="price-box">
                                                        <span class="new-price">${item.itemPrice}</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="list-slide_item">
                                    <div class="single-product">
                                        <div class="product-img">
                                            <a href="single-product.html">
                                                <img class="primary-img" src="../${item.itemImage}" alt="${item.itemName}">
                                            </a>
                                        </div>
                                        <div class="product-content">
                                            <div class="product-desc_info">

                                                <h6><a class="product-name" href="single-product.html">${item.itemName}</a></h6>
                                                <div class="price-box">
                                                    <span class="new-price">${item.itemPrice}</span>
                                                </div>
                                                <div class="product-short_desc">
                                                    <p>${item.itemDescription}</p>
                                                </div>
                                            </div>
                                            <div class="add-actions">
                                                <ul>
                                                    <li><a class="uren-add_cart add-to-cart" id="add-to-cart-pop-up-btn" data-toggle="tooltip" data-placement="top" title="Add To Cart"                         
                                                    data-id="${item.itemId}" data-name="${item.itemName}" data-desc="${item.itemDescription}" 
                                                    data-model="${item.vehicleModel}" data-fuel="${item.fuelType}" data-price="${item.itemPrice}"
                                                    data-qty="${item.itemQty}" data-shopid="${item.shopId}" data-image="${item.itemImage}"><i class="ion-bag"></i></a>
                                                    </li>
                                                    <li><a class="uren-wishlist add-to-wishlist" href="wishlist.html" data-toggle="tooltip" data-placement="top" title="Add To Wishlist"><i
                                                        class="ion-android-favorite-outline"></i></a>
                                                    </li>
                                                    <li class="quick-view-btn" data-toggle="modal" data-target="#exampleModalCenter"><a href="javascript:void(0)" data-toggle="tooltip" data-placement="top" title="Quick View"><i
                                                        class="ion-android-open"></i></a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
`;

                productContainer.append(productHTML); // Append the dynamically created product card
            });
        },
        error: function (xhr) {
            console.log("Error loading items", xhr);
        }
    });
});

$(".add-to_cart").click (function (e){
    e.preventDefault();
    console.log("Add to cart button clicked");

})

$(document).on("click", "#add-to-cart-btn", function (e) {
    e.preventDefault();
    console.log("item name eka "+$(this).data("item-name"))
    $.ajax({
        url: "http://localhost:8082/api/v1/cart/add",
        method: "POST",
        contentType: "application/json",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        data: JSON.stringify({
            itemId: $(this).data("id"),
            qty: 1,
            name: $(this).data("name"),
            image: $(this).data("image"),
            price: $(this).data("price"),
            shopId: $(this).data("shopid"),

        }),
        success: function (response) {
            console.log("Item added to cart", response);
            Swal.fire({
                toast: true,
                position: 'bottom-end', // bottom-right corner
                icon: 'success',
                title: 'Item added to cart',
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true,
                didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                }
            });
            loadCart();
        },
        error: function (xhr, status, error) {
            console.log("Error adding item to cart", xhr);
            Swal.fire({
                toast: true,
                position: 'bottom-end',
                icon: 'error',
                title: 'Failed to add item to cart',
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true,
                didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                }
            });

        }
    })
});

 function loadCart () {
        $.ajax({
            url: "http://localhost:8082/api/v1/cart/get",
            method: "GET",
            contentType: "application/json",
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            },
            success: function (response) {
                console.log(response);
                $("#cart-table").empty();

                let subtotal = 0;

                $(".minicart-list").empty();

                response.data.forEach(item => {
                    let total = item.price * item.qty;
                    subtotal += total;

                    let miniCartListHTML = $(".minicart-list");

                    console.log(response.data)

                    let miniCartList = `
                    <li class="minicart-product" data-cart-id="${item.cartId}">
                                <a class="product-item_remove" href="javascript:void(0)" id="item-remove"><i
                                    class="ion-android-close"></i></a>
                                <div class="product-item_img">
                                    <img src="../${item.image}" alt="${item.name}">
                                </div>
                                <div class="product-item_content">
                                    <a class="product-item_title" href="pages/item-right-sidebar.html">${item.name}</a>
                                    <span class="product-item_quantity">${item.qty} x RS ${item.price}</span>
                                </div>
                            </li>
                `

                    miniCartListHTML.append(miniCartList);
                });

                console.log("Subtotal:"+subtotal);

                $(".total-price").text("RS:" + subtotal)
                $(".item-count").text(response.data.length);
                $(".ammount").text("RS:" + subtotal)


            },
            error: function (error) {
                console.log(error);
            }
        })
    }


$(document).on("click", "#item-remove", function (e) {
    e.preventDefault();
    console.log("item remove clicked");
    let cartId = $(this).closest(".minicart-product").data("cart-id");
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'

    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "http://localhost:8082/api/v1/cart/delete/" + cartId,
                method: "DELETE",
                contentType: "application/json",
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
                success: function (response) {
                    Swal.fire({
                        icon: 'success',
                        title: 'Deleted!',
                        text: 'Item has been deleted.',
                        confirmButtonText: 'Okay'

                    }).then(() => {
                        location.reload();
                    });
                },
                error: function (error) {
                    console.log(error);
                }
            })
        }
    });

});

$(document).ready(function () {
        $.ajax({
            url: "http://localhost:8082/api/v1/cart/get",
            method: "GET",
            contentType: "application/json",
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            },
            success: function (response) {
                console.log(response);
                $("#cart-table").empty();

                let subtotal = 0;

                response.data.forEach(item => {
                    let total = item.price * item.qty;
                    subtotal += total;

                    let miniCartListHTML = $(".minicart-list");

                    console.log(response.data)

                    let miniCartList = `
                    <li class="minicart-product" data-cart-id="${item.cartId}">
                                <a class="product-item_remove" href="javascript:void(0)" id="item-remove"><i
                                    class="ion-android-close"></i></a>
                                <div class="product-item_img">
                                    <img src="../${item.image}" alt="${item.name}">
                                </div>
                                <div class="product-item_content">
                                    <a class="product-item_title" href="pages/item-right-sidebar.html">${item.name}</a>
                                    <span class="product-item_quantity">${item.qty} x RS ${item.price}</span>
                                </div>
                            </li>
                `

                    miniCartListHTML.append(miniCartList);
                });

                console.log("Subtotal:"+subtotal);

                $(".total-price").text("RS:" + subtotal)
                $(".item-count").text(response.data.length);
                $(".ammount").text("RS:" + subtotal)


            },
            error: function (error) {
                console.log(error);
            }
        })
    }
);
$(document).on("click", "#item-remove", function (e) {
    e.preventDefault();
    console.log("item remove clicked");
    let cartId = $(this).closest(".minicart-product").data("cart-id");
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'

    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "http://localhost:8082/api/v1/cart/delete/" + cartId,
                method: "DELETE",
                contentType: "application/json",
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
                success: function (response) {
                    Swal.fire({
                        icon: 'success',
                        title: 'Deleted!',
                        text: 'Item has been deleted.',
                        confirmButtonText: 'Okay'

                    }).then(() => {
                        location.reload();
                    });
                },
                error: function (error) {
                    console.log(error);
                }
            })
        }
    });

});
$("#my-account-btn").click (function (e) {
    e.preventDefault();
    window.location.href = "../../partsLK/frontEnd/pages/my-account.html";
});