package com.jungsoo.indoornavigationapp;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;


public class SearchListFragment extends Fragment implements onBackPressedListener {

    // 목적지 리스트 선언
    public static ArrayList<destination> destinationList = new ArrayList<destination>();

    MapActivity activity;

    ListView listView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_search_list, container, false);

        // 파이어베이스 데이터 셋업
        setUpData();

        // 검색창 리스트 셋업
        setUpList(rootView);

        // 목적지 검색창 동작 함수
        searchDestination(rootView);

        Button backBtn = rootView.findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                transaction.remove(SearchListFragment.this).commit();
                fragmentManager.popBackStack();
            }
        });


        return rootView;
    }

    @Override
    public void onBackPressed() {
        goToMain();
    }

    //프래그먼트 종료
    private void goToMain(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().remove(SearchListFragment.this).commit();
        //SearchListFragment.this = null;
        //fragmentManager.popBackStack();
    }

    // 목적지 검색창 동작 함수
    private void searchDestination(ViewGroup rootView) {
        SearchView searchView = rootView.findViewById(R.id.searchView);
        listView = rootView.findViewById(R.id.destList);

        // searchView 전체를 누를 수 있도록 구현
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setIconified(false);
                listView.setVisibility(View.VISIBLE);
            }
        });

          // 검색창 X 버튼
//        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
//            @Override
//            public boolean onClose() {
//                listView.setVisibility(View.INVISIBLE);
//                return true;
//            }
//        });


        // searchView의 검색 이벤트
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            // 키보드의 검색 버튼을 눌렀을 경우
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 검색창의 텍스트가 바뀔 때마다 호출
            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<destination> filterDestination = new ArrayList<>();

                for (int i = 0; i < destinationList.size(); ++i) {

                    destination dest = destinationList.get(i);

                    //데이터와 비교해서 내가 쓴 호실 번호 혹은 호실 이름이 있다면
                    if(dest.getRoomName().toLowerCase().contains(newText.toLowerCase()) || dest.getRoomNum().toLowerCase().contains(newText.toLowerCase())){

                        filterDestination.add(dest);
                    }
                }

                DestinationAdapter adapter = new DestinationAdapter(getActivity().getApplicationContext(), 0, filterDestination);
                listView.setAdapter(adapter);

                return false;
            }
        });
    }


    // 파이어베이스 데이터 셋업
    private void setUpData() {
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        //DatabaseReference thirdFloorRef = mRootRef.child("3");
        //DatabaseReference fourthFloorRef = mRootRef.child("4");
        for (int i = 301; i < 330; ++i) {
            int from = i;
            String to = Integer.toString(from);
            DatabaseReference FloorRef = mRootRef.child("3").child(to).child("name");

            FloorRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String floorStr = dataSnapshot.getValue(String.class);
                    destination test = new destination(to, floorStr);
                    destinationList.add(test);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        for (int i = 401; i < 431; ++i) {
            if (i == 413)
                continue;
            int from = i;
            String to = Integer.toString(from);
            DatabaseReference FloorRef = mRootRef.child("4").child(to).child("name");

            FloorRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String floorStr = dataSnapshot.getValue(String.class);
                    destination test = new destination(to, floorStr);
                    destinationList.add(test);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }



    }

    // 검색창 리스트 셋업
    private void setUpList(ViewGroup rootView) {
        listView = rootView.findViewById(R.id.destList);

        DestinationAdapter adapter = new DestinationAdapter(getActivity().getApplicationContext(), 0, destinationList);
        listView.setAdapter(adapter);
    }

//    public void onBackPressed() {
//        activity.setCurrentItem();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        activity.setOnBackPressedListener(this);
//    }
}