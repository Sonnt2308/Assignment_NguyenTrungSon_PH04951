package com.example.assignment.ScreenView.ScreenView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.R;
import com.example.assignment.ScreenView.Model.Class1;
import com.example.assignment.ScreenView.Database.Class_DAO;
import com.example.assignment.ScreenView.Database.DbHelper;





public class Dialog_Activity1 extends AppCompatActivity {

    private TextView tvIDclass;
    private EditText edtIDclass;
    private LinearLayout tvNameclass;
    private EditText edtNameclass;
    private Button btnDelAll;
    private Button btnSaveclass;
    private Class_DAO classDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_1);

        final DbHelper dbHelper = new DbHelper(this);
        classDAO = new Class_DAO(this);


        tvIDclass = findViewById(R.id.tvIDclass);
        edtIDclass = findViewById(R.id.edtIDclass);
        tvNameclass = findViewById(R.id.tvNameclass);
        edtNameclass = findViewById(R.id.edtNameclass);
        btnDelAll = findViewById(R.id.btnDelAll);
        btnSaveclass = findViewById(R.id.btnSaveclass);



        edtIDclass = findViewById(R.id.edtIDclass);
        edtNameclass = findViewById(R.id.edtNameclass);
        btnSaveclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyClass = edtIDclass.getText().toString();
                String NameClass = edtNameclass.getText().toString();
                if (TextUtils.isEmpty(keyClass) && TextUtils.isEmpty(NameClass)){
                    Toast.makeText(Dialog_Activity1.this, "Vui lòng nhập mã lớp và tên lớp", Toast.LENGTH_SHORT).show();

                }else if (TextUtils.isEmpty(keyClass)){
                    Toast.makeText(Dialog_Activity1.this, "Vui lòng nhập tên lớp ", Toast.LENGTH_SHORT).show();

                }
                else if (TextUtils.isEmpty(NameClass)){
                    Toast.makeText(Dialog_Activity1.this, "Vui lòng nhập mã lớp", Toast.LENGTH_SHORT).show();
                }
                else  {
                    Class1 classModel = new Class1(keyClass,NameClass);
                    classDAO.addClass(classModel);
                    Toast.makeText(Dialog_Activity1.this, "Thêm Lớp thành công !!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Dialog_Activity1.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });




        btnDelAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtIDclass.setText("");
                edtNameclass.setText("");
            }
        });
    }
    public Class1 createClass(){
        String keyClass = edtIDclass.getText().toString();
        String NameClass = edtNameclass.getText().toString();
        Class1 classModel = new Class1(keyClass,NameClass);
        return classModel;
    }
}
