package com.qurix.ihubtechnologies.minque.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.qurix.ihubtechnologies.minque.R;
import com.qurix.ihubtechnologies.minque.model.request.PaneRequest;
import com.qurix.ihubtechnologies.minque.model.respose.PaneResponse;
import com.qurix.ihubtechnologies.minque.utils.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DummyActivity extends BaseActivity {

    @BindView(R.id.id_queue)
    FrameLayout idQueue;
    @BindView(R.id.id_paly_back1)
    FrameLayout idPalyBack1;
    @BindView(R.id.id_paly_back2)
    FrameLayout idPalyBack2;
    @BindView(R.id.id_paly_back3)
    FrameLayout idPalyBack3;
    @BindView(R.id.id_paly_back4)
    FrameLayout idPalyBack4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_full_pop);
        ButterKnife.bind(this);

        Call<List<PaneResponse>> call = client.getPaneData(new PaneRequest(null, "74e6"));
        call.enqueue(new Callback<List<PaneResponse>>() {
            @Override
            public void onResponse(Call<List<PaneResponse>> call, Response<List<PaneResponse>> response) {

                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(response.body().get(0).getWidth(), response.body().get(0).getHeight());
                params.gravity = Gravity.BOTTOM;
                idPalyBack3.setLayoutParams(params);

            }

            @Override
            public void onFailure(Call<List<PaneResponse>> call, Throwable t) {

            }
        });


    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}
