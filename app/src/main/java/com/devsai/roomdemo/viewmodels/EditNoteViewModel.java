package com.devsai.roomdemo.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.devsai.roomdemo.model.Note;
import com.devsai.roomdemo.persistence.NoteDao;
import com.devsai.roomdemo.persistence.NoteRoomDatabase;

public class EditNoteViewModel extends AndroidViewModel {

    private NoteRoomDatabase db;
    private NoteDao noteDao;
    private static final String TAG = "EditNoteViewModel";

    public EditNoteViewModel(@NonNull Application application) {
        super(application);
        db = NoteRoomDatabase.getInstance(application);

        noteDao = db.getDao();
    }

    public LiveData<Note> getNote(String noteId) {

        return noteDao.getNote(noteId);

    }
}
