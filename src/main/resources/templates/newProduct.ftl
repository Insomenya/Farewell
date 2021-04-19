<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>Добавление товара</h1>
    <form method="POST" enctype="multipart/form-data">
        <input type="text" name="name" placeholder="Имя товара">
        <label for="price"><input type="number" step="0.01" name="price" id="price"></label>
        <input type="text" name="description" placeholder="Описание товара">
        <label for="state">Состояние:</label>
        <select name="state" id="state">
            <option value="1">Плохое</option>
            <option value="2">Хорошее</option>
            <option value="3">Отличное</option>
        </select>
        <label for="category">Категория:</label>
        <select name="category" id="category">
            <#list categories as category>
                <option value="${category.id}">${category.name}</option>
            </#list>
        </select>
        <label for="image">Изображение:</label>
        <input type="file" name="image" id="image">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Добавить</button>
    </form>
</body>
</html>