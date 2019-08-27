package com.example.assignment.ScreenView.ScreenView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.assignment.R;

public class MainActivity extends AppCompatActivity {

    private Button btnaddstudent;
    private Button btndanhsachlop;
    private Button btnquanlysinhvien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnaddstudent = findViewById(R.id.btnaddstudent);
        btndanhsachlop = findViewById(R.id.btndanhsachlop);
        btnquanlysinhvien = findViewById(R.id.btnquanlysinhvien);

        btnaddstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Dialog_Activity1.class);
                startActivity(intent);
            }
        });

        btndanhsachlop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, danhsachlop.class);
                startActivity(intent);
            }
        });

        btnquanlysinhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, quanlisinhvien.class);
                startActivity(intent);
            }
        });

    }
}
