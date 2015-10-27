package com.exia.lan.ratingapp.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.exia.lan.ratingapp.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ProgressIndicator implements I_ProsgressIndicator {

    private ProgressBar progressBar;
    private TextView textProgress;
    private String textPosition;

    public ProgressIndicator(ProgressBar progressBar, TextView textView){
        this.progressBar = progressBar;
        this.textProgress = textView;
    }

    public void onProgresssUpdated(int i){
        progressBar.setProgress(i);
        textProgress.setText(String.format(textPosition, progressBar.getProgress()));
    }

    public void setMaxProgress(int maxProgress){
        this.progressBar.setMax(maxProgress - 1);
        this.textPosition = "%s/"+ this.progressBar.getMax();
    }
}
