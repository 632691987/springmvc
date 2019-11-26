<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<head>
    <title>Data table example</title>
    <spring:url value="/springpure/resources/js/jquery-3.4.1.min.js" var="jqueryJS"/>
    <script type="text/javascript" src="${jqueryJS}"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css"/>
    <link rel="stylesheet" type="text/css" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"/>
    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
    <script language="JavaScript">
        $(document).ready(function () {
            var eventFired = function (type) {
                console.log(type + " event fired")
            }


            $('#example')
                .on('order.dt', function () {
                    eventFired('排序');
                })
                .on('search.dt', function () {
                    eventFired('搜索');
                })
                .on('draw.dt', function () {
                    eventFired('draw');
                })
                .on('init.dt', function () {
                    eventFired('init');
                })
                .on('page.dt', function () {
                    eventFired('翻页');
                })
                .DataTable({
                    "ajax": "datatable/data",
                    "columns": [
                        {"data": "name"},
                        {"data": "position"},
                        {"data": "office"},
                        {"data": "extn"},
                        {"data": "start_date"},
                        {"data": "salary"}
                    ]
                });
        });
    </script>
</head>
<body>
<table id="example" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>Name</th>
        <th>Position</th>
        <th>Office</th>
        <th>Extn.</th>
        <th>Start date</th>
        <th>Salary</th>
    </tr>
    </thead>
    <tbody></tbody>
    <tfoot>
    <tr>
        <th>Name</th>
        <th>Position</th>
        <th>Office</th>
        <th>Extn.</th>
        <th>Start date</th>
        <th>Salary</th>
    </tr>
    </tfoot>
</table>
</body>
</html>
