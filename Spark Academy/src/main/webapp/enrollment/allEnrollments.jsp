<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../navbar.jsp" %>

<div class="container-fluid row justify-content-center" style="padding-right: 8vw;padding-left: 8vw;">
<div class="mt-5 mb-5">
<div class="card shadow text-center" style="  min-height : 100px;">
  <div class="card-body">
    <h5 class="card-title">Enrollments</h5>
    <p class="card-text">list of Enrollments</p>
    <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
    <p><span class="badge bg-info text-dark">Info</span> Go to <a href="/${role}/academics/batches">Batches</a> to add enrollment </p>
    </sec:authorize>
  </div>
</div>
</div>
    <div class="card shadow mt-2 mb-5 p-3 mt-2">
        <table class="table table-hover mt-4" id="datatable">
            <thead>
                <tr>
                    <th>Enrollment ID</th>
                    <th>Student ID</th>
                    <th>Course ID</th>
                    <th>Batch ID</th>
                    <th>Join Date</th>
                    <th>End Date</th>
                    <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF', 'ROLE_STUDENT')">
                    <th>Action</th>
                    </sec:authorize>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${enrollments}" var="enrollment">
                <tr>
                    <td>${enrollment.enrollmentId}</td>
                    <td>
                     <sec:authorize access='hasRole("ROLE_ADMIN")'>
                       <a href="/${role}/students/${enrollment.studentId}">${enrollment.studentId}</a>
                    </sec:authorize>
                    
                    <sec:authorize access='!hasRole("ROLE_ADMIN")'>
                          ${enrollment.studentId}
                    </sec:authorize>
                   
                    </td>
                    <td><a href="/${role}/academics/courses/${enrollment.courseId}">${enrollment.courseId}</a></td>
                    <td><a href="/${role}/academics/courses/${enrollment.courseId}/${enrollment.batchId}">${enrollment.batchId}</a></td>
                    <td><fmt:formatDate pattern="dd-MM-yyyy" value="${enrollment.joinDate}" /></td>
                    <td><fmt:formatDate pattern="dd-MM-yyyy" value="${enrollment.endDate}" /></td>
                    <td>
                    <div class="d-flex">
                        <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF', 'ROLE_STUDENT')">
                        <a class="btn btn-success p-1 me-2 shadow" href="/${role}/academics/enrollments/${enrollment.enrollmentId}"
                            role="button"> <span class="fa fa-eye" aria-hidden="true"></span></a>
                        </sec:authorize>
                        <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')">
                        <a class="btn btn-primary p-1 me-2 shadow" href="/${role}/academics/enrollments/${enrollment.enrollmentId}/edit"
                            role="button"><span class="fa fa-pen" aria-hidden="true"></span></a>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <a class="btn btn-danger p-1 me-2 shadow" href="/${role}/academics/enrollments/${enrollment.enrollmentId}/delete"                      
                            role="button"> <span class="fa fa-trash" aria-hidden="true"></span></a>
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