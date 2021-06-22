const cartCounter = document.getElementById("cart-counter");

function toggleButton(btn){
    if (btn.dataset.inCart == 'true'){
        btn.dataset.inCart = 'false';
        btn.classList.remove("btn-danger");
        btn.classList.add("btn-primary");
        btn.children[1].classList.remove("d-none");
        btn.children[0].classList.add("d-none");
        cartCounter.textContent--;
    } else {
        btn.dataset.inCart = 'true';
        btn.classList.remove("btn-primary");
        btn.classList.add("btn-danger");
        btn.children[0].classList.remove("d-none");
        btn.children[1].classList.add("d-none");
        cartCounter.textContent++;
    }
}

async function sendFunc(obj, btn) {
    await fetch('http://localhost:8080/rest/toggleCart', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(obj)
    }).then(response => {
        if (response.ok) {
            console.log('response is ok')

            toggleButton(btn);

        } else {
            console.log('no response')
        }
    }, error => {
        console.log('no response')
        let date = new Date();
        document.addToast("Ошибка", date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(), "Не удалось выполнить операцию. Перезагрузите страницу");
    })
}