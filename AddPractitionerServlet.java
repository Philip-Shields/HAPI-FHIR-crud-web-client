

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hl7.fhir.dstu3.model.Enumerations.AdministrativeGender;

/**
 * Servlet implementation class HapiFhirPractitionerServlet
 */
@WebServlet("/AddPractitionerServlet")
public class AddPractitionerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPractitionerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get fields from patient.jsp
				
				String prefix=request.getParameter("prefix");
				String qualification=request.getParameter("qualification");
				
				String famName=request.getParameter("famName");
				String givenName=request.getParameter("givenName");
				String usualName=request.getParameter("usualName");
				AdministrativeGender gender = AdministrativeGender.valueOf(request.getParameter("gender"));
				
				String registrationNumber=request.getParameter("registrationNumber");
				String payNumber=request.getParameter("payNumber");
				String addressLine=request.getParameter("addressLine");
				String city=request.getParameter("city");
				String state=request.getParameter("state");
				String postCode=request.getParameter("postCode");
				
				String language=request.getParameter("language");
				
				String workPhone=request.getParameter("workPhone");
				String mobilePhone=request.getParameter("mobilePhone");
				String email=request.getParameter("email");
				
				
				SearchPatient.addAPractitioner(prefix, qualification, famName, givenName,	usualName, gender,  
						 registrationNumber, payNumber, addressLine, city, state, postCode, language,  workPhone, mobilePhone, email);
				
				
				
					//AddPatient.createPractitioner(true); 
					
					//get the message from HapiFhirPatient
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
