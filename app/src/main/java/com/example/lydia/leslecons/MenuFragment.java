package com.example.lydia.leslecons;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MenuFragment extends Fragment {
    HashMap<String, String> lessonMap = new HashMap<>();

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    private void readFiles() {
        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(getActivity().openFileInput("lessons.txt")));
            String line;
            StringBuffer text = new StringBuffer();
            String[] textArr;
            while ((line = bReader.readLine()) != null) {
                textArr = line.split("\t");
                lessonMap.put(textArr[0], textArr[1]);
            }
        } catch (IOException e) {
            Toast.makeText(getActivity(), "We were unable to load your lessons. Please try again.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        readFiles();

        // initialize list items and listener
        ListView list = getActivity().findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_activated_1, new ArrayList<String>());
        list.setAdapter(adapter);
        for (String key : lessonMap.keySet()) {
            adapter.add(key);
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openPost((TextView) view);
            }
        });

        // add listener to 'new' button
        getActivity().findViewById(R.id.new_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPost(view);
            }
        });
    }

    private void openPost(TextView view) {
        DashboardActivity activity = (DashboardActivity) getActivity();
        Intent intent = new Intent(activity, PostActivity.class);
        String title = view.getText().toString();
        String post = lessonMap.get(title);
        intent.putExtra("title", title);
        intent.putExtra("post", post);
        activity.startActivity(intent);
    }

    private void newPost(View view) {
        DashboardActivity activity = (DashboardActivity) getActivity();
        Intent intent = new Intent(activity, NewActivity.class);

        activity.startActivity(intent);
    }
}
