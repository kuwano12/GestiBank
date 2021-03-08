package com.example.gestibank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gestibank.model.User;
import com.example.gestibank.remote.APIUtils;
import com.example.gestibank.remote.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDemandActivity extends AppCompatActivity {
    private RetrofitInterface retrofitInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_demand);
        retrofitInterface = APIUtils.getuserInterface();
        Call<List<User>> call = retrofitInterface.getPendingAccount("w");
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    List<User> listUser = response.body();
                    final ListView listView = (ListView) findViewById(R.id.demandListView);
                    listView.setAdapter(new CustomDemandListAdapter(ListDemandActivity.this, listUser));
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }
}