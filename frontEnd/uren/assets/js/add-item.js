$(document).ready(function () {
    loadCart();
    let shopId;
    const token = localStorage.getItem("token");

    // Check if token is available
    if (!token || token.trim() === "" || token === "null") {
        Swal.fire({
            icon: 'error',
            title: 'Login Required!',
            text: 'Please login first',
            confirmButtonText: 'Okay'
        }).then(() => {
            window.location.href = "../pages/login-page.html";
        });
        return; // Stop further execution
    }

    // Fetch Shop ID
    $.ajax({
        url: 'http://localhost:8082/api/v1/shop/search',
        type: 'GET',
        headers: {
            "Authorization": "Bearer " + token
        },
        success: function (response) {
            shopId = response.data.shopId;
            console.log("Shop ID:", shopId);
            $("#shopId").val(shopId); // Set shopId in the hidden input
        },
        error: function (xhr) {
            let data = xhr.responseJSON?.data || "Something went wrong";
            console.log("Error fetching shopId:", data);
        }
    });
});

// Submit Form
$("#addItem").click(function (e) {
    e.preventDefault();
    const formData = new FormData($('#item-inputs')[0]);

    // Append shopId manually
    formData.append("shopId", $("#shopId").val());

    $.ajax({
        url: "http://localhost:8082/api/v1/item/save",
        method: "POST",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        data: formData,
        cache: false,
        processData: false,
        contentType: false,

        success: function (response) {
            console.log(response);
            if (response.code === 201) {
                Swal.fire({
                    icon: 'success',
                    title: 'Item Added!',
                    text: 'Item has been added successfully.',
                    confirmButtonText: 'Okay'
                }).then(() => {
                    window.location.href = "../pages/my-shop.html";
                });
            }
        },
        error: function (xhr) {
            console.log("Full Error Response:", xhr);

            console.log(xhr.responseJSON);

            let errorMessage = "Failed to add item."; // Default message

            // Check if the response contains a message
            if (xhr.responseJSON?.message) {
                errorMessage = xhr.responseJSON.message; // Use the backend message
            } else if (xhr.status === 413) {
                errorMessage = "File size exceeds the maximum upload limit! Please upload a smaller file.";
            } else if (xhr.status === 406) {
                errorMessage = "Not Acceptable! The request could not be processed.";
            } else if (xhr.status === 0) {
                errorMessage = "Failed to connect to the server. Possible CORS issue or server down!";
            } else if (xhr.status === 403) {
                errorMessage = "You are not authorized to perform this action.";
            } else if (xhr.status === 404) {
                errorMessage = "API endpoint not found! Check the server URL.";
            } else if (xhr.status === 500) {
                errorMessage = "Internal Server Error! Please try again later.";
            }
            // Show the error in a SweetAlert popup
            Swal.fire({
                icon: 'error',
                title: 'Item Add Failed!',
                text: errorMessage,
                confirmButtonText: 'Okay'
            });
        }
    });
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



