<#import "parts/common.ftl" as c>

<@c.page>
    <div class="container w-50"><h1 class="text-center">Редактирование персональных скидок</h1></div>
    <div class="container w-50 text-center mt-3">
        <div class="row  justify-content-center">
            <div class="form-group col-sm-3 text-start mt-3">
                <label for="customer">Выберите покупателя:</label>
            </div>
            <div class="form-group col-sm-9">
                <input list="customerList" id="customer" name="customer" class="mt-3 w-100" />
                <datalist id="customerList">
                    <#list customerList as customer>
                    <option value="${customer.getPhoneNum()}" label="${customer.getFullName()}">
                        </#list>
                </datalist>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="form-group col-sm-3 text-start mt-3">
                <label for="discountControl">Размер скидки:</label>
            </div>
            <div class="form-group col-sm-9">
                <input type="number" class="form-control mt-3 w-100" id="discountControl" value="0" step="0.1" min="0" name="discount" id="discount">
            </div>
        </div>
        <div class="row d-flex justify-content-start">
            <div class="form-group col-sm-3"></div>
            <div class="form-group col-sm-9 d-flex">
                <button type="submit" class="btn btn-primary mt-3" id="submitButton">Изменить</button>
            </div>
        </div>
    </div>
    <#include "parts/toasts.ftl">
    <script src="/static/js/ChangeDiscounts.js"></script>
</@c.page>