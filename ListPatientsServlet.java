

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListPatientsServlet
 */
@WebServlet("/ListPatientsServlet")
public class ListPatientsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListPatientsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get name from text area in listPatients.jsp
		String familyName=request.getParameter("familyName");
		//send it to Search patient
		SearchPatient.listAPatient(familyName);
		
		request.setAttribute("listId", SearchPatient.patientListId);
		request.setAttribute("listFamilyName", SearchPatient.patientListFamilyName);
		request.setAttribute("listGiven", SearchPatient.patientListGiven);
		request.setAttribute("listStreet", SearchPatient.patientListStreet);
		request.setAttribute("listTown", SearchPatient.patientListTown);
		
		response.setContentType("text/html");
		request.setAttribute("message", SearchPatient.message);
		try {
			
			//send results to index.jsp
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
