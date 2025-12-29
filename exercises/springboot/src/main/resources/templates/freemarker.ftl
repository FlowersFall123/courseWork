<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Freemarker 模板引擎</title>
</head>
<body>

<!-- 可以按照我们 HTML 的写法 -->

字符串输出：
${"Hello ${name} !"} / ${"Hello " + name + " !"}

<!--
#assign 定义变量
-->
<#assign cname="特殊字符完成输出(https://www.baidu.com)">
${cname}

字符串截取：zhangsan
通过下标直接获取下标对应的字符：${name[2]}
起点下标，结束下标获取字符串：${name[0..5]}

算数运算：
<!-- 支持 "+", "-", "*", "/", "%", "!" 运算符 -->
<#assign number1 = 10>
<#assign number2 = 5>

"+" ：${number1 + number2}
"-" ：${number1 - number2}
"*" ：${number1 * number2}
"/" ：${number1 / number2}
"%" ：${number1 % number2}

比较运算符：
<#if number1 + number2 gte 12 || number1 - number2 lt 6>
    "*" ：${number1 * number2}
<#else>
    "/" ：${number1 / number2}
</#if>

内建函数：
<#assign data = "abcd1234">
第一个字母大写：${data?cap_first}
所有字母小写：${data?lower_case}
所有字母大写：${data?upper_case}

<#assign floatData = 12.34>
数值取整：${floatData?int}
获取集合的长度：${users?size}
时间格式化：${dateTime?string("yyyy-MM-dd")}

空判断和对象集合：
<#if users??>
    <#list users as user>
        姓名：${user.username} - 年纪：${user.age} - 班级：${user.clazz}
    </#list>
<#else>
    ${user!"变量为空则给一个默认值"}
</#if>

Map 集合：
<#assign mapData={"name":"程序员","salary":15000}>
直接通过 Key 获取 Value 值：${mapData["name"]}

通过 Key 遍历 Map：
<#list mapData?keys as key>
    Key：${key} - Value：${mapData[key]}
</#list>

通过 Value 遍历 Map：
<#list mapData?values as value>
    Value：${value}
</#list>

List 集合：
<#assign listData=["ITDragon","blog","is","cool"]>
<#list listData as value>${value}</#list>

<!-- include 指令 -->
<!-- 引入其他文件：<#include "otherFreeMarker.ftl" /> -->

macro 宏指令：
<#macro mo>
    定义无参数的 macro - ${name}
</#macro>

使用宏 macro：<@mo />

<#macro moArgs a b c>
    定义带参数的宏 macro - ${a + b + c}
</#macro>

使用带参数的宏 macro：<@moArgs a=1 b=2 c=3 />

<!-- 命名空间 -->
<!--
<#import "otherFreeMarker.ftl" as otherFtl>
${otherFtl.otherName}
<@otherFtl.addMethod a=10 b=20 />
<#assign otherName="修改 otherFreeMarker.ftl 中的 otherName 变量值">
${otherFtl.otherName}
<#assign otherName="修改 otherFreeMarker.ftl 中的 otherName 变量值" in otherFtl />
${otherFtl.otherName}
-->

</body>
</html>
