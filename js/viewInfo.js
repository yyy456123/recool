const info = JSON.parse(localStorage.getItem('info') || '[]');
const infoList = document.getElementById('infoList');

for (let i = 0; i < info.length; i++) {
    const item = info[i];
    const li = document.createElement('li');
    li.textContent = `Name: ${item.name}, Age: ${item.age}`;
    infoList.appendChild(li);
}
