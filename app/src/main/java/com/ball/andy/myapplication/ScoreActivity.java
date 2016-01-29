package com.ball.andy.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ball.andy.myapplication.adapter.PlayGameAdapter;
import com.ball.andy.myapplication.controller.ScoreController;

/**
 * Created by Andy on 2016/1/25.
 */
public class ScoreActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ScoreController controller;

    private ProgressDialog progressDialog;

//    private Ballot ballot = null;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        controller.loadGames();
        mAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.controller = new ScoreController(this.getApplicationContext());

        mRecyclerView = (RecyclerView) findViewById(R.id.recylerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);


        // use a linear layout manager

        mLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        controller.loadGames();

        mAdapter = new PlayGameAdapter(controller.getDto().getGamePOs());
        mRecyclerView.setAdapter(mAdapter);


    }
//
//    public class Ballot extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//            progressDialog = ProgressDialog.show(MainActivity.this, "羽球分組工具", "分組ING", true);
//        }
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            controller.ballot();
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            progressDialog.dismiss();
//
//            ballot = null;
//
//            Intent it = new Intent();
//            it.putExtra("data", (Serializable) controller.getDto().g());
//            it.setClass(MainActivity.this, GameActivity.class);
//
//            startActivity(it);
//        }
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
     //   getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.make_team) {
//            if (ballot == null) {
//                ballot = new Ballot();
//                ballot.execute();
//            }
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
