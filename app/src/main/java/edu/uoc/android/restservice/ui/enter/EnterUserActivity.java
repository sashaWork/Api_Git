package edu.uoc.android.restservice.ui.enter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import edu.uoc.android.restservice.R;
import edu.uoc.android.restservice.ui.followers.UserFollowersActivity;

public class EnterUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUser;
    private Button btnFollowers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_user);
        initViews();
    }

    private void initViews() {
        etUser = (EditText) findViewById(R.id.enter_user_edit_text);
        btnFollowers = (Button) findViewById(R.id.enter_user_button);
        btnFollowers.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnFollowers) {
            startActivity(UserFollowersActivity.makeIntent(this, etUser.getText().toString()));
        }
    }
}
