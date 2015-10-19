package com.exia.lan.ratingapp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.exia.lan.ratingapp.R;
import com.exia.lan.ratingapp.controller.Question;
import com.exia.lan.ratingapp.utils.DB;
import com.exia.lan.ratingapp.utils.DBHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class AdminActivity extends AbstractActivity {


    private TextView questInput;
    private ImageButton addButton;
    private ImageButton editButton;
    private ImageButton deleteButton;
    private ListView questList;
    private Question selectedQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);

        this.initComponents();

        this.printData();
    }

    private void initComponents() {
        this.questInput = (TextView) findViewById(R.id.editText);
        this.addButton = (ImageButton) findViewById(R.id.add_btn);
        this.editButton = (ImageButton) findViewById(R.id.edit_btn);
        this.deleteButton = (ImageButton) findViewById(R.id.delete_btn);
        this.questList = (ListView) findViewById(R.id.quest_list);

        this.addButton.setOnClickListener(this);
        this.editButton.setOnClickListener(this);
        this.deleteButton.setOnClickListener(this);
        this.questList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {
                selectedQuestion = DB.getDBHandler().getQuestionByText(String.valueOf(parent.getItemAtPosition(pos)));
                questInput.setText(selectedQuestion.getQuestion());
                return true;
            }
        });
    }

    @Override
    public void update(Observable observable, Object data) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_btn:
                this.addQuestion();
                break;
            case R.id.edit_btn:
                updateQuestion();
                break;
            case R.id.delete_btn:
                deleteQuestion();
                break;
            default:
                break;
        }
        this.reseteAll();
    }

    private void addQuestion(){
        // TODO wtf where the mvc arch gone? i defenetely need to rewrite this.
        Question question =new Question();
        question.setQuestion(this.questInput.getText().toString());
        DB.getDBHandler().addQuestion(question);
        this.printData();
    }

    private void updateQuestion(){
        Question newQuest = new Question();
        newQuest.setQuestion(this.questInput.getText().toString());
        DB.getDBHandler().updateQuestion(selectedQuestion, newQuest);
        this.printData();
    }

    private void deleteQuestion(){
        //TODO i think i already told you about mvc...
        DB.getDBHandler().deleteQuestion(this.questInput.getText().toString());
        this.printData();
    }

    public void printData(){
        List<Question> questions = DB.getDBHandler().getQuestionsData();

        // TODO Not good, not good
        ArrayList q_texts = new ArrayList();
        for (Question q:questions ) {
            q_texts.add(q.getQuestion());
        }

        ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, q_texts);
        questList.setAdapter(listAdapter);
    }

    public void reseteAll(){
        this.selectedQuestion = null;
        this.questInput.setText("");
    }
}
