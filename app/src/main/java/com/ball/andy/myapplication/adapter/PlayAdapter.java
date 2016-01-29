package com.ball.andy.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ball.andy.myapplication.R;
import com.ball.andy.myapplication.dto.MainPlayerDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2016/1/21.
 */
public class PlayAdapter extends RecyclerView.Adapter<PlayAdapter.ViewHolder> {

    private List<MainPlayerDTO> mainPlayerDTOList=new ArrayList<>();

    public PlayAdapter(List<MainPlayerDTO> mainPlayerDTsO) {
        this.mainPlayerDTOList.addAll(mainPlayerDTsO);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        View contactView = LayoutInflater.from(context).inflate(R.layout.item_contact, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MainPlayerDTO contact = mainPlayerDTOList.get(position);


        holder.name.setText(contact.getName());
        holder.name.setTag(contact);


        holder.email.setText(contact.getMail());
        holder.checkBox.setChecked(contact.isClick());
        holder.checkBox.setTag(contact);

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CheckBox cb = (CheckBox) v;
                final MainPlayerDTO contact = (MainPlayerDTO) cb.getTag();
                contact.setClick(cb.isChecked());
            }
        });


    }

    @Override
    public int getItemCount() {
        return mainPlayerDTOList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        private TextView email;
        private CheckBox checkBox;


        public ViewHolder(View itemView) {
            super(itemView);

            this.name = (TextView) itemView.findViewById(R.id.name);
            this.email = (TextView) itemView.findViewById(R.id.email);
            this.checkBox = (CheckBox) itemView.findViewById(R.id.chkSelected);

        }
    }
}
