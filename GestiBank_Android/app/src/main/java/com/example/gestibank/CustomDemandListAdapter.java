package com.example.gestibank;

import android.content.Context;
import android.os.Build;
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

public class CustomDemandListAdapter extends BaseAdapter {
    private List<User> listUser;
    private List<User> listAgent;
    private LayoutInflater layoutInflater;
    private Context context;
    private RetrofitInterface retrofitInterface;

    public CustomDemandListAdapter(Context context, List<User> listUser){
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
            convertView = layoutInflater.inflate(R.layout.custom_demand_listview, null);
            holder = new ViewHolder();
            holder.demandName = (TextView) convertView.findViewById(R.id.demandName);
            holder.demandMail = (TextView) convertView.findViewById(R.id.demandMail);
            holder.agentSpinner  = (Spinner) convertView.findViewById(R.id.agentSpinner);
            holder.btnAffectTo = (Button) convertView.findViewById(R.id.btnAffect);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        User u = this.listUser.get(position);
        holder.demandName.setText(u.getName() + " " + u.getFirstname());
        holder.demandMail.setText(u.getMail());
        retrofitInterface = APIUtils.getuserInterface();
        listAgent = new ArrayList<>();

        Call<List<User>> call = retrofitInterface.getAgents("AGENT");
        call.enqueue(new Callback<List<User>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    listAgent = response.body();
                    List<String>agents = new ArrayList<>();
                    listAgent.forEach(agent -> agents.add(agent.getName()));
                    ArrayAdapter adapter = new ArrayAdapter(context,
                            android.R.layout.simple_spinner_item, agents);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    holder.agentSpinner.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

        holder.btnAffectTo.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String agent  = holder.agentSpinner.getSelectedItem().toString();
                String client = holder.demandMail.getText().toString();
                User a = new User();
                a.setAgent(agent);
                Call<User> call2 = retrofitInterface.affectUser(client, a);
                call2.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(context, "Client affecté", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });

            }
        });

        return convertView;
    }

    static class ViewHolder{
        TextView demandName;
        TextView demandMail;
        Spinner agentSpinner;
        Button btnAffectTo;
    }
}
