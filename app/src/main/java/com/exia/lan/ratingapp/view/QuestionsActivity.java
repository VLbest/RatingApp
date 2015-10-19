package com.exia.lan.ratingapp.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.exia.lan.ratingapp.R;

import java.util.Observable;

public class QuestionsActivity extends AbstractActivity {

    private  CollectionPageAdaper pageAdapter;
    private ViewPager viewPager;

    private ImageButton veryHappyBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        this.initComponents();
    }

    private void initComponents(){

        this.pageAdapter = new CollectionPageAdaper(getSupportFragmentManager(), getApplicationContext());
        this.viewPager = (ViewPager)findViewById(R.id.pager);
        this.viewPager.setAdapter(this.pageAdapter);

    }

    @Override
    public void update(Observable observable, Object data) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_btn:
                // WTF i supposed to do?
                break;
            case R.id.right_btn:

                break;
            default:
                break;
        }
    }
}
