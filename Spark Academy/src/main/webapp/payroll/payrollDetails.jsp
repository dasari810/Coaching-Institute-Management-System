<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../navbar.jsp" %>

<div class="container" style="padding-left: 5%; padding-right: 5%;">

 <div class="card text-center mt-5 shadow mb-5" style="  min-height : 100px;">
  <div class="card-body">
    <h5 class="card-title">Payroll Details</h5>
    <p class="card-text">View Details of a Payroll</p>
  </div>
</div>
    <div class="row shadow bg-white rounded mb-5" style="border: 1px solid whitesmoke; padding: 0 40px;">
        <table class="table table-borderless mt-4">
             <tr>
                <th style="width: 40%;"></th>
                <th style="width: 10%;"></th>
                <td style="width: 50%; text-align: right;">
                    <a class="btn btn-primary" href="/${role}/payroll/${payroll.paymentRefNo}/edit"
                        role="button">Edit Payroll</a>
                </td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Payment Ref No</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${payroll.paymentRefNo}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Employee</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">
                    <c:if test="${payroll.employee.user.role == 'ROLE_TEACHER'}">
                        
                            ${payroll.employee.employeeId} - ${payroll.employee.user.firstName} ${payroll.employee.user.middleName}
                            ${payroll.employee.user.lastName}
                       
                    </c:if>
                    <c:if test="${payroll.employee.user.role == 'ROLE_STAFF'}">
                        
                            ${payroll.employee.employeeId} - ${payroll.employee.user.firstName} ${payroll.employee.user.middleName}
                            ${payroll.employee.user.lastName}
                        
                    </c:if>
                </td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Month</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${payroll.month}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Year</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${payroll.year}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Salary credited</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${payroll.salaryCredited}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Date credited</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%"><fmt:formatDate pattern="dd-MM-yyyy" value="${payroll.dateCredited}" /></td>
            </tr>
        </table>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>