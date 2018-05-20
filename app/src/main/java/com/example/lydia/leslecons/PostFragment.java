package com.example.lydia.leslecons;


import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PostFragment extends Fragment {


    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String title = getActivity().getIntent().getStringExtra("title");
        String post = getActivity().getIntent().getStringExtra("post");
        displayPost(title, post);
    }

    public void displayPost(String title, String post) {
        TextView titleView = getActivity().findViewById(R.id.title);
        TextView postView = getActivity().findViewById(R.id.post);
        if (title != null && post != null) {
            titleView.setText(title);
            postView.setText(post);
        } else {
            postView.setText("select a lesson to review.");
        }
    }
}
