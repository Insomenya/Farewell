const toastContainer = document.getElementById("toast-container");
const toastTemplate = document.getElementById("toast-template");
document.addToast = function (heading, timestamp, body) {
    let newToast = toastContainer.appendChild(toastTemplate.cloneNode(true));
    newToast.children[0].querySelector(".toast-heading").innerHTML = heading;
    newToast.children[0].querySelector(".toast-timestamp").innerHTML = timestamp;
    newToast.children[1].innerHTML = body;
    new bootstrap.Toast(newToast).show();
}