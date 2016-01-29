package com.ball.andy.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ball.andy.myapplication.R;
import com.ball.andy.myapplication.adapter.TeamAdapter;
import com.ball.andy.myapplication.controller.ScoreController;

/**
 * Created by Andy on 2016/1/28.
 */
public class TeamFragment extends Fragment {

    private View rootView;


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ScoreController controller;






    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.content_gaming, container, false);
        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.controller = new ScoreController(this.getContext());

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recylerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);


        // use a linear layout manager

        mLayoutManager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setLayoutManager(mLayoutManager);


        controller.loadTeams();

        mAdapter = new TeamAdapter(controller.getDto().getTeamPOs());

        mRecyclerView.setAdapter(mAdapter);
    }
}
