

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
@WebServlet("/UpdatePractitionerServlet")
public class UpdatePractitionerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<String> idDisplay = new ArrayList<String>();	
	 ArrayList<String> idValue = new ArrayList<String>();
	 ArrayList<String> idSystem = new ArrayList<String>();
	
	 ArrayList<String> telecomValue = new ArrayList<String>();
	 ArrayList<String> qualification = new ArrayList<String>();
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePractitionerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String crudResponse=request.getParameter("crud");
		
		
		if(crudResponse.equals("delete")) {
			String practitionerId=request.getParameter("id");
			System.out.println("patient's id= "+practitionerId);
			response.setContentType("text/html");
			request.setAttribute("practitionerId", practitionerId);//seems like all the name have to be the same for this to work
			
			//response.sendRedirect("deletePatients.jsp");
				try {
					 
				request.getRequestDispatcher("/deletePractitioner.jsp").forward(request, response);
		 } catch (ServletException e) {
			}
		}
		
		if(crudResponse.equals("goHome")) {
			response.sendRedirect("index.jsp");
		}
		
		if(crudResponse.equals("update")) {
		idDisplay.clear();	
		 idValue.clear();
		 idSystem.clear();
		qualification.clear();
		 telecomValue.clear();
		
		
	
	
	
	
	//get variables from searchPatient.java
	
		String active=request.getParameter("active");//
		String resourceType=request.getParameter("resourceType");//
	String practitionerId=request.getParameter("id");//
	String version=request.getParameter("version");//
	String lastUpdate=request.getParameter("lastUpdate");//
	String language=request.getParameter("language");//
	String gender=request.getParameter("gender");//
	
	
	String prefix=request.getParameter("prefix");//
	String familyName=request.getParameter("familyName");//
	 
		String nameGiven=request.getParameter("nameGiven");// maybe an array
		
		 String street = request.getParameter("street");//
		 String city = request.getParameter("city");//
		 String state = request.getParameter("state");//
		 String postcode = request.getParameter("postcode");//
		 
		 int qualificationCount = Integer.parseInt(request.getParameter("qualificationCount"));
		 
		 for(int i=0;i<qualificationCount;i++) {			
			 qualification.add(request.getParameter("qualification"+i)); 		
		 }
		 
		 
		 int idCount = Integer.parseInt(request.getParameter("idCount"));
		 
		 for(int i=0;i<idCount;i++) {
			 
			// if(request.getParameter("idValue"+i)!="") {
			 idValue.add(request.getParameter("idValue"+i));
			 //}else {}
				 //if(request.getParameter("idDisplay"+i)!="") {
			 idDisplay.add(request.getParameter("idDisplay"+i)); 
				// }else {}
					 //if(request.getParameter("idSystem"+i)!="") {
					 idSystem.add(request.getParameter("idSystem"+i));
					// }else {}
		 }
	
	 
	 System.out.println("idValue array = "+idValue);
	 System.out.println("idDisplay array = "+idDisplay);
	 
	 
	
	 
	
		
		 if(request.getParameter("workPhone")!="") {
		 telecomValue.add(0,request.getParameter("workPhone")); 
		 }else telecomValue.add(0,"NULL");	 
		
		 
		 if(request.getParameter("email")!="") {
		 telecomValue.add(1,request.getParameter("email")); 
		 }else telecomValue.add(1,"NULL");
		
		 if(request.getParameter("mobilePhone")!="") {
			 telecomValue.add(2,request.getParameter("mobilePhone")); 
			 }else telecomValue.add(2,"NULL");
	 
		 System.out.println("update servlet array telecom value = "+telecomValue);
		 
	 response.setContentType("text/html");
	request.setAttribute("message", SearchPatient.message);
	
	
	//send results to searchPatient.jsp
	//request.getRequestDispatcher("/searchPatientResults.jsp").forward(request, response);
	SearchPatient.updateAPractitioner(practitionerId, active, prefix, familyName, nameGiven, 
	gender,idValue, idDisplay, street, city, state, postcode, language, telecomValue,qualification);
	
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
