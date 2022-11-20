package com.jungsoo.indoornavigationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchListActivity extends AppCompatActivity {

    // 목적지 리스트 선언
    public static ArrayList<destination> destinationList = new ArrayList<destination>();

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        // 파이어베이스 데이터 셋업
        setUpData();

        // 검색창 리스트 셋업
        setUpList();

        // 목적지 검색창 동작 함수
        searchDestination();

        // 검색창 입력 전 원본 리스트에서 목적지 선택하여 검색창 자동 완성 함수 호출
        autoCompleteSearchView(destinationList);
    }

    // 목적지 리스트 선택 시 검색창 자동 완성 함수
    private void autoCompleteSearchView(ArrayList<destination> filterDestination) {
        SearchView arriveSearch = findViewById(R.id.arriveSearch);
        listView = findViewById(R.id.destList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedDestination = filterDestination.get(i).getRoomName();
                arriveSearch.setQuery(selectedDestination, true);
            }
        });
    }



    // 목적지 검색창 동작 함수
    private void searchDestination() {
        SearchView arriveSearch = findViewById(R.id.arriveSearch);
        listView = findViewById(R.id.destList);

        // searchView 전체를 누를 수 있도록 구현
        arriveSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arriveSearch.setIconified(false);
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
        arriveSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

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

                DestinationAdapter adapter = new DestinationAdapter(getApplicationContext(), 0, filterDestination);
                listView.setAdapter(adapter);

                // 검색창 입력 후 필터 리스트에서 목적지 선택하여 검색창 자동 완성 함수 호출
                autoCompleteSearchView(filterDestination);

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
    private void setUpList() {
        listView = findViewById(R.id.destList);

        DestinationAdapter adapter = new DestinationAdapter(getApplicationContext(), 0, destinationList);
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

    // 뒤로가기 구현
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}