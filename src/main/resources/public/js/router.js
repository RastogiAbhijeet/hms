var app = angular.module("manageDoctors", ['ngRoute', 'manageDoctors.controllers']);

app.config(['$routeProvider','$locationProvider',function($routeProvider, $locationProvider){
    $routeProvider
     .when("/", {
         templateUrl : 'html/doctors/doctorLogin.html',
         controller : "LoginCredentials"
     })
    .when("/admin", {
        templateUrl : 'html/doctors/admin.html',
        controller : "adminLoginCredentials"
    })
    .when("/newReferral", {
        templateUrl : 'html/doctors/newreferral.html',
        controller : "newReferralCtrl"
    })
    .when("/nonpgiHome", {
        templateUrl : 'html/doctors/allReferrals.html',
        controller : "allReferrals"
    })
    .when("/nonOpdRef", {
        templateUrl : 'html/doctors/allOpdReferral.html',
        controller : "allOpdReferrals"
    })
    .when("/manageDepartment", {
    	templateUrl : 'html/admin/manageDepartment.html',
        controller : "manageDepartments"
    })
    .when("/manageReasons", {
        templateUrl : 'html/admin/manageReasons.html',
        controller : "manageReasons"
    })
    .when("/registration", {
        templateUrl : 'html/doctors/registration.html',
        controller : "registration"
    })
    .when("/feedback", {
        templateUrl : 'html/doctors/feedback.html',
        controller : "feedback"
    })
    .when("/pgiHome", {
        templateUrl : 'html/doctors/emergencyReferral.html',
        controller : "emergencyReferral"
    })
    .when("/adminHome", {
        templateUrl : 'html/admin/manageDoctors.html',
        controller : "manageDoctors"
    })
    .when("/opdReferrals", {
    	templateUrl : 'html/doctors/opdReferrals.html',
        controller : "opdReferrals"
    })
    .when("/newOpdReferral", {
    	templateUrl : 'html/doctors/newOpdReferral.html',
        controller : "newOpdReferralCtrl"
    })
    .otherwise({
    	template : "PAGE NOT AVAILABLE"
    });
}]);
