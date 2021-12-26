<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/navbar.jsp" %>

<div class="container-fluid row justify-content-center" style="padding-right: 8vw;padding-left: 8vw;">
<div class="mt-5 mb-5">
<div class="card shadow text-center" style="  min-height : 100px;">
  <div class="card-body">
    <h5 class="card-title">Teacher Portal</h5>
    <p class="card-text">View All Teachers</p>
    <a  href="/admin/teachers/add" class="btn btn-warning shadow ms-auto">Add Teacher</a>
  </div>
</div>
</div>
    <div class="card shadow mt-2 mb-5 p-3 mt-2">
        <table class="table table-hover mt-4" id="datatable">
            <thead>
                <tr>
                    <th>Employee ID</th>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Subject</th>
                    <th>Username</th>
                    <th>Email Address</th>
                    <th>Date Created</th>
                    <th>Activated?</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${teachers}" var="teacher">
                <tr>
                    <td>${teacher.employee.employeeId}</td>
                    <td>${teacher.employee.user.firstName} ${teacher.employee.user.middleName} ${teacher.employee.user.lastName}</td>
                    <td>${teacher.employee.user.gender}</td>
                    <td>${teacher.subject.subjectName}</td>
                    <td>${teacher.employee.user.username}</td>
                    <td>${teacher.employee.user.emailAddress}</td>
                    <td>
                        <fmt:formatDate pattern="dd-MM-yyyy" value="${teacher.employee.user.dateCreated}" />
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${teacher.employee.user.isActive == true}">Yes</c:when>
                            <c:otherwise><a class="btn btn-outline-success btn-sm"
                                    onclick="getRequest('/${role}/users/${teacher.employee.user.userId}/activate')" role="button">Activate</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                       <div class="d-flex">
                                <a class="btn btn-success p-1 me-2 shadow" role="button" href="/admin/teachers/${teacher.teacherId}">
                                    <span class="fa fa-eye" aria-hidden="true"></span>
                                </a>
                                
                                <a class="btn btn-primary p-1 me-2 shadow" role="button" href="/admin/teachers/${teacher.teacherId}/edit" >
                                    <span class="fa fa-pen" aria-hidden="true"></span>
                                </a>
                                <a class="btn btn-danger p-1 me-1 shadow" role="button" href="/admin/teachers/${teacher.employee.user.userId}/delete">
                                    <span class="fa fa-trash" aria-hidden="true"></span>
                                </a>   
                                </div>          
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@ include file="/footer.jsp" %>