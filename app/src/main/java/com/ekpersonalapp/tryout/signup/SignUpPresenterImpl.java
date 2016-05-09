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
        mInteractor = new SignUpInteractorImpl(context);
    }

    @Override
    public void validateEmailAndPassword(String userName, String accountId, String password) {
        if (mView == null || mInteractor == null)
            return;
        String emailAddress = accountId.trim();
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
            if (mInteractor.isRegisteredUser(userName)) {
                mView.hideProgress();
                mView.showEmailErrorMessage(mContext.getString(R.string
                        .unregistered_user_error_message));
            } else {
                mInteractor.addUserToDatabase(userName, accountId, password);
                mView.hideProgress();
                mView.navigateToSignInScreen();
            }
        }

    }

    @Override
    public void showHidePassword(boolean isPasswordVisible) {
        if (isPasswordVisible) {
            mView.hidePassword();
        } else {
            mView.showPassword();
        }
    }
}