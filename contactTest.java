import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Practitioner;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;

public class contactTest {
	static FhirContext ctx = FhirContext.forDstu3();	
	static IGenericClient client = ctx.newRestfulGenericClient("http://192.168.1.25:8080/hapi-fhir-jpaserver/fhir/");
	 //static HashMap<String, String> practitionerData = new HashMap<String, String>();
	
	 
	 
	public static void main(String[] args) {
		
		Bundle response = client.search()
			      .forResource(Practitioner.class)				     
			      .count(100) //need this to search the whole bundle
			      .returnBundle(Bundle.class)
			      .execute();
		 
		 //Map<String, List<String>> practitionerData = new HashMap<String, List<String>>();
		MultiValuedMap<String, String> map = new HashSetValuedHashMap<String,String>();
		
		 if(response!=null) {				   
				
					for(int i=0;i<response.getTotal();i++) {
						Practitioner prac = new Practitioner();
					prac=(Practitioner) response.getEntry().get(i).getResource();
					//practitionerData.put( prac.getIdElement().getIdPart().toString(),(prac.getName().get(0).getFamily().toString())+" "+prac.getName().get(0).getGivenAsSingleString());
					
					
					if (prac.getQualification().size()!=0) {
					for(int a=0;a<prac.getQualification().size();a++) {
					
						if(prac.getQualification().get(a).getCode().getCoding().get(0).getDisplay()!=null) {
						
									//practitionerData.put(prac.getQualification().get(a).getCode().getCoding().get(0).getDisplay().toString(),prac.getIdElement().getIdPart().toString());
									//System.out.println(prac.getQualification().get(a).getCode().getCoding().get(0).getDisplay().toString());
									
									
									map.put(prac.getIdElement().getIdPart().toString(), prac.getQualification().get(a).getCode().getCoding().get(0).getDisplay().toString());
						}
						
						
						
						
		 }
					//values.clear();
		
			}else {
				
				
				map.put(prac.getIdElement().getIdPart().toString(),"NULL");
			}
		 }
		 }else 	System.out.println("Nothing to show");
		 
		for(int i=0;i<response.getTotal();i++) {
			
			Practitioner prac = new Practitioner();
			 prac=(Practitioner) response.getEntry().get(i).getResource();
			 
			 System.out.println("");
			System.out.println(prac.getIdElement().getIdPart().toString());	
			System.out.println(prac.getName().get(0).getFamily().toString());	
		      
			System.out.println(prac.getName().get(0).getGivenAsSingleString());
			
			
		for (Entry<String, String> entry : map.entries()) {
			
			if(prac.getIdElement().getIdPart().toString()==entry.getKey()) {
			 
				
				
						
				// System.out.println("length = " + entry.getValue().length());	
			    System.out.println("Qualification = " + entry.getValue());
				
			    
				}
		}
		}
		
		//map.asMap();
	}
}


