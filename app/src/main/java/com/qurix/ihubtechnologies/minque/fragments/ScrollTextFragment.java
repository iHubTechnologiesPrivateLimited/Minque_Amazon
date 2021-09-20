package com.qurix.ihubtechnologies.minque.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.qurix.ihubtechnologies.minque.R;
import com.qurix.ihubtechnologies.minque.utils.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ScrollTextFragment extends Fragment {
    @BindView(R.id.scrolling_text)
    TextView scrollingText;
    Unbinder unbinder;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.playbacktwolayout, container, false);
        unbinder = ButterKnife.bind(this, view);

        scrollingText.setTypeface(((BaseActivity)mContext).bold);

        Animation translatebu= AnimationUtils.loadAnimation(mContext, R.anim.scrollinganim);


//        scrollingText.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        scrollingText.startAnimation(translatebu);

//        scrollingText.setSelected(true);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAttach(Context context) {
        mContext= context;
        super.onAttach(context);
    }
}
