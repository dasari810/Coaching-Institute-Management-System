<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ include file="navbar.jsp" %>


 <div id="carouselExampleIndicators" class="carousel slide carousel-fade mb-5" data-bs-ride="carousel" data-bs-interval="1500">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
    
  </div>
  <div class="carousel-inner" style="max-height:500px">
    <div class="carousel-item active" >
     <img src="https://media-exp1.licdn.com/dms/image/C561BAQFZgwPaJoNnXQ/company-background_10000/0/1630564237814?e=2159024400&v=beta&t=-RINLy1JlDxZ8dhqCO0ODsVS44cJngl76UTrZptToIk" class="d-block w-100" alt="...">
    
    
    </div>
    
    <div class="carousel-item">
    
      <img src="https://pbs.twimg.com/media/EbYsE2_X0AIMN90.jpg" class="d-block w-100" height="650"  alt="...">
     
      
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
  </div>

  <!-- carosoul ends  -->
  
  
  <div>
  
  <div class='container-fluid mt-5 mb-5 col-12' style="text-align: center">
  
    <div class="row" style="justify-content: center" class="mb-5">
 
        <div class="card col-md-3 col-12 shadow " style="cursor:pointer" onclick="location.href='/register/student'">
            <div class="card-content">
                <div class="card-body"> <img class="img mb-3" src="https://www.clipartmax.com/png/full/159-1593805_zac-and-pasindu-student-flat-icon-png.png" height="100" width="100" />
                    <div class="shadow"></div>
                    <h5 class="card-title"> Register As Student </h5>
                    <div class="card-subtitle">
                        <p> <small class="text-muted">Register a student to start enrolling in course , an kick off your journey </small> </p>
                    </div>
                </div>
            </div>
        </div>
       
        <div class="card col-md-3 col-12 ml-2 shadow mx-3" style="cursor:pointer;max-height:10rem" onclick="location.href='/user/courses'">
            <div class="card-content" >
                <div class="card-body"> <img class="img mb-3" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS2c1-z232iqZNZILLfR2szXQ4nHs-YIZ86E7QdyqpG4O4PTwPpx4IO2laNAw1Q3CTr77o&usqp=CAU"  height="100" width="100" />
                     <h5 class="card-title"> View Courses</h5>
                    <div class="card-subtitle">
                        <p> <small class="text-muted"> see the details of course offered by our academy  </small> </p>
                    </div>
                </div>
            </div>
        </div>
        
    </div>
    
</div>
  </div>
 
  
  <!-- features end  -->
  
  
  <!-- testimonals start  -->
  
    <div class="testi">
    <h3  style="text-align: center" class="mb-4">Testimonials </h3>
      <div class="container mb-5">
    <div class="row">
        <div class="col-lg-3">
            <div class="card">
                <div class="face front-face"> <img src="https://m.economictimes.com/thumb/msid-59094348,width-1200,height-900,resizemode-4,imgsize-200195/iit-jee-advanced-results-announced-chandigarh-student-sarvesh-mehtani-tops.jpg" alt="" class="profile">
                    <div class="pt-3 text-uppercase name"> Vikas jain  </div>
                    <div class="designation"> our  IIT JEE topper  	</div>
                </div>
                <div class="face back-face"> <span class="fas fa-quote-left"></span>
                    <div class="testimonial">I was a student of IIT JEE batch at Spark. There are various facilities provided by Spark for students. If you want to crack IIT JEE then must join, and if you will work hard, you will definitely be success.
                    
          </div> <span class="fas fa-quote-right"></span>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="card">
                <div class="face front-face"> <img src="https://akm-img-a-in.tosshub.com/aajtak/images/story/202109/mridul_agarwal-sixteen_nine.jpg?size=948:533" alt="" class="profile">
                    <div class="pt-3 text-uppercase name"> Jeffery Kennan </div>
                    <div class="designation">Our BISAT topper</div>
                </div>
                <div class="face back-face"> <span class="fas fa-quote-left"></span>
                    <div class="testimonial">I am thankful to the Spark team especially AG sir. AG sir always motivated me and all other students to work hard and stay focused. 
           
                     The faculty at Spark is very hard working and it made my dream come true </div> <span class="fas fa-quote-right"></span>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div class="card">
                <div class="face front-face"> <img src="https://images.news18.com/ibnlive/uploads/2021/08/untitled-design-16.jpg?impolicy=website&width=0&height=0" alt="" class="profile">
                    <div class="pt-3 text-uppercase name"> Issac Maxwell </div>
                    <div class="designation">Our JEE Mains</div>
                </div>
                <div class="face back-face"> <span class="fas fa-quote-left"></span>
                    <div class="testimonial">  I feel really lucky to be a part of such a great institute. Here teachers are very helpful and hard working. I heartily thank the spark team to give me the way to achieving my goal </div> <span class="fas fa-quote-right"></span>
                </div>
            </div>
        </div>
         <div class="col-lg-3">
            <div class="card">
                <div class="face front-face"> <img src="https://www.thenewsminute.com/sites/default/files/Chirag_Falor-compressed.jpg" alt="" class="profile">
                    <div class="pt-3 text-uppercase name"> Akashat Agarwal  </div>
                    <div class="designation">Our KVPY topper</div>
                </div>
                <div class="face back-face"> <span class="fas fa-quote-left"></span>
                    <div class="testimonial"> I strongly recommend spark for preparation of jee which have excellent teaching with highly experienced faculties ... their painstaking efforts and hard works provide us platform to get into IITs</div> <span class="fas fa-quote-right"></span>
                </div>
            </div>
        </div>
    </div>
</div>
    </div>
    
    <!-- testimonals end  -->
    
    <div>
  
  <div class='container-fluid mt-5 mb-5 col-12' style="text-align: center">
    <h4 class="hd">Why Choose Spark Academy ? </h4>
    <p><small class="text-muted">Not just the quality of faculty at Spark is excellent<br />Spark also offers a commitment to personally care for students. </small></p>
    <div class="row" style="justify-content: center" class="mb-5">
        <div class="card col-md-3 col-12 shadow ">
            <div class="card-content">
                <div class="card-body"> <img class="img" src="https://www.matrixedu.in/assets/default/images/latest-tech.jpg" />
                    <div class="shadow"></div>
                    <div class="card-title"> Best Teaching Methodology </div>
                    <div class="card-subtitle">
                        <p> <small class="text-muted">At Spark, we provide a three layered preparatory schedule to our students to ensure that they excel in JEE advanced while doing equally good in JEE main and board examinations.</small> </p>
                    </div>
                </div>
            </div>
        </div>
        <div class="card col-md-3 col-12 ml-2 shadow mx-3">
            <div class="card-content">
                <div class="card-body"> <img class="img" src="https://www.matrixedu.in/assets/default/images/result.jpg" />
                    <div class="card-title"> Best Results in JEE </div>
                    <div class="card-subtitle">
                        <p> <small class="text-muted"> Consistently high quality results in JEE Main, JEE Advanced , KVPY & board examinations since inception; SPARK has also given best ranks from Hyderabad in JEE Main, JEE Advanced, BITSAT, NTSE & KVPY during last year. </small> </p>
                    </div>
                </div>
            </div>
        </div>
        <div class="card col-md-3 col-12 ml-2 shadow">
            <div class="card-content">
                <div class="card-body"> <img class="img rck" src="https://www.matrixedu.in/assets/default/images/best-faculty.jpg" />
                    <div class="card-title"> Best faculty team </div>
                    <div class="card-subtitle">
                        <p> <small class="text-muted">With more than 100 full time faculties, faculty team of SPARK is an unmatched intellectual pool of mentors for competitive exams with excellent qualification from top Indian Universities such as IIT Kharagpur, IIT Kanpur, IIT Delhi, IIM Ahmedabad & IIM Calcutta.</small> </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="mt-3">
        <p class="chk"><small class="text-muted">Still not Convinced ?</small></p>
        <a href="mailto:spark.coaching.academy@gmail.com" target="_blank" class="btn btn-primary">Contact Us  </a>
       
    </div>
</div>
  </div>
    
    <!-- faq start  -->
    
    <div class="container mb-5 mt-5">
    
   
     <h3  style="text-align: center" class="mb-4">Faq's </h3>
    <div class="accordion" id="accordionExample">
  <div class="accordion-item">
    <h2 class="accordion-header" id="headingOne">
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
        How to enroll in a couse ? 
      </button>
    </h2>
    <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
      <div class="accordion-body">
      
          first register as a student , then go to a specific batch of a course you want to enroll into press the "enroll" button there 
          
      </div>
    </div>
  </div>
  <div class="accordion-item">
    <h2 class="accordion-header" id="headingTwo">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
        What are class days and timings ? 
      </button>
    </h2>
    <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
      <div class="accordion-body">
           class days are from monday to Friday , while timings different from batch to batch , which you will know after you enroll into a batch 
       </div>
    </div>
  </div>
  <div class="accordion-item">
    <h2 class="accordion-header" id="headingThree">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
        how to get my other doubts cleared ? 
      </button>
    </h2>
    <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
      <div class="accordion-body">
          you can contact us either by mail or number provided at the bottom of this page or you directly reach to academy present at the address given below and get you doubts resolved
        </div>
    </div>
  </div>
</div>
    
    </div>

<%@ include file="footer.jsp" %>
	