package edu.uoc.android.restservice.core.model;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.uoc.android.restservice.R;
import edu.uoc.android.restservice.repo.GitActivity;


public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    private EditText etEmail, etPass;

    public String username = "oshturniev@griddynamics.com";
    public String password = "Sasha33!";
//    String encode = Base64.encodeToString((username + ":" + password).getBytes(),
//            Base64.DEFAULT).replace("\n", "");

    Button btnIn;

    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    void init() {
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        btnIn = (Button) findViewById(R.id.btnIn);

        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etEmail.getText().toString();
                password = etPass.getText().toString();

                Intent intent = new Intent(MainActivity.this, GitActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Need more latters in a field", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
