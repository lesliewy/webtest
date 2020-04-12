<%--
  Created by IntelliJ IDEA.
  User: leslie
  Date: 2020/3/21
  Time: 下午10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<input type="text" value="<%= request.getParameter("keyword") %>">
<button>搜索</button>
<div>
    您搜索的关键词是：<%= request.getParameter("keyword") %>
</div>
</body>
</html>
