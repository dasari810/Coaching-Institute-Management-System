 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/navbar.jsp" %>

<div class="container-fluid row justify-content-center" style="padding-right: 8vw;padding-left: 8vw;">
<div class="mt-5 mb-5">
<div class="card shadow text-center" style="  min-height : 100px;">
  <div class="card-body">
    <h5 class="card-title">Staff Portal</h5>
    <p class="card-text">View All Staff</p>
    <a  href="/admin/staffs/add" class="btn btn-warning shadow ms-auto">Add Staff</a>
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
                    <th>Designation</th>
                    <th>Username</th>
                    <th>Email Address</th>
                    <th>Date Created</th>
                    <th>Activated?</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${staffs}" var="staff">
                <tr>
                    <td>${staff.employee.employeeId}</td>
                    <td>${staff.employee.user.firstName} ${staff.employee.user.middleName} ${staff.employee.user.lastName}</td>
                    <td>${staff.employee.user.gender}</td>
                    <td>${staff.designation}</td>
                    <td>${staff.employee.user.username}</td>
                    <td>${staff.employee.user.emailAddress}</td>
                    <td>
                        <fmt:formatDate pattern="dd-MM-yyyy" value="${staff.employee.user.dateCreated}" />
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${staff.employee.user.isActive == true}">Yes</c:when>
                            <c:otherwise><a class="btn btn-outline-success btn-sm"
                                    onclick="getRequest('/${role}/users/${staff.employee.user.userId}/activate')" role="button">Activate</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                       <div class="d-flex">
                                <a class="btn btn-success p-1 me-2 shadow" role="button" href="/admin/staffs/${staff.staffId}">
                                    <span class="fa fa-eye" aria-hidden="true"></span>
                                </a>
                                
                                <a class="btn btn-primary p-1 me-2 shadow" role="button" href="/admin/staffs/${staff.staffId}/edit" >
                                    <span class="fa fa-pen" aria-hidden="true"></span>
                                </a>
                                <a class="btn btn-danger p-1 me-1 shadow" role="button" href="/admin/staffs/${staff.employee.user.userId}/delete">
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