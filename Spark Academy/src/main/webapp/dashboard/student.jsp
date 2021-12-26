<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/navbar.jsp" %>
 	
<div class="container">

    <div class="row my-lg-5 justify-content-center">
    
       
        <div class="col-4 feature-card p-lg-4 p-4 mx-5" style="cursor:pointer" onclick="location.href='/${role}/academics'">
        
        
            <div class="card card-profile shadow">
            <div class="row justify-content-center">
              <div class="col-lg-3 order-lg-2">
                <div class="card-profile-image">
                  <a href="#">
                    <img src="https://www.pinclipart.com/picdir/big/15-158087_academics-logo-youtube-png-clipart.png" class="rounded-circle">
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
                       <h4 class="my-3">ACADEMIC PORTAL</h3>
            <p>Manage all academic stuff.</p>
                    </div>
                  </div>
                </div>
              </div>
              
            </div>
          </div>
           
        </div>
        
        <div class="col-4 feature-card p-lg-4 p-4 mx-4" style="cursor:pointer" onclick="location.href='/profile/student'">
        
        
           <div class="card card-profile shadow">
            <div class="row justify-content-center">
              <div class="col-lg-3 order-lg-2">
                <div class="card-profile-image">
                  <a href="#">
                    <img src="https://www.hncpl.com/upload/demo.png" class="rounded-circle">
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
                      <h4 class="my-3">PROFILE</h3>
                        <p>view and edit your profile</p>
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