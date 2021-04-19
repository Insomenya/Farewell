<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page "Главная | farewell">
    <h1>Список товаров</h1>
    <#if products?has_content>
    <form method="POST" modelAttribute="checkedProducts">
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
                            <div class="me-3" style="max-width: 40%;">
                                <#if product.image??>
                                    <img src="uimg/${product.image}" alt="${product.name}" class="img-thumbnail">
                                <#else>
                                    <img src="img/noimage.jpg" alt="dummy image" class="img-thumbnail">
                                </#if>
                            </div>
                            <div>
                                <h5 class="card-title">${product.name}</h5>
                                <p class="card-text">${product.description}</p>
                            </div>
                        </div>
                        <div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="checkbox" path="listIdProducts" name ="checkedProducts" id="check${product.id}" value="${product.id}">
                                <label class="form-check-label" for="check${product.id}">Добавить к заказу</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    </div>
        <#if isCustomer>
            <button type="submit" class="btn btn-primary">Сделать заказ</button>
        </#if>
    </form>
    <#else>
        <h2>Нет товаров</h2>
    </#if>
    <#if !known>
        <div class="alert alert-danger mt-3" role="alert">
            Чтобы купить товар необходимо <a href="/login" class="alert-link">авторизоваться</a>.
        </div>
    </#if>
</@c.page>