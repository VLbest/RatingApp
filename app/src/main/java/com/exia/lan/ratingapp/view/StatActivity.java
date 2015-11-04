package com.exia.lan.ratingapp.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.exia.lan.ratingapp.R;
import com.exia.lan.ratingapp.utils.DB;
import com.exia.lan.ratingapp.utils.I_PDFGenerator;
import com.exia.lan.ratingapp.utils.I_StatActivity;
import com.exia.lan.ratingapp.utils.PDFGenerator;
import com.exia.lan.ratingapp.view.QuestionsParties.interfaces.I_QuestionStatist;
import com.exia.lan.ratingapp.view.QuestionsParties.interfaces.I_QuestionsDBHandler;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class StatActivity extends Activity implements I_StatActivity {

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
    @InjectView(R.id.purge_db_btn)
    Button purgeDbBtn;
    @InjectView(R.id.total_stat)
    TextView totalStat;
    @InjectView(R.id.total_a_stat)
    TextView totalAStat;
    @InjectView(R.id.total_b_stat)
    TextView totalBStat;
    @InjectView(R.id.total_c_stat)
    TextView totalCStat;
    @InjectView(R.id.total_d_stat)
    TextView totalDStat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);
        ButterKnife.inject(this);

        this.printData();
    }

    private void printData() {
        List<I_QuestionStatist> questionList = DB.getDBHandler().getQuestionsStats();

        this.printTotalStats(questionList);

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

    private void printTotalStats(List<I_QuestionStatist> questionList) {
        int totalStat = 0;
        int totalAStat = 0;
        int totalBStat = 0;
        int totalCStat = 0;
        int totalDStat = 0;

        for (I_QuestionStatist q: questionList) {
            totalStat += (q.getA_count() + q.getB_count() + q.getC_count() + q.getD_count());
            totalAStat += q.getA_count();
            totalBStat += q.getB_count();
            totalCStat += q.getC_count();
            totalDStat += q.getD_count();
        }

        this.totalStat.setText(String.format(this.totalStat.getText().toString(), String.valueOf(totalStat)));

        this.totalAStat.setText(String.format(this.totalAStat.getText().toString(), String.valueOf(totalAStat), getPercent(totalAStat,totalStat)));
        this.totalBStat.setText(String.format(this.totalBStat.getText().toString(), String.valueOf(totalBStat), getPercent(totalBStat,totalStat)));
        this.totalCStat.setText(String.format(this.totalCStat.getText().toString(), String.valueOf(totalCStat), getPercent(totalCStat,totalStat)));
        this.totalDStat.setText(String.format(this.totalDStat.getText().toString(), String.valueOf(totalDStat), getPercent(totalDStat, totalStat)));

    }

    private String getPercent(int nb, int total){
        float pr = ((float) nb * 100) / (float) total;
        return String.valueOf(Math.round(pr));
    }

    @OnClick(R.id.print_btn)
    public void generatePDF() {
        Toast.makeText(getApplicationContext(), "Enregistrement est en cours", Toast.LENGTH_SHORT).show();
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        RelativeLayout root = (RelativeLayout) inflater.inflate(R.layout.stats, null);
        root.setDrawingCacheEnabled(true);
        View myView = this.getWindow().findViewById(R.id.stat_full_layout);
        Bitmap screen = getBitmapFromView(myView);

        String FILE = "mnt/sdcard/invoice.pdf"; // add permission in your manifest...

        try {
            Document document = new Document();
            document.setPageSize(new Rectangle(myView.getMeasuredWidth(),myView.getMeasuredHeight()));
            document.setMargins(0,0,0,0);
            document.setMarginMirroring(true);
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            screen.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            addImage(document, byteArray);
            document.close();
            Toast.makeText(getApplicationContext(), "Enregistrement terminé", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Échec d'enregistrement", Toast.LENGTH_LONG).show();
        }
    }

    private static void addImage(Document document,byte[] byteArray) {
        Image image = null;
        try {
            image = Image.getInstance(byteArray);
        } catch (BadElementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // image.scaleAbsolute(150f, 150f);
        try {
            document.add(image);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getMeasuredWidth(),view.getMeasuredHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }

    @OnClick(R.id.purge_db_btn)
    public void Click_purge_db_btn() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        I_QuestionsDBHandler DataBase = DB.getDBHandler();
                        DataBase.purgeStats();
                        finish();
                        startActivity(getIntent());
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Effacer les statistiques ?").setPositiveButton("Oui", dialogClickListener)
                .setNegativeButton("Non", dialogClickListener).show();
    }

}
