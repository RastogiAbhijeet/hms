var app = angular.module("manageDoctors.controllers", ["manageDoctors.services"]);

// @URL = /adminLogin
app.controller("adminLoginCredentials", function($scope, $window, $rootScope, APIS){
	
	$scope.checkCredentials = function(){
	if(confirm("Are you sure?")){
		if($scope.loginData.username == "admin" && $scope.loginData.password == "12345"){
			APIS.fetchDoctor("/admin/fetchDoctor").then(function(data){
				$rootScope.doctorInfo = data;
				console.log($window.location.host);
				$window.location.href = "http://" + $window.location.host+"/#!/adminHome";
			});
		}		
	}}	
});

// @URL = /loginCredentials 
app.controller("LoginCredentials", function($scope, $window, $rootScope, APIS){
	
	 $scope.validateLogin = function(){
		 if(confirm("Are you sure?")){
	 	APIS.getData($scope.loginData, "/doctor/loginCredentials").then(function(data){
             $rootScope.loginResponse = data;
//             alert(data);
             if($rootScope.loginResponse != null){
                 if($rootScope.loginResponse['action'] == "Enable"){
                     if($rootScope.loginResponse['doctorType'] == "PGI"){
                         link = "/pgiHome"
                     }else if($rootScope.loginResponse['doctorType'] == "Non PGI"){
                         link = "/nonpgiHome"
                     }
                     $window.location.href = "http://" + $window.location.host+"/#!" + link;
                 }else{
                     alert("Your Account has not been enable please contact the administrator");
                 }
             }else{
                 alert("The record does not exits. Please register in order to Access Your Account");
             }
         });
     }
	 }
    
})

//  @URl = /enableId
app.controller("manageDoctors", function($scope, $window, $rootScope, APIS){
	alert("Hello Admin");
	console.log($rootScope.doctorInfo);
    $scope.label = ["Name", "Registration Number", "Mobile", "Email", "Doctor Type", "Actions"];

    $scope.reverse = true;
    $scope.counter = 0;
    $scope.orderByMe = function(x){
        $scope.counter += 1;
        console.log(x);
        $scope.myOrder = x; 
        if($scope.counter%2 == 0)
            $scope.reverse = true;
        else
            $scope.reverse = false;              
    };

    $scope.redirect = function(link){
        console.log($window.location.origin);
        s = $window.location.hash.split("/");
        $window.location.href = $window.location.origin+"/"+s[0]+"/"+link;
    }
    
    // this method is going to handle the Toggling Action of a doctors record
    
    $scope.toggleAction = function(action, registrationNumber){
    	if(confirm("Are you sure?")){
    	$scope.packet = {'message' : registrationNumber.toString()}
    	
    	APIS.toggleAction($scope.packet, "/admin/toggleAction").then(function(data){
    		console.log(typeof $rootScope.doctorInfo);
    		if(data['message'] == "Toggled"){
    			
    			size = 0;
    			
    			for(x in $rootScope.doctorInfo){
    				size = size +1;
    			}
    			
	    		for(i = 0; i<size;i++){
	    			
	    			if($rootScope.doctorInfo[i]['Registration Number'] == registrationNumber){
	    				
	    				if($rootScope.doctorInfo[i]['Actions'] == 'Disable')
	    					$rootScope.doctorInfo[i]['Actions'] = 'Enable';
	    				else 
	    					if($rootScope.doctorInfo[i]['Actions'] == 'Enable')
	    					$rootScope.doctorInfo[i]['Actions'] = 'Disable';
	    			}
	    		}
	    	}
    		else{
    			alert("try After some Time");
    		}
    	})    	
    }
    }
    
})

// @URl = /addReferral
app.controller("newReferralCtrl", function($scope, $window, $rootScope,$filter,APIS){
    
    $scope.CurrentDate = new Date();
    

    
    
    APIS.fetchData('/admin/fetchDepartment')
	.then(function(data){
		$scope.departments = data['Department'];
	});
		   
    
    $scope.addNewReferral = function(){
    	if(confirm("Are you sure?")){ 	
    	
    	console.log("TP PRE : ",$scope.newReferral.trainedProfessional)
    	console.log("TP PRE : ",$scope.newReferral.ambulance)
    	
    	$scope.newReferral.trainedProfessional = ""+$scope.newReferral.trainedProfessional;
		$scope.newReferral.ambulance = ""+$scope.newReferral.ambulance;
		$scope.newReferral.hospitalDepartment = "Emergency";
		
		console.log("TP PRE : ",$scope.newReferral.trainedProfessional)
		console.log("TP PRE : ",$scope.newReferral.ambulance)
		
    	$scope.newReferral.date = $scope.CurrentDate.toLocaleDateString();
    	$scope.newReferral.time = $scope.CurrentDate.toLocaleTimeString();
    	console.log($scope.CurrentDate.toLocaleDateString())
    	console.log($scope.CurrentDate.toLocaleTimeString())
    	
    	$scope.newReferral.arrivalTime = $scope.newReferral.arrivalTime.toLocaleTimeString();
    	console.log($scope.newReferral.arrivalTime)
    	
//    	$scope.ageInYears();
    	
    	APIS.sendRegistration($scope.newReferral, "/doctor/registerPatient")
    	.then(function(data){
    		alert(data['message']);
    	})
    }
    }
    $scope.redirect = function(link){
        console.log($window.location.origin);
        s = $window.location.hash.split("/");
        $window.location.href = $window.location.origin+"/"+s[0]+"/"+link;
    }
    
});


// @URl = /addOpdReferral
app.controller("newOpdReferralCtrl", function($scope, $window, $rootScope,$filter,APIS){
    
    $scope.CurrentDate = new Date();
 
    
    
    APIS.fetchData('/admin/fetchDepartment')
	.then(function(data){
		$scope.departments = data['Department'];
	});
    
    	$scope.addNewReferral = function(){
    		
    		if(confirm("Are you sure?")){
    		
    		$scope.newReferral.hospitalDepartment = "OPD";

	      	$scope.newReferral.date = $scope.CurrentDate.toLocaleDateString();
	    	$scope.newReferral.time = $scope.CurrentDate.toLocaleTimeString();
	    	console.log($scope.CurrentDate.toLocaleDateString())
	    	console.log($scope.CurrentDate.toLocaleTimeString())
	    	
	    	$scope.newReferral.arrivalDate = $scope.newReferral.arrivalDate.toLocaleDateString();
	    	console.log($scope.newReferral.arrivalDate)
	    	
	//    	$scope.ageInYears();
	    	
	    	APIS.sendRegistration($scope.newReferral, "/doctor/registerPatient")
	    	.then(function(data){
	    		alert(data['message']);
	    	})
    		}
    	}
    $scope.redirect = function(link){
        console.log($window.location.origin);
        s = $window.location.hash.split("/");
        $window.location.href = $window.location.origin+"/"+s[0]+"/"+link;
    }
    
})

app.controller("allOpdReferrals",function($scope, $window, $rootScope, APIS){

	APIS.getData({"message" : "OPD"}, "/doctor/fetchReferral").then(function(data){
		console.log(data);
		$scope.referralLabels = data;
	});
	
	$scope.stateDiv = "hidden";

	$scope.hide = function(){
		return {"visibility":$scope.stateDiv};
	}

	$scope.changeState = function(value){
		$scope.stateDiv = value;
	}

	$scope.changeState = function(value, patientName){
		message = {"message":patientName}
		APIS.getData(message, "/doctor/fetchPatient")
		.then(function(data){
			$scope.patientData = data;
			$scope.patientLabels = ["Patient Name", "Age", "Provisional Diagnosis", "Doctor Name", "Department", "Referral Date", "Expected Arrival Date", "Is Admitted", "Acknowledgement", "PGI Feedback"];
			$scope.stateDiv = value;
		})
	}

	$scope.detailKeys = ["Patient Name", "Expected Arrival Date", "Provided Diagnosis", "Acknowledgement", "PGI Feedback"];
	
	$scope.reverse = true;
	$scope.counter = 0;
	$scope.orderByMe = function(x){
		$scope.counter += 1;
		console.log(x);
		$scope.myOrder = x; 
		if($scope.counter%2 == 0)
			$scope.reverse = true;
		else
			$scope.reverse = false;              
	};


	$scope.redirect = function(link){
		console.log($window.location.origin);
		s = $window.location.hash.split("/");
		$window.location.href = $window.location.origin+"/"+s[0]+"/"+link;
	}

});
// @URl = /fetchReferral
app.controller("allReferrals",function($scope, $window, $rootScope, APIS){
	    APIS.getData({"message" : "Emergency"}, "/doctor/fetchReferral").then(function(data){
		console.log(data);
    	$scope.referralLabels = data;
	});
	    
	    $scope.stateDiv = "hidden";

    $scope.hide = function(){
    	return {"visibility":$scope.stateDiv};
    }
    
    $scope.changeState = function(value){
    	$scope.stateDiv = value;
    }
    
    $scope.changeState = function(value, patientName){
    	message = {"message":patientName}
    	APIS.getData(message, "/doctor/fetchPatient")
    	.then(function(data){
    		$scope.patientData = data;
    		$scope.patientLabels = ["Patient Name", "Doctor Name", "Referred From", "Department", "Date", "Time",  "Arrival Time", "Age", "Referral Reason", "Provisional Diagnosis", "Comment", "Treatment Given", "Oxygen", "Ventilator", "Inotropes", "Ambulance Provided", "Trained Prof", "PGI Feedback"];
    		$scope.stateDiv = value;
    	})
    }
    
    $scope.detailKeys = ["Patient Name", "Date", "Referral Time", "Provided Diagnosis", "Acknowledgement", "PGI Feedback"];
      
    $scope.reverse = true;
    $scope.counter = 0;
    $scope.orderByMe = function(x){
        $scope.counter += 1;
        console.log(x);
        $scope.myOrder = x; 
        if($scope.counter%2 == 0)
            $scope.reverse = true;
        else
            $scope.reverse = false;              
    };

    
    $scope.redirect = function(link){
        console.log($window.location.origin);
        s = $window.location.hash.split("/");
        $window.location.href = $window.location.origin+"/"+s[0]+"/"+link;
    }
    
})

// @URl = /getDepartment , /addDepartment
app.controller("manageDepartments", function($scope, $window, $rootScope, APIS){
	
	APIS.fetchData('/admin/fetchDepartment')
	.then(function(data){
		$scope.departmentInfo = data['Department'];
	});
		
	$scope.addDepartment = function(){
		
		if(confirm("Are you sure?")){
		
		var dept_data = {'message':$scope.department_field};
		APIS.addData(dept_data, '/admin/addDepartment')
		.then(function(data){
			if(data['message'] == "Department Added"){
				$scope.departmentInfo.push($scope.department_field);
			}else{
				alert("Department Cant be added")
			}
			
		})
	}
	}
	
	$scope.deleteDepartment = function(index){
		
		if(confirm("Are you sure?")){
		
		var dept_data = {'message':$scope.departmentInfo[index]};
		
		APIS.deleteData(dept_data, '/admin/deleteDepartment')
		.then(function(data){
			if(data['message'] = "Deleted Department"){
				$scope.departmentInfo.splice(index, 1);
			}else{
				alert("Department Can't be Deleted");
			}
		})
		}
	}
	
    $scope.Labels = ["S.No","Department", "Action"];

    $scope.redirect = function(link){
        console.log($window.location.origin);
        s = $window.location.hash.split("/");
        $window.location.href = $window.location.origin+"/"+s[0]+"/"+link;
    }
})

// @URl = /getReason  , /addReason
app.controller("manageReasons", function($scope, $window, $rootScope, APIS){
//	$scope.reasonInfo = [];
	
	APIS.fetchData("/admin/fetchReason")
	.then(function(data){
		$scope.reasonInfo = data['Reason'];
	});
		
	$scope.addReason = function(){
		
		if(confirm("Are you sure?")){
		
		var message = {"message" : $scope.reason_field};
		APIS.addData(message, '/admin/addReason')
		.then(function(data){
			if(data['message'] == "Reason Added"){
				$scope.reasonInfo.push($scope.reason_field);
			}else{
				alert("reason Could not be added");
			}
		})
	}
	}
	
	$scope.deleteReason = function(index){
		
		if(confirm("Are you sure?")){
		var reason_data = {'message':$scope.reasonInfo[index]};
		
		APIS.deleteData(reason_data, '/admin/deleteReason')
		.then(function(data){
			if(data['message'] = "Deleted Reason"){
				$scope.reasonInfo.splice(index, 1);
			}else{
				alert("Reason Can't be Deleted");
			}
		})
	}
	}
    $scope.Labels = ["S.No","Reason", "Action"];

    $scope.redirect = function(link){
        console.log($window.location.origin);
        s = $window.location.hash.split("/");
        $window.location.href = $window.location.origin+"/"+s[0]+"/"+link;
    }
});

// @URL = /addDoctor after adding doctor Redirection..
app.controller("registration", function($scope, $window, $rootScope, APIS){
    $scope.submitForm = function(){
    	if(confirm("Are you sure?")){
    	console.log($scope.regForm)
    	alert();
    	if($scope.regForm.password == $scope.confirmPassword){
    		APIS.sendRegistration($scope.regForm, "/doctor/registerDoctor")
    		.then(function(data){
    			$window.location.href = $window.location.origin+"/";
    		})
    	}else{
    		alert("Confirm Password and Password Don't Match");
    	}
    }
    }
});

// @URL = /getReferrals | 
app.controller("emergencyReferral", function($scope, $window, $rootScope, APIS){
	
	$scope.background = ""
	
	$scope.background = function(){
		return{"background":$scope.background};
	}
	
	$scope.stateDiv = "hidden";

    $scope.hide = function(){
    	return {"visibility":$scope.stateDiv};
    }
    
    $scope.changeState = function(value){
    	$scope.stateDiv = value;
    }
    
    $scope.changeState = function(value, patientName){
    	message = {"message":patientName}
    	APIS.getData(message, "/doctor/fetchPatient")
    	.then(function(data){
    		$scope.patientData = data;
    		$scope.patientLabels = ["Patient Name", "Date", "Referral Time", "Expected Arrival Time", "Provisional Diagnosis", "PGI Action"];
    		$scope.stateDiv = value;
    	})
    }

    
    APIS.getData({"message" : "Emergency"}, "/doctor/fetchReferral").then(function(data){
		$rootScope.testData = data;
    });
    
    $scope.headings = ["Patient Name", "Date", "Referral Time", "Expected Arrival Time", "Provisional Diagnosis", "PGI Action"];

    $scope.listOfOptions = ['10','50','100','All'];
    $scope.pageSize='10';
    $scope.pageSizeChanged = function(){
        scope.$apply($scope.pageSize);
    }
    
    
    $scope.feedback = function(patient){
    	$rootScope.feedbackPatient = patient;
		$scope.redirect("feedback");
	}
   
    $scope.redirect = function(link){
        console.log($window.location.origin);
        s = $window.location.hash.split("/");
        $window.location.href = $window.location.origin+"/"+s[0]+"/"+link;
    }
});


app.controller("opdReferrals", function($scope, $window, $rootScope, APIS){
	
	$scope.background = ""
	
	$scope.background = function(){
		return{"background":$scope.background};
	}
	
	$scope.stateDiv = "hidden";

    $scope.hide = function(){
    	return {"visibility":$scope.stateDiv};
    }
    
    $scope.changeState = function(value){
    	$scope.stateDiv = value;
    }
    
    $scope.changeState = function(value, patientName){
    	message = {"message":patientName}
    	APIS.getData(message, "/doctor/fetchPatient")
    	.then(function(data){
    		$scope.patientData = data;
    		$scope.patientLabels = ["Patient Name", "Age", "Provisional Diagnosis", "Doctor Name", "Department", "Referral Date", "Arrival Date", "Is Admitted", "Acknowledgement", "PGI Feedback"];
    		$scope.stateDiv = value;
    	})
    }

    APIS.getData({"message" : "OPD"}, "/doctor/fetchReferral").then(function(data){
		$rootScope.testData = data;
    });
    
    $scope.headings = ["Patient Name", "Date", "Expected Arrival Date", "Provisional Diagnosis", "PGI Action"];

    $scope.listOfOptions = ['10','50','100','All'];
    $scope.pageSize='10';
    $scope.pageSizeChanged = function(){
        scope.$apply($scope.pageSize);
    }
    
    
    $scope.feedback = function(patient){
    	$rootScope.feedbackPatient = patient;
		$scope.redirect("feedback");
	}
   
    $scope.redirect = function(link){
        console.log($window.location.origin);
        s = $window.location.hash.split("/");
        $window.location.href = $window.location.origin+"/"+s[0]+"/"+link;
    }
});

app.controller("feedback", function($scope, $rootScope, $window, APIS ){
	
	$scope.patientFeedback = $rootScope.feedbackPatient;

	
	 
	$scope.sendFeedback = function(){
		
		if(confirm("Are you sure?")){
		
		alert($scope.patientFeedback['Is Admitted']);
		$scope.patientFeedback['Is Admitted'] = "" + $scope.patientFeedback['Is Admitted'];

		if($scope.patientFeedback['Acknowledgement'] == "" && $scope.patientFeedback['PGI Feedback'] != ""){
			$scope.patientFeedback['PGI Action'] = "Feedback Given"
		}else if ($scope.patientFeedback["Acknowledgement"] != "" && $scope.patientFeedback['PGI Feedback'] != ""){
			$scope.patientFeedback['PGI Action'] = "Feedback Given"
		}else if ($scope.patientFeedback["Acknowledgement"] != "" && $scope.patientFeedback['PGI Feedback'] == ""){
			$scope.patientFeedback['PGI Action'] = "Acknowledgement Given"
		}else if ($scope.patientFeedback["Acknowledgement"] == "" && $scope.patientFeedback['PGI Feedback'] == ""){
			$scope.patientFeedback['PGI Action'] = "Not Acknowledged"
		}
		
		alert($scope.patientFeedback['PGI Feedback'])
		
		console.log($scope.patientFeedback);
		APIS.sendRegistration($scope.patientFeedback, "/doctor/updateFeedback").then(function(data){
			alert(data['message']);
			if(data['message'] == "Successfull Updation"){
				$scope.redirect("pgiHome");
			}
	    });
		}
	}
	
	
    
	
	$scope.redirect = function(link){
        console.log($window.location.origin);
        s = $window.location.hash.split("/");
        $window.location.href = $window.location.origin+"/"+s[0]+"/"+link;
    }
	
	
});


