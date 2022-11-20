package com.jungsoo.indoornavigationapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class SearchFragment extends Fragment {

    private static final String TAG = "SearchFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_search, container, false);

        SearchView searchView = rootView.findViewById(R.id.searchView);

        //FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();


        // searchView 전체를 누를 수 있도록 구현
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setIconified(false);

                // SearchListFragment로 이동
                SearchListFragment searchListFragment = new SearchListFragment();
                transaction.replace(R.id.searchFragment, searchListFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }

        });

        return rootView;
    }
}