<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 
<html>
<script type="text/javascript">

//input the user data page


//validate fields for acuity max 16 min 0
function minmax(value) 
{
    
	if(parseInt(value) < 0 || isNaN(parseInt(value))) 
        return ""; 
    else if(parseInt(value) > 16) 
        return 16; 
    else return value;
}
//validate fields for not empty
window.onload = function () {
	  var form = document.getElementById('patientForm');
	  form.button.onclick = function (){
	    for(var i=0; i < form.elements.length; i++){
	      if(form.elements[i].value === '' && form.elements[i].hasAttribute('required')){
	        alert('There are some required fields!');
	        return false;
	      }
	    }
	  }; 
	};
</script>

<head>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-111678682-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-111678682-1');
  
  
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hapi Fhir patient</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/drop.css">
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Open+Sans|Space+Mono">
<link href="https://fonts.googleapis.com/css?family=Open+Sans|Space+Mono" rel="stylesheet">
</head>
<body background="${pageContext.request.contextPath}/images/rockBackground.gif">

<script language="javascript" type="text/javascript"></script>
<table border="0" align="center" width="1000" >



<tr>
<td><img src="${pageContext.request.contextPath}/images/banner1.jpg"></td>
</tr>

    <tr>
      <td colspan="2" bgcolor="#a7c7fc">
<div align="center" class="menu-wrap">

    <nav class="menu">
        <ul class="clearfix">
            <li><a href="http://114.142.160.89:8080/ic/index.jsp">Home</a></li>
            <li>
                <a href="#">Demonstrations <span class="arrow">&#9660;</span></a>
 
                <ul class="sub-menu">
                     <li><a href="http://114.142.160.89:8080/ic/out.jsp">ICD-10AM search</a></li>
                    <li><a href="http://114.142.160.89:8080/ic/reasoning.jsp">AI Reasoning</a></li>
                    <li><a href="http://114.142.160.89:8080/ic/IcnpInitServlet">ICNP text suggester</a></li>  
                    <li><a href="http://114.142.160.89:8080/ic/jsonFhirSelector.jsp">JSON and FHIR</a></li>                  
                </ul>
            </li>
            <li><a href="http://114.142.160.89:8080/ic/about.jsp">About</a></li>
            <li><a href="http://114.142.160.89:8080/ic/contact.jsp">Contact</a></li>
            <li> <a href="http://114.142.160.89:8080/ic/publications.jsp">Publications</a></li>
            <li><a href="http://114.142.160.89:8080/ic/whatis.jsp">What's an ontology?</a></li>
        </ul>
    </nav>
    
</div>
</td>
</tr>
</table>




<p>

<%@ page import="java.util.ArrayList" %>


  
<form name="queryForm" id="patientForm" method="post" action="UpdatePatientServlet">


<b><font color = "red"> ${message}</font></b><br><p>
  <p>
<div align="left">
  <center>
  <table border="0" width="1000" height="157" >
  
    <tr>
      <td> 
      <!-- <td><c:out value= "${html}"/> <br> -->
</td>
    </tr>
  </table>
  </center>
</div>
<div align="left">
  <center>
  <table border="0" width="1000" bgcolor="lightblue">
    <tr>
      <td width="514" valign="top">


 
 
 <p>
 <table border="0">
 <tr>
 <th>Prefix </th><th>Given name</th><th>Family name </th><th>Preferred name </th> 
 </tr>
 <tr>
 <td><input type="text" name="prefix" size = "3" value="${prefix}" required></td><td><input type="text" name="nameGiven" value="${nameGiven}" required></td>
 <td><input type="text" name="familyName" value="${familyName}" required></td><td><input type="text" name="nameUse" value="${nameUse}"></td>
 </tr>
<tr>
<th> Patient active</th><th>Resource type</th><th>Id</th><th>Version</th>
</tr>
<tr>
<td><input type="text" name="active" size="5" value="${active}" required></td><td><input type="text" name="resourceType" readonly size="10" value="${resourceType}">
</td><td><input type="text" name="id" size="15" readonly value="${id}"></td><td><input type="text" name="version" readonly size="3" value="${version}"></td>
</tr>
<tr>
<th>Last update </th><th>Language</th><th>Gender</th><th>Birth date</th>
</tr>
<tr>
<td><input type="text" name="lastUpdate" size="20" readonly value="${lastUpdate}"></td><td><input type="text" name="language" size="10" value="${language}" required></td>
<td><input type="text" name="gender" size="10" value="${gender}" required></td><td><input type="text" name="birthDate" size="10" value="${birthDate}" required></td>
</tr>
<tr>
<th>Marital status</th>
</tr>
<tr>
<td><input type="text" name="maritalStatus" size="10" value="${maritalStatus}" required></td>

</tr>
</table>
 
 <br>
 <c:set var="idCount" value="0" scope="page" />
  <c:set var="telecomCount" value="0" scope="page" />
 
 
   <c:set var="contactsSystemCounter" value="0" scope="page" />
  <c:set var="practitionerCount" value="0" scope="page" />
  
  Patients' Ids<br>
  
   
<c:forEach var="i" begin = "0" step="1" end="${fn:length(idValue)-1}">
       
      <input type="text" name="idDisplay${i}" size="30" value="${idDisplay[i]}" required>  
        <input type="text" name="idValue${i}" size="10" value="${idValue[i]}" required> <br>
      <c:set var="idCount" value="${idCount + 1}" scope="page"/>
    </c:forEach>
    <input type="hidden" name="idCount" value="${idCount}">
    
 <br>
 

<p>
  
   Patients' home phone number<br> 
  
    <input type="text" name="homePhone" size="10" value="${telecomValue[0]}"><br>     
    <p>  
       Patients' work phone number<br> 
    <input type="text" name="workPhone" size="10" value="${telecomValue[1]}"><br>
      
     <p>
      Patients' email<br> 
     
    <input type="text" name="email" size="30" value="${telecomValue[2]}"><br>
    <p>
     
  Patients' mobile number<br> 
     
    <input type="text" name="mobilePhone" size="10" value="${telecomValue[3]}" ><br>
     
 
    
 
  <p>
  
  Patients' address<br>
   
  
 <input type="text" name="street" size="10" value="${street}" required>
    <input type="text" name="city" size="10" value="${city}" required> 
     <input type="text" name="state" size="10" value="${state}" required>
     <input type="text" name="postcode" size="10" value="${postcode}" required>
    
     <br>
 
  <p>
  
  Patients' contacts<br>
 
   Relationship&nbsp;Name <br>
   
  
 <input type="text" name="contactRelationshipCodePrimary" size="10" value="${contactRelationshipCodePrimary}" >
 <input type="text" name="contactRelationshipNamePrimary" size="10" value="${contactRelationshipNamePrimary}" ><br>
   
    <c:forEach var="i" begin = "0" step="1" end="${fn:length(contactTelecomSystemPrimary)-1}">
    <input type="text" name="contactTelecomSystemPrimary${i}" size="10" readonly value="${contactTelecomSystemPrimary[i]}" >
 <input type="text" name="contactTelecomUsePrimary${i}" size="10" readonly value="${contactTelecomUsePrimary[i]}" >
 <input type="text" name="contactTelecomValuePrimary${i}" size="10" value="${contactTelecomValuePrimary[i]}" ><br>
   
 </c:forEach>
     <br>    
     
 <p>
 <input type="text" name="contactRelationshipCodeSecondary" size="10" value="${contactRelationshipCodeSecondary}">
 <input type="text" name="contactRelationshipNameSecondary" size="10" value="${contactRelationshipNameSecondary}"><br>
 
 <c:forEach var="i" begin = "0" step="1" end="${fn:length(contactTelecomSystemSecondary)-1}">
    <input type="text" name="contactTelecomSystemSecondary${i}" size="10" readonly value="${contactTelecomSystemSecondary[i]}">
 <input type="text" name="contactTelecomUseSecondary${i}" size="10" readonly value="${contactTelecomUseSecondary[i]}">
 <input type="text" name="contactTelecomValueSecondary${i}" size="10" value="${contactTelecomValueSecondary[i]}"><br>
   
 </c:forEach>
     <br>    
     
 <p>
 

  <c:forEach var="i" begin = "0" step="1" end="${fn:length(practitioner)-1}">
    <input type="text" name="practitioner${i}" size="30" readonly value="${practitioner[i]}"> <br>    
        
       
      <c:set var="practitionerCount" value="${practitionerCount + 1}" scope="page"/>
    </c:forEach>
    <input type="hidden" name="practitionerCount" value="${practitionerCount}">
    
    
  
 <br>
 <input type = "radio" name="crud" value="update">Update this patient<br>
  <input type = "radio" name="crud" value="delete">Delete this patient<br>
   <input type = "radio" name="crud" value="goHome">Back to start<br>
   <p>
 <input type="submit" value="Go" name="aScores"><br> 
 
 
</tr>

<p>
 
  
  

     

      </table>
      
   
  </center>
</div>


</form>




<p align="center"><a href="http://114.142.160.89:8080/hapi-fhir-jpaserver/">HAPI-FHIR server</a><br><p>



</body>

