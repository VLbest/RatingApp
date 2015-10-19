package com.exia.lan.ratingapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exia.lan.ratingapp.R;
import com.exia.lan.ratingapp.controller.EndQuestion;

public class EndQuestionFragment extends Fragment implements View.OnClickListener{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.end_question_fragment, container, false);

        TextView quest = (TextView)view.findViewById(R.id.quest_text);
        quest.setText("Terminer");

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.end_btn:
                int i =5;
                break;
            default:
                break;
        }
    }
}
