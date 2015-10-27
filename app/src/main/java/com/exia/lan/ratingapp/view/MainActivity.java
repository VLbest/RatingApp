package com.exia.lan.ratingapp.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.exia.lan.ratingapp.R;
import com.exia.lan.ratingapp.utils.DB;
import com.exia.lan.ratingapp.view.QuestionsParties.QuestionsActivity;
import com.exia.lan.ratingapp.view.QuestionsParties.QuestionsDBHandler;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.start_button)
    Button startButton;
    @InjectView(R.id.admin_btn)
    ImageButton adminBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.inject(this);

        this.initComponents();
    }

    private void initComponents() {
        DB.setDBHandler(new QuestionsDBHandler(this, null, null, 1));
    }

    @OnClick(R.id.start_button)
    public void Click_start_button() {
        this.launchQuestions();
    }

    @OnClick(R.id.admin_btn)
    public void Click_admin_btn() {
        this.launchAdminPanel();
    }


    private void launchQuestions() {
        Intent i = new Intent(MainActivity.this, QuestionsActivity.class);
        startActivity(i);
        //finish();
    }

    private void launchAdminPanel() {
        askPassword();
    }

    public void askPassword() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Mot de passe"); //Message here

        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String srt = input.getEditableText().toString();
                if (srt.equals("dodo")) {
                    Intent i = new Intent(MainActivity.this, AdminActivity.class);
                    startActivity(i);
                    //finish();
                }
            }
        });
        alert.setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();

    }
}
