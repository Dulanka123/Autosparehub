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
        window.location.href = "../../partsLK/frontEnd/pages/register-page.html";
    }
});
$("#my-account-btn").click(function(e) {
    console.log("My Account button clicked");
    const token = localStorage.getItem("token");

    console.log(token);

    if (token){
        window.location.href = "../../partsLK/frontEnd/pages/my-account.html";
    }else {
        window.location.href = "../../partsLK/frontEnd/pages/login-page.html";
    }
});
$("#account-logout-tab").click(function(e) {
    localStorage.removeItem("token");
    window.location.href = "../../frontEnd/pages/login-page.html";
});

$("#sign-out").click(function(e) {
    localStorage.removeItem("token");
    window.location.href = "../../partsLK/frontEnd/pages/login-page.html";
});

$(document).ready(function () {

    $.ajax({
        url: "http://localhost:8082/api/v1/user/get",
        method: "GET",
        contentType: "application/json",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        success: function (response) {
            console.log(response);

            // Check if response.data is a non-empty object
            if (response.data && Object.keys(response.data).length > 0) {
                $(".without-login").hide();
                $(".with-login").show();

                $("#username").text(response.data.name);
                $("#username-again").text(response.data.name);

                $(".user-address").text(response.data.address);
                $("#account-details-name").val(response.data.name);
                $("#account-details-email").val(response.data.email);
                $("#account-details-mobile").val(response.data.mobile);
                $("#account-details-address").val(response.data.address);
                $("#account-details-nic").val(response.data.nic);
                $("#account-details-dob").val(response.data.dob);

            } else {
                $(".with-login").hide();
                $(".without-login").show();
            }
        },
        error: function (error) {
            console.log("Error fetching user:", error);
            $(".with-login").hide();
            $(".without-login").show();
        }
    });
});


$("#save-changes").click(function (e) {
    e.preventDefault();

    let newpass =$("#account-details-newpass").val();
    let conformpass = $("#account-details-confpass").val();

    if (conformpass === newpass) {
        $.ajax({
            url: "http://localhost:8082/api/v1/user/update",
            method: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            },
            data: JSON.stringify({
                name: $("#account-details-name").val(),
                email: $("#account-details-email").val(),
                mobile: $("#account-details-mobile").val(),
                address: $("#account-details-address").val(),
                nic: $("#account-details-nic").val(),
                dob: $("#account-details-dob").val(),
                oldPassword: $("#account-details-oldpass").val(),
                newPassword: newpass
            }),
            success: function (response) {
                console.log(response);
                if (response.code === 200) {
                    clearForm();
                    Swal.fire({
                        icon: 'success',
                        title: 'Changes Saved!',
                        text: 'Your changes have been saved successfully.',
                        confirmButtonText: 'Okay'
                    });
                }
            },
            error: function (error) {
                console.log("Error saving changes:", error);
                Swal.fire({
                    icon: 'error',
                    title: 'Error Saving Changes',
                    text: 'Please Check Current Password And Try Again.',
                    confirmButtonText: 'Okay'
                });
            }
        })
    }else {
        Swal.fire({
            icon: 'error',
            title: 'Error Saving Changes',
            text: 'New Password And Conform Password Mismatch',
            confirmButtonText: 'Okay'
        });
    }
});

function clearForm(){
    $("#account-details-oldpass").val("")
    $("#account-details-newpass").val("")
    $("#account-details-confpass").val("")

}