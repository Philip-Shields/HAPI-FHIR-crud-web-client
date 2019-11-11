

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

/**
 * Servlet implementation class FhirCrudServlet
 */
@WebServlet("/FhirCrudServlet")
public class FhirCrudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FhirCrudServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String serverURL=request.getParameter("server");
		//try
		//{
		   // String filename= "C:/Users/Phil standard/My Documents/ServerURL.txt";
			//String filename="/home/phil/fhir/ServerURL.txt";
		   // FileWriter fw = new FileWriter(filename,false); //the false doesnt append the new data
		   // fw.write(serverURL);
		   // fw.close();
		//}
		//catch(IOException ioe)
		//{
		    //System.err.println("IOException: " + ioe.getMessage());
		//}
		
		ServletContext context = getServletContext();
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		
		
		//get fields from .jsp
				String crudResponse=request.getParameter("crud");
				
				
						
				if(crudResponse.equals("addAPatient")) {
					//HapiFhirPatient.createPatient(true);
					response.sendRedirect("patient.jsp");
					
				}
				
				if(crudResponse.equals("addAPractitioner")) {
					//HapiFhirPatient.createPatient(true);
					response.sendRedirect("practitioner.jsp");
					
				}
				
				if(crudResponse.equals("listPatient")) {
					response.sendRedirect("listPatients.jsp");
				}
				
				if(crudResponse.equals("listPractitioners")) {					
					
					ArrayList<String> listPracId = new ArrayList<String>();
					ArrayList<String> listPracFamilyName = new ArrayList<String>();
					ArrayList<String> listPracGiven = new ArrayList<String>();	
					 MultiValuedMap<String, String> qualification = new HashSetValuedHashMap<String,String>();
					 String message = null;
					 SearchPatient.listPractitioners();
					listPracId.addAll(SearchPatient.practitionerListId);
					listPracFamilyName.addAll(SearchPatient.practitionerListFamilyName);
					listPracGiven.addAll(SearchPatient.practitionerListGiven);
					qualification=(SearchPatient.practitionerListDiscipline);
					
					
					
					
					
					 try {
						 
						 SearchPatient.listPractitioners();
						listPracId=(SearchPatient.practitionerListId);
						listPracFamilyName=(SearchPatient.practitionerListFamilyName);
						listPracGiven=(SearchPatient.practitionerListGiven);
						qualification=(SearchPatient.practitionerListDiscipline);
						 message=SearchPatient.message;
						
						 } catch (Exception e) {
					          System.out.println(e.getMessage());    
					        }
						
						out.println( 
								"  \r\n" + 
								"  \r\n" + 
								"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n" + 
								"\r\n" + 
								" \r\n" + 
								"<html>\r\n" + 
								"<script type=\"text/javascript\">\r\n" + 
								"\r\n" + 
								"//input the user data page\r\n" + 
								"\r\n" + 
								"\r\n" + 
								"//validate fields for acuity max 16 min 0\r\n" + 
								"function minmax(value) \r\n" + 
								"{\r\n" + 
								"    \r\n" + 
								"	if(parseInt(value) < 0 || isNaN(parseInt(value))) \r\n" + 
								"        return \"\"; \r\n" + 
								"    else if(parseInt(value) > 16) \r\n" + 
								"        return 16; \r\n" + 
								"    else return value;\r\n" + 
								"}\r\n" + 
								"//validate fields for not empty\r\n" + 
								"window.onload = function () {\r\n" + 
								"	  var form = document.getElementById('patientForm');\r\n" + 
								"	  form.button.onclick = function (){\r\n" + 
								"	    for(var i=0; i < form.elements.length; i++){\r\n" + 
								"	      if(form.elements[i].value === '' && form.elements[i].hasAttribute('required')){\r\n" + 
								"	        alert('There are some required fields!');\r\n" + 
								"	        return false;\r\n" + 
								"	      }\r\n" + 
								"	    }\r\n" + 
								"	  }; \r\n" + 
								"	};\r\n" + 
								"</script>\r\n" + 
								"\r\n" + 
								"<head>\r\n" + 
								"<!-- Global site tag (gtag.js) - Google Analytics -->\r\n" + 
								"<script async src=\"https://www.googletagmanager.com/gtag/js?id=UA-111678682-1\"></script>\r\n" + 
								"<script>\r\n" + 
								"  window.dataLayer = window.dataLayer || [];\r\n" + 
								"  function gtag(){dataLayer.push(arguments);}\r\n" + 
								"  gtag('js', new Date());\r\n" + 
								"\r\n" + 
								"  gtag('config', 'UA-111678682-1');\r\n" + 
								"  \r\n" + 
								"  \r\n" + 
								"</script>\r\n" + 
								"\r\n" + 
								"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n" + 
								"<title>Hapi Fhir client</title>\r\n" + 
								"<link type=\"text/css\" rel=\"stylesheet\" href=\""+request.getContextPath()+ "/style/drop.css\">\r\n" + 
								"<link rel=\"stylesheet\" type=\"text/css\" href=\"https://fonts.googleapis.com/css?family=Open+Sans|Space+Mono\">\r\n" + 
								"<link href=\"https://fonts.googleapis.com/css?family=Open+Sans|Space+Mono\" rel=\"stylesheet\">\r\n" + 
								"</head>\r\n" + 
								"<body background=\""+request.getContextPath()+ "/images/rockBackground.gif\">\r\n" + 
										"<script language=\"javascript\" type=\"text/javascript\"></script>\r\n" + 
										"\r\n" + 
										"<table border=\"0\" align=\"center\" width=\"1000\">\r\n" + 
										"\r\n" + 
										"\r\n" + 
										"\r\n" + 
										"<tr>\r\n" + 
										"<td><img src=\""+request.getContextPath()+ "/images/banner1.jpg\"></td>\r\n" + 
										"</tr>\r\n" + 
										"\r\n" + 
										"    <tr>\r\n" + 
										"      <td colspan=\"2\" bgcolor=\"#a7c7fc\">\r\n" + 
										"<div align=\"center\" class=\"menu-wrap\">\r\n" + 
										"\r\n" + 
										"    <nav class=\"menu\">\r\n" + 
										"        <ul class=\"clearfix\">\r\n" + 
										"            <li><a href=\"http://114.142.160.89:8080/ic/index.jsp\">Home</a></li>\n" + 
										"            <li>\n" + 
										"                <a href=\"#\">Demonstrations <span class=\"arrow\">&#9660;</span></a>\n" + 
										" \n" + 
										"                <ul class=\"sub-menu\">\n" + 
										"                     <li><a href=\"http://114.142.160.89:8080/ic/out.jsp\">ICD-10AM search</a></li>\n" + 
										"                    <li><a href=\"http://114.142.160.89:8080/ic/reasoning.jsp\">AI Reasoning</a></li>\n" + 
										"                    <li><a href=\"http://114.142.160.89:8080/ic/IcnpInitServlet\">ICNP text suggester</a></li>  \n" + 
										"                    <li><a href=\"http://114.142.160.89:8080/ic/jsonindex.jsp\">JSON and FHIR</a></li>                  \n" + 
										"                </ul>\n" + 
										"            </li>\n" + 
										"            <li><a href=\"http://114.142.160.89:8080/ic/about.jsp\">About</a></li>\n" + 
										"            <li><a href=\"http://114.142.160.89:8080/ic/contact.jsp\">Contact</a></li>\n" + 
										"            <li> <a href=\"http://114.142.160.89:8080/ic/publications.jsp\">Publications</a></li>\n" + 
										"            <li><a href=\"http://114.142.160.89:8080/ic/whatis.jsp\">What's an ontology?</a></li>\n"+
										"        </ul>\r\n" + 
										"    </nav>\r\n" + 
										"    \r\n" + 
								"</div>\r\n" + 
								"</td>\r\n" + 
								"</tr>\r\n" + 
								"</table>\r\n" + 
								"\r\n" + 
								"\r\n" + 
								"<form name=\"queryForm\" id=\"patientForm\" method=\"post\" action=\"FhirCrudServlet\">\r\n" + 
								"\r\n" + 
								"<p>\r\n" + 
								"\r\n" + 
								"<c:set var=\"qualificationCount\" value=\"0\" scope=\"page\" />\r\n" + 
								"\r\n" + 
								"<div align=\"left\">\r\n" + 
								"  <center>\r\n" + 
								"  <table border=\"1\" width=\"1000\" bgcolor=\"lightblue\">\r\n" + 
								"    <tr>\r\n" + 
								"      <td width=\"500\" valign=\"top\">\r\n" + 
								"      <b><font color=\"blue\">Do things with patients and practitioners</font></b><p><br>\r\n" + 
								"\r\n" + 
								"<b><font color = \"red\">");
								
								out.println(message);
								
								out.println("</font></b><br><p>\r\n" + 
								"  <p>\r\n" + 
								"\r\n" + 
								
								"<p>"+
								"<div>\r\n" + 
								"<b><font color=\"blue\">Create</font></b><br>\r\n" + 
								"\r\n" + 
								"<input type = \"radio\" name=\"crud\" value=\"addAPatient\">Add a patient<br>\r\n" + 
								"<p>\r\n" + 
								"<p>\r\n" + 
								"<input type = \"radio\" name=\"crud\" value=\"addAPractitioner\">Add a practitioner<br>\r\n" + 
								"<p>\r\n" + 
								"<p>\r\n" + 
								" \r\n" + 
								" <b><font color=\"blue\">Search</font></b><br>\r\n" + 
								" <input type = \"radio\" name=\"crud\" value=\"searchAPatient\">Display a patient using id number<br>\r\n" + 
								"<p>\r\n" + 
								"<input type = \"radio\" name=\"crud\" value=\"searchAPractitioner\">Display a practitioner using id number<br>\r\n" + 
								"<p>\r\n" + 
								" \r\n" + 
								" <input type = \"radio\" name=\"crud\" value=\"listPatient\">List patients with the surname<br>\r\n" + 
								"\r\n" + 
								"<p>\r\n" + 
								"<input type = \"radio\" name=\"crud\" value=\"listPractitioners\">List practitioners<br>\r\n" + 
								"  <p>\r\n" + 
								"<p>\r\n" + 
								"  \r\n" + 
								"\r\n" + 
								" \r\n" + 
								"  \r\n" + 
								"</div>\r\n" + 
								"</td>\r\n" + 
								"  <td width=\"500\" valign=\"top\">\r\n" + 
								"  \r\n" + 
								"  <textarea  readonly name=\"listResults\" id= \"listTxtArea\" style=\"width:500px; height:250px;\" rows=\"2\" cols=\"10\">\r\n" + 
								
								"  \r\n" + 
								"  </textarea>\r\n" + 
								"   <textarea  readonly name=\"listPrac\" id= \"listPrac\" style=\"width:500px; height:250px;\" rows=\"2\" cols=\"10\">\r\n" + 
								"    \r\n" );
								
								
								for(int i=0; i<listPracId.size();i++) { 
								out.println(listPracId.get(i)); 
								out.println(listPracGiven.get(i));
								out.println(listPracFamilyName.get(i)); 
								
								for (Entry<String, String> entry : qualification.entries()) {
									
									if(listPracId.get(i)==entry.getKey()) {
									 
									    out.println("Qualification = " + entry.getValue());
										
									    
										}
									
								}
								
								out.println("--------------");
								out.println("");
								out.println("");
								}
								out.println(" \r\n" + 
								"  \r\n"+
								
								"  </textarea>\r\n" + 
								"  </td>  \r\n" + 
								"  \r\n" + 
								"  \r\n" + 
								" \r\n" + 
								"  \r\n" + 
								"    \r\n" + 
								"</tr>\r\n" + 
								"\r\n" + 
								"<tr>\r\n" + 
								"<td width= \"1000\">\r\n" + 
								" <input type=\"submit\" value=\"Submit\" name=\"aScores\"><input type=\"reset\" value=\"Reset\" name=\"B2\"> \r\n" + 
								"</td>\r\n" + 
								"</tr>\r\n" + 
								"\r\n" + 
								"      </table>\r\n" + 
								"      \r\n" + 
								"     \r\n" + 
								"  </center>\r\n" + 
								"</div>\r\n" + 
								"\r\n" + 
								"</form>\r\n" + 
								"\r\n" + 
								"\r\n" + 
								"\r\n" + 
								"\r\n" + 
								"\r\n" + 
								"<p align=\"center\"><a href=\""+request.getContextPath()+"/http://114.142.160.89:8080/hapi-fhir-jpaserver/\">HAPI-FHIR server</a>\r\n" + 
								"application.</p>\r\n" + 
								"\r\n" + 
								"\r\n" + 
								"\r\n" + 
								"</body>\r\n" + 
								"\r\n"  
								);
				}
				
				
				
				if(crudResponse.equals("searchAPatient")) {
					//SearchPatient.listPractitioners(); //load up practitioner array for later use
					response.sendRedirect("findPatient.jsp");
					//get fields from index.jsp
					
				}
				
				if(crudResponse.equals("searchAPractitioner")) {
					
					response.sendRedirect("findPractitioner.jsp");
					
					
				}
				}
				
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

