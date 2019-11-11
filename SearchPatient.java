
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.apache.commons.io.IOUtils;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.ContactPoint;
import org.hl7.fhir.dstu3.model.DateType;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.IdType;
import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.OperationOutcome;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.dstu3.model.ContactPoint.ContactPointUse;
import org.hl7.fhir.dstu3.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.dstu3.model.Patient.ContactComponent;
import org.hl7.fhir.dstu3.model.Practitioner.PractitionerQualificationComponent;
import org.hl7.fhir.dstu3.model.Practitioner;
import org.hl7.fhir.instance.model.api.IBaseOperationOutcome;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.server.exceptions.ResourceGoneException;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;

public class SearchPatient {
	static FhirContext ctx = FhirContext.forDstu3();
	
	
	//static IGenericClient client = ctx.newRestfulGenericClient("http://114.142.160.89:8080/hapi-fhir-jpaserver/fhir/");
	
	static IdType idType=null;
	static String outPut="bundle";
	static String clear=""; //used to clear text boxes in search patient.jsp
	static String html;
	static boolean active;
	static String resourceType;
	static String id;
	static String version;
	static String lastUpdate;
	static String language;
	static String gender;
	static String birthDate;
	static String maritalStatus;
	static String maritalSystem;
	static String maritalCode;
	static String prefix;
	static String familyName;
	static String nameGiven;
	static String nameUse;
	static String message;
	static String error;
	static String street;
	static String town;
	static String city;
	static String state;
	static String postcode;
	static String contactRelationshipCodePrimary;
	static String contactRelationshipSystemPrimary;
	static String contactRelationshipNamePrimary;
	static String contactRelationshipCodeSecondary;
	static String contactRelationshipSystemSecondary;
	static String contactRelationshipNameSecondary;
	static String serverURL="http://192.168.1.5:8080/hapi-fhir-jpaserver/fhir";
	static Patient patient = new Patient();
	static IdType idT=null;
	
	
	static ArrayList<String> idSystem = new ArrayList<String>();
	static ArrayList<String> idValue = new ArrayList<String>();
	static ArrayList<String> idDisplay = new ArrayList<String>();
	
	 
	static ArrayList<String> telecomSystem = new ArrayList<String>();
	static ArrayList<String> telecomValue = new ArrayList<String>();
	static ArrayList<String> telecomUse = new ArrayList<String>();	
	
	
	
	static ArrayList<String> contactTelecomSystemPrimary = new ArrayList<String>();
	static ArrayList<String> contactTelecomValuePrimary = new ArrayList<String>();
	static ArrayList<String> contactTelecomUsePrimary = new ArrayList<String>();
	static ArrayList<String> contactTelecomSystemSecondary = new ArrayList<String>();
	static ArrayList<String> contactTelecomValueSecondary = new ArrayList<String>();
	static ArrayList<String> contactTelecomUseSecondary = new ArrayList<String>();
	
	static ArrayList<String> practitioner = new ArrayList<String>();
	
	 static HashMap<String, String> practitionerData = new HashMap<String, String>();
	static ArrayList<String> patientListId = new ArrayList<String>();
	static ArrayList<String> patientListFamilyName = new ArrayList<String>();
	static ArrayList<String> patientListGiven = new ArrayList<String>();
	static ArrayList<String> patientListStreet = new ArrayList<String>();
	static ArrayList<String> patientListTown = new ArrayList<String>();
	
	static ArrayList<String> practitionerListId = new ArrayList<String>();
	static ArrayList<String> practitionerListFamilyName = new ArrayList<String>();
	static ArrayList<String> practitionerListGiven = new ArrayList<String>();
	static ArrayList<String> qualification = new ArrayList<String>();
	
	static MultiValuedMap<String, String> practitionerListDiscipline = new HashSetValuedHashMap<String,String>();
		
	
		//Bundle outPut = client.search()
			      //.forResource(Patient.class)
			     // .where(Patient.IDENTIFIER.exactly().systemAndCode("http://www.ontohealth.com.au", "345672"))
			      
			      //.where(Patient.ADDRESS.matches().values("Toronto"))
			      //.returnBundle(Bundle.class)
			      //.execute();
			     
public static void getAPatient(String patientId)  throws ResourceGoneException, ResourceNotFoundException, IOException{	
			
			//patient details
			id="";
			version="";
			lastUpdate="";
			language="";
			gender="";
			birthDate="";
			maritalStatus="";
			maritalSystem="";
			maritalCode="";
			prefix="";
			familyName="";
			message="";
			error="";
			street="";
			city="";
			state="";
			postcode="";
			
			
			idSystem.clear();
			idValue.clear();
			idDisplay.clear();
			
			telecomSystem.clear();
			telecomValue.clear();
			telecomUse.clear();
			
			nameUse="";
			nameGiven=""; 
			
			
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
			
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			  //Date date = sdf.parse(outPut.getBirthDate().toString());
			//System.out.println("Patient id= "+sdf.format(patientId));
			 IGenericClient client = ctx.newRestfulGenericClient(serverURL);
			try {
		Patient outPut = client.read()
                .resource(Patient.class)
                .withId(patientId)  
                .execute(); 
		
		 
			 
		if(outPut!=null) {
		html=(outPut.getText().getDiv().toString());
		
		//System.out.println("Active= "+outPut.getActive());
		active=outPut.getActive();
		//System.out.println("Resource type= "+outPut.getResourceType().toString());
		resourceType=outPut.getResourceType().toString();
		//System.out.println("Id= "+outPut.getId().toString());
		id=outPut.getIdElement().getIdPart().toString();
		//System.out.println("Version= "+outPut.getMeta().getVersionId().toString());
		version=outPut.getMeta().getVersionId().toString();
		//System.out.println("Last updated= "+outPut.getMeta().getLastUpdated().toString());
		lastUpdate=outPut.getMeta().getLastUpdated().toString();
		//System.out.println("Language= "+outPut.getLanguage().toString());
		
		if (outPut.getLanguage()!=null) {
			language=outPut.getLanguage().toString();
		}else language="";
			
		//System.out.println("Gender= "+outPut.getGender().toString());
		gender=outPut.getGender().toString();
		//System.out.println("Birthdate= "+sdf.format(outPut.getBirthDate()));
		birthDate= sdf.format(outPut.getBirthDate());
		//System.out.println("Marital status= "+outPut.getMaritalStatus().getCoding().get(0).getDisplay().toString());
		maritalStatus=outPut.getMaritalStatus().getCoding().get(0).getDisplay().toString();
		//System.out.println("Marital system= "+outPut.getMaritalStatus().getCoding().get(0).getSystem().toString());
		maritalSystem=outPut.getMaritalStatus().getCoding().get(0).getSystem().toString();
		//System.out.println("Marital code= "+outPut.getMaritalStatus().getCoding().get(0).getCode().toString());
		maritalCode=outPut.getMaritalStatus().getCoding().get(0).getCode().toString();
		
		if(outPut.getIdentifier().size()!=0) {
			for(int i =0;i<outPut.getIdentifier().size();i++) {		
			 if (outPut.getIdentifier().get(i).getType().getCoding().get(0).getSystem()!=null) {	
			//System.out.println(outPut.getIdentifier().get(i).getSystem().toString());
			idSystem.add(i,outPut.getIdentifier().get(i).getType().getCoding().get(0).getSystem().toString());
			 }//else idSystem.add(i,"NULL");
			 
			 if (outPut.getIdentifier().get(i).getType().getCoding().get(0).getCode()!=null) {
			//System.out.println(outPut.getIdentifier().get(i).getValue().toString());
			idValue.add(i,outPut.getIdentifier().get(i).getType().getCoding().get(0).getCode().toString());
			 }//else idValue.add(i,"NULL");
			 
			 if (outPut.getIdentifier().get(i).getType().getCoding().get(0).getDisplay()!=null) {
			//System.out.println(outPut.getIdentifier().get(i).getType().getCoding().get(0).getDisplay().toString());
			idDisplay.add(i,outPut.getIdentifier().get(i).getType().getCoding().get(0).getDisplay().toString());
			}//else idDisplay.add(i,"NULL");
			 
			
			
			}
			}
		
		
		
		
			
			//System.out.println(outPut.getName().get(0).getPrefixAsSingleString());
			
			prefix=outPut.getName().get(0).getPrefixAsSingleString();
			
			
			//System.out.println(outPut.getName().get(0).getFamily().toString());
			familyName=outPut.getName().get(0).getFamily().toString();			
			//System.out.println(outPut.getName().get(0).getUse().toString());
			nameUse=outPut.getName().get(1).getGivenAsSingleString();
			//System.out.println(outPut.getName().get(0).getGivenAsSingleString());	//removes braces	
			nameGiven=outPut.getName().get(0).getGivenAsSingleString();	
		
		
		if(outPut.getTelecom().size()!=0) {
				
				
				
			for(int i=0;i<outPut.getTelecom().size();i++) {
				
				if(outPut.getTelecom().get(i).getSystem()!=null) {	
				telecomSystem.add(i,outPut.getTelecom().get(i).getSystem().toString());
				}
				
				if(outPut.getTelecom().get(i).getValue()!=null) {
				telecomValue.add(i,outPut.getTelecom().get(i).getValue().toString());
				}
				
				if(outPut.getTelecom().get(i).getUse()!=null) {
				telecomUse.add(i,outPut.getTelecom().get(i).getUse().toString());
				}
				
				
				//System.out.println(outPut.getTelecom().get(i).getSystem().toString());	
				//telecomSystem.add(1,outPut.getTelecom().get(1).getSystem().toString());
				//System.out.println(outPut.getTelecom().get(i).getValue().toString());
				//telecomValue.add(1,outPut.getTelecom().get(1).getValue().toString());
				//System.out.println(outPut.getTelecom().get(i).getUse().toString());
				//telecomUse.add(1,outPut.getTelecom().get(1).getUse().toString());
				
				//System.out.println(outPut.getTelecom().get(i).getSystem().toString());	
				//telecomSystem.add(2,outPut.getTelecom().get(2).getSystem().toString());
				//System.out.println(outPut.getTelecom().get(i).getValue().toString());
				//telecomValue.add(2,outPut.getTelecom().get(2).getValue().toString());
				//System.out.println(outPut.getTelecom().get(i).getUse().toString());
				//telecomUse.add(2,outPut.getTelecom().get(2).getUse().toString());
				
			}
			}
		
		
		System.out.println("Telecom system getAPatient= "+telecomSystem);
		System.out.println("Telecom value getAPatient= "+telecomValue);
		System.out.println("telecom use getAPatient= "+telecomUse);
		
			
			//System.out.println(outPut.getAddress().get(i).getLine().toString());
			String str=outPut.getAddress().get(0).getLine().toString();
			
			removeBraces(str);
			////System.out.println(outPut.getAddress().get(i).getCity().toString());
			city=outPut.getAddress().get(0).getCity().toString();
			//System.out.println(outPut.getAddress().get(i).getState().toString());
			state=outPut.getAddress().get(0).getState().toString();
			//System.out.println(outPut.getAddress().get(i).getPostalCode().toString());
			postcode=outPut.getAddress().get(0).getPostalCode().toString();
			
		
		
		
			System.out.println("Contact size= "+outPut.getContact().size());
			System.out.println("getTelecom size= "+outPut.getContact().get(0).getTelecom().size());
			
			
			
					if(outPut.getContact().get(0).getRelationship().get(0).getCoding().get(0).getCode()!=null) {		
				//System.out.println("Relationship code in get patient "+outPut.getContact().get(0).getRelationship().get(0).getCoding().get(0).getCode().toString());
				contactRelationshipCodePrimary=outPut.getContact().get(0).getRelationship().get(0).getCoding().get(0).getCode().toString();
					}else contactRelationshipCodePrimary="NULL";
					
				//System.out.println("relationship system in get patient "+outPut.getContact().get(0).getRelationship().get(0).getCoding().get(0).getSystem().toString());
				//contactRelationshipSystemPrimary=outPut.getContact().get(0).getRelationship().get(0).getCoding().get(0).getSystem().toString();
					if(outPut.getContact().get(0).getName().getGivenAsSingleString()!=null) {
				//System.out.println("Relationship name in get patient "+outPut.getContact().get(0).getName().getGivenAsSingleString());
				contactRelationshipNamePrimary=outPut.getContact().get(0).getName().getGivenAsSingleString();
					}else contactRelationshipNamePrimary="NULL";
				//System.out.println("i= "+i);
			
				
				
					if(outPut.getContact().get(0).getTelecom().get(0).getSystem()!=null) {
					//System.out.println(outPut.getContact().get(0).getTelecom().get(0).getSystem().toString());
					contactTelecomSystemPrimary.add(0,outPut.getContact().get(0).getTelecom().get(0).getSystem().toString());
					}else contactTelecomSystemPrimary.add(0,"NULL");
					
					if(outPut.getContact().get(0).getTelecom().get(0).getValue()!=null) {
					//System.out.println(outPut.getContact().get(0).getTelecom().get(0).getValue().toString());
					contactTelecomValuePrimary.add(0,outPut.getContact().get(0).getTelecom().get(0).getValue().toString());
					}else contactTelecomValuePrimary.add(0,"NULL");
					
					if(outPut.getContact().get(0).getTelecom().get(0).getUse()!=null) {
					//System.out.println(outPut.getContact().get(0).getTelecom().get(0).getUse().toString());
					contactTelecomUsePrimary.add(0,outPut.getContact().get(0).getTelecom().get(0).getUse().toString());
					}else contactTelecomUsePrimary.add(0,"NULL");
					
					if(outPut.getContact().get(0).getTelecom().get(1).getSystem()!=null) {
					//System.out.println(outPut.getContact().get(0).getTelecom().get(1).getSystem().toString());
					contactTelecomSystemPrimary.add(1,outPut.getContact().get(0).getTelecom().get(1).getSystem().toString());
					}else contactTelecomSystemPrimary.add(1,"NULL");
					
					if(outPut.getContact().get(0).getTelecom().get(1).getValue()!=null) {
					//System.out.println(outPut.getContact().get(0).getTelecom().get(1).getValue().toString());
					contactTelecomValuePrimary.add(1,outPut.getContact().get(0).getTelecom().get(1).getValue().toString());
					}else contactTelecomValuePrimary.add(1,"NULL");
					
					if(outPut.getContact().get(0).getTelecom().get(1).getUse()!=null) {
					//System.out.println(outPut.getContact().get(0).getTelecom().get(1).getUse().toString());
					contactTelecomUsePrimary.add(1,outPut.getContact().get(0).getTelecom().get(1).getUse().toString());
					}else contactTelecomUsePrimary.add(1,"NULL");
					//System.out.println("a= "+a);
					
					if(outPut.getContact().get(0).getTelecom().get(2).getSystem()!=null) {
					//System.out.println(outPut.getContact().get(0).getTelecom().get(2).getSystem().toString());
					contactTelecomSystemPrimary.add(2,outPut.getContact().get(0).getTelecom().get(2).getSystem().toString());
					}else contactTelecomSystemPrimary.add(2,"NULL");
					
					if(outPut.getContact().get(0).getTelecom().get(2).getValue()!=null) {
					//System.out.println(outPut.getContact().get(0).getTelecom().get(2).getValue().toString());
					contactTelecomValuePrimary.add(2,outPut.getContact().get(0).getTelecom().get(2).getValue().toString());
					}else contactTelecomValuePrimary.add(2,"NULL");
					
					if(outPut.getContact().get(0).getTelecom().get(2).getUse()!=null) {
					//System.out.println(outPut.getContact().get(0).getTelecom().get(2).getUse().toString());
					contactTelecomUsePrimary.add(2,outPut.getContact().get(0).getTelecom().get(2).getUse().toString());
					}else contactTelecomUsePrimary.add(2,"NULL");
					
					if(outPut.getContact().get(1).getName().getGivenAsSingleString()!=null) {
					//contactRelationshipSystemSecondary=outPut.getContact().get(1).getRelationship().get(0).getCoding().get(0).getSystem().toString();
					//System.out.println("i= "+i+"Name in get patient "+outPut.getContact().get(i).getName().getGiven().toString());
					contactRelationshipNameSecondary=outPut.getContact().get(1).getName().getGivenAsSingleString();
					}else contactRelationshipNameSecondary="NULL";
					
					if(outPut.getContact().get(1).getRelationship().get(0).getCoding().get(0).getCode()!=null) {
					//System.out.println("i= "+i+"Relationship in get patient "+outPut.getContact().get(i).getRelationship().get(0).getCoding().get(0).getCode().toString());
					contactRelationshipCodeSecondary=outPut.getContact().get(1).getRelationship().get(0).getCoding().get(0).getCode().toString();
					}else contactRelationshipCodeSecondary="NULL";
					
					//System.out.println(outPut.getContact().get(i).getRelationship().get(0).getCoding().get(0).getSystem().toString());
					
					if(outPut.getContact().get(1).getTelecom().get(0).getSystem()!=null) {
					//System.out.println(outPut.getContact().get(1).getTelecom().get(0).getSystem().toString());
					contactTelecomSystemSecondary.add(0,outPut.getContact().get(1).getTelecom().get(0).getSystem().toString());
					}else contactTelecomSystemSecondary.add(0,"NULL");
					
					if(outPut.getContact().get(1).getTelecom().get(0).getValue()!=null) {
					//System.out.println(outPut.getContact().get(1).getTelecom().get(0).getValue().toString());
					contactTelecomValueSecondary.add(0,outPut.getContact().get(1).getTelecom().get(0).getValue().toString());
					}else contactTelecomValueSecondary.add(0,"NULL");
					
					if(outPut.getContact().get(1).getTelecom().get(0).getUse()!=null) {
					//System.out.println(outPut.getContact().get(1).getTelecom().get(0).getUse().toString());
					contactTelecomUseSecondary.add(0,outPut.getContact().get(1).getTelecom().get(0).getUse().toString());
					}else contactTelecomUseSecondary.add(0,"NULL");
					
					if(outPut.getContact().get(1).getTelecom().get(1).getSystem()!=null) {
					//System.out.println(outPut.getContact().get(1).getTelecom().get(1).getSystem().toString());
					contactTelecomSystemSecondary.add(1,outPut.getContact().get(1).getTelecom().get(1).getSystem().toString());
					}else contactTelecomSystemSecondary.add(1,"NULL");
					
					if(outPut.getContact().get(1).getTelecom().get(1).getValue()!=null) {
					//System.out.println(outPut.getContact().get(1).getTelecom().get(1).getValue().toString());
					contactTelecomValueSecondary.add(1,outPut.getContact().get(1).getTelecom().get(1).getValue().toString());
					}else contactTelecomValueSecondary.add(1,"NULL");
					
					if(outPut.getContact().get(1).getTelecom().get(1).getUse()!=null) {
					//System.out.println(outPut.getContact().get(1).getTelecom().get(1).getUse().toString());
					contactTelecomUseSecondary.add(1,outPut.getContact().get(1).getTelecom().get(1).getUse().toString());
					}else contactTelecomUseSecondary.add(1,"NULL");
					
					if(outPut.getContact().get(1).getTelecom().get(2).getSystem()!=null) {
					//System.out.println(outPut.getContact().get(1).getTelecom().get(2).getSystem().toString());
					contactTelecomSystemSecondary.add(2,outPut.getContact().get(1).getTelecom().get(2).getSystem().toString());
					}else contactTelecomSystemSecondary.add(2,"NULL");
					
					if(outPut.getContact().get(1).getTelecom().get(2).getValue()!=null) {
					//System.out.println(outPut.getContact().get(1).getTelecom().get(2).getValue().toString());
					contactTelecomValueSecondary.add(2,outPut.getContact().get(1).getTelecom().get(2).getValue().toString());
					}else contactTelecomValueSecondary.add(2,"NULL");
					
					if(outPut.getContact().get(1).getTelecom().get(2).getUse()!=null) {
					//System.out.println(outPut.getContact().get(1).getTelecom().get(2).getUse().toString());
					contactTelecomUseSecondary.add(2,outPut.getContact().get(1).getTelecom().get(2).getUse().toString());
					}else contactTelecomUseSecondary.add(2,"NULL");
			
			//System.out.println("get system= "+contactTelecomSystem);
			//System.out.println("get value= "+contactTelecomValue);
			//System.out.println("get use= "+contactTelecomUse);
		
			
		if(outPut.getGeneralPractitioner().size()!=0) {
			for(int i =0;i<outPut.getGeneralPractitioner().size();i++) {
				
				//System.out.println(outPut.getGeneralPractitioner().get(i).getReference().toString());
				practitioner.add(i,outPut.getGeneralPractitioner().get(i).getReference().toString());
			}
		
		
		
		}
		
		}
			}catch (ResourceNotFoundException e) {
				
				message= e.getMessage();
				
				//System.out.println("An issue "+oo.getIssue().get(0).getDiagnostics().toString());
			}catch (ResourceGoneException e) {
				message= e.getMessage();
			}catch (IllegalArgumentException e ) {
				message= e.getMessage();
			}
}
			
		//System.out.println(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle));
		
		 //String encoded = ctx.newJsonParser().setPrettyPrint(true)
	                //.encodeResourceToString(outPut);

	        //System.out.println(encoded);

public static void addAPatient(String prefix, String famName, String givenName, String usualName, 
		  AdministrativeGender gender, String birthDate, String urNumber, String mediBankNumber, String maritalStatus, String generalPractitioner,
		  String street, String city, String state, String postcode, String language, 
		  String homePhone, String workPhone, String mobilePhone, String email, String primaryContactRelationship, String primaryContactGivenName, 
		  String primaryContactHomePhone, String primaryContactWorkPhone, String primaryContactMobilePhone, String secondaryContactRelationship,
		  String secondaryContactGivenName, String secondaryContactHomePhone, String secondaryContactWorkPhone, String secondaryContactMobilePhone) throws ParseException {
	        
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	  final Date date = sdf.parse(birthDate);
	  
	        patient.setDeceased(null);
	        
	        patient.addName().setUse(HumanName.NameUse.OFFICIAL)
	                .addPrefix(prefix).setFamily(famName).addGiven(givenName);
	        patient.addName().setUse(HumanName.NameUse.USUAL).addGiven(usualName);
	        patient.setActive(true);
	        patient.setGender(gender);
	        
	        //patient.setBirthDateElement(new DateType(birthDate));
	        patient.setBirthDate(date);
	       
	        
	        patient.addIdentifier()
      	.setType(new CodeableConcept().addCoding(new Coding().setSystem("http://www.ontohealth.com.au")
      			.setCode(urNumber).setDisplay("UR Number")));
		 
	        patient.addIdentifier()
      	.setType(new CodeableConcept().addCoding(new Coding().setSystem("medibank.gov.au")
      			.setCode(mediBankNumber).setDisplay("Medibank Number")));
	        
	        patient.addAddress().addLine(street).setCity(city).setState(state).setPostalCode(postcode);
	        patient.setLanguage(language);  
	        
	        patient.setMaritalStatus(new CodeableConcept().addCoding(new Coding().setCode("365581002").setDisplay(maritalStatus)
					.setSystem("http://purl.bioontology.org/ontology/SNOMEDCT/")));
	        
	        patient.addGeneralPractitioner(new Reference(new IdType(generalPractitioner)));
	        
	       
	        ContactPoint hPhone = patient.addTelecom();	        
	        ContactPoint wPhone = patient.addTelecom();
	        ContactPoint hEmail = patient.addTelecom();
	        ContactPoint mobile = patient.addTelecom();
	        
	        	        
			hPhone.setUse(ContactPointUse.HOME).setSystem(ContactPointSystem.PHONE).setValue(homePhone);
			wPhone.setUse(ContactPointUse.WORK).setSystem(ContactPointSystem.PHONE).setValue(workPhone);
			mobile.setUse(ContactPointUse.MOBILE).setSystem(ContactPointSystem.PHONE).setValue(mobilePhone);
			hEmail.setUse(ContactPointUse.HOME).setSystem(ContactPointSystem.EMAIL).setValue(email);
			
	        
			
			if(primaryContactGivenName !="") {
				ContactComponent primary=patient.addContact();	
			
				primary.addRelationship(new CodeableConcept().addCoding(new Coding().setCode(primaryContactRelationship)
						.setSystem("http://purl.bioontology.org/ontology/SNOMEDCT/125677006")));
				
			primary.setName(new HumanName().addGiven(primaryContactGivenName));
			
			primary.addTelecom().setUse(ContactPointUse.HOME).setSystem(ContactPointSystem.PHONE).setValue(primaryContactHomePhone);
			primary.addTelecom().setUse(ContactPointUse.WORK).setSystem(ContactPointSystem.PHONE).setValue(primaryContactWorkPhone);
			primary.addTelecom().setUse(ContactPointUse.MOBILE).setSystem(ContactPointSystem.PHONE).setValue(primaryContactMobilePhone);
			
			}
			
			if(secondaryContactGivenName !="") {
				ContactComponent secondary=patient.addContact();	
			
				secondary.addRelationship(new CodeableConcept().addCoding(new Coding().setCode(secondaryContactRelationship)
						.setSystem("http://purl.bioontology.org/ontology/SNOMEDCT/125677006")));
				
			secondary.setName(new HumanName().addGiven(secondaryContactGivenName));
			
			secondary.addTelecom().setUse(ContactPointUse.HOME).setSystem(ContactPointSystem.PHONE).setValue(secondaryContactHomePhone);
			secondary.addTelecom().setUse(ContactPointUse.WORK).setSystem(ContactPointSystem.PHONE).setValue(secondaryContactWorkPhone);
			secondary.addTelecom().setUse(ContactPointUse.MOBILE).setSystem(ContactPointSystem.PHONE).setValue(secondaryContactMobilePhone);
			}
			
			IGenericClient client = ctx.newRestfulGenericClient(serverURL);
			MethodOutcome outcome = client.create()
		    		   .resource(patient)
		    		   .prettyPrint()
		    		   .encodedJson()
		    		   .execute();
		    
		   
		    message="";	
		    idT = (IdType) outcome.getId();
		    message= idT.getValue();
		    System.out.println("Patients' ID and history: " + idT.getValue());
		    
	        // create a new XML parser and serialize our Patient object with it
	       // String encoded = ctx.newJsonParser().setPrettyPrint(true)
	               // .encodeResourceToString(patient);

	       // System.out.println(encoded);

	       
}
		
public static void addAPractitioner(String prefix, String qualification, String famName, String givenName, String usualName, 
		  AdministrativeGender gender,  String registrationNumber, String payNumber, String street, String city, String state, String postcode, String language, 
		  String workPhone, String mobilePhone, String email) {
	  
	Practitioner pract = new Practitioner();
	  PractitionerQualificationComponent q1 = new PractitionerQualificationComponent(new CodeableConcept().addCoding(new Coding().setCode("309371000")
				.setSystem("http://purl.bioontology.org/ontology/SNOMEDCT/").setDisplay(qualification)));
	  
	  //PractitionerQualificationComponent q2 = new PractitionerQualificationComponent(new CodeableConcept().addCoding(new Coding().setCode("309371000")
				//.setSystem("http://purl.bioontology.org/ontology/SNOMEDCT/").setDisplay(qualification2)));
		
		        
		  ContactPoint wPhone = pract.addTelecom();
		  ContactPoint hEmail = pract.addTelecom();
		  ContactPoint mobile = pract.addTelecom();
		
		 
		  
		  pract.addQualification(q1);
		  //practitioner.addQualification(q2);
		  
		  pract.addName().setUse(HumanName.NameUse.OFFICIAL)
	      .addPrefix(prefix).setFamily(famName).addGiven(givenName);
		  pract.addName().setUse(HumanName.NameUse.USUAL).addGiven(usualName);
		  pract.setActive(true);
		  pract.setGender(gender);
		  pract.addIdentifier().setUse(Identifier.IdentifierUse.OFFICIAL).setType(new CodeableConcept().addCoding(new Coding().setCode(registrationNumber)
					.setSystem("https://www.ahpra.gov.au/Registration/Registers-of-Practitioners.aspx").setDisplay("Registration/Billing number")));
		 
		  pract.addIdentifier().setUse(Identifier.IdentifierUse.OFFICIAL).setType(new CodeableConcept().addCoding(new Coding().setCode(payNumber)
					.setSystem("https://www.myhospital/employees").setDisplay("Hospital pay number")));
		  
		  pract.addAddress().addLine(street).setCity(city).setState(state).setPostalCode(postcode);
		  pract.setLanguage(language); 
		  

		  wPhone.setUse(ContactPointUse.WORK).setSystem(ContactPointSystem.PHONE).setValue(workPhone);
		  mobile.setUse(ContactPointUse.MOBILE).setSystem(ContactPointSystem.PHONE).setValue(mobilePhone);
		  hEmail.setUse(ContactPointUse.HOME).setSystem(ContactPointSystem.EMAIL).setValue(email);
		  
		  IGenericClient client = ctx.newRestfulGenericClient(serverURL);
		  MethodOutcome outcome = client.create()
	    		   .resource(pract)
	    		   .prettyPrint()
	    		   .encodedJson()
	    		   .execute();
		    
		   
		    message="";	
		    idT = (IdType) outcome.getId();
		    message= idT.getValue();
		    System.out.println("Patients' ID and history: " + idT.getValue());
		  
		// create a new XML parser and serialize our Patient object with it
	        //String encoded = ctx.newJsonParser().setPrettyPrint(true)
	                //.encodeResourceToString(pract);

	        //System.out.println(encoded);
	        
	        //encoded="";

}
		
public static void getAPractitioner(String practitionerId) throws IOException {	
			
			
			id="";
			version="";
			lastUpdate="";
			language="";
			gender="";
			birthDate="";
			
			prefix="";
			nameUse="";
			nameGiven=""; 
			familyName="";
			message="";
			
			street="";
			city="";
			state="";
			postcode="";
			
			
			idSystem.clear();
			idValue.clear();
			idDisplay.clear();
			
			telecomSystem.clear();
			telecomValue.clear();
			telecomUse.clear();
			qualification.clear();
			
			IGenericClient client = ctx.newRestfulGenericClient(serverURL);
			try {
			
		Practitioner outPut = client.read()
               .resource(Practitioner.class)
               .withId(practitionerId)  
               .execute(); 
			
		
			 
		if(outPut!=null) {
		//html=(outPut.getText().getDiv().toString());
		
		//System.out.println("Active= "+outPut.getActive());
		active=outPut.getActive();
		//System.out.println("Resource type= "+outPut.getResourceType().toString());
		resourceType=outPut.getResourceType().toString();
		//System.out.println("Id= "+outPut.getId().toString());
		id=outPut.getIdElement().getIdPart().toString();
		//System.out.println("Version= "+outPut.getMeta().getVersionId().toString());
		version=outPut.getMeta().getVersionId().toString();
		//System.out.println("Last updated= "+outPut.getMeta().getLastUpdated().toString());
		lastUpdate=outPut.getMeta().getLastUpdated().toString();
		//System.out.println("Language= "+outPut.getLanguage().toString());
		if (outPut.getLanguage()!=null) {
			language=outPut.getLanguage().toString();
		}else language="";
		//System.out.println("Gender= "+outPut.getGender().toString());
		
		gender=outPut.getGender().toString();
		//System.out.println("Id size= "+outPut.getIdentifier().size());
		
		
		
		if(outPut.getIdentifier().size()!=0) {
			for(int i =0;i<outPut.getIdentifier().size();i++) {		
			 if (outPut.getIdentifier().get(i).getType().getCoding().get(0).getSystem()!=null) {	
			//System.out.println(outPut.getIdentifier().get(i).getSystem().toString());
			idSystem.add(i,outPut.getIdentifier().get(i).getType().getCoding().get(0).getSystem().toString());
			 }//else idSystem.add(i,"NULL");
			 
			 if (outPut.getIdentifier().get(i).getType().getCoding().get(0).getCode()!=null) {
			//System.out.println(outPut.getIdentifier().get(i).getValue().toString());
			idValue.add(i,outPut.getIdentifier().get(i).getType().getCoding().get(0).getCode().toString());
			 }//else idValue.add(i,"NULL");
			 
			 if (outPut.getIdentifier().get(i).getType().getCoding().get(0).getDisplay()!=null) {
			//System.out.println(outPut.getIdentifier().get(i).getType().getCoding().get(0).getDisplay().toString());
			idDisplay.add(i,outPut.getIdentifier().get(i).getType().getCoding().get(0).getDisplay().toString());
			}//else idDisplay.add(i,"NULL");
			 
			 //if (outPut.getIdentifier().get(1).getType().getCoding().get(0).getSystem()!=null) {
			//System.out.println(outPut.getIdentifier().get(i).getSystem().toString());
			//idSystem.add(1,outPut.getIdentifier().get(1).getType().getCoding().get(0).getSystem().toString());
			// }else idSystem.add(1,"NULL");
			 
			// if (outPut.getIdentifier().get(1).getType().getCoding().get(0).getCode()!=null) {
			//System.out.println(outPut.getIdentifier().get(i).getValue().toString());
			//idValue.add(1,outPut.getIdentifier().get(1).getType().getCoding().get(0).getCode().toString());
			// }else idValue.add(1,"NULL");
			 
			// if (outPut.getIdentifier().get(1).getType().getCoding().get(0).getDisplay()!=null) {
			//System.out.println(outPut.getIdentifier().get(i).getType().getCoding().get(0).getDisplay().toString());
			//idDisplay.add(1,outPut.getIdentifier().get(1).getType().getCoding().get(0).getDisplay().toString());
			//}else idDisplay.add(1,"NULL");
			
			}
			}
		System.out.println("Id system getAPatient= "+idSystem);
		System.out.println("Id value getAPatient= "+idValue);
		System.out.println("Id display getAPatient= "+idDisplay);
		
		
		if (outPut.getQualification().size()!=0) {
		for(int i=0;i<outPut.getQualification().size();i++) {
		
				if(outPut.getQualification().get(i).getCode().getCoding().get(0).getDisplay()!=null) {
				qualification.add(i,outPut.getQualification().get(i).getCode().getCoding().get(0).getDisplay().toString());
				}//else qualificationDisplay.add(i,"NULL");
		}
		
				
			}
			
		
			
		
		
		
			prefix=outPut.getName().get(0).getPrefixAsSingleString();
			
			
			//System.out.println(outPut.getName().get(0).getFamily().toString());
			familyName=outPut.getName().get(0).getFamily().toString();			
			//System.out.println(outPut.getName().get(0).getUse().toString());
			//if (outPut.getName().get(1).getGivenAsSingleString()!="NULL") {
			//nameUse=outPut.getName().get(1).getGivenAsSingleString();
			//}else nameUse="NULL";
			//System.out.println(outPut.getName().get(0).getGivenAsSingleString());	//removes braces	
			nameGiven=outPut.getName().get(0).getGivenAsSingleString();	
		
		
		if(outPut.getTelecom().size()!=0) {
				
			for(int i=0;i<outPut.getTelecom().size();i++) {
			
				if(outPut.getTelecom().get(i).getSystem()!=null) {	
				telecomSystem.add(i,outPut.getTelecom().get(i).getSystem().toString());
				}
				
				if(outPut.getTelecom().get(i).getValue()!=null) {
				telecomValue.add(i,outPut.getTelecom().get(i).getValue().toString());
				}
				
				if(outPut.getTelecom().get(i).getUse()!=null) {
				telecomUse.add(i,outPut.getTelecom().get(i).getUse().toString());
				}
				
				//System.out.println(outPut.getTelecom().get(i).getSystem().toString());	
				//telecomSystem.add(1,outPut.getTelecom().get(1).getSystem().toString());
				//System.out.println(outPut.getTelecom().get(i).getValue().toString());
				//telecomValue.add(1,outPut.getTelecom().get(1).getValue().toString());
				//System.out.println(outPut.getTelecom().get(i).getUse().toString());
				//telecomUse.add(1,outPut.getTelecom().get(1).getUse().toString());
				
				//System.out.println(outPut.getTelecom().get(i).getSystem().toString());	
				//telecomSystem.add(2,outPut.getTelecom().get(2).getSystem().toString());
				//System.out.println(outPut.getTelecom().get(i).getValue().toString());
				//telecomValue.add(2,outPut.getTelecom().get(2).getValue().toString());
				//System.out.println(outPut.getTelecom().get(i).getUse().toString());
				//telecomUse.add(2,outPut.getTelecom().get(2).getUse().toString());
				
				//System.out.println(outPut.getTelecom().get(i).getSystem().toString());	
				//telecomSystem.add(3,outPut.getTelecom().get(3).getSystem().toString());
				//System.out.println(outPut.getTelecom().get(i).getValue().toString());
				//telecomValue.add(3,outPut.getTelecom().get(3).getValue().toString());
				//System.out.println(outPut.getTelecom().get(i).getUse().toString());
				//telecomUse.add(3,outPut.getTelecom().get(3).getUse().toString());
			
				
				//if (outPut.getTelecom().get(1).getSystem()!=null) {
				//System.out.println(outPut.getTelecom().get(i).getSystem().toString());	
				//telecomSystem.add(1,outPut.getTelecom().get(1).getSystem().toString());
				//}else telecomSystem.add(1,"NULL");
				
				//if (outPut.getTelecom().get(1).getValue()!=null) {
				//System.out.println(outPut.getTelecom().get(i).getValue().toString());
				//telecomValue.add(1,outPut.getTelecom().get(1).getValue().toString());
				//}else telecomValue.add(1,"NULL");
				
				//if (outPut.getTelecom().get(1).getUse()!=null) {
				//System.out.println(outPut.getTelecom().get(i).getUse().toString());
				//telecomUse.add(1,outPut.getTelecom().get(1).getUse().toString());
				//}else telecomUse.add(1,"NULL");
				
				//if (outPut.getTelecom().get(2).getSystem()!=null) {
				//System.out.println(outPut.getTelecom().get(i).getSystem().toString());	
				//telecomSystem.add(2,outPut.getTelecom().get(2).getSystem().toString());
				//}else telecomSystem.add(2,"NULL");
				
				//if (outPut.getTelecom().get(2).getValue()!=null) {
				//System.out.println(outPut.getTelecom().get(i).getValue().toString());
				//telecomValue.add(2,outPut.getTelecom().get(2).getValue().toString());
				//}else telecomValue.add(2,"NULL");
				
				//if (outPut.getTelecom().get(2).getUse()!=null) {
				//System.out.println(outPut.getTelecom().get(i).getUse().toString());
				//telecomUse.add(2,outPut.getTelecom().get(2).getUse().toString());
				//}else telecomUse.add(2,"NULL");
				
		}

		}
		
		System.out.println("Telecom system getAPatient= "+telecomSystem);
		System.out.println("Telecom value getAPatient= "+telecomValue);
		System.out.println("telecom use getAPatient= "+telecomUse);
		
			
			//System.out.println(outPut.getAddress().get(i).getLine().toString());
			String str=outPut.getAddress().get(0).getLine().toString();
			
			removeBraces(str);
			////System.out.println(outPut.getAddress().get(i).getCity().toString());
			city=outPut.getAddress().get(0).getCity().toString();
			//System.out.println(outPut.getAddress().get(i).getState().toString());
			state=outPut.getAddress().get(0).getState().toString();
			//System.out.println(outPut.getAddress().get(i).getPostalCode().toString());
			postcode=outPut.getAddress().get(0).getPostalCode().toString();
		}
		}catch (ResourceNotFoundException e) {
			
			message= e.getMessage();
			
			//System.out.println("An issue "+oo.getIssue().get(0).getDiagnostics().toString());
		}catch (ResourceGoneException e) {
			message= e.getMessage();
		}catch (IllegalArgumentException e ) {
			message= e.getMessage();
		}
}

		
public static void listAPatient(String famName) throws IOException {
			//clear patient details
			patientListId.clear();
			patientListFamilyName.clear();
			patientListGiven.clear();
			patientListStreet.clear();
			patientListTown.clear();
			message="";
			
			IGenericClient client = ctx.newRestfulGenericClient(serverURL);
			   Bundle response = client.search()
			      .forResource(Patient.class)
			     .where(Patient.FAMILY.matches().value(famName))
			      //.and(Patient.BIRTHDATE.before().day("2014-01-01"))
			      .count(100)
			      .returnBundle(Bundle.class)
			      .execute();
			
			 
			   
			   if(response.getTotal()!=0) {
				   
	for(int i=0;i<response.getTotal();i++) {
		Patient pt = new Patient();
		pt=(Patient) response.getEntry().get(i).getResource();
		
		//System.out.println(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(pt));
		
		//String encoded = ctx.newJsonParser().setPrettyPrint(true)
	                //.encodeResourceToString(response);

	      //  //System.out.println(encoded);
		if(pt.getIdElement().getIdPart()!=null) {
		patientListId.add(pt.getIdElement().getIdPart().toString());	
		}
		
		if(pt.getName().get(0).getFamily()!=null) {
		patientListFamilyName.add(pt.getName().get(0).getFamily().toString());	
		}else patientListFamilyName.add("NULL");
		
		if(pt.getName().get(0).getGivenAsSingleString()!=null) {
		patientListGiven.add(pt.getName().get(0).getGivenAsSingleString());
		}else patientListGiven.add("NULL");
		
		if(pt.getAddress().get(0).getLine()!=null) {
		patientListStreet.add(pt.getAddress().get(0).getLine().toString());
		}else patientListStreet.add("NULL");
		
		if(pt.getAddress().get(0).getCity()!=null) {
		patientListTown.add(pt.getAddress().get(0).getCity().toString());
		}else patientListTown.add("NULL");
	}


			   // How many resources did we find?
			   //System.out.println("Responses: " + response.getTotal());
			   
			   //System.out.println(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(response));
				
				//String encoded = ctx.newJsonParser().setPrettyPrint(true)
			               // .encodeResourceToString(response);

			       // //System.out.println(encoded);
			   
			   }else {
			   
				   message="Nothing to show";
				   
			   }
		}
		
public static void listPractitioners() throws IOException {
			 practitionerListId.clear();
			practitionerListFamilyName.clear();
			practitionerListGiven.clear();
			practitionerListDiscipline.clear();
			message="";
			IGenericClient client = ctx.newRestfulGenericClient(serverURL);
			 Bundle response = client.search()
				      .forResource(Practitioner.class)				     
				      .count(100)
				      .returnBundle(Bundle.class)
				      .execute();
			 
			
			 
			 if(response!=null) {				   
					
					for(int i=0;i<response.getTotal();i++) {
						Practitioner prac = new Practitioner();
					prac=(Practitioner) response.getEntry().get(i).getResource();
					//practitionerData.put( prac.getIdElement().getIdPart().toString(),(prac.getName().get(0).getFamily().toString())+" "+prac.getName().get(0).getGivenAsSingleString());
					
					
					practitionerListId.add(prac.getIdElement().getIdPart().toString());	
					
					if(prac.getName().get(0).getFamily()!=null) {
					practitionerListFamilyName.add(prac.getName().get(0).getFamily().toString());
					}
					
					if(prac.getName().get(0).getGivenAsSingleString()!=null) {
					practitionerListGiven.add(prac.getName().get(0).getGivenAsSingleString());
					}
					//practitionerListDiscipline.put(prac.getIdElement().getIdPart().toString(), prac.getIdElement().getIdPart().toString());	
					//practitionerListDiscipline.put(prac.getIdElement().getIdPart().toString(), prac.getName().get(0).getGivenAsSingleString());
					//practitionerListDiscipline.put(prac.getIdElement().getIdPart().toString(), prac.getName().get(0).getFamily().toString());
					
					if (prac.getQualification().size()!=0) {
					for(int a=0;a<prac.getQualification().size();a++) {
					
						if(prac.getQualification().get(a).getCode().getCoding().get(0).getDisplay()!=null) {
						
									//practitionerData.put(prac.getQualification().get(a).getCode().getCoding().get(0).getDisplay().toString(),prac.getIdElement().getIdPart().toString());
									//System.out.println(prac.getQualification().get(a).getCode().getCoding().get(0).getDisplay().toString());
									
								
							practitionerListDiscipline.put(prac.getIdElement().getIdPart().toString(), prac.getQualification().get(a).getCode().getCoding().get(0).getDisplay().toString());
						}
						
						
						
						
		 }
					//values.clear();
		
			}else practitionerListDiscipline.put(prac.getIdElement().getIdPart().toString(),"NULL");
			
		 }
		 }else 	System.out.println("Nothing to show");
			 
			 //for(int i=0;i<response.getTotal();i++) {
					
					//Practitioner prac = new Practitioner();
					// prac=(Practitioner) response.getEntry().get(i).getResource();
					 
					// System.out.println("");
					//System.out.println(prac.getIdElement().getIdPart().toString());	
					//System.out.println(prac.getName().get(0).getFamily().toString());	
				      
					//System.out.println(prac.getName().get(0).getGivenAsSingleString());
					
					
				//for (Entry<String, String> entry : practitionerListDiscipline.entries()) {
					
					//if(prac.getIdElement().getIdPart().toString()==entry.getKey()) {
					 
						
						
								
						// System.out.println("length = " + entry.getValue().length());	
					   // System.out.println("Qualification = " + entry.getValue());
						
					    
						//}
				 //}
//} 				
			 }

		
public static void deleteAPatient(String id) throws IOException {
			//IBaseOperationOutcome resp = client.delete().resourceById(new IdType("Patient", id)).execute();
	IGenericClient client = ctx.newRestfulGenericClient(serverURL);
			IBaseOperationOutcome resp = client.delete().resourceById(new IdDt("Patient", id)).execute();
			
			 if (resp != null) {
			     OperationOutcome outcome = (OperationOutcome) resp;
			     message=outcome.getIssue().get(0).getDiagnostics();
			   //System.out.println(outcome.getIssue().get(0).getDiagnostics().toString());
			     //System.out.println(outcome.getIssueFirstRep().getCodeElement().getValue());
			     //System.out.println(outcome.getText().getDiv());
			}
			
		}

public static void deleteAPractitioner(String id) throws IOException {
	IGenericClient client = ctx.newRestfulGenericClient(serverURL);
	IBaseOperationOutcome resp = client.delete().resourceById(new IdDt("Practitioner", id)).execute();
	
	 if (resp != null) {
	     OperationOutcome outcome = (OperationOutcome) resp;
	     message=outcome.getIssue().get(0).getDiagnostics();
	   //System.out.println(outcome.getIssue().get(0).getDiagnostics().toString());
	     //System.out.println(outcome.getIssueFirstRep().getCodeElement().getValue());
	     //System.out.println(outcome.getText().getDiv());
	}
	
}
		
public static void updateAPatient(String patientId, String active, String prefix, String familyName, String nameGiven, String nameUsual, 
				  String ptGender, String birthDate, ArrayList<String>idValue, ArrayList<String>idDisplay, String maritalStatus,
				  ArrayList<String>practitioner, String street, String city, String state, String postcode, String language, 
				  ArrayList<String>telecomValue, String contactRelationshipCodePrimary, String contactRelationshipSystemPrimary,
				  String contactRelationshipNamePrimary, ArrayList<String>contactTelecomSystemPrimary, ArrayList<String>contactTelecomValuePrimary, 
				  ArrayList<String>contactTelecomUsePrimary, String contactRelationshipCodeSecondary, String contactRelationshipSystemSecondary,
				  String contactRelationshipNameSecondary, ArrayList<String>contactTelecomSystemSecondary, ArrayList<String>contactTelecomValueSecondary, 
				  ArrayList<String>contactTelecomUseSecondary) throws IOException {
			Patient patient = new Patient();
			ContactComponent primary=patient.addContact();	
			ContactComponent secondary=patient.addContact();
			 message="";
			 patient.setDeceased(null);
			 //System.out.println("Patient id in Searchpatient.updateAPatient.java "+patientId);
			patient.setId(patientId);
	        patient.addName().setUse(HumanName.NameUse.OFFICIAL)
	                .addPrefix(prefix).setFamily(familyName).addGiven(nameGiven);
	        patient.addName().setUse(HumanName.NameUse.USUAL).addGiven(nameUsual);
	        patient.setActive(Boolean.parseBoolean(active));
	        patient.setGender(AdministrativeGender.valueOf(ptGender));
	        
	        patient.setBirthDateElement(new DateType(birthDate));
	        
	        
	        if(idValue.size()!=0) {
	            for (int i=0;i<idValue.size();i++) {
	            	patient.addIdentifier()
	            	.setType(new CodeableConcept().addCoding(new Coding().setSystem("http://www.ontohealth.com.au")
	            			.setCode(idValue.get(i)).setDisplay(idDisplay.get(i))));
	            }		
	           }
	       
	           //System.out.println("ids= "+idValue);
	           //System.out.println("id display= "+idDisplay);
	       
	        patient.addAddress().addLine(street).setCity(city).setState(state).setPostalCode(postcode);
	        patient.setLanguage(language);  
	        
	        patient.setMaritalStatus(new CodeableConcept().addCoding(new Coding().setCode("365581002").setDisplay(maritalStatus)
					.setSystem("http://purl.bioontology.org/ontology/SNOMEDCT/")));
	        
	        for (int i=0;i<practitioner.size();i++) {
	        patient.addGeneralPractitioner(new Reference(new IdType((String) practitioner.get(i))));
	        }
	        
	        //System.out.println("Practitioner= "+practitioner);
	       
	        ContactPoint hPhone = patient.addTelecom();	        
	        ContactPoint wPhone = patient.addTelecom();	       
	        ContactPoint mobile = patient.addTelecom();
	        ContactPoint hEmail = patient.addTelecom();
	              
			hPhone.setUse(ContactPointUse.HOME).setSystem(ContactPointSystem.PHONE).setValue(telecomValue.get(0));
			wPhone.setUse(ContactPointUse.WORK).setSystem(ContactPointSystem.PHONE).setValue(telecomValue.get(1));			
			hEmail.setUse(ContactPointUse.MOBILE).setSystem(ContactPointSystem.PHONE).setValue(telecomValue.get(2));
			mobile.setUse(ContactPointUse.HOME).setSystem(ContactPointSystem.EMAIL).setValue(telecomValue.get(3));
			
			System.out.println("Home phone in update patient= "+telecomValue.get(0));
			System.out.println("Work phone in update patient= "+telecomValue.get(1));
			System.out.println("Mobile phone in update patient= "+telecomValue.get(2));
			System.out.println("Email in update patient= "+telecomValue.get(3));
			
			//contacts seem only to work if they are seperated entities...
				 
					
				primary.setName(new HumanName().addGiven(contactRelationshipNamePrimary));				
				primary.addRelationship(new CodeableConcept().addCoding(new Coding().setCode(contactRelationshipCodePrimary)
					.setSystem("http://purl.bioontology.org/ontology/SNOMEDCT/125677006")));
				
				secondary.setName(new HumanName().addGiven(contactRelationshipNameSecondary));
				secondary.addRelationship(new CodeableConcept().addCoding(new Coding().setCode(contactRelationshipCodeSecondary)
						.setSystem("http://purl.bioontology.org/ontology/SNOMEDCT/125677006")));
				
			
			
			
			primary.addTelecom().setUse(ContactPointUse.HOME).setSystem(ContactPointSystem.PHONE).setValue(contactTelecomValuePrimary.get(0));
			primary.addTelecom().setUse(ContactPointUse.WORK).setSystem(ContactPointSystem.PHONE).setValue(contactTelecomValuePrimary.get(1));
			primary.addTelecom().setUse(ContactPointUse.MOBILE).setSystem(ContactPointSystem.PHONE).setValue(contactTelecomValuePrimary.get(2));
			secondary.addTelecom().setUse(ContactPointUse.HOME).setSystem(ContactPointSystem.PHONE).setValue(contactTelecomValueSecondary.get(0));
			secondary.addTelecom().setUse(ContactPointUse.WORK).setSystem(ContactPointSystem.PHONE).setValue(contactTelecomValueSecondary.get(1));
			secondary.addTelecom().setUse(ContactPointUse.MOBILE).setSystem(ContactPointSystem.PHONE).setValue(contactTelecomValueSecondary.get(2));
			
			
			
			
			//System.out.println("Contact relationship code in update patient mat= "+contactRelationshipCode.get(0));
			//System.out.println("Contact relationship name in update patient mat= "+contactRelationshipName.get(0));
			
			//System.out.println("Contact relationship code in update patient erin= "+contactRelationshipCode.get(1));
			//System.out.println("Contact relationship name in update patient erin= "+contactRelationshipName.get(1));
			
			//System.out.println("Contact telecom value in update patient= "+contactTelecomValue);
			
			IGenericClient client = ctx.newRestfulGenericClient(serverURL);
			MethodOutcome outcome = client.update()
		    		   .resource(patient)
		    		   .prettyPrint()
		    		   .encodedJson()
		    		   .execute();
			
				
			    idType = (IdType) outcome.getId();
			    message= idType.getValue();
			    //System.out.println("Patients' ID and history: " + idType.getValue());
		}


public static void updateAPractitioner(String practitionerId, String active, String prefix, String familyName, String nameGiven, 
		  String ptGender,ArrayList<String>idValue, ArrayList<String>idDisplay, String street, 
		  String city, String state, String postcode, String language, ArrayList<String>telecomValue, ArrayList<String>qualification) throws IOException {
	
	Practitioner prac = new Practitioner();
	
	 message="";
	 
	 //System.out.println("Patient id in Searchpatient.updateAPatient.java "+patientId);
	prac.setId(practitionerId);
    prac.addName().setUse(HumanName.NameUse.OFFICIAL)
            .addPrefix(prefix).setFamily(familyName).addGiven(nameGiven);
   
    prac.setActive(Boolean.parseBoolean(active));
    prac.setGender(AdministrativeGender.valueOf(ptGender));
    
    //for (int i=0;i<idValue.size();i++) {
    	//prac.addIdentifier()
       // .setSystem("http://www.ontohealth.com.au")
    	// .setValue((String) idValue.get(i))
           // .setType(new CodeableConcept().addCoding(new Coding().setDisplay(idDisplay.get(i))));
   // }
    
    if(idValue.size()!=0) {
    for (int i=0;i<idValue.size();i++) {
    	prac.addIdentifier()
    	.setType(new CodeableConcept().addCoding(new Coding().setSystem("http://www.ontohealth.com.au")
    			.setCode(idValue.get(i)).setDisplay(idDisplay.get(i))));
    }		
   }
   
    if(qualification.size()!=0) {
    for (int i=0;i<qualification.size();i++) {
    	prac.addQualification()
    	.setCode(new CodeableConcept().addCoding(new Coding().setSystem("http://www.ontohealth.com.au")
    			.setCode("543654").setDisplay(qualification.get(i))));
    			
   }
    }
       //System.out.println("ids= "+idValue);
       //System.out.println("id display= "+idDisplay);
   
    prac.addAddress().addLine(street).setCity(city).setState(state).setPostalCode(postcode);
    prac.setLanguage(language); 
    
    	        
    ContactPoint wPhone = prac.addTelecom();	       
    ContactPoint mobile = prac.addTelecom();
    ContactPoint hEmail = prac.addTelecom();
          
    
	wPhone.setUse(ContactPointUse.WORK).setSystem(ContactPointSystem.PHONE).setValue(telecomValue.get(0));
	mobile.setUse(ContactPointUse.HOME).setSystem(ContactPointSystem.EMAIL).setValue(telecomValue.get(1));
	hEmail.setUse(ContactPointUse.MOBILE).setSystem(ContactPointSystem.PHONE).setValue(telecomValue.get(2));
	IGenericClient client = ctx.newRestfulGenericClient(serverURL);
	MethodOutcome outcome = client.update()
 		   .resource(prac)
 		   .prettyPrint()
 		   .encodedJson()
 		   .execute();
	
		
	    idType = (IdType) outcome.getId();
	    message= idType.getValue();
	
}
		
public static void removeBraces(String braces) {
			int indexOfOpenBracket = braces.indexOf("[");
			int indexOfLastBracket = braces.lastIndexOf("]");
			street=braces.substring(indexOfOpenBracket+1, indexOfLastBracket);
			System.out.println("Line without braces= "+street);
		}

public static String readServerURL() {
	
	 File file = new File("C:/Users/Phil standard/My Documents/ServerURL.txt");
	//File file=new File("/home/phil/fhir/ServerURL.txt");
	 message="";
     BufferedReader br;
     String serverURL="";
     String st="";
	try {
		br = new BufferedReader(new FileReader(file));		 
		while((st=br.readLine()) != null){
			serverURL=st; 
			} 
    
			br.close();
			
	} catch (FileNotFoundException e) {
		message=e.getMessage();
	} catch (IOException e) {
		message=e.getMessage();
	}
	return serverURL;

}

}
		



	



