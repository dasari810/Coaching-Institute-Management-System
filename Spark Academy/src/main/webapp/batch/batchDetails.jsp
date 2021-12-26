<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../navbar.jsp" %>

<div class="container" style="padding-left: 5%; padding-right: 5%;">

 <div class="card text-center mt-5 shadow mb-5" style="  min-height : 100px;">
  <div class="card-body">
    <h5 class="card-title">Batch Details</h5>
    <p class="card-text">View Details of a Batch</p>
  </div>
</div>
    <div class="row shadow bg-white rounded mb-5" style="border: 1px solid whitesmoke; padding: 0 40px;">
        <table class="table table-borderless mt-4">
            <tr>
                <th style="width: 40%;"></th>
                <th style="width: 10%;"></th>
                <td style="width: 50%; text-align: right;">
                                                    
                    <sec:authorize access='hasAnyRole("ROLE_ADMIN", "ROLE_STUDENT")'>
                    <a class="btn btn-warning shadow" href="/${role}/academics/courses/${batch.course.courseId}/${batch.batchId}/enrollments/add"
                        role="button">Enroll</a>         
                        
                    </sec:authorize>
                    <sec:authorize access='hasRole("ROLE_ADMIN")'>
                    <a class="btn btn-primary shadow" href="/${role}/academics/courses/${batch.course.courseId}/${batch.batchId}/enrollments"
                        role="button">View Students</a>
                    <a class="btn btn-success shadow" href="/${role}/academics/courses/${batch.course.courseId}/${batch.batchId}/edit"
                        role="button">Edit Batch</a>
                     <a class="btn btn-info shadow" href="/${role}/academics/courses/${batch.course.courseId}/${batch.batchId}/tests/add"
                       role="button">Add Test</a>   
                    </sec:authorize>
                    </td>
                </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Batch ID</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${batch.batchId}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Batch Name</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${batch.batchName}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Course</th>
                <th style="width: 10%;"></th>
               <td style="width: 50%"> <a href="/${role}/academics/courses/${batch.course.courseId}">${batch.course.courseName} - ${batch.course.courseId}</a></td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Fee</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${batch.fee}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Room Number</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${batch.roomNumber}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Start Time</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%"><fmt:formatDate pattern="HH:mm:ss" value="${batch.startTime}" /></td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">End Time</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%"><fmt:formatDate pattern="HH:mm:ss" value="${batch.endTime}" /></td>
            </tr>
        </table>
        <div class="col-12" style="text-align: center;">
            <hr>
            <h5>Teachers</h5>
        </div>
        <sec:authorize access='hasRole("ROLE_ADMIN")'>
        <div class="col-12" style="text-align: right;">
            <a class="btn btn-warning shadow" href="/${role}/academics/courses/${batch.course.courseId}/${batch.batchId}/add-teacher"
                role="button">Manage Teachers</a>
        </div>
        </sec:authorize>
        <div class="col-12 mt-2">
        <table class="table table-hover mt-4 table-sort">
            <thead>
                <tr>
                    <th>Teacher ID</th>
                    <th>Name</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${teachers}" var="teacher">
                <tr>
                    <td>
                    <sec:authorize access='hasRole("ROLE_ADMIN")'>
                       <a href="/${role}/teachers/${teacher.teacherId}">${teacher.teacherId}</a>
                    </sec:authorize>
                    
                    <sec:authorize access='!hasRole("ROLE_ADMIN")'>
                      ${teacher.teacherId}
                    </sec:authorize>
                   
                    </td>
                    <td>${teacher.employee.user.firstName} ${teacher.employee.user.middleName} ${teacher.employee.user.lastName}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>