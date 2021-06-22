<#macro logout>
    <form action="/logout" method="POST">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Выйти</button>
    </form>
</#macro>

<#macro login>
    <a href="/login" class="btn btn-primary me-2">Войти</a>
</#macro>

<#macro loginForm path isRegisterForm userRegisterForm>
    <div class="container w-50 text-center">
        <form action="${path}" method="POST">
            <div class="form-group row justify-content-around">
                <div class="col-sm-6"><label class="col-sm-2 col-form-label mr-2">Логин:</label></div>
                <div class="col-sm-6">
                    <input type="text" name="username" value="<#if customer??>${customer.phoneNum}</#if>"
                           class="form-control ${(usernameError??)?string('is-invalid', '')}"
                           placeholder="<#if userRegisterForm>Номер телефона(10 цифр):<#else>Логин:</#if>" <#if userRegisterForm> pattern="[0-9]{10}" </#if> />
                    <#if usernameError??>
                        <div class="invalid-feedback">
                            ${usernameError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row justify-content-around mt-3">
                <div class="col-sm-6"><label class="d-inline col-sm-2 col-form-label mr-2">Пароль:</label></div>
                <div class="col-sm-6">
                    <input type="password" name="password"
                           class="form-control ${(passwordError??)?string('is-invalid', '')}"
                           placeholder="Пароль" />
                    <#if passwordError??>
                        <div class="invalid-feedback">
                            ${passwordError}
                        </div>
                    </#if>
                </div>
            </div>
            <#if isRegisterForm>
                <div class="form-group row justify-content-center">
                    <div class="col-sm-6"><label class="col-sm-2 col-form-label">Ваше имя:</label></div>
                    <div class="col-sm-6">
                        <input type="text" name="fullName" value="<#if customer??>${customer.fullName}</#if>"
                               class="form-control ${(fullNameError??)?string('is-invalid', '')} mt-3"
                               placeholder="Полное имя" />
                    </div>
                </div>
            </#if>
            <div class="form-group row">
                <div class="input-group-btn">
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <#if !isRegisterForm><a href="/register" class="mt-3">Зарегистрироваться</a><br></#if>
                    <button class="btn btn-primary mt-3" type="submit"><#if isRegisterForm>Регистрация<#else>Войти</#if></button>
                </div>
            </div>
        </form>
    </div>
</#macro>