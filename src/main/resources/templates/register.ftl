<#import "parts/common.ftl" as c>
<#import "parts/auth.ftl" as l>

<@c.page>
    <div class="container w-75 text-center"><h1>Регистрация нового покупателя</h1></div>
    <#if Session?? && RequestParameters.error??>
        <div class="alert alert-danger" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>
    <@l.loginForm "/register" true true/>
</@c.page>