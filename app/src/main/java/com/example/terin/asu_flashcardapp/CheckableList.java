package com.example.terin.asu_flashcardapp;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephanie on 8/14/17.
 * This will show the user's list of decks associated
 * with the course, from which the user
 * chooses which deck(s) the user would like to study.
 */

public class CheckableList extends ArrayAdapter{

    Context context;
    List<String> modelItems;
    @SuppressWarnings("unchecked")

    public CheckableList(Context context, List<Deck> resource)
    {
        super(context,R.layout.fragment_decklist_checks,resource);
        // TODO Auto-generated constructor stub
        this.context = context;
        //this.modelItems = resource.toString();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.fragment_decklist_checks, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.textView1);
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);

        name.setText(modelItems.get(position));

        return convertView;
    }
}

