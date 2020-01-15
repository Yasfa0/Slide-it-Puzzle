package com.example.pkk_sip;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "DB_SIP";

    private static final String TABLE_NAME = "table_score";
    private static final String SCORE_ID = "score_id";
    private static final String SCORE_USER = "score_user";
    private static final String SCORE_VALUE = "score_value";
    private static final String SCORE_TIME = "score_time";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createScoreTable = "Create Table " + TABLE_NAME + "( " + SCORE_ID + " INTEGER PRIMARY KEY, " + SCORE_USER + " TEXT," + SCORE_VALUE + " INTEGER," + SCORE_TIME + " INTEGER)";
        db.execSQL(createScoreTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = ("drop table if exists " + TABLE_NAME);
        db.execSQL(sql);
        onCreate(db);
    }

    // Select Data
    public List<Score> selectUserData() {
        ArrayList<Score> scoreList = new ArrayList<Score>();

        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {SCORE_ID, SCORE_USER, SCORE_VALUE, SCORE_TIME};
        Cursor c = db.query(TABLE_NAME, columns, null, null, null, null, null);

        while (c.moveToNext()) {
            int id = c.getInt(0);
            String user = c.getString(1);
            int value = c.getInt(2);
            int time = c.getInt(3);

            Score score = new Score();
            score.setId_score(id);
            score.setUser(user);
            score.setValue(value);
            score.setTime(time);
            scoreList.add(score);
        }
        return scoreList;
    }

    // Delete Data
    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String WhereClause = SCORE_ID + "='" + id + "'";
        db.delete(TABLE_NAME, WhereClause, null);
    }

    //Insert Data
    public void insert(Score score) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues konten = new ContentValues();

        konten.put(SCORE_ID, score.getId_score());
        konten.put(SCORE_USER, score.getUser());
        konten.put(SCORE_VALUE, score.getValue());
        konten.put(SCORE_TIME, score.getTime());

        db.insert(TABLE_NAME, null, konten);
    }

    //Update Data
    public void update(Score score) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues konten = new ContentValues();

        konten.put(SCORE_ID, score.getId_score());
        konten.put(SCORE_USER, score.getUser());
        konten.put(SCORE_VALUE, score.getValue());
        konten.put(SCORE_TIME, score.getTime());

        String indikator = SCORE_ID + " = '" + score.getId_score() + "'";

        db.update(TABLE_NAME, konten, indikator, null);
    }

}
