<#import "parts/common.ftl" as c>
<#import "parts/auth.ftl" as l>

<@c.page>
    <div class="container w-75"><h1 class="text-center">Регистрация нового оператора</h1></div>
    <#if message??>
        <div class="alert alert-success" role="alert">
            ${message}
        </div>
    </#if>
    <#if Session?? && RequestParameters.error??>
        <div class="alert alert-danger" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>
    <@l.loginForm "/operator/register" true false/>
</@c.page>