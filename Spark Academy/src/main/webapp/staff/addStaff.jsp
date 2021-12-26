<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../navbar.jsp" %>

<div class="container" style="padding-left: 5%; padding-right: 5%;">
    <div class="row justify-content-center mt-5 mb-3">
        <h2>Add Staff Details </h2>
    </div>
    <div class="row shadow bg-white rounded mb-5" style="border: 1px solid whitesmoke; padding: 0 40px;">
        <table class="table table-borderless mt-4">
            <form:form class="form-horizontal" action="${submiturl}" method="post" modelAttribute="staff">
                <tr>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%; text-align: right;">
                        <a href="#" onclick="window.location.reload();">Reset <i class="fa fa-refresh"
                                aria-hidden="true"></i></a>
                    </td>
                </tr>
                <c:if test="${edit == true}">
                <tr>
                    <th style="width: 40%; text-align: center;">Staff ID</th>
                    
                    <td style="width: 50%">${staff.staffId}</td>
                </tr>
                </c:if>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <tr>
                    <th style="width: 40%; text-align: center;">Name ${mandatory}</th>
                    
                    <td style="width: 50%">
                        <form:input type="text" path="employee.user.firstName" class="form-control" required="true"
                            placeholder="First Name"></form:input>
                        <form:errors path="employee.user.firstName" style="color: red;"></form:errors>
                        <form:input type="text" path="employee.user.middleName" class="form-control" placeholder="Middle Name"></form:input>
                        <form:errors path="employee.user.middleName" style="color: red;"></form:errors>
                        <form:input type="text" path="employee.user.lastName" class="form-control" placeholder="Last Name"></form:input>
                        <form:errors path="employee.user.lastName" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Username ${mandatory}</th>
                   
                    <td style="width: 50%">
                        <form:input type="text" path="employee.user.username" class="form-control" required="true"></form:input>
                        <form:errors path="employee.user.username" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Email Address ${mandatory}</th>
                    
                    <td style="width: 50%">
                        <form:input type="email" path="employee.user.emailAddress" class="form-control" required="true"></form:input>
                        <form:errors path="employee.user.emailAddress" style="color: red;"></form:errors>
                    </td>
                </tr>
                </sec:authorize>
                
                <tr>
                    <th style="width: 40%; text-align: center;">Gender ${mandatory}</th>
                   
                    <td style="width: 50%">
                        <form:select class="form-control" path="employee.user.gender" required="true">
                            <form:option value="Male">Male</form:option>
                            <form:option value="Female">Female</form:option>
                            <form:option value="Not Specified">Not Specified</form:option>
                        </form:select>
                        <form:errors path="employee.user.gender" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Address ${mandatory}</th>
                   
                    <td style="width: 50%">
                        <form:input type="text" path="employee.houseNumber" class="form-control" placeholder="House Number"></form:input>
                        <form:errors path="employee.houseNumber" style="color: red;"></form:errors>
                        <form:input type="text" path="employee.street" class="form-control" required="true" placeholder="Street"></form:input>
                        <form:errors path="employee.street" style="color: red;"></form:errors>
                        <form:input type="text" path="employee.city" class="form-control" required="true" placeholder="City"></form:input>
                        <form:errors path="employee.city" style="color: red;"></form:errors>
                        <form:input type="text" path="employee.state" class="form-control" required="true" placeholder="State"></form:input>
                        <form:errors path="employee.state" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Designation</th>
                   
                    <td style="width: 50%">
                        <form:input type="text" path="designation" class="form-control"></form:input>
                        <form:errors path="designation" style="color: red;"></form:errors>
                    </td>
                </tr>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <tr>
                    <th style="width: 40%; text-align: center;">Basic Salary ${mandatory}</th>
                   
                    <td style="width: 50%">
                        <form:input type="number" path="employee.basicSalary" class="form-control"></form:input>
                        <form:errors path="employee.basicSalary" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Join Date</th>
                    
                    <td style="width: 50%">
                        <form:input type="date" path="employee.joinDate" class="form-control"></form:input>
                        <form:errors path="employee.joinDate" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">End Date</th>
                  
                    <td style="width: 50%">
                        <form:input type="date" path="employee.endDate" class="form-control"></form:input>
                        <form:errors path="employee.endDate" style="color: red;"></form:errors>
                    </td>
                </tr>
                </sec:authorize>
                <tr>
                    <th style="width: 40%; text-align: center;">PAN Number ${mandatory}</th>
                   
                    <td style="width: 50%">
                        <form:input type="text" path="employee.panNumber" class="form-control" required="true" placeholder="Format: AAAAA1234A"></form:input>
                        <form:errors path="employee.panNumber" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Account Number ${mandatory}</th>
                    
                    <td style="width: 50%">
                        <form:input type="number" path="employee.accountNumber" class="form-control" required="true"></form:input>
                        <form:errors path="employee.accountNumber" style="color: red;"></form:errors>
                        <script>
                            var accountNumber = document.getElementById("employee.accountNumber").value;
                            if (accountNumber == 0) {
                                document.getElementById("employee.accountNumber").value = "";
                            }
                        </script>
                    </td>
                </tr>
                <c:if test="${edit == true}">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <tr>
                    <th style="width: 40%; text-align: center;">Activated? ${mandatory}</th>
                  
                    <td style="width: 50%">
                        <form:select class="form-control" path="employee.user.isActive" required="true">
                            <form:option value="1">Yes</form:option>
                            <form:option value="0">No</form:option>
                        </form:select>
                        <form:errors path="employee.user.isActive" style="color: red;"></form:errors>
                    </td>
                </tr>
                </sec:authorize>
                </c:if>
                <tr>
                    <th style="width: 40%; text-align: center;"></th>
                    
                    <td style="width: 50%">
                        <button class="btn btn-primary" type="submit">Go to Next Step</button>
                    </td>
                </tr>
                
            </form:form>
        </table>
    </div>
</div>

<%@ include file="../footer.jsp" %>