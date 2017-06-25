package com.example.terin.asu_flashcardapp;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.*;

/**
 * Created by Stephanie on 5/31/17.
 * File that controls the fragment_create_title.xml file.
 */

public class TitleCreateFragment extends Fragment{

    private static EditText tempTextInputField;

    TitleCreateListener activityCommander;

    /**
     * The overriden method of onCreateView that works with fragments.
     * @param inflater A tool that helps build the activity view on screen.
     * @param container A holder of widgets.
     * @param savedInstanceState This instance.
     * @return view The view to be created upon execution.
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_title, container, false);;

        //Check the if running Android 5.0 or higher i.e Lollipop or other, used for Materials design.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            //Material API's called.

            //view = inflater.inflate(R.layout.fragment_create_title, container, false);
            tempTextInputField = (EditText) view.findViewById(R.id.tempTextInputField);
            final Button createButton = (Button) view.findViewById(R.id.createButton);

            createButton.setOnClickListener(
                    new View.OnClickListener(){
                        public void onClick(View view){
                            buttonClicked(view);
                            /**
                             * TODO:
                             * Add the functionality to the create button
                             * that changes the text on the
                             * fragment_create_preview.
                             */
                        }
                    }
            );

        }else{
            //Implement this feature with out Material design.


        }

      return view;
    }

    /**
     * This interface ensures communication between fragment(s) and the "main" activity.
     */
    public interface TitleCreateListener{
        //The following method communicates between the "main" activity, and the fragments(s).
        void createPreview(String titleString);

    }

    /**
     * Method that attaches the specified fragment to the "main" activity screen.
     * @param childFragment The fragment to be used.
     */
    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);

        try{
            activityCommander = (TitleCreateListener) childFragment;
        }catch(ClassCastException e){
            throw new ClassCastException(childFragment.toString());
        }
    }


    /**
     * Method that execute the action to take place once the createButton is clicked.
     * @param view The fragment view being passed.
     */
    public void buttonClicked(View view){
        activityCommander.createPreview(tempTextInputField.getText().toString());
    }
}
