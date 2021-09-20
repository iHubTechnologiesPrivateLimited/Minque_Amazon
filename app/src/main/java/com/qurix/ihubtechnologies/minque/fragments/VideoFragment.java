package com.qurix.ihubtechnologies.minque.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.qurix.ihubtechnologies.minque.R;

public class VideoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pop_layout, container, false);

        VideoView videoView = view.findViewById(R.id.simpleVideoView);

//        //Creating MediaController
//        MediaController mediaController= new MediaController(getActivity());
//        mediaController.setAnchorView(videoView);
//
//        //specify the location of media file
//    //    Uri uri=Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/media/1.mp4");
//        Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.video);
//        //Setting MediaController and URI, then starting the videoView
//        videoView.setMediaController(mediaController);
//        videoView.setVideoURI(uri);
//        videoView.requestFocus();
//        videoView.start();
//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mp.setLooping(true);
//            }
//        });


        return view;
    }
}
