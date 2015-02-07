package com.danieldyba.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends ActionBarActivity {

    private boolean mAnswerIsTrue;
    private boolean mIsAnswerShown;
    private int mQuestionIndex;
    private TextView mAnswerTextView;
    private Button mShowAnswer;

    public static final String EXTRA_ANSWER_IS_TRUE = "com.danieldyba.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.danieldyba.geoquiz.answer_shown";
    public static final String EXTRA_QUESTION_INDEX = "com.danieldyba.geoquiz.question_index";

    private static final String KEY_CHEATER = "cheater";
    private static final String KEY_ANSWER_IS_TRUE = "answer_is_true";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        mQuestionIndex = getIntent().getIntExtra(EXTRA_QUESTION_INDEX, -1);
        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);

        if (savedInstanceState != null) {
            mIsAnswerShown = savedInstanceState.getBoolean(KEY_CHEATER, false);
            mAnswerIsTrue = savedInstanceState.getBoolean(KEY_ANSWER_IS_TRUE, mAnswerIsTrue);
            setAnswerShownResult(mIsAnswerShown);
        }

        if (mIsAnswerShown) {
            setAnswerText(mAnswerIsTrue);
        }

        mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnswerText(mAnswerIsTrue);
                mIsAnswerShown = true;
                setAnswerShownResult(mIsAnswerShown);
            }
        });
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        data.putExtra(EXTRA_QUESTION_INDEX, mQuestionIndex);
        setResult(RESULT_OK, data);
    }

    private void setAnswerText(boolean answerIsTrue) {
        if (answerIsTrue) {
            mAnswerTextView.setText(R.string.true_button);
        } else {
            mAnswerTextView.setText(R.string.false_button);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cheat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_CHEATER, mIsAnswerShown);
        savedInstanceState.putBoolean(KEY_ANSWER_IS_TRUE, mAnswerIsTrue);
    }
}
