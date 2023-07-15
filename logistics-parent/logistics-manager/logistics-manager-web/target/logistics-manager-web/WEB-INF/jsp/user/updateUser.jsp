<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">表单</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>用户操作</span></div>
    <form action="/user/saveOrUpdate" method="post">
        <ul class="forminfo">

            <li><label>账号</label><input name="user.userName" type="text" class="dfinput" /><i></i></li>
            <li><label>密码</label><input name="user.password" type="text" class="dfinput"  /><i></i></li>
            <li><label>姓名</label><input name="user.realName" type="text" class="dfinput"  /><i></i></li>
            <li><label>邮箱</label><input name="user.email" type="text" class="dfinput"  /><i></i></li>
            <li><label>电话</label><input name="user.phone" type="text" class="dfinput"  /><i></i></li>
            <li><label>角色</label>
                <div style="height: 32px;line-height: 32px;">
                    <c:forEach items="${roles}" var="role">
                        <input type="checkbox" value="${role.roleId}" name="roleIds">${role.roleName}
                        &nbsp;&nbsp;
                    </c:forEach>
                    <i></i>
                </div>
            </li>

            <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存"/></li>
        </ul>
    </form>


</div>


<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>