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




  
<form name="queryForm" id="patientForm" method="post" action="FindPatientServlet">

  <p>
<div align="left">
  <center>
  <table border="0" width="1000" height="157" >
  
    <tr>
      <td> 
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
<b><font color="blue">Input</font></b><p>

<b> Enter patient's id number to find </b>

  <input type="text" name="patientId" value= "" size="15"> <br>
  
  
  <p>
  
  <input type="submit" value="Submit" name="aScores"><input type="reset" value="Reset" name="B2">
</div>
</td>
      

      </table>
      
     
  </center>
</div>


</form>




<p align="center"><a href="http://114.142.160.89:8080/hapi-fhir-jpaserver/">HAPI-FHIR server</a><br><p>



</body>
