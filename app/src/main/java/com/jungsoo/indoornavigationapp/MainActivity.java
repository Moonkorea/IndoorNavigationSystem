package com.jungsoo.indoornavigationapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// photoview 확대, 축소 관련
import com.github.chrisbanes.photoview.PhotoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // photoview 확대, 축소 구현
        //PhotoView photoView = findViewById(R.id.photoView);
        //photoView.setImageResource(R.drawable.mapsample);

        // 실내 길 안내 서비스 시작 버튼 누르면 맵 액티비티로 이동
        Button indoorMapServiceStartBtn = findViewById(R.id.indoorMapServiceStartBtn);
        indoorMapServiceStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

    }
}