<#include "security.ftl">
<#import "auth.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light" id="navbar">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Farewell</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <#if isOperator>
                    <li class="nav-item">
                        <a class="nav-link" href="/operator/orders">Заказы в обработке</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/operator/history">История заказов</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/operator/stats">Статистика</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/operator/newProduct">Добавить товар</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/operator/newCategory">Добавить категорию</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/operator/changeDiscounts">Редактировать скидки</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/operator/register">Регистрация оператора</a>
                    </li>
                </#if>
                <#if isCustomer>
                    <li class="nav-item">
                        <a class="nav-link" href="/customer/history">История заказов</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/customer/options">Настройка профиля</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/customer/cart">Корзина <#if cartSize??><span class="badge bg-dark" id="cart-counter">${cartSize}</span></#if></a>
                    </li>
                </#if>
            </ul>
        </div>
        <#if known>
            <div class="d-flex">
                <div class="navbar-text me-2 phone">${name}</div>
                <@l.logout/>
            </div>
        <#else>
            <@l.login/>
        </#if>
    </div>
</nav>