document.addEventListener('DOMContentLoaded', () => {
    loadHeaderAndFooter();
    setupAddToCart();
});

function loadHeaderAndFooter() {
    fetch('html/header-front.html')
        .then(response => response.ok ? response.text() : Promise.reject('Error loading header'))
        .then(data => document.getElementById('header-container').innerHTML = data)
        .catch(error => {
            console.error(error);
            document.getElementById('header-container').innerHTML = '<p>無法加載頭部內容。</p>';
        });

    fetch('html/footer-front.html')
        .then(response => response.ok ? response.text() : Promise.reject('Error loading footer'))
        .then(data => document.getElementById('footer-container').innerHTML = data)
        .catch(error => {
            console.error(error);
            document.getElementById('footer-container').innerHTML = '<p>無法加載尾部內容。</p>';
        });
}

function setupAddToCart() {
    const addToCartButton = document.getElementById('add-to-cart-button');
    addToCartButton.addEventListener('click', addToCart);
}

function addToCart() {
    // 定義商品信息
    const productId = 1;
    const productName = '龍貓拼圖';
    const price = 500;

    // 獲取數量
    const quantityInput = document.getElementById('quantity-input');
    const quantity = parseInt(quantityInput.value);

    // 校驗數量
    if (isNaN(quantity) || quantity <= 0 || quantity > 100) {
        alert("請輸入有效的數量（1-100）。");
        return;
    }

    // 獲取購物車數據
    const cart = JSON.parse(localStorage.getItem('cart')) || [];
    const existingProductIndex = cart.findIndex(item => item.productId === productId);

    // 更新購物車
    if (existingProductIndex > -1) {
        cart[existingProductIndex].quantity += quantity;
    } else {
        cart.push({ productId, productName, price, quantity });
    }

    // 保存購物車到 localStorage
    localStorage.setItem('cart', JSON.stringify(cart));
    alert(`${productName} 已加入購物車`);
}
