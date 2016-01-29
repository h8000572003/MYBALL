package com.ball.andy.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.ball.andy.myapplication.adapter.PlayerSettingAdapter;
import com.ball.andy.myapplication.controller.PlayerSettingController;

/**
 * Created by Andy on 2016/1/29.
 */
public class PlayerSettingActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressDialog progressDialog;

    private PlayerSettingController controller = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_player_edit);

        this.controller = new PlayerSettingController(getApplicationContext());

        this.mRecyclerView = (RecyclerView) findViewById(R.id.recylerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        this.mRecyclerView.setHasFixedSize(true);


        // use a linear layout manager
        controller.load();


        this.mLayoutManager = new GridLayoutManager(this, 1);
        this.mRecyclerView.setLayoutManager(mLayoutManager);


        this.mAdapter = new PlayerSettingAdapter(controller.getDto().getPlayerPOs());
        this.mRecyclerView.setAdapter(this.mAdapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
}
