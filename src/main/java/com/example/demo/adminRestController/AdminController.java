package com.example.demo.adminRestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Database;

import org.springframework.web.bind.annotation.RequestMethod;


 class DetailMessage{
	String message;
	
	public String getMessage() {
		return message;
	}
	
	public void setUsername(String message) {
		this.message = message ;
	}

}


@RestController
@RequestMapping("/admin")
public class AdminController {
	@RequestMapping(value = "/fetchDoctor", method = RequestMethod.POST)
	public List<HashMap<String, String>> fetchDoctors(){
		Database obj = new Database();
		return obj.getDoctorInfo();
	}
	
	@RequestMapping(value = "/toggleAction", method = RequestMethod.POST)
	public HashMap<String, String> toggleAction(@RequestBody DetailMessage req){
		
		Database obj = new Database();
		String res = null;
		try {
			res = obj.updateDoctorAction(req.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(req);
//		
		HashMap<String, String> h_obj = new HashMap<String, String>();
		h_obj.put("message", res);
		return h_obj;
}

	@RequestMapping(value = "/fetchDepartment", method = RequestMethod.POST)
	public HashMap<String, List<String>> fetchDepartment(){
		
		Database obj = new Database();
		String res = null;
		try {
			return obj.getDepartment();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/addDepartment", method = RequestMethod.POST)
	public HashMap<String, String> addDepartment(@RequestBody DetailMessage req){
		
		Database obj = new Database();
		String res = null;
		try {
			HashMap<String, String> hm = new HashMap<>();
			hm.put("message",obj.addDepartment(req.getMessage()));
			return hm;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/fetchReason", method = RequestMethod.POST)
	public HashMap<String, List<String>> fetchReason(){
		
		Database obj = new Database();
		String res = null;
		try {
			return obj.getReason();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/addReason", method = RequestMethod.POST)
	public HashMap<String, String> addReason(@RequestBody DetailMessage req){
		
//		System.out.println(req.message);
		Database obj = new Database();
		String res = null;
		try {
			HashMap<String, String> hm = new HashMap<>();
			hm.put("message",obj.addReason(req.getMessage()));
			return hm;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/deleteReason", method = RequestMethod.POST)
	public HashMap<String, String> deleteReason(@RequestBody DetailMessage req){
		
//		System.out.println(req.message);
		Database obj = new Database();
		String res = null;
		try {
			HashMap<String, String> hm = new HashMap<>();
			hm.put("message",obj.deleteReason(req.getMessage()));
			return hm;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/deleteDepartment", method = RequestMethod.POST)
	public HashMap<String, String> deleteDepartment(@RequestBody DetailMessage req){
		
//		System.out.println(req.message);
		Database obj = new Database();
		String res = null;
		try {
			HashMap<String, String> hm = new HashMap<>();
			hm.put("message",obj.deleteDepartment(req.getMessage()));
			return hm;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


}
