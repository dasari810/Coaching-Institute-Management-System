<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../navbar.jsp" %>

<div class="container-fluid row justify-content-center" style="padding-right: 8vw;padding-left: 8vw;">
<div class="mt-5 mb-5">
<div class="card shadow text-center" style="  min-height : 100px;">
  <div class="card-body">
    <h5 class="card-title">Results</h5>
    <p>Result of a test</p>
     <sec:authorize access='hasAnyRole("ROLE_ADMIN,ROLE_STAFF")'>
                  
    <a  href="/${role}/academics/tests/${testId}/results/add" class="btn btn-warning shadow ms-auto">Add Result</a>
    </sec:authorize>
  </div>
</div>
</div>
    <div class="card shadow mt-2 mb-5 p-3 mt-2">
        <table class="table table-hover mt-4" id="datatable">
             <thead>
                <tr>
                    <th>Student ID</th>
                    <th>Student Name</th>
                    <th>Total Marks</th>
                    <th>Rank</th>
                    <sec:authorize access='hasAnyRole("ROLE_STAFF", "ROLE_ADMIN")'>
                    <th>Action</th>
                    </sec:authorize>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${results}" var="result">
                <tr <c:if test="${result.student.user.userId == pageContext.request.userPrincipal.principal.user.userId}">class="table-success"</c:if>>
                    <td>${result.student.studentId}</td>
                    <td>${result.student.user.firstName} ${result.student.user.middleName} ${result.student.user.lastName}</td>
                    <td>
                        ${result.marksScored}
                    </td>
                    <td>${marksToRank[result.marksScored]}</td>
                    
                    <sec:authorize access='hasAnyRole("ROLE_STAFF", "ROLE_ADMIN")'>
                    
                    <td>
                       <div class="d-flex">
                        <a class="btn btn-primary p-1 me-2 shadow" href="/${role}/academics/tests/${testId}/results/${result.student.studentId}/edit"
                            role="button"><span class="fa fa-pen" aria-hidden="true"></span></a>
                        <a class="btn btn-danger p-1 me-2 shadow" href="/${role}/academics/tests/${testId}/results/${result.student.studentId}/delete" 
                            role="button"><span class="fa fa-trash" aria-hidden="true"></span></a>
                            
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