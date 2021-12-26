<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="/navbar.jsp" %>

<div class="login-form section text-center py-5">
    <div class="container">
        <div class="row">
            <div class="col-sm-8 offset-sm-2">
                <div class="mt-5">
                    <div class="card card-info" style="min-width:1000px">
                        <div class="card-header" >
                              <div class="row">
			<div class="col-md-8 offset-md-2 d-flex justify-content-center text-center border-info">
				<h2 class="mt-3 ">Student Registration</h2>
			</div>
		</div>
                        </div>
                        <div class="card-body">
                            <form:form class="form-horizontal needs-validation" action="/register/student" method="post" modelAttribute="student">
                            <div  class="row mb-5">
                                <!--  <div class="form-group col-lg-6">
                                    <label class="form-label">Username: ${mandatory}</label>
                                    <div >
                                        <form:input type="text" path="user.username" class="form-control" placeholder="Enter the username" autofocus="true" required="true"></form:input>
                                        <div class="ml-2" style="text-align: left;">
                                            <form:errors path="user.username" style="color: red;"></form:errors>
                                        </div>
                                    </div>
                                </div> -->
                                <div class="form-group col-lg-6">
								<c:set var="usernameErrors">
									<form:errors path="user.username" />
								</c:set>
								<form:label path="user.username">Username</form:label>
								<form:input path="user.username" required="true"
									class="form-control ${not empty usernameErrors ? 'is-invalid' : ''}" />
								<div class="invalid-feedback">
									<c:if test="${not empty usernameErrors}">
										<form:errors path="user.username"></form:errors>
									</c:if>
									<c:if test="${empty usernameErrors}">
										Please provide a username
									</c:if>
								</div>
							</div>
                                <div class="form-group col-lg-6">
                                    <label class="form-label">Password: ${mandatory}</label>
                                    <div >
                                        <form:input type="password" path="user.password" class="form-control" placeholder="Enter the password" required="true"></form:input>                                        
                                        <div class="ml-2" style="text-align: left;">
                                            <form:errors path="user.password" style="color: red;"></form:errors>
                                        </div>
                                    </div>
                                </div>
                                </div>
                                <div class="form-group row mb-4">
                                    <label class="col-3 control-label">Name: ${mandatory}</label>
                                    <div class="col-3">
                                        <form:input type="text" path="user.firstName" class="form-control" placeholder="First name" required="true"></form:input>
                                    </div>
                                    <div class="col-3">
                                        <form:input type="text" path="user.middleName" class="form-control" placeholder="Middle name ( Optional )"></form:input>
                                    </div>
                                    <div class="col-3">
                                        <form:input type="text" path="user.lastName" class="form-control" placeholder="Last name" required="true"></form:input>
                                    </div>
                                    <div class="col-9 offset-3 ml-2">
                                        <form:errors path="user.firstName" style="color: red;"></form:errors>
                                        <form:errors path="user.middleName" style="color: red;"></form:errors>
                                        <form:errors path="user.lastName" style="color: red;"></form:errors>
                                    </div>
                                    
                                </div>
                                <div class="form-group row mb-4">
                                    <label class="col-3 control-label">Email Address: ${mandatory}</label>
                                    <div class="col-9">
                                        <form:input type="email" path="user.emailAddress" class="form-control" placeholder="Enter the email address" required="true"></form:input>
                                        <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
                                        <div class="ml-2" style="text-align: left;">
                                            <form:errors path="user.emailAddress" style="color: red;"></form:errors>
                                        </div>
                                        <div class="invalid-feedback">Please provide a valid Email ID</div>
                                    </div>
                                </div>
                      <div class="row mb-4">
						<div class="form-group col-lg-6">
							<form:label path="dateOfBirth">DOB ${mandatory}</form:label>
							<c:set var="today" value="<%=new java.util.Date()%>"/>
		                    <fmt:formatDate var="today_formated_date" value="${today}" pattern="yyyy-MM-dd"/>  
							<form:input path="dateOfBirth" type="date" max="${today_formated_date}"
								class="form-control" required="true" />
							<div class="invalid-feedback">Please provide a Birth Date</div>
						</div>
						<div class="form-group col-lg-6">
							<form:label path="user.gender">Gender ${mandatory}</form:label>
							<form:select class="form-select" path="user.gender" required="true">
								<option selected disabled value="">Choose...</option>
								<form:option path="user.gender" value="Male" selected="">Male</form:option>
								<form:option path="user.gender" value="Female">Female</form:option>
								<form:option path="user.gender" value="Other">Other</form:option>
							</form:select>
							<div class="invalid-feedback">Please select a Gender</div>
						</div>
					</div>
					 <div class="row mb-4">
						<div class="form-group col-lg-6">
							<form:label path="schoolAttending">School/College Attending</form:label>
							 <form:input type="text" path="schoolAttending" class="form-control"></form:input>
							<div class="invalid-feedback">Please provide a name </div>
						</div>
						<div class="form-group col-lg-6">
							<form:label path="percentage10th">10th percentage </form:label>
							 <form:input type="text" path="percentage10th" class="form-control"></form:input>
							<div class="invalid-feedback">Please provide occupation </div>
						</div>
					</div>
					<h4 class="mt-3 mb-3">Address Details  </h4>
					<div class="row mb-4">
						<div class="form-group col-lg-6">
							<form:label path="street">Street ${mandatory}</form:label>
							<form:input path="street" maxlength="255"
								class="form-control" required="true" />
							<div class="invalid-feedback">Please provide a street name </div>
						</div>
						<div class="form-group col-lg-6">
							<form:label path="city">City ${mandatory}</form:label>
							<form:input path="city" maxlength="255" class="form-control"
								required="true" />
							<div class="invalid-feedback">Please provide a city name </div>
						</div>
					</div>

					<div class="row mb-4">
						<div class="form-group col-lg-6">
							<form:label path="state">State ${mandatory}</form:label>
							<form:input path="state" maxlength="255"
								class="form-control" required="true" />
							<div class="invalid-feedback">Please provide a state name </div>
						</div>
						<div class="form-group col-lg-6">
							<form:label path="houseNumber">House Number </form:label>
							<form:input path="houseNumber" maxlength="255"
								class="form-control" required="true" />
							<div class="invalid-feedback">Please provide a House Number name </div>
						</div>
						
					</div>
			   
               
                 <tr>
                    <th style="width: 40%;">
                        <h4>Guardian Details</h4>
                    </th>
                    <th style="width: 10%;"></th>
                    <td style="width: 50%; text-align: right;"></td>
                </tr>
                <div class="row mb-4">
						<div class="form-group col-lg-6">
							<form:label path="street">Name ${mandatory}</form:label>
							 <form:input type="text" path="guardian.name" class="form-control"></form:input>
							<div class="invalid-feedback">Please provide a name </div>
						</div>
						<div class="form-group col-lg-6">
							<form:label path="city">Occupation</form:label>
							 <form:input type="text" path="guardian.occupation" class="form-control"></form:input>
							<div class="invalid-feedback">Please provide occupation </div>
						</div>
					</div>

					<div class="row mb-4">
						<div class="form-group col-lg-6">
							<form:label path="state">Email ${mandatory}</form:label>
							 <form:input type="text" path="guardian.email" class="form-control"></form:input>
							<div class="invalid-feedback">Please provide a emailaddress </div>
						</div>
						<div class="form-group col-lg-6">
							<form:label path="houseNumber">Address ${mandatory}</form:label>
							 <form:input type="text" path="guardian.address" class="form-control"></form:input>
							<div class="invalid-feedback">Please provide address </div>
						</div>
						
					</div>
					
					 <div class="row mb-4 justify-center">
						<div class="form-group col-lg-6">
							<form:label path="street">Relation with Student ${mandatory}</form:label>
							
							  <form:select class="form-select" path="guardian.relationWithStudent" required="true">
                            <form:option value="Father">Father</form:option>
                            <form:option value="Mother">Mother</form:option>
                            <form:option value="Other">Other</form:option>
                        </form:select>
                        <form:errors path="guardian.relationWithStudent" style="color: red;"></form:errors>
							<div class="invalid-feedback">Please provide a relation with students </div>
						</div>
						
					</div>
                
                              <div class="row">
						<div class="col-md-4 offset-md-4 text-center">
							<form:button class="btn btn-lg btn-primary btn-block" type="submit">Submit</form:button>
						</div>
					</div>
                                
                            </form:form>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-md-6 control">
                                            <div>
                                                Already Registered ? <a href="/login">Login Here </a>
                                            </div>
                                        </div>
                                        <div class="col-md-6 control">
                                            <div>
                                                Forgot password ?  <a href="/user/forgot-password"> Click Here </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<%@ include file="/footer.jsp" %>
