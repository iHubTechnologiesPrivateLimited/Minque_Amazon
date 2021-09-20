package com.qurix.ihubtechnologies.minque.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

public class SessionManager implements Serializable {

    private final static String DISPLAYCODE = "displaycode";
    private final static String DISPLAYURL = "displayurl";
    private final static String PREF_NAME = "SuperDoc";

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setDisplyCode(String displaycode) {
        editor.putString(DISPLAYCODE, displaycode);
        editor.commit();
    }

    public String getDisplyCode() {
        return pref.getString(DISPLAYCODE, null);
    }
    public void  setDisplayurl(String displayurl){
        editor.putString(DISPLAYURL, displayurl);
        editor.commit();
    }

    public String getDisplayurl(){
        return pref.getString(DISPLAYURL,null);
    }
}
