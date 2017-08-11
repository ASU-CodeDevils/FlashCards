package com.example.terin.asu_flashcardapp;

import android.app.Activity;
import android.content.Context;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.*;


/**
 * Created by Stephanie on 5/31/17.
 * File that controls the fragment_create_coursee.xml file.
 */

public class TitleCreateFragment extends Fragment{

    private static EditText tempTextInputField;
    TitleCreateListener activityCommander;

    /**
     * This interface ensures communication between fragment(s) and the "main" activity.
     */
    public interface TitleCreateListener{
        //The following method communicates between the "main" activity, and the fragment(s).
        void createPreview(String titleString);

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        Activity activity;

        if(context instanceof Activity) {
            activity = (Activity) context;
            try {
                activityCommander = (TitleCreateListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException("\nTHERE'S AN ISSUE WITH ACTIVITY IN TITLECREATE.java.");
            }
        }
    }

    /**
     * The overridden method of onCreateView that works with fragments.
     * @param inflater A tool that helps build the activity view on screen.
     * @param container A holder of widgets.
     * @param savedInstanceState This instance.
     * @return view The view to be created upon execution.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //This is where the change of EditText: tempInputText needs to be changed based on the button that got here.
        View view = inflater.inflate(R.layout.fragment_create_input, container, false);


        //tempTextInputField.setHint("@string/deckInput");




        Button createButton = (Button) view.findViewById(R.id.createButton);

        createButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        buttonClicked(view);
                    }
                }
        );


      return view;
    }

    /**
     * Method that execute the action to take place once the createButton is clicked.
     * @param view The fragment view being passed.
     */
    public void buttonClicked(View view){
        activityCommander.createPreview(tempTextInputField.getText().toString());

    }



}
