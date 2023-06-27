// register.js

$(function() {
    // 当用户提交表单时触发
    $('form#register-form').submit(function(e) {
        e.preventDefault();
        var username = $('#username').val();
        var password = $('#password').val();
        var confirm_password = $('#confirm_password').val();
        var email = $('#email').val();
        // 检查密码强度
        if (!checkPasswordStrength(password)) {
            alert('The password should be at least 8 characters long and contain at least one lowercase letter, one uppercase letter, one number, and one symbol.');
            return;
        }
        // 检查用户名是否已存在
        checkUsernameExist(username).done(function(data) {
            if (data.exist) {
                alert('The username already exists. Please choose another username.');
            } else {
                // 检查邮箱地址格式是否正确
                if (!checkEmailFormat(email)) {
                    alert('Please enter a valid email address.');
                    return;
                }
                // 提交表单
                $('form#register-form').unbind('submit').submit();
            }
        });
    });
});
