<#import "parts/errorPage.ftl" as e>
<@e.error name="Внутренняя ошибка сервера" message="На сервере произошла ошибка. Попробуйте повторить операцию." code="500">
</@e.error>