<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
    pageEncoding="ISO-8859-1"%>
    
<script type="text/javascript" src="${commonResourcePath}/js/newslettersubscriptionformparagraphcomponent.js"></script>
    
<form:form id="newsletterSubscriptionFormComponent" method="POST" action="/newsletter/newsletter-register">

	<input type="text" name="firstName" placeholder="Your first name"></input><br/>
    
	<input type="text" name="lastName" placeholder="Your last name"></input><br/> 
    
	<input type="email" name="email" placeholder="Your e-mail"></input><br/> 

	<select name="titleCode">
        <c:forEach items="${titles}" var="title">
   		 	<option value="${title.code}">${title.name}</option>
  		</c:forEach>
    </select>

	<select name="genderCode">
        <c:forEach items="${genders}" var="gender">
   		 	<option value="${gender.code}">${gender.name}</option>
  		</c:forEach>
    </select>

    
	<button>Subscribe</button>

</form:form>