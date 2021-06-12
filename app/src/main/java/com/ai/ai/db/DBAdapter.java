package com.ai.ai.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


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

    /**
     * 插入函数接口
     */
    public long insert(clesson lesson){
        ContentValues newValues=new ContentValues();

        newValues.put(KEY_NAME,lesson.getCname());
        newValues.put(KEY_LOC,lesson.getLocat());
        newValues.put(KEY_TN,lesson.getTname());
        newValues.put(KEY_ID,lesson.getCno());
        newValues.put(KEY_TD,lesson.getTimedata());

        return db.insert(DB_TABLE,null,newValues);//insert(DB_TABLE,null,newValues);
    }

    /**
     * 各类函数封装
     * @return
     */
    public long deleteAllData(){
        return db.delete(DB_TABLE,null,null);
    }
    public long deleteOneData(long id){
        return db.delete(DB_TABLE,KEY_ID+"=",null);
    }

    /**
     * 查询数据
     * @return
     */
    public clesson[] queryAllData(){
        Cursor results=db.query(DB_TABLE,new String[]{KEY_ID,KEY_NAME,KEY_LOC,KEY_TD,KEY_TN},null,null,null,null,null);
        return ConvertToclesson(results);
    }
    public clesson[] queryOneDate(long id){
        Cursor results=db.query(DB_TABLE,new String[]{KEY_ID,KEY_NAME,KEY_LOC,KEY_TD,KEY_TN},KEY_ID+"="+id,null,null,null,null);
        return ConvertToclesson(results);
    }

    /**
     * 更新数据
     */
    public long updateOneData(long id,clesson lesson){
        ContentValues updateValues=new ContentValues();
        updateValues.put(KEY_NAME,lesson.getCname());
        updateValues.put(KEY_LOC,lesson.getLocat());
        updateValues.put(KEY_TD,lesson.getTimedata());
        updateValues.put(KEY_TN,lesson.getTname());

        return db.update(DB_TABLE,updateValues,KEY_ID+"="+id,null);
    }

    /**
     *
     */
    private clesson[] ConvertToclesson(Cursor cursor){
        int resultCounts=cursor.getCount();
        if(resultCounts==0||!cursor.moveToFirst()){
            return null;
        }
        clesson[] lessons=new clesson[resultCounts];
        for(int i=0;i<resultCounts;i++){
            lessons[i]=new clesson();
            lessons[i].setCno(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            lessons[i].setCname(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            lessons[i].setLocat(cursor.getString(cursor.getColumnIndex(KEY_LOC)));
            lessons[i].setTname(cursor.getString(cursor.getColumnIndex(KEY_TN)));
            lessons[i].setTimedata(cursor.getInt(cursor.getColumnIndex(KEY_TD)));
            cursor.moveToNext();
        }
        return lessons;
    }

    private static class DBOpenHelper extends SQLiteOpenHelper{
        public DBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        String DB_CREATE= "CREATE TABLE "+DB_TABLE+"("+KEY_ID+" INTEGER PRIMARY KEY, "+KEY_TN+" txet not null, "+KEY_TD+" integer, "+KEY_NAME+" text, "+KEY_LOC+" text not null );";
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
