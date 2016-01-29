package com.ball.andy.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ball.andy.myapplication.R;
import com.ball.andy.myapplication.dto.PlayerItemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2016/1/29.
 */
public class PlayerSettingAdapter extends RecyclerView.Adapter<PlayerSettingAdapter.ViewHolder> {

    private List<PlayerItemDTO> playerItemDTOs = new ArrayList<>();

    public PlayerSettingAdapter(List<PlayerItemDTO> playerItemDTOs) {
        this.playerItemDTOs.clear();
        ;
        this.playerItemDTOs.addAll(playerItemDTOs);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        View contactView = LayoutInflater.from(context).inflate(R.layout.content_game, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PlayerItemDTO contact = playerItemDTOs.get(position);


        holder.name.setText(contact.getName());
        holder.name.setTag(contact);


        holder.email.setText("");


    }

    @Override
    public int getItemCount() {
        return playerItemDTOs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        private TextView email;


        public ViewHolder(View itemView) {
            super(itemView);

            this.name = (TextView) itemView.findViewById(R.id.name);
            this.email = (TextView) itemView.findViewById(R.id.email);


        }
    }
}
