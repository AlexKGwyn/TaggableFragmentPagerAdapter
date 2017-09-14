package com.alexgwyn.taggablefragmentpageradapter.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class SampleFragment extends Fragment {

    private static final String KEY_IMAGE = "image";

    public static SampleFragment newInstance(String imageUrl) {
        SampleFragment fragment = new SampleFragment();

        Bundle args = new Bundle();
        args.putString(KEY_IMAGE, imageUrl);

        fragment.setArguments(args);
        return fragment;
    }

    private ImageView imageView;

    private String url;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString(KEY_IMAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sample, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = view.findViewById(R.id.image);

        Picasso.with(getContext())
                .load(url)
                .into(imageView);
    }
}
