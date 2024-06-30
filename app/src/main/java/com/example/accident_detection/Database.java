package com.example.accident_detection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory,int version){
        super(context, name, factory, version);
    }
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int i,int j){

    }
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String str1 = "create table users(username text,password text,phone text,bg text,gardian text,gardian_phone text)";
        sqLiteDatabase.execSQL(str1);
    }

    public void register(String name,String pass,String phone,String blood,String gname,String gphone){
        ContentValues cv = new ContentValues();
        cv.put("username",name);
        cv.put("password",pass);
        cv.put("phone",phone);
        cv.put("bg",blood);
        cv.put("gardian",gname);
        cv.put("gardian_phone",gphone);
        SQLiteDatabase sb = getWritableDatabase();
        sb.insert("users",null,cv);
        sb.close();
    }
    public boolean login(String name,String pass){
        String str[]=new String[2];
        str[0]= name;
        str[1]= pass;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("Select * from users where username=? and password =?",str);
        if(c.moveToFirst()){
            return true;
        }
        return false;
    }
    public String[] get_user_info(String name){
        SQLiteDatabase db= getReadableDatabase();
        String userinfo[]=new String[4];
        Cursor c = db.rawQuery("SELECT * FROM users where username=?",new String[]{name});
        if(c.moveToFirst()){
            userinfo[0]=c.getString(0);
            userinfo[1]=c.getString(2);
            userinfo[2]=c.getString(3);
            userinfo[3]=c.getString(5);

        }
        else{
            userinfo = new String[]{"","","",""};
        }
        c.close();
        db.close();
        return userinfo;
    }

}