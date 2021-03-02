package com.example.gestibank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestibank.model.Client;
import com.example.gestibank.remote.APIUtils;
import com.example.gestibank.remote.RetrofitInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity {
    EditText login, password;
    Button btnAuth;
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        login = (EditText) findViewById(R.id.editClientLogin);
        password = (EditText) findViewById(R.id.editClientPassword);
        btnAuth = (Button) findViewById(R.id.btnConnect);
        retrofitInterface = APIUtils.getuserInterface();
    }

    public void Login(View v){
        HashMap<String, String> map = new HashMap<>();
        map.put("login", login.getText().toString());
        map.put("password", password.getText().toString());
        Call<Client> call = retrofitInterface.login(map);
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Connexion réussie"
                            , Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Login ou mot de passe incorrecte"
                        , Toast.LENGTH_LONG).show();
                Log.i("Error", t.getMessage());
            }
        });
    }
}