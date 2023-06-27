$(document).ready(function() {
    $("#login-form").submit(function(e) {
        e.preventDefault();
        if (validateForm()) {
            alert("登录成功！");
            window.location.href = "index.html";
        }
    });
});
