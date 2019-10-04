package com.example.mestudent;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderRecycler extends RecyclerView.ViewHolder {
    final TextView txtDiscName;

    public ViewHolderRecycler(@NonNull View itemView) {
        super(itemView);

        txtDiscName = (TextView)itemView.findViewById(R.id.txtDiscName);
    }
}
