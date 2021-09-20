package com.qurix.ihubtechnologies.minque.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.qurix.ihubtechnologies.minque.R;
import com.qurix.ihubtechnologies.minque.model.respose.PaneResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class ImageFragment extends Fragment {
    Context mContext;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    Unbinder unbinder;
    List<PaneResponse.ListOfPlayListBean> listData = new ArrayList<>();

    @SuppressLint("ValidFragment")
    public ImageFragment(List<PaneResponse.ListOfPlayListBean> listOfPlayList) {
        listData = listOfPlayList;
    }

    public ImageFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.playbackonelayout, container, false);
        unbinder = ButterKnife.bind(this, view);

        downloadZipFile();
        loadGifImage();

        return view;
    }

    private void loadGifImage() {
        RequestManager requestManager = Glide.with(this);
        String url = listData.get(0).getFilePath();
        Log.d("madhu",listData.get(0).getFilePath());
        Uri imageUri = Uri.parse(url);

        RequestBuilder requestBuilder = requestManager.load(imageUri);

        requestBuilder.into(imageView3);
    }

    private void downloadZipFile() {

//        Call<ResponseBody> call = client.downloadFileByUrl("anupamchugh/AnimateTextAndImageView/archive/master.zip");
//
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
//                if (response.isSuccessful()) {
//                    Log.d(TAG, "Got the body for the file");
//
//                    Toast.makeText(getApplicationContext(), "Downloading...", Toast.LENGTH_SHORT).show();
//
//                    downloadZipFileTask = new DownloadZipFileTask();
//                    downloadZipFileTask.execute(response.body());
//
//                } else {
//                    Log.d(TAG, "Connection failed " + response.errorBody());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                t.printStackTrace();
//                Log.e(TAG, t.getMessage());
//            }
//        });
    }
//    private class DownloadZipFileTask extends AsyncTask<ResponseBody, Pair<Integer, Long>, String> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//        }
//
//        @Override
//        protected String doInBackground(ResponseBody... urls) {
//            //Copy you logic to calculate progress and call
//            saveToDisk(urls[0], "myvideo.mp4");
//            return null;
//        }
//
//        protected void onProgressUpdate(Pair<Integer, Long>... progress) {
//
//            Log.d("API123", progress[0].second + " ");
//
//            if (progress[0].first == 100)
//                Toast.makeText(getApplicationContext(), "File downloaded successfully", Toast.LENGTH_SHORT).show();
//
//
//            if (progress[0].second > 0) {
//                int currentProgress = (int) ((double) progress[0].first / (double) progress[0].second * 100);
//                progressBar.setProgress(currentProgress);
//
//                txtProgressPercent.setText("Progress " + currentProgress + "%");
//
//            }
//
//            if (progress[0].first == -1) {
//                Toast.makeText(getApplicationContext(), "Download failed", Toast.LENGTH_SHORT).show();
//            }
//
//        }
//
//        public void doProgress(Pair<Integer, Long> progressDetails) {
//            publishProgress(progressDetails);
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//
//        }
//    }
//private void saveToDisk(ResponseBody body, String filename) {
//    try {
//
//        File destinationFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename);
//
//        InputStream inputStream = null;
//        OutputStream outputStream = null;
//
//        try {
//
//            inputStream = body.byteStream();
//            outputStream = new FileOutputStream(destinationFile);
//            byte data[] = new byte[4096];
//            int count;
//            int progress = 0;
//            long fileSize = body.contentLength();
//            Log.d(TAG, "File Size=" + fileSize);
//            while ((count = inputStream.read(data)) != -1) {
//                outputStream.write(data, 0, count);
//                progress += count;
//                Pair<Integer, Long> pairs = new Pair<>(progress, fileSize);
//                downloadZipFileTask.doProgress(pairs);
//                Log.d(TAG, "Progress: " + progress + "/" + fileSize + " >>>> " + (float) progress / fileSize);
//            }
//
//            outputStream.flush();
//
//            Log.d(TAG, destinationFile.getParent());
//            Pair<Integer, Long> pairs = new Pair<>(100, 100L);
//            downloadZipFileTask.doProgress(pairs);
//            return;
//        } catch (IOException e) {
//            e.printStackTrace();
//            Pair<Integer, Long> pairs = new Pair<>(-1, Long.valueOf(-1));
//            downloadZipFileTask.doProgress(pairs);
//            Log.d(TAG, "Failed to save the file!");
//            return;
//        } finally {
//            if (inputStream != null) inputStream.close();
//            if (outputStream != null) outputStream.close();
//        }
//    } catch (IOException e) {
//        e.printStackTrace();
//        Log.d(TAG, "Failed to save the file!");
//        return;
//    }
//}

    //    private void askForPermission(String permission, Integer requestCode) {
//        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
//
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permission)) {
//                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
//
//            } else {
//                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
//            }
//        } else if (ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
//            Toast.makeText(getApplicationContext(), "Permission was denied", Toast.LENGTH_SHORT).show();
//        }
//    }
//@Override
//public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    if (ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
//
//        if (requestCode == 101)
//            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
//    } else {
//        Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
//    }
//}
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
