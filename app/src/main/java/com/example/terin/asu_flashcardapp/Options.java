package com.example.terin.asu_flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by Stephanie on 5/21/17.
 */

public class Options extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Button create_new = (Button)findViewById(R.id.create_new_course);
        Button goto_study = (Button)findViewById(R.id.goto_study);
        Button goto_stats = (Button)findViewById(R.id.goto_stats);

        //The action listener for create_new_course button.
        create_new.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent startCreateActivity = new Intent(getApplicationContext(),
                                CreateOptions.class);
                        startActivity(startCreateActivity);

                    }
                }
        );

        //The action listener for goto_study button.
        goto_study.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent startCreateActivity = new Intent(getApplicationContext(),
                                CourseList.class);
                        startActivity(startCreateActivity);

                    }
                }
        );

        //The action listener for goto_stats button.
        goto_stats.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent startUserStatsActivity = new Intent(getApplicationContext(),
                                UserStats.class);
                        startActivity(startUserStatsActivity);

                    }
                }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        return true;
    }
}
