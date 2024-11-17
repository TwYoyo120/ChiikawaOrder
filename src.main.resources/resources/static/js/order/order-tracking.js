document.addEventListener('DOMContentLoaded', () => {
    loadOrderTracking();
    loadHeaderAndFooter();
});

function loadHeaderAndFooter() {
    fetch('./html/header-front.html')
        .then(response => response.ok ? response.text() : Promise.reject('Error loading header'))
        .then(data => document.getElementById('header-container').innerHTML = data)
        .catch(error => console.error('Error loading header:', error));

    fetch('./html/footer-front.html')
        .then(response => response.ok ? response.text() : Promise.reject('Error loading footer'))
        .then(data => document.getElementById('footer-container').innerHTML = data)
        .catch(error => console.error('Error loading footer:', error));
}

function getOrderIdFromUrl() {
    const params = new URLSearchParams(window.location.search);
    return params.get('id');
}

function loadOrderTracking() {
    const orderId = getOrderIdFromUrl();
    if (!orderId) {
        alert('無效的訂單 ID');
        return;
    }
    fetch(`http://localhost:8080/ChiikawaGame/OrderServlet.do?action=getOrderStatus&id=${encodeURIComponent(orderId)}`)
        .then(response => response.json())
        .then(status => {
            const trackingDiv = document.getElementById('order-tracking');
            const statuses = ['已下單', '已付款', '出貨中', '配送中', '已送達'];
            const currentStatusIndex = statuses.indexOf(status);

            let progressBar = '<ul class="list-group">';
            statuses.forEach((s, index) => {
                progressBar += `
                    <li class="list-group-item ${index <= currentStatusIndex ? 'list-group-item-success' : ''}">
                        ${s}
                    </li>`;
            });
            progressBar += '</ul>';

            trackingDiv.innerHTML = progressBar;
            document.getElementById('loading').style.display = 'none';
        })
        .catch(error => {
            console.error('Error loading order tracking:', error);
            alert('無法加載訂單追蹤，請稍後再試');
        });
}
