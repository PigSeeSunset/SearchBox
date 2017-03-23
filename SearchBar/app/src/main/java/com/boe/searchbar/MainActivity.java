package com.boe.searchbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
Button search_btn1,search_btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_btn1 = (Button) findViewById(R.id.search_btn1);
        search_btn2 = (Button) findViewById(R.id.search_btn2);
        search_btn1.setOnClickListener(this);
        search_btn2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_btn1:

                Intent intent1 =new Intent(MainActivity.this,SearchBarOneActivity.class);
                startActivity(intent1);

                break;
            case R.id.search_btn2:
                Intent intent2 =new Intent(MainActivity.this,SearchBarTwoActivity.class);
                startActivity(intent2);

                break;
            default:
                break;
        }

    }
}
