package com.example.lenarv0203;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class RecordFragment extends Fragment implements View.OnClickListener {

    LinearLayout recordDetailLayout, recordDetailMiniLayout;
    ImageView detailBtn, recordStartStopBtn;
    Boolean isDetailOpened, isRecording;
    Spinner resSpinner, fpsSpinner, bitSpinner;
    TextView recordTimeText;
    RtspRecord mRtspRecord = new RtspRecord();


    public RecordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.record_fragment, container, false);
        //detail layout and button
        recordDetailLayout = view.findViewById(R.id.record_detail_layout);
        recordDetailMiniLayout = view.findViewById(R.id.record_detail_mini_layout);
        detailBtn = view.findViewById(R.id.detail_btn);
        detailBtn.setOnClickListener(this);
        //boolean
        isDetailOpened = false;
        isRecording = false;
        //record start stop btn
        recordStartStopBtn = view.findViewById(R.id.record_start_stop_btn);
        recordStartStopBtn.setOnClickListener(this);
        //background blurring

        //record value textviews
        recordTimeText = view.findViewById(R.id.record_time_text);

        //spinner init
        ArrayAdapter resAdapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.record_res, R.layout.spinner_item);
        ArrayAdapter fpsAdapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.record_fps, R.layout.spinner_item);
        ArrayAdapter bitAdapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.record_bit, R.layout.spinner_item);
        resAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fpsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        resSpinner = view.findViewById(R.id.res_spinner);
        fpsSpinner = view.findViewById(R.id.fps_spinner);
        bitSpinner = view.findViewById(R.id.bit_spinner);

        resSpinner.setAdapter(resAdapter);
        resSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        fpsSpinner.setAdapter(fpsAdapter);
        fpsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        bitSpinner.setAdapter(bitAdapter);
        bitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_btn:
                if (isDetailOpened) {
                    recordDetailLayout.setVisibility(View.GONE);
                    recordDetailMiniLayout.setVisibility(View.VISIBLE);
                    isDetailOpened = false;
                } else {
                    recordDetailLayout.setVisibility(View.VISIBLE);
                    recordDetailMiniLayout.setVisibility(View.GONE);
                    isDetailOpened = true;
                }
                break;
            case R.id.record_start_stop_btn:
                if(isRecording){
                    mRtspRecord.recordStop(getActivity().getBaseContext());
                    recordStartStopBtn.setImageResource(R.drawable.ic_record_start);
                    isRecording = false;
                }else{
                    mRtspRecord.recordStart(getActivity().getBaseContext());
                    recordStartStopBtn.setImageResource(R.drawable.ic_record_stop);
                    isRecording = true;
                    Timer timer = new Timer();
                    timer.start();
                }
                break;
        }
    }

    public class Timer extends Thread {
        final Handler handler = new Handler();
        @Override
        public void run() {
            final long startTime = System.currentTimeMillis();
            while (isRecording) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {  // 화면에 그려줄 작업
                        recordTimeText.setText(mRtspRecord.recordTimeCal(startTime));
                    }
                });
            }
            handler.post(new Runnable() {
                @Override
                public void run() {  // 화면에 그려줄 작업
                    recordTimeText.setText("00:00:00");
                }
            });
        }
    }

}
