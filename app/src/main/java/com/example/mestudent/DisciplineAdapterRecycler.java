package com.example.mestudent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DisciplineAdapterRecycler extends RecyclerView.Adapter {

    private final List<DisciplinesInfo> list;
    private Context context;

    public DisciplineAdapterRecycler(List<DisciplinesInfo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_rec_view_itens, parent, false);
        return new ViewHolderRecycler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderRecycler vh = (ViewHolderRecycler) holder;
        DisciplinesInfo Discipline = list.get(position);

        vh.txtDiscName.setText(Discipline.getDiscName());
    }

    @Override
    public int getItemCount() {
        return (list!=null)?list.size():0;
    }
}
