package com.jungsoo.indoornavigationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

public class MapActivity extends AppCompatActivity implements MapBtnFragment.ImageSelectionCallback{

    private FragmentManager fragmentManager;
    private MapFragment mapfragment;
    private MapBtnFragment mapbtnfragment;
    private SearchFragment searchfragment;

    // 3층, 4층 맵 이미지 저장 배열
    int []mapImages = {R.drawable.mapsample, R.drawable.mapsample4};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        fragmentManager = getSupportFragmentManager();


        //mapfragment = (MapFragment) fragmentManager.findFragmentById(R.id.mapFragment);
        mapbtnfragment = (MapBtnFragment) fragmentManager.findFragmentById(R.id.mapBtnFragment);
        //searchfragment = (SearchFragment) fragmentManager.findFragmentById(R.id.searchFragment);

        Button searchBtn = findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MapActivity.this, SearchListActivity.class);
                startActivity(myIntent);
                //finish(); // 현재 액티비티 종료
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();




    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
//        //프래그먼트 onBackPressedListener사용
//        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
//        for(Fragment fragment : fragmentList){
//            if(fragment instanceof onBackPressedListener){
//                ((onBackPressedListener)fragment).onBackPressed();
//                return;
//            }
//        }

        //두 번 클릭시 어플 종료
//        if(System.currentTimeMillis() - lastTimeBackPressed < 1500){
//            finish();
//            return;
//        }
//        lastTimeBackPressed = System.currentTimeMillis();
//        Toast.makeText(this,"'뒤로' 버튼을 한 번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();

    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // 시스템 Back 버튼 호출 시
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            Toast.makeText(this, "뒤로가기 버튼이 눌렸습니다.", Toast.LENGTH_LONG).show();
//            return true;
//        }
//        // return super.onKeyDown(keyCode, event); // 코드 제거 시 뒤로가기 기능이 수행되지 않음
//        return true;
//    }

    // mapImage 이미지 뷰에 지도 이미지 세팅하는 콜백 함수
    @Override
    public void onImageSelected(int position) {
        ImageView mapImage = findViewById(R.id.mapImage);
        mapImage.setImageResource(mapImages[position]);
        //mapfragment.setImage(mapImages[position]);

        ImageView currentPosIcon = findViewById(R.id.currentPosIcon);
        currentPosIcon.setImageResource(R.drawable.currentposxml);
        View currentPosV = findViewById(R.id.currentPosIcon);
        View mapV = findViewById(R.id.mapImage);

        currentPosV.setX(mapV.getLeft()+500);
        currentPosV.setY(mapV.getTop()+500);

        ObjectAnimator animation = ObjectAnimator.ofFloat(currentPosV, "translationY", 600f);
        animation.setDuration(2000);
        animation.start();



    }
}