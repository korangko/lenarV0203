package com.example.lenarv0203;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Time;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton recordBtn, liveBtn, timeLapseBtn;
    TextView recordBtnText, liveBtnText, timeLapseBtnText, modeSelectText;
    Boolean isAllFabsVisible;
    static Context mainContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainContext = this;

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
        recordBtnText =findViewById(R.id.record_btn_text);
        liveBtnText = findViewById(R.id.live_btn_text);
        timeLapseBtnText = findViewById(R.id.timelapse_btn_text);
        //floating boolean
        isAllFabsVisible = false;
        //fragment initiating
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.fragmentView, new RecordFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mode_select_text:
                modeShowAndHide();
                break;
            case R.id.record_btn:
                modeShowAndHide();
                switchFragment(0);
                modeSelectText.setText("RECORD");
                break;
            case R.id.live_btn:
                modeShowAndHide();
                switchFragment(1);
                modeSelectText.setText("LIVE STREAM");
                break;
            case R.id.timelapse_btn:
                modeShowAndHide();
                switchFragment(2);
                modeSelectText.setText("TIMELAPSE");
                break;
        }
    }

    public void modeShowAndHide(){
        if(isAllFabsVisible){
            recordBtn.hide();
            liveBtn.hide();
            timeLapseBtn.hide();
            recordBtnText.setVisibility(View.GONE);
            liveBtnText.setVisibility(View.GONE);
            timeLapseBtnText.setVisibility(View.GONE);
            isAllFabsVisible = false;
        }else{
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

}