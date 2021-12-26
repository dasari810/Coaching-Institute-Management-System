<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../navbar.jsp" %>

<div class="container-fluid row justify-content-center" style="padding-right: 8vw;padding-left: 8vw;">
<div class="mt-5 mb-5">
<div class="card shadow text-center" style="  min-height : 100px;">
  <div class="card-body">
    <h5 class="card-title">Payroll Management</h5>
    <p>view all payrolls</p>
     <sec:authorize access='hasRole("ROLE_ADMIN")'>
    <a  href="/${role}/payroll/add" class="btn btn-warning shadow ms-auto">Add Payroll</a>
    </sec:authorize>
  </div>
</div>
</div>
    <div class="card shadow mt-2 mb-5 p-3 mt-2">
        <table class="table table-hover mt-4 table-sort" id="datatable">
             <thead>
                <tr>
                    <th>Payment Ref No</th>
                    <th>Employee</th>
                    <th>Month</th>
                    <th>Year</th>
                    <th>Salary credited</th>
                    <th>Date credited</th>
                    <sec:authorize access='hasRole("ROLE_ADMIN")'><th>Action</th></sec:authorize>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${payrolls}" var="payroll">
                <tr>
                    <td>${payroll.paymentRefNo}</td>
                    <td>
                        <c:if test="${payroll.employee.user.role == 'ROLE_TEACHER'}">
                            ${payroll.employee.employeeId} - ${payroll.employee.user.firstName} ${payroll.employee.user.middleName}
                            ${payroll.employee.user.lastName}
                        </c:if>
                        <c:if test="${payroll.employee.user.role == 'ROLE_STAFF'}">
                            ${payroll.employee.employeeId} - ${payroll.employee.user.firstName} ${payroll.employee.user.middleName}
                            ${payroll.employee.user.lastName}
                        </c:if>
                    </td>
                    <td>${payroll.month}</td>
                    <td>${payroll.year}</td>
                    <td>${payroll.salaryCredited}</td>
                    <td><fmt:formatDate pattern="dd-MM-yyyy" value="${payroll.dateCredited}" /></td>
                    <sec:authorize access='hasRole("ROLE_ADMIN")'>
                    <td>
                       <div class="d-flex">
                             <a class="btn btn-success p-1 me-2 shadow" role="button" href="/${role}/payroll/${payroll.paymentRefNo}">
                                    <span class="fa fa-eye" aria-hidden="true"></span>
                                </a>
                                
                                <a class="btn btn-primary p-1 me-2 shadow" role="button" href="/${role}/payroll/${payroll.paymentRefNo}/edit" >
                                    <span class="fa fa-pen" aria-hidden="true"></span>
                                </a>
                                <a class="btn btn-danger p-1 me-1 shadow" role="button" href="/${role}/payroll/${payroll.paymentRefNo}/delete">
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