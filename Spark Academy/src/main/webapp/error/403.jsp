<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../navbar.jsp" %>

<div class="container" style="padding-left: 5%; padding-right: 5%;">
        
  
<div class="container mt-5 mb-5">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1 class="mb-5">
                    Oops!</h1>
                <h2>
                    403 Access Denied</h2>
                <div class="error-details mb-5">
                    Sorry,  You are not do not have permission to view the requested page 
                </div>
                <div class="error-actions">
                    <a href="/" class="btn btn-warning btn"><span class="glyphicon glyphicon-home"></span>
                        Take Me Home </a>
                          <a href="mailto:spark.coaching.academy@gmail.com" target="_blank" class="btn btn-warning">Contact Support  </a>
       
                </div>
            </div>
        </div>
    </div>
</div>
  
</div>

<%@ include file="../footer.jsp" %>