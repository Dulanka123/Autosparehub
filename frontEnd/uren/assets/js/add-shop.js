$("#registerShop").click (function(e) {
    e.preventDefault();
    console.log("Register Shop button clicked");
    $.ajax({
        url: "http://localhost:8082/api/v1/shop/save",
        method: "POST",
        contentType: "application/json",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        data: JSON.stringify({
            shopName: $("#shopName").val(),
            shopAddress: $("#shopAddress").val(),
            shopContact: $("#shopContact").val(),
            shopEmail: $("#shopEmail").val(),
            shopOwner: $("#shopOwner").val(),
            shopOwnerContact: $("#shopOwnerContact").val(),
            shopOwnerEmail: $("#shopOwnerEmail").val(),
            shopOwnerNIC: $("#shopOwnerNIC").val(),
            shopOwnerDOB: $("#shopOwnerDOB").val(),


        }),
        success: function (response) {
            console.log(response);
            if (response.code === 201) {
                Swal.fire({
                    icon: 'success',
                    title: 'Registration Successful!',
                    text: 'You have been registered successfully.',
                    confirmButtonText: 'Okay'
                }).then((result) => {
                    if (result.isConfirmed) {
                        location.reload();
                    }
                })
                console.log(response.data.token);
                localStorage.setItem("token", response.data.token);
                window.location.href = "../../index.html";
            }
        },
        error: function (xhr, status, error, response) {
            let data = xhr.responseJSON.data;
            console.log(data);

            if (data.shopName != null) {
                Swal.fire({
                    icon: 'error',
                    title: 'Registration Failed!',
                    text: data.shopName,
                    confirmButtonText: 'Okay'
                });
            } else if (data.shopAddress != null) {
                Swal.fire({
                    icon: 'error',
                    title: 'Registration Failed!',
                    text: data.shopAddress,
                    confirmButtonText: 'Okay'
                });
            } else if (data.shopContact != null) {
                Swal.fire({
                    icon: 'error',
                    title: 'Registration Failed!',
                    text: data.shopContact,
                    confirmButtonText: 'Okay'
                });
            } else if (data.shopEmail != null) {
                Swal.fire({
                    icon: 'error',
                    title: 'Registration Failed!',
                    text: data.shopEmail,
                    confirmButtonText: 'Okay'
                });
            } else if (data.shopOwner != null) {
                Swal.fire({
                    icon: 'error',
                    title: 'Registration Failed!',
                    text: data.shopOwner,
                    confirmButtonText: 'Okay'
                });
            } else if (data.shopOwnerContact != null) {
                Swal.fire({
                    icon: 'error',
                    title: 'Registration Failed!',
                    text: data.shopOwnerContact,
                    confirmButtonText: 'Okay'
                });
            } else if (data.shopOwnerEmail != null) {
                Swal.fire({
                    icon: 'error',
                    title: 'Registration Failed!',
                    text: data.shopOwnerEmail,
                    confirmButtonText: 'Okay'
                });
            } else if (data.shopOwnerNIC != null) {
                Swal.fire({
                    icon: 'error',
                    title: 'Registration Failed!',
                    text: data.shopOwnerNIC,
                    confirmButtonText: 'Okay'
                });
            } else if (data.shopOwnerDOB != null) {
                Swal.fire({
                    icon: 'error',
                    title: 'Registration Failed!',
                    text: data.shopOwnerDOB,
                    confirmButtonText: 'Okay'
                });
            }
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
    //loadCart();
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