package com.boe.searchbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SearchBarOneActivity extends AppCompatActivity implements SearchBarWidget.onSearchListener{
    private SearchBarWidget mSearchBarWidget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar_one);
        mSearchBarWidget = (SearchBarWidget)findViewById(R.id.search_bar);
        mSearchBarWidget.setOnSearchListener(this);
    }
    @Override
    public void onSearchChange(String search) {

        //mSearchContentTextView.setText(search);
    }
}
