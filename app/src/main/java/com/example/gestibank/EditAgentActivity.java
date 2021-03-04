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

public class EditAgentActivity extends AppCompatActivity {
    EditText editName, editFirstname, editPhone, editMail, editMatr;
    Button edit, delete;
    User u = new User();
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_agent);
        editName = (EditText) findViewById(R.id.editViewAgentName);
        editFirstname = (EditText) findViewById(R.id.editViewAgentFirstiname);
        editPhone = (EditText) findViewById(R.id.editAgentPhone);
        editMail = (EditText) findViewById(R.id.editMail);
        editMatr = (EditText) findViewById(R.id.editMatr);
        edit = (Button) findViewById(R.id.btnEditAgent);
        delete = (Button) findViewById(R.id.btnDeleteAgent);

        Bundle extra = getIntent().getBundleExtra("extra");
        u = (User) extra.getSerializable("User");
        editName.setText(u.getName());
        editFirstname.setText(u.getFirstname());
        editPhone.setText(u.getPhone());
        editMail.setText(u.getMail());
        editMatr.setText(u.getMatricule());
        retrofitInterface = APIUtils.getuserInterface();
    }

    public void editAgent(View v){
        if(v == edit){
            if(editName.getText().toString().trim().length() == 0 ||
                    editFirstname.getText().toString().trim().length() == 0 ||
                    editMail.getText().toString().trim().length() == 0 ||
                    editPhone.getText().toString().trim().length() == 0 ){
                Utils.showMessage("Error", "Veuillez remplir tous les champs",
                        EditAgentActivity.this);
                return;
            }
            User user = new User();
            user.setName(editName.getText().toString());
            user.setFirstname(editFirstname.getText().toString());
            user.setPhone(editPhone.getText().toString());
            user.setMail(editMail.getText().toString());
            user.setPassword(u.getPassword());
            user.setActivated(u.getActivated());
            user.setMatricule(editMatr.getText().toString());
            user.setRole("AGENT");
            Call<User> call = retrofitInterface.updateUser(editMail.getText().toString(), user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Toast.makeText(EditAgentActivity.this, "Agent Modifié", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(getIntent());
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("Error", t.getMessage());
                }
            });
        }
    }
    public void deleteAgent(View v){
        if(v == delete){
            Call<User> call = retrofitInterface.deleteUser(editMail.getText().toString(),
                    editMatr.getText().toString());
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(EditAgentActivity.this, "Agent supprimé",
                                Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), AgentListActivity.class);
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
}