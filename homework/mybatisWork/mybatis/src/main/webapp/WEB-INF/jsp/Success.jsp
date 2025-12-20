<%-- 修改第一行，增加 isELIgnored="false" --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生信息管理</title>
    <style>
        body { font-family: "Microsoft YaHei", sans-serif; text-align: center; }
        table { margin: 20px auto; border-collapse: collapse; width: 80%; }
        th, td { border: 1px solid black; padding: 10px; text-align: center; }
        th { background-color: #f2f2f2; }
        .add-form { margin: 20px auto; padding: 20px; border: 1px solid #ccc; width: fit-content; }
        .add-form input { margin: 5px; }
        .btn-del { color: red; text-decoration: none; }
        .btn-del:hover { text-decoration: underline; }
    </style>
</head>
<body>

<h1 style="text-align:center;">学生信息表</h1>

<div class="add-form">
    <h3>添加新学生</h3>
    <form action="addStudent" method="post">
        姓名: <input type="text" name="name" required>
        生日: <input type="date" name="birthday" required>
        性别:
        <select name="sex">
            <option value="男">男</option>
            <option value="女">女</option>
        </select>
        爱好: <input type="text" name="hobbyName" required>
        <input type="submit" value="添加学生">
    </form>
</div>

<table>
    <thead>
    <tr>
        <th>姓名</th>
        <th>生日</th>
        <th>性别</th>
        <th>爱好</th>
        <th>操作</th> </tr>
    </thead>
    <tbody>
    <c:forEach items="${studentAndHobbies}" var="stu">
        <tr>
            <td>${stu.name}</td>
            <td><fmt:formatDate value="${stu.birthday}" pattern="yyyy-MM-dd" /></td>
            <td>${stu.sex}</td>
            <td>${stu.hobbyName}</td>
            <td>
                <a href="deleteStudent/?id=${stu.id}"
                   class="btn-del"
                   onclick="return confirm('确定要删除 ${stu.name} 吗？')">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>