import java.util.ArrayList;

import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.DomainResource;
import org.hl7.fhir.dstu3.model.IdType;
import org.hl7.fhir.dstu3.model.OperationOutcome;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Practitioner;
import org.hl7.fhir.instance.model.api.IBaseOperationOutcome;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.server.exceptions.ResourceGoneException;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;

public class TestList {
	static FhirContext ctx = FhirContext.forDstu3();	
	static IGenericClient client = ctx.newRestfulGenericClient("http://114.142.160.89:8080/hapi-fhir-jpaserver/fhir/");
	//static ArrayList<Patient> listPatients = new ArrayList<Patient>();
	static String street;
	static OperationOutcome oo = new OperationOutcome();
	public static void main(String[] args) {
				
		try{
			 Patient response = client.read()
		                .resource(Patient.class)
		                .withId("")  
		                .execute(); 
			 
		 System.out.println(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(response));
	 
		}catch (ResourceNotFoundException e) {
			
			System.out.println("An issue "+ e.getMessage());
			
			//System.out.println("An issue "+oo.getIssue().get(0).getDiagnostics().toString());
		}catch (ResourceGoneException e) {
			System.out.println("An issue "+ e.getMessage());
		}catch (IllegalArgumentException e ) {
			System.out.println("An issue "+ e.getMessage());
		}
	}
	
	
}

