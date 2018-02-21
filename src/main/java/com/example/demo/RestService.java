package com.example.demo;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.springframework.http.HttpStatus;
//import org.springframework.http.RequestEntity;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

class Details{
	String username, password;
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setUsername(String username) {
		this.username = username ;
	}
	
	public void setRegisnumber(String password) {
		this.password = password;
	}
}

@RestController
@RequestMapping("/doctor")
public class RestService {	
	
	@RequestMapping(value = "/loginCredentials", method = RequestMethod.POST)
	public HashMap<String, String> hell(@RequestBody Details details) {
		
		
		try {
			
			Database obj = new Database();
			HashMap<String, String> hm = obj.getDoctorAction(details.username, details.password);
			return hm;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(details.username);	
		Map<String,String> model = new HashMap<String,String>();
	    
	    return null;
	}

	// Register doctor
	@RequestMapping(value = "/registerDoctor", method = RequestMethod.POST)
	public Map<String,String> registerDoctor(@RequestBody DoctorDetails details) {


//		System.out.println(details.doctorName);
		Map<String,String> model = new HashMap<String,String>();
		Database dbObj = new Database();
		try {
			String response = dbObj.addDoctorInfo(details);
			model.put("messgage", response);
		    return model;		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("message", "Bad Data ! Refill the form with Correct Info");
			return model;
		}
				    
	}
	
	@RequestMapping(value = "/registerPatient", method = RequestMethod.POST)
	public Map<String,String> registerPatient(@RequestBody HashMap<String, String> details) {
		
		System.out.println(details.toString());
		

//		System.out.println(details.doctorDepartment);
		Map<String,String> model = new HashMap<String,String>();
		Database dbObj = new Database();
		try {
			String response = dbObj.addPatientInfo(details);
			model.put("message", response);
		    return model;		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("message", "Bad Data ! Refill the form with Correct Info");
			return model;
		}
				    
	}
	
	
	@RequestMapping(value = "/fetchReferral", method = RequestMethod.POST)
	public List<HashMap<String, String>> fetchEmergencyReferral(@RequestBody HashMap<String,String> req) {
		
		Database dbObj = new Database();
		try {
			
			if(req.get("message").equals("OPD")) {
				return dbObj.getOpdInfo();
			}else if(req.get("message").equals("Emergency")){
				return dbObj.getEmergencyInfo();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				    
		return null;
	}

	@RequestMapping(value = "/fetchPatient", method = RequestMethod.POST)
	public HashMap<String, String> fetchpatient(@RequestBody HashMap<String, String> hm) {
		
		Database dbObj = new Database();
		try {
			return dbObj.getPatientInfo(hm.get("message"));
		} catch (Exception e) {
			e.printStackTrace();
		}
				    
		return null;
	}

	
	@RequestMapping(value = "/updateFeedback", method = RequestMethod.POST)
	public HashMap<String,String> updateFeedback(@RequestBody HashMap<String, String> details) {

		System.out.println(details.toString());
		
//		System.out.println(details.doctorName);
	
		Database dbObj = new Database();
		try {
			return dbObj.updateFeedback(details);	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return null;
		}
		
		
				    
	}
	
}


