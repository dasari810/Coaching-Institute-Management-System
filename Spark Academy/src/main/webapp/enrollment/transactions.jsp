<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../navbar.jsp" %>

<div class="container-fluid row justify-content-center" style="padding-right: 8vw;padding-left: 8vw;">
<div class="mt-5 mb-5">
<div class="card shadow text-center" style="  min-height : 100px;">
  <div class="card-body">
    <h5 class="card-title">Transactions</h5>
    <p class="card-text">list of Transactions</p>
    <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
    <p><span class="badge bg-info text-dark">Info</span> Go to <a href="/${role}/academics/enrollments">Enrollments</a> to see more details </p>
    </sec:authorize>
  </div>
</div>
</div>
    <div class="card shadow mt-2 mb-5 p-3 mt-2">
        <table class="table table-hover mt-4" id="datatable">
            <thead>
                <tr>
                    <th>Transaction ID</th>
                    <th>Amount</th>
                    <th>Date and Time </th>
                    <th>Mode</th>                   
                    <th>isSuccess</th>                 
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${transactions}" var="transaction">
                <tr>
                    <td>${transaction.transactionId}</td>
                    <td>${transaction.amount}</td>
                    <td>${transaction.date} ${transaction.time}</td>
                    <td>${transaction.transactionMode}</td>
                     <td>${transaction.isSuccess}</td>
                   
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@ include file="../footer.jsp" %>