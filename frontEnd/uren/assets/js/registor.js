$("#register").on("click", function (e) {
    e.preventDefault();
    console.log("Register button clicked");

    // Basic client-side validation (optional quick checks)
    const requiredFields = ["#name", "#email", "#mobile", "#address", "#password"]; 
    for (const sel of requiredFields) {
        if (!$(sel).val()) {
            Swal.fire({
                icon: 'warning',
                title: 'Missing Field',
                text: 'Please fill all required fields.',
            });
            return;
        }
    }

    const payload = {
        name: $("#name").val(),
        email: $("#email").val(),
        mobile: $("#mobile").val(),
        address: $("#address").val(),
        nic: $("#nic").val(),
        dob: $("#dob").val(), // Expected format yyyy-MM-dd
        password: $("#password").val(),
        role: "USER"
    };

    $.ajax({
        url: "http://localhost:8082/api/v1/user/register",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(payload),
        success: function (response) {
            console.log("Register success response", response);
            if (response && response.code === 201 && response.data && response.data.token) {
                Swal.fire({
                    icon: 'success',
                    title: 'Registration Successful!',
                    text: 'You have been registered successfully.',
                    confirmButtonText: 'Okay'
                }).then(result => {
                    if (result.isConfirmed) {
                        localStorage.setItem("token", response.data.token);
                        window.location.href = "../../index.html";
                    }
                });
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'Unexpected Response',
                    text: 'Could not verify registration result.'
                });
            }
        },
        error: function (xhr, status, errorThrown) {
            // Safe extraction
            const resp = xhr.responseJSON || {};
            const code = resp.code;
            const message = resp.message || 'Registration failed';
            const data = resp.data || {}; // May be null for 406 -> becomes {}

            console.log("Register error", resp);

            // Field specific messages in priority order
            const fieldOrder = ['password','email','mobile','address','nic','dob','name'];
            for (const field of fieldOrder) {
                if (data && data[field]) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Registration Failed!',
                        text: data[field],
                        confirmButtonText: 'Okay'
                    });
                    return;
                }
            }

            // Handle duplicate email (406) or generic backend message
            if (code === 406) {
                Swal.fire({
                    icon: 'error',
                    title: 'Email Already Used',
                    text: message,
                    confirmButtonText: 'Okay'
                });
                return;
            }

            // Spring validation (400) default structure fallback
            if (xhr.status === 400 && resp.errors) {
                const first = resp.errors[0];
                Swal.fire({
                    icon: 'error',
                    title: 'Validation Error',
                    text: (first && first.defaultMessage) || message,
                    confirmButtonText: 'Okay'
                });
                return;
            }

            Swal.fire({
                icon: 'error',
                title: 'Registration Failed!',
                text: message,
                confirmButtonText: 'Okay'
            });
        }
    });
});
