package com.example.terin.asu_flashcardapp;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
/**
 * Created by Stephanie on 5/31/17.
 * Java file that controls the fragment_create_preview.xml file.
 */

public class PreviewFragment extends Fragment{

    private static TextView userTitle;

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
        View view = inflater.inflate(R.layout.fragment_create_preview, container, false);

        return view;
    }

    /**
     *The methos that sets the text on the preview image.
     * @param previewText the text entered by the user.
     */
    public void setPreviewText(String previewText){
        userTitle.setText(previewText);

    }
}
