
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>

<title>模仿天猫官网</title>
<script>
    $(function(){

        <c:if test="${!empty msg}">
        $("span.errorMessage").html("${msg}");
        $("div.registerErrorMessageDiv").css("visibility","visible");
        </c:if>
        // $("#name").change(function () {
        // 	var name=$(this).val();
        // 	var p=/^[A-Za-z]\w/;
        // 	if ( p.test(name)){
        // 		$("span.errorMessage").html("用户名必须是字母，数字和下划线的组合,并且以字母开头");
        // 		$("div.registerErrorMessageDiv").css("visibility","visible")
        // 	}
        // });
        $(".registerForm").submit(function(){
            if(0==$("#password").val().length){
                $("span.errorMessage").html("请输入密码");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }

            return true;
        });
    })
</script>



<form method="post" action="checkuser" class="registerForm">


    <div class="registerDiv">
        <div class="registerErrorMessageDiv">
            <div class="alert alert-danger" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                <span class="errorMessage"></span>
            </div>
        </div>


        <table class="registerTable" align="center">
            <tr>
                <td  class="registerTip registerTableLeftTD">修改登陆密码</td>
            </tr>
            <tr>
                <td class="registerTableLeftTD">登陆密码</td>
                <td class="registerTableRightTD"><input id="password" name="password" type="password"   > </td>
            </tr>

            <tr>
                <td colspan="2" class="registerButtonTD">
                    <a href="registerSuccess.jsp"><button>提   交</button></a>
                </td>
            </tr>
        </table>
    </div>
</form>