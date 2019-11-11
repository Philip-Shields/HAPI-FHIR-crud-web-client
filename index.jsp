<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib prefix="h" uri="http://java.sun.com/jsp/jstl/core" %>
  
  
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
<title>Hapi Fhir client</title>
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


<form name="queryForm" id="patientForm" method="post" action="FhirCrudServlet">

<p>

<c:set var="qualificationCount" value="0" scope="page" />

<div align="left">
  <center>
  <table border="1" width="1000" bgcolor="lightblue">
    <tr>
      <td width="500" valign="top">
      <b><font color="blue">Do things with patients and practitioners</font></b><p><br>

<b><font color = "red"> ${message}</font></b><br><p>
  <p>
 
<p>
<div>
<b><font color="blue">Create</font></b><br>

<input type = "radio" name="crud" value="addAPatient">Add a patient<br>
<p>
<p>
<input type = "radio" name="crud" value="addAPractitioner">Add a practitioner<br>
<p>
<p>
 
 <b><font color="blue">Search</font></b><br>
 <input type = "radio" name="crud" value="searchAPatient">Display a patient using id number<br>
<p>
<input type = "radio" name="crud" value="searchAPractitioner">Display a practitioner using id number<br>
<p>
 
 <input type = "radio" name="crud" value="listPatient">List patients with the surname<br>

<p>
<input type = "radio" name="crud" value="listPractitioners">List practitioners<br>
  <p>
<p>
  

 
  
</div>
</td>
  <td width="500" valign="top">
  
  <textarea  readonly name="listResults" id= "listTxtArea" style="width:500px; height:250px;" rows="2" cols="10">
  <c:forEach var="i" begin = "0" step="1" end="${fn:length(listId)}">
   <c:out value= "${listId[i]}"/>
   <c:out value= "${listFamilyName[i]}"/>   
  <c:out value= "${listGiven[i]}"/> 
  <c:out value= "${listStreet[i]}"/> 
  <c:out value= "${listTown[i]}"/> 
  </c:forEach> 
  
  </textarea>
   <textarea  readonly name="listPrac" id= "listPrac" style="width:500px; height:250px;" rows="2" cols="10">
    
  <c:forEach var="i" begin = "0" step="1" end="${fn:length(listPracId)}">
  
   <c:out value= "${listPracId[i]}"/>      
  <c:out value= "${listPracGiven[i]}"/>  
  <c:out value= "${listPracFamilyName[i]}"/>
   <c:out value= "${qualification[i]}"/>
  
   </c:forEach>
 
  
  </textarea>
  </td>  
  
  
 
  
    
</tr>

<tr>
<td width= "1000">
 <input type="submit" value="Submit" name="aScores"><input type="reset" value="Reset" name="B2"> 
</td>
</tr>

      </table>
      
     
  </center>
</div>

</form>

<p align="center"><a href="http://114.142.160.89:8080/hapi-fhir-jpaserver/">HAPI-FHIR server</a><br><p>



</body>

