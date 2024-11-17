// 獲取購物車數據
function getCart() {
    return JSON.parse(localStorage.getItem('cart')) || [];
}

// 保存購物車數據
function saveCart(cart) {
    localStorage.setItem('cart', JSON.stringify(cart));
}

// 加載購物車內容
function loadCart() {
    const cart = getCart();
    const cartItemsDiv = document.getElementById('cart-items');
    cartItemsDiv.innerHTML = '';
    let totalPrice = 0;

    if (cart.length === 0) {
        cartItemsDiv.innerHTML = '<p>購物車是空的。</p>';
        document.getElementById('checkout-btn').disabled = true;
        return;
    } else {
        document.getElementById('checkout-btn').disabled = false;
    }

    cart.forEach((item, index) => {
        const itemTotal = item.price * item.quantity;
        totalPrice += itemTotal;

        cartItemsDiv.innerHTML += `
            <div class="cart-item card mb-3">
                <div class="row g-0">
                    <div class="col-md-2">
                        <img src="${item.image}" class="img-fluid rounded-start" alt="${item.productName}">
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title">
                                <a href="product.html?id=${item.productId}">${item.productName}</a>
                            </h5>
                            <p class="card-text">價格：NT$${item.price}</p>
                            <p class="card-text">
                                數量: 
                                <input type="number" min="1" value="${item.quantity}" 
                                onchange="updateQuantity(${index}, this.value)">
                            </p>
                            <p class="card-text">小計: NT$${itemTotal}</p>
                        </div>
                    </div>
                    <div class="col-md-2 d-flex align-items-center justify-content-center">
                        <button class="remove-btn btn btn-danger" onclick="confirmRemoveFromCart(${index})">刪除</button>
                    </div>
                </div>
            </div>`;
    });

    const shippingCost = 50;
    totalPrice += shippingCost;
    document.getElementById('total').innerHTML = `<h4>總計：NT$${totalPrice.toFixed(2)}（含運費 NT$${shippingCost}）</h4>`;
}

// 更新商品數量
function updateQuantity(index, quantity) {
    const cart = getCart();
    cart[index].quantity = parseInt(quantity);
    if (cart[index].quantity <= 0) {
        cart.splice(index, 1);
    }
    saveCart(cart);
    loadCart();
}

// 確認刪除商品
function confirmRemoveFromCart(index) {
    if (confirm("您確定要刪除此商品嗎？")) {
        removeFromCart(index);
    }
}

// 刪除購物車中的商品
function removeFromCart(index) {
    const cart = getCart();
    cart.splice(index, 1);
    saveCart(cart);
    loadCart();
}

// 結帳
function checkout() {
    const cart = getCart();
    if (cart.length === 0) {
        alert("購物車是空的，無法結帳！");
        return;
    }
    window.location.href = 'checkout.html';
}

// 頁面加載時顯示購物車內容
window.onload = function () {
    loadCart();
}
