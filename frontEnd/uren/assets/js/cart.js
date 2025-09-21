$(document).ready(function (e){
    //loadCart();
    console.log("document ready");
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
                $("#cart-table").append(`
                <tr data-cart-id="${item.cartId}">
                                            <td class="uren-product-remove"><a href="javascript:void(0)" id="delete-item"><i class="fa fa-trash"
                                                title="Remove"></i></a></td>
                                            <td class="uren-product-thumbnail text-center"><a href="javascript:void(0)"><img src="../${item.image}" class="responsive-cart-img" alt="${item.name} Cart Image"></a></td>
                                            <style>
                                                .responsive-cart-img {
                                                    max-width: 100px; !important;
                                                    width: 100%;
                                                    height: auto;
                                                    display: block;
                                                    margin: 0 auto; /* Center the image horizontally */
                                                    object-fit: contain;

                                                }
                                                </style>
                                            <td class="uren-product-name"><a href="javascript:void(0)">${item.name}</a></td>
                                            <td class="uren-product-price"><span class="amount">Rs ${item.price}</span></td>
                                            <td class="quantity">
                                                <label>Quantity</label>
                                                <div class="cart-plus-minus">
                                                    <input class="cart-plus-minus-box" value="${item.qty}" type="text">
                                                    <div class="dec qtybutton"><i class="fa fa-angle-down"></i></div>
                                                    <div class="inc qtybutton"><i class="fa fa-angle-up"></i></div>
                                                </div>
                                            </td>
                                            <td class="product-subtotal"><span class="amount">Rs ${total}</span></td>
                                        </tr>
                `);
            });

            console.log("Subtotal:"+subtotal);

            $("#sub-total").text(subtotal)
            $("#total").text(subtotal)


        },
        error: function (error) {
            console.log(error);
        }
    })
})

// Handle quantity increase
$("#cart-table").on("click", ".inc", function () {
    const $input = $(this).siblings("input.cart-plus-minus-box");
    let currentVal = parseInt($input.val());
    if (!isNaN(currentVal)) {
        $input.val(currentVal + 1);
    }
});

// Handle quantity decrease
$("#cart-table").on("click", ".dec", function () {
    const $input = $(this).siblings("input.cart-plus-minus-box");
    let currentVal = parseInt($input.val());
    if (!isNaN(currentVal) && currentVal > 1) {
        $input.val(currentVal - 1);
    }
});




$("#update-cart").click(function (e) {
    e.preventDefault();
    const cartItems = [];
    $("#cart-table tr").each(function () {
        const cartId = $(this).data("cart-id"); // ✅ Get cartId here
        const name = $(this).find(".uren-product-name a").text().trim();
        const priceText = $(this).find(".uren-product-price .amount").text().replace('Rs', '').trim();
        const qty = $(this).find(".cart-plus-minus-box").val();

        if (name && priceText && qty) {
            const price = parseFloat(priceText);
            cartItems.push({
                cartId: cartId,
                name: name,
                price: price,
                qty: parseInt(qty)
            });
        }
    });
    console.log(cartItems)

    $.ajax({
        url: "http://localhost:8082/api/v1/cart/update",
        method: "PUT",
        contentType: "application/json",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        data: JSON.stringify(cartItems),
        success: function (response) {
            Swal.fire({
                toast: true,
                position: 'bottom-end', // bottom-right corner
                icon: 'success',
                title: 'Cart Updated Successfully',
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true,
                didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                }
            });
            // Wait 3 seconds before reloading
            setTimeout(function () {
                location.reload();
            }, 3000);
        },
        error: function (error) {
            Swal.fire({
                toast: true,
                position: 'bottom-end',
                icon: 'error',
                title: 'Failed to Update cart',
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true,
                didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                }
            });
        }
    });
})

$(document).on("click", "#delete-item", function (e) {
    e.preventDefault();

    const cartId = $(this).closest("tr").data("cart-id");
    console.log("cart id " + cartId);

    Swal.fire({
        title: 'Are you sure?',
        text: "Do you really want to delete this item?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Yes, delete it!',
        cancelButtonText: 'Cancel'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "http://localhost:8082/api/v1/cart/delete/" + cartId,
                method: "DELETE",
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
                success: function (response) {
                    console.log("Item deleted:", response);
                    Swal.fire(
                        'Deleted!',
                        'Item has been deleted.',
                        'success'
                    ).then(() => {
                        location.reload(); // Refresh after confirmation alert
                    });
                },
                error: function (error) {
                    console.error("Error deleting item:", error);
                    Swal.fire(
                        'Failed!',
                        'Failed to delete the item.',
                        'error'
                    );
                }
            });
        }
    });
});

$("#proceed-to-checkout").click(function () {
    console.log("Proceed to checkout button clicked");
    window.location.href = "../../../../../partsLK/frontEnd/pages/checkout.html";
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


                let minileftCartListHTML = $(".minicart-list");

                console.log(response.data)

                let miniLeftCartList = `
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

                minileftCartListHTML.append(miniLeftCartList);

                $(".total-price").text("RS:" + subtotal)
                $(".item-count").text(response.data.length);
                $(".ammount").text("RS:" + subtotal)

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

/*$(document).ready(function () {
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
);*/
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
