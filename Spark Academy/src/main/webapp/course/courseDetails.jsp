<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../navbar.jsp" %>

<div class="container" style="padding-left: 5%; padding-right: 5%;">

 <div class="card text-center mt-5 shadow mb-5" style="  min-height : 100px;">
  <div class="card-body">
    <h5 class="card-title">Course Details</h5>
    <p class="card-text">View Details of a Course</p>
  </div>
</div>
    <div class="row shadow bg-white rounded mb-5" style="border: 1px solid whitesmoke; padding: 0 40px;">
        <table class="table table-borderless mt-4">
            <sec:authorize access='hasRole("ROLE_ADMIN")'>
            <tr>
                <th style="width: 40%;"></th>
                <th style="width: 10%;"></th>
                <td style="width: 50%; text-align: right;">
                    <a class="btn btn-warning shadow" href="/${role}/academics/courses/${course.courseId}/enrollments" role="button">View
                        Students</a>
                    <a class="btn btn-warning  shadow" href="/${role}/academics/courses/${course.courseId}/add-subject" role="button">
                        Add / Edit Subject</a>
                    <a class="btn btn-warning  shadow" href="/${role}/academics/courses/${course.courseId}/edit" role="button">Edit Course</a>
                </td>
            </tr>
            </sec:authorize>
            <tr>
                <th style="width: 40%; text-align: center;">Course ID</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${course.courseId}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Course Name</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${course.courseName}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Description</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${course.description}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Subjects</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">
                    <c:forEach var="subject" items="${subjects}">
                        <div>${subject.subjectName} - ${subject.subjectId}</div>
                    </c:forEach>
                </td>
            </tr>
        </table>
        <div class="col-12 mt-4" style="text-align: center;">
            <hr>
            <h5>Batches</h5>
        </div>
        <sec:authorize access='hasRole("ROLE_ADMIN")'>
        <div class="col-12" style="text-align: right;">
            <a class="btn btn-warning shadow" href="/${role}/academics/courses/${course.courseId}/add-batch" role="button">Add Batch</a>
        </div>
        </sec:authorize>
        <div class="col-12 mt-2">
        <table class="table table-hover mt-4 table-sort">
            <thead>
                <tr>
                    <th>Batch ID</th>
                    <th>Batch Name</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${batches}" var="batch">
                <tr>
                    <td>${batch.batchId}</td>
                    <td>${batch.batchName}</td>
                    <td>
                        
                         <a class="btn btn-success p-1 me-2 shadow" role="button" href="/${role}/academics/courses/${batch.course.courseId}/${batch.batchId}">
                                    <span class="fa fa-eye" aria-hidden="true"></span>
                                </a>
                                 <sec:authorize access='hasRole("ROLE_ADMIN")'>
                                <a class="btn btn-primary p-1 me-2 shadow" role="button" href="/${role}/academics/courses/${batch.course.courseId}/${batch.batchId}/edit" >
                                    <span class="fa fa-pen" aria-hidden="true"></span>
                                </a>
                                <a class="btn btn-danger p-1 me-1 shadow" role="button" href="/${role}/academics/courses/${batch.course.courseId}/${batch.batchId}/delete">
                                    <span class="fa fa-trash" aria-hidden="true"></span>
                                </a>   
                        </sec:authorize>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>