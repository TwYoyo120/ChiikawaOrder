document.addEventListener('DOMContentLoaded', () => {
    loadHeaderAndFooter();
    loadOrders();
});

function loadHeaderAndFooter() {
    fetch('./html/header-front.html')
        .then(response => response.text())
        .then(data => document.getElementById('header-container').innerHTML = data)
        .catch(error => console.error('Error loading header:', error));

    fetch('./html/footer-front.html')
        .then(response => response.text())
        .then(data => document.getElementById('footer-container').innerHTML = data)
        .catch(error => console.error('Error loading footer:', error));
}

function loadOrders() {
    fetch('http://localhost:8080/ChiikawaGame/OrderServlet.do?action=getAllOrders')
        .then(response => {
            if (!response.ok) {
                throw new Error(`网络响应不正常 (${response.status}: ${response.statusText})`);
            }
            return response.json();
        })
        .then(orders => {
            displayOrders(orders);
        })
        .catch(error => {
            console.error('Error loading orders:', error);
            alert('无法加载订单，请稍后再试');
        });
}

function displayOrders(orders) {
    const ordersDiv = document.getElementById('orders');
    ordersDiv.innerHTML = '';

    if (!orders || orders.length === 0) {
        ordersDiv.innerHTML = '<p class="text-center text-muted">目前没有任何订单。</p>';
        return;
    }

    orders.forEach(order => {
        ordersDiv.innerHTML += `
            <div class="col-md-4 mb-3">
                <div class="card">
                    <div class="card-header">
                        訂單號：${order.orderId}
                    </div>
                    <div class="card-body">
                        <p>總金額：NT$${order.totalAmount}</p>
                        <p>狀態：${order.status}</p>
                        <p>物流狀態：${order.shippingInfo.status}</p>
                        <a href="order-details.html?orderId=${order.orderId}" class="btn btn-primary">查看詳情</a>
                        <button class="btn btn-secondary mt-2" onclick="updateOrderStatus(${order.orderId})">更新狀態</button>
                    </div>
                </div>
            </div>
        `;
    });
}

function updateOrderStatus(orderId) {
    const newStatus = prompt("请输入新的订单状态（例如：PAID、SHIPPED、COMPLETED、CANCELLED）：");
    if (newStatus) {
        fetch('http://localhost:8080/ChiikawaGame/OrderServlet.do', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: `action=updateOrderStatus&orderId=${orderId}&status=${newStatus}`
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('更新订单状态失败');
            }
            return response.json();
        })
        .then(data => {
            alert(data.message);
            loadOrders();
        })
        .catch(error => {
            console.error('Error updating order status:', error);
            alert('无法更新订单状态，请稍后再试');
        });
    }
}
