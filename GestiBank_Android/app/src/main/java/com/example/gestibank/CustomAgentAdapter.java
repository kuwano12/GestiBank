package com.example.gestibank;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.gestibank.model.User;
import com.example.gestibank.remote.APIUtils;
import com.example.gestibank.remote.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomAgentAdapter extends BaseAdapter {
    private List<User> listUser;
    private LayoutInflater layoutInflater;
    private Context context;
    List<String>statusList = new ArrayList<>();
    private RetrofitInterface retrofitInterface;

    public CustomAgentAdapter(Context context, List<User> listUser){
        this.context = context;
        this.listUser = listUser;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listUser.size();
    }

    @Override
    public Object getItem(int position) {
        return listUser.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.custom_agent_listview, null);
            holder = new ViewHolder();
            holder.pendingN = (TextView) convertView.findViewById(R.id.pendingName);
            holder.pendingM = (TextView) convertView.findViewById(R.id.pendingMail);
            holder.pendingStatus = (Spinner) convertView.findViewById(R.id.pendingStatus);
            holder.btnValidPending = (Button) convertView.findViewById(R.id.validatePending);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        retrofitInterface = APIUtils.getuserInterface();
        User u = this.listUser.get(position);
        holder.pendingN.setText(u.getName());
        holder.pendingM.setText(u.getMail());
        statusList.add("w");
        statusList.add("t");
        statusList.add("f");
        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, statusList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.pendingStatus.setAdapter(adapter);
        holder.btnValidPending.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String status = holder.pendingStatus.getSelectedItem().toString();
                String client = holder.pendingM.getText().toString();
                String pass = Utils.generatePassword();
                User u = new User();
                u.setActivated(status);
                u.setPassword(pass);
                Call<User>call = retrofitInterface.changeClientStatus(client, u);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(context, "Statut client modifié", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e("Error", t.getMessage());
                    }
                });
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView pendingN;
        TextView pendingM;
        Spinner pendingStatus;
        Button btnValidPending;
    }
}
