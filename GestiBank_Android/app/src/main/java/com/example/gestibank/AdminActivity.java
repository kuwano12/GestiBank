package com.example.gestibank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {
    Button btnAddAgent, btnShowAgentList, btnShowListDemand, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        btnAddAgent       = (Button) findViewById(R.id.btnAgentAdd);
        btnShowAgentList  = (Button) findViewById(R.id.btnAgentList);
        btnShowListDemand = (Button) findViewById(R.id.btnDemand);
        btnLogout         = (Button) findViewById(R.id.logoutAdmin);
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

    public void gotToListDemand(View v){
        if(v == btnShowListDemand){
            Intent i = new Intent(getApplicationContext(), ListDemandActivity.class);
            startActivity(i);
        }
    }
    public void logout(View v){
        if(v == btnLogout) {
            Intent i = new Intent(getApplicationContext(), AuthActivity.class);
            startActivity(i);
            finish();
        }
    }
}