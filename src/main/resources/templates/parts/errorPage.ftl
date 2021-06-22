<#macro error name message code="unknown">
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
              crossorigin="anonymous">
        <title>${name} | Farewell</title>
    </head>
    <body>
        <div class="container-fluid d-flex justify-content-center mt-5">
            <div class="card w-50 text-center">
                <div class="card-header">
                    ${name}
                </div>
                <div class="card-body">
                    <h5 class="card-title"><#if code=="unknown">Неизвестная ошибка<#else>Ошибка ${code}</#if></h5>
                    <p class="card-text">${message}</p>
                    <a href="/" class="btn btn-primary">Вернуться на главную</a>
                </div>
            </div>
        </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
            crossorigin="anonymous"></script>
    </body>
    </html>
</#macro>