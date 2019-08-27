package com.example.assignment.ScreenView.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import com.example.assignment.ScreenView.Model.Student;

public class Student_DAO {
    private SQLiteDatabase db;
    private DbHelper dbHelper;
    private static final String TAG = "StudentDAO";
    public Student_DAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<Student> getAllStudent(String sql){
        List<Student> studentList = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            Student student = new Student(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3));
            studentList.add(student);
            cursor.moveToNext();
        }
        Log.d(TAG, "get sinh vien thanh cong ");
        return studentList;
    }

    public List<Student> getStudentFiltered(int keyClass){
        String selectQuery = "SELECT * FROM "+ Student.TABLE_STUDENT_NAME +
                " WHERE "+ Student.KEY_CLASS +" = "+keyClass; // dieu kien
        List<Student> studentListFiltered = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            Student student = new Student(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3));
            studentListFiltered.add(student);
            cursor.moveToNext();
        }
        return studentListFiltered;

    }
// thêm Sinh viên
    public void addStudent(Student student){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Student.STUDENT_NAME,student.getmName());
        values.put(Student.BIRTHDAY,student.getmBirthday());
        values.put(Student.KEY_CLASS,student.getmIdClass());
        db.insert(Student.TABLE_STUDENT_NAME,null,values);
        db.close();
        Log.d(TAG, "addStudent: them sv vao db thanh cong");
    }
    // Xóa sinh viên
    public void delteStudent(int id){
        db = dbHelper.getReadableDatabase();
        db.delete(Student.TABLE_STUDENT_NAME, Student.ID_STUDENT + " = ?",new String[]{String.valueOf(id)});
    }
// Sửa thông tin sinh viên
    public void updateClass(Student student){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Student.STUDENT_NAME,student.getmName());
        values.put(Student.BIRTHDAY,student.getmBirthday());
        values.put(Student.KEY_CLASS,student.getmIdClass());
        db.update(Student.TABLE_STUDENT_NAME,values, Student.ID_STUDENT+" = ? ",new String[]{String.valueOf(student.getmID())});
        db.close();
    }

}
