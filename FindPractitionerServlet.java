

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
@WebServlet("/FindPractitionerServlet")
public class FindPractitionerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FindPractitionerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String practitionerId=request.getParameter("practitionerId");
		SearchPatient.getAPractitioner(practitionerId);
		
				//response.sendRedirect("searchPatient.jsp");
		
			
			
			
			request.setAttribute("html", SearchPatient.html);
			request.setAttribute("active", SearchPatient.active);
			request.setAttribute("resourceType", SearchPatient.resourceType);
			request.setAttribute("id", SearchPatient.id);
			request.setAttribute("version", SearchPatient.version);
			request.setAttribute("lastUpdate", SearchPatient.lastUpdate);
			request.setAttribute("language", SearchPatient.language);
			request.setAttribute("gender", SearchPatient.gender);
			request.setAttribute("birthDate", SearchPatient.birthDate);
			
			
			request.setAttribute("prefix", SearchPatient.prefix);
			request.setAttribute("familyName", SearchPatient.familyName);				
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
			
			request.setAttribute("qualification", SearchPatient.qualification);
			
			//System.out.println("Telecom system findPatientServlet= "+SearchPatient.contactTelecomSystem);
			//System.out.println("Telecom value findPatientServlet= "+SearchPatient.contactTelecomValue);
			//System.out.println("telecom use findPatientServlet= "+SearchPatient.contactTelecomUse);
			
			
			
			response.setContentType("text/html");
			request.setAttribute("message", SearchPatient.message);
			
			
			try {
				if(!SearchPatient.message.equals("")) {
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}else
				//send results to searchPatient.jsp
				request.getRequestDispatcher("/searchPractitionerResults.jsp").forward(request, response);
					
				
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
