document.addEventListener('DOMContentLoaded', () => {
    loadOrderDetails();
    loadHeaderAndFooter();
});

function loadHeaderAndFooter() {
    fetch('html/header-front.html')
        .then(response => response.ok ? response.text() : Promise.reject('Error loading header'))
        .then(data => document.getElementById('header-container').innerHTML = data)
        .catch(error => console.error('Error loading header:', error));

    fetch('html/footer-front.html')
        .then(response => response.ok ? response.text() : Promise.reject('Error loading footer'))
        .then(data => document.getElementById('footer-container').innerHTML = data)
        .catch(error => console.error('Error loading footer:', error));
}

function getQueryParam(param) {
    let urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

function loadOrderDetails() {
    const orderId = parseInt(getQueryParam('orderId'), 10);

    if (isNaN(orderId) || orderId <= 0) {
        alert('無效的訂單 ID');
        return;
    }

    fetch(`/ChiikawaGame/OrderServlet.do?orderId=${encodeURIComponent(orderId)}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`网络响应不正常 (${response.status}: ${response.statusText})`);
            }
            return response.json();
        })
        .then(order => {
            displayOrderDetails(order);
        })
        .catch(error => {
            console.error('Error loading order details:', error);
            alert('無法加載訂單詳情，請稍後再試');
        });
}

function displayOrderDetails(order) {
    const orderDetailsDiv = document.getElementById('order-details');
    orderDetailsDiv.innerHTML = '';

    if (order) {
        const orderInfo = document.createElement('div');
        orderInfo.innerHTML = `
            <h4>訂單編號：${escapeHtml(order.orderId)}</h4>
            <p><strong>訂單日期：</strong> ${new Date(order.orderDate).toLocaleString()}</p>
            <p><strong>總金額：</strong> NT$${order.totalAmount}</p>
            <p><strong>訂單狀態：</strong> ${escapeHtml(order.status)}</p>
            <p><strong>物流狀態：</strong> ${escapeHtml(order.shippingInfo.status)}</p>
            <h5 class="mt-4">收件人資訊：</h5>
            <p><strong>姓名：</strong> ${escapeHtml(order.shippingInfo.recipientName)}</p>
            <p><strong>地址：</strong> ${escapeHtml(order.shippingInfo.address)}</p>
            <p><strong>聯絡電話：</strong> ${escapeHtml(order.shippingInfo.contactNumber)}</p>
            <h5 class="mt-4">商品列表：</h5>
        `;

        const itemsTable = document.createElement('table');
        itemsTable.className = 'table table-striped';

        const thead = document.createElement('thead');
        thead.innerHTML = `
            <tr>
                <th>商品名稱</th>
                <th>單價</th>
                <th>數量</th>
                <th>小計</th>
            </tr>
        `;

        const tbody = document.createElement('tbody');

        order.items.forEach(item => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${escapeHtml(item.productName)}</td>
                <td>NT$${item.price}</td>
                <td>${item.quantity}</td>
                <td>NT$${(item.price * item.quantity).toFixed(2)}</td>
            `;
            tbody.appendChild(tr);
        });

        itemsTable.appendChild(thead);
        itemsTable.appendChild(tbody);
        orderInfo.appendChild(itemsTable);

        orderDetailsDiv.appendChild(orderInfo);
    } else {
        orderDetailsDiv.innerHTML = '<p>未找到該訂單。</p>';
    }
}

function escapeHtml(text) {
    if (typeof text !== 'string') return text;
    return text.replace(/[&<>"']/g, function(match) {
        const escapeChars = {
            '&': '&amp;',
            '<': '&lt;',
            '>': '&gt;',
            '"': '&quot;',
            "'": '&#039;'
        };
        return escapeChars[match];
    });
}
