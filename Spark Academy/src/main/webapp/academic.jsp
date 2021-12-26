<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp" %>
 	
<div class="container">
    <div class="row my-lg-5 justify-content-center">
        <div class="col feature-card p-lg-4 p-4 mx-4" style="cursor:pointer" onclick="location.href='/${role}/academics/subjects'">
        
        
             <div class="card card-profile shadow">
            <div class="row justify-content-center">
              <div class="col-lg-3 order-lg-2">
                <div class="card-profile-image">
                  <a href="#">
                    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS2c1-z232iqZNZILLfR2szXQ4nHs-YIZ86E7QdyqpG4O4PTwPpx4IO2laNAw1Q3CTr77o&usqp=CAU" class="rounded-circle">
                  </a>
                </div>
              </div>
            </div>
            <div class="card-header text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4">
              
            </div>
            <div class="card-body pt-0 pt-md-4">
              <div class="row">
                <div class="col">
                  <div class="card-profile-stats d-flex justify-content-center mt-md-5">
                    <div>
                      <h4 class="my-3">SUBJECTS</h4>
            <p>View all  subjects</p>
                    </div>
                  </div>
                </div>
              </div>
              
            </div>
          </div>
           
        </div>
        
        <div class="col feature-card p-lg-4 p-4 mx-4" style="cursor:pointer" onclick="location.href='/${role}/academics/courses'">
        
        
            <div class="card card-profile shadow">
            <div class="row justify-content-center">
              <div class="col-lg-3 order-lg-2">
                <div class="card-profile-image">
                  <a href="#">
                    <img src="https://www.pnglib.com/wp-content/uploads/2020/08/book-stack_5f3924a60a469.png" class="rounded-circle">
                  </a>
                </div>
              </div>
            </div>
            <div class="card-header text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4">
              
            </div>
            <div class="card-body pt-0 pt-md-4">
              <div class="row">
                <div class="col">
                  <div class="card-profile-stats d-flex justify-content-center mt-md-5">
                    <div>
                         <h4 class="my-3">COURSES</h4>
            <p>View all the courses.</p>
                    </div>
                  </div>
                </div>
              </div>
              
            </div>
          </div>
           
        </div>
        
        <div class="col feature-card p-lg-4 p-4 mx-4" style="cursor:pointer" onclick="location.href='/${role}/academics/batches'">
        
        
           <div class="card card-profile shadow">
            <div class="row justify-content-center">
              <div class="col-lg-3 order-lg-2">
                <div class="card-profile-image">
                  <a href="#">
                    <img src="https://brandlogos.net/wp-content/uploads/2020/09/google-classroom-logo.png" class="rounded-circle">
                  </a>
                </div>
              </div>
            </div>
            <div class="card-header text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4">
              
            </div>
            <div class="card-body pt-0 pt-md-4">
              <div class="row">
                <div class="col">
                  <div class="card-profile-stats d-flex justify-content-center mt-md-5">
                    <div>
                     <h4 class="my-3">BATCHES</h4>
            <p>View all the batches.</p>
                    </div>
                  </div>
                </div>
              </div>
              
            </div>
          </div>           
        </div>
    </div>
    
    <div class="row my-lg-5 justify-content-center">
     <sec:authorize access="hasAnyRole('ROLE_TEACHER')">
        <div class="col-4 feature-card p-lg-4 p-4 mx-4" style="cursor:pointer" onclick="location.href='/teacher/academics/batches-assigned'">
        <div class="card card-profile shadow">
            <div class="row justify-content-center">
              <div class="col-lg-3 order-lg-2">
                <div class="card-profile-image">
                  <a href="#">
                    <img src="https://p.kindpng.com/picc/s/756-7568258_classroom-training-icon-png-transparent-png.png" class="rounded-circle">
                  </a>
                </div>
              </div>
            </div>
            <div class="card-header text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4">            
            </div>
            <div class="card-body pt-0 pt-md-4">
              <div class="row">
                <div class="col">
                  <div class="card-profile-stats d-flex justify-content-center mt-md-5">
                    <div>
                    <h4 class="my-3">BATCHES ASSIGNED</h4>
                       <p>View all the batches assigned to you</p>
                     
                    </div>
                  </div>
                </div>
              </div>
              
            </div>
          </div>
           
           
        </div>
        </sec:authorize>
        <div class="col-4 feature-card p-lg-4 p-4 mx-4" style="cursor:pointer" onclick="location.href='/${role}/academics/tests'">
           
            <div class="card card-profile shadow">
            <div class="row justify-content-center">
              <div class="col-lg-3 order-lg-2">
                <div class="card-profile-image">
                  <a href="#">
                    <img src="https://i.pinimg.com/originals/ea/ac/26/eaac269b38f72dc15c683a2229d2bd55.png" class="rounded-circle">
                  </a>
                </div>
              </div>
            </div>
            <div class="card-header text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4">
              
            </div>
            <div class="card-body pt-0 pt-md-4">
              <div class="row">
                <div class="col">
                  <div class="card-profile-stats d-flex justify-content-center mt-md-5">
                    <div>
                        <h4 class="my-3">TESTS AND RESULTS PORTAL</h4>
                          <p>View all the tests and their results.</p>
                    </div>
                  </div>
                </div>
              </div>
              
            </div>
          </div>
           
        </div>
        
        <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT')">
        <div class="col-4 feature-card p-lg-4 p-4 mx-4" style="cursor:pointer" onclick="location.href='/${role}/academics/enrollments'">
         <div class="card card-profile shadow">
            <div class="row justify-content-center">
              <div class="col-lg-3 order-lg-2">
                <div class="card-profile-image">
                  <a href="#">
                    <img src="https://cdn4.iconfinder.com/data/icons/pretty_office_3/256/Add-Male-User.png" class="rounded-circle">
                  </a>
                </div>
              </div>
            </div>
            <div class="card-header text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4">
              
            </div>
            <div class="card-body pt-0 pt-md-4">
              <div class="row">
                <div class="col">
                  <div class="card-profile-stats d-flex justify-content-center mt-md-5">
                    <div>
                      <h3 class="my-3">ENROLLMENT PORTAL</h3>
            <p>View all the enrollments.</p>
                    </div>
                  </div>
                </div>
              </div>
              
            </div>
          </div>
          </div>
        </sec:authorize>
        
    </div>
    </div>
   

<%@ include file="footer.jsp" %>