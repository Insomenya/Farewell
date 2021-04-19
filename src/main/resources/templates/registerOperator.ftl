<#import "parts/common.ftl" as c>
<#import "parts/auth.ftl" as l>

<@c.page>
    <h1>Регистрация нового оператора</h1>
    ${message?ifExists}
    <@l.loginForm "operator/registerOperator" true false/>
</@c.page>