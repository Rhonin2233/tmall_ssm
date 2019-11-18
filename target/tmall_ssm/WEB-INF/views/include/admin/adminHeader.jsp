<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"
    pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><%=request.getParameter("pageTitle")%></title>
    <script src="${pageContext.request.contextPath}/js/jquery/2.0.0/jquery.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap/3.3.6/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/back/style.css" rel="stylesheet">
    <script>
        function checkEmpty(id,name) {
            var value=$("#"+id).val();
            if(null==value||value.length==0){
                alert(name+"不能为空");
                $("#"+id)[0].focus();
                return false;
            }
            return true;
        }
        function checkNumber(id, name) {
            var value=$("#"+id).val();
            if(null==value||value.length==0){
                alert(name+"不能为空");
                $("#"+id)[0].focus();
                return false;
            }
            //判断是否是非数字
            if(isNaN(value)){
                alert(name+"必须是数字");
                $("#"+id)[0].focus();
                return false;
            }
            return true;
        }
        function checkInt(id, name) {
            var value=$("#"+id).val();
            if(null==value||value.length==0){
                alert(name+"不能为空");
                $("#"+id)[0].focus();
                return false;
            }
            //返回字符串的开头字符数字
            //如果开头是非数字返回NaN
            //有小数只会返回整数
            if(parseInt(value)!=value){
                alert(name+"必须是整数");
                $("#"+id)[0].focus();
                return false;
            }
            return true;
        }
        $(function () {
            $("a").click(function () {
                var deleteLink=$(this).attr("deleteLink");
                console.log(deleteLink);
                // if("category"==deleteLink){
                //     alert("该操作会直接清空分类下的属性和产品");
                //     var confirmDelete=confirm("确认删除");
                //     if (confirmDelete) return true;
                //     return false;
                // }
                if ("property"==deleteLink){
                    alert("该操作会直接清空分类下的该产品属性");
                    var confirmDelete=confirm("确认删除");
                    if (confirmDelete) return true;
                    return false;
                }
                if ("true"==deleteLink){
                    var confirmDelete=confirm("确认删除");
                    if (confirmDelete) return true;
                    return false;
                }
            });
        })
    </script>
    <style>
        .center{
            text-align: center;
        }
        .max{
            width: 50%;
            height: auto;
        }
        .min{
            width: 130px;
            height: auto;
        }
    </style>
</head>
<body>



