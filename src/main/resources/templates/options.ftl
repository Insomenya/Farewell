<#import "parts/common.ftl" as c>

<@c.page>
    <div class="container w-75"><h1 class="text-center">Настройка профиля покупателя</h1></div>
    <#if Session?? && RequestParameters.error??>
        <div class="alert alert-danger" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>
    <#if message??>
        ${message}
    </#if>
    <form action="/customer/options" class="col-lg-6 offset-lg-3" method="POST">
        <div class="form-group row justify-content-center">
            <label class="col-sm-2 col-form-label">Ваше имя:</label>
            <div class="col-sm-6">
                <input type="text" name="fullName" value="<#if customer??>${customer.fullName}</#if>"
                       class="form-control ${(fullNameError??)?string('is-invalid', '')}"
                       placeholder="Полное имя" />
                <#if fullNameError??>
                    <div class="invalid-feedback">
                        ${fullNameError}
                    </div>
                </#if>
            </div>
        </div>
        <br>
        <div class="form-group row justify-content-center">
            <label class="col-sm-2 col-form-label">Новый пароль:</label>
            <div class="col-sm-6">
                <input type="password" name="newPass"
                       class="form-control ${(newPassError??)?string('is-invalid', '')}"
                       placeholder="Пароль" />
                <#if newPassError??>
                    <div class="invalid-feedback">
                        ${newPassError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row justify-content-center">
            <label class="col-sm-2 col-form-label">Подтверждение пароля:</label>
            <div class="col-sm-6">
                <input type="password" name="passVerification"
                       class="form-control"
                       placeholder="Пароль" id="passVerification" />
            </div>
        </div>
        <div class="form-group row justify-content-center">
            <div class="input-group-btn">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button class="btn btn-primary mt-3" type="submit">Изменить</button>
            </div>
        </div>
    </form>
</@c.page>