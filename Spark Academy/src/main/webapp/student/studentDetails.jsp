<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/navbar.jsp" %>

<div class="container-fluid custom-container" style="padding-right: 10vw;padding-left: 10vw;">
<div class="mt-5 mb-5 justify-content-center">
<div class="card text-center" style="  min-height : 100px;">
  <div class="card-body">
    <h5 class="card-title">Student Details</h5>
    <p class="card-text">View Details of a Single Student</p>
  </div>
</div>
</div>
    <div class="table-responsive mt-2" style="overflow-x: hidden; ">
    
         <div>
                    <c:if test="${role == 'admin' || role == 'staff'}">
                        <a class="btn btn-primary" href="/${role}/students/${student.studentId}/edit" role="button">Edit Student</a>
                    </c:if>
                    <c:if test="${role == 'student'}">
                        <a class="btn btn-outline-success" href="/profile/change-password" role="button">Change Password</a>
                        <a class="btn btn-primary" href="/profile/student/edit" role="button">Edit Student</a>
                    </c:if>
          
         </div>
        <table class="table mt-4 mb-5 table-bordered">
           
            <tr>
                <th style="text-align: center;">Student ID</th>
              
                <td >${student.studentId}</td>
            </tr>
            <tr>
                <th style="text-align: center;">Name</th>
                
                <td>${student.user.firstName} ${student.user.middleName} ${student.user.lastName}</td>
            </tr>
            <tr>
                <th style="text-align: center;">Username</th>
               
                <td >${student.user.username}</td>
            </tr>
            <tr>
                <th style="text-align: center;">Email Address</th>
               
                <td >${student.user.emailAddress}</td>
            </tr>
            <tr>
                <th style="text-align: center;">Gender</th>
               
                <td >${student.user.gender}</td>
            </tr>
            <tr>
                <th style="text-align: center;">Date of Birth</th>
               
                <td ><fmt:formatDate pattern="dd-MM-yyyy" value="${student.dateOfBirth}" /></td>
            </tr>
            <tr>
                <th style="text-align: center;">Address</th>
               
                <td >${student.houseNumber}, ${student.street}, ${student.city}, ${student.state}</td>
            </tr>
            <tr>
                <th style="text-align: center;">School Attending</th>
               
                <td >${student.schoolAttending}</td>
            </tr>
             <tr>
                <th style="text-align: center;">10th Percentage</th>
               
                <td >${student.percentage10th}</td>
            </tr>
            <tr>
                <th style="text-align: center;">Activated?</th>
              
                <td >
                    <c:if test="${student.user.isActive == true}"><span style="color: green;">Yes</span></c:if>
                    <c:if test="${student.user.isActive == false}"><span style="color: red;">No</span></c:if>
                </td>
            </tr>
            <tr>
                <th style="text-align: center;">Date Created</th>
               
                <td ><fmt:formatDate pattern="dd-MM-yyyy" value="${student.user.dateCreated}" /></td>
            </tr>
            <tr>
                <th style="text-align: center;">Last Login</th>
               
                <td >
                    <fmt:formatDate pattern="dd-MM-yyyy" value="${student.user.lastLoginDate}" />
                    <fmt:formatDate pattern="HH:mm:ss" value="${student.user.lastLoginTime}" />
                </td>
            </tr>
            <tr>
                <th style="text-align: center;">Phone Numbers</th>
                
                <td><c:forEach var="userPhoneNumber" items="${userPhoneNumbers}">
                    <div>${userPhoneNumber.phoneNumber}</div>
                </c:forEach></td>
            </tr>
        </table>
        <div class="col-12" style="text-align: center;">
            <hr>
            <h5>Guardian Details</h5>
        </div>
        <table class="table table-bordered mt-4 mb-5">
            <tr>
                <th style="width: 40%; text-align: center;">Name</th>
               
                <td style="width: 50%">${student.guardian.name}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Occupation</th>
               
                <td style="width: 50%">${student.guardian.occupation}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Address</th>
               
                <td style="width: 50%">${student.guardian.address}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Email Address</th>
               
                <td style="width: 50%">${student.guardian.email}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Relation With Student</th>
              
                <td style="width: 50%">${student.guardian.relationWithStudent}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Phone Numbers</th>
                
                <td style="width: 50%">
                    <c:forEach var="guardianPhoneNumber" items="${guardianPhoneNumbers}">
                        <div>${guardianPhoneNumber.phoneNumber}</div>
                    </c:forEach>
                </td>
            </tr>
        </table>
    </div>
</div>
<%@ include file="/footer.jsp" %>