package com.example.lydia.leslecons;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
    public static final String POST_FRAGMENT_TAG = "POST_FRAGMENT_TAG";
    public static final String NEW_POST_FRAGMENT_TAG = "NEW_POST_FRAGMENT_TAG";
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
        if (getActivity().getFileStreamPath("lessons.txt").exists()) {
            readFiles();
        }

        // initialize list items and listener
        ListView list = getActivity().findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, new ArrayList<String>());
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
        String title = view.getText().toString();
        String post = lessonMap.get(title);
        if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(activity, PostActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("post", post);
            activity.startActivity(intent);
        } else {
            PostFragment postFrag = (PostFragment) activity.getSupportFragmentManager().findFragmentByTag(POST_FRAGMENT_TAG);
            if (postFrag == null) {
                postFrag = new PostFragment();
                NewFragment newFrag = (NewFragment) activity.getSupportFragmentManager().findFragmentByTag(NEW_POST_FRAGMENT_TAG);
                replaceFragments(newFrag, postFrag, POST_FRAGMENT_TAG);
            }
            postFrag.displayPost(title, post);

        }
    }

    private void newPost(View view) {
        DashboardActivity activity = (DashboardActivity) getActivity();
        if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(activity, NewActivity.class);
            activity.startActivity(intent);
        } else {
            NewFragment newFrag = new NewFragment();
            PostFragment postFrag = (PostFragment) activity.getSupportFragmentManager().findFragmentByTag(POST_FRAGMENT_TAG);
            replaceFragments(postFrag, newFrag, NEW_POST_FRAGMENT_TAG);
        }
    }

    private void replaceFragments(Fragment oldFrag, Fragment newFrag, String fragTag) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.post_add_container, newFrag, fragTag);
        transaction.commit();
        getActivity().getSupportFragmentManager().executePendingTransactions();
    }
}
