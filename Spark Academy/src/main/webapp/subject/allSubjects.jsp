<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../navbar.jsp" %>

<div class="container-fluid row justify-content-center" style="padding-right: 8vw;padding-left: 8vw;">
<div class="mt-5 mb-5">
<div class="card shadow text-center" style="  min-height : 100px;">
  <div class="card-body">
    <h5 class="card-title">Subjects</h5>
    <p class="card-text">View All Subjects</p>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
     <a  href="/admin/academics/subjects/add" class="btn btn-warning shadow ms-auto">Add Subject</a>
     </sec:authorize>
  </div>
</div>
</div>
    <div class="card shadow mt-2 mb-5 p-3 mt-2">
        <table class="table table-hover mt-4" id="datatable">
            <thead>
                <tr>
                    <th>Subject ID</th>
                    <th>Subject Name</th>
                    <th>Description</th>
                    <sec:authorize access="hasRole('ROLE_ADMIN')"><th>Action</th></sec:authorize>
                </tr>
            </thead>
           <tbody>
            <c:forEach items="${subjects}" var="subject">
                <tr>
                    <td>${subject.subjectId}</td>
                    <td>${subject.subjectName}</td>
                    <td>${subject.description}</td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <td>
                             <div class="d-flex">
                               
                                <a class="btn btn-primary p-1 me-2 shadow" role="button" href="/${role}/academics/subjects/${subject.subjectId}/edit" >
                                    <span class="fa fa-pen" aria-hidden="true"></span>
                                </a>
                                <a class="btn btn-danger p-1 me-1 shadow" role="button" href="/${role}/academics/subjects/${subject.subjectId}/delete">
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