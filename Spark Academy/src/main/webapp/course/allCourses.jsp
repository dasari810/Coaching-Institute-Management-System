<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../navbar.jsp" %>

<div class="container-fluid row justify-content-center" style="padding-right: 8vw;padding-left: 8vw;">
<div class="mt-5 mb-5">
<div class="card shadow text-center" style="  min-height : 100px;">
  <div class="card-body">
    <h5 class="card-title">Courses</h5>
    <p class="card-text">list of Courses</p>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
    <a  href="/admin/academics/courses/add" class="btn btn-warning shadow ms-auto">Add Course</a>
    </sec:authorize>
  </div>
</div>
</div>
    <div class="card shadow mt-2 mb-5 p-3 mt-2">
        <table class="table table-hover mt-4" id="datatable">
            <thead>
                <tr>
                    <th>Course ID</th>
                    <th>Course Name</th>
                    <th>Description</th>
                    <th>Subjects</th>
                    <c:if test="${not empty pageContext.request.userPrincipal}">
                    <th>Action</th>
                    </c:if>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${courses}" var="course">
                <tr>
                    <td>${course.courseId}</td>
                    <td>${course.courseName}</td>
                    <td>${course.description}</td>
                    <td>
                        <c:forEach var="subject" items="${course.subjects}">
                            <div>${subject.subjectName} - ${subject.subjectId}</div>
                        </c:forEach>
                    </td>
                   
                    <c:if test="${not empty pageContext.request.userPrincipal}">
                    <td>
                    <div class="d-flex">
                                <a class="btn btn-success p-1 me-2 shadow" role="button" href="/${role}/academics/courses/${course.courseId}">
                                    <span class="fa fa-eye" aria-hidden="true"></span>
                                </a>
                                 <sec:authorize access='hasRole("ROLE_ADMIN")'>
                                <a class="btn btn-primary p-1 me-2 shadow" role="button" href="/${role}/academics/courses/${course.courseId}/edit" >
                                    <span class="fa fa-pen" aria-hidden="true"></span>
                                </a>
                                <a class="btn btn-danger p-1 me-1 shadow" role="button" href="/${role}/academics/courses/${course.courseId}/delete">
                                    <span class="fa fa-trash" aria-hidden="true"></span>
                                </a>   
                        </sec:authorize>
                        </div>
                    </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@ include file="../footer.jsp" %>