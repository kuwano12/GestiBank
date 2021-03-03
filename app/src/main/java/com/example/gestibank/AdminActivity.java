package com.example.gestibank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {
    Button btnAddAgent, btnShowAgentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        btnAddAgent = (Button) findViewById(R.id.btnAgentAdd);
        btnShowAgentList = (Button) findViewById(R.id.btnAgentList);
    }
    public void goToAgentList(View v){
        if(v == btnShowAgentList){
            Intent i = new Intent(getApplicationContext(), AgentListActivity.class);
            startActivity(i);
        }
    }

    public void goToAddAgentForm(View v){
        if(v == btnAddAgent){
            Intent i = new Intent(getApplicationContext(), AddAgentActivity.class);
            startActivity(i);
        }
    }
}