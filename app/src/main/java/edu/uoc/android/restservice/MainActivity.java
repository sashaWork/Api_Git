package edu.uoc.android.restservice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


import edu.uoc.android.restservice.rest.model.Autorization;
//import edu.uoc.android.restservice.test.LoginResponse;
import edu.uoc.android.restservice.test.LoginService;
import edu.uoc.android.restservice.test.ServiceGenerator;
import edu.uoc.android.restservice.test.User;
import edu.uoc.android.restservice.ui.enter.EnterUserActivity;
import edu.uoc.android.restservice.ui.followers.UserFollowersActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static edu.uoc.android.restservice.rest.contants.ApiConstants.EMAIL;
import static edu.uoc.android.restservice.rest.contants.ApiConstants.PASS;


public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    private EditText etEmail, etPass;

    String result = null;
//    private static final String API_URL = "https://api.github.com/user";
    String response;
    private Call<Autorization> callOwner;
    String username = "oshturniev@griddynamics.com", password = "Sasha502633!";
    String encode = Base64.encodeToString((username + ":" + password).getBytes(),
            Base64.DEFAULT).replace("\n", "");

    CheckBox cbSave;
    Button btnIn;
    String email, pass;

    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.d(LOG_TAG, "onCreate: " + EMAIL + " " + PASS);
        init();

//        JsonReader.setLenient(true);

//        LoginService loginService =
//                ServiceGenerator.createService(LoginService.class, username, password);
//        Call<User> call = loginService.basicLogin();
//            Log.d(LOG_TAG, "loginService" + " " );
//        call.enqueue(new Callback<User>() {
//                         @Override
//                         public void onResponse(Call<User> call, Response<User> response) {
//                             if (response.isSuccessful()) {
//                                 // user object available
//                                 Log.d(LOG_TAG, "isSuccessful: " + EMAIL + " " + PASS);
//                                 Toast.makeText(MainActivity.this, User.getResource_id(), Toast.LENGTH_LONG).show();
//                             } else {
//                                 // error response, no access to resource?
//                                 Log.d(LOG_TAG, "error" + " " + response);
//                             }
//                         }
//
//                         @Override
//                         public void onFailure(Call<User> call, Throwable t) {
//                             // something went completely south (like no internet connection)
//                             Log.d(LOG_TAG, "T: " + t.getMessage());
//                         }
//                     });

        Log.d(LOG_TAG, "onCreate: " + EMAIL + " " + PASS);
//        Log.d(LOG_TAG, "MainActivity :  " + result);
//        Log.d(LOG_TAG, "MainActivity 1 :  " + result);

//        Request request = new Request.Builder()
//                .header("Authorization", "Basic " + encode).url("https://api.github.com/authorizations").build();

    }

    void init() {
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        cbSave = (CheckBox) findViewById(R.id.cbSave);
        btnIn = (Button) findViewById(R.id.btnIn);

        cbSave.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "Save", Toast.LENGTH_SHORT).show();
//                    saveText();
                } else {
                    Toast.makeText(MainActivity.this, "Not save", Toast.LENGTH_SHORT).show();
//                    loadText();
                }
            }
        });

        email = etEmail.getText().toString();
        pass = etPass.getText().toString();

//       final boolean findEmail = isValidEmail(email);

        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    if (findEmail) {
                Intent intent = new Intent(MainActivity.this, UserFollowersActivity.class);
                startActivity(intent);
//                    } else {
                btnIn.setClickable(false); // setEnabled
                Toast.makeText(MainActivity.this, "Need more latters in a field", Toast.LENGTH_SHORT).show();
//                    }
            }
        });
    }


//    public final static boolean isValidEmail(CharSequence email) {
//        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
//    }

//    void saveText() {
//        sPref = getDefaultSharedPreferences(getApplicationContext());
//        SharedPreferences.Editor ed = sPref.edit();
//        ed.putString(EMAIL, email);
//        ed.putString(PASS, pass);
//        ed.commit();
//
//        Log.d(LOG_TAG, "savaText: " + EMAIL + " " + PASS);
//    }
//
//    void loadText() {
//        sPref = getDefaultSharedPreferences(getApplicationContext());
//        email = sPref.getString(EMAIL, "");
//        pass = sPref.getString(PASS, "");
//        Log.d(LOG_TAG, "savaText: " + email + " " + pass + EMAIL + " " + PASS);
//    }
}
