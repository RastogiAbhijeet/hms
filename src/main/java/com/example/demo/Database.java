package com.example.demo;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Database {
	
		Connection conn;
		
		public Database()
		  {
		    try
		    {
		    	System.out.println("haha");
		    	conn = DriverManager.getConnection("jdbc:mysql://localhost/hms","root","");
		    }
		    catch (Exception ex) {System.err.println(ex.getMessage());}
		  }

		public String addDoctorInfo(HashMap<String, String> doctorObj){
//			System.out.println(doctorObj.doctorSpeciality);
			// the mysql insert statement
		    String query1 = "INSERT INTO doctor_table (FirstName, LastName, RegNo, Email, MobileNo, Password, DoctorType, HospitalName, HospitalCity, HospitalDistrict, HospitalState, HospitalZipcode, Speciality, Acknowledgement)"
		        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		    
//		     create the mysql insert preparedstatement
		    PreparedStatement preparedStmt1;
			try {
				preparedStmt1 = conn.prepareStatement(query1);
				preparedStmt1.setString (1, doctorObj.get("firstName"));
			    preparedStmt1.setString (2, doctorObj.get("lastName"));
			    preparedStmt1.setString (3, doctorObj.get("regNo"));
			    preparedStmt1.setString (4, doctorObj.get("email"));
			    preparedStmt1.setString (5, doctorObj.get("mobileNo"));
			    preparedStmt1.setString (6, doctorObj.get("password"));
			    preparedStmt1.setString (7, doctorObj.get("doctorType"));
			    preparedStmt1.setString (8, doctorObj.get("hospitalName"));
			    preparedStmt1.setString (9, doctorObj.get("hospitalCity"));
			    preparedStmt1.setString (10, doctorObj.get("hospitalDistrict"));
			    preparedStmt1.setString (11, doctorObj.get("hospitalState"));
			    preparedStmt1.setString (12, doctorObj.get("hospitalZipcode"));
			    preparedStmt1.setString (13, doctorObj.get("doctorSpeciality"));
			    preparedStmt1.setString (14, "");
			    
			    // execute the preparedstatement
			    preparedStmt1.execute();
			    
			   		    
			    //ADDING EMAIL PASSWORD ETC TO DOCTOR_ACTION_TABLE
			    // the mysql insert statement
			    String query2 = "INSERT INTO doctor_action_table (Email, Password, RegNo, Action, DoctorType)"
			        + " values (?, ?, ?, ?,?)";
			    // create the mysql insert preparedstatement
			    PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
			    preparedStmt2.setString (1, doctorObj.get("email"));
			    preparedStmt2.setString (2, doctorObj.get("password"));
			    preparedStmt2.setString (3, doctorObj.get("regNo"));
			    preparedStmt2.setString (4, "Enable"); //by default, set to false
			    preparedStmt2.setString (5, doctorObj.get("doctorType"));
			    
			    // execute the preparedstatement
			    preparedStmt2.execute();
			    
			    return "Doctor Registered";
			    
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
				return "Unsuccessful Registration ! Maybe Data already Exists.. Contact Admin";
			}
		    
//		    getDoctorInfo();
		}

		public List<HashMap<String, String>> getDoctorInfo() {
			List<HashMap<String, String>> list = new ArrayList<>();
			//create a statement
			Statement stmt;
			try {
				stmt = conn.createStatement();
				//create a query
				String query = "SELECT * FROM doctor_table JOIN doctor_action_table USING (Email)";
				//execute SQL query
				ResultSet rs = stmt.executeQuery(query);
				//process the result set
				while(rs.next()) {
					HashMap<String, String> obj = new HashMap<>();
					obj.put("Name",rs.getString("FirstName")+" "+(rs.getString("LastName")));
					obj.put("Registration Number",rs.getString("RegNo"));
					obj.put("Email",rs.getString("Email"));
					obj.put("Doctor Type",rs.getString("DoctorType"));
					obj.put("Actions",rs.getString("Action"));
					obj.put("Mobile",rs.getString("MobileNo"));
					obj.put("Speciality", rs.getString("Speciality"));
				
					list.add(obj);
					
					System.out.println(obj.toString());
				}
				return list;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			
		}

		public String addPatientInfo(HashMap<String, String> abc) {
			
			try {
				System.out.println(abc.get("arrivalDate"));
				
			// the mysql insert statement
		    String query = "INSERT INTO patient_referral_table (DoctorName, ReferredFrom, Department, Date, Time, PatientName, ArrivalTime, Age,ReferralReason, ProvisionalDiagnosis, Comment, TreatmentGiven, Oxygen, Ventilator, Inotropes, AmbulanceProvided, TrainedProf, PGIAction, HospitalDepartment, ExpectedArrivalDate)"
		        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		    // create the mysql insert preparedstatement
		    PreparedStatement preparedStmt = conn.prepareStatement(query);
		    preparedStmt.setString (1, abc.get("doctorName"));
		    preparedStmt.setString (2, abc.get("referredFrom"));
		    preparedStmt.setString (3, abc.get("doctorDepartment"));
		    preparedStmt.setString (4, abc.get("date"));
		    preparedStmt.setString (5, abc.get("time"));
		    preparedStmt.setString (6, abc.get("patientName"));
		    preparedStmt.setString (7, abc.get("arrivalTime"));
		    preparedStmt.setString (8, abc.get("age"));
		    preparedStmt.setString (9, abc.get("referralReason"));
		    preparedStmt.setString (10, abc.get("provisionalDiagnosis"));
		    preparedStmt.setString (11, abc.get("comment"));
		    preparedStmt.setString (12, abc.get("treatmentGiven"));
		    preparedStmt.setString (13, abc.get("oxygen"));
		    preparedStmt.setString (14, abc.get("ventilator"));
		    preparedStmt.setString (15, abc.get("inotropes"));
		    preparedStmt.setString (16, abc.get("ambulance"));
		    preparedStmt.setString (17, abc.get("trainedProfessional"));
		    preparedStmt.setString (18, "Not Acknowledged");
		    preparedStmt.setString (19, abc.get("hospitalDepartment"));
		    preparedStmt.setString (20, abc.get("arrivalDate"));
		    
		    
		    
		    // execute the preparedstatement
		    preparedStmt.execute();
			
		    	return "Patient Referred";
		    	
		    }catch(Exception e) {
		    	e.printStackTrace();
		    	return "Bad Data"; 
		    }
			
		}
		
		public List<HashMap<String, String>> getPatientInfo() throws Exception {
			
			List<HashMap<String, String>> list = new ArrayList<>();
			
			//create a statement
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM patient_referral_table";
			ResultSet rs = stmt.executeQuery(query);
			//process the result set
			while(rs.next()) {
		
				HashMap<String, String> obj = new HashMap<>();
				obj.put("Patient Name",rs.getString("PatientName"));
				obj.put("Referral Date",rs.getString("Date"));
				obj.put("Provided Diagnosis",rs.getString("ProvisionalDiagnosis"));
				obj.put("Acknowledgement",rs.getString("Acknowledgement"));
				obj.put("PGI Feedback",rs.getString("Feedback"));			
				list.add(obj);				
			}
			
			return list;
		}
		
		public List<HashMap<String, String>> getEmergencyInfo() throws Exception {
			
			List<HashMap<String, String>> list = new ArrayList<>();
			
			//create a statement
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM patient_referral_table WHERE HospitalDepartment='Emergency'";
			ResultSet rs = stmt.executeQuery(query);
			//process the result set
			while(rs.next()) {
		
				HashMap<String, String> obj = new HashMap<>();
				obj.put("Patient Name",rs.getString("PatientName"));
				obj.put("Date",rs.getString("Date"));
				obj.put("Referral Time",rs.getString("Time"));
				obj.put("Expected Arrival Time",rs.getString("ArrivalTime"));
				obj.put("Provisional Diagnosis", rs.getString("ProvisionalDiagnosis"));
				obj.put("PGI Feedback",rs.getString("Feedback"));	
				obj.put("Doctor Name", rs.getString("DoctorName"));
				obj.put("Referred From", rs.getString("ReferredFrom"));
				obj.put("Acknowledgement", rs.getString("Acknowledgement"));
				obj.put("PGI Action", rs.getString("PGIAction"));
				obj.put("Is Admitted", rs.getString("IsAdmitted"));
				obj.put("Expected Arrival Date", rs.getString("ExpectedArrivalDate"));
				obj.put("Hospital Department", rs.getString("HospitalDepartment"));
				list.add(obj);				
			}
			
			return list;
		}
		
		public List<HashMap<String, String>> getOpdInfo() throws Exception {
			
			List<HashMap<String, String>> list = new ArrayList<>();
			
			//create a statement
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM patient_referral_table WHERE HospitalDepartment='OPD'";
			ResultSet rs = stmt.executeQuery(query);
			//process the result set
			while(rs.next()) {
		
				HashMap<String, String> obj = new HashMap<>();
				obj.put("Patient Name",rs.getString("PatientName"));
				obj.put("Date",rs.getString("Date"));
				obj.put("Expected Arrival Date",rs.getString("ExpectedArrivalDate"));
				obj.put("Provisional Diagnosis", rs.getString("ProvisionalDiagnosis"));
				obj.put("PGI Feedback",rs.getString("Feedback"));	
				obj.put("Doctor Name", rs.getString("DoctorName"));
				obj.put("Referred From", rs.getString("ReferredFrom"));
				obj.put("Acknowledgement", rs.getString("Acknowledgement"));
				obj.put("PGI Action", rs.getString("PGIAction"));
				obj.put("Is Admitted", rs.getString("IsAdmitted"));
				obj.put("Expected Arrival Date", rs.getString("ExpectedArrivalDate"));
				obj.put("Hospital Department", rs.getString("HospitalDepartment"));
				obj.put("Speciality", rs.getString("Department"));
				list.add(obj);				
			}
			
			return list;
		}
		
		public HashMap<String, String> getPatientInfo(String patientName) throws Exception {
			//create a statement
//			Statement stmt = conn.createStatement();
			//create a query
			
			String query = "SELECT * FROM patient_referral_table where PatientName = ?";
			
			PreparedStatement preparedStmt = conn.prepareStatement(query);
		    preparedStmt.setString (1, patientName);
		    
			ResultSet rs = preparedStmt.executeQuery();
			//process the result set
			
			HashMap<String, String> obj = new HashMap<>();
			while(rs.next()) {
				
				obj.put("Patient Name", rs.getString("PatientName"));
				obj.put("Doctor Name", rs.getString("DoctorName"));
				obj.put("Referred From", rs.getString("ReferredFrom"));
				obj.put("Referral Date", rs.getString("Date"));
				obj.put("Department", rs.getString("Department"));
				obj.put("Time", rs.getString("Time"));
				obj.put("Arrival Time", rs.getString("ArrivalTime"));
				obj.put("Comment", rs.getString("Comment"));
				obj.put("Age", rs.getString("Age"));
				obj.put("Referral Reason", rs.getString("ReferralReason"));
				obj.put("Provisional Diagnosis", rs.getString("ProvisionalDiagnosis"));
				obj.put("Treatment Given", rs.getString("TreatmentGiven"));
				obj.put("Oxygen", rs.getString("Oxygen"));
				obj.put("Ventilator", rs.getString("Ventilator"));			
				obj.put("Inotropes", rs.getString("inotropes"));
				obj.put("Ambulance Provided", rs.getString("AmbulanceProvided"));
				obj.put("Trained Prof", rs.getString("TrainedProf"));
				obj.put("Acknowledgement", rs.getString("Acknowledgement"));
				obj.put("PGI Feedback", rs.getString("Feedback"));
				obj.put("Arrival Date", rs.getString("ExpectedArrivalDate"));
				obj.put("Is Admitted", rs.getString("IsAdmitted"));
				
				
			}
			
			return obj;
		}
		
		public String addDepartment(String dept) throws Exception {
			// the mysql insert statement
		    String query = "INSERT INTO department_table (Department)"
		        + " values (?)";
		    // create the mysql insert preparedstatement
		    PreparedStatement preparedStmt = conn.prepareStatement(query);
		    preparedStmt.setString (1, dept);
		    
		    // execute the preparedstatement
		    preparedStmt.execute();
		    
		    return "Department Added"; 
		}
		
		public String deleteDepartment(String abc) throws Exception {
			// the mysql delete statement
		    String query = "DELETE FROM department_table where Department=?";
		    // create the mysql delete preparedstatement
		    PreparedStatement preparedStmt = conn.prepareStatement(query);
		    preparedStmt.setString (1, abc);
		    
		    // execute the preparedstatement
		    preparedStmt.execute();
		    
		    return "Deleted Department";
		    
		}
		
		public HashMap<String, List<String>> getDepartment() throws Exception {
			//create a statement
			Statement stmt = conn.createStatement();
			//create a query
			String query = "SELECT * FROM department_table";
			//execute SQL query
			ResultSet rs = stmt.executeQuery(query);
			List<String> ls= new ArrayList<>();
			//process the result set
			while(rs.next()) {
				ls.add(rs.getString("Department"));
//				System.out.println(rs.getString("Department"));
			}
			
			HashMap<String, List<String>> hm =new HashMap<>();
			hm.put("Department", ls);
			return hm;
		}

		public String addReason(String reason) throws Exception {
			// the mysql insert statement
		    String query = "INSERT INTO reason_table (Reason)"
		        + " values (?)";
		    // create the mysql insert preparedstatement
		    PreparedStatement preparedStmt = conn.prepareStatement(query);
		    preparedStmt.setString (1, reason);
		    
		    // execute the preparedstatement
		    preparedStmt.execute();
		    return "Reason Added"; 
		}
		
		public String deleteReason(String abc) throws Exception {
			
			System.out.println("HUm Reason Delete Kr denge");
			
			// the mysql delete statement
		    String query = "DELETE FROM reason_table where Reason=?";
		    // create the mysql delete preparedstatement
		    PreparedStatement preparedStmt = conn.prepareStatement(query);
		    preparedStmt.setString (1, abc);
		    
		    // execute the preparedstatement
		    preparedStmt.execute();
		    
		    return "Delete Department";
		}
		
		public  HashMap<String, List<String>>  getReason() throws Exception {
			//create a statement
			Statement stmt = conn.createStatement();
			//create a query
			String query = "SELECT * FROM reason_table";
			//execute SQL query
			ResultSet rs = stmt.executeQuery(query);
			List<String> ls= new ArrayList<>();
			//process the result set
			while(rs.next()) {
				ls.add(rs.getString("Reason"));
//				System.out.println(rs.getString("Reason"));
			}
			
			HashMap<String, List<String>> hm =new HashMap<>();
			hm.put("Reason", ls);
			return hm;
		}

		public HashMap<String, String> updateFeedback(HashMap<String, String> details){
			
			String query = "update patient_referral_table set Feedback = ? , Acknowledgement = ? , PGIAction = ?, IsAdmitted = ? where DoctorName = ? AND ReferredFrom = ? AND PatientName = ?";
			System.out.println(details.toString());
			HashMap<String, String> hm = new HashMap<>();
			try {
				PreparedStatement preparedStmt = conn.prepareStatement(query);
			    preparedStmt.setString(1, details.get("PGI Feedback"));
			    preparedStmt.setString(2, details.get("Acknowledgement"));
			    preparedStmt.setString(3, details.get("PGI Action"));
			    preparedStmt.setString(5, details.get("Doctor Name"));
			    preparedStmt.setString(6, details.get("Referred From"));
			    preparedStmt.setString(7, details.get("Patient Name"));
			    
			    if(details.get("Is Admitted") != "undefined") {
			    	System.out.println(details.get("Is Admitted"));
			    	preparedStmt.setString(4, details.get("Is Admitted"));
			    }
			    
			    preparedStmt.executeUpdate();
			    hm.put("message", "Successfull Updation");
			    return hm;
			}catch(SQLException e) {
				e.printStackTrace();
				hm.put("message", "Couldn't Update");
				return hm;
			}
		}

		public HashMap<String,String> getDoctorAction(String username, String password) {
			System.out.println("Username " + username + "Password " + password);
			//create a statement
			try {
				Statement stmt = conn.createStatement();
				String query = "SELECT * FROM doctor_action_table where Email = '"+username+"' AND Password = '"+password+"'";
				ResultSet rs = stmt.executeQuery(query);
				
				if(rs.next()) {
					HashMap<String, String> hm = new HashMap<>();
					rs.previous();
					while(rs.next()) {
						
						hm.put("email", rs.getString("Email"));
						hm.put("doctorType",rs.getString("DoctorType"));
						hm.put("action", rs.getString("action"));
					}
					
					System.out.println(hm.toString());
					return hm;
	
				}else {
					HashMap<String, String> hm = new HashMap<>();
					hm.put("message", "Record Doesn't Exist");
					System.out.println(hm.toString());
					return null;
					
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			
			return null;
		}
		
		public String updateDoctorAction(String abc) throws Exception {
			//create a statement
			Statement stmt = conn.createStatement();
			//create a query
			String query1 = "SELECT * FROM doctor_action_table WHERE RegNo = '" + abc + "'";
			
			
			//execute SQL query
			ResultSet rs = stmt.executeQuery(query1);
			rs.next(); //positioning the cursor before the first row and then requesting data
			
			String action = rs.getString("action");
			System.out.println(action);
			//used boolean because not working with strings in any way
//			System.out.println(actionType);
			//rs.getString("action")=="disabled" from this query1 used to check what the action is
			
		    if(action.equals("Enable")) { 
		    	String query2 = "update doctor_action_table set action = ? WHERE RegNo = ?";
				PreparedStatement preparedStmt = conn.prepareStatement(query2);
			    preparedStmt.setString(1, "Disable");
				preparedStmt.setString(2, abc);
			    // execute the prepared statement
			    preparedStmt.execute();
			}
			else {
				String query2 = "update doctor_action_table set action = ? WHERE RegNo = ?";
				PreparedStatement preparedStmt = conn.prepareStatement(query2);
				preparedStmt.setString(1, "Enable");
				preparedStmt.setString(2, abc);
			    // execute the prepared statement
			    preparedStmt.execute();
			}
//		    getDoctorAction();
		    return "Toggled";
		}
		
		public HashMap<String, String> get_doct_ref(HashMap<String,String> req){
			
			Statement stmt;
			try {
				stmt = conn.createStatement();
				String query1 = "SELECT * FROM doctor_table WHERE Email = '" + req.get("email") + "'";
				
				
				//execute SQL query
				ResultSet rs = stmt.executeQuery(query1);
				 //positioning the cursor before the first row and then requesting data
				
				HashMap<String,String> obj = new HashMap<>();
				
				rs.next();
					obj.put("Name",rs.getString("FirstName") +" "+ rs.getString("LastName"));
					obj.put("Registration Number",rs.getString("RegNo"));
					obj.put("Email",rs.getString("Email"));
					obj.put("Doctor Type",rs.getString("DoctorType"));
					obj.put("Hospital Name", rs.getString("HospitalName"));
					obj.put("Speciality",rs.getString("Speciality"));
				
				System.out.println(obj.toString());
				return obj;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
	

}
