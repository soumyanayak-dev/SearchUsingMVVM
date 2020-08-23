package com.soumya.altimetriksearch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.soumya.altimetriksearch.adapter.SearchAdapter;
import com.soumya.altimetriksearch.model.Search;
import com.soumya.altimetriksearch.viewmodel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private MainActivityViewModel mMainActivityViewModel;
    private RecyclerView mRecyclerView;
    private SearchAdapter mAdapter;
    private SearchView mSearchView;
    private Spinner spinner;
    private String[] searchOptions = { "Artistname", "Trackname", "Collectionname", "Collectionprice", "Releasedate"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.search_list);
        mSearchView = findViewById(R.id.search_view);
        spinner = findViewById(R.id.spinner);

        mMainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        initSpinner();
        initRecyclerView();
        subscribeObservers();
        initSearchView();
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }

    private void subscribeObservers(){
        mMainActivityViewModel.getSearch().observe(this, search -> {
            if(search != null){
                if(mMainActivityViewModel.IsViewingSearch()) {
                    mAdapter.setSearch(search);
                    mMainActivityViewModel.setIsQueryingSearch(false);
                }
            }
        });
    }

    private void initSpinner(){
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,searchOptions);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    private void initRecyclerView(){
        mAdapter = new SearchAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void initSearchView(){
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mMainActivityViewModel.searchApi(query);
                mSearchView.clearFocus();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        initRecyclerView();
        mMainActivityViewModel.searchApi(spinner.getSelectedItem().toString());
        mSearchView.clearFocus();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
