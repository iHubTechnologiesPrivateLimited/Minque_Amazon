package com.qurix.ihubtechnologies.minque.model;

import java.io.Serializable;

public class UpnextMdel implements Serializable {
    private String number;
    private String name;
    private String mobile;

    public UpnextMdel(String number, String name, String mobile) {
        this.number = number;
        this.name = name;
        this.mobile = mobile;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
