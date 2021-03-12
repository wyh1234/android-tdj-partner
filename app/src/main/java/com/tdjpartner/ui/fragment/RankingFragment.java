package com.tdjpartner.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tdjpartner.R;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RankingFragment extends Fragment {

    private int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("~~" + getClass().getSimpleName() + ".onCreateView~~");
        System.out.println("inflater = " + inflater + ", container = " + container + ", savedInstanceState = " + savedInstanceState);

        return inflater.inflate(R.layout.fragment_ranking, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayAdapter<List<String>> arrayAdapter = new ArrayAdapter<List<String>>(getContext(), R.layout.adapter_ranking) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                System.out.println("~~" + getClass().getSimpleName() + ".getView~~");
                System.out.println("position = " + position + ", convertView = " + convertView + ", parent = " + parent);

                convertView = getLayoutInflater().inflate(R.layout.adapter_ranking, parent, false);

                List<String> data = getItem(position);
                System.out.println("data = " + data);
                TextView textView = convertView.findViewById(R.id.tv_ranking);
                textView.setText(data.get(0));
                textView = convertView.findViewById(R.id.tv_db);
                textView.setText(data.get(1));
                textView = convertView.findViewById(R.id.tv_higher);
                textView.setText(data.get(2));
                textView = convertView.findViewById(R.id.tv_action);
                textView.setText(data.get(3));

                return convertView;
            }
        };


        Random random = new Random();
        for (int i = 0; i < 10; i++) {
//            List<String> data = new ArrayList<>();
//            data.add("" + random.nextInt(999));
//            data.add("张" + random.nextInt(999));
//            data.add("李" + random.nextInt(999));
//            data.add("" + random.nextInt(999));
//            arrayAdapter.add(data);
            arrayAdapter.add(Arrays.asList("" + random.nextInt(999), "张" + random.nextInt(999), "李" + random.nextInt(999), "" + random.nextInt(999)));
        }



        ListView listView = view.findViewById(R.id.lv);
        listView.setAdapter(arrayAdapter);
        listView.setNestedScrollingEnabled(true);

    }
}