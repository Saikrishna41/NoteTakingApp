package com.devsai.roomdemo.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class verticalSpaceDecoration  extends RecyclerView.ItemDecoration {

    private int veritcalSpace;

    public verticalSpaceDecoration(int veritcalSpace) {
        this.veritcalSpace = veritcalSpace;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.top = veritcalSpace;
    }
}
