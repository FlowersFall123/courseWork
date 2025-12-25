<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>课程管理界面</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; padding: 20px; font-size: 14px; }
        .search-bar input { margin-right: 5px; border-radius: 4px; border: 1px solid #ddd; padding: 5px 10px; }
        .btn-query { background-color: #5cb85c; color: white; border: none; padding: 5px 20px; border-radius: 4px; }
        .btn-add { background-color: #7acc47; color: white; border: none; padding: 5px 20px; border-radius: 4px; margin-left: 10px; }
        .table thead th { background-color: #fcfcfc; color: #888; font-weight: normal; border-bottom: 1px solid #eee; }
        .pagination .page-link { color: #666; border: 1px solid #eee; margin: 0 2px; }
        .pagination .active .page-link { background-color: #3399ff; border-color: #3399ff; }
        .delete-link { color: #3399ff; text-decoration: none; }
    </style>
</head>
<body>

<div class="container-fluid bg-white p-4 shadow-sm">

    <form action="queryCourse" method="get" class="d-flex mb-4 search-bar">
        <input type="number" name="id" placeholder="请输入课程编号" value="${param.id}">
        <input type="text" name="courseName" placeholder="请输入课程名称" value="${param.courseName}">
        <input type="text" name="room" placeholder="请输入课程教室" value="${param.room}">
        <input type="number" name="minNumber" placeholder="请输入最小人数" value="${param.minNumber}">
        <input type="number" name="maxNumber" placeholder="请输入最大人数" value="${param.maxNumber}">
        <input type="hidden" name="action" value="query">
        <button type="submit" class="btn-query">查询</button>
        <a href="addCourseView" class="btn-add" style="text-decoration: none; display: inline-block; text-align: center;">添加</a>
    </form>

    <table class="table">
        <thead>
        <tr>
            <th>课程编号</th>
            <th>课程名称</th>
            <th>课程代码</th>
            <th>授课老师</th>
            <th>课程教室</th>
            <th>课程人数</th>
            <th class="text-end">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${courses}" var="course">
            <tr>
                <td>${course.id}</td>
                <td>${course.courseName}</td>
                <td>${course.code}</td>
                <td>${course.teacher}</td>
                <td>${course.room}</td>
                <td>${course.number}</td>
                <td class="text-end">
                    <a href="deleteCourse?id=${course.id}" class="delete-link" onclick="return confirm('确定删除吗？')">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <nav>
        <ul class="pagination pagination-sm">
            <li class="page-item"><a class="page-link" href="#">&lt;</a></li>
            <li class="page-item active"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item"><a class="page-link" href="#">4</a></li>
            <li class="page-item"><a class="page-link" href="#">5</a></li>
            <li class="page-item"><a class="page-link" href="#">6</a></li>
            <li class="page-item disabled"><span class="page-link">...</span></li>
            <li class="page-item"><a class="page-link" href="#">20</a></li>
            <li class="page-item"><a class="page-link" href="#">&gt;</a></li>
        </ul>
    </nav>

</div>

</body>
</html>