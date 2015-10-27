package com.exia.lan.ratingapp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.exia.lan.ratingapp.view.QuestionsParties.Question;

import java.util.ArrayList;
import java.util.List;

public class DBHandler /*extends SQLiteOpenHelper*/{
/*
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "rating_app.db";

    public  static  final String TABLE_QUESTIONS = "questions";

    public  static  final String COLUMN_ID = "_id";
    public  static  final String COLUMN_QUESTION = "question";
    public  static  final String COLUMN_RATE_A = "rating_A";
    public  static  final String COLUMN_RATE_B = "rating_B";
    public  static  final String COLUMN_RATE_C = "rating_C";
    public  static  final String COLUMN_RATE_D = "rating_D";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Ajust data types
        String query = "CREATE TABLE " + TABLE_QUESTIONS + " ( " +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_QUESTION + " VARCHAR(250) NOT NULL UNIQUE , " +
                COLUMN_RATE_A + " INT, " +
                COLUMN_RATE_B + " INT, " +
                COLUMN_RATE_C + " INT, " +
                COLUMN_RATE_D + " INT " +
                " ); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_QUESTIONS );
        onCreate(db);
    }

    public void addQuestion(Question quest){
        ContentValues values = new ContentValues();

       // values.put(COLUMN_ID, "NULL");
        values.put(COLUMN_QUESTION, quest.getQuestion());
        values.put(COLUMN_RATE_A, quest.getRating_A());
        values.put(COLUMN_RATE_B, quest.getRating_B());
        values.put(COLUMN_RATE_C, quest.getRating_C());
        values.put(COLUMN_RATE_D, quest.getRating_D());

        SQLiteDatabase db = getWritableDatabase();
        long i = db.insertOrThrow(TABLE_QUESTIONS, null, values);
        db.close();
    }

    public void deleteQuestion(Question quest){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_QUESTIONS + " WHERE " +
                COLUMN_QUESTION + " ='" + quest.getQuestion().replace("'", "''") + "';");
        db.close();
    }

    public void deleteQuestion(String questText){
        Question q = new Question();
        q.setQuestion(questText);
        this.deleteQuestion(q);
    }

    public void updateQuestion(Question oldQuest, Question newQuest){
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_QUESTIONS + " SET " +
                COLUMN_QUESTION + " = '" + newQuest.getQuestion().replace("'", "''") +
                "', " + COLUMN_RATE_A + " = " + newQuest.getRating_A() +
                ", " + COLUMN_RATE_B + " = " + newQuest.getRating_B() +
                ", " + COLUMN_RATE_C + " = " + newQuest.getRating_C() +
                ", " + COLUMN_RATE_D + " = " + newQuest.getRating_D() +
                " WHERE " + COLUMN_QUESTION + " ='" + oldQuest.getQuestion().replace("'", "''") + "';";
        db.execSQL(query);
        db.close();
    }

    public void updateQuestion(Question quest){
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_QUESTIONS + " SET " +
                COLUMN_RATE_A + " = " + quest.getRating_A() +
                ", " + COLUMN_RATE_B + " = " + quest.getRating_B() +
                ", " + COLUMN_RATE_C + " = " + quest.getRating_C() +
                ", " + COLUMN_RATE_D + " = " + quest.getRating_D() +
                " WHERE " + COLUMN_QUESTION + " ='" + quest.getQuestion().replace("'", "''") + "';";
        db.execSQL(query);
        db.close();
    }

    public Question getQuestionByText(String quest){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_QUESTIONS + " WHERE " +
                COLUMN_QUESTION + " ='" + quest.replace("'", "''") + "';";
        Cursor c = db.rawQuery(query, null);
        Question q = new Question();
        if (c.moveToFirst()){
                q.set_id(c.getInt(c.getColumnIndex("_id")));
                q.setQuestion(c.getString(c.getColumnIndex("question")));
                q.setRating_A(c.getInt(c.getColumnIndex("rating_A")));
                q.setRating_B(c.getInt(c.getColumnIndex("rating_B")));
                q.setRating_C(c.getInt(c.getColumnIndex("rating_C")));
                q.setRating_D(c.getInt(c.getColumnIndex("rating_D")));
        }
        return q;
    }

    public List<Question> getQuestionsData(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_QUESTIONS + " WHERE 1 ";
        Cursor c = db.rawQuery(query, null);

        // TODO it's pretty scary thing, do not keep it this way
        List<Question> qs = new ArrayList<>();


        if (c.moveToFirst()){
            do{
                Question q = new Question();
                q.set_id(c.getInt(c.getColumnIndex("_id")));
                q.setQuestion(c.getString(c.getColumnIndex("question")));
                q.setRating_A(c.getInt(c.getColumnIndex("rating_A")));
                q.setRating_B(c.getInt(c.getColumnIndex("rating_B")));
                q.setRating_C(c.getInt(c.getColumnIndex("rating_C")));
                q.setRating_D(c.getInt(c.getColumnIndex("rating_D")));
                qs.add(q);
            }while(c.moveToNext());
        }
        c.close();
        return qs;
    }
*/
}
