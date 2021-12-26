<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../navbar.jsp" %>

<div class="container mt-5 mb-5" style="padding-left: 5%; padding-right: 5%;">
    
     <div class="row justify-content-center mt-4 mb-3">
        <h2>Add Test Details </h2>
    </div>
    <div class="row shadow bg-white rounded" style="border: 1px solid whitesmoke; padding: 0 40px;">
        <table class="table table-borderless mt-4">
            <form:form class="form-horizontal" action="${submiturl}"
                method="post" modelAttribute="test">
                <c:if test="${edit == true}">
                    <tr>
                        <th style="width: 40%; text-align: center;">Test ID</th>
                        <th style="width: 10%;"></th>
                        <td style="width: 50%">${test.testId}</td>
                    </tr>
                </c:if>
                <tr>
                    <th style="width: 40%; text-align: center;">Test Name ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="text" path="testName" class="form-control" required="true"></form:input>
                        <form:errors path="testName" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Course </th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        ${course.courseId} - 
                        ${course.courseName}
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Batch</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        ${batch.batchId} - ${batch.batchName}
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Room Number ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="number" path="roomNumber" class="form-control" required="true"></form:input>
                        <form:errors path="roomNumber" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Test Date ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="date" path="testDate" class="form-control"  required="true" min="${todayFormatted}"></form:input>
                        <form:errors path="testDate" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Start Time ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="time" path="startTime" class="form-control"  required="true"></form:input>
                        <form:errors path="startTime" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">End Time ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="time" path="endTime" class="form-control"  required="true"></form:input>
                        <form:errors path="endTime" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Maximum Marks ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="number" path="maximumMarks" class="form-control"  required="true"></form:input>
                        <form:errors path="maximumMarks" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;"></th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <button class="btn btn-primary" type="submit">Submit</button>
                    </td>
                </tr>
            </form:form>
        </table>
    </div>
</div>

<%@ include file="../footer.jsp" %>