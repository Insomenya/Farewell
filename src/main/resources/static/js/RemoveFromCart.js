const totalSum = document.getElementById("totalSum");
const totalDiscountSum = document.getElementById("totalDiscountSum");
const personalDiscount = document.getElementById("personalDiscount");

function removeCard(btn){
    document.querySelectorAll(".card").forEach(function (cd) {
        if (cd.contains(btn)){
            cd.parentNode.removeChild(cd);
            totalSum.textContent = (parseFloat(totalSum.textContent) - parseFloat(btn.dataset.productPrice)).toFixed(3);
            totalDiscountSum.textContent = parseFloat(parseFloat(personalDiscount.textContent) > 0 ? parseFloat(totalSum.textContent) * (1 - (parseFloat(personalDiscount.textContent) / 100)) : totalSum.textContent).toFixed(3);
        }
    });
}

async function sendFunc(obj, btn) {
    await fetch('http://localhost:8080/rest/removeCartItem', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(obj)
    }).then(response => {
        if (response.ok) {
            console.log('response is ok')

            removeCard(btn);

        } else {
            console.log('no response')
        }
    }, error => {
        console.log('no response')
        let date = new Date();
        document.addToast("Ошибка", date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(), "Не удалось выполнить операцию. Перезагрузите страницу");
    })
}