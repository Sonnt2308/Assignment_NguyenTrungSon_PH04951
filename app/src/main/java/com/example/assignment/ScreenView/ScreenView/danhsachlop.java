package com.example.assignment.ScreenView.ScreenView;

/*
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.R;
import com.example.assignment.ScreenView.Adapter.CustomAdapter;
import com.example.assignment.ScreenView.Database.Class_DAO;
import com.example.assignment.ScreenView.Model.Class1;
import com.example.assignment.ScreenView.ScreenView.UpdateClass;

import java.util.ArrayList;
import java.util.List;

public class danhsachlop extends AppCompatActivity {
    private List<Class1> arr;
    private CustomAdapter customAdapter;
    public ListView lv;
    private static final String selectQuery = "SELECT * FROM " + Class1.TABLE_CLASS_NAME;

    private TextView tvIDclass;
    private EditText edtIDclass;
    private LinearLayout tvNameclass;
    private EditText edtNameclass;
    private Button btnDelAll;
    private Button btnSaveclass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachlop);
        final Class_DAO classDAO = new Class_DAO(this);
        arr = classDAO.getAllClass(selectQuery);

        setAdapter();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int key_id = arr.get(position).getmId();
                showAlertDialog(key_id, classDAO);


            }
        });

//        lv_class = findViewById(R.id.lv_class);
//        final ArrayAdapter<lop> adapter = new ArrayAdapter<lop>(this, android.R.layout.simple_list_item_1, lopList);
//        lv_class.setAdapter(adapter);
//        lopList = new ArrayList<>();

    }

    public void showAlertDialog(final int key_id, final Class_DAO classDAO) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Chọn một hành động: ");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                classDAO.deleteClass(key_id);
                refresh(classDAO);
                Toast.makeText(danhsachlop.this, "Da Xoa Thanh Cong", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                showDialogUpdate(key_id, classDAO);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void refresh(Class_DAO classDAO) {
        arr.clear();
        arr.addAll(classDAO.getAllClass(selectQuery));
        setAdapter();
    }

    private void addWidget() {
        lv = (ListView) findViewById(R.id.lv_listview);
    }

    public void setAdapter() {
        customAdapter = new CustomAdapter(this, R.layout.row_item_listview, arr);
        lv.setAdapter(customAdapter);
    }

    public void showDialogUpdate(final int key_id, final Class_DAO classDAO) {
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(true);
        dialog.setTitle("UPDATE Thông tin lớp");
        dialog.setContentView(R.layout.activity_update_class);
        dialog.show();

        edtIDclass = dialog.findViewById(R.id.edtIDclass);
        edtNameclass = dialog.findViewById(R.id.edtNameclass);
        btnDelAll = dialog.findViewById(R.id.btnDelAll);
        btnSaveclass = dialog.findViewById(R.id.btnSaveclass);
        btnSaveclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(danhsachlop.this);
                dialog.setCancelable(true);
                dialog.setTitle("UPDATE");
                dialog.setContentView(R.layout.activity_update_class);
                dialog.show();
                String keyClass = edtIDclass.getText().toString();
                String NameClass = edtNameclass.getText().toString();
                if (TextUtils.isEmpty(keyClass) && TextUtils.isEmpty(NameClass)) {
                    Toast.makeText(danhsachlop.this, "Vui lòng nhập mã lớp và tên lớp", Toast.LENGTH_SHORT).show();
                    dialog.setCancelable(true);
                } else if (TextUtils.isEmpty(keyClass)) {
                    Toast.makeText(danhsachlop.this, "Vui lòng nhập tên lớp ", Toast.LENGTH_SHORT).show();
                    dialog.setCancelable(true);
                } else if (TextUtils.isEmpty(NameClass)) {
                    Toast.makeText(danhsachlop.this, "Vui lòng nhập mã lớp", Toast.LENGTH_SHORT).show();
                    dialog.setCancelable(true);
                } else {
                    Class1 classModel = new Class1(key_id, keyClass, NameClass);
                    classDAO.updateClass(classModel);
                    refresh(classDAO);
                    Toast.makeText(danhsachlop.this, "Update Thông Tin Lớp Thành Công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
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
}
*/

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.assignment.R;
import com.example.assignment.ScreenView.Adapter.CustomAdapter;
import com.example.assignment.ScreenView.Database.Class_DAO;
import com.example.assignment.ScreenView.Model.Class1;

import java.util.ArrayList;
import java.util.List;



public class danhsachlop extends AppCompatActivity {
    private List<Class1> arr;
    private CustomAdapter customAdapter;
    public ListView lv;
    private static final String selectQuery = "SELECT * FROM "+ Class1.TABLE_CLASS_NAME;
    private Button btnClearForm;
    private Button btnSaveForm;
    private EditText edtClassName;
    private EditText edtClassKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_show_listclass);
        final Class_DAO classDAO = new Class_DAO(this);
        arr = classDAO.getAllClass(selectQuery);
        addWidget();
        setAdapter();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int key_id = arr.get(position).getmId();
                showAlertDialog(key_id,classDAO);
            }
        });

    }
    public void refresh(Class_DAO classDAO){
        arr.clear();
        arr.addAll(classDAO.getAllClass(selectQuery));
        setAdapter();
    }

    public void showAlertDialog(final int key_id, final Class_DAO classDAO){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Bạn đang muốn: ");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                classDAO.deleteClass(key_id);
                refresh(classDAO);
                Toast.makeText(danhsachlop.this, "Đã xóa lớp thành công!!!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Sửa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showDialogUpdate(key_id,classDAO);
            }
        });

//        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                refresh(classDAO);
//            }
//        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void showDialogUpdate(final int key_id, final Class_DAO classDAO){
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(true);
        dialog.setTitle("UPDATE");
        dialog.setContentView(R.layout.dialog_add_class);
        dialog.show();
        btnClearForm = (Button) dialog.findViewById(R.id.btn_dialog_clear);
        btnSaveForm = (Button) dialog.findViewById(R.id.btn_dialog_save);
        edtClassKey = (EditText) dialog.findViewById(R.id.edt_key_class);
        edtClassName = (EditText) dialog.findViewById(R.id.edt_name_class);
        btnSaveForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyClass = edtClassKey.getText().toString();
                String NameClass = edtClassName.getText().toString();
                if (TextUtils.isEmpty(keyClass) && TextUtils.isEmpty(NameClass)){
                    Toast.makeText(danhsachlop.this, "Vui lòng nhập tên lớp và mã lớp ! ", Toast.LENGTH_SHORT).show();
                    dialog.setCancelable(true);
                }else if (TextUtils.isEmpty(keyClass)){
                    Toast.makeText(danhsachlop.this, "Vui lòng nhập mã lớp", Toast.LENGTH_SHORT).show();
                    dialog.setCancelable(true);
                }
                else if (TextUtils.isEmpty(NameClass)){
                    Toast.makeText(danhsachlop.this, "Vui lòng nhập tên lớp ", Toast.LENGTH_SHORT).show();
                    dialog.setCancelable(true);
                }
                else {
                    Class1 classModel = new Class1(key_id ,keyClass,NameClass);
                    classDAO.updateClass(classModel);
                    refresh(classDAO);
                    Toast.makeText(danhsachlop.this, "Update Thành Công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });



        btnClearForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtClassKey.setText("");
                edtClassName.setText("");
            }
        });

    }

    private void addWidget(){
        lv = (ListView) findViewById(R.id.lv_listview);
    }

    public void setAdapter(){
        customAdapter = new CustomAdapter(this,R.layout.row_item_listview,arr);
        lv.setAdapter(customAdapter);
    }
}

