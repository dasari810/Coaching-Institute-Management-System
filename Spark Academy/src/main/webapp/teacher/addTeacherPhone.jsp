<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../navbar.jsp" %>

<div class="container" style="padding-left: 5%; padding-right: 5%;">
    
    <div class="row shadow bg-white rounded mt-5 mb-5" style="border: 1px solid whitesmoke; padding: 0 40px;">
        
        <div class="container">
        
        <div class="row justify-content-center mt-5 mb-3">
        <h3>Edit Teacher Phone </h2>
       </div>
        

            <div class="row mt-5 mb-5">
                <div class="col-sm-5" style="text-align: center;">
                    Phone Numbers:
                </div>
                <div class="col-sm-7">
                    <c:forEach var="phoneNumber" items="${phoneNumbers}">
                        <div>${phoneNumber.phoneNumber} &emsp;
                            <a href="#" onclick="postRequest('/profile/users/${userId}/phoneNumber/delete',
                            {'phoneNumber': '${phoneNumber.phoneNumber}'})"> Remove
                            </a>
                        </div>
                    </c:forEach>

                    <input type="text" id="phoneNumber">
                    <a class="btn btn-warning btn-sm" onclick="postRequest('/profile/users/${userId}/phoneNumber/add',
                    {'phoneNumber': $('#phoneNumber').val()})" role="button">Add</a>
                    <div id="error" style="color: red;"></div>
            </div>
        </div>
        <div class="row mt-4 mb-4">
            <div class="col-sm-7 offset-sm-5">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <a class="btn btn-primary" type="button" href="/${role}/teachers/${teacherId}">submit</a>
                </sec:authorize>
                <sec:authorize access="!hasRole('ROLE_ADMIN')">
                    <a class="btn btn-primary" type="button" href="/profile/teacher">Submit</a>
                </sec:authorize>
            </div>
        </div>
    </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<%@ include file="../footer.jsp" %>