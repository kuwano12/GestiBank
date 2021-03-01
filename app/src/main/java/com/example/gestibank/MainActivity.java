package com.example.gestibank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button connect, createAcc, convertDevise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connect = (Button) findViewById(R.id.btnGoToAuth);
        createAcc = (Button) findViewById(R.id.btnCreateAccount);
        convertDevise = (Button) findViewById(R.id.btnGoToConv);
    }

    public void goToAuthPage(View v){
        Intent i = new Intent(getApplicationContext(), AuthActivity.class);
        startActivity(i);
    }
    public void goToCreateAccountPage(View v){
        Intent i = new Intent(getApplicationContext(), AdClientActivity.class);
        startActivity(i);
    }
    public void gotToConversionPage(View v){
        Intent i = new Intent(getApplicationContext(), ConversionActivity.class);
        startActivity(i);
    }
}