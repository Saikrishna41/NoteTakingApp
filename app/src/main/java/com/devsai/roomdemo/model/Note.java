package com.devsai.roomdemo.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")

public class Note implements Parcelable {



    @PrimaryKey()
    @NonNull
    private String id;

    @NonNull
    @ColumnInfo(name = "note")
    private String mNotes;

    @Ignore
    public Note() {
    }

    public Note(@NonNull String id, @NonNull String mNotes) {
        this.id = id;
        this.mNotes = mNotes;
    }

    protected Note(Parcel in) {
        id = in.readString();
        mNotes = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getNotes() {
        return mNotes;
    }

    public void setNotes(@NonNull String mNotes) {
        this.mNotes = mNotes;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id='" + id + '\'' +
                ", mNotes='" + mNotes + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(mNotes);
    }
}
