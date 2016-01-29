package com.ball.andy.myapplication.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ball.andy.myapplication.R;
import com.ball.andy.myapplication.dao.TeamDAO;
import com.ball.andy.myapplication.domain.TeamPO;
import com.ball.andy.myapplication.dto.MainPlayerDTO;
import com.ball.andy.myapplication.service.MainService;
import com.ball.andy.myapplication.service.MainServiceImpl;
import com.ball.andy.myapplication.util.GameCompnet;
import com.ball.andy.myapplication.util.GameCompnetImpl;
import com.ball.andy.myapplication.util.TeamCompnet;
import com.ball.andy.myapplication.util.TeamCompnetImpl;

import java.util.List;
import java.util.Map;

/**
 * Created by Andy on 2016/1/22.
 */
public class GameActivity extends AppCompatActivity {

    private Map<String, List<MainPlayerDTO>> teams;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private FloatingActionButton button;

    private GameCompnet gameCompnet = GameCompnetImpl.get();
    private TeamCompnet teamCompnet = TeamCompnetImpl.get();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        teams = (Map<String, List<MainPlayerDTO>>) getIntent().getSerializableExtra("data");


        mRecyclerView = (RecyclerView) findViewById(R.id.recylerView);
        this.button = (FloatingActionButton) findViewById(R.id.fab);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);


        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new GameAdapter(teams);
        mRecyclerView.setAdapter(mAdapter);

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final List<TeamPO> teamPOs = teamCompnet.makeTeams(teams, getApplication());



                final TeamDAO dao = new TeamDAO(getApplicationContext());


                List<TeamPO> allTeams = dao.getAll();

                for (TeamPO teamPO : allTeams) {
                    dao.delete(teamPO);
                }
                for (TeamPO teamPO : teamPOs) {
                    dao.insert(teamPO);
                }


                gameCompnet.makeGames(teamPOs, GameActivity.this);

                Snackbar.make(v, "已建立比賽清單", Snackbar.LENGTH_SHORT).setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();

                    }
                }).show();
            }
        });
    }

    private MainService getMainService() {
        return MainServiceImpl.get();
    }
}
