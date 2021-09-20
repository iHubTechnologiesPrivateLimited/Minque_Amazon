package com.qurix.ihubtechnologies.minque.model.respose;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.qurix.ihubtechnologies.minque.storage.AppointmentsTypeConverter;
import com.qurix.ihubtechnologies.minque.storage.StringTypeConverter;

import java.io.Serializable;
import java.util.List;

@Entity
public class DoctorsData implements Serializable {
    /**
     * doctorId : EMPMA001
     * doctorName : Manidhar Reddy D
     * doctorExperience : 0
     * doctorFee : null
     * designation : sr doctor
     * organization : Jubilee Hospitals
     * doctorSpeciality : ["General Medicine"]
     * doctorDepartment : ["General Medicine"]
     * doctorstudies : MBBS
     *  doctorImg: "https://ankurahospital.com/wp-content/uploads/2019/02/dr-kl-poornima-boduppal.png",
     * doctorProfileImg : null
     * doctorTime : 09:00-14:00
     * dcDoctorAppointmentDisplays : [{"apptId":189,"apptPatientId":"TMPP19000105","apptPatientName":"asha","apptPatientMobileNo":"8976543481","apptPatientImage":null,"apptProcedure":"Consultation","apptDoctorName":"Manidhar Reddy D","apptBookedTime":"12:00","apptCheckInTime":"00:00","apptWaitingTime":"840","scheduledTime":"12:00","apptStatus":"IN","deptId":10,"apptDoctorRoomNo":"Room 6A","apptReferedType":"Online Appointment","apptModifiedDate":null,"emergency":false,"firstTimeUser":false,"apptmntType":"Appointment"},{"apptId":191,"apptPatientId":"TMPP19000108","apptPatientName":"kumar","apptPatientMobileNo":"8976544498","apptPatientImage":null,"apptProcedure":"Consultation","apptDoctorName":"Manidhar Reddy D","apptBookedTime":"11:10","apptCheckInTime":"17:17","apptWaitingTime":"-197","scheduledTime":"17:17","apptStatus":"IN","deptId":10,"apptDoctorRoomNo":"Room 6A","apptReferedType":"Online Appointment","apptModifiedDate":null,"emergency":false,"firstTimeUser":true,"apptmntType":"Appointment"}]
     */
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String doctorId;
    private String doctorName;
    private String doctorExperience;
    private String doctorFee;
    private String designation;
    private String organization;
    private String doctorstudies;
    private String doctorImg;
    private String doctorProfileImg;
    private String doctorTime;
    @TypeConverters(StringTypeConverter.class)
    private List<String> doctorSpeciality;
    @TypeConverters(StringTypeConverter.class)
    private List<String> doctorDepartment;
    @TypeConverters(AppointmentsTypeConverter.class)
    private List<DcDoctorAppointmentDisplaysBean> dcDoctorAppointmentDisplays;

    public DoctorsData(int id, String doctorId, String doctorName, String doctorExperience, String doctorFee, String designation, String organization, String doctorstudies, String doctorImg,String doctorProfileImg, String doctorTime, List<String> doctorSpeciality, List<String> doctorDepartment, List<DcDoctorAppointmentDisplaysBean> dcDoctorAppointmentDisplays) {
        this.id = id;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorExperience = doctorExperience;
        this.doctorFee = doctorFee;
        this.designation = designation;
        this.organization = organization;
        this.doctorstudies = doctorstudies;
        this.doctorImg = doctorImg;
        this.doctorProfileImg = doctorProfileImg;
        this.doctorTime = doctorTime;
        this.doctorSpeciality = doctorSpeciality;
        this.doctorDepartment = doctorDepartment;
        this.dcDoctorAppointmentDisplays = dcDoctorAppointmentDisplays;
    }

    public String getDoctorImg() {
        return doctorImg;
    }

    public void setDoctorImg(String doctorImg) {
        this.doctorImg = doctorImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorExperience() {
        return doctorExperience;
    }

    public void setDoctorExperience(String doctorExperience) {
        this.doctorExperience = doctorExperience;
    }
    public String getDoctorFee() {
        return doctorFee;
    }

    public void setDoctorFee(String doctorFee) {
        this.doctorFee = doctorFee;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getDoctorstudies() {
        return doctorstudies;
    }

    public void setDoctorstudies(String doctorstudies) {
        this.doctorstudies = doctorstudies;
    }

    public String getDoctorProfileImg() {
        return doctorProfileImg;
    }

    public void setDoctorProfileImg(String doctorProfileImg) {
        this.doctorProfileImg = doctorProfileImg;
    }

    public String getDoctorTime() {
        return doctorTime;
    }

    public void setDoctorTime(String doctorTime) {
        this.doctorTime = doctorTime;
    }

    public List<String> getDoctorSpeciality() {
        return doctorSpeciality;
    }

    public void setDoctorSpeciality(List<String> doctorSpeciality) {
        this.doctorSpeciality = doctorSpeciality;
    }

    public List<String> getDoctorDepartment() {
        return doctorDepartment;
    }

    public void setDoctorDepartment(List<String> doctorDepartment) {
        this.doctorDepartment = doctorDepartment;
    }

    public List<DcDoctorAppointmentDisplaysBean> getDcDoctorAppointmentDisplays() {
        return dcDoctorAppointmentDisplays;
    }

    public void setDcDoctorAppointmentDisplays(List<DcDoctorAppointmentDisplaysBean> dcDoctorAppointmentDisplays) {
        this.dcDoctorAppointmentDisplays = dcDoctorAppointmentDisplays;
    }

    public static class DcDoctorAppointmentDisplaysBean {
        /**
         * apptId : 189
         * apptPatientId : TMPP19000105
         * apptPatientName : asha
         * apptPatientMobileNo : 8976543481
         * apptPatientImage : null
         * apptProcedure : Consultation
         * apptDoctorName : Manidhar Reddy D
         * apptBookedTime : 12:00
         * apptCheckInTime : 00:00
         * apptWaitingTime : 840
         * scheduledTime : 12:00
         * apptStatus : IN
         * deptId : 10
         * <p>
         * "doctorId":"EMPS0001",    newly added
         * "sessionId":4,
         * <p>
         * apptDoctorRoomNo : Room 6A
         * apptReferedType : Online Appointment
         * apptModifiedDate : null
         * emergency : false
         * firstTimeUser : false
         * apptmntType : Appointment
         */

        private int apptId;
        private String apptPatientId;
        private String apptPatientName;
        private String apptPatientMobileNo;
        private String apptPatientImage;
        private String apptProcedure;
        private String apptDoctorName;
        private String apptBookedTime;
        private String apptCheckInTime;
        private String apptWaitingTime;
        private String scheduledTime;
        private String apptStatus;
        private String deptId;
        private String doctorId;
        private String sessionId;
        private String apptDoctorRoomNo;
        private String apptReferedType;
        private String apptModifiedDate;
        private String emergency;
        private boolean firstTimeUser;
        private String apptmntType;

        public DcDoctorAppointmentDisplaysBean(int apptId, String apptPatientId, String apptPatientName, String apptPatientMobileNo, String apptPatientImage, String apptProcedure, String apptDoctorName, String apptBookedTime, String apptCheckInTime, String apptWaitingTime, String scheduledTime, String apptStatus, String deptId, String doctorId, String sessionId, String apptDoctorRoomNo, String apptReferedType, String apptModifiedDate, String emergency, boolean firstTimeUser, String apptmntType) {
            this.apptId = apptId;
            this.apptPatientId = apptPatientId;
            this.apptPatientName = apptPatientName;
            this.apptPatientMobileNo = apptPatientMobileNo;
            this.apptPatientImage = apptPatientImage;
            this.apptProcedure = apptProcedure;
            this.apptDoctorName = apptDoctorName;
            this.apptBookedTime = apptBookedTime;
            this.apptCheckInTime = apptCheckInTime;
            this.apptWaitingTime = apptWaitingTime;
            this.scheduledTime = scheduledTime;
            this.apptStatus = apptStatus;
            this.deptId = deptId;
            this.doctorId = doctorId;
            this.sessionId = sessionId;
            this.apptDoctorRoomNo = apptDoctorRoomNo;
            this.apptReferedType = apptReferedType;
            this.apptModifiedDate = apptModifiedDate;
            this.emergency = emergency;
            this.firstTimeUser = firstTimeUser;
            this.apptmntType = apptmntType;
        }

        public DcDoctorAppointmentDisplaysBean() {

        }

        public int getApptId() {
            return apptId;
        }

        public void setApptId(int apptId) {
            this.apptId = apptId;
        }

        public String getApptPatientId() {
            return apptPatientId;
        }

        public void setApptPatientId(String apptPatientId) {
            this.apptPatientId = apptPatientId;
        }

        public String getApptPatientName() {
            return apptPatientName;
        }

        public void setApptPatientName(String apptPatientName) {
            this.apptPatientName = apptPatientName;
        }

        public String getApptPatientMobileNo() {
            return apptPatientMobileNo;
        }

        public void setApptPatientMobileNo(String apptPatientMobileNo) {
            this.apptPatientMobileNo = apptPatientMobileNo;
        }

        public String getApptPatientImage() {
            return apptPatientImage;
        }

        public void setApptPatientImage(String apptPatientImage) {
            this.apptPatientImage = apptPatientImage;
        }

        public String getApptProcedure() {
            return apptProcedure;
        }

        public void setApptProcedure(String apptProcedure) {
            this.apptProcedure = apptProcedure;
        }

        public String getApptDoctorName() {
            return apptDoctorName;
        }

        public void setApptDoctorName(String apptDoctorName) {
            this.apptDoctorName = apptDoctorName;
        }

        public String getApptBookedTime() {
            return apptBookedTime;
        }

        public void setApptBookedTime(String apptBookedTime) {
            this.apptBookedTime = apptBookedTime;
        }

        public String getApptCheckInTime() {
            return apptCheckInTime;
        }

        public void setApptCheckInTime(String apptCheckInTime) {
            this.apptCheckInTime = apptCheckInTime;
        }

        public String getApptWaitingTime() {
            return apptWaitingTime;
        }

        public void setApptWaitingTime(String apptWaitingTime) {
            this.apptWaitingTime = apptWaitingTime;
        }

        public String getScheduledTime() {
            return scheduledTime;
        }

        public void setScheduledTime(String scheduledTime) {
            this.scheduledTime = scheduledTime;
        }

        public String getApptStatus() {
            return apptStatus;
        }

        public void setApptStatus(String apptStatus) {
            this.apptStatus = apptStatus;
        }

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public String getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(String doctorId) {
            this.doctorId = doctorId;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public String getApptDoctorRoomNo() {
            return apptDoctorRoomNo;
        }

        public void setApptDoctorRoomNo(String apptDoctorRoomNo) {
            this.apptDoctorRoomNo = apptDoctorRoomNo;
        }

        public String getApptReferedType() {
            return apptReferedType;
        }

        public void setApptReferedType(String apptReferedType) {
            this.apptReferedType = apptReferedType;
        }

        public String getApptModifiedDate() {
            return apptModifiedDate;
        }

        public void setApptModifiedDate(String apptModifiedDate) {
            this.apptModifiedDate = apptModifiedDate;
        }

        public String isEmergency() {
            return emergency;
        }

        public void setEmergency(String emergency) {
            this.emergency = emergency;
        }

        public boolean isFirstTimeUser() {
            return firstTimeUser;
        }

        public void setFirstTimeUser(boolean firstTimeUser) {
            this.firstTimeUser = firstTimeUser;
        }

        public String getApptmntType() {
            return apptmntType;
        }

        public void setApptmntType(String apptmntType) {
            this.apptmntType = apptmntType;
        }

        @Override
        public String toString() {
            return "DcDoctorAppointmentDisplaysBean{" +
                    "apptId=" + apptId +
                    ", apptPatientId='" + apptPatientId + '\'' +
                    ", apptPatientName='" + apptPatientName + '\'' +
                    ", apptPatientMobileNo='" + apptPatientMobileNo + '\'' +
                    ", apptPatientImage='" + apptPatientImage + '\'' +
                    ", apptProcedure='" + apptProcedure + '\'' +
                    ", apptDoctorName='" + apptDoctorName + '\'' +
                    ", apptBookedTime='" + apptBookedTime + '\'' +
                    ", apptCheckInTime='" + apptCheckInTime + '\'' +
                    ", apptWaitingTime='" + apptWaitingTime + '\'' +
                    ", scheduledTime='" + scheduledTime + '\'' +
                    ", apptStatus='" + apptStatus + '\'' +
                    ", deptId='" + deptId + '\'' +
                    ", apptDoctorRoomNo='" + apptDoctorRoomNo + '\'' +
                    ", apptReferedType='" + apptReferedType + '\'' +
                    ", apptModifiedDate='" + apptModifiedDate + '\'' +
                    ", emergency=" + emergency +
                    ", firstTimeUser=" + firstTimeUser +
                    ", apptmntType='" + apptmntType + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DoctorsDataNew{" +
                "id=" + id +
                ", doctorId='" + doctorId + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", doctorExperience='" + doctorExperience + '\'' +
                ", doctorFee='" + doctorFee + '\'' +
                ", designation='" + designation + '\'' +
                ", organization='" + organization + '\'' +
                ", doctorstudies='" + doctorstudies + '\'' +
                ", doctorProfileImg='" + doctorProfileImg + '\'' +
                ", doctorTime='" + doctorTime + '\'' +
                ", doctorSpeciality=" + doctorSpeciality +
                ", doctorDepartment=" + doctorDepartment +
                ", dcDoctorAppointmentDisplays=" + dcDoctorAppointmentDisplays +
                '}';
    }

}


// old response
/**
 * doctorId : EMPMA001
 * doctorName : Manidhar Reddy D
 * doctorExperience : 0
 * doctorFee : null
 * designation : sr doctor
 * organization : Jubilee Hospitals
 * doctorSpeciality : ["General Medicine"]
 * doctorDepartment : ["General Medicine"]
 * doctorstudies : MBBS
 * doctorProfileImg : null
 * doctorTime : 09:00-12:00
 * dcDoctorAppointmentDisplays : [{"patientId":"2_2@JH1701432","patientName":"H.K CHOWDARY  ","patientMobileNumber":"919441117764","appiontmentStatus":null,"appointmentTime":"21:00"},{"patientId":null,"patientName":null,"patientMobileNumber":null,"appiontmentStatus":null,"appointmentTime":"19:00"}]
 */

//    @PrimaryKey(autoGenerate = true)
//    private int id;
//
//    private String doctorId;
//    private String doctorName;
//    private String doctorExperience;
//    private String doctorFee;
//    private String designation;
//    private String organization;
//    private String doctorstudies;
//    private String doctorProfileImg;
//    private String doctorTime;
//    @TypeConverters(StringTypeConverter.class)
//    private List<String> doctorSpeciality;
//    @TypeConverters(StringTypeConverter.class)
//    private List<String> doctorDepartment;
//    @TypeConverters(AppointmentsTypeConverter.class)
//    private List<DcDoctorAppointmentDisplaysBean> dcDoctorAppointmentDisplays;
//
//    public DoctorsData(String doctorId, String doctorName, String doctorExperience, String doctorFee, String designation, String organization, String doctorstudies, String doctorProfileImg, String doctorTime, List<String> doctorSpeciality, List<String> doctorDepartment, List<DcDoctorAppointmentDisplaysBean> dcDoctorAppointmentDisplays) {
//        this.doctorId = doctorId;
//        this.doctorName = doctorName;
//        this.doctorExperience = doctorExperience;
//        this.doctorFee = doctorFee;
//        this.designation = designation;
//        this.organization = organization;
//        this.doctorstudies = doctorstudies;
//        this.doctorProfileImg = doctorProfileImg;
//        this.doctorTime = doctorTime;
//        this.doctorSpeciality = doctorSpeciality;
//        this.doctorDepartment = doctorDepartment;
//        this.dcDoctorAppointmentDisplays = dcDoctorAppointmentDisplays;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getDoctorId() {
//        return doctorId;
//    }
//
//    public void setDoctorId(String doctorId) {
//        this.doctorId = doctorId;
//    }
//
//    public String getDoctorName() {
//        return doctorName;
//    }
//
//    public void setDoctorName(String doctorName) {
//        this.doctorName = doctorName;
//    }
//
//    public String getDoctorExperience() {
//        return doctorExperience;
//    }
//
//    public void setDoctorExperience(String doctorExperience) {
//        this.doctorExperience = doctorExperience;
//    }
//
//    public String getDoctorFee() {
//        return doctorFee;
//    }
//
//    public void setDoctorFee(String doctorFee) {
//        this.doctorFee = doctorFee;
//    }
//
//    public String getDesignation() {
//        return designation;
//    }
//
//    public void setDesignation(String designation) {
//        this.designation = designation;
//    }
//
//    public String getOrganization() {
//        return organization;
//    }
//
//    public void setOrganization(String organization) {
//        this.organization = organization;
//    }
//
//    public String getDoctorstudies() {
//        return doctorstudies;
//    }
//
//    public void setDoctorstudies(String doctorstudies) {
//        this.doctorstudies = doctorstudies;
//    }
//
//    public String getDoctorProfileImg() {
//        return doctorProfileImg;
//    }
//
//    public void setDoctorProfileImg(String doctorProfileImg) {
//        this.doctorProfileImg = doctorProfileImg;
//    }
//
//    public String getDoctorTime() {
//        return doctorTime;
//    }
//
//    public void setDoctorTime(String doctorTime) {
//        this.doctorTime = doctorTime;
//    }
//
//    public List<String> getDoctorSpeciality() {
//        return doctorSpeciality;
//    }
//
//    public void setDoctorSpeciality(List<String> doctorSpeciality) {
//        this.doctorSpeciality = doctorSpeciality;
//    }
//
//    public List<String> getDoctorDepartment() {
//        return doctorDepartment;
//    }
//
//    public void setDoctorDepartment(List<String> doctorDepartment) {
//        this.doctorDepartment = doctorDepartment;
//    }
//
//    public List<DcDoctorAppointmentDisplaysBean> getDcDoctorAppointmentDisplays() {
//        return dcDoctorAppointmentDisplays;
//    }
//
//    public void setDcDoctorAppointmentDisplays(List<DcDoctorAppointmentDisplaysBean> dcDoctorAppointmentDisplays) {
//        this.dcDoctorAppointmentDisplays = dcDoctorAppointmentDisplays;
//    }
//
//    public static class DcDoctorAppointmentDisplaysBean {
//        /**
//         * patientId : 2_2@JH1701432
//         * patientName : H.K CHOWDARY
//         * patientMobileNumber : 919441117764
//         * appiontmentStatus : null
//         * appointmentTime : 21:00
//         */
//
//        private String patientId;
//        private String patientName;
//        private String patientMobileNumber;
//        private String appiontmentStatus;
//        private String appointmentTime;
//
//        public DcDoctorAppointmentDisplaysBean(String patientId, String patientName, String patientMobileNumber, String appiontmentStatus, String appointmentTime) {
//            this.patientId = patientId;
//            this.patientName = patientName;
//            this.patientMobileNumber = patientMobileNumber;
//            this.appiontmentStatus = appiontmentStatus;
//            this.appointmentTime = appointmentTime;
//        }
//
//        public String getPatientId() {
//            return patientId;
//        }
//
//        public void setPatientId(String patientId) {
//            this.patientId = patientId;
//        }
//
//        public String getPatientName() {
//            return patientName;
//        }
//
//        public void setPatientName(String patientName) {
//            this.patientName = patientName;
//        }
//
//        public String getPatientMobileNumber() {
//            return patientMobileNumber;
//        }
//
//        public void setPatientMobileNumber(String patientMobileNumber) {
//            this.patientMobileNumber = patientMobileNumber;
//        }
//
//        public String getAppiontmentStatus() {
//            return appiontmentStatus;
//        }
//
//        public void setAppiontmentStatus(String appiontmentStatus) {
//            this.appiontmentStatus = appiontmentStatus;
//        }
//
//        public String getAppointmentTime() {
//            return appointmentTime;
//        }
//
//        public void setAppointmentTime(String appointmentTime) {
//            this.appointmentTime = appointmentTime;
//        }
//
//        @Override
//        public String toString() {
//            return "DcDoctorAppointmentDisplaysBean{" +
//                    "patientId='" + patientId + '\'' +
//                    ", patientName='" + patientName + '\'' +
//                    ", patientMobileNumber='" + patientMobileNumber + '\'' +
//                    ", appiontmentStatus='" + appiontmentStatus + '\'' +
//                    ", appointmentTime='" + appointmentTime + '\'' +
//                    '}';
//        }
//    }
//
//    @Override
//    public String toString() {
//        return "DoctorsData{" +
//                "doctorId='" + doctorId + '\'' +
//                ", doctorName='" + doctorName + '\'' +
//                ", doctorExperience='" + doctorExperience + '\'' +
//                ", doctorFee=" + doctorFee +
//                ", designation='" + designation + '\'' +
//                ", organization='" + organization + '\'' +
//                ", doctorstudies='" + doctorstudies + '\'' +
//                ", doctorProfileImg=" + doctorProfileImg +
//                ", doctorTime='" + doctorTime + '\'' +
//                ", doctorSpeciality=" + doctorSpeciality +
//                ", doctorDepartment=" + doctorDepartment +
//                ", dcDoctorAppointmentDisplays=" + dcDoctorAppointmentDisplays +
//                '}';
//    }
// }
