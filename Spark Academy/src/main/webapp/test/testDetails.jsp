<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../navbar.jsp" %>

<div class="container" style="padding-left: 5%; padding-right: 5%;">

 <div class="card text-center mt-5 shadow mb-5" style="  min-height : 100px;">
  <div class="card-body">
    <h5 class="card-title">Test Details</h5>
    <p class="card-text">View Details of a Test</p>
  </div>
</div>
    <div class="row shadow bg-white rounded mb-5" style="border: 1px solid whitesmoke; padding: 0 40px;">
        <table class="table table-borderless mt-4">
            <tr>
                <th style="width: 40%;"></th>
                <th style="width: 10%;"></th>
                <td style="width: 50%; text-align: right;">
                    <a class="btn btn-success shadow" href="/${role}/academics/tests/${test.testId}/results/" role="button">View Results</a>
                    <a class="btn btn-primary shadow" href="/${role}/academics/tests/${test.testId}/edit" role="button">Edit Test</a>
                </td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Test ID</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${test.testId}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Test Name</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${test.testName}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Course</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${course.courseId} - ${course.courseName}</td>
            </tr>
             <tr>
                <th style="width: 40%; text-align: center;">Batch</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${batch.batchId} - ${batch.batchName}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Room Number</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${test.roomNumber}</td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Test Date</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%"><fmt:formatDate pattern="dd-MM-yyyy" value="${test.testDate}" /></td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Start Time</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%"><fmt:formatDate pattern="HH:mm:ss" value="${test.startTime}" /></td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">End Time</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%"><fmt:formatDate pattern="HH:mm:ss" value="${test.endTime}" /></td>
            </tr>
            <tr>
                <th style="width: 40%; text-align: center;">Maximum Marks</th>
                <th style="width: 10%;"></th>
                <td style="width: 50%">${test.maximumMarks}</td>
            </tr>
           
        </table>
        </div>
    </div>

<%@ include file="../footer.jsp" %>