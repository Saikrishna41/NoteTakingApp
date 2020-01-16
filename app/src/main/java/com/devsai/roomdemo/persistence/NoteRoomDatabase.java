package com.devsai.roomdemo.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.devsai.roomdemo.model.Note;



@Database(entities = Note.class, version = 1)
public abstract class NoteRoomDatabase extends RoomDatabase {

    private static NoteRoomDatabase instance;

    public static NoteRoomDatabase getInstance(final Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteRoomDatabase.class,
                    "notes_database"
                        )
                    .build();
        }
        return instance;
    }

    public abstract NoteDao getDao();
}
