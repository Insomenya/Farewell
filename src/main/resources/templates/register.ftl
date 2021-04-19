<#import "parts/common.ftl" as c>
<#import "parts/auth.ftl" as l>

<@c.page>
    <h1>Регистрация нового покупателя</h1>
    ${message?ifExists}
    <@l.loginForm "/register" true true/>
</@c.page>