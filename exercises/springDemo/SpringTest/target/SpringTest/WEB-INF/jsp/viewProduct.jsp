<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<html>
<body>
<div id="global">
    <h4>The product has been saved!</h4>
    <h5>Details:</h5>
    Tips:${message} <br/>
    Product ID: ${product.id}<br/>
    Product Name:${product.name} <br />
    Description:${product.description} <br />
    Price:￥${product.price} <br />
</div>
</body>
</html>
