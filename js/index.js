// 获取当前时间
function getCurrentTime() {
    const now = new Date(); // 创建一个日期对象
    const hours = now.getHours().toString().padStart(2, '0'); // 获取小时，并保证为两位数
    const minutes = now.getMinutes().toString().padStart(2, '0'); // 获取分钟，并保证为两位数
    const seconds = now.getSeconds().toString().padStart(2, '0'); // 获取秒钟，并保证为两位数
    return `${hours}:${minutes}:${seconds}`; // 返回格式化后的时间字符串
}

// 更新页面上的时间
function updateTime() {
    const currentTime = getCurrentTime(); // 获取当前时间
    const timeElement = document.getElementById('current-time'); // 获取显示时间的元素
    timeElement.innerHTML = currentTime; // 更新元素内容
}

// 每秒钟更新一次时间
setInterval(updateTime, 1000);

/* 滚动到顶部按钮 */
const scrollToTopButton = document.querySelector('#scroll-to-top');

window.addEventListener('scroll', () => {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        scrollToTopButton.style.display = 'block';
    } else {
        scrollToTopButton.style.display = 'none';
    }
});

scrollToTopButton.addEventListener('click', () => {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
});


/* 轮播图 */
const carouselImages = [
    'https://picsum.photos/id/237/960/500',
    'https://picsum.photos/id/239/960/500',
    'https://picsum.photos/id/240/960/500',
];

let currentImageIndex = 0;

const carouselImage = document.querySelector('.carousel-image');
const nextButton = document.querySelector('#next-button');
const prevButton = document.querySelector('#prev-button');

function displayImage() {
    carouselImage.src = carouselImages[currentImageIndex];
}

displayImage();

nextButton.addEventListener('click', () => {
    currentImageIndex++;
    if (currentImageIndex >= carouselImages.length) {
        currentImageIndex = 0;
    }
    displayImage();
});

prevButton.addEventListener('click', () => {
    currentImageIndex--;
    if (currentImageIndex < 0) {
        currentImageIndex = carouselImages.length - 1;
    }
    displayImage();
});


/* 导航菜单 */
const hamburgerMenu = document.querySelector('#hamburger-menu');
const navMenu = document.querySelector('#nav-menu');

hamburgerMenu.addEventListener('click', () => {
    navMenu.classList.toggle('show-menu');
});

const navLinks = document.querySelectorAll('.nav-link');

navLinks.forEach(link => {
    link.addEventListener('click', () => {
        navMenu.classList.remove('show-menu');
    });
});


/* 模态框 */
const openModalButtons = document.querySelectorAll('[data-modal-target]');
const closeModalButtons = document.querySelectorAll('[data-close-button]');
const overlay = document.querySelector('#overlay');

openModalButtons.forEach(button => {
    button.addEventListener('click', () => {
        const modal = document.querySelector(button.dataset.modalTarget);
        openModal(modal);
    });
});

closeModalButtons.forEach(button => {
    button.addEventListener('click', () => {
        const modal = button.closest('.modal');
        closeModal(modal);
    });
});

overlay.addEventListener('click', () => {
    const modals = document.querySelectorAll('.modal.show-modal');
    modals.forEach(modal => {
        closeModal(modal);
    });
});

function openModal(modal) {
    if (modal == null) return;
    modal.classList.add('show-modal');
    overlay.classList.add('show-overlay');
}

function closeModal(modal) {
    if (modal == null) return;
    modal.classList.remove('show-modal');
    overlay.classList.remove('show-overlay');
}


/* 表单验证 */
const form = document.querySelector('#signup-form');
const emailInput = document.querySelector('#email');
const passwordInput = document.querySelector('#password');
const confirmPasswordInput = document.querySelector('#confirm-password');
const errorMessages = document.querySelectorAll('.error-message');
const successMessage = document.querySelector('.success-message');

form.addEventListener('submit', e => {
    let isError = false;

    if (!isValidEmail(emailInput.value)) {
        displayErrorMessage(errorMessages[0]);
        isError = true;
    } else {
        hideErrorMessage(errorMessages[0]);
    }

    if (!isValidPassword(passwordInput.value)) {
        displayErrorMessage(errorMessages[1]);
        isError = true;
    } else {
        hideErrorMessage(errorMessages[1]);
    }

    if (passwordInput.value !== confirmPasswordInput.value) {
        displayErrorMessage(errorMessages[2]);
        isError = true;
    } else {
        hideErrorMessage(errorMessages[2]);
    }

    if (isError) {
        e.preventDefault();
    } else {
        showSuccessMessage();
    }
});

function isValidEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

function isValidPassword(password) {
    return password.length >= 8;
}

function displayErrorMessage(errorMessage) {
    errorMessage.style.display = 'block';
}

function hideErrorMessage(errorMessage) {
    errorMessage.style.display = 'none';
}

function showSuccessMessage() {
    successMessage.style.display = 'block';
}


/* 平滑滚动 */
const smoothScrollLinks = document.querySelectorAll('a[href^="#"]');
smoothScrollLinks.forEach(link => {
    link.addEventListener('click', e => {
        e.preventDefault();
        const targetId = link.getAttribute('href');
        const targetElement = document.querySelector(targetId);
        const yOffset = -70;
        const y = targetElement.getBoundingClientRect().top + window.pageYOffset + yOffset;

        window.scrollTo({
            top: y,
            behavior: 'smooth'
        });
    });
});


/* 标签切换 */
const tabButtons = document.querySelectorAll('.tab-button');
const tabContents = document.querySelectorAll('.tab-content');

tabButtons.forEach(button => {
    button.addEventListener('click', () => {
        const target = button.dataset.target;
        tabContents.forEach(tabContent => {
            tabContent.classList.remove('show-tab');
        });
        document.querySelector(target).classList.add('show-tab');

        tabButtons.forEach(button => {
            button.classList.remove('active');
        });
        button.classList.add('active');
    });
});


/* 滑动菜单 */
const slides = document.querySelectorAll('.slide');
const slideContainer = document.querySelector('.slide-container');
const prevSlideButton = document.querySelector('#prev-slide');
const nextSlideButton = document.querySelector('#next-slide');

let currentSlideIndex = 0;
let slideWidth = slides[0].clientWidth;

slideContainer.style.width = `${slides.length * slideWidth}px`;

prevSlideButton.addEventListener('click', () => {
    currentSlideIndex--;
    if (currentSlideIndex < 0) {
        currentSlideIndex = slides.length - 1;
    }
    slideContainer.style.transform = `translateX(${-slideWidth * currentSlideIndex}px)`;
});

nextSlideButton.addEventListener('click', () => {
    currentSlideIndex++;
    if (currentSlideIndex >= slides.length) {
        currentSlideIndex = 0;
    }
    slideContainer.style.transform = `translateX(${-slideWidth * currentSlideIndex}px)`;
});

window.addEventListener('resize', () => {
    slideWidth = slides[0].clientWidth;
    slideContainer.style.width = `${slides.length * slideWidth}px`;
    slideContainer.style.transform = `translateX(${-slideWidth * currentSlideIndex}px)`;
});
