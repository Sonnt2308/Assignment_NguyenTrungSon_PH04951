package com.example.assignment.ScreenView.Model;

public class Student {
    public static final String TABLE_STUDENT_NAME = "student_manager";
    public static final String ID_STUDENT = "id";
    public static final String STUDENT_NAME = "student_name";
    public static final String BIRTHDAY = "birthday";
    public static final String KEY_CLASS = "key_class";

    private int ID;
    private String Name;
    private String Birthday;
    private int IdClass;

    public static final String SQL_STUDENT = "CREATE TABLE "+ TABLE_STUDENT_NAME+" ("+
            ID_STUDENT + " integer primary key autoincrement, "+
            STUDENT_NAME + " TEXT, " +
            BIRTHDAY + " TEXT, "+
            KEY_CLASS + " integer, "+
            " FOREIGN KEY ( "+ KEY_CLASS +" ) REFERENCES "+ Class1.TABLE_CLASS_NAME +" ( "+ Class1
            .ID_CLASS +" ))";

    public Student(int mID, String mName, String mBirthday, int mIdClass) {
        this.ID = mID;
        this.Name = mName;
        this.Birthday = mBirthday;
        this.IdClass = mIdClass;
    }

    public Student(String mName, String mBirthday, int mIdClass) {
        this.Name = mName;
        this.Birthday = mBirthday;
        this.IdClass = mIdClass;
    }

    public Student(int key_id, String keyClass, String nameClass, int[] classId) {
    }

    public int getmID() {
        return ID;
    }

    public void setmID(int mID) {
        this.ID = mID;
    }

    public String getmName() {
        return Name;
    }

    public void setmName(String mName) {
        this.Name = mName;
    }

    public String getmBirthday() {
        return Birthday;
    }

    public void setmBirthday(String mBirthday) {
        this.Birthday = mBirthday;
    }

    public int getmIdClass() {
        return IdClass;
    }

    public void setmIdClass(int mIdClass) {
        this.IdClass = mIdClass;
    }
}
