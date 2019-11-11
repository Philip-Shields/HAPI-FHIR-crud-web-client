

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeletePatientsServlet
 */
@WebServlet("/FindPatientServlet")
public class FindPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 ArrayList<String> contactRelationshipCode = new ArrayList<String>();
		static ArrayList<String> contactRelationshipSystem = new ArrayList<String>();
		static ArrayList<String> contactRelationshipName = new ArrayList<String>();
		static ArrayList<String> contactTelecomSystem = new ArrayList<String>();
		static ArrayList<String> contactTelecomValue = new ArrayList<String>();
		static ArrayList<String> contactTelecomUse = new ArrayList<String>();
		
		
		
		
    public FindPatientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String patientId=request.getParameter("patientId");
		SearchPatient.getAPatient(patientId);
		
				//response.sendRedirect("searchPatient.jsp");
		
			//get variables from searchPatient.java
			
			
			request.setAttribute("html", SearchPatient.html);
			request.setAttribute("active", SearchPatient.active);
			request.setAttribute("resourceType", SearchPatient.resourceType);
			request.setAttribute("id", SearchPatient.id);
			request.setAttribute("version", SearchPatient.version);
			request.setAttribute("lastUpdate", SearchPatient.lastUpdate);
			request.setAttribute("language", SearchPatient.language);
			request.setAttribute("gender", SearchPatient.gender);
			request.setAttribute("birthDate", SearchPatient.birthDate);
			request.setAttribute("maritalStatus", SearchPatient.maritalStatus);
			request.setAttribute("maritalSystem", SearchPatient.maritalSystem);
			request.setAttribute("maritalCode", SearchPatient.maritalCode);
			
			request.setAttribute("prefix", SearchPatient.prefix);
			request.setAttribute("familyName", SearchPatient.familyName);				
			request.setAttribute("nameUse", SearchPatient.nameUse);
			request.setAttribute("nameGiven", SearchPatient.nameGiven);
			
			request.setAttribute("street", SearchPatient.street);
			request.setAttribute("city", SearchPatient.city);
			request.setAttribute("state", SearchPatient.state);
			request.setAttribute("postcode", SearchPatient.postcode);
			
			//get arrays		
			request.setAttribute("idSystem", SearchPatient.idSystem);
			request.setAttribute("idValue", SearchPatient.idValue);
			request.setAttribute("idDisplay", SearchPatient.idDisplay);
			
			request.setAttribute("telecomSystem", SearchPatient.telecomSystem);
			request.setAttribute("telecomValue", SearchPatient.telecomValue);
			request.setAttribute("telecomUse", SearchPatient.telecomUse);
			
			//System.out.println("Telecom system findPatientServlet= "+SearchPatient.contactTelecomSystem);
			//System.out.println("Telecom value findPatientServlet= "+SearchPatient.contactTelecomValue);
			//System.out.println("telecom use findPatientServlet= "+SearchPatient.contactTelecomUse);
			
			request.setAttribute("contactRelationshipCodePrimary", SearchPatient.contactRelationshipCodePrimary);
			request.setAttribute("contactRelationshipNamePrimary", SearchPatient.contactRelationshipNamePrimary);
			request.setAttribute("contactRelationshipCodeSecondary", SearchPatient.contactRelationshipCodeSecondary);
			request.setAttribute("contactRelationshipNameSecondary", SearchPatient.contactRelationshipNameSecondary);
			
			request.setAttribute("contactTelecomSystemPrimary", SearchPatient.contactTelecomSystemPrimary);
			request.setAttribute("contactTelecomValuePrimary", SearchPatient.contactTelecomValuePrimary);
			request.setAttribute("contactTelecomUsePrimary", SearchPatient.contactTelecomUsePrimary);
			request.setAttribute("contactTelecomSystemSecondary", SearchPatient.contactTelecomSystemSecondary);
			request.setAttribute("contactTelecomValueSecondary", SearchPatient.contactTelecomValueSecondary);
			request.setAttribute("contactTelecomUseSecondary", SearchPatient.contactTelecomUseSecondary);
			request.setAttribute("practitioner", SearchPatient.practitioner);
			//request.setAttribute("practitionerData", SearchPatient.practitionerData);
			
			response.setContentType("text/html");
			request.setAttribute("message", SearchPatient.message);
			
			
			try {
				if(!SearchPatient.message.equals("")) {
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}else
				//send results to searchPatient.jsp
				request.getRequestDispatcher("/searchPatientResults.jsp").forward(request, response);
					
				
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
