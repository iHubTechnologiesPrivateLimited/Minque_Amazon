package com.qurix.ihubtechnologies.minque.model.respose;

import java.io.Serializable;

public class DisplayCodeResponse implements Serializable {

    /**
     * macId : 08:25:25:5D:E7:3C
     * displayCode : k0OM
     * description : null
     * locationId : 0
     * url : null
     * locUuid : null
     */

    private String macId;
    private String displayCode;
    private Object description;
    private int locationId;
    private Object url;
    private Object locUuid;

    public String getMacId() {
        return macId;
    }

    public void setMacId(String macId) {
        this.macId = macId;
    }

    public String getDisplayCode() {
        return displayCode;
    }

    public void setDisplayCode(String displayCode) {
        this.displayCode = displayCode;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public Object getLocUuid() {
        return locUuid;
    }

    public void setLocUuid(Object locUuid) {
        this.locUuid = locUuid;
    }
}
