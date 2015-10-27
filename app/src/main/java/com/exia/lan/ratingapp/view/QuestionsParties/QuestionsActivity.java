package com.exia.lan.ratingapp.view.QuestionsParties;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.exia.lan.ratingapp.R;
import com.exia.lan.ratingapp.utils.I_ProsgressIndicator;
import com.exia.lan.ratingapp.utils.ProgressIndicator;
import com.exia.lan.ratingapp.view.ThanksActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class QuestionsActivity extends AppCompatActivity {


    @InjectView(R.id.reset_btn)
    ImageButton resetBtn;
    @InjectView(R.id.left_btn)
    Button leftBtn;
    @InjectView(R.id.left_frame)
    FrameLayout leftFrame;
    @InjectView(R.id.right_btn)
    Button rightBtn;
    @InjectView(R.id.right_frame)
    FrameLayout rightFrame;
    @InjectView(R.id.pager_title_strip)
    PagerTitleStrip pagerTitleStrip;
    @InjectView(R.id.quest_progressBar)
    ProgressBar questProgressBar;
    @InjectView(R.id.question_indicator)
    TextView questionIndicator;

    private QuestionsSession pageAdapter;
    private ViewPager viewPager;
    private I_ProsgressIndicator progressIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);
        ButterKnife.inject(this);

        this.initComponents();
    }

    private void initComponents() {

        this.pageAdapter = new QuestionsSession(getSupportFragmentManager(), getApplicationContext());
        this.pageAdapter.setMyActivity(this);
        this.viewPager = (ViewPager) findViewById(R.id.pager);
        this.viewPager.setAdapter(this.pageAdapter);
        this.progressIndicator = new ProgressIndicator(questProgressBar, questionIndicator);
        this.progressIndicator.setMaxProgress(this.pageAdapter.getCount());
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                progressIndicator.onProgresssUpdated(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



    @OnClick(R.id.left_frame)
    public void Click_left_frame() {
        this.viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
    }

    @OnClick(R.id.left_btn)
    public void Click_left_btn() {
        this.Click_left_frame();
    }

    @OnClick(R.id.right_frame)
    public void Click_right_frame() {
        this.viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
    }

    @OnClick(R.id.right_btn)
    public void Click_right_btn() {
        this.Click_right_frame();
    }

    @OnClick(R.id.reset_btn)
    public void Click_reset_btn() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Intent i = new Intent(QuestionsActivity.this, ThanksActivity.class);
                        startActivity(i);
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Arreter ?").setPositiveButton("Oui", dialogClickListener)
                .setNegativeButton("Non", dialogClickListener).show();
    }

    public void endQuestionning() {
        Intent i = new Intent(QuestionsActivity.this, ThanksActivity.class);
        startActivity(i);
        finish();
    }

}
