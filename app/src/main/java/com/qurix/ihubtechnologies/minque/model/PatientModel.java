package com.qurix.ihubtechnologies.minque.model;

import java.io.Serializable;

public class PatientModel implements Serializable {
    private String patient;
    private String contact;
    private String status;

    public PatientModel(String number, String name, String mobile) {
        this.patient = number;
        this.contact = name;
        this.status = mobile;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
