<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page "Главная | farewell">
    <h1>Список заказов</h1>
    <#if orderList?has_content>
        <div>
            <#list orderList as order>
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
                            <div class="me-2">Логин покупателя: <strong>${order.getCustomer().getPhoneNum()}</strong></div>
                            <div class="me-2">Общая стоимость: <strong>${order.getTotal()}</strong></div>
                        </div>
                    </div>
                    <div class="card-body">
                        <h2>Список товаров:</h2>
                        <div class="row">
                            <div class="col-10 justify-content-between">
                                <#list order.getProducts() as product>
                                    <h5 class="card-title"><span>(${product.getCategory().getName()})</span> ${product.getName()} - <span>${product.getPrice()}</span></h5>
                                </#list>
                                <p>Дата создания заказа: <span>${order.getDate()}</span></p>
                            </div>
                            <div class="col-2">
                                <button type="button" class="btn btn-primary order-button" data-order-id="${order.getId()}" data-operator-name="${name}">Подтвердить заказ</button>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    <#else>
        <h2>Нет заказов</h2>
    </#if>
    <#include "parts/toasts.ftl">
    <script src="/static/js/ConfirmOrder.js"></script>
</@c.page>