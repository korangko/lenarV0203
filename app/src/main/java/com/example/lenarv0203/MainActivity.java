package com.example.lenarv0203;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.videolan.libvlc.MediaPlayer;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton recordBtn, liveBtn, timeLapseBtn;
    TextView recordBtnText, liveBtnText, timeLapseBtnText;
    Boolean isAllFabsVisible;
    static Context mainContext;

    NeumorphImageButton settingBtn;
    NeumorphButton modeSelectText;

    //rtsp Receiver Variable
    TextureView rtspReceiveView;
    RtspReceiver mRtspReceiver = new RtspReceiver();
    public static MediaPlayer mMediaPlayer = null;
    String url = "rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov";

    //background blur, setting btn
    ImageView backgroundBlur;

    //test
    RtmpBroadcast mRtmpBroadcast = new RtmpBroadcast();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainContext = this;

        //rtsp texture view
        rtspReceiveView = findViewById(R.id.rtspReceiveView);
        rtspReceiveView.setSurfaceTextureListener(mSurfaceTextureListener);
        //setting button
        settingBtn = findViewById(R.id.setting_btn);
        settingBtn.setOnClickListener(this);
        // floating button
        recordBtn = findViewById(R.id.record_btn);
        recordBtn.setOnClickListener(this);
        liveBtn = findViewById(R.id.live_btn);
        liveBtn.setOnClickListener(this);
        timeLapseBtn = findViewById(R.id.timelapse_btn);
        timeLapseBtn.setOnClickListener(this);
        modeSelectText = findViewById(R.id.mode_select_text);
        modeSelectText.setOnClickListener(this);
        //floating button text
        recordBtnText = findViewById(R.id.record_btn_text);
        liveBtnText = findViewById(R.id.live_btn_text);
        timeLapseBtnText = findViewById(R.id.timelapse_btn_text);
        //background Blurring
        backgroundBlur = findViewById(R.id.background_blur);
        //floating boolean
        isAllFabsVisible = false;
        //fragment initiating
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.fragmentView, new RecordFragment());
        fragmentTransaction.commit();
//        ViewGroup.LayoutParams params = rtspReceiveView.getLayoutParams();
        //permission
        checkPermission();
    }

    private TextureView.SurfaceTextureListener mSurfaceTextureListener =
            new TextureView.SurfaceTextureListener() {
                @Override
                public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                    System.out.println("josh size is " + width + ", " +height);
//                    mRtspReceiver.createPlayer(mainContext, url, rtspReceiveView, height, width);
                }

                @Override
                public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                    System.out.println("josh size is " + width + ", " +height);
//                    mRtspReceiver.createPlayer(mainContext, url, rtspReceiveView, height, width);
                }

                @Override
                public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                    return false;
                }

                @Override
                public void onSurfaceTextureUpdated(SurfaceTexture surface) {
                }
            };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mode_select_text:
                modeShowAndHide();
                break;
            case R.id.record_btn:
                switchFragment(0);
                modeShowAndHide();
                modeSelectText.setText("RECORD");
                break;
            case R.id.live_btn:
                switchFragment(1);
                modeShowAndHide();
                modeSelectText.setText("LIVE STREAM");
                break;
            case R.id.timelapse_btn:
                switchFragment(2);
                modeShowAndHide();
                modeSelectText.setText("TIMELAPSE");
                break;
            case R.id.setting_btn:
//                mRtmpBroadcast.broadcastStart(this);
                break;
        }
    }

    public void modeShowAndHide(){
        if(isAllFabsVisible){
            backgroundBlur.setVisibility(View.GONE);
            recordBtn.hide();
            liveBtn.hide();
            timeLapseBtn.hide();
            recordBtnText.setVisibility(View.GONE);
            liveBtnText.setVisibility(View.GONE);
            timeLapseBtnText.setVisibility(View.GONE);
            isAllFabsVisible = false;
        }else{
            backgroundBlur.setVisibility(View.VISIBLE);
            recordBtn.show();
            liveBtn.show();
            timeLapseBtn.show();
            recordBtnText.setVisibility(View.VISIBLE);
            liveBtnText.setVisibility(View.VISIBLE);
            timeLapseBtnText.setVisibility(View.VISIBLE);
            isAllFabsVisible = true;
        }
    }

    public void switchFragment(int fragmentNum) {
        Fragment fr;
        if (fragmentNum == 0) {
            fr = new RecordFragment();
        } else if(fragmentNum == 1){
            fr = new LiveFragment();
        }else{
            fr = new TimelapseFragment();
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentView, fr);
        fragmentTransaction.commit();
    }

    //permission check class
    void checkPermission(){
        int cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (cameraPermission == PackageManager.PERMISSION_GRANTED) {
            // ????????? ?????? ??????
        } else {
            requestPermission();
        }
    }
    void requestPermission() {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, permissions, 321);
    }

}