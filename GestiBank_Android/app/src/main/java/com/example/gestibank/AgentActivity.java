package com.example.gestibank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.gestibank.model.User;
import com.example.gestibank.remote.APIUtils;
import com.example.gestibank.remote.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgentActivity extends AppCompatActivity {
    private RetrofitInterface retrofitInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent);
        retrofitInterface = APIUtils.getuserInterface();
        Call<List<User>> call = retrofitInterface.getPendingAccount("w");
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    List<User> listUser = response.body();
                    final ListView listView = (ListView)findViewById(R.id.agentListView);
                    listView.setAdapter(new CustomAgentAdapter(AgentActivity.this, listUser));
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });

    }
    public void agentLogOut(View v){
        Intent i = new Intent(AgentActivity.this, AuthActivity.class);
        startActivity(i);
        finish();
    }
}