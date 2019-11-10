package com.example.mestudent;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderRecycler extends RecyclerView.ViewHolder{
    final TextView txtDiscName;


    public ViewHolderRecycler(@NonNull View itemView) {
        super(itemView);

        txtDiscName = (TextView)itemView.findViewById(R.id.txtDiscName);
    }

}
