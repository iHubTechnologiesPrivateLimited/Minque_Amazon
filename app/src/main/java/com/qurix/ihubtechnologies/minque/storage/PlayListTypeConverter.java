package com.qurix.ihubtechnologies.minque.storage;

import com.qurix.ihubtechnologies.minque.model.respose.PaneResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class PlayListTypeConverter {

    static Gson gson = new Gson();

    @android.arch.persistence.room.TypeConverter
    public static List<PaneResponse.ListOfPlayListBean> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<PaneResponse.ListOfPlayListBean>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @android.arch.persistence.room.TypeConverter
    public static String someObjectListToString(List<PaneResponse.ListOfPlayListBean> someObjects) {
        return gson.toJson(someObjects);
    }
}
