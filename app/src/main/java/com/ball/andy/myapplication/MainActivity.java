package com.ball.andy.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ball.andy.myapplication.adapter.PlayAdapter;
import com.ball.andy.myapplication.controller.MainController;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private MainController controller;

    private ProgressDialog progressDialog;

    private Ballot ballot = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.controller = new MainController(this.getApplicationContext());

        mRecyclerView = (RecyclerView) findViewById(R.id.recylerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);


        // use a linear layout manager

        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        controller.loadData();

        mAdapter = new PlayAdapter(controller.getDto().getPlayerDTOs());
        mRecyclerView.setAdapter(mAdapter);




    }



    public class Ballot extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MainActivity.this, "羽球分組工具", "分組ING", true);
        }

        @Override
        protected Void doInBackground(Void... params) {
            controller.ballot();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();

            ballot = null;

            Intent it = new Intent();
            it.putExtra("data", (Serializable) controller.getDto().getTeamPOs());
            it.setClass(MainActivity.this, GameActivity.class);

            startActivity(it);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.make_team) {
            if (ballot == null) {
                ballot = new Ballot();
                ballot.execute();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
