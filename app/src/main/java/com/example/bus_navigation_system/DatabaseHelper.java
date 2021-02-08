package com.example.bus_navigation_system;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper<pubic> extends SQLiteOpenHelper {
    private Context context;
    final static String DATABASE_NAME = "Bus.db";
    final static String TABLE_NAME = "Bus_Details_table";
    final static String COL_1 = "ID";
    final static String COL_2 = "Bus_No";
    final static String COL_3 = "Name";
    final static String COL_4 = "Contact_No";
    final static String COL_5 = "Reg_No";
    final static String COL_6 = "Source";
    final static String COL_7 = "Destination";

    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("Create Table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, BUS_NO INTEGER, NAME TEXT, REG_NO TEXT, CONTACT_NO INTEGER, SOURCE TEXT, DESTINATION TEXT)");
        db.execSQL("Create Table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, BUS_NO INTEGER, Name TEXT, CONTACT_NO INTEGER, REG_NO TEXT, SOURCE TEXT, DESTINATION TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(String bus_no, String name, String contact_no, String regno, String source, String destination){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(COL_2,bus_no);
        contentValues.put(COL_3,name);
        contentValues.put(COL_4,contact_no);
        contentValues.put(COL_5,regno);
        contentValues.put(COL_6,source);
        contentValues.put(COL_7,destination);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * From "+TABLE_NAME,null);
        return res;
    }
    public boolean updateData(String row_id, String source, String dest, String name, String contact, String reg_no){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_6, source);
        cv.put(COL_7,dest);
        cv.put(COL_3,name);
        cv.put(COL_4,contact);
        cv.put(COL_5,reg_no);
        db.update(TABLE_NAME,cv,"Bus_No=?",new String[]{row_id});
        return true;
    }
    public Integer deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"Bus_No=?",new String[]{row_id});

    }
}
