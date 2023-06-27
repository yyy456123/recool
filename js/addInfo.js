function submitForm() {
    // 获取表单数据
    const name = document.getElementById('name').value;
    const age = document.getElementById('age').value;

    // 将数据存储到本地存储中
    const info = JSON.parse(localStorage.getItem('info') || '[]');
    info.push({ name, age });
    localStorage.setItem('info', JSON.stringify(info));

    // 页面跳转
    location.href = '../html/viewInfo.html';
}
