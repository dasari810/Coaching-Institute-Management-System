<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Registration</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link
	href="https://fonts.googleapis.com/css2?family=Lato:ital@1&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="/css/main.css" type="text/css">
</head>
<body>

<div id="main-wrapper">

<jsp:include page="navbar.jsp">
       <jsp:param name="role" value="${curRole}"/>
</jsp:include>

	<div class="container align-self-center col-lg-8 shadow my-5">
		<div class="card-header" >
                              <div class="row">
			<div class="col-md-8 offset-md-2 d-flex justify-content-center text-center border-info">
				<h2 class="mt-3 ">Student Registration</h2>
			</div>
		</div>
                        </div>
		<div class="row">
			<div class="col">

				<c:set var="sid" scope="session" value="${student.studentId}" />

				<form:form action="" method="post" class="needs-validation m-3"
					modelAttribute="student" novalidate="true">

					<c:if test="${sid==0}">
						<div class="form-row">
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
							<div class="form-group col-lg-6" id="show_hide_password">
								<c:set var="passwordErrors">
									<form:errors path="user.password" />
								</c:set>
								<form:label path="user.password">Password</form:label>
								<form:password path="user.password" required="true"
									class="form-control ${not empty passwordErrors ? 'is-invalid' : ''}" />
									 <div class="col-lg-2">
                                             <a href=""><i class="fa fa-eye-slash" aria-hidden="true"></i></a>
                                             </div>
								<div class="invalid-feedback">
									<c:if test="${not empty passwordErrors}">
										<form:errors path="user.password"></form:errors>
									</c:if>
									<c:if test="${empty passwordErrors}">
										Please provide a strong password
									</c:if>
								</div>							</div>
						</div>
						<div class="form-group row">
							<form:label path="user.emailAddress" class="col-lg-4 col-form-label">Email Address</form:label>
							<div class="col-lg-8">
								<form:input class="form-control" type="email"
									path="user.emailAddress" maxlength="255" required="true" />
								<div class="invalid-feedback">Please provide a valid Email ID</div>
							</div>
						</div>
					</c:if>

					<c:if test="${sid!=0}">
						<form:hidden path="studentId" value="${studentID}" />
						<form:hidden path="user.active" value="${user.active}" />
						<form:hidden path="user.role" value="${role}" />
						<form:hidden path="user.username" value="Dummy Username" />
						<form:hidden path="user.password" value="Dummy Password" />
						<form:hidden path="user.emailAddress" value="${user.emailAddress}" />
					</c:if>





					

					
					<div class="form-row">
						<div class="form-group col-lg-6">
							<form:label path="dateOfBirth">Birth Date</form:label>
							<c:set var="today" value="<%=new java.util.Date()%>"/>
		<fmt:formatDate var="today_formated_date" value="${today}" pattern="yyyy-MM-dd"/>  
							<form:input path="dateOfBirth" type="date" max="${today_formated_date}"
								class="form-control" required="true" />
							<div class="invalid-feedback">Please provide a Birth Date</div>
						</div>
						<div class="form-group col-lg-6">
							<form:label path="user.gender">Gender</form:label>
							<form:select class="custom-select" path="user.gender" required="true">
								<option selected disabled value="">Choose...</option>
								<form:option path="user.gender" value="Male" selected="">Male</form:option>
								<form:option path="user.gender" value="Female">Female</form:option>
								<form:option path="user.gender" value="Other">Other</form:option>
							</form:select>
							<div class="invalid-feedback">Please select a Gender</div>
						</div>
					</div>

					<div class="form-row">
						<div class="form-group col-lg-4">
							<form:label path="user.firstName">First Name</form:label>
							<form:input path="user.firstName" maxlength="255"
								class="form-control" required="true" />
							<div class="invalid-feedback">Please provide a valid first name </div>
						</div>
						<div class="form-group col-lg-4">
							<form:label path="user.middleName">Middle Name</form:label>
							<form:input path="user.middleName" maxlength="255"
								class="form-control" placeholder="optional" />
							<div class="invalid-feedback">Please provide a valid middle name </div>
						</div>
						<div class="form-group col-lg-4">
							<form:label path="user.lastName">Last Name</form:label>
							<form:input path="user.lastName" maxlength="255"
								class="form-control" required="true" />
							<div class="invalid-feedback">Please provide a valid last name </div>
						</div>
					</div>
					<div class="form-row">
						
						<div class="form-group col-lg-6">
							<form:label path="percentage10th">Precentage</form:label>
							<form:input path="percentage10th" class="form-control"
								type="number" max="100" min="0" step="1" />
						</div>
						<div class="form-group col-lg-6">
							<form:label path="schoolAttending">School/College Attending</form:label>
							<form:input path="schoolAttending" class="form-control"
								 type="text" />
							
						</div>
					</div>
                    <h4 class="mt-3 mb-3 col-md-4 offset-md-4 text-center">Address Details  </h4>
					<div class="form-row">
					<div class="form-group col-lg-6">
							<form:label path="houseNumber">House No</form:label>
							<form:input path="houseNumber" maxlength="255" class="form-control"
								/>
						</div>
						<div class="form-group col-lg-6">
							<form:label path="street">Street</form:label>
							<form:input path="street" maxlength="255"
								class="form-control" required="true" />
							<div class="invalid-feedback">Please provide a street name </div>
						</div>
						
					</div>

					<div class="form-row">
						<div class="form-group col-lg-6">
							<form:label path="state">State</form:label>
							<form:input path="state" maxlength="255"
								class="form-control" required="true" />
							<div class="invalid-feedback">Please provide a state name </div>
						</div>
						<div class="form-group col-lg-6">
							<form:label path="city">City</form:label>
							<form:input path="city" maxlength="255" class="form-control"
								required="true" />
							<div class="invalid-feedback">Please provide a city name </div>
						</div>
					</div>
	<h4 class="mt-3 mb-3 col-md-4 offset-md-4 text-center">Guardian Details  </h4>
	 <div class="row mb-2">
						<div class="form-group col-lg-6">
							<form:label path="street">Name</form:label>
							 <form:input type="text" path="guardian.name" class="form-control" required="true"></form:input>
							<div class="invalid-feedback">Please provide a name </div>
						</div>
						<div class="form-group col-lg-6">
							<form:label path="city">Occupation</form:label>
							 <form:input type="text" path="guardian.occupation" class="form-control"></form:input>
							<div class="invalid-feedback">Please provide occupation </div>
						</div>
					</div>

					<div class="row mb-2">
						<div class="form-group col-lg-6">
							<form:label path="state">Email</form:label>
							 <form:input type="text" path="guardian.email" class="form-control" required="true"></form:input>
							<div class="invalid-feedback">Please provide a emailaddress </div>
						</div>
						<div class="form-group col-lg-6">
							<form:label path="houseNumber">Address</form:label>
							 <form:input type="text" path="guardian.address" class="form-control" required="true"></form:input>
							<div class="invalid-feedback">Please provide address </div>
						</div>
						
					</div>
					
					 <div class="row mb-2 justify-center">
						<div class="form-group col-lg-6">
							<form:label path="street">Relation with Student</form:label>
							
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
							<form:button class="btn btn-warning" type="submit">Submit</form:button>
						</div>
					</div>
				</form:form>
			</div>
		</div>

	</div>
	
<jsp:include page="footer.jsp">
    <jsp:param name="role" value="${curRole}"/>
</jsp:include>

</div>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
		crossorigin="anonymous"></script>
	<script src="/js/main.js"></script>
</body>
</html>