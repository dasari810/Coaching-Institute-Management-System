<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../navbar.jsp" %>

<div class="container" style="padding-left: 5%; padding-right: 5%;">

 <div class="card text-center mt-5 shadow mb-5" style="  min-height : 100px;">
  <div class="card-body">
    <h5 class="card-title">Enrollment Details</h5>
    <p class="card-text">View Details of a Enrollment</p>
  </div>
</div>
    <div class="row shadow bg-white rounded mb-5" style="border: 1px solid whitesmoke; padding: 0 40px;">
        <table class="table table-borderless mt-4">
            <tr>
                <th style="width: 40%;"></th>
                <th style="width: 10%;"></th>
                <td style="width: 50%; text-align: right;">
                    <sec:authorize access="!hasRole('ROLE_STUDENT')">
                    <a class="btn btn-warning shadow" href="/${role}/academics/enrollments/${enrollment.enrollmentId}/edit" role="button">Edit Enrollment</a>
                    </sec:authorize>
                </td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Enrollment ID</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${enrollment.enrollmentId}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Student ID</th>
                <th style="width: 10%;"></th>
                <td>
                 <sec:authorize access='hasRole("ROLE_ADMIN")'>
                       <a href="/${role}/students/${enrollment.studentId}">${enrollment.studentId}</a>
                    </sec:authorize>
                    
                    <sec:authorize access='!hasRole("ROLE_ADMIN")'>
                          ${enrollment.studentId}
                    </sec:authorize>
               </td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Course ID</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%"><a href="/${role}/academics/courses/${enrollment.courseId}">${enrollment.courseId}</a></td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Batch ID</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%"><a href="/${role}/academics/courses/${enrollment.courseId}/${enrollment.batchId}">${enrollment.batchId}</a></td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Join Date</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%"><fmt:formatDate pattern="dd-MM-yyyy" value="${enrollment.joinDate}" /></td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">End Date</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%"><fmt:formatDate pattern="dd-MM-yyyy" value="${enrollment.endDate}" /></td>
            </tr>
        </table>
        <div class="col-12" style="text-align: center;">
            <hr>
            <h5>Transaction Details</h5>
        </div>
        <table class="table table-borderless mt-4">
            <tr>
                <th style="width: 40%; text-align: center;">Transaction ID</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${enrollment.transaction.transactionId}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Amount</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">Rs ${enrollment.transaction.amount}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Date</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%"><fmt:formatDate pattern="dd-MM-yyyy" value="${enrollment.transaction.date}" /></td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Time</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%"><fmt:formatDate pattern="HH:mm:ss" value="${enrollment.transaction.time}" /></td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Transaction Mode</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${enrollment.transaction.transactionMode}</td>
            </tr>
        </table>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>