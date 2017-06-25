package com.example.terin.asu_flashcardapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.transition.*;


/**
 * Created by Stephanie on 5/21/17.
 */

public class Options extends AppCompatActivity{

    private Intent startCreateActivity;
    TransitionConstants.TransitionType type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        final Button create_new_course = (Button) findViewById(R.id.create_new_course);
        Button goto_study = (Button) findViewById(R.id.goto_study);
        Button goto_stats = (Button) findViewById(R.id.goto_stats);

        //The action listener for create_new_course button.
        create_new_course.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View view){

                        startCreateActivity = new Intent(getApplicationContext(),
                                CreateItems.class);
                                //AddCourse.class);
                        startActivity(startCreateActivity);
                        //explodeTransition(create_new_course);
                    }
                }
        );

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //The action listener for goto_study button.
        goto_study.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent startCreateActivity = new Intent(getApplicationContext(),
                                StudyWindow.class);
                        startActivity(startCreateActivity);

                    }
                }
        );

        //The action listener for goto_stats button.
        goto_stats.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent startUserStatsActivity = new Intent(getApplicationContext(),
                                UserStats.class);
                        startActivity(startUserStatsActivity);

                    }
                }
        );

        //initPage();
        //initAnimation();


    }

    /**
     * Method to maintain a unified menu across all screens.
     * @param menu The menu being used.
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Method for functionality of buttons in the menu.
     * @param item The item being selected.
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){


        return true;
    }

    /**
     * Method that controls the transitions of this window activity
     * to other window activities.
     * @param view the view being transitioned.
     */
    public void explodeTransition(View view){
        //Check the if running Android 5.0 or higher i.e Lollipop or other, used for Materials design.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            Intent intent = new Intent(Options.this, AddCourse.class);
            intent.putExtra(TransitionConstants.KEY_ANIM_TYPE, TransitionConstants.TransitionType.ExplodeButton);
            startActivity(intent, options.toBundle());

        }else{
            //Implement this feature with out Material design.
        }
    }


    /**
     * Method that handles transitions between activity screens.
     *
     *
     private void initPage() {

     type = (TransitionConstants.TransitionType) getIntent().getSerializableExtra(TransitionConstants.KEY_ANIM_TYPE);



     }
     */

    /**
     *private void initAnimation() {

     switch (type) {

     case ExplodeButton: {
     //Check the if running Android 5.0 or higher i.e Lollipop or other, used for Materials design.
     if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
     Explode enterTransition = new Explode();
     enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
     getWindow().setEnterTransition(enterTransition);

     }else{
     //Implement this feature with out Material design.
     }
     break;
     }

     }
     }
     */

}
