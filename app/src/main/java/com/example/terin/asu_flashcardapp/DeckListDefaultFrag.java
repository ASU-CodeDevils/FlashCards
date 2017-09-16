package com.example.terin.asu_flashcardapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Stephanie on 8/13/17.
 */

public class DeckListDefaultFrag extends Fragment {

    ChangeFragListener activityCommander;

    /**
     * Empty constructor.
     */
    public DeckListDefaultFrag() {}

    /**
     * Interface that controls the changes the list of decks
     * to a list with check boxes.
     */
    public interface ChangeFragListener{
        void changeFragment(int id);
    }

    /**
     * The overridden method of onCreateView that communicates with fragments.
     * @param inflater A tool that helps build the activity view on screen.
     * @param container A holder of widgets. 
     * @param savedInstanceState This instance. 
     * @return view The view to be created upon execution.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_decklist_default,container, false);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;

        if(context instanceof Activity) {
            activity = (Activity) context;
            try{
                activityCommander = (ChangeFragListener) activity;
            }catch(ClassCastException e) {
                throw new ClassCastException("\nTHERE'S AN ISSUE " +
                        "WITH ACTIVITY IN DeckListDefaultFrag.java.");
            }
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        activityCommander = null;

    }

}

