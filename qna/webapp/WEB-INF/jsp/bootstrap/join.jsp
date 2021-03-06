<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<!--  Head -->
<%@ include file="../bootstrap/commons/_head.jspf" %>

<body id="page-top" class="index">
	
    <!--  Header -->
	<%@ include file="../bootstrap/commons/_header.jspf" %>

	<!-- Contact Section -->
    <section id="memberjoin">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2>Join</h2>
                    <hr class="star-primary">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2">
                	<c:choose>
                    <c:when test = "${empty user.userId }">
               	    	<c:set var ="method" value="post" />
               	    	<c:set var ="attr" value="" />
                    </c:when>
                    <c:otherwise>
                    	<c:set var = "method" value="put" />
                    	<c:set var ="attr" value="readonly='readonly' style='background-color:#fff;'" />
                    </c:otherwise>
                    </c:choose>
                    
                    <form name="sentMessage" id="contactForm" novalidate method="post">
                    	<input type="hidden" name = "_method" value="${method }">
                    	<div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <label>ID</label>
                                <input type="text" class="form-control" placeholder="ID" id="id" name="userId" value="${user.userId }"   ${attr}  required data-validation-required-message="Please enter your ID.">
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
                        
                        <div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <label>PASSWORD</label>
                                <input type="password" class="form-control" placeholder="PASSWORD" name="password"  id="password" required data-validation-required-message="Please enter your password.">
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
                        
                        <div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <label>Name</label>
                                <input type="text" class="form-control" placeholder="Name" id="name" name="name" value="${user.name }" required data-validation-required-message="Please enter your name.">
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
                        <div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <label>Email Address</label>
                                <input type="email" class="form-control" placeholder="Email Address" name="email" value="${user.email }" id="email" required data-validation-required-message="Please enter your email address.">
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
                        <div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <label>Phone Number</label>
                                <input type="tel" class="form-control" placeholder="Phone Number" id="phone" name="phone" value="${user.phone }" required data-validation-required-message="Please enter your phone number.">
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
                        <div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <label>Message</label>
                                <textarea rows="5" class="form-control" placeholder="Message" id="message" name="message" required data-validation-required-message="Please enter a message.">${user.message }</textarea>
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
                        <br>
                        <div id="success"></div>
                        <div class="row">
                            <div class="form-group col-xs-12">
                                <button type="submit" class="btn btn-success btn-lg">Send</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    
    
    
    <!-- jQuery -->
    <script src="vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>

    <!-- Contact Form JavaScript -->
    <script src="js/jqBootstrapValidation.js"></script>
    <script src="js/contact_me.js"></script>

    <!-- Theme JavaScript -->
    <script src="js/freelancer.min.js"></script>
    
    <script type="text/javascript">
    	$(window).load(function(){
    		$('#mainNav').find('ul').remove();
    		$('#mainNav').find('button').remove();
    	});
    </script>
</body>
</html>