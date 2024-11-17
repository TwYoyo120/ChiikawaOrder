// 獲取購物車數據
function getCart() {
    return JSON.parse(localStorage.getItem('cart')) || [];
}

// 提交訂單
function submitOrder() {
    const address = document.getElementById('address').value.trim();
    const recipientName = document.getElementById('recipientName').value.trim();
    const contactNumber = document.getElementById('contactNumber').value.trim();

    if (!address || !recipientName || !contactNumber) {
        alert("請完整填寫所有信息");
        return;
    }

    const cart = getCart();

    if (cart.length === 0) {
        alert("購物車是空的，無法提交訂單！");
        return;
    }

    const totalAmount = calculateTotal(cart);

    const orderData = {
        action: "createOrder",
        totalAmount: totalAmount,
        items: cart,
        address: address,
        recipientName: recipientName,
        contactNumber: contactNumber,
        status: "PENDING"
    };

    fetch('http://localhost:8080/ChiikawaGame/OrderServlet.do', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(orderData)
    })
    .then(response => {
        if (!response.ok) {
            return response.text().then(text => {
                throw new Error('網路響應不正常，狀態碼：' + response.status + '，信息：' + text);
            });
        }
        return response.json();
    })
    .then(data => {
        alert("訂單已確認！");
        localStorage.removeItem('cart'); // 清空購物車
        window.location.href = 'order-confirmation.html'; // 跳轉到訂單確認頁面
    })
    .catch((error) => {
        console.error('錯誤：', error);
        alert("提交訂單失敗，請稍後再試。錯誤信息：" + error.message);
    });
}

// 計算總金額
function calculateTotal(cart) {
    let totalPrice = 0;
    cart.forEach(item => {
        totalPrice += item.price * item.quantity;
    });
    return totalPrice + 50; // 假設運費是50元
}
