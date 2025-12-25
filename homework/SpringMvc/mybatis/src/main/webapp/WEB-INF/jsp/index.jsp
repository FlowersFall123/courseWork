<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>欢迎来到智慧教育平台 - 登录</title>
    <style>
        /* 页面基础样式，确保垂直居中 */
        body {
            font-family: "Microsoft YaHei", -apple-system, sans-serif;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            background-color: #ffffff;
            color: #333;
        }

        /* 标题样式：加粗、大字号 */
        h1 {
            font-size: 42px;
            font-weight: bold;
            margin-bottom: 40px;
            letter-spacing: 2px;
        }

        /* 表单容器 */
        .login-form {
            width: 100%;
            max-width: 450px; /* 控制表单宽度 */
        }

        /* 输入组布局 */
        .input-group {
            margin-bottom: 20px;
            display: flex;
            align-items: center;
        }

        /* 标签样式：灰色文字，固定宽度对齐 */
        .input-group label {
            font-size: 18px;
            color: #666;
            width: 60px;
            text-align: left;
            margin-right: 15px;
        }

        /* 输入框样式：圆角、浅色边框 */
        .input-group input {
            flex: 1; /* 自动填充剩余空间 */
            padding: 12px 15px;
            font-size: 16px;
            border: 1px solid #dcdfe6;
            border-radius: 6px; /* 圆角设置 */
            outline: none;
            transition: border-color 0.2s;
        }

        /* 输入框聚焦效果 */
        .input-group input:focus {
            border-color: #409eff;
        }

        /* 占位符颜色 */
        .input-group input::placeholder {
            color: #c0c4cc;
        }

        /* 登录按钮：蓝色背景、白色文字、全宽 */
        .submit-btn {
            width: 100%;
            padding: 12px;
            font-size: 18px;
            color: white;
            background-color: #3b9cff; /* 图片中的亮蓝色 */
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s;
            margin-top: 10px;
        }

        .submit-btn:hover {
            background-color: #2a8df2;
        }

        /* 响应式微调 */
        @media (max-width: 500px) {
            h1 { font-size: 32px; }
            .login-form { padding: 0 20px; }
        }
    </style>
</head>
<body>

<h1>欢迎来到智慧教育平台</h1>

<form action="authLogin" method="post" class="login-form">
    <div class="input-group">
        <label>账号</label>
        <input type="text" name="username" placeholder="请输入账号" required>
    </div>

    <div class="input-group">
        <label>密码</label>
        <input type="password" name="password" placeholder="请输入密码" required>
    </div>

    <button type="submit" class="submit-btn">登录</button>
</form>

</body>
</html>