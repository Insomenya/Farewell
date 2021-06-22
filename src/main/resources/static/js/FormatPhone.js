for (let phoneInstance of document.getElementsByClassName('phone')){
    phoneInstance.textContent = formatPhoneNumber(phoneInstance.textContent);
}

function formatPhoneNumber(username) {
    if (isNaN(username)){
        return username;
    } else {
        var cleaned = ('' + username).replace(/\D/g, '');
        var match = cleaned.match(/^(1|)?(\d{3})(\d{3})(\d{4})$/);
        if (match) {
            var intlCode = (match[1] ? '+7 ' : '');
            return [intlCode, '(', match[2], ') ', match[3], '-', match[4]].join('');
        }
        return username;
    }
}