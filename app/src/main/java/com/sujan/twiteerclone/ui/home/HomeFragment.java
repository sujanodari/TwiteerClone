package com.sujan.twiteerclone.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sujan.twiteerclone.R;
import com.sujan.twiteerclone.adapter.TweetAdapter;
import com.sujan.twiteerclone.model.TweetM;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    List<TweetM> tweetMS = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.HomeRV);

        loadCurrentUser();


        return root;
    }

    private void loadCurrentUser() {


        tweetMS.add(new TweetM("My life My rule","easy-come easy-go","my.jpg", "mym.jpg"));
        TweetAdapter tweetAdapter = new TweetAdapter(getContext(), tweetMS);
        recyclerView.setAdapter(tweetAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}