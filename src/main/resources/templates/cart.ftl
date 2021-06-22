<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page "Главная | farewell">
    <h1>Корзина</h1>
    <#if products?has_content>
        <#if error??>
            <div class="alert alert-danger" role="alert">
                ${error}
            </div>
        </#if>
        <div>
            <#list products as product>
                <div class="card w-100 mt-3">
                    <div class="card-header">
                        <div class="d-flex w-100 justify-content-start">
                            <div class="me-2">Категория: <strong>${product.category.name}</strong></div>
                            <div class="me-2">Состояние:
                                <strong>
                                    <#switch product.state>
                                        <#case 1>
                                            плохое
                                            <#break>
                                        <#case 2>
                                            хорошее
                                            <#break>
                                        <#case 3>
                                            отличное
                                            <#break>
                                        <#default>
                                            не указано
                                    </#switch>
                                </strong>
                            </div>
                            <div class="me-2">Цена: <strong>${product.price}</strong></div>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="d-flex justify-content-between">
                            <div class="d-flex justify-content-start mw-75">
                                <div class="me-3" style="width: 160px">
                                    <#if product.image??>
                                        <img src="/uimg/${product.image}" alt="${product.name}" class="img-thumbnail" style="width: 160px">
                                    <#else>
                                        <img src="/static/img/noimage.jpg" alt="dummy image" class="img-thumbnail" style="width: 160px">
                                    </#if>
                                </div>
                                <div>
                                    <h5 class="card-title">${product.name}</h5>
                                    <p class="card-text">${product.description}</p>
                                </div>
                            </div>
                            <div>
                                <button type="button" class="btn btn-danger cart-button" data-product="${product.id}" data-product-price="${product.price?string.computer}">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart-x" viewBox="0 0 16 16">
                                        <path d="M7.354 5.646a.5.5 0 1 0-.708.708L7.793 7.5 6.646 8.646a.5.5 0 1 0 .708.708L8.5 8.207l1.146 1.147a.5.5 0 0 0 .708-.708L9.207 7.5l1.147-1.146a.5.5 0 0 0-.708-.708L8.5 6.793 7.354 5.646z"/>
                                        <path d="M.5 1a.5.5 0 0 0 0 1h1.11l.401 1.607 1.498 7.985A.5.5 0 0 0 4 12h1a2 2 0 1 0 0 4 2 2 0 0 0 0-4h7a2 2 0 1 0 0 4 2 2 0 0 0 0-4h1a.5.5 0 0 0 .491-.408l1.5-8A.5.5 0 0 0 14.5 3H2.89l-.405-1.621A.5.5 0 0 0 2 1H.5zm3.915 10L3.102 4h10.796l-1.313 7h-8.17zM6 14a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm7 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
                                    </svg>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
        <h2>Общая сумма: <span id="totalSum">${totalSum?string.computer}</span></h2>
        <h2>Ваша персональная скидка: <span id="personalDiscount">${personalDiscount?string.computer}</span>%</h2>
        <h2>Сумма со скидкой: <span id="totalDiscountSum">${totalDiscountSum?string.computer}</span></h2>
        <#if isCustomer>
            <form action="/customer/cart" method="POST">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit" class="btn btn-primary">Сделать заказ</button>
            </form>
        </#if>
    <#else>
        <h2>Корзина пуста</h2>
    </#if>
    <#if isCustomer>
        <#include "parts/toasts.ftl">
        <script src="/static/js/RemoveFromCart.js"></script>
        <script src="/static/js/SendEvent.js"></script>
    </#if>
</@c.page>