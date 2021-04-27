package com.example.lenarv0203;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import static com.example.lenarv0203.MainActivity.mainContext;

public class RecordFragment extends Fragment implements View.OnClickListener {

    ConstraintLayout recordDetailLayout;
    ImageView detailBtn;
    Boolean isDetailOpened;
    Spinner resSpinner,fpsSpinner,bitSpinner;


    public RecordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.record_fragment, container, false);
        //detail layout and button
        recordDetailLayout = view.findViewById(R.id.record_detail_layout);
        detailBtn = view.findViewById(R.id.detail_btn);
        detailBtn.setOnClickListener(this);
        //detail boolean
        isDetailOpened = false;

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
            } //이 오버라이드 메소드에서 position은 몇번째 값이 클릭됬는지 알 수 있습니다.
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        fpsSpinner.setAdapter(fpsAdapter); //어댑터에 연결해줍니다.
        fpsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            } //이 오버라이드 메소드에서 position은 몇번째 값이 클릭됬는지 알 수 있습니다.
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        bitSpinner.setAdapter(bitAdapter); //어댑터에 연결해줍니다.
        bitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            } //이 오버라이드 메소드에서 position은 몇번째 값이 클릭됬는지 알 수 있습니다.
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
                    isDetailOpened = false;
                }else{
                    recordDetailLayout.setVisibility(View.VISIBLE);
                    isDetailOpened = true;
                }
                break;
        }
    }
}
