<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../navbar.jsp" %>

<div class="container-fluid row justify-content-center" style="padding-right: 8vw;padding-left: 8vw;">
<div class="mt-5 mb-5">
<div class="card shadow text-center" style="  min-height : 100px;">
  <div class="card-body">
    <h5 class="card-title">Batches</h5>
    <p class="card-text">list of Batches</p>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
    <a  href="/admin/academics/batches/add" class="btn btn-warning shadow ms-auto">Add Batch</a>
    </sec:authorize>
  </div>
</div>
</div>
    <div class="card shadow mt-2 mb-5 p-3 mt-2">
        <table class="table table-hover mt-4" id="datatable">
            <thead>
                <tr>
                    <th>Batch ID</th>
                    <th>Batch Name</th>
                    <th>Course</th>
                    <th>Fee</th>
                    <th>Room Number</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${batches}" var="batch">
                <tr>
                    <td>${batch.batchId}</td>
                    <td>${batch.batchName}</td>
                    <td>${batch.course.courseName} - ${batch.course.courseId}</td>
                    <td>${batch.fee}</td>
                    <td>${batch.roomNumber}</td>
                    <td><fmt:formatDate pattern="HH:mm:ss" value="${batch.startTime}" /></td>
                    <td><fmt:formatDate pattern="HH:mm:ss" value="${batch.endTime}" /></td>
                    <td>
                      <div class="d-flex">
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
                               </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@ include file="../footer.jsp" %>