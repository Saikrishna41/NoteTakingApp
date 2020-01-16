package com.devsai.roomdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.devsai.roomdemo.model.Note;
import com.devsai.roomdemo.viewmodels.EditNoteViewModel;

public class EditNoteActivity extends AppCompatActivity {
    private Button mUpdateBtn;
    private Button mCancelBtn;

    private EditText mEditNote;

    private Bundle bundle;

    private String uid;

    private LiveData<Note> note;

    EditNoteViewModel mEditNoteViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEditNoteViewModel = ViewModelProviders.of(this).get(EditNoteViewModel.class);


        setContentView(R.layout.activity_editnote);

        mUpdateBtn = findViewById(R.id.update_button);

        mCancelBtn = findViewById(R.id.cancel_button);

        mEditNote = findViewById(R.id.updateNote);


        bundle = getIntent().getExtras();



        if(bundle != null) {
            uid = bundle.getString("note_id");
        }

        note = mEditNoteViewModel.getNote(uid);


        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String updateNote = mEditNote.getText().toString();

                Intent resultIntent = new Intent();

                resultIntent.putExtra("note_id", uid);
                resultIntent.putExtra("note_text",updateNote);

                setResult(RESULT_OK,resultIntent);
                finish();
            }
        });

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        onSubscribeObservers();


    }

    private void onSubscribeObservers() {

        mEditNoteViewModel.getNote(uid).observe(this, new Observer<Note>() {
            @Override
            public void onChanged(Note note) {

                mEditNote.setText(note.getNotes());

            }
        });
    }
}
