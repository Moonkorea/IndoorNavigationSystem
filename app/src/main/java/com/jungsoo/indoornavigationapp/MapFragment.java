package com.jungsoo.indoornavigationapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;


public class MapFragment extends Fragment {

    PhotoView photoView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_map, container, false);

//        photoView = (PhotoView)rootView.findViewById(R.id.photoView);
//
//        View photoV = rootView.findViewById(R.id.photoView);
//        View currentPosV = rootView.findViewById(R.id.currentPosIcon);
//
//        // 사용자 현재 위치를 포토뷰의 좌표로 놓기
//        currentPosV.setX(photoV.getLeft()+200);
//        currentPosV.setY(photoV.getTop()+350);

        return rootView;
    }

    // 이미지 설정
    public void setImage(int resId) {
        photoView.setImageResource(resId);
    }
}