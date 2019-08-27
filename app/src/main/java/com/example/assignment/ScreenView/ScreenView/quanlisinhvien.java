package com.example.assignment.ScreenView.ScreenView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.assignment.R;
import com.example.assignment.ScreenView.Adapter.CustomAdapter;
import com.example.assignment.ScreenView.Adapter.CustomAdapterForStudent;
import com.example.assignment.ScreenView.Database.Class_DAO;
import com.example.assignment.ScreenView.Database.Student_DAO;
import com.example.assignment.ScreenView.Model.Class1;
import com.example.assignment.ScreenView.Model.Student;
import com.example.assignment.ScreenView.Database.DbHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class quanlisinhvien extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private List<Student> brr;
    private List<Student> listStudentFiltered;
    private CustomAdapter customAdapter;
    public ListView lv;
    private static final String selectQuery = "SELECT * FROM " + Student.TABLE_STUDENT_NAME;
    private static final String TAG = "List_Student";
    private EditText edtName;
    private EditText edtBirthday;
    private Button btnSave;
    private Student_DAO studentDAO;
    private CustomAdapterForStudent customAdapterForStudent;
    private Spinner spnClass,spnClassUpdate;
    private List<String> listNameClass;
    private danhsachlop listClass;
    private Class_DAO classDAO;
    private Button btnClearForm;
    private Button btnSaveForm;
    private EditText edtClassName;
    private EditText edtClassKey;
    private static final String selectQueryClass = "SELECT "+ Class1.CLASS_NAME +" FROM " + Class1.TABLE_CLASS_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_student_listview);
        addWidget();
        spinerClass();
        setAdapter();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = createStudent();
                if (student == null){

                }else {
                    studentDAO.addStudent(student);
                    refresh(studentDAO);
                    clearForm();
                    Toast.makeText(quanlisinhvien.this, "Thêm Sinh Viên Thành Công", Toast.LENGTH_SHORT).show();
                }

            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int key_id = brr.get(position).getmID();
                int key_Class = brr.get(position).getmIdClass();
                showAlertDialog(studentDAO,key_id,key_Class,listNameClass);
            }
        });
    }
    public void clearForm(){
        edtName.setText("");
        edtBirthday.setText("");
    }

    public void spinerClass(){
        listNameClass = classDAO.getNameClass(selectQueryClass);
        listNameClass.add("Show_All");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, listNameClass);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnClass.setAdapter(adapter);
        spnClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int classId = classDAO.getIdClass(spnClass.getSelectedItem().toString());
                setAdapter();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void showAlertDialog(final Student_DAO studentDAO, final int key_id, final int key_Class, final List<String> list){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Bạn đang muốn: ");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                studentDAO.delteStudent(key_id);
                refresh(studentDAO);
                Toast.makeText(quanlisinhvien.this, "Đã xóa sinh viên" , Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showDialogUpdate(key_id,studentDAO,key_Class,list);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void showDialogUpdate(final int key_id, final Student_DAO studentDAO, final int key_Class,List<String> list){
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(true);
        dialog.setTitle("UPDATE STUDENT");
        dialog.setContentView(R.layout.dialog_update_student);
        dialog.show();
        btnClearForm = (Button) dialog.findViewById(R.id.btn_dialog_clear1);
        btnSaveForm = (Button) dialog.findViewById(R.id.btn_dialog_save1);
        edtClassKey = (EditText) dialog.findViewById(R.id.edt_key_class1);
        edtClassName = (EditText) dialog.findViewById(R.id.edt_name_class1);
        spnClassUpdate = (Spinner) dialog.findViewById(R.id.spn_class1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(quanlisinhvien.this,android.R.layout.simple_spinner_item, listNameClass);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnClassUpdate.setAdapter(adapter);
        final int[] classId = {0};
        spnClassUpdate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classId[0] = classDAO.getIdClass(spnClassUpdate.getSelectedItem().toString());
                //Toast.makeText(List_Student.this, classId[0] +"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSaveForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyClass = edtClassKey.getText().toString();
                String NameClass = edtClassName.getText().toString();
                if (TextUtils.isEmpty(keyClass)){
                    Toast.makeText(quanlisinhvien.this, "Vui lòng điền mã lớp", Toast.LENGTH_SHORT).show();
                    dialog.setCancelable(true);
                }else if (TextUtils.isEmpty(NameClass)){
                    Toast.makeText(quanlisinhvien.this, "Vui lòng điền tên lớp ", Toast.LENGTH_SHORT).show();
                    dialog.setCancelable(true);
                }else {
                    Student student = new Student(key_id,keyClass,NameClass, classId[0]);
                    studentDAO.updateClass(student);
                    refresh(studentDAO);
                    Toast.makeText(quanlisinhvien.this, "Update Thành Công", Toast.LENGTH_SHORT).show();
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
    //  Thêm sinh viên
    public Student createStudent(){
        String name = edtName.getText().toString();
        String birthday = edtBirthday.getText().toString();
        int classId = classDAO.getIdClass(spnClass.getSelectedItem().toString());
        if (TextUtils.isEmpty(name) &&  TextUtils.isEmpty(birthday)){
            Toast.makeText(quanlisinhvien.this, "Vui lòng nhập thông tin", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(name)){
            Toast.makeText(quanlisinhvien.this, "Vui lòng nhập tên sinh viên", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(birthday)){
            Toast.makeText(quanlisinhvien.this, "Vui lòng nhập ngày sinh", Toast.LENGTH_SHORT).show();
        }
        else {
            Student student = new Student(name,birthday,classId);
            return student;
        }
        return null;
    }

    public void refresh(Student_DAO studentDAO){
        brr.clear();
        brr.addAll(studentDAO.getAllStudent(selectQuery));
        setAdapter();
    }

    public void showAlertDialog(final int key_id, final Class_DAO classDAO){
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setCancelable(true);

        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void addWidget(){
        spnClass = (Spinner) findViewById(R.id.spn_class);
        edtName = (EditText) findViewById(R.id.edt_student_tensv);
        edtBirthday = (EditText) findViewById(R.id.edt_student_ngaysinh);
        btnSave = (Button) findViewById(R.id.btn_student_themsv);
        lv = (ListView) findViewById(R.id.lv_student_listview);
        studentDAO = new Student_DAO(this);
        classDAO = new Class_DAO(this);
        listClass = new danhsachlop();
        brr = studentDAO.getAllStudent(selectQuery);
    }


    public void setAdapter(){
        int classId = classDAO.getIdClass(spnClass.getSelectedItem().toString());
        if (classId == -1) {
            customAdapterForStudent = new CustomAdapterForStudent(this, R.layout.row_item_listview, brr);
            lv.setAdapter(customAdapterForStudent);
        }else {
            listStudentFiltered = studentDAO.getStudentFiltered(classId);
            customAdapterForStudent = new CustomAdapterForStudent(this, R.layout.row_item_listview, listStudentFiltered);
            lv.setAdapter(customAdapterForStudent);
        }
    }
//    public DatePickerDialog(Context context, DatePickerDialog.OnDateSetListener listener, int year, int month, int dayOfMonth){
//        Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        DatePickerDialog datePickerDialog = new DatePickerDialog(quanlisinhvien.this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//
//            }
//        }, year, month, day);
//    }

}