<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<#assign charts>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['table', 'corechart']});
        google.charts.setOnLoadCallback(drawTable);
        google.charts.setOnLoadCallback(drawOrderCountChart);
        google.charts.setOnLoadCallback(drawCategoryCountChart);

        function drawTable() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Покупатель');
            data.addColumn('string', 'Оператор');
            data.addColumn('date', 'Дата создания');
            data.addColumn('number', 'Общая стоимость');
            data.addRows([
                <#list orders as order>
                    ['${order.getCustomer().getFullName()} (${order.getCustomer().getPhoneNum()})', '<#if order.getProcessedBy()??>${order.getProcessedBy().getFullName()}<#else>Неизвестен</#if>' , new Date('${order.getDate()}'), ${order.getTotal()?string.computer}] <#if !order?is_last>,</#if>
                </#list>
            ]);

            var table = new google.visualization.Table(document.getElementById('table_div'));

            table.draw(data, {showRowNumber: true, width: '100%', height: '100%'});
        }

        function drawOrderCountChart() {

            var data = google.visualization.arrayToDataTable([
                ['Количество заказов', 'Оператор'],
                <#list orderCountPieChart as cnt>
                    ['${cnt.getOperator().getFullName()}', ${cnt.getOrderCount()?string.computer}] <#if !cnt?is_last>,</#if>
                </#list>
            ]);

            var options = {
                title: 'Количество завершенных заказов для каждого оператора'
            };

            var chart = new google.visualization.PieChart(document.getElementById('orderCountByOperator'));

            chart.draw(data, options);
        }

        function drawCategoryCountChart() {

            var data = google.visualization.arrayToDataTable([
                ['Количество заказов', 'Категория'],
                <#list categoryCountPieChart as ctp>
                ['${ctp.getCategory().getName()}', ${ctp.getOrderCount()?string.computer}] <#if !ctp?is_last>,</#if>
                </#list>
            ]);

            var options = {
                title: 'Количество завершенных заказов для каждой категории'
            };

            var chart = new google.visualization.PieChart(document.getElementById('categoryCountByOperator'));

            chart.draw(data, options);
        }

    </script>
</#assign>

<@c.page "Статистика | farewell" charts>
    <h1>Статистика</h1>
    <div class="container w-75 text-center">
        <div class="form-group row justify-content-center">
            <h2>Информация о завершенных заказах</h2>
            <#if orders?has_content>
                <div>
                    <div id="table_div" class="mb-3"></div>
                </div>
            <#else>
                <h2>Нет информации о заказах.</h2>
            </#if>
        </div>

        <div class="form-group row justify-content-center">
            <h2>Анализ наиболее удачных описаний товаров</h2>
            <#if orderCountPieChart?has_content>
                <div>
                    <div id="orderCountByOperator" style="width: 900px; height: 500px;"></div>
                </div>
            <#else>
                <h2>Нет информации о наиболее удачных описаниях товаров.</h2>
            </#if>
        </div>

        <div class="form-group row justify-content-center">
            <h2>Анализ наиболее продаваемых категорий товаров</h2>
            <#if categoryCountPieChart?has_content>
                <div>
                    <div id="categoryCountByOperator" style="width: 900px; height: 500px;"></div>
                </div>
            <#else>
                <h2>Нет информации о наиболее категориях товаров.</h2>
            </#if>
        </div>
    </div>
</@c.page>