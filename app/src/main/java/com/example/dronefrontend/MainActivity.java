package com.example.dronefrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button sendBtn;
    TextView text;
    String json_url = "http://10.0.2.2:8000/api/v1/income_classifier/predict";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBtn = (Button)findViewById(R.id.sendBtn);
        text = (TextView)findViewById(R.id.text);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest postRequest = new StringRequest(Request.Method.POST, json_url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                                Log.d("Response", response);
                                text.setText(response);
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Log.d("Error.Response", error.getMessage());
                                text.setText(error.getMessage());
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("age", "37");
                        params.put("workclass", "Private");
                        params.put("fnlwgt", "34146");
                        params.put("education", "HS-grad");
                        params.put("education-num", "9");
                        params.put("marital-status", "Married-civ-spouse");
                        params.put("occupation", "Craft-repair");
                        params.put("relationship", "Husband");
                        params.put("race", "White");
                        params.put("sex", "Male");
                        params.put("capital-gain", "0");
                        params.put("capital-loss", "0");
                        params.put("hours-per-week", "68");
                        params.put("native-country", "United-States");

                        return params;
                    }
                };
                MySingleton.getInstance(MainActivity.this).addToRequestQueue(postRequest);
            }
        });

    }
}