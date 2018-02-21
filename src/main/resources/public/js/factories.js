var app = angular.module("manageDoctors.services",[]);

var url = "http://localhost:8080";

app.service('APIS', function($http){

	return{
		
		sendRegistration : function(data_req, directory){
			return $http.post(url + directory, data_req)
			.then(function(response){
				console.log("Response Received");
				return response.data
			}, function(error){
				console.log("Eroor Che", error);
			});
		},
		
		getData : function(data_req, directory){
			return $http.post(url + directory, data_req)
			.then(function(response){
				console.log(response.data);
				console.log("Response Received");
				return response.data
			}, function(error){
				console.log("Eroor Che", error);
			});
		}, 

		fetchDoctor : function(directory){
			return $http.post(url + directory)
			.then(function(response){
				console.log("Response Received");
				return response.data
			}, function(error){
				console.log("Eroor Che", error);
			});
		},
		
		fetchData: function(directory){
			return $http.post(url + directory)
			.then(function(response){
				console.log(response);
				return response.data
			}, function(error){
				console.log("Eroor Che", error);
			});
		},
		
		toggleAction : function(data, directory){
			console.log(data);
			return $http.post(url + directory, data)
			.then(function(response){
				console.log("Response Received");
				return response.data
			}, function(error){
				console.log("Eroor Che", error);
			});
		},
		
		addData: function(data, directory){
			console.log(data);
			return $http.post(url + directory, data)
			.then(function(response){
				console.log("Response Received");
				return response.data
			}, function(error){
				console.log("Eroor Che", error);
			});
		},
		
		deleteData: function(data, directory){
			console.log(data);
			return $http.post(url + directory, data)
			.then(function(response){
				console.log("Response Received");
				return response.data
			}, function(error){
				console.log("Eroor Che", error);
			});
		}
		
		
	}
});