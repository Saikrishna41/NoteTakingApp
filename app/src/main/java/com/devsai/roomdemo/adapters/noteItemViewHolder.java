package com.devsai.roomdemo.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.devsai.roomdemo.EditNoteActivity;
import com.devsai.roomdemo.MainActivity;
import com.devsai.roomdemo.R;
import com.devsai.roomdemo.model.Note;


public class noteItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final String TAG = "noteItemViewHolder";
    TextView title;
    TextView uid;
    AppCompatImageView mEdit;
    AppCompatImageView mDelete;

    NoteClickListener mNoteClickListener;

    onNoteDeleteListener mOnNoteDeleteListener;

    private int mPosition;

    public noteItemViewHolder(@NonNull View itemView)  {
        super(itemView);

        title = itemView.findViewById(R.id.title);

        uid = itemView.findViewById(R.id.u_id);

        mEdit = itemView.findViewById(R.id.edit);

        mDelete = itemView.findViewById(R.id.delete);

        mEdit.setOnClickListener(this);

        mDelete.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

//        mNoteClickListener.onNoteClick(getAdapterPosition());


    }

    public void setListener(final Context context, final String uid) {


        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditNoteActivity.class);
                intent.putExtra("note_id",uid);

                ((Activity)context).startActivityForResult(intent, MainActivity.UPDATE_NOTE_ACTIVITY_REQUEST_CODE);


                Log.d(TAG, "onClick: "+uid);
            }
        });


    }

    public void deleteListener(final Note note,onNoteDeleteListener onNoteDeleteListener) {

        this.mOnNoteDeleteListener = onNoteDeleteListener;

        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                        mOnNoteDeleteListener.OnDeleteClick(note);
                }

            }
        });
    }
}

