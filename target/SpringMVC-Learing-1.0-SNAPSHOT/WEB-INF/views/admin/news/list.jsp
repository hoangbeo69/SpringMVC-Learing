<%--
  Created by IntelliJ IDEA.
  User: Smurf3r
  Date: 9/18/2020
  Time: 06:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>Danh Sách Bài Viết</title>
</head>

<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="container-fluid">
            <!-- DataTales Example -->
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Danh Sách Bài Viết</h6>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>Tên Bài Viết</th>
                                <th>Mô Tả Ngắn</th>
                                <th>Button</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th>Tên Bài Viết</th>
                                <th>Mô Tả Ngắn</th>
                                <th>Button</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach var="item" items="${listNews}">
                                <tr>
                                    <td>${item.title}</td>
                                    <td>${item.shortDescription}</td>
                                    <td>
                                        <c:url var="singleURL" value="/admin/edit">
                                            <c:param name="type" value="single"/>
                                            <c:param name="id" value="${item.id}"/>
                                        </c:url>
                                        <a class="btn btn-info" title="Chỉnh Sửa"  data-toggle="tooltip" href="<c:url value='${singleURL}'/>">
                                            <i class="fas fa-edit" aria-hidden="true"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div><!-- /.main-content -->
</body>
</html>