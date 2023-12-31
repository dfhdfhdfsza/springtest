<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</head>
<body>
	<h1>My First Spring Project</h1>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active"  href="/">Home</a>
        </li>
       <c:if test="${loginmvo eq null}">
       		<li class="nav-item">
          		<a class="nav-link" href="/member/login" >Log in</a>
        	</li>
       </c:if>
        <c:if test="${loginmvo ne null}">
        	<li class="nav-item">
          		<a class="nav-link"  href="/member/logout">Log out</a>
        	</li>
        </c:if>
        <li class="nav-item">
          <a class="nav-link" href="/member/signup">Sign up</a>
        </li>
        <li class="nav-item">
          <a class="nav-link"  href="/board/register">REG</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/board/list">Board List</a>
        </li>
        <c:if test="${loginmvo ne null}">
        	<li> <a class="nav-link" href="/member/modify">${loginmvo.id} 님 로그인되셨습니다.</a></li>
		</c:if>
      </ul>
      <form class="d-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>