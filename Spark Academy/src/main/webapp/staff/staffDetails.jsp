<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../navbar.jsp" %>

<div class="container mt-5" style="padding-left: 5%; padding-right: 5%;">
   
   <div class="card text-center" style="  min-height : 100px;">
  <div class="card-body">
    <h5 class="card-title">Staff Details</h5>
    <p class="card-text">View Details of a Staff</p>
  </div>
</div>
   
   
    <div class="row shadow bg-white rounded mt-5 mb-5" style="border: 1px solid whitesmoke; padding: 0 40px;">
        <c:if test="${not empty successmessage}">
            <div class="alert alert-success alert-dismissible mt-4" style="width: 100%; text-align: center;">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                ${successmessage}
            </div>
        </c:if>
        <div class="mt-5 mb-5 justify-content-center">

        <table class="table table-borderless mt-4">
            <tr>
                <th style="width: 40%;"></th>
                <th style="width: 10%;"></th>
                <td style="width: 50%; text-align: right;">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <a class="btn btn-primary" href="/${role}/staffs/${staff.staffId}/edit" role="button">Edit Staff</a>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_STAFF')">
                        <a class="btn btn-outline-success" href="/profile/change-password" role="button">Change Password</a>
                        <a class="btn btn-primary" href="/profile/staff/edit-staff" role="button">Edit Staff</a>
                    </sec:authorize>
                </td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Employee ID</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.employee.employeeId}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Name</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.employee.user.firstName} ${staff.employee.user.middleName} ${staff.employee.user.lastName}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Username</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.employee.user.username}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Email Address</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.employee.user.emailAddress}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Gender</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.employee.user.gender}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Address</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.employee.houseNumber}, ${staff.employee.street}, ${staff.employee.city}, ${staff.employee.state}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Designation</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.designation}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Activated?</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">
                    <c:if test="${staff.employee.user.isActive == true}"><span style="color: green;">Yes</span></c:if>
                    <c:if test="${staff.employee.user.isActive == false}"><span style="color: red;">No</span></c:if>
                </td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Date Created</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%"><fmt:formatDate pattern="dd-MM-yyyy" value="${staff.employee.user.dateCreated}" /></td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Last Login</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">
                    <fmt:formatDate pattern="dd-MM-yyyy" value="${staff.employee.user.lastLoginDate}" />
                    <fmt:formatDate pattern="HH:mm:ss" value="${staff.employee.user.lastLoginTime}" />
                </td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Phone Numbers</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%"><c:forEach var="userPhoneNumber" items="${userPhoneNumbers}">
                    <div>${userPhoneNumber.phoneNumber}</div>
                </c:forEach></td>
            </tr>
        </table>
        <div class="col-12" style="text-align: center;">
            <hr>
            <h5>Employee Details</h5>
        </div>
        <table class="table table-borderless mt-4">
            <tr>
                <th style="width: 40%; text-align: center;">Basic Salary</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.employee.basicSalary}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Join Date</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%"><fmt:formatDate pattern="dd-MM-yyyy" value="${staff.employee.joinDate}" /></td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">End Date</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%"><fmt:formatDate pattern="dd-MM-yyyy" value="${staff.employee.endDate}" /></td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">PAN Number</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.employee.panNumber}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Account Number</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${staff.employee.accountNumber}</td>
            </tr>
        </table>
    </div>
</div>
</div>

<%@ include file="../footer.jsp" %>