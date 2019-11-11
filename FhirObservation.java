import java.time.LocalDateTime;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.DateTimeType;
import org.hl7.fhir.dstu3.model.IdType;
import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.dstu3.model.Quantity;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.Observation.ObservationStatus;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;

public class FhirObservation {
	
	static FhirContext ctx = FhirContext.forDstu3();
	static Observation obs = new Observation();
	 static Quantity value = new Quantity();
	 static IGenericClient client = ctx.newRestfulGenericClient("http://114.142.160.89:8080/hapi-fhir-jpaserver/fhir/");
	 
	public static void main(String[] args) {
		
		obs.setSubjectTarget(obs.setId("Patient/5796"));
		obs.addIdentifier().setUse(Identifier.IdentifierUse.OFFICIAL).setType(new CodeableConcept().addCoding(new Coding().setCode("59408-5")
				.setSystem("http://purl.bioontology.org/ontology/LNC").setDisplay("Oxygen saturation in Arterial blood by Pulse oximetry")));		
		
		obs.addCategory(new CodeableConcept().addCoding(new Coding().setCode("Vital-Signs")
				.setSystem("http://hl7.org/fhir/observation-category").setDisplay("Vital Signs")));
	 
		
        obs.setStatus(ObservationStatus.PRELIMINARY);
        //DateTimeType obsEffectiveTime = new DateTimeType("2015-04-11T12:22:01-04:00");
        DateTimeType obsEffectiveTime = new DateTimeType(LocalDateTime.now().toString());
        
        obs.setEffective(obsEffectiveTime);
	     // Give the observation a code (what kind of observation is this)
	        //Coding coding = obs.getCode().addCoding();
	  //coding.setCode("29463-7").setSystem("http://loinc.org").setDisplay("Body Weight");
	
	  
     value.setValue(83.9).setSystem("http://unitsofmeasure.org").setCode("percent");
     	obs.setValue(value);
     	
     // create a new XML parser and serialize our Patient object with it
        String encoded = ctx.newJsonParser().setPrettyPrint(true)
                .encodeResourceToString(obs);

        System.out.println(encoded);
     	
     	//MethodOutcome outcome = client.create()
     		   //.resource(obs)
     		   //.prettyPrint()
     		   //.encodedJson()
     		   //.execute();
     
    // IdType id = (IdType) outcome.getId();
    // System.out.println("Got ID: " + id.getValue());
	}

}
