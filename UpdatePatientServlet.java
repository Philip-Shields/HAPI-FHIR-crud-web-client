

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hl7.fhir.dstu3.model.Enumerations.AdministrativeGender;

/**
 * Servlet implementation class UpdatePatientServlet
 */
@WebServlet("/UpdatePatientServlet")
public class UpdatePatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<String> idDisplay = new ArrayList<String>();	
	 ArrayList<String> idValue = new ArrayList<String>();
	 
	
	 ArrayList<String> telecomValue = new ArrayList<String>();
	
	 
	 String contactRelationshipCodePrimary;
	String contactRelationshipSystemPrimary;
		String contactRelationshipNamePrimary;
		 String contactRelationshipCodeSecondary;
			String contactRelationshipSystemSecondary;
				String contactRelationshipNameSecondary;
		ArrayList<String> contactTelecomSystemPrimary = new ArrayList<String>();
		ArrayList<String> contactTelecomValuePrimary = new ArrayList<String>();
		ArrayList<String> contactTelecomUsePrimary = new ArrayList<String>();
		ArrayList<String> contactTelecomSystemSecondary = new ArrayList<String>();
		ArrayList<String> contactTelecomValueSecondary = new ArrayList<String>();
		ArrayList<String> contactTelecomUseSecondary = new ArrayList<String>();
		
		ArrayList<String> practitioner = new ArrayList<String>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePatientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String crudResponse=request.getParameter("crud");
		
		
		if(crudResponse.equals("delete")) {
			String patientId=request.getParameter("id");
			System.out.println("patient's id= "+patientId);
			response.setContentType("text/html");
			request.setAttribute("patientId", patientId);//seems like all the name have to be the same for this to work
			
			//response.sendRedirect("deletePatients.jsp");
				try {
					 
				request.getRequestDispatcher("/deletePatients.jsp").forward(request, response);
		 } catch (ServletException e) {
			}
		}
		
		if(crudResponse.equals("goHome")) {
			response.sendRedirect("index.jsp");
		}
		
		if(crudResponse.equals("update")) {
		idDisplay.clear();	
		 idValue.clear();
		 
		
		 telecomValue.clear();
		
		 contactRelationshipCodePrimary="";
		contactRelationshipSystemPrimary="";
		contactRelationshipNamePrimary="";
		 contactRelationshipCodeSecondary="";
			contactRelationshipSystemSecondary="";			
			contactRelationshipNameSecondary="";
			
		contactTelecomSystemPrimary.clear();
		contactTelecomValuePrimary.clear();
		contactTelecomUsePrimary.clear();
		
		contactTelecomSystemSecondary.clear();
		contactTelecomValueSecondary.clear();
		contactTelecomUseSecondary.clear();
	
	practitioner.clear();
	
	
	//get variables from searchPatient.java
	
		String active=request.getParameter("active");//
		String resourceType=request.getParameter("resourceType");//
	String patientId=request.getParameter("id");//
	String version=request.getParameter("version");//
	String lastUpdate=request.getParameter("lastUpdate");//
	String language=request.getParameter("language");//
	String gender=request.getParameter("gender");//
	String birthDate=request.getParameter("birthDate");//
	String maritalStatus=request.getParameter("maritalStatus");//
	String maritalSystem=request.getParameter("maritalSystem");
	String maritalCode=request.getParameter("maritalCode");
	
	String prefix=request.getParameter("prefix");//
	String familyName=request.getParameter("familyName");//
	 String nameUsual=request.getParameter("nameUse");// maybe an array
		String nameGiven=request.getParameter("nameGiven");// maybe an array
		
		 String street = request.getParameter("street");//
		 String city = request.getParameter("city");//
		 String state = request.getParameter("state");//
		 String postcode = request.getParameter("postcode");//
		 
		 
		 
		 int idCount = Integer.parseInt(request.getParameter("idCount"));
		
		 for(int i=0;i<idCount;i++) {
			 idValue.add(request.getParameter("idValue"+i)); 
			 idDisplay.add(request.getParameter("idDisplay"+i)); 
			
		 }
	
	 
	 System.out.println("idValue array = "+idValue);
	 System.out.println("idDisplay array = "+idDisplay);
	 
	 
	
	 
	 if(request.getParameter("homePhone")!="") {
		 telecomValue.add(0,request.getParameter("homePhone"));
	 }else telecomValue.add(0,"NULL");
		
		 if(request.getParameter("workPhone")!="") {
		 telecomValue.add(1,request.getParameter("workPhone")); 
		 }else telecomValue.add(1,"NULL");
		 
		 if(request.getParameter("mobilePhone")!="") {
		 telecomValue.add(2,request.getParameter("mobilePhone")); 
		 }else telecomValue.add(2,"NULL");
		 
		 if(request.getParameter("email")!="") {
		 telecomValue.add(3,request.getParameter("email")); 
		 }else telecomValue.add(3,"NULL");
		
	 
	 if(request.getParameter("contactRelationshipCodePrimary")!="") {
		 contactRelationshipCodePrimary=request.getParameter("contactRelationshipCodePrimary"); //name and code are fixed number
	 }else contactRelationshipCodePrimary="NULL";
		 
		 //contactRelationshipSystem.add(request.getParameter("contactRelationshipSystem"+i)); 
		 if(request.getParameter("contactRelationshipNamePrimary")!="") {
		 contactRelationshipNamePrimary=request.getParameter("contactRelationshipNamePrimary"); 
		 }else contactRelationshipNamePrimary="NULL";
		 
		 if(request.getParameter("contactRelationshipCodeSecondary")!="") {
		 contactRelationshipCodeSecondary=request.getParameter("contactRelationshipCodeSecondary"); //name and code are fixed number
		 }else contactRelationshipCodeSecondary="NULL";
		 
		 //contactRelationshipSystem.add(request.getParameter("contactRelationshipSystem"+i)); 
		 if(request.getParameter("contactRelationshipNameSecondary")!="") {
		 contactRelationshipNameSecondary=request.getParameter("contactRelationshipNameSecondary"); 
		 }else contactRelationshipNameSecondary="NULL";
		 
		 if(request.getParameter("contactTelecomSystemPrimary0")!="") {
		 contactTelecomSystemPrimary.add(0,request.getParameter("contactTelecomSystemPrimary0"));
		 }else contactTelecomSystemPrimary.add(0,"NULL");
		 
		 if(request.getParameter("contactTelecomValuePrimary0")!="") {
		 contactTelecomValuePrimary.add(0,request.getParameter("contactTelecomValuePrimary0"));
		 }else contactTelecomValuePrimary.add(0,"NULL");
		 
		 if(request.getParameter("contactTelecomUsePrimary0")!="") {
		 contactTelecomUsePrimary.add(0,request.getParameter("contactTelecomUsePrimary0"));
		 }else contactTelecomUsePrimary.add(0,"NULL");
		 
		 if(request.getParameter("contactTelecomSystemPrimary1")!="") {		
		 contactTelecomSystemPrimary.add(1,request.getParameter("contactTelecomSystemPrimary1")); 
		 }else contactTelecomSystemPrimary.add(1,"NULL");
		 
		 if(request.getParameter("contactTelecomValuePrimary1")!="") {
		 contactTelecomValuePrimary.add(1,request.getParameter("contactTelecomValuePrimary1"));
		 }else contactTelecomValuePrimary.add(1,"NULL");
		 
		 if(request.getParameter("contactTelecomUsePrimary1")!="") {
		 contactTelecomUsePrimary.add(1,request.getParameter("contactTelecomUsePrimary1"));
		 }else contactTelecomUsePrimary.add(1,"NULL");
		 
		 if(request.getParameter("contactTelecomSystemPrimary2")!="") {
		 contactTelecomSystemPrimary.add(2,request.getParameter("contactTelecomSystemPrimary2")); 
		 }else contactTelecomSystemPrimary.add(2,"NULL");
		 
		 if(request.getParameter("contactTelecomValuePrimary2")!="") {
		 contactTelecomValuePrimary.add(2,request.getParameter("contactTelecomValuePrimary2"));
		 }else contactTelecomValuePrimary.add(2,"NULL");
		 
		 if(request.getParameter("contactTelecomUsePrimary2")!="") {
		 contactTelecomUsePrimary.add(2,request.getParameter("contactTelecomUsePrimary2"));
		 }else contactTelecomUsePrimary.add(2,"NULL");
		 
		 if(request.getParameter("contactTelecomSystemSecondary0")!="") {
		 contactTelecomSystemSecondary.add(0,request.getParameter("contactTelecomSystemSecondary0")); 
		 }else contactTelecomSystemSecondary.add(0,"NULL");
		 
		 if(request.getParameter("contactTelecomValueSecondary0")!="") {
		 contactTelecomValueSecondary.add(0,request.getParameter("contactTelecomValueSecondary0"));
		 }else contactTelecomValueSecondary.add(0,"NULL");
		 
		 if(request.getParameter("contactTelecomUseSecondary0")!="") {
		 contactTelecomUseSecondary.add(0,request.getParameter("contactTelecomUseSecondary0"));
		 }else contactTelecomUseSecondary.add(0,"NULL");
		 
		 if(request.getParameter("contactTelecomSystemSecondary1")!="") {
		 contactTelecomSystemSecondary.add(1,request.getParameter("contactTelecomSystemSecondary1")); 
		 }else contactTelecomSystemSecondary.add(1,"NULL");
		 
		 if(request.getParameter("contactTelecomValueSecondary1")!="") {
		 contactTelecomValueSecondary.add(1,request.getParameter("contactTelecomValueSecondary1"));
		 }else contactTelecomValueSecondary.add(1,"NULL");
		 
		 if(request.getParameter("contactTelecomUseSecondary1")!="") {
		 contactTelecomUseSecondary.add(1,request.getParameter("contactTelecomUseSecondary1"));
		 }else contactTelecomUseSecondary.add(1,"NULL");
		 
		 if(request.getParameter("contactTelecomSystemSecondary2")!="") {
		 contactTelecomSystemSecondary.add(2,request.getParameter("contactTelecomSystemSecondary2")); 
		 }else contactTelecomSystemSecondary.add(2,"NULL");
		 
		 if(request.getParameter("contactTelecomValueSecondary2")!="") {
		 contactTelecomValueSecondary.add(2,request.getParameter("contactTelecomValueSecondary2"));
		 }else contactTelecomValueSecondary.add(2,"NULL");
		 
		 if(request.getParameter("contactTelecomUseSecondary2")!="") {
		 contactTelecomUseSecondary.add(2,request.getParameter("contactTelecomUseSecondary2"));
		 }else contactTelecomUseSecondary.add(2,"NULL");
	 
	 
	
	 int practitionerCount = Integer.parseInt(request.getParameter("practitionerCount"));
		
	 for(int i=0;i<practitionerCount;i++) {
		 practitioner.add(request.getParameter("practitioner"+i)); 	  
			 }
	 System.out.println("Practitioner array = "+practitioner);
	 
	 response.setContentType("text/html");
	request.setAttribute("message", SearchPatient.message);
	
	
	//send results to searchPatient.jsp
	//request.getRequestDispatcher("/searchPatientResults.jsp").forward(request, response);
	SearchPatient.updateAPatient(patientId, active, prefix, familyName, nameGiven, nameUsual, 
			  gender, birthDate, idValue, idDisplay, maritalStatus, practitioner,
			  street, city, state, postcode, language,  telecomValue,  contactRelationshipCodePrimary, contactRelationshipSystemPrimary,
			  contactRelationshipNamePrimary, contactTelecomSystemPrimary, contactTelecomValuePrimary, contactTelecomUsePrimary , 
			  contactRelationshipCodeSecondary, contactRelationshipSystemSecondary,	  contactRelationshipNameSecondary, 
			  contactTelecomSystemSecondary, contactTelecomValueSecondary, contactTelecomUseSecondary);
	
	request.getRequestDispatcher("/index.jsp").forward(request, response);
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
