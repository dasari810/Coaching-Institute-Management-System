<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/navbar.jsp" %>

<div class="container-fluid row justify-content-center" style="padding-right: 10vw;padding-left: 10vw;">
<div class="mt-5 mb-5 justify-content-center">
<div class="card text-center" style="  min-height : 100px;">
  <div class="card-body shadow">
    <h5 class="card-title">User Portal</h5>
    <p class="card-text">View All Users</p>
  </div>
</div>
</div>

    <div class="table-responsive card shadow mt-2 mb-5 p-3">
    
        <table class="table table-hover mt-4 mb-5" id="datatable">
        
            <thead  class="thead-light">
                <tr>
                    <th>Username</th>
                    <th>Email Address</th>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Date Created</th>
                    <th>Last Login</th>
                    <th>Role</th>
                    <th>Email Verified?</th>
                    <th>Active?</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.emailAddress}</td>
                    <td>${user.firstName} ${user.middleName} ${user.lastName}</td>
                    <td>${user.gender}</td>
                    <td>
                        <fmt:formatDate pattern="dd-MM-yyyy" value="${user.dateCreated}" />
                    </td>
                    <td>
                        <fmt:formatDate pattern="dd-MM-yyyy" value="${user.lastLoginDate}" />
                        <fmt:formatDate pattern="HH:mm:ss" value="${user.lastLoginTime}" />
                    </td>
                    <td>${user.role}</td>
                    <td>
                        <c:if test="${user.isEmailVerified == true}"><span class="badge bg-success text-white">  Yes  </span></c:if>
                        <c:if test="${user.isEmailVerified == false}"><span class="badge bg-danger text-white">No</span></c:if>
                    </td>
                    <td>
                        <c:if test="${user.isActive == true}"><span class="badge bg-success text-white">  Yes  </span></c:if>
                        <c:if test="${user.isActive == false}"><span class="badge bg-danger text-white">No</span></c:if>
                    </td>
                    <td>
                        <a  href="/admin/users/${user.userId}/delete"
                        role="button"><button type="button" class="btn btn-danger shadow">Delete</button></a>
                       
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@ include file="/footer.jsp" %>