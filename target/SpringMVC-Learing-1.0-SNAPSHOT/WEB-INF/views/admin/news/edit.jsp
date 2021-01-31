<%--
  Created by IntelliJ IDEA.
  User: Smurf3r
  Date: 11/14/2020
  Time: 07:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-news"/>
<c:url var="Newsurl" value="/admin-news-list"/>
<!DOCTYPE html>
<html>
<head>
    <title>Chỉnh Sửa Bài Viết</title>
</head>
<body>
<div class="container">
    <div class="col-sm-12 margin-15" >
        <form id="formNews">
            <div class="form-group">
                <label for="categoryId">Thể Loại</label>
                <select class="form-control" id="categoryId" name="categoryId" required="">
                    <option value="none">Chọn loại bài viết</option>
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.id}" <c:if test="${category.id == model.categoryId}">selected</c:if> >
                        ${category.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="title">Tiêu đề</label>
                <input type="text" required="" class="form-control" name="title" id="title" placeholder="" value="${model.title}">
            </div>
            <div class="form-group">
                <label for="thumbnail">Thumbnail</label>
                <input type="text" class="form-control" name="thumbnail" id="thumbnail" placeholder="">
            </div>
            <div class="form-group">
                <label for="shortDescription">Mô tả ngắn</label>
                <input type="text" required="" class="form-control"  name="shortDescription" id="shortDescription" placeholder="" value="${model.shortDescription}">
            </div>
            <div class="form-group">
                <label for="content">Nội dung</label>
                <textarea class="form-control" id="content" name="content" style="height: 10em">${model.content}</textarea>
            </div>
            <div class="form-group">
                <c:if test="${not empty model.id}">
                    <input type="button" class="btn btn-primary" id="btnUpdateOrAddNews" value="Chỉnh Sửa"/>
                </c:if>
                <c:if test="${empty model.id}">
                    <input type="button" class="btn btn-primary" id="btnUpdateOrAddNews" value="Thêm Mới"/>
                </c:if>
            </div>
            <input type="hidden" id="id" name="id" value="${model.id}">
        </form>
    </div>
</div>
<script>
    // CKediter dùng để soạn thảo bài viết template la Document
    var content ;
    ClassicEditor
        .create( document.querySelector( '#content' ))
        .then( newEditor =>{  // lấy dữ liệu từ tất cả các dòng của textarea
            content = newEditor;
        })
        .catch( error => {
            console.error( error );
        } );
    //kiểm tra là thực hiện edit hay thêm mới
    $('#btnUpdateOrAddNews').click(function (e){
        e.preventDefault(); //sử dụng để tránh việc submit nhầm url đang hiện hành
        var  data ={};
        var formData  = $('#formNews').serializeArray(); //sử dụng tạo ra 1 array keyvalue với key là các name và value là giá trị của thẻ input đó
        console.log(formData)
        $.each(formData,function (i,v){
            data[""+v.name+""]  = v.value;
        });
        data["content"] = content.getData(); //lấy dữ liệu từ textare
        var id = $('#id').val();
        if(id == ""){
            addNews(data);
        }else {
            updateNews(data);
        }
    });
    function addNews(data){
        $.ajax({
            url:'${APIurl}',
            type: 'POST',
            contentType: 'application/json',  //kiểu dữ liệu gửi lên server
            dataType: 'json',  //chấp nhận kiểu dữ liệu trả về là json
            data: JSON.stringify(data),  //hàm convert từ object sang JSONstring
            success:()=>{
                window.location.href = "${Newsurl}?type=list&page=1&maxPageItem=2&sortName='title'&sortBy='desc'&message=insert_success&alert=success";
            },
            error:()=>{
                window.location.href = "${Newsurl}?type=list&maxPageItem=2&page=1&sortName='title'&sortBy='desc'&message=error_system&alert=danger";
            }
        });
    }
    function updateNews(data){
        $.ajax({
            url:'${APIurl}',
            type: 'PUT',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(data),
            success:()=>{
                window.location.href = "${Newsurl}?type=list&page=1&maxPageItem=2&sortName='title'&sortBy='desc'&message=update_success&alert=success";
            },
            error:()=>{
                window.location.href = "${Newsurl}?type=list&page=1&maxPageItem=2&sortName='title'&sortBy='desc'&message=error_system&alert=danger";
            }
        })
    }
</script>
</body>
</html>
