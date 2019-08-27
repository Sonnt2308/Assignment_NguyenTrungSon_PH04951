package com.example.assignment.ScreenView.Model;

public class Class1 {
    public static final String TABLE_CLASS_NAME = "class_manager";
    public static final String ID_CLASS = "id";
    public static final String KEY_CLASS = "key_class";
    public static final String CLASS_NAME = "class_name";

    private int mId;
    private String KeyClass;
    private String ClassName;

    public static final String SQL_CLASS = "CREATE TABLE "+ TABLE_CLASS_NAME+" ("+
            ID_CLASS + " integer primary key autoincrement, "+
            KEY_CLASS + " TEXT, " +
            CLASS_NAME + " TEXT) ";

    public Class1() {
    }

    public Class1(String KeyClass, String ClassName) {
        this.KeyClass = KeyClass;
        this.ClassName = ClassName;
    }

    public Class1(int mId, String KeyClass, String ClassName) {
        this.mId = mId;
        this.KeyClass = KeyClass;
        this.ClassName = ClassName;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getKeyClass() {
        return KeyClass;
    }

    public void setKeyClass(String KeyClass) {
        this.KeyClass = KeyClass;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String ClassName) {
        this.ClassName = ClassName;
    }
}


