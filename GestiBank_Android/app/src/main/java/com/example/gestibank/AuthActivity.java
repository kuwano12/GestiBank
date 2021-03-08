package com.example.gestibank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestibank.model.User;
import com.example.gestibank.remote.APIUtils;
import com.example.gestibank.remote.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity {
    EditText mail, password;
    Button btnAuth;
    User u = new User();
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        mail = (EditText) findViewById(R.id.editClientMail);
        password = (EditText) findViewById(R.id.editClientPassword);
        btnAuth = (Button) findViewById(R.id.btnConnect);
        retrofitInterface = APIUtils.getuserInterface();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void Login(View v){
        Call<User> call = retrofitInterface.login(mail.getText().toString(),
                password.getText().toString());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    if(response.body() == null){
                        Toast.makeText(getApplicationContext(), "Compte n'existe pas"
                                , Toast.LENGTH_LONG).show();
                        return;
                    }
                    u = response.body();
                    if(u.getActivated().equals("t") && u.getRole().equals("ADMIN")){
                        Intent i = new Intent(AuthActivity.this, AdminActivity.class);
                        startActivity(i);
                        finish();
                    } else if (u.getActivated().equals("t") && u.getRole().equals("AGENT")){
                        Intent i = new Intent(AuthActivity.this, AgentActivity.class);
                        startActivity(i);
                        finish();
                        Toast.makeText(getApplicationContext(), "AGENT"
                                , Toast.LENGTH_LONG).show();
                    } else if(u.getActivated().equals("t") && u.getRole().equals("CLIENT")){
                        Toast.makeText(getApplicationContext(), "CLIENT"
                                , Toast.LENGTH_LONG).show();
                    }else if(u.getActivated().equals("w")){
                        Toast.makeText(getApplicationContext(), "Compte en attente d'activation"
                                , Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Login ou mot de passe incorrecte"
                        , Toast.LENGTH_LONG).show();
                Log.i("Error", t.getMessage());
            }
        });
    }

}