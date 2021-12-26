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
        <h3>Manage Subjects</h3>
       </div>
        
      <div class="container">

            <div class="row">
                <div class="col-sm-5" style="text-align: center;">
                    Teachers:
                </div>
                <div class="col-sm-7">
                    <c:forEach var="teacher" items="${teachersPresent}">
                        <div>ES${teacher.employee.employeeId} - ${teacher.employee.user.firstName} ${teacher.employee.user.middleName}
                            ${teacher.employee.user.lastName} &emsp;
                            <a href="#" onclick="postRequest('/${role}/academics/courses/${courseId}/${batchId}/delete-teacher',
                                {'teacherId': '${teacher.teacherId}'})"> Remove
                            </a>
                        </div>
                    </c:forEach>

                    <c:if test="${not empty teachersNotPresent}">
                    <select id="teacherId">
                        <c:forEach var="teacher" items="${teachersNotPresent}">
                            <option value="${teacher.teacherId}">ES${teacher.employee.employeeId} - ${teacher.employee.user.firstName} ${teacher.employee.user.middleName}
                            ${teacher.employee.user.lastName}</option>
                        </c:forEach>
                    </select>
                    <a class="btn btn-outline-danger btn-sm" onclick="postRequest('/${role}/academics/courses/${courseId}/${batchId}/add-teacher',
                    {'teacherId': $('#teacherId').val()})" role="button">Add</a>
                    </c:if>

                    <div id="error" style="color: red;"></div>
                </div>
            </div>
            <div class="row mt-4 mb-4">
                <div class="col-sm-7 offset-sm-5">
                    <a class="btn btn-primary" type="button"
                        href="/${role}/academics/courses/${courseId}/${batchId}">Submit</a>
                </div>
            </div>
        </div>
</div>
</div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<%@ include file="../footer.jsp" %>