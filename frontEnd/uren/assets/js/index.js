$("#login-btn").click(function(e) {
    console.log("Login button clicked");
    const token = localStorage.getItem("token");

    if (token){
        window.location.href = "../../partsLK/frontEnd/pages/my-account.html";
    }else {
        window.location.href = "../../partsLK/frontEnd/pages/login-page.html";
    }
});

$("#register-btn").click(function(e) {
    console.log("Register button clicked");
    const token = localStorage.getItem("token");

    if (token){
        window.location.href = "../../partsLK/frontEnd/pages/my-account.html";
    }else {
        console.log("Token is null");
        window.location.href = "../../partsLK/frontEnd/pages/register-page.html";
    }
});
$("#my-account-btn").click(function(e) {
    console.log("My Account button clicked");
    const token = localStorage.getItem("token");
    if (token){
        console.log("Token is not null");
        /* window.location.href = "../../partsLK/frontEnd/pages/my-account.html";*/
    }else {
        window.location.href = "../../partsLK/frontEnd/pages/login-page.html";
    }
});

$("#shop-nav-btn").click(function(e) {
    console.log("Shop button clicked");
    const token = localStorage.getItem("token");
    if (token){
            $.ajax({
                url: "http://localhost:8082/api/v1/shop/search",
                method: "GET",
                contentType: "application/json",
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("token")
                },
                success: function (response) {
                    console.log(response.data);
                    if (response.data.shopId != null) {
                         window.location.href = "../../partsLK/frontEnd/pages/my-shop.html";
                    }
                },
                error: function (xhr, status, error) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error!',
                        text: 'You have not created a shop yet Please create a shop first',
                        confirmButtonText: 'Okay'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.href = "../../partsLK/frontEnd/pages/add-shop.html";
                        }
                    });
                }
            })
    }else {
        Swal.fire({
            icon: 'error',
            title: 'Login Required!',
            text: 'Please login first',
            confirmButtonText: 'Okay'
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = "../../partsLK/frontEnd/pages/login-page.html";
            }
        });
    }
});

$(document).ready(function () {
    findRole();
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
                                    <img src="${item.image}" alt="${item.name}">
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

function findRole(){
    $.ajax({
        url: "http://localhost:8082/api/v1/user/get",
        method: "GET",
        contentType: "application/json",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        success: function (response) {
            const adminLink = document.querySelector('.admin-menu a');

            if (response.data.role === "ADMIN") {
                adminLink.removeAttribute('hidden');
            }
        }
})
}
$(document).on("click", ".admin-menu a", function (e) {
    e.preventDefault();

    window.location.href = "../../partsLK/frontEnd/pages/admin-controller.html";
});