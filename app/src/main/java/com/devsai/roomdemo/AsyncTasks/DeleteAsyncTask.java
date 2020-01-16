package com.devsai.roomdemo.AsyncTasks;

import android.os.AsyncTask;

import com.devsai.roomdemo.model.Note;
import com.devsai.roomdemo.persistence.NoteDao;

public class DeleteAsyncTask extends AsyncTask<Note,Void,Void> {


    private NoteDao mNoteDao;

    public DeleteAsyncTask(NoteDao noteDao) {
        this.mNoteDao = noteDao;
    }

    @Override
    protected Void doInBackground(Note... notes) {

        mNoteDao.delete(notes[0]);

        return null;
    }
}
