package com.ekpersonalapp.tryout.signup;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.ekpersonalapp.tryout.R;
import com.ekpersonalapp.tryout.utilities.LoaderDialogUtil;

public class SignUpActivity extends AppCompatActivity implements SignUpView, View.OnClickListener {

    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private SignUpPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findViewById(R.id.sign_up_button).setOnClickListener(this);
        mPresenter = new SignUpPresenterImpl(this, this);
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
    public void showRepeatPasswordErrorMessage(String errorMessage) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.sign_up_button):
                if (mPresenter == null)
                    return;
                mEmailEditText = (EditText) findViewById(R.id.account_id_edit_text);
                String email = mEmailEditText.getText().toString();
                mPasswordEditText = (EditText) findViewById(R.id.password_edit_text);
                String password = mPasswordEditText.getText().toString();
                mPasswordEditText = (EditText) findViewById(R.id.repeat_password_edit_text);
                String repeatPassword = mPasswordEditText.getText().toString();
                mPresenter.validateEmailAndPassword(email, password, repeatPassword);
        }
    }
}
