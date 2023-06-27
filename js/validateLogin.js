function validateForm() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    // 判断用户名是否为空
    if (username == "") {
        alert("用户名不能为空！");
        return false;
    }

    // 判断密码是否为空
    if (password == "") {
        alert("密码不能为空！");
        return false;
    }

    return true;
}
