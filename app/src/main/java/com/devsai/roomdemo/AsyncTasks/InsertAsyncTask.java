package com.devsai.roomdemo.AsyncTasks;

import android.os.AsyncTask;

import com.devsai.roomdemo.model.Note;
import com.devsai.roomdemo.persistence.NoteDao;

public class InsertAsyncTask extends AsyncTask<Note,Void,Void> {

    NoteDao mNoteDao;

    public InsertAsyncTask(NoteDao noteDao) {


        this.mNoteDao = noteDao;
    }

    @Override
    protected Void doInBackground(Note... notes) {

        mNoteDao.insert(notes);

        return null;

    }
}
