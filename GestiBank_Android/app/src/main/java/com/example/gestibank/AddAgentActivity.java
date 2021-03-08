package com.example.gestibank;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gestibank.model.User;
import com.example.gestibank.remote.APIUtils;
import com.example.gestibank.remote.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.gestibank.Utils.generatePassword;

public class AddAgentActivity extends AppCompatActivity {
    EditText agentName, agentFirstname, agentPhone, agentMail;
    Button btnAddNewAgent;
    private RetrofitInterface retrofitInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_agent);
        agentName      = (EditText) findViewById(R.id.editNewAgentName);
        agentFirstname = (EditText) findViewById(R.id.editNewAgentFirstName);
        agentPhone     = (EditText) findViewById(R.id.editNewAgentPhone);
        agentMail      = (EditText) findViewById(R.id.editNewAgentEmail);
        btnAddNewAgent = (Button) findViewById(R.id.btnCreateNewAgent);
        retrofitInterface = APIUtils.getuserInterface();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void createNewAgent(View v) {
        if(v == btnAddNewAgent){
            if(agentName.getText().toString().trim().length() == 0 ||
                    agentFirstname.getText().toString().trim().length() == 0 ||
                    agentPhone.getText().toString().trim().length() == 0 ||
                    agentMail.getText().toString().trim().length() == 0){
                Utils.showMessage("Error", "Veuillez remplir tous les champs",
                        AddAgentActivity.this);
                return;
            }

            Call<List<User>> call = retrofitInterface.getAgents("AGENT");
            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    if(response.isSuccessful()){
                       int i = response.body().size();
                       i = i+ 1;
                       String pass = generatePassword();
                       User c = new User(agentName.getText().toString(),
                               agentFirstname.getText().toString(),
                               agentPhone.getText().toString(),
                               agentMail.getText().toString(),
                               pass,
                               "AGENT",
                               "t",
                               "", String.valueOf(i));
                       Call<User> call2 = retrofitInterface.addClient(c);
                       call2.enqueue(new Callback<User>() {
                           @Override
                           public void onResponse(Call<User> call, Response<User> response) {
                               if(response.isSuccessful()){
                                   Toast.makeText(getApplicationContext(), "Compte agent crée",
                                            Toast.LENGTH_LONG).show();
                                   clearText();
//                                   Intent i = new Intent(getApplicationContext(), AuthActivity.class);
//                                   startActivity(i);
                               }
                           }
                           @Override
                           public void onFailure(Call<User> call, Throwable t) {
                               Log.e("ERROR", t.getMessage());
                           }
                       });
                    }
                }
                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    Log.e("ERROR", t.getMessage());
                }
            });
        }
    }
    public void clearText(){
        agentName.setText("");
        agentFirstname.setText("");
        agentPhone.setText("");
        agentMail.setText("");
    }
}