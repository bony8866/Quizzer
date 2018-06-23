package com.techogamer.www.quizzer;

import android.content.DialogInterface;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mtrue_Button;
    Button mfalse_Button;
    TextView mquest_tv;
    int mindex;
    int mscore;
    TextView mscoreview;
    ProgressBar mProgressBar;

    TrueFalse[] tf = new TrueFalse[]{
            new TrueFalse(R.string.question_1,true),
            new TrueFalse(R.string.question_2,true),
            new TrueFalse(R.string.question_3,true),
            new TrueFalse(R.string.question_4,true),
            new TrueFalse(R.string.question_5,true),
            new TrueFalse(R.string.question_6,false),
            new TrueFalse(R.string.question_7,true),
            new TrueFalse(R.string.question_8,false),
            new TrueFalse(R.string.question_9,true),
            new TrueFalse(R.string.question_10,true),
            new TrueFalse(R.string.question_11,false),
            new TrueFalse(R.string.question_12,false),
            new TrueFalse(R.string.question_13,true),
    };

    int PROGRESS_BAR = (int) Math.ceil(100/tf.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null)
        {
            mscore = savedInstanceState.getInt("scoreid");
            mindex = savedInstanceState.getInt("questionno");
        }
        else {
            mscore = 0;
            mindex = 0;
        }

        mtrue_Button = findViewById(R.id.true_button);
        mfalse_Button = findViewById(R.id.false_button);

        mquest_tv = findViewById(R.id.question_text_view);
        mquest_tv.setText(tf[mindex].getQuestion_id());

        mscoreview = findViewById(R.id.score);
        mscoreview.setText("Score" + mscore + "/" +tf.length);

        mProgressBar = findViewById(R.id.progress_bar);

        mtrue_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkans(true);
                updatequestion();
            }
        });

        mfalse_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkans(false);
                updatequestion();
            }
        });
    }

    private void updatequestion() {
        mindex = (mindex + 1) % tf.length;
        mProgressBar.incrementProgressBy(8);

        if (mindex == 0) {
            AlertDialog.Builder alert= new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("Your Score is: " +mscore +"/" +tf.length);
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        } else {
            mscoreview.setText("Score" + mscore + "/" +tf.length);
            mquest_tv.setText(tf[mindex].getQuestion_id());
        }
    }
    private void checkans(boolean ans)
    {
        boolean answer = tf[mindex].isAnswer();
        if(ans == answer)
        {
            mscore = (mscore + 1);
            Toast.makeText(getApplicationContext(),"You got it correct",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Its Wrong",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("scoreid",mscore);
        outState.putInt("questionno",mindex);
    }
}
