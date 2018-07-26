package com.example.terin.asu_flashcardapp;

import android.content.Context;
import android.widget.Toast;

public class CourseCreationException extends Exception {
    public CourseCreationException() {
        super();
    }

    public CourseCreationException(Context context, String message) {
        super(message);
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
    }
}
