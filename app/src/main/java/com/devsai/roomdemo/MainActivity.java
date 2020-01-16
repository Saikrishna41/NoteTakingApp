package com.devsai.roomdemo;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.devsai.roomdemo.adapters.RecyclerViewAdapter;
import com.devsai.roomdemo.adapters.onNoteDeleteListener;
import com.devsai.roomdemo.model.Note;
import com.devsai.roomdemo.utils.verticalSpaceDecoration;
import com.devsai.roomdemo.viewmodels.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.UUID;

import static com.devsai.roomdemo.InsertNoteActivity.NOTE_ADDED;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,onNoteDeleteListener
{


    private static final int NEW_NOTE_ACTIVITY_REQUEST_CODE = 1 ;
    public static final int UPDATE_NOTE_ACTIVITY_REQUEST_CODE = 2 ;
    private FloatingActionButton fab;
    private static final String TAG = "MainActivity";
    public NoteViewModel mNoteViewModel;

    private RecyclerView mRecyclerView;

    private RecyclerViewAdapter mRecyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.fab);

        mRecyclerView = findViewById(R.id.recyclerview);


        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        setTitle("Notes List");


        initRecyclerView();

        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");

                Intent intent = new Intent(MainActivity.this, InsertNoteActivity.class);

                startActivityForResult(intent,NEW_NOTE_ACTIVITY_REQUEST_CODE);

            }
        });

        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        onSubscribed();



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            final String note_id = UUID.randomUUID().toString();

            Note note = new Note(note_id, data.getStringExtra(NOTE_ADDED));

            mNoteViewModel.insert(note);

            Toast.makeText(getApplicationContext(),"Added successfull",Toast.LENGTH_LONG).show();
        }
        else if(requestCode == UPDATE_NOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {


            Note note = new Note(data.getStringExtra("note_id"),
                    data.getStringExtra("note_text"));

            mNoteViewModel.update(note);

            Toast.makeText(getApplicationContext(),"Updated successfull",Toast.LENGTH_LONG).show();




        }
        else {
            Toast.makeText(getApplicationContext(),"not saved",Toast.LENGTH_LONG).show();

        }
    }


    private void onSubscribed() {

        mNoteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {

                if(notes != null) {
                    mRecyclerViewAdapter.setNotes(notes);

                }

            }
        });

    }

    private void initRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(linearLayoutManager);

        verticalSpaceDecoration verticalSpaceDecoration = new verticalSpaceDecoration(15);


        mRecyclerViewAdapter = new RecyclerViewAdapter(this, (onNoteDeleteListener) this);

        mRecyclerView.addItemDecoration(verticalSpaceDecoration);


        mRecyclerView.setAdapter(mRecyclerViewAdapter);


    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void OnDeleteClick(Note note) {

        mNoteViewModel.delete(note);
    }
}
