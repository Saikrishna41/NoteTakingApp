package com.devsai.roomdemo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devsai.roomdemo.R;
import com.devsai.roomdemo.model.Note;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private NoteClickListener mNoteClickListener;

    private onNoteDeleteListener mOnNoteDeleteListener;

    private List<Note> mNotes;

    Context context;

    public RecyclerViewAdapter(Context context, onNoteDeleteListener onNoteDeleteListener) {

        this.context = context;
        this.mOnNoteDeleteListener = onNoteDeleteListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item,parent,false);

        return new noteItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        (((noteItemViewHolder)holder).title).setText(mNotes.get(position).getNotes());
        (((noteItemViewHolder)holder).uid).setText(mNotes.get(position).getId());
        //update notes
        (((noteItemViewHolder)holder)).setListener(context,mNotes.get(position).getId());
        //delete notes
        (((noteItemViewHolder)holder)).deleteListener(mNotes.get(position),mOnNoteDeleteListener);


    }

    @Override
    public int getItemCount() {
        if(mNotes != null) {
            return mNotes.size();
        }
        else  {
            return 0;
        }

    }

    public void setNotes(List<Note> notes) {
        mNotes = notes;
        notifyDataSetChanged();
    }

}
