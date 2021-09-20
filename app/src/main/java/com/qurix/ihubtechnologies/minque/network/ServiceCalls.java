package com.qurix.ihubtechnologies.minque.network;

import com.qurix.ihubtechnologies.minque.model.request.PaneRequest;
import com.qurix.ihubtechnologies.minque.model.respose.DoctorsData;
import com.qurix.ihubtechnologies.minque.model.respose.PaneResponse;
import com.qurix.ihubtechnologies.minque.model.respose.DisplayCodeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServiceCalls {

    @GET("doctorConsultation/listAppointmentsForEachDoctor")
    Call<List<DoctorsData>> getDoctorsData();

    @GET("requestDisplayCode/{macid}")
    Call<DisplayCodeResponse> getDisplayCode(@Path("macid") String macid);

    @POST("doctorConsultation/tvScreenPanes")
    Call<List<PaneResponse>> getPaneData(@Body PaneRequest paneRequest);

//    @GET("dummyurl/dummy")
//    Call<List<PaneResponse>> getcomplitPaneData(@Query("uuid") String uuid);


}
