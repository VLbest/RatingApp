package com.exia.lan.ratingapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.exia.lan.ratingapp.R;
import com.exia.lan.ratingapp.view.QuestionsParties.Question;
import com.exia.lan.ratingapp.utils.DB;
import com.exia.lan.ratingapp.view.QuestionsParties.interfaces.I_NormalQuestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AdminActivity extends AbstractActivity {

    @InjectView(R.id.add_btn)
    ImageButton addBtn;
    @InjectView(R.id.edit_btn)
    ImageButton editBtn;
    @InjectView(R.id.delete_btn)
    ImageButton deleteBtn;
    @InjectView(R.id.quest_list)
    ListView questList;
    @InjectView(R.id.editText)
    EditText editText;
    @InjectView(R.id.show_stat_btn)
    ImageButton showStatBtn;

    I_NormalQuestion selectedQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);
        ButterKnife.inject(this);

        this.initComponents();

        this.printData();
    }

    private void initComponents() {

        this.addBtn.setOnClickListener(this);
        this.editBtn.setOnClickListener(this);
        this.deleteBtn.setOnClickListener(this);
        this.showStatBtn.setOnClickListener(this);
        this.questList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {
                selectedQuestion = DB.getDBHandler().getQuestionByText(String.valueOf(parent.getItemAtPosition(pos)));
                editText.setText(selectedQuestion.getText());
                return true;
            }
        });
    }

    @Override
    public void update(Observable observable, Object data) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_btn:
                this.addQuestion();
                break;
            case R.id.edit_btn:
                updateQuestion();
                break;
            case R.id.delete_btn:
                deleteQuestion();
                break;
            case R.id.show_stat_btn:
                this.showStats();
                break;
            default:
                break;
        }
        this.reseteAll();
    }

    private void addQuestion() {
        I_NormalQuestion question = new Question();
        question.setText(this.editText.getText().toString());
        DB.getDBHandler().insertQuestion(question);
        this.printData();
    }

    private void updateQuestion() {
        DB.getDBHandler().updateQuestionTextByText(selectedQuestion.getText(), this.editText.getText().toString());
        this.printData();
    }

    private void deleteQuestion() {
        DB.getDBHandler().deleteQuestionByText(this.editText.getText().toString());
        this.printData();
    }

    public void printData() {
        List<I_NormalQuestion> questions = DB.getDBHandler().getQuestions();

        // TODO Not good, not good
        ArrayList q_texts = new ArrayList();
        for (I_NormalQuestion q : questions) {
            q_texts.add(q.getText());
        }

        ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, q_texts);
        questList.setAdapter(listAdapter);
    }

    private void showStats() {
        Intent i = new Intent(AdminActivity.this, StatActivity.class);
        startActivity(i);
        //finish();
    }

    public void reseteAll() {
        this.selectedQuestion = null;
        this.editText.setText("");
    }
}
