document.addEventListener('DOMContentLoaded', () => {
    for (const btn of document.querySelectorAll(".cart-button")){
        btn.onclick = async event => {
            let data = { productId: parseInt(btn.dataset.product) };
            await sendFunc(data, btn).then(response => {
                console.log('send is ok');
            }, error => {
                console.log(error);
                console.log('error sending');
                let date = new Date();
                document.addToast("Ошибка", date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(), "Сервер недоступен");
            });
        };
    }
});