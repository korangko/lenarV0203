package com.example.lenarv0203;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import static com.example.lenarv0203.MainActivity.mainContext;

public class RecordFragment extends Fragment implements View.OnClickListener {

    ConstraintLayout recordDetailLayout;
    LinearLayout recordDetailMiniLayout;
    ImageView detailBtn;
    Boolean isDetailOpened;
    Spinner resSpinner,fpsSpinner,bitSpinner;
    TextView recordResValue, recordFpsValue, recordBitValue;
    int recRes, recFps, recBit;


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
        //detail boolean
        isDetailOpened = false;
        //resolution fps bit init
        recRes = 720;
        recFps = 30;
        recBit = 10;
        //record value textviews
        recordResValue = view.findViewById(R.id.record_res_text);
        recordFpsValue = view.findViewById(R.id.record_fps_text);
        recordBitValue = view.findViewById(R.id.record_bit_text);

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

        resSpinner.setAdapter(resAdapter); //어댑터에 연결해줍니다.
        resSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                recordResValue.setText(resSpinner.getItemIdAtPosition(position)+"p");
                recordResValue.setText(resSpinner.getSelectedItem()+"p");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        fpsSpinner.setAdapter(fpsAdapter); //어댑터에 연결해줍니다.
        fpsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                recordFpsValue.setText(fpsSpinner.getSelectedItem()+"fps");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        bitSpinner.setAdapter(bitAdapter); //어댑터에 연결해줍니다.
        bitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                recordBitValue.setText(bitSpinner.getSelectedItem()+"Mbps");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
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
                if(isDetailOpened){
                    recordDetailLayout.setVisibility(View.GONE);
                    recordDetailMiniLayout.setVisibility(View.VISIBLE);
                    isDetailOpened = false;
                }else{
                    recordDetailLayout.setVisibility(View.VISIBLE);
                    recordDetailMiniLayout.setVisibility(View.GONE);
                    isDetailOpened = true;
                }
                break;
        }
    }
}
