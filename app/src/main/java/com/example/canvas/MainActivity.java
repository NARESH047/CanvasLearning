package com.example.canvas;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.canvas.views.CustomView;

public class MainActivity extends AppCompatActivity {

//    private CustomView mCustomView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomView mCustomView = (CustomView) findViewById(R.id.Canvas);

        findViewById(R.id.SwapColor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCustomView.SwapColor();

            }
        });

    }
}
