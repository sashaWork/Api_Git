//package edu.uoc.android.restservice.core.model;
//
//import android.support.annotation.VisibleForTesting;
//import android.view.View;
//import android.widget.EditText;
//
//public class LoginViewModel extends ViewModel {
//    private LoginForm login;
//    private View.OnFocusChangeListener onFocusEmail;
//    private View.OnFocusChangeListener onFocusPassword;
//
//    @VisibleForTesting
//    public void init() {
//        login = new LoginForm();
//        onFocusEmail =  new View.OnFocusChangeListener() {
//
//            @Override
//            public void onFocusChange(View view, boolean focused) {
//                EditText et = (EditText) view;
//                if (et.getText().length() > 0 && !focused) {
//                    login.isEmailValid(true);
//                }
//            }
//        };
//
//        onFocusPassword = new View.OnFocusChangeListener() {
//
//            @Override
//            public void onFocusChange(View view, boolean focused) {
//                EditText et = (EditText) view;
//                if (et.getText().length() > 0 && !focused) {
//                    login.isPasswordValid(true);
//                }
//            }
//        };
//    }
//
//    public LoginForm getLogin() {
//        return login;
//    }
//
//    public View.OnFocusChangeListener getEmailOnFocusChangeListener() {
//        return onFocusEmail;
//    }
//
//    public View.OnFocusChangeListener getPasswordOnFocusChangeListener() {
//        return onFocusPassword;
//    }
//
//    public void onButtonClick() {
//        login.onClick();
//    }
//
//    public MutableLiveData<LoginFields> getLoginFields() {
//        return login.getLoginFields();
//    }
//
//    public LoginForm getForm() {
//        return login;
//    }
//
//    @BindingAdapter("error")
//    public static void setError(EditText editText, Object strOrResId) {
//        if (strOrResId instanceof Integer) {
//            editText.setError(
//                    editText.getContext().getString((Integer) strOrResId));
//        } else {
//            editText.setError((String) strOrResId);
//        }
//    }
//
//    @BindingAdapter("onFocus")
//    public static void bindFocusChange(EditText editText, View.OnFocusChangeListener onFocusChangeListener) {
//        if (editText.getOnFocusChangeListener() == null) {
//            editText.setOnFocusChangeListener(onFocusChangeListener);
//        }
//    }
//}
