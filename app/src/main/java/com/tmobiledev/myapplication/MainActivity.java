package com.tmobiledev.myapplication;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.tmobiledev.myapplication.adapter.NameAdapter;
import com.tmobiledev.myapplication.model.NameModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NameAdapter.NameAdapterCallback {
    private static final String TAG = MainActivity.class.getName();
    private NameAdapter nameAdapter;

    private EditText edittext_search;
    private Button button_add, button_delete;
    private RecyclerView recyclerview_name;

//    private List<NameModel> nameModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext_search = (EditText) findViewById(R.id.edittext_search);
        button_add = (Button) findViewById(R.id.button_add);
        button_delete = (Button) findViewById(R.id.button_delete);
        recyclerview_name = findViewById(R.id.recyclerview_name);

        button_add.setBackgroundColor(ContextCompat.getColor(MainActivity.this, android.R.color.holo_red_light));

        nameAdapter = new NameAdapter(MainActivity.this, this);
        recyclerview_name.setAdapter(nameAdapter);
        recyclerview_name.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        edittext_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if(edittext_search.getText().toString().isEmpty()){
                    button_add.setBackgroundColor(ContextCompat.getColor(MainActivity.this, android.R.color.holo_red_light));
                } else {
                    button_add.setBackgroundColor(ContextCompat.getColor(MainActivity.this, android.R.color.holo_red_dark));
                }
            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edittext_search.getText().toString().isEmpty()){
                    nameAdapter.addNasme(edittext_search.getText().toString());
                }
            }
        });

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameAdapter.getNameModelList().size() > 0){
                    nameAdapter.removeItem();
//                    onDeleteName();
                }
            }
        });
    }

    private void onDeleteName(){
        List<NameModel> positionList = nameAdapter.getNameModelList();
        for(int i = 0;i < positionList.size();i++){
            if(positionList.get(i).getIsCheck()){
//                positionList.remove(i);
                Log.w(TAG, "remove : " + i);
            }
        }
        nameAdapter.notifyDataSetChanged();
//        nameAdapter.notifyItemRangeRemoved(0, nameModelList.size() - 1);
    }

    @Override
    public void onNameAdapterClickCheck(NameModel nameModel, int position) {
//        nameModelList.get(position).setName(nameModel.getName());
//        nameModelList.get(position).setIsCheck(nameModel.getIsCheck());
//        nameAdapter.notifyDataSetChanged();
    }
}