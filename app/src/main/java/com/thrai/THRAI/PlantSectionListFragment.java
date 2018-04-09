package com.thrai.THRAI;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thrai.THRAI.Adapters.PlantSectionAdapter;
import com.thrai.THRAI.Interfaces.OnListFragmentInteractionListener;
import com.thrai.THRAI.ModelClasses.Plant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * Created by fahim on 3/15/18.
 */

public class PlantSectionListFragment extends Fragment {
    private OnListFragmentInteractionListener mListener;

    private HashSet<String> all_tags = new HashSet<>();

    private List<Plant> plants = new ArrayList<>();

    private SectionedRecyclerViewAdapter sectionAdapter;

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plant_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            sectionAdapter = new SectionedRecyclerViewAdapter();

            recyclerView.setAdapter(sectionAdapter);
        }
        return view;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    public void setAll_tags(HashSet<String> all_tags) {
        this.all_tags = all_tags;
    }

    public void updateList(){
        sectionAdapter = new SectionedRecyclerViewAdapter();
        new AddSectionsAsync().execute();
    }

    private void refreshView(){
        sectionAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(sectionAdapter);
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private class AddSectionsAsync extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            Iterator<String> iterator = all_tags.iterator();
            while (iterator.hasNext()){
                String tag = iterator.next();
                List<Plant> sectionPlant = new ArrayList<>();
                for (Plant p :
                        plants) {
                    if(p.getTags().contains(tag))
                        sectionPlant.add(p);
                }
                PlantSectionAdapter plantSectionAdapter = new PlantSectionAdapter(tag,sectionPlant,mListener);
                sectionAdapter.addSection(plantSectionAdapter);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            refreshView();
        }
    }
}
