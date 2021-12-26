<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp" %>

<div class="login-form section text-center py-5">
    <div class="container">
        <div class="row">
            <div class="col-sm-6 offset-sm-3">
                <div class="mt-5">
                    <div class="card card-info" style="min-width: 25rem;">
                        <div class="card-header">
                            ${title}
                        </div>
                        <div class="card-body">
                            <form class="form-horizontal" action="${submitUrl}" method="post">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <c:if test="${not empty errormessage}">
                                            <div class="alert alert-danger alert-dismissible">
                                                <button type="button" class="close"
                                                    data-dismiss="alert">&times;</button>
                                                ${errormessage}
                                            </div>
                                        </c:if>

                                        <div class="input-group mt-4">
                                            <input id="oldPassword" type="password" class="form-control"
                                                name="oldPassword" placeholder="Old Password" required>
                                        </div>

                                        <div class="input-group mt-4">
                                            <input id="password" type="password" class="form-control" name="password"
                                                placeholder="New Password" required>
                                        </div>

                                        <div class="input-group mt-4">
                                            <input id="confirmPassword" type="password" class="form-control"
                                                name="confirmPassword" placeholder="Confirm New Password" required>
                                        </div>

                                        <div class="input-group mt-4">
                                            <button class="btn btn-lg btn-warning btn-block"
                                                type="submit">Submit</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<%@ include file="footer.jsp" %>