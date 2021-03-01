package com.example.gestibank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AdClientActivity extends AppCompatActivity {
    TextView txtGotoConnect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_client);
        txtGotoConnect = (TextView) findViewById(R.id.textViewGotoAuth);
    }

    public void goToAuthFromTxtV(View v){
        Intent i = new Intent(getApplicationContext(), AuthActivity.class);
        startActivity(i);
    }
}