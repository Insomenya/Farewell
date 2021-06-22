<#import "parts/common.ftl" as c>

<@c.page>
    <div class="container w-75"><h1 class="text-center">Добавление категории</h1></div>
    <#if newCategoryName??>
        <div class="alert alert-success mt-3" role="alert">
            Категория ${newCategoryName} успешно добавлена.
        </div>
    </#if>
    <div class="container w-75 text-center">
            <div class="form-group">
            <form method="post">
                <div class="row">
                    <div class="form-group">
                        <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}" name="name" placeholder="Введите название категории" />
                        <#if nameError??>
                            <div class="invalid-feedback">
                                ${nameError}
                            </div>
                        </#if>
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                </div>
                <div class="row">
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary mt-3">Добавить категорию</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</@c.page>