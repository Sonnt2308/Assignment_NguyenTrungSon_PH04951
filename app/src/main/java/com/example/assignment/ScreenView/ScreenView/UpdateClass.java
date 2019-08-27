package com.example.assignment.ScreenView.ScreenView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.R;
import com.example.assignment.ScreenView.ScreenView.danhsachlop;
import com.example.assignment.ScreenView.Adapter.CustomAdapter;
import com.example.assignment.ScreenView.Database.Class_DAO;
import com.example.assignment.ScreenView.Model.Class1;

import java.util.List;


public class UpdateClass extends AppCompatActivity {
    private List<Class1> arr;
    private CustomAdapter customAdapter;
    public ListView lv;
    private static final String selectQuery = "SELECT * FROM "+ Class1.TABLE_CLASS_NAME;

    private TextView tvIDclass;
    private EditText edtIDclass;
    private LinearLayout tvNameclass;
    private EditText edtNameclass;
    private Button btnDelAll;
    private Button btnSaveclass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_class);

        final Class_DAO classDAO = new Class_DAO(this);
        arr = classDAO.getAllClass(selectQuery);

        tvNameclass = findViewById(R.id.tvNameclass);
        tvIDclass = findViewById(R.id.tvIDclass);
        edtIDclass = findViewById(R.id.edtIDclass);

        edtNameclass = findViewById(R.id.edtNameclass);
        btnDelAll = findViewById(R.id.btnDelAll);
        btnSaveclass = findViewById(R.id.btnSaveclass);





    }
}
