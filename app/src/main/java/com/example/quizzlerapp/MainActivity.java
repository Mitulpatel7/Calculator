package com.example.quizzlerapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    Button truebtn , flsbtn;
    TextView questiontextview , scoretextview;
    ProgressBar progressBar;
    int score;
    int index;
    int questions;


    private TrueFalse[] questionbank = new TrueFalse[]
            {
                    new TrueFalse(R.string.question_1 , true),
                    new TrueFalse(R.string.question_2 , true),
                    new TrueFalse(R.string.question_3 , true),
                    new TrueFalse(R.string.question_4 , true),
                    new TrueFalse(R.string.question_5 , true),
                    new TrueFalse(R.string.question_6 , false),
                    new TrueFalse(R.string.question_7 , true),
                    new TrueFalse(R.string.question_8 , false),
                    new TrueFalse(R.string.question_9 , true),
                    new TrueFalse(R.string.question_10 , true),
                    new TrueFalse(R.string.question_11 , false),
                    new TrueFalse(R.string.question_12 , false),
                    new TrueFalse(R.string.question_13 , true)

            };
    final int PROGRESS_BAR_INCREMENT =(int) Math.ceil(100.0 / questionbank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        truebtn = findViewById(R.id.true_btn);
        flsbtn = findViewById(R.id.false_btn);
        questiontextview = findViewById(R.id.question_text_view);
        scoretextview = findViewById(R.id.intial_score);
        progressBar = findViewById(R.id.progressbar);
        questions = questionbank[index].getQuestionID();
        questiontextview.setText(questions);


        truebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkanswer(true);
                updatequestion();
            }
        });


        flsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkanswer(false);
                updatequestion();
            }
        });


    }

    private void updatequestion()
    {
        index = (index+1) % questionbank.length;

        if(index == 0 )
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game over");
            alert.setCancelable(false);
            alert.setMessage("You Scored "+ score + " points!");
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }

        questions = questionbank[index].getQuestionID();
        questiontextview.setText(questions);
        progressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        scoretextview.setText("Score "+ score + "/" + questionbank.length);
    }

    private void checkanswer(boolean userselection)
    {
        boolean correctanswer = questionbank[index].isAnswer();
        if(userselection == correctanswer)
        {
            Toast.makeText(this, "You got it", Toast.LENGTH_SHORT).show();
            score = score + 1;
        }
        else
        {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
        }
    }
}
