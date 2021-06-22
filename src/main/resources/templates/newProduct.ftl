<#import "parts/common.ftl" as c>

<@c.page>
    <div class="container w-75"><h1 class="text-center">Добавление товара</h1></div>
    <#if success??>
        <div class="alert alert-success mt-3" role="alert">
            Товар успешно добавлен.
        </div>
    </#if>
    <#if message??>
        <div class="alert alert-danger mt-3" role="alert">
            ${message}
        </div>
    </#if>
    <div class="container w-75 mb-5">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data" accept="image/png, image/jpeg">
                <div class="form-group">
                    <input type="text" class="form-control mt-3 ${(nameError??)?string('is-invalid', '')}" name="name" value="<#if product??>${product.name}</#if>" placeholder="Введите название товара" />
                    <#if nameError??>
                        <div class="invalid-feedback">
                            ${nameError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <label for="price" class="mt-3">Цена <input type="number" class="form-control mt-3 ${(priceError??)?string('is-invalid', '')}" value="<#if product??>${product.price}<#else>0.01</#if>" step="0.01" min="0.01" name="price" id="price"></label>
                    <#if priceError??>
                        <div class="invalid-feedback">
                            ${priceError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <label for="state" class="mt-3">Состояние:</label>
                    <select name="state" id="state" class="form-control mt-3 ${(stateError??)?string('is-invalid', '')}">
                        <option value="1" <#if product?? && product.state == 1>selected</#if>>Плохое</option>
                        <option value="2" <#if product?? && product.state == 2>selected</#if>>Хорошее</option>
                        <option value="3" <#if product?? && product.state == 3>selected</#if>>Отличное</option>
                    </select>
                    <#if stateError??>
                        <div class="invalid-feedback">
                            ${stateError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <label for="category" class="mt-3">Категория:</label>
                    <select name="category_id" id="category_id" class="form-control mt-3">
                        <#list categories as category>
                            <option value="${category.id}">${category.name}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group">
                    <textarea id="description" name="description" class="form-control mt-3 ${(descriptionError??)?string('is-invalid', '')}" rows="5" cols="33" placeholder="Введите название товара"><#if product??>${product.description}</#if></textarea>
                    <#if descriptionError??>
                        <div class="invalid-feedback">
                            ${descriptionError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <div class="custom-file mt-3">
                        <input type="file" name="image_file" id="image_file">
                        <label class="custom-file-label" for="image_file">Выберите изображение</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group">
                    <button type="submit" class="btn btn-primary mt-3">Добавить продукт</button>
                </div>
            </form>
        </div>
    </div>
</@c.page>