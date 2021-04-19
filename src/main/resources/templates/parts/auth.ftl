<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="_csrf.token" />
        <button class="btn btn-primary" type="submit">Выйти</button>
    </form>
</#macro>

<#macro login>
    <a href="/login" class="btn btn-primary me-2">Войти</a>
</#macro>

<#macro loginForm path isRegisterForm userRegisterForm>
    <form action="${path}" class="col-lg-6 offset-lg-3" method="POST">
        <div class="form-group row justify-content-center">
            <label class="col-sm-2 col-form-label">Логин:</label>
            <div class="col-sm-6">
                <input type="text" name="username" value="<#if user??>${user.username}</#if>"
                       class="form-control ${(usernameError??)?string('is-invalid', '')}"
                       placeholder="<#if userRegisterForm>Номер телефона(10 цифр):<#else>Логин:</#if>" <#if userRegisterForm> pattern="[0-9]{10}" </#if> />
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row justify-content-center">
            <label class="col-sm-2 col-form-label">Пароль:</label>
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
                <label class="col-sm-2 col-form-label">Ваше имя:</label>
                <div class="col-sm-6">
                    <input type="text" name="fullName" value=""
                           class="form-control ${(fullNameError??)?string('is-invalid', '')}"
                           placeholder="Полное имя" />
                </div>
            </div>
        </#if>
        <div class="form-group row">
            <div class="input-group-btn">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <#if !isRegisterForm><a href="/register">Зарегистрироваться</a><br></#if>
                <button class="btn btn-primary" type="submit"><#if isRegisterForm>Регистрация<#else>Войти</#if></button>
            </div>
        </div>
    </form>
</#macro>