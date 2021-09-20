package com.qurix.ihubtechnologies.minque.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenarator {

 //   public static final String BASE_API = "http://139.59.17.219:9099/imedihubhims/openapi/displayManager/"; //qa
//    public static final String BASE_API = "http://qurix.ai:8080/qurixHIMS/openapi/displayManager/";
//  public static final String BASE_API = "http://qurix.ai:8080/qurix_uat/openapi/displayManager/"; // prod
// public static final String BASE_API =  "http://preprod.qurix.io/preprodhims/openapi/displayManager/";
 public static final String BASE_API =  "http://qurix.io/hims/openapi/displayManager/";
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build();




    public static <S> S createService(Class<S> serviceCalls){
        return retrofit.create(serviceCalls);

    }

}
