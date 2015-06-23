package com.dnwilson.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by dwilson on 6/22/15.
 */
public class CheatActivity extends Activity {
    private static final String TAG = "CheatActivity";
    public static final String EXTRA_ANSWER_IS_TRUE = "com.dnwilson.android.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.dnwilson.android.geoquiz.answer_shown";

    private boolean mAnswerIsTrue;
    private Button mShowAnswer;
    private TextView mAnswerTextView;
    private boolean mAnswerShown;

    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);

        // Answer will not be shown until the user
        // presses the button
        if(savedInstanceState != null){
            mAnswerIsTrue = savedInstanceState.getBoolean(EXTRA_ANSWER_IS_TRUE);
            mAnswerShown = savedInstanceState.getBoolean(EXTRA_ANSWER_SHOWN);

            if (mAnswerIsTrue){
                mAnswerTextView.setText(R.string.true_button);
            } else {
                mAnswerTextView.setText(R.string.false_button);
            }

            setAnswerShownResult(mAnswerIsTrue);
        } else {
            mAnswerShown = false;
            setAnswerShownResult(false);
        }

        mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswerIsTrue){
                    mAnswerTextView.setText(R.string.true_button);
                } else{
                    mAnswerTextView.setText(R.string.false_button);
                }
                mAnswerShown = true;
                Log.i(TAG, "onClick mAnswerIsTrue = " + Boolean.toString(mAnswerIsTrue));
                Log.i(TAG, "onClick mAnswerShown(" + Boolean.toString(mAnswerIsTrue) +
                        ") should be true");
                setAnswerShownResult(true);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState mAnswerIsTrue = " + Boolean.toString(mAnswerIsTrue));
        Log.i(TAG, "onSaveInstanceState mAnswerShown = " + Boolean.toString(mAnswerShown));
        savedInstanceState.putBoolean(EXTRA_ANSWER_SHOWN, mAnswerShown);
        savedInstanceState.putBoolean(EXTRA_ANSWER_IS_TRUE, mAnswerIsTrue);
    }
}
