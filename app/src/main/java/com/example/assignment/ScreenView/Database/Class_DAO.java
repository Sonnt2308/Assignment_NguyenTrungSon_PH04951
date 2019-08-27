package com.example.assignment.ScreenView.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignment.ScreenView.Model.Class1;

import java.util.ArrayList;
import java.util.List;

public class Class_DAO {
    private SQLiteDatabase db;
    private DbHelper dbHelper;

    public Class_DAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();

    }

    public List<Class1> getAllClass(String sql) {
        // lay danh sach lop trong bang roi dua vao list;
        List<Class1> classList = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Class1 newClass = new Class1(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            classList.add(newClass);
            cursor.moveToNext();
        }
        return classList;
    }

    public List<String> getNameClass(String sql) {
        List<String> listName = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            //String name = cursor.getString(cursor.getColumnIndex(Class_model.CLASS_NAME));
            // getColumIndex(ten_cot) la get cac phan tu cua cot dua vao ten_cot;
            String name = new String(cursor.getString(0));
            listName.add(name);
            cursor.moveToNext();
        }
        return listName;
    }

    public int getIdClass(String className) {
        int classId;
        String selectQuery = "SELECT "+ Class1.ID_CLASS +" FROM "+ Class1.TABLE_CLASS_NAME +
                " WHERE "+ Class1.CLASS_NAME +" = '"+className+"'"; // dieu kien
        db = dbHelper.getReadableDatabase();
        if (className.equals("Show_All")){
            classId = -1;
        }else {
            Cursor cursor = db.rawQuery(selectQuery, null);
            cursor.moveToFirst();
            classId = cursor.getInt(cursor.getColumnIndex(Class1.ID_CLASS));
            //classId = new Integer(cursor.getInt(0));
        }

        return classId;
    }

    public void addClass(Class1 classModel) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(classModel.KEY_CLASS, classModel.getKeyClass());
        values.put(classModel.CLASS_NAME, classModel.getClassName());
        db.insert(classModel.TABLE_CLASS_NAME, null, values);
        db.close();
    }

    public void deleteClass(int key_id) {
        db = dbHelper.getReadableDatabase();
        db.delete(Class1.TABLE_CLASS_NAME, Class1.ID_CLASS + " = ?", new String[]{String.valueOf(key_id)});
        db.close();
    }

    public void updateClass(Class1 classModel){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(classModel.KEY_CLASS, classModel.getKeyClass());
        values.put(classModel.CLASS_NAME, classModel.getClassName());
        db.update(Class1.TABLE_CLASS_NAME,values, Class1.ID_CLASS+" = ? ",new String[]{String.valueOf(classModel.getmId())});
        db.close();
    }

}
