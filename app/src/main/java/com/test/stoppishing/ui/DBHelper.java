package com.test.stoppishing.ui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MobileNumber.db";

    public DBHelper(Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FeedEntry.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(FeedEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "phoneBook"; //테이블 명
        public static final String COLUMN_NAME_NAME = "name"; //컬럼 명
        public static final String COLUMN_NAME_NUMBER = "number"; //컬럼 명

        //테이블 생성 쿼리
        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                        FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        FeedEntry.COLUMN_NAME_NAME + " TEXT," +
                        FeedEntry.COLUMN_NAME_NUMBER + " TEXT)";


        //DROP TABLE 테이블을 삭제 쿼리
        //IF EXISTS 절을 사용하면 삭제하려는 데이터베이스나 테이블이 존재하지 않아서 발생하는 에러를 미리 방지.
        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
    }
}
