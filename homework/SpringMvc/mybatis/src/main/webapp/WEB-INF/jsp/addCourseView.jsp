<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>添加课程操作界面</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .add-card {
            background: white;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.05);
            width: 100%;
            max-width: 500px;
        }
        .add-card h2 {
            font-weight: bold;
            margin-bottom: 30px;
            font-size: 28px;
        }
        .form-label {
            color: #666;
            width: 100px; /* 固定标签宽度，实现对齐 */
            margin-bottom: 0;
            line-height: 38px;
        }
        .form-control {
            border-color: #dee2e6;
            border-radius: 6px;
        }
        .form-control::placeholder {
            color: #ccc;
        }
        .btn-submit {
            background-color: #3b9cff;
            border: none;
            color: white;
            padding: 10px;
            font-size: 16px;
            border-radius: 6px;
            width: 100%;
            margin-top: 20px;
        }
        .btn-submit:hover {
            background-color: #007bff;
        }
        /* 行容器样式 */
        .form-group-row {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<div class="add-card">
    <h2>添加课程操作界面</h2>

    <form action="addCourse" method="post">

        <div class="form-group-row">
            <label class="form-label">课程名称</label>
            <input type="text" name="courseName" class="form-control" placeholder="请输入课程名称" required>
        </div>

        <div class="form-group-row">
            <label class="form-label">课程代码</label>
            <input type="text" name="code" class="form-control" placeholder="请输入课程代码" required>
        </div>

        <div class="form-group-row">
            <label class="form-label">授课老师</label>
            <input type="text" name="teacher" class="form-control" placeholder="请输入授课老师" required>
        </div>

        <div class="form-group-row">
            <label class="form-label">课程教室</label>
            <input type="text" name="room" class="form-control" placeholder="请输入课程教室" required>
        </div>

        <div class="form-group-row">
            <label class="form-label">课程人数</label>
            <input type="number" name="number" class="form-control" placeholder="请输入课程人数" required>
        </div>

        <button type="submit" class="btn-submit">添加</button>

    </form>
</div>

</body>
</html>