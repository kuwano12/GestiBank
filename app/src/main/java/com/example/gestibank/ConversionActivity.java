package com.example.gestibank;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gestibank.model.Devise;
import com.example.gestibank.remote.APIUtils;
import com.example.gestibank.remote.RetrofitInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConversionActivity extends AppCompatActivity {
    private RetrofitInterface retrofitInterface;
    private Spinner spinner;
    private Button btnConverter;
    private TextView result;
    private EditText value;
    private AutoCompleteTextView textView;
    Devise list = new Devise();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);
//        spinner = (Spinner) findViewById(R.id.spinner);
        btnConverter = (Button) findViewById(R.id.btnConvertTo);
        value = (EditText) findViewById(R.id.editValue);
        result = (TextView) findViewById(R.id.editResult);
        retrofitInterface = APIUtils.getDeviceInterface();


        Call<Devise> call = retrofitInterface.getDevices();
        call.enqueue(new Callback<Devise>() {
            @Override
            public void onResponse(Call<Devise> call, Response<Devise> response) {
                if(response.isSuccessful()){
                    list = response.body();
//                    ArrayAdapter adapter = new ArrayAdapter(ConversionActivity.this,
//                            android.R.layout.simple_list_item_1, list.getRates().keySet().toArray());
//                    spinner.setAdapter(adapter);
                    ArrayAdapter adapter2 = new ArrayAdapter(ConversionActivity.this,
                            android.R.layout.simple_dropdown_item_1line,
                            list.getRates().keySet().toArray());
                    textView = (AutoCompleteTextView) findViewById(R.id.listDevise);
                    textView.setThreshold(0);

                    textView.setAdapter(adapter2);
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            textView.showDropDown();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<Devise> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void getConvertedValue(View v){
        if(v == btnConverter){
            String rateKey = textView.getText().toString();
            Double rateValue = list.getRates().get(rateKey);
            Double aValue = null;
            if(value.getText().toString().trim().length() == 0) {
                Toast.makeText(getApplicationContext(), "Veuillez renseigner une valeur", Toast.LENGTH_LONG).show();
                return;
            }
            aValue = Double.valueOf(value.getText().toString());

            Double converted = aValue * rateValue;

            result.setText(converted.toString());
        }
    }

    public static void showMessage(String title, String message, Context context) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}