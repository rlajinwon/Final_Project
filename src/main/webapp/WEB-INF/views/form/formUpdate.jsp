<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
<script src="https://cdn.ckeditor.com/4.22.1/full-all/ckeditor.js"></script>
</head>
<body>
	<h2>전자결재 양식 수정</h2>
	<form action="./formUpdate" method="post">

        <input type="hidden" name="formId" value="${vo.formId}">

        <input type="hidden" name="categoryId" value="${vo.categoryId}">

		<label>양식 제목</label><br>
		<input type="text" name="formTitle" value="${vo.formTitle}" required><br><br>
		

        <label>내용</label><br>
        <textarea name="contentHtml" id="editor">${vo.contentHtml}</textarea><br><br>
        
        <button type="submit">수정</button>
        
	</form>
	
	<script>
        CKEDITOR.replace('editor',{
            extraPlugins: 'forms,tableresize',
            extraAllowedContent: 'td[id, contenteditable];th[id, contenteditable];img[src,style];',
            height: 1000
        });
    </script>
</body>
</html>