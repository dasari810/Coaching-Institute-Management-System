<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../navbar.jsp" %>

<div class="container-fluid row justify-content-center" style="padding-right: 8vw;padding-left: 8vw;">
<div class="mt-5 mb-5">
<div class="card shadow text-center" style="  min-height : 100px;">
  <div class="card-body">
    <h5 class="card-title">Tests and Results</h5>
    <p><span class="badge bg-info text-dark">Info</span>go to a <a href="/${role}/academics/batches"> batch </a> to add a new test</p>
  </div>
</div>
</div>
    <div class="card shadow mt-2 mb-5 p-3 mt-2">
        <table class="table table-hover mt-4" id="datatable">
             <thead>
                <tr>
                    <th>Test ID</th>
                    <th>Test Name</th>
                    <th>Course</th>
                    <th>batch</th>
                    <th>Room Number</th>
                    <th>Test Date</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                    <th>Maximum Marks</th>
                    <th>Result</th>
                    <sec:authorize access='hasAnyRole("ROLE_STAFF", "ROLE_ADMIN")'><th>Action</th></sec:authorize>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${tests}" var="test">
                <tr>
                    <td>${test.testId}</td>
                    <td>${test.testName}</td>
                    <c:choose>
                        <c:when test="${role == 'student'}"><td>${test.courseName} - ${test.courseId}</td></c:when>
                        <c:otherwise><td>${test.courseId}</td></c:otherwise>
                    </c:choose>
                    <td>${test.batchId}</td>
                    <td>${test.roomNumber}</td>
                    <td><fmt:formatDate pattern="dd-MM-yyyy" value="${test.testDate}" /></td>
                    <td><fmt:formatDate pattern="HH:mm:ss" value="${test.startTime}" /></td>
                    <td><fmt:formatDate pattern="HH:mm:ss" value="${test.endTime}" /></td>
                    <td>${test.maximumMarks}</td>
                    <td>
                        <c:choose>
                            <c:when test="${role == 'student' && test.marksScored == null}">
                                <div style="color: red;">Not appeared</div>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-success" href="/${role}/academics/tests/${test.testId}/results" role="button">View</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <sec:authorize access='hasAnyRole("ROLE_STAFF", "ROLE_ADMIN")'>
                    <td>
                            <div class="d-flex">
                                <a class="btn btn-success p-1 me-2 shadow" role="button" href="/${role}/academics/tests/${test.testId}">
                                    <span class="fa fa-eye" aria-hidden="true"></span>
                                </a>
                                
                                <a class="btn btn-primary p-1 me-2 shadow" role="button" href="/${role}/academics/tests/${test.testId}/edit" >
                                    <span class="fa fa-pen" aria-hidden="true"></span>
                                </a>
                                <a class="btn btn-danger p-1 me-1 shadow" role="button" href="/${role}/academics/tests/${test.testId}/delete">
                                    <span class="fa fa-trash" aria-hidden="true"></span>
                                </a>   
                                </div>       
                    </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@ include file="../footer.jsp" %>