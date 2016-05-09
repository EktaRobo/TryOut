package com.ekpersonalapp.tryout.signup;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ekpersonalapp.tryout.R;
import com.ekpersonalapp.tryout.signin.SignInActivity;
import com.ekpersonalapp.tryout.utilities.LoaderDialogUtil;

public class SignUpActivity extends AppCompatActivity implements SignUpView, View.OnClickListener {

    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private SignUpPresenter mPresenter;
    private boolean mIsPasswordVisible;
    private EditText mUserNameEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initUI();
        initClickListeners();
        mPresenter = new SignUpPresenterImpl(this, this);
    }

    private void initUI() {
        mUserNameEditText = (EditText) findViewById(R.id.user_name_edit_text);
        mEmailEditText = (EditText) findViewById(R.id.account_id_edit_text);
        mPasswordEditText = (EditText) findViewById(R.id.password_edit_text);
    }

    private void initClickListeners() {
        findViewById(R.id.sign_up_button).setOnClickListener(this);
        findViewById(R.id.show_or_hide_password).setOnClickListener(this);
    }

    @Override
    public void showProgress() {
        LoaderDialogUtil.getInstance().showLoader(this);
    }

    @Override
    public void hideProgress() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                LoaderDialogUtil.getInstance().dismissLoader(SignUpActivity.this);
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
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.sign_up_button):
                fetchEditTextValues();
                break;
            case (R.id.show_or_hide_password):
                if (mPresenter == null)
                    return;
                mPresenter.showHidePassword(mIsPasswordVisible);
                break;
        }
    }

    private void fetchEditTextValues() {
        if (mPresenter == null)
            return;
        String userName = mUserNameEditText.getText().toString();
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        mPresenter.validateEmailAndPassword(userName, email, password);
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
    public void navigateToSignInScreen() {
        Intent signInIntent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(signInIntent);
        finish();
    }
}
