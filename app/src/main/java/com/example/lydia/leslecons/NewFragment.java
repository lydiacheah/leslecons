package com.example.lydia.leslecons;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintStream;

import static android.content.Context.MODE_APPEND;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewFragment extends Fragment {


    public NewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().findViewById(R.id.done_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveLesson(view);
            }
        });
    }

    public void saveLesson(View view) {
        Activity activity = getActivity();
        String title = ((EditText) activity.findViewById(R.id.title_edit)).getText().toString();
        String post = ((EditText) activity.findViewById(R.id.post_edit)).getText().toString();
        try {
            PrintStream output = new PrintStream(activity.openFileOutput("lessons.txt", MODE_APPEND));
            output.println(title + "\t" + post);
            output.close();
            Toast.makeText(activity, "Lesson saved.", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(activity, "We were unable to save this lesson. Please try again.", Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(activity, DashboardActivity.class);
        startActivity(intent);
    }

}
