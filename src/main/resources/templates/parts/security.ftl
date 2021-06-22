<#assign known = Session.SPRING_SECURITY_CONTEXT??>

<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        isOperator = user.isOperator()
        isCustomer = user.isCustomer()
        >
    <#if isOperator>
        <#assign operator = user.operator>
    </#if>
    <#if isCustomer>
        <#assign customer = user.customer>
    </#if>
<#else>
    <#assign
        name = "Неизвестный"
        isOperator = false
        isCustomer = false
        >
</#if>