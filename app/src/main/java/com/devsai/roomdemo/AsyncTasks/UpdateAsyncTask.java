package com.devsai.roomdemo.AsyncTasks;

import android.os.AsyncTask;

import com.devsai.roomdemo.model.Note;
import com.devsai.roomdemo.persistence.NoteDao;

public class UpdateAsyncTask extends AsyncTask<Note,Void,Void> {

    private NoteDao mNoteDao;

    public UpdateAsyncTask(NoteDao noteDao) {

        this.mNoteDao = noteDao;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        mNoteDao.update(notes[0]);
        return null;
    }
}
