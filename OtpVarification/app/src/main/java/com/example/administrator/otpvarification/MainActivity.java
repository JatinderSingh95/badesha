package com.example.administrator.otpvarification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private RequestQueue requestQueue;
    EditText editText;
    TextView textView;
    public static final String REGISTER_URL = "http://bestwaycabs.com/sms_page.php";
    public static final String KEY_PHONE = "customer_mobile";
    private String phone;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edt_num);
        button = (Button) findViewById(R.id.btnsub);
        textView=(TextView)findViewById(R.id.txt);
        requestQueue = Volley.newRequestQueue(this);
        button.setOnClickListener(this);
    }

    private  void submit() {
        phone = editText.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("qwerty" + response);
                            textView.setText(response);
//                            if (response==response){
//                                startActivity(new Intent(MainActivity.this, Succes.class));
//                            }
//                            else {
//                                Toast.makeText(MainActivity.this, "otp is not macthed", Toast.LENGTH_LONG).show();
//                            }
                        } catch (Exception e) {

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                
                params.put(KEY_PHONE, phone);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    @Override
    public void onClick(View view) {
submit();
    }
//    @Override
//    public void onResume() {
//        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(receiver, new IntentFilter("AddedItem"));
//        super.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        super.onDestroy();
//        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(receiver);
//    }
//    private BroadcastReceiver receiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//           // if (intent.getAction()) {
//                final String message = intent.getStringExtra("message");
//            System.out.println("qwertyu"+message);
//                   textView.setText(message);
//                //Do whatever you want with the code here
//           // }
//        }
//    };
}
