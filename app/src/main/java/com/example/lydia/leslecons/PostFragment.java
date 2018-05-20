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
        PostActivity activity = (PostActivity) getActivity();
        TextView title = activity.findViewById(R.id.title);
        TextView post = activity.findViewById(R.id.post);
        title.setText(activity.getIntent().getStringExtra("title"));
        post.setText(activity.getIntent().getStringExtra("post"));
    }
}
