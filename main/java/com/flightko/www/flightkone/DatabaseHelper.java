package com.flightko.www.flightkone;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sourabhzalke on 15/02/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="login.db";
    public static final String TABLE_NAME = "login_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "PASSWORD";
    public static final String COL_4 = "CARNUMBER";
    public static int x;



    //Constructor will called database will gets created
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

       // SQLiteDatabase db = this.getWritableDatabase(); // to check whether table is created or not
    }

    //when onCreate is called table is created
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME+"(ID INTEGER AUTO INCREMENT,NAME TEXT PRIMARY KEY,PASSWORD TEXT,CARNUMBER TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(db);
    }

    //New Method to Insert Data
    public boolean insertData(String name,String carnumber,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,carnumber);
        contentValues.put(COL_4,password);
        ContentValues Val = new ContentValues();
        Val.put("IDD", name);
        Val.put("Category", carnumber);
        long result = db.insertWithOnConflict(TABLE_NAME, null, contentValues,SQLiteDatabase.CONFLICT_REPLACE);
        if(result==-1)
        return false;
        else
        return true;
    }

    //Viewing All Data
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    //method for updating the data
    public boolean updateData(String id,String name,String password,String flat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,password);
        contentValues.put(COL_4,flat);
        db.update(TABLE_NAME,contentValues,"id=?",new String[] {id});
        return true;
    }
    //method for deleting the data
    public Integer deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"id=?",new String[] {id});
    }

    public  Cursor getSepficItem(String name,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.rawQuery("select * from "+TABLE_NAME+" where "+COL_2+" = '"+name+"' & "+COL_3+" = '"+password+"'",null);
    }

    }

