package com.qurix.ihubtechnologies.minque.model.request;

import java.io.Serializable;

public class UuidRequest implements Serializable {

    /**
     * macId : 00:E0:4C:4A:76:EC
     * uuId : 74e6
     */

    private String macId;
    private String uuId;

    public UuidRequest(String macId, String uuId) {
        this.macId = macId;
        this.uuId = uuId;
    }

    public String getMacId() {
        return macId;
    }

    public void setMacId(String macId) {
        this.macId = macId;
    }

    public String getUuId() {
        return uuId;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId;
    }
}
