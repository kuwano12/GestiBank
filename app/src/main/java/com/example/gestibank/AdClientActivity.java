package com.example.gestibank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestibank.model.Client;
import com.example.gestibank.remote.APIUtils;
import com.example.gestibank.remote.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdClientActivity extends AppCompatActivity {
    TextView txtGotoConnect;
    EditText name, firstname, phone, mail, login, password, confPass;
    Button btnAddUser;
    private RetrofitInterface retrofitInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_client);
        txtGotoConnect = (TextView) findViewById(R.id.textViewGotoAuth);
        name           = (EditText) findViewById(R.id.editNewClientName);
        firstname      = (EditText) findViewById(R.id.editNewClientFirstName);
        phone          = (EditText) findViewById(R.id.editNewClientPhone);
        mail           = (EditText) findViewById(R.id.editNewClientEmail);
        login          = (EditText) findViewById(R.id.editLogin);
        password = (EditText) findViewById(R.id.editPassword);
        confPass = (EditText) findViewById(R.id.editConfPassword);
        btnAddUser = (Button) findViewById(R.id.btnCreateNewUser);
        retrofitInterface = APIUtils.getuserInterface();
    }

    public void createNewUser(View v){
        if(v == btnAddUser){
            if(name.getText().toString().trim().length() == 0 ||
                    firstname.getText().toString().trim().length() == 0 ||
                    phone.getText().toString().trim().length() == 0 ||
                    mail.getText().toString().trim().length() == 0 ||
                    login.getText().toString().trim().length() == 0 ||
                    password.getText().toString().trim().length() == 0 ||
                    confPass.getText().toString().trim().length() == 0
            ){
                Utils.showMessage("Erreur", "Veuillez remplir tous les champs",
                        AdClientActivity.this);
                return;
            }
            if(!password.getText().toString().equals(confPass.getText().toString())){
                Utils.showMessage("Erreur", password.getText().toString() + " " + confPass.getText().toString(),
                        AdClientActivity.this);
                return;
            }
            Client c = new Client(name.getText().toString(), firstname.getText().toString(),
                    phone.getText().toString(), mail.getText().toString(),
                    login.getText().toString(), password.getText().toString());
            Call<Client> call = retrofitInterface.addClient(c);
            call.enqueue(new Callback<Client>() {
                @Override
                public void onResponse(Call<Client> call, Response<Client> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(AdClientActivity.this, "Compte crée",
                                Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onFailure(Call<Client> call, Throwable t) {
                    Log.e("Error", t.getMessage());
                }
            });
        }

    }



    public void goToAuthFromTxtV(View v){
        Intent i = new Intent(getApplicationContext(), AuthActivity.class);
        startActivity(i);
    }
}