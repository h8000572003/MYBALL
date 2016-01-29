package com.ball.andy.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ball.andy.myapplication.R;
import com.ball.andy.myapplication.dto.TeamDTO;

import java.util.List;

/**
 * Created by Andy on 2016/1/28.
 */
public class TeamScoreAdapter extends RecyclerView.Adapter<TeamScoreAdapter.ViewHolder> {

    private List<TeamDTO> teamPOs;

    public TeamScoreAdapter(List<TeamDTO> teamPOs) {
        this.teamPOs = teamPOs;
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
        final TeamDTO contact = teamPOs.get(position);


        holder.name.setText(contact.getTeamName() + "(" + contact.getScore() + ")");
        holder.name.setTag(contact);
        holder.email.setText(contact.getPlayer1() + "," + contact.getPlayer2());


    }

    @Override
    public int getItemCount() {
        return teamPOs.size();
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
