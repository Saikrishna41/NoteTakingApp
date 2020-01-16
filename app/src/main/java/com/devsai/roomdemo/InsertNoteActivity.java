package com.devsai.roomdemo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InsertNoteActivity extends AppCompatActivity {
    private static final String TAG = "InsertNoteActivity";
     static final String  NOTE_ADDED = "new_note";

    //ui components
    private EditText mEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_newnote);

        mEditText = findViewById(R.id.note_title);

        Button mButton = findViewById(R.id.save_btn);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: btn works");

                Intent resultIntent = new Intent();

                if(TextUtils.isEmpty(mEditText.getText())) {

                    setResult(RESULT_CANCELED,resultIntent);
                }
                else {

                    String note = mEditText.getText().toString();

                    resultIntent.putExtra(NOTE_ADDED,note);

                    setResult(RESULT_OK,resultIntent);

                }

                finish();
            }
        });

    }
}
