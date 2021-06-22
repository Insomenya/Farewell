<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>

<@c.page "Главная | farewell">
    <h1>Список товаров</h1>
    <@p.pager url page />
    <div class="row" class="justify-content-center">
        <div class="col-2 mt-3">
            <ul class="nav flex-column nav-pills text-start">
                <#list categories as category>
                    <li class="nav-item">
                        <a class="nav-link <#if RequestParameters.category??><#if RequestParameters.category == category.getId()?string>active</#if></#if>" aria-current="page" href="<#if !RequestParameters.size?? && !RequestParameters.page??>/?category=${category.getId()}<#else>/?page=${RequestParameters.page}&size=${RequestParameters.size}&category=${category.getId()}</#if> "> ${category.getName()}</a>
                    </li>
                </#list>
            </ul>
        </div>
    <#if page.content?has_content>
        <div class="col-10 justify-content-around">
            <#list page.content as productEntry>
                <div class="card w-100 mt-3 <#if productEntry?is_last>mb-5</#if>">
                    <div class="card-header">
                        <div class="d-flex w-100 justify-content-start">
                            <div class="me-2">Категория: <strong>${productEntry.getProduct().category.name}</strong></div>
                            <div class="me-2">Состояние:
                                <strong>
                                    <#switch productEntry.getProduct().state>
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
                            <div class="me-2">Цена: <strong>${productEntry.getProduct().price}</strong></div>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="d-flex justify-content-between">
                            <div class="d-flex justify-content-start mw-75">
                                <div class="me-3 image-container" style="width: 160px;">
                                    <#if productEntry.getProduct().image??>
                                        <img src="/uimg/${productEntry.getProduct().image}" alt="${productEntry.getProduct().name}" class="img-thumbnail" style="width: 160px">
                                    <#else>
                                        <img src="/static/img/noimage.jpg" alt="dummy image" class="img-thumbnail" style="width: 160px">
                                    </#if>
                                </div>
                                <div>
                                    <h5 class="card-title">${productEntry.getProduct().name}</h5>
                                    <p class="card-text">${productEntry.getProduct().description}</p>
                                </div>
                            </div>
                            <div>
                                <#if isCustomer>
                                    <button type="button" class="btn btn-<#if productEntry.inCart>danger<#else>primary</#if> cart-button" data-product="${productEntry.getProduct().id}" data-in-cart="<#if productEntry.inCart>true<#else>false</#if>">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="<#if !productEntry.inCart>d-none</#if> bi bi-cart-x" viewBox="0 0 16 16">
                                            <path d="M7.354 5.646a.5.5 0 1 0-.708.708L7.793 7.5 6.646 8.646a.5.5 0 1 0 .708.708L8.5 8.207l1.146 1.147a.5.5 0 0 0 .708-.708L9.207 7.5l1.147-1.146a.5.5 0 0 0-.708-.708L8.5 6.793 7.354 5.646z"/>
                                            <path d="M.5 1a.5.5 0 0 0 0 1h1.11l.401 1.607 1.498 7.985A.5.5 0 0 0 4 12h1a2 2 0 1 0 0 4 2 2 0 0 0 0-4h7a2 2 0 1 0 0 4 2 2 0 0 0 0-4h1a.5.5 0 0 0 .491-.408l1.5-8A.5.5 0 0 0 14.5 3H2.89l-.405-1.621A.5.5 0 0 0 2 1H.5zm3.915 10L3.102 4h10.796l-1.313 7h-8.17zM6 14a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm7 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
                                        </svg>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="<#if productEntry.inCart>d-none</#if> bi bi-cart-plus" viewBox="0 0 16 16">
                                            <path d="M9 5.5a.5.5 0 0 0-1 0V7H6.5a.5.5 0 0 0 0 1H8v1.5a.5.5 0 0 0 1 0V8h1.5a.5.5 0 0 0 0-1H9V5.5z"/>
                                            <path d="M.5 1a.5.5 0 0 0 0 1h1.11l.401 1.607 1.498 7.985A.5.5 0 0 0 4 12h1a2 2 0 1 0 0 4 2 2 0 0 0 0-4h7a2 2 0 1 0 0 4 2 2 0 0 0 0-4h1a.5.5 0 0 0 .491-.408l1.5-8A.5.5 0 0 0 14.5 3H2.89l-.405-1.621A.5.5 0 0 0 2 1H.5zm3.915 10L3.102 4h10.796l-1.313 7h-8.17zM6 14a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm7 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
                                        </svg>
                                    </button>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    <#else>
        <div class="col-10"><h2>Нет товаров</h2></div>
    </#if>
    </div>
    <#if isCustomer>
        <a href="/customer/cart"><button class="btn btn-primary mt-3">Перейти к корзине</button></a>
    </#if>
    <#if !known>
        <div class="alert alert-danger mt-3" role="alert">
            Чтобы купить товар необходимо <a href="/login" class="alert-link">авторизоваться</a>.
        </div>
    </#if>
    <#if isCustomer>
        <#include "parts/toasts.ftl">
        <script src="/static/js/AddToCart.js"></script>
        <script src="/static/js/SendEvent.js"></script>
    </#if>
</@c.page>