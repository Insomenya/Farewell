<#import "parts/common.ftl" as c>
<#import  "parts/auth.ftl" as l>

<@c.page "Вход | farewell">
    <h1>Форма входа</h1>
    <#if Session?? && RequestParameters.error??>
        <div class="alert alert-danger" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>
    <#if message??>
        <div class="alert alert-danger" role="alert">
            ${message}
        </div>
    </#if>
    <@l.loginForm "/login" false false/>
</@c.page>