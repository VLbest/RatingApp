package com.exia.lan.ratingapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.print.pdf.PrintedPdfDocument;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.exia.lan.ratingapp.R;
import com.exia.lan.ratingapp.utils.DB;
import com.exia.lan.ratingapp.utils.I_PDFGenerator;
import com.exia.lan.ratingapp.utils.PDFGenerator;
import com.exia.lan.ratingapp.view.QuestionsParties.interfaces.I_QuestionStatist;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class StatActivity extends Activity {

    @InjectView(R.id.print_btn)
    Button printBtn;
    @InjectView(R.id.stat_question_coll)
    TextView statQuestionColl;
    @InjectView(R.id.stat_a_rate)
    TextView statARate;
    @InjectView(R.id.stat_b_rate)
    TextView statBRate;
    @InjectView(R.id.stat_c_rate)
    TextView statCRate;
    @InjectView(R.id.stat_d_rate)
    TextView statDRate;
    @InjectView(R.id.stat_top_row)
    TableRow statTopRow;
    @InjectView(R.id.stat_table)
    TableLayout statTable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);
        ButterKnife.inject(this);

        this.printData();
    }

    private void printData() {
        List<I_QuestionStatist> questionList = DB.getDBHandler().getQuestionsStats();

        for (I_QuestionStatist question : questionList) {
            TableRow newRow = new TableRow(getApplicationContext());
            newRow.setLayoutParams(statTopRow.getLayoutParams());

            TextView quest = new TextView(getApplicationContext());
            TextView a = new TextView(getApplicationContext());
            TextView b = new TextView(getApplicationContext());
            TextView c = new TextView(getApplicationContext());
            TextView d = new TextView(getApplicationContext());

            quest.setLayoutParams(statQuestionColl.getLayoutParams());
            a.setLayoutParams(statARate.getLayoutParams());
            b.setLayoutParams(statBRate.getLayoutParams());
            c.setLayoutParams(statCRate.getLayoutParams());
            d.setLayoutParams(statDRate.getLayoutParams());

            quest.setText(question.getText());
            a.setText(String.valueOf(question.getA_count()));
            b.setText(String.valueOf(question.getB_count()));
            c.setText(String.valueOf(question.getC_count()));
            d.setText(String.valueOf(question.getD_count()));

            newRow.addView(quest);
            newRow.addView(a);
            newRow.addView(b);
            newRow.addView(c);
            newRow.addView(d);

            statTable.addView(newRow);
        }

    }

    @OnClick(R.id.print_btn)
    public void generatePDF(){
        I_PDFGenerator pdfGenerator = new PDFGenerator();

    }
}
