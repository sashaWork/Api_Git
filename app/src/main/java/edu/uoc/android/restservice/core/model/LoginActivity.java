//package edu.uoc.android.restservice.core.model;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.widget.Toast;
//
//
//public class LoginActivity extends AppCompatActivity {
//    private LoginViewModel viewModel;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setupBindings(savedInstanceState);
//    }
//
//    private void setupBindings(Bundle savedInstanceState) {
//        ActivityLoginBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
//        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
//        if (savedInstanceState == null) {
//            viewModel.init();
//        }
//        activityBinding.setModel(viewModel);
//        setupButtonClick();
//    }
//
//    private void setupButtonClick() {
//        viewModel.getLoginFields().observe(this, new Observer<LoginFields>() {
//            @Override
//            public void onChanged(LoginFields loginModel) {
//                Toast.makeText(LoginActivity.this,
//                        "Email " + loginModel.getEmail() + ", Password " + loginModel.getPassword(),
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
