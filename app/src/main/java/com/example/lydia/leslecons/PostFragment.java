package com.example.lydia.leslecons;

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
        displayPost(getActivity().getIntent().getStringExtra("title"), getActivity().getIntent().getStringExtra("post"));
    }

    public void displayPost(String title, String post) {
        // getActivity() will return null if the method is called but onCreateView() and onActivityCreated() are not called yet
        if (getActivity() != null) {
            TextView titleView = getActivity().findViewById(R.id.title);
            TextView postView = getActivity().findViewById(R.id.post);
            titleView.setText(title);
            postView.setText(post);
        } else {
            System.out.println("getActivity() returned null in PostFragment.displayPost().");
        }
    }
}
