const customerControl = document.getElementById("customer");
const discountControl = document.getElementById("discountControl");
const submitButton = document.getElementById("submitButton");

document.getElementById("customer").addEventListener("change", async event => {
    let data = { name: customerControl.value};
    await getDiscountValue(data).then(response => {
        console.log('send is ok');
    }, error => {
        console.log('error sending');
        let date = new Date();
        document.addToast("Ошибка", date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(), "Сервер недоступен");
    });
});

async function getDiscountValue(data) {
    await fetch('http://localhost:8080/rest/getDiscountValue?customer=' + customerControl.value, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => {
        if (response.ok) {
            console.log('response is ok')
            response.json().then((data) => {
                discountControl.value = data.discount;
            });
        } else {
            console.log('no response')
        }
    }, error => {
        console.log('no response')
        let date = new Date();
        document.addToast("Ошибка", date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(), "Не удалось получить данные от сервера.");
    })
}

submitButton.addEventListener("click", async event => {
    let data = { phone: customerControl.value ,discount: parseFloat(discountControl.value)};
    await updateDiscount(data).then(response => {
        console.log('send is ok');
    }, error => {
        console.log('error sending');
        let date = new Date();
        document.addToast("Ошибка", date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(), "Сервер недоступен");
    });
});

async function updateDiscount(data){
    await fetch('http://localhost:8080/rest/updateDiscount', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(response => {
        if (response.ok) {
            console.log('response is ok')
            let date = new Date();
            document.addToast("Выполнено", date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(), "Персональная скидка успешно изменена.");
        } else {
            console.log('no response')
        }
    }, error => {
        console.log('no response')
        let date = new Date();
        document.addToast("Ошибка", date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(), "Не удалось получить данные от сервера.");
    })
}