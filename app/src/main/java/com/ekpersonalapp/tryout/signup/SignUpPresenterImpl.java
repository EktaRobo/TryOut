package com.ekpersonalapp.tryout.signup;

import android.content.Context;
import android.text.TextUtils;

import com.ekpersonalapp.tryout.R;
import com.ekpersonalapp.tryout.utilities.EmailUtils;

/**
 * Created by ekta on 24/2/16.
 */
public class SignUpPresenterImpl implements SignUpPresenter {
    Context mContext;
    SignUpView mView;
    SignUpInteractor mInteractor;

    public SignUpPresenterImpl(Context context, SignUpView signUpView) {
        mView = signUpView;
        mContext = context;
        mInteractor = new SignUpInteractorImpl();
    }

    @Override
    public void validateEmailAndPassword(String email, String password, String repeatPassword) {
        if (mView == null || mInteractor == null)
            return;
        String emailAddress = email.trim();
        if (TextUtils.isEmpty(emailAddress)) {
            mView.showEmailErrorMessage(mContext.getString(R.string.empty_email_error_message));
        } else if (!EmailUtils.isEmailValid(emailAddress)) {
            mView.showEmailErrorMessage(mContext.getString(R.string.invalid_email_error_message));
        } else if (TextUtils.isEmpty(password)) {
            mView.showPasswordErrorMessage(mContext.getString(R.string
                    .empty_password_error_message));
        } else if (password.trim().length() < 6) {
            mView.showPasswordErrorMessage(mContext.getString(R.string
                    .short_password_error_message));
        } else {
            mView.showProgress();
            if (mInteractor.isRegisteredUser(email)) {

            } else {
                mView.hideProgress();
                mView.showEmailErrorMessage(mContext.getString(R.string
                        .unregistered_user_error_message));
            }
        }

    }
}