<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp" %>

<div class="container" style="padding-left: 5%; padding-right: 5%;">
        
  <div class="jumbotron">
  <h1 class="display-4">Verification</h1>
  <p class="lead">Your Email-ID has been verified successfully</p>
  <hr class="my-4">
  <p>Please login to your account</p>
  <p class="lead">
    <a class="btn btn-primary btn-lg btn-success" href="/login" role="button">Login</a>
  </p>
  </div>
        
</div>

<%@ include file="footer.jsp" %>