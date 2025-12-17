<%@ page contentType="text/html;charset=UTF-8"  language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<html>
<body>
<div>
    <form action="saveProduct" method="post">
        <fieldset>
            <legend>Add a product</legend>
            <label for="name">Product Name:</label>
            <input type="text" id="name" name="name" value="" tabindex="1" />

            <label for="description">Description:</label>
            <input type="text" id="description" name="description" value="" tabindex="2" />

            <label for="price">Price:</label>
            <input type="text" id="price" name="price" value="" tabindex="3" />

            <div id="buttons">
                <label for="dummy" > </label>
                <input id="reset" type="reset" tabindex="4"/>
                <input id="submit" type="submit" tabindex="5" value="Add Product" />
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>
