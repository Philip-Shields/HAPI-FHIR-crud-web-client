

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hl7.fhir.dstu3.model.Enumerations.AdministrativeGender;

/**
 * Servlet implementation class HapiFhirPatientServlet
 */
@WebServlet("/AddPatientServlet")
public class AddPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public AddPatientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get fields from patient.jsp
		//String addAPatient=request.getParameter("addAPatient");
		String prefix=request.getParameter("prefix");
		String famName=request.getParameter("famName");
		String givenName=request.getParameter("givenName");
		String usualName=request.getParameter("usualName");
		AdministrativeGender gender = AdministrativeGender.valueOf(request.getParameter("gender"));
		String ptBirthDate=request.getParameter("ptBirthDate");
		
		String urNumber=request.getParameter("urNumber");
		String mediBankNumber=request.getParameter("mediBankNumber");
		String ptMaritalStatus=request.getParameter("ptMaritalStatus");
		String generalPractitioner=request.getParameter("generalPractitioner");
		String addressLine=request.getParameter("addressLine");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String postCode=request.getParameter("postCode");
		
		String language=request.getParameter("language");
		
		String homePhone=request.getParameter("homePhone");
		String workPhone;
		String mobilePhone;
		String email;
		
		if (request.getParameter("workPhone")!="") {
		workPhone=request.getParameter("workPhone");
		}else workPhone="NULL";
		
		if (request.getParameter("mobilePhone")!="") {
		mobilePhone=request.getParameter("mobilePhone");
		}else mobilePhone="NULL";
		
		if (request.getParameter("email")!="") {
		email=request.getParameter("email");
		}else email="NULL";
		
		String primaryContactRelationship;
		String primaryContactGivenNames;
		String primaryContactHomePhone;
		String primaryContactWorkPhone;
		String primaryContactMobilePhone;
		String secondaryContactRelationship;
		String secondaryContactGivenName;
		String secondaryContactHomePhone;
		String secondaryContactWorkPhone;
		String secondaryContactMobilePhone;
		
		if (request.getParameter("primaryContactRelationship")!="") {
		primaryContactRelationship=request.getParameter("primaryContactRelationship");
		}else primaryContactRelationship="NULL";
		
		if (request.getParameter("primaryContactGivenName")!="") {
		primaryContactGivenNames=request.getParameter("primaryContactGivenName");
		}else primaryContactGivenNames="NULL";
		
		if (request.getParameter("primaryContactHomePhone")!="") {
		primaryContactHomePhone=request.getParameter("primaryContactHomePhone");
		}else primaryContactHomePhone="NULL";
		
		if (request.getParameter("primaryContactWorkPhone")!="") {
		primaryContactWorkPhone=request.getParameter("primaryContactWorkPhone");	
		}else primaryContactWorkPhone="NULL";
		
		if (request.getParameter("primaryContactMobilePhone")!="") {
		primaryContactMobilePhone=request.getParameter("primaryContactMobilePhone");
		}else primaryContactMobilePhone="NULL";
		
		if (request.getParameter("secondaryContactRelationship")!="") {		
		secondaryContactRelationship=request.getParameter("secondaryContactRelationship");
		}else secondaryContactRelationship="NULL";
		
		if (request.getParameter("secondaryContactGivenName")!="") {
		secondaryContactGivenName=request.getParameter("secondaryContactGivenName");
		}else secondaryContactGivenName="NULL";
		
		if (request.getParameter("secondaryContactHomePhone")!="") {
		secondaryContactHomePhone=request.getParameter("secondaryContactHomePhone");
		}else secondaryContactHomePhone="NULL";
		
		if (request.getParameter("secondaryContactWorkPhone")!="") {
		secondaryContactWorkPhone=request.getParameter("secondaryContactWorkPhone");
		}else secondaryContactWorkPhone="NULL";
		
		if (request.getParameter("secondaryContactMobilePhone")!="") {
		secondaryContactMobilePhone=request.getParameter("secondaryContactMobilePhone");
		}else secondaryContactMobilePhone="NULL";
		
		
		try {
			SearchPatient.addAPatient(prefix, famName, givenName,	usualName, gender, ptBirthDate, urNumber, mediBankNumber, ptMaritalStatus, 
					generalPractitioner, addressLine, city, state, postCode, language, homePhone, workPhone, mobilePhone, email, primaryContactRelationship, primaryContactGivenNames, 
					primaryContactHomePhone, primaryContactWorkPhone, primaryContactMobilePhone, secondaryContactRelationship,
					secondaryContactGivenName, secondaryContactHomePhone, secondaryContactWorkPhone, secondaryContactMobilePhone);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
			//AddPatient.createPatient(true); 
			
			//get the message from addPatient
			response.setContentType("text/html");
			request.setAttribute("message", SearchPatient.message);
			try {
				
				//send results to jsp
				request.getRequestDispatcher("/index.jsp").forward(request, response);
					
				
			  } catch (ServletException e) {
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
