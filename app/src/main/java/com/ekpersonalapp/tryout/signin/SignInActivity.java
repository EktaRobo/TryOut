package com.ekpersonalapp.tryout.signin;

/**
 * Created by ekta on 17/2/16.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ekpersonalapp.tryout.R;
import com.ekpersonalapp.tryout.homescreen.HomeScreenActivity;
import com.ekpersonalapp.tryout.signup.SignUpActivity;
import com.ekpersonalapp.tryout.utilities.LoaderDialogUtil;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener, SignInView {
    private SignInPresenter mPresenter;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private boolean mIsPasswordVisible;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        intUi();
        initClickListeners();
        mPresenter = new SignInPresenterImpl(this);

    }

    private void intUi() {
        mEmailEditText = (EditText) findViewById(R.id.account_id_edit_text);
        mPasswordEditText = (EditText) findViewById(R.id.password_edit_text);

    }

    private void initClickListeners() {
        findViewById(R.id.not_registered_text_view).setOnClickListener(this);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.show_or_hide_password).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.not_registered_text_view):
                navigateToSignUpScreen();
                break;
            case (R.id.sign_in_button):
                fetchEmailAndPassword();
                break;
            case (R.id.show_or_hide_password):
                if (mPresenter == null)
                    return;
                mPresenter.showHidePassword(mIsPasswordVisible);
                break;
        }
    }

    private void navigateToSignUpScreen() {
        Intent signUpIntent = new Intent(this, SignUpActivity.class);
        startActivity(signUpIntent);
    }

    private void fetchEmailAndPassword() {
        if (mPresenter == null)
            return;
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        mPresenter.validateEmailAndPassword(email, password, this);
    }

    @Override
    public void showProgress() {
        LoaderDialogUtil.getInstance().showLoader(this);
    }

    @Override
    public void hideProgress() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                LoaderDialogUtil.getInstance().dismissLoader(SignInActivity.this);
            }
        }, 100L);

    }

    @Override
    public void showEmailErrorMessage(String errorMessage) {
        mEmailEditText.setError(errorMessage);
    }

    @Override
    public void showPasswordErrorMessage(String errorMessage) {
        mPasswordEditText.setError(errorMessage);
    }

    @Override
    public void showPassword() {
        mPasswordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        ((TextView) findViewById(R.id.show_or_hide_password)).setText(R.string.hide_text_view);
        mPasswordEditText.setSelection(mPasswordEditText.getText().length());
        mIsPasswordVisible = true;
    }

    @Override
    public void hidePassword() {
        mPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType
                .TYPE_TEXT_VARIATION_PASSWORD);
        ((TextView) findViewById(R.id.show_or_hide_password)).setText(R.string.show_text_view);
        mPasswordEditText.setSelection(mPasswordEditText.getText().length());
        mIsPasswordVisible = false;
    }

    @Override
    public void navigateToHomeScreen() {
        Intent homeScreenIntent = new Intent(SignInActivity.this, HomeScreenActivity.class);
        startActivity(homeScreenIntent);
        finish();
    }

}
