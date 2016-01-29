package com.ball.andy.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ball.andy.myapplication.ModifyScoreActivity;
import com.ball.andy.myapplication.R;
import com.ball.andy.myapplication.domain.GamePO;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2016/1/25.
 */
public class PlayGameAdapter extends RecyclerView.Adapter<PlayGameAdapter.ViewHolder> {

    private List<GamePO> pos = new ArrayList<>();
    private Context context;

    public PlayGameAdapter(List<GamePO> pos) {
        this.pos = pos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        View contactView = LayoutInflater.from(context).inflate(R.layout.content_game, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final GamePO contact = pos.get(position);


        if (StringUtils.equalsIgnoreCase(contact.getStatus(), "Y")) {


            SpannableStringBuilder style1 = new SpannableStringBuilder(contact.getName());
            int begin = contact.getName().indexOf("VS");
            int end = contact.getName().indexOf("VS") + 2;

            style1.setSpan(new StrikethroughSpan(), 0, contact.getName().length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);


            holder.name.setText(style1);
            holder.name.setTag(contact);


            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("data", (Serializable) holder.name.getTag());
                    intent.setClass(context, ModifyScoreActivity.class);


                    ((Activity) context).startActivityForResult(intent, 300);
                }
            });


            final String showMessage = contact.getaScore() + ":" + contact.getbScore();
            SpannableStringBuilder style = new SpannableStringBuilder(showMessage);


            if (NumberUtils.isNumber(contact.getaScore()) && NumberUtils.isNumber(contact.getbScore())) {

                int aScore = Integer.parseInt(contact.getaScore());
                int bScore = Integer.parseInt(contact.getbScore());
                if (aScore > bScore) {
                    style.setSpan(new ForegroundColorSpan(Color.RED), 0, showMessage.indexOf(":"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else if (aScore < bScore) {
                    style.setSpan(new ForegroundColorSpan(Color.RED), showMessage.indexOf(":") + 1, showMessage.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

                }
            }


//        style.setSpan(new ForegroundColorSpan(Color.DKGRAY),0,showMessage.indexOf(":"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        style.setSpan(new ForegroundColorSpan(Color.BLUE),showMessage.indexOf(":")+1,showMessage.length(),Spannable.SPAN_EXCLUSIVE_INCLUSIVE);


            holder.email.setText(style);
        } else {


            SpannableStringBuilder style1 = new SpannableStringBuilder(contact.getName());
            int begin = contact.getName().indexOf("VS");
            int end = contact.getName().indexOf("VS") + 2;
            style1.setSpan(new ForegroundColorSpan(Color.GREEN), begin, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            //  style1.setSpan(new ForegroundColorSpan(Color.BLUE),showMessage.indexOf(":")+1,showMessage.length(),Spannable.SPAN_EXCLUSIVE_INCLUSIVE);


            holder.name.setText(style1);
            holder.name.setTag(contact);


            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("data", (Serializable) holder.name.getTag());
                    intent.setClass(context, ModifyScoreActivity.class);


                    ((Activity) context).startActivityForResult(intent, 300);
                }
            });


            final String showMessage = contact.getaScore() + ":" + contact.getbScore();
            SpannableStringBuilder style = new SpannableStringBuilder(showMessage);


            if (NumberUtils.isNumber(contact.getaScore()) && NumberUtils.isNumber(contact.getbScore())) {

                int aScore = Integer.parseInt(contact.getaScore());
                int bScore = Integer.parseInt(contact.getbScore());
                if (aScore > bScore) {
                    style.setSpan(new ForegroundColorSpan(Color.RED), 0, showMessage.indexOf(":"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else if (aScore < bScore) {
                    style.setSpan(new ForegroundColorSpan(Color.RED), showMessage.indexOf(":") + 1, showMessage.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

                }
            }


//        style.setSpan(new ForegroundColorSpan(Color.DKGRAY),0,showMessage.indexOf(":"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        style.setSpan(new ForegroundColorSpan(Color.BLUE),showMessage.indexOf(":")+1,showMessage.length(),Spannable.SPAN_EXCLUSIVE_INCLUSIVE);


            holder.email.setText(style);
        }


    }

    @Override
    public int getItemCount() {
        return pos.size();
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
