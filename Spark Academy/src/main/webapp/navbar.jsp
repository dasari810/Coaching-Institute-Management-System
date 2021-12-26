<%@ page language="java"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="mandatory"><span style="color: red;">*</span></c:set>
<c:set var="role">${pageContext.request.userPrincipal.principal.user.smallRole}</c:set>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="today" value="<%= new java.util.Date()%>" />
<fmt:formatDate var="todayFormatted" value="${today}" pattern="yyyy-MM-dd" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html class="h-100">
 <head>
     <title>${title}</title>
      <meta charset="utf-8">
      <title>Spark Academy</title>
      <link rel="stylesheet" href="/css/main.css" type="text/css">
      
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap5.min.css" rel="stylesheet">



    <script src="https://kit.fontawesome.com/c4ec982e3f.js" crossorigin="anonymous"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lobster+Two&display=swap" rel="stylesheet">
    
<link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"/>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/css/iziToast.min.css" integrity="sha512-O03ntXoVqaGUTAeAmvQ2YSzkCvclZEcPQu1eqloPaHfJ5RuNGiS4l+3duaidD801P50J28EHyonCV06CUlTSag==" crossorigin="anonymous" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/js/iziToast.min.js" integrity="sha512-Zq9o+E00xhhR/7vJ49mxFNJ0KQw1E1TMWkPTxrWcnpfEFDEXgUiwJHIKit93EW/XxE31HSI5GEOW06G6BF1AtA==" crossorigin="anonymous"></script>
</head>


 </head>
  
  
<body class="d-flex flex-column h-100">


<sec:authorize var="isLoggedIn" access="isAuthenticated()" />

<header>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
 <div class="container-fluid">
   <a class="navbar-brand" href="/" style="font-family:Apple Chancery, cursive;font-weight: bold">
    <img src="/img/logo.png" width="30" height="30" class="d-inline-block align-top" alt="" style="border-radius: 50%;" >
    Spark Academy
  </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

<div class="collapse navbar-collapse" id="navbarText">
 
  <ul class="navbar-nav">
    <c:if test="${isLoggedIn }">
      <li class="nav-item active">
        <a class="nav-link text-white" href="/${role}">DashBoard</a>
      </li>
       <li class="nav-item active">
        <a class="nav-link text-white" href="/${role}/academics">Academic Portal</a>
      </li>
       <li class="nav-item">
	        <a class="nav-link text-white" href="/profile">Profile</a>
	   </li>
      </c:if>
    </ul>

</div>
  
   
    <ul class="navbar-nav ml-auto">
   
       <li class="nav-item mt-1 mr-2">
        <a class="nav-link active" href="/">Home</a>
      </li>
       <c:if test="${not isLoggedIn }">
       <li class="nav-item active">
       
        <a class="nav-link" href="/login"><button type="button" class="btn btn-warning">Login</button></a>
      </li>
      </c:if>
       <c:if test="${isLoggedIn }">
       <li class="nav-item mt-1 mr-2">
         <a class="nav-link active" href="/">  Hi ${pageContext.request.userPrincipal.principal.user.username} ! </a>
           
      </li>
       <li class="nav-item active">
        <a class="nav-link" href="/logout"><button type="button" class="btn btn-warning">Logout</button></a>
      </li>
      </c:if>
   
    </ul>
    
  </div>
</nav>
</header>
<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
  <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
    <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
  </symbol>
  <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
    <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
  </symbol>
  <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
    <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
  </symbol>
</svg>

<c:if test="${not empty successToast}">

<div class="alert alert-success alert-dismissible fade show" role="alert">
 <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
   <strong>${successToast}</strong>
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
</c:if>
<c:if test="${not empty errorToast}">

<div class="alert alert-danger alert-dismissible fade show" role="alert">
<svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
 <strong>${errorToast}</strong>
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
</c:if>
<c:if test="${not empty infoToast}">

<div class="alert alert-primary alert-dismissible fade show" role="alert">
 <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Info:"><use xlink:href="#info-fill"/></svg>
  <strong>${infoToast}</strong>
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
</c:if>
<c:if test="${not empty warningToast}">

<div class="alert alert-warning alert-dismissible fade show" role="alert">
<svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Warning:"><use xlink:href="#exclamation-triangle-fill"/></svg>
 <strong>${warningToast}</strong>
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
</c:if>
