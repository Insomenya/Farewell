document.addEventListener('DOMContentLoaded', () => {
    for (const btn of document.querySelectorAll(".order-button")){
        btn.onclick = async event => {
            let data = { orderId: parseInt(btn.dataset.orderId), operatorName: btn.dataset.operatorName};
            await confirmOrder(data, btn).then(response => {
                console.log('send is ok');
            }, error => {
                console.log('error sending');
                console.log(error);
                let date = new Date();
                document.addToast("Ошибка", date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(), "Сервер недоступен");
            });
        };
    }
});

async function confirmOrder(data, btn) {
    await fetch('http://localhost:8080/rest/confirmOrder', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(response => {
        if (response.ok) {
            console.log('response is ok')

            hideOrder(btn);

        } else {
            console.log('no response')
        }
    }, error => {
        console.log('no response')
        let date = new Date();
        document.addToast("Ошибка", date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(), "Не удалось выполнить операцию. Перезагрузите страницу");
    })
}

function hideOrder(btn){
    document.querySelectorAll(".card").forEach((order) => {
       if (order.contains(btn)){
           order.parentNode.removeChild(order);
       }
    });
}