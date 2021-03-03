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

import com.example.gestibank.model.User;
import com.example.gestibank.remote.APIUtils;
import com.example.gestibank.remote.RetrofitInterface;

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
        name           = (EditText) findViewById(R.id.editNewAgentName);
        firstname      = (EditText) findViewById(R.id.editNewAgentFirstName);
        phone          = (EditText) findViewById(R.id.editNewAgentPhone);
        mail           = (EditText) findViewById(R.id.editNewAgentEmail);
        btnAddUser = (Button) findViewById(R.id.btnCreateNewAgent);
        retrofitInterface = APIUtils.getuserInterface();
    }

    public void createNewUser(View v){
        if(v == btnAddUser){
            if(name.getText().toString().trim().length() == 0 ||
                    firstname.getText().toString().trim().length() == 0 ||
                    phone.getText().toString().trim().length() == 0 ||
                    mail.getText().toString().trim().length() == 0

            ){
                Utils.showMessage("Erreur", "Veuillez remplir tous les champs",
                        AdClientActivity.this);
                return;
            }
            User c = new User(name.getText().toString(), firstname.getText().toString(),
                    phone.getText().toString(), mail.getText().toString(), "AGENT", "w");
            Call<User> call = retrofitInterface.addClient(c);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(AdClientActivity.this, "Compte crée",
                                Toast.LENGTH_LONG).show();
                        clearText();
                        Intent i = new Intent(getApplicationContext(), AuthActivity.class);
                        startActivity(i);
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("Error", t.getMessage());
                }
            });
        }

    }

    public void goToAuthFromTxtV(View v){
        Intent i = new Intent(getApplicationContext(), AuthActivity.class);
        startActivity(i);
    }
    public void clearText(){
        name.setText("");
        firstname.setText("");
        phone.setText("");
        mail.setText("");
        login.setText("");
        password.setText("");
        confPass.setText("");

    }
}