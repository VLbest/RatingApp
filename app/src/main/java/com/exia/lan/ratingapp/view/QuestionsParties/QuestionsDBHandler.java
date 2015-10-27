package com.exia.lan.ratingapp.view.QuestionsParties;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.exia.lan.ratingapp.view.QuestionsParties.interfaces.I_QuestionStatist;
import com.exia.lan.ratingapp.view.QuestionsParties.interfaces.I_QuestionsDBHandler;
import com.exia.lan.ratingapp.view.QuestionsParties.interfaces.I_NormalQuestion;

import java.util.ArrayList;
import java.util.List;

public class QuestionsDBHandler extends SQLiteOpenHelper implements I_QuestionsDBHandler {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "rating_app.db";
    private final QuestionTable TABLE = new QuestionTable();

    public QuestionsDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = " " +
                "CREATE TABLE IF NOT EXISTS `%s` (  "
                +   " `%s` INTEGER PRIMARY KEY, "
                +   " `%s` DATETIME NOT NULL, "
                +   " `%s` VARCHAR(250) NOT NULL UNIQUE, "
                +   " `%s` INT NOT NULL, "
                +   " `%s` INT NOT NULL, "
                +   " `%s` INT NOT NULL, "
                +   " `%s` INT NOT NULL); ";

        query = String.format(query, TABLE.T_NAME, TABLE.ID, TABLE.DATE, TABLE.TEXT,
                                        TABLE.A, TABLE.B, TABLE.C, TABLE.D);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS %s";

        query = String.format(query, TABLE.T_NAME);
        db.execSQL(query);
        onCreate(db);
    }

    public List<I_NormalQuestion> getQuestions(){
        SQLiteDatabase sqlDB = getWritableDatabase();

        String query = "SELECT * FROM `%s` WHERE 1";

        query = String.format(query , TABLE.T_NAME);
        Cursor c = sqlDB.rawQuery(query, null);
        return this.processData(c);
    }

    private List<I_NormalQuestion> processData(Cursor c) {
        List<I_NormalQuestion> qs = new ArrayList<>();
        if (c.moveToFirst()){
            do{
                Question q = new Question();
                q.set_id(c.getInt(c.getColumnIndex(TABLE.ID)));
                q.setText(c.getString(c.getColumnIndex(TABLE.TEXT)));
                qs.add(q);
            }while(c.moveToNext());
        }
        c.close();
        return qs;
    }

    public I_NormalQuestion getQuestionByText(String text){
        SQLiteDatabase sqlDB = getWritableDatabase();

        String query = "SELECT * FROM `%s` WHERE `%s` ='%s'";

        query = String.format(query, TABLE.T_NAME, TABLE.TEXT, escText(text));
        Cursor c = sqlDB.rawQuery(query, null);
        I_NormalQuestion question = this.processData(c).get(0);
        return question;
    }

    public void insertQuestion(I_NormalQuestion Quest){
        SQLiteDatabase sqlDB = getWritableDatabase();

        String query = " " +
                "INSERT INTO `%s`(`%s`, `%s`, `%s`, `%s`, `%s`, `%s`) "
                +   " VALUES (%s,'%s',%s,%s,%s,%s) ";

        query = String.format(query, TABLE.T_NAME, TABLE.DATE, TABLE.TEXT, TABLE.A, TABLE.B, TABLE.C, TABLE.D,
                "datetime()", Quest.getText(), 0, 0, 0, 0);
        sqlDB.execSQL(query);
        sqlDB.close();
    }

    public void updateQuestionTextByText(String newText, String oldText){
        SQLiteDatabase sqlDB = getWritableDatabase();

        String query = " " +
                "UPDATE `%s` SET "
                +   " `%s`='%s' "
                +   " WHERE `%s` = '%s' ";

        query = String.format(query, TABLE.T_NAME, TABLE.TEXT, escText(oldText), TABLE.TEXT, escText(newText));
        sqlDB.execSQL(query);
        sqlDB.close();
    }

    public void updateQuestionStatByText(String text, char rating){
        SQLiteDatabase sqlDB = getWritableDatabase();

        String preQuery = "SELECT `%s` FROM `%s` WHERE `%s` = '%s' ";

        preQuery = String.format(preQuery, Character.toLowerCase(rating), TABLE.T_NAME, TABLE.TEXT, escText(text));
        Cursor c = sqlDB.rawQuery(preQuery, null);
        int count = 0;
        if (c.moveToFirst()){
            do{
                count = c.getInt(c.getColumnIndex(String.valueOf(Character.toLowerCase(rating))));
            }while(c.moveToNext());
        }
        c.close();

        String query = " " +
                "UPDATE `%s` SET "
                +   " `%s`='%s' "
                +   " WHERE `%s` = '%s' ";

        query = String.format(query, TABLE.T_NAME, Character.toLowerCase(rating), count + 1, TABLE.TEXT, escText(text));
        sqlDB.execSQL(query);
        sqlDB.close();
    }

    public void deleteQuestionByText(String text){
        SQLiteDatabase sqlDB = getWritableDatabase();

        String query = "DELETE FROM `%s` "
                +   " WHERE `%s` = '%s' ";

        query = String.format(query, TABLE.T_NAME, TABLE.TEXT, escText(text));
        sqlDB.execSQL(query);
        sqlDB.close();
    }

    public List<I_QuestionStatist> getQuestionsStats(){
        SQLiteDatabase sqlDB = getWritableDatabase();

        String query = "SELECT * FROM `%s` WHERE 1";

        query = String.format(query , TABLE.T_NAME);
        Cursor c = sqlDB.rawQuery(query, null);
        return this.processStatData(c);

    }

    private List<I_QuestionStatist> processStatData(Cursor c) {
        List<I_QuestionStatist> qs = new ArrayList<>();
        if (c.moveToFirst()){
            do{
                I_QuestionStatist q = new QuestionStatist();
                q.set_id(c.getInt(c.getColumnIndex(TABLE.ID)));
                q.setText(c.getString(c.getColumnIndex(TABLE.TEXT)));
                q.setA_count(c.getInt(c.getColumnIndex(TABLE.A)));
                q.setB_count(c.getInt(c.getColumnIndex(TABLE.B)));
                q.setC_count(c.getInt(c.getColumnIndex(TABLE.C)));
                q.setD_count(c.getInt(c.getColumnIndex(TABLE.D)));
                qs.add(q);
            }while(c.moveToNext());
        }
        c.close();
        return qs;
    }

    private String escText(String text){
        return text.replace("'", "''");
    }



}
