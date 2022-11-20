package com.jungsoo.indoornavigationapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MapBtnFragment extends Fragment {

    public ImageSelectionCallback callback;

    // 인터페이스 - 탭 클릭 시 이미지 변경하기 위한 인터페이스
    // 여러 액티비티가 프래그먼트를 호출하여도 동일한 인터페이스를 구현하도록 한다.
    public static interface ImageSelectionCallback {
        public void onImageSelected(int position);
    }

    // 프래그먼트가 액티비티에 연결되었을 때 호출
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ImageSelectionCallback) {
            callback = (ImageSelectionCallback)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_map_btn, container, false);

        Button thirdFloorBtn = (Button) rootView.findViewById(R.id.thirdFloorBtn);
        Button fourthFloorBtn = (Button) rootView.findViewById(R.id.fourthFloorBtn);

        // 이벤트
        thirdFloorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onImageSelected(0);
            }
        });
        fourthFloorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onImageSelected(1);
            }
        });

        return rootView;
    }
}