<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page "Главная | farewell">
    <h1>Список заказов</h1>
    <#if page.content?has_content>
        <div class="mb-5">
            <#list page.content as order>
                <div class="card w-100 mt-3">
                    <div class="card-header">
                        <div class="d-flex w-100 justify-content-start">
                            <div class="me-2">Идентификатор заказа: <strong>${order.getId()}</strong></div>
                            <div class="me-2">Статус:
                                <strong>
                                    <#switch order.getStatus()>
                                        <#case 1>
                                            В ожидании
                                            <#break>
                                        <#case 2>
                                            Принят к выполнению
                                            <#break>
                                        <#default>
                                            не указано
                                    </#switch>
                                </strong>
                            </div>
                            <#if isCustomer><div class="me-2">Ответственный оператор: <strong><#if order.getProcessedBy()?exists>${order.getProcessedBy().getFullName()}<#else>Ожидается</#if></strong></div></#if>
                            <#if isOperator><div class="me-2">Логин покупателя: <strong>${order.getCustomer().getPhoneNum()}</strong></div></#if>
                            <div class="me-2">Общая стоимость: <strong>${order.getTotal()}</strong></div>
                        </div>
                    </div>
                    <div class="card-body">
                        <h2>Список товаров:</h2>
                        <div class="d-flex justify-content-between">
                            <div class="d-flex justify-content-start mw-75">
                                <div>
                                    <#list order.getProducts() as product>
                                        <h5 class="card-title"><span>(${product.getCategory().getName()})</span> ${product.getName()} - <span>${product.getPrice()}</span></h5>
                                    </#list>
                                    <small>Дата создания заказа: <span>${order.getDate()}</span></small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    <#else>
        <h2>Нет заказов</h2>
    </#if>
</@c.page>