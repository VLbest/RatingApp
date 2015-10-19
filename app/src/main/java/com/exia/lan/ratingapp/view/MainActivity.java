package com.exia.lan.ratingapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.exia.lan.ratingapp.R;
import com.exia.lan.ratingapp.model.MainModel;
import com.exia.lan.ratingapp.utils.DB;
import com.exia.lan.ratingapp.utils.DBHandler;

import java.util.Observable;

public class MainActivity extends AbstractActivity {

    private MainModel Model;
    private Button start_button;
    private Button admin_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        this.initComponents();
    }

    private void initComponents(){
        DB.setDBHandler(new DBHandler(this, null, null, 1));

        this.Model = new MainModel();
        this.Model.addObserver(this);

        this.start_button = (Button) findViewById(R.id.start_button);
        this.admin_button = (Button)findViewById(R.id.admin_btn);

        this.start_button.setOnClickListener(this);
        this.admin_button.setOnClickListener(this);
    }

    @Override
    public void update(Observable observable, Object data) {
        this.start_button.setText(this.Model.getBtText());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_button:
                this.launchQuestions();
                break;
            case R.id.admin_btn:
                this.launchAdminPanel();
                break;
            default:
                break;
        }
    }

    private void launchQuestions(){
        Intent i = new Intent(MainActivity.this, QuestionsActivity.class);
        startActivity(i);
        //finish();
    }

    private void launchAdminPanel(){
        Intent i = new Intent(MainActivity.this, AdminActivity.class);
        startActivity(i);
        //finish();
    }

}
