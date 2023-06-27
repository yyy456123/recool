// validateRegister.js

// 检查用户名是否已存在
function checkUsernameExist(username) {
    return $.ajax({
        url: '/check_username_exist',
        data: {username: username},
        dataType: 'json',
        type: 'GET'
    });
}

// 检查密码强度
function checkPasswordStrength(password) {
    // 密码强度必须大于8位，包含数字、符号以及大小写字母等
    return /(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d!@#$%^&*()_+]{8,}/.test(password);
}

// 检查邮箱地址格式是否正确
function checkEmailFormat(email) {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}
