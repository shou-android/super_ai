package com.ai.ai.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Contacts;

import  com.ai.ai.lesson.clesson;



import androidx.annotation.Nullable;

public class DBAdapter {
    private static final String DB_NAME="lesson.db";
    private static final String DB_TABLE="lessoninfo";
    private static final int DB_VERSION=1;

    private static final String KEY_ID="_id";
    private static final String KEY_NAME="cname";
    private static final String KEY_LOC="locat";
    private static final String KEY_TN="tname";
    private static final String KEY_TD="timedata";

    private SQLiteDatabase db;
    private final Context context;
    private DBOpenHelper dbOpenHelper;


    public long insert1(clesson lesson){
        ContentValues newValues=new ContentValues();

        newValues.put(KEY_NAME,lesson.getCname());
        newValues.put(KEY_LOC,lesson.getLocat());
        newValues.put(KEY_TN,lesson.getTname());
        newValues.put(KEY_ID,lesson.getCno());
        newValues.put(KEY_TD,lesson.getTimedata());

        return db.insert(DB_TABLE,null,newValues);//insert(DB_TABLE,null,newValues);
    }
    /*
    public long deleteAllData(){
        return db.delete(DB_TABLE,null,null);
    }
    public long deleteOneData(long id){
        return db.delete(DB_TABLE,KEY_ID+"=",null);
    }
    public People[] queryAllData(){
        Cursor results=db.query(DB_TABLE,new String[]{KEY_ID,KEY_NAME,KEY_AGE,KEY_DEP},null,null,null,null,null);
        return ConvertToPeople(results);
    }
    public People[] queryOneDate(long id){
        Cursor results=db.query(DB_TABLE,new String[]{KEY_ID,KEY_NAME,KEY_AGE,KEY_DEP},KEY_ID+"="+id,null,null,null,null);
        return ConvertToPeople(results);
    }
    public long updateOneData(long id,People people){
        ContentValues updateValues=new ContentValues();
        updateValues.put(KEY_NAME,people.Name);
        updateValues.put(KEY_AGE,people.Age);
        updateValues.put(KEY_DEP,people.Department);

        return db.update(DB_TABLE,updateValues,KEY_ID+"="+id,null);
    }

    private People[] ConvertToPeople(Cursor cursor){
        int resultCounts=cursor.getCount();
        if(resultCounts==0||!cursor.moveToFirst()){
            return null;
        }
        People[] peoples=new People[resultCounts];
        for(int i=0;i<resultCounts;i++){
            peoples[i]=new People();
            peoples[i].ID=cursor.getInt(0);
            peoples[i].Name=cursor.getString(cursor.getColumnIndex(KEY_NAME));
            peoples[i].Age=cursor.getInt(cursor.getColumnIndex(KEY_AGE));
            peoples[i].Department=cursor.getString(cursor.getColumnIndex(KEY_DEP));
            cursor.moveToNext();
        }
        return peoples;
    }
*/
    private static class DBOpenHelper extends SQLiteOpenHelper{
        public DBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        String DB_CREATE= "CREATE TABLE "+DB_TABLE;//+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_NAME+" txet not null, "+KEY_AGE+" integer, "+KEY_DEP+" text);";
        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
            _db.execSQL(DB_CREATE);
            onCreate(_db);
        }

    }
    public DBAdapter(Context _context){
        context=_context;
    }

    public void open()throws SQLiteException{
        dbOpenHelper=new DBOpenHelper(context,DB_NAME,null,DB_VERSION);
        try{
            db=dbOpenHelper.getWritableDatabase();
        }catch(SQLiteException ex){
            db=dbOpenHelper.getWritableDatabase();
        }
    }
    public void close(){
        if(db!=null){
            db.close();
            db=null;
        }
    }
}

