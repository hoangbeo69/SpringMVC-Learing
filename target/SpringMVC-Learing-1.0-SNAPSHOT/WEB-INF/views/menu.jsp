<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: smurF3r
  Date: 1/24/2021
  Time: 4:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<ul>
    <c:forEach var="item" items="${menu}">
        <li>${item}</li>
    </c:forEach>
</ul>

