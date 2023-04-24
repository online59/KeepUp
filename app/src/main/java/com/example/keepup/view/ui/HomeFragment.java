package com.example.keepup.view.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.keepup.R;


public class HomeFragment extends Fragment {

    public static HomeFragment instance;

    private HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment getInstance() {

        if (instance == null) {
            instance = new HomeFragment();
        }

        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}