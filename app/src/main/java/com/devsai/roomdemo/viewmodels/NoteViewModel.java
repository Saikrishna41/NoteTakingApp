package com.devsai.roomdemo.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Update;

import com.devsai.roomdemo.AsyncTasks.DeleteAsyncTask;
import com.devsai.roomdemo.AsyncTasks.InsertAsyncTask;
import com.devsai.roomdemo.AsyncTasks.UpdateAsyncTask;
import com.devsai.roomdemo.model.Note;
import com.devsai.roomdemo.persistence.NoteDao;
import com.devsai.roomdemo.persistence.NoteRoomDatabase;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private static final String TAG = "NoteViewModel";

    private NoteDao noteDao;

    private NoteRoomDatabase noteDb;

    private LiveData<List<Note>>  mAllNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        noteDb = NoteRoomDatabase.getInstance(application);

        noteDao = noteDb.getDao();

        mAllNotes = noteDao.getAllNotes();

    }

    public void insert(Note note) {

        new InsertAsyncTask(noteDao).execute(note);
    }
    public void update(Note note) {

        new UpdateAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note) {
        new DeleteAsyncTask(noteDao).execute(note);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: Viewmodel destroyed");
    }

    public LiveData<List<Note>> getAllNotes() {

        return mAllNotes;
    }
}
