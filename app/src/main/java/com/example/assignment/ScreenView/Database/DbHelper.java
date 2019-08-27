package com.example.assignment.ScreenView.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.assignment.ScreenView.Model.Class1;
import  com.example.assignment.ScreenView.Model.Student;


public class DbHelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "assigment1_qlsv";
    private Context context;

    private static final int VERSION = 1 ;
    private static final String TAG = "DbHelper";
    private SQLiteDatabase db;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Class1.SQL_CLASS);
        db.execSQL(Student.SQL_STUDENT);
        Log.d(TAG, "onCreate: Tao Database Thanh Cong");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_class_table = "DROP TABLE IF EXISTS "+ Class1.TABLE_CLASS_NAME;
        db.execSQL(drop_class_table);
        String drop_student_table = "DROP TABLE IF EXISTS "+ Student.TABLE_STUDENT_NAME;
        db.execSQL(drop_student_table);
        onCreate(db);
        Log.d(TAG, "Xoa va tao lai bang ");
    }


}
