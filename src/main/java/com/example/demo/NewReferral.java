package com.example.demo;

public class NewReferral {
	

    String doctorName,referredFrom,doctorDepartment,date,time,patientName,arrivalTime,age,referralReason,provisionalDiagnosis;
    String comment,treatmentGiven,oxygen,ventilator,inotropes,ambProvided,trainedProf,acknowledgement, feedback,pgiAction, isAdmitted;
        
    public String getDoctorName(){
        return doctorName;
    }
    public String getReferredFrom(){
        return referredFrom;
    }
    public String getDepartment(){
        return doctorDepartment;
    }
    public String getDate(){
        return date;
    }
    public String getTime(){
        return time;
    }
    public String getPatientName(){
        return patientName;
    }
    public String getArrivalTime(){
        return arrivalTime;
    }
    public String getAge(){
        return age;
    }
    public String getReferralReason(){
        return referralReason;
    }
    public String getProvisionalDiagnosis(){
        return provisionalDiagnosis;
    }
    public String getComment(){
        return comment;
    }
    public String getTreatmentGiven(){
        return treatmentGiven;
    }
    public String getOxygen(){
        return oxygen;
    }
    public String getVentilator(){
        return ventilator;
    }
    public String getInotropes(){
        return inotropes;
    }
    public String getAmbProvided(){
        return ambProvided;
    }
    public String getTrainedProf(){
        return trainedProf;
    }
    
    public void setDoctorName(String doctorName){
        this.doctorName = doctorName;
    }
    public void setReferredFrom(String referredFrom){
        this.referredFrom = referredFrom;
    }
    public void setDepartment(String doctorDepartment){
        this.doctorDepartment = doctorDepartment;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setTime(String time){
        this.time = time;
    }
    public void setPatientName(String patientName){
        this.patientName = patientName;
    }
    public void setArrivalTime(String arrivalTime){
        this.arrivalTime = arrivalTime;
    }
    public void setAge(String age){
        this.age = age;
    }
    public void setReferralReason(String referralReason){
        this.referralReason = referralReason;
    }
    public void setProvisionalDiagnosis(String provisionalDiagnosis){
        this.provisionalDiagnosis = provisionalDiagnosis;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
    public void setTreatmentGiven(String treatmentGiven){
        this.treatmentGiven = treatmentGiven;
    }
    public void setOxygen(String oxygen){
        this.oxygen = oxygen;
    }
    public void setVentilator(String ventilator){
        this.ventilator = ventilator;
    }
    public void setInotropes(String inotropes){
        this.inotropes = inotropes;
    }
    public void setAmbProvided(String ambProvided){
        this.ambProvided = ambProvided;
    }
    public void setTrainedProf(String trainedProf){
        this.trainedProf = trainedProf;
    }
    
//    $scope.referralLabels = ["Patient_Name","Treatment_Description", "Comments","Oxygen", "Ambulance","Trained_Professional",
//                             "Referral_Date","Expected_Time_Arrival", "DoctorName", "Age","ProvidedDiagnosis", "Ventilator", "Inotropes", "Department","Referral_Time","Acknowledgement", "Feedback",
//                             "Is_Admitted"
////                         ];

}
