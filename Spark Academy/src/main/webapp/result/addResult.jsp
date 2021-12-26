<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../navbar.jsp" %>

<div class="container mt-5 mb-5" style="padding-left: 5%; padding-right: 5%;">
    
     <div class="row justify-content-center mt-4 mb-3">
        <h2>Add Result Details </h2>
    </div>
    <div class="row shadow bg-white rounded" style="border: 1px solid whitesmoke; padding: 0 40px;">
        <table class="table table-borderless mt-4">
            <form:form class="form-horizontal" action="${submiturl}"
                method="post" modelAttribute="result">
                <tr>
                    <th style="width: 40%; text-align: center;">Test ID</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">${testId}</td>
                </tr>
                <c:choose>
                    <c:when test="${edit == true}">
                        <tr>
                            <th style="width: 40%; text-align: center;">Student ID ${mandatory}</th>
                            <th style="width: 10%;"></th>
                            <td style="width: 50%">${result.student.studentId}</td>
                        </tr>
                        <tr>
                            <th style="width: 40%; text-align: center;">Student Name ${mandatory}</th>
                            <th style="width: 10%;"></th>
                            <td style="width: 50%">${result.student.user.firstName} ${result.student.user.middleName} ${result.student.user.lastName}</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <th style="width: 40%; text-align: center;">Student ${mandatory}</th>
                            <th style="width: 10%;"></th>
                            <td style="width: 50%">
                                <form:select class="form-control" path="student.studentId" required="true">
                                    <c:forEach var="student" items="${students}">
                                        <form:option value="${student.studentId}">
                                            ${student.studentId} - ${student.user.firstName} ${student.user.middleName} ${student.user.lastName}
                                        </form:option>
                                    </c:forEach>
                                </form:select>
                                <form:errors path="student.studentId" style="color: red;"></form:errors>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                <tr>
                    <th style="width: 40%; text-align: center;">Marks Scored ${mandatory}</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">
                        <form:input type="number" path="marksScored" class="form-control" required="true"></form:input>
                        <form:errors path="marksScored" style="color: red;"></form:errors>
                    </td>
                </tr>
                <tr>
                    <th style="width: 40%; text-align: center;">Maximum Marks</th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%">${maximumMarks}</td>
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