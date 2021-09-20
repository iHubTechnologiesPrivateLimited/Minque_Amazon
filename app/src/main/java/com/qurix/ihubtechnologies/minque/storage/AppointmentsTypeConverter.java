package com.qurix.ihubtechnologies.minque.storage;


import com.qurix.ihubtechnologies.minque.model.respose.DoctorsData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class AppointmentsTypeConverter {

    static Gson gson = new Gson();

    @android.arch.persistence.room.TypeConverter
    public static List<DoctorsData.DcDoctorAppointmentDisplaysBean> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<DoctorsData.DcDoctorAppointmentDisplaysBean>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @android.arch.persistence.room.TypeConverter
    public static String someObjectListToString(List<DoctorsData.DcDoctorAppointmentDisplaysBean> someObjects) {
        return gson.toJson(someObjects);
    }
}
