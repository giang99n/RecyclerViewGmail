package com.example.recyclerviewgmail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<EmailItemModel> items;
    List<EmailItemModel>itemFavorite ;
    List<EmailItemModel>items2;
    EditText editText ;
    Button btnFavorite;
    EmailItemAdapter adapter;
    Boolean btnF=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();

        items.add(new EmailItemModel("Name1","Subject", "Content","13:15PM"));
        items.add(new EmailItemModel("B Name","Subject2", "Content","13:15PM"));
        items.add(new EmailItemModel("C Name1","Subject3", "Content","13:15PM"));
        items.add(new EmailItemModel("D Name1","Subject4", "Content","13:15PM"));
        items.add(new EmailItemModel("Name1","Subject5", "Content","13:15PM"));
        items.add(new EmailItemModel("A Name","Subject6", "Content","13:15PM"));
        items.add(new EmailItemModel("Name4","Subject77", "Content","13:15PM"));
        items.add(new EmailItemModel("Name3","Subject", "Content","13:15PM"));
        items.add(new EmailItemModel("Name2","Subject", "Content","13:15PM"));
        items.add(new EmailItemModel("Name1","Subject", "Content","13:15PM"));

        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

         adapter =new EmailItemAdapter(items);
        recyclerView.setAdapter(adapter);

        editText = findViewById(R.id.txtTim);
        btnFavorite = findViewById(R.id.btnfavorite);
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnF==false){
                    itemFavorite = new ArrayList<>();
                    for(int i=0;i<items.size();i++){
                        if(items.get(i).isFavorite())
                            itemFavorite.add(items.get(i));
                    }
                    adapter = new EmailItemAdapter(itemFavorite);
                    recyclerView.setAdapter(adapter);
                    btnF=true;
                    btnFavorite.setText("BACK");
                }else {
                    adapter = new EmailItemAdapter(items);
                    recyclerView.setAdapter(adapter);
                    btnF =false;
                    btnFavorite.setText("FAVORITE");
                }
                editText.setText("");
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              if(editText.getText().toString()==""){
                  adapter = new EmailItemAdapter(items);
                  recyclerView.setAdapter(adapter);
              }else{
                  if(btnF==false){
                      items2 =new ArrayList<>();
                      for(int i=0;i<items.size();i++){
                          String s1=editText.getText().toString();
                          if(items.get(i).getName().toString().toLowerCase().indexOf(s1.toLowerCase())!=-1)
                              items2.add(items.get(i));
                      }
                      adapter =new EmailItemAdapter(items2);
                      recyclerView.setAdapter(adapter);
                  }else{
                      items2 =new ArrayList<>();
                      for(int i=0;i<itemFavorite.size();i++){
                          String s1=editText.getText().toString();
                          if(itemFavorite.get(i).getName().toString().toLowerCase().indexOf(s1.toLowerCase())!=-1)
                              items2.add(itemFavorite.get(i));
                      }
                      adapter =new EmailItemAdapter(items2);
                      recyclerView.setAdapter(adapter);
                  }

              }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
