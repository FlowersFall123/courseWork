<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Java Web考试平台 - 登录</title>
    <style>
        /* 页面居中样式 */
        body {
            font-family: "Microsoft YaHei", sans-serif;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            background-color: #ffffff;
        }

        h1 {
            font-size: 48px;
            margin-bottom: 30px;
        }

        /* 表单布局 */
        .login-container {
            display: inline-block;
        }

        .input-group {
            margin-bottom: 10px;
            display: flex;
            align-items: center;
        }

        .input-group label {
            font-size: 20px;
            width: 80px;
            text-align: left;
        }

        .input-group input {
            padding: 5px;
            font-size: 16px;
            border: 1px solid #ccc;
            width: 250px;
        }

        /* 提交按钮样式 */
        .submit-btn {
            margin-top: 10px;
            padding: 5px 20px;
            font-size: 16px;
            cursor: pointer;
            background-color: #f0f0f0;
            border: 1px solid #a9a9a9;
        }

        .submit-btn:hover {
            background-color: #e0e0e0;
        }
    </style>
</head>
<body>

<h1>Java Web考试平台</h1>

<form action="authLogin" method="post" class="login-container">
    <div class="input-group">
        <label>用户名:</label>
        <label>
            <input type="text" name="username" placeholder="请输入用户名" required>
        </label>
    </div>

    <div class="input-group">
        <label>密码</label>
        <label>
            <input type="password" name="password" placeholder="请输入密码" required>
        </label>
    </div>

    <input type="submit" value="提交" class="submit-btn">
</form>

</body>
</html>