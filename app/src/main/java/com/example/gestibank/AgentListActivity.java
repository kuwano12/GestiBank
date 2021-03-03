package com.example.gestibank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gestibank.model.User;
import com.example.gestibank.remote.APIUtils;
import com.example.gestibank.remote.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgentListActivity extends AppCompatActivity {
    private RetrofitInterface retrofitInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_list);
        retrofitInterface = APIUtils.getuserInterface();
        Call<List<User>> call = retrofitInterface.getAgents("AGENT");
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    List<User> listUser = response.body();
//                    Utils.showMessage("test", listUser.toString(), AgentListActivity.this);
                    final ListView listView = (ListView) findViewById(R.id.listView);
                    listView.setAdapter(new CustomListAdapter(AgentListActivity.this, listUser));
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Object o = listView.getItemAtPosition(position);
                            User u = (User) o;
                            Intent i = new Intent(getApplicationContext(), EditAgentActivity.class);
                            Bundle extra = new Bundle();
                            extra.putSerializable("User", u);
                            i.putExtra("extra", extra);
                            startActivity(i);
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