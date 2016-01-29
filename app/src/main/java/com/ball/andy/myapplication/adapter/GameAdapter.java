package com.ball.andy.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ball.andy.myapplication.R;
import com.ball.andy.myapplication.dto.MainPlayerDTO;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Andy on 2016/1/22.
 */
public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private Map<String, List<MainPlayerDTO>> teams;

    public GameAdapter(Map<String, List<MainPlayerDTO>> teams) {
        this.teams = teams;
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
        final List<MainPlayerDTO> contact = teams.get(position+"");


        holder.name.setText(position+"組");
        holder.name.setTag(contact);

        List<String> strings = new ArrayList<>();

        for (MainPlayerDTO playerDTO : contact) {
            strings.add(playerDTO.getName());
        }

        holder.email.setText(StringUtils.join(strings, ","));

    }

    @Override
    public int getItemCount() {
        return teams.size();
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
