<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp" %>

<div class="login-form section text-center py-5 mr-5">
    <div class="container">
        <div class="row mb-5">
            <div class="col-sm-6 offset-sm-3">
                <div class="mt-5">
                    <div class="card card-info" style="min-width:30rem">
                        <div class="card-header">
                           <div class="row">
			<div class="col-md-8 offset-md-2 d-flex justify-content-center text-center border-info">
				<h2 class="mt-3 ">Log In</h2>
			</div>
		</div>
                        </div>
                        <div class="card-body">
                            <form class="form-horizontal" action="/login" method="post">
                              
                                        <c:if test="${not empty message}">
                                            <div class="alert alert-success alert-dismissible">
                                                <button type="button" class="close" data-dismiss="alert">&times;</button>
                                                ${ message }
                                            </div>
                                        </c:if>
                                        <c:if test="${not empty error}">
                                            <div class="alert alert-danger alert-dismissible">
                                                <button type="button" class="close" data-dismiss="alert">&times;</button>
                                                ${ error }
                                            </div>
                                        </c:if>
                                        
                                        <div class="input-group mt-2">
                                            <label class="mx-2">Username</label>
                                            <input id="username" type="text" class="form-control" name="username" value=""
                                                placeholder="Username" autofocus="true" required>
                                        </div>

                                        <div class="input-group mt-4" id="show_hide_password">
                                            <label class="mx-2">Password</label>
                                            <input id="password" type="password" class="form-control mx-1" name="password"
                                                placeholder="Password" required>
                                                 <div class="input-group-addon mt-2">
                                             <a href=""><i class="fa fa-eye-slash" aria-hidden="true"></i></a>
                                             </div>
                                        </div>
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                                        <div class="input-group mt-4">
                                            <button class="btn btn-lg btn-warning btn-block" type="submit">Submit</button>
                                        </div>
                                   
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-md-6 control">
                                            <div style="padding-top:15px;">
                                                Don't have an account ?  <br><a href="/register/student"> Register Here </a>
                                            </div>
                                        </div>
                                        <div class="col-md-6 control">
                                            <div style="padding-top:15px;">
                                                Forgot password ? <br><a href="/user/forgot-password"> Click Here </a>
                                            </div>
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
