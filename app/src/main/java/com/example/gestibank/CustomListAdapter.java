package com.example.gestibank;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gestibank.model.User;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private List<User> listUser;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter(Context context, List<User>listUser) {
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
            convertView = layoutInflater.inflate(R.layout.custom_listview, null);
            holder = new ViewHolder();
            holder.agentName = (TextView) convertView.findViewById(R.id.textAgentName);
            holder.agentFirstname = (TextView) convertView.findViewById(R.id.textAgentFirstname);
            holder.agentPhone = (TextView) convertView.findViewById(R.id.textAgentPhone);
            holder.agentMail = (TextView) convertView.findViewById(R.id.textAgentMail);
            holder.agentMatr = (TextView) convertView.findViewById(R.id.editTextAgentMatr);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        User u = this.listUser.get(position);
        holder.agentName.setText(u.getName());
        holder.agentFirstname.setText(u.getFirstname());
        holder.agentMail.setText(u.getMail());
        holder.agentPhone.setText(u.getPhone());
        holder.agentMatr.setText(u.getMatricule());
        return convertView;
    }
    static class ViewHolder{
        TextView agentName;
        TextView agentFirstname;
        TextView agentPhone;
        TextView agentMail;
        TextView agentMatr;
    }
}
