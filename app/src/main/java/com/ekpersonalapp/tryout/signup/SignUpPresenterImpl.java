package com.ekpersonalapp.tryout.signup;

import android.content.Context;
import android.text.TextUtils;

import com.ekpersonalapp.tryout.R;
import com.ekpersonalapp.tryout.utilities.EmailUtils;

/**
 * Created by ekta on 24/2/16.
 */
public class SignUpPresenterImpl implements SignUpPresenter {
    SignUpView mView;
    SignUpInteractor mInteractor;

    public SignUpPresenterImpl(SignUpView signUpView) {
        mView = signUpView;
        mInteractor = new SignUpInteractorImpl();
    }

    @Override
    public void validateEmailAndPassword(String userName, String accountId, String password,
                                         Context context) {
        if (mView == null || mInteractor == null)
            return;
        String emailAddress = accountId.trim();
        if (TextUtils.isEmpty(emailAddress)) {
            mView.showEmailErrorMessage(context.getString(R.string.empty_email_error_message));
        } else if (!EmailUtils.isEmailValid(emailAddress)) {
            mView.showEmailErrorMessage(context.getString(R.string.invalid_email_error_message));
        } else if (TextUtils.isEmpty(password)) {
            mView.showPasswordErrorMessage(context.getString(R.string
                    .empty_password_error_message));
        } else if (password.trim().length() < 6) {
            mView.showPasswordErrorMessage(context.getString(R.string
                    .short_password_error_message));
        } else {
            mView.showProgress();
            if (!mInteractor.isRegisteredUser(emailAddress, context)) {
                mView.hideProgress();
                mView.showEmailErrorMessage(context.getString(R.string
                        .registered_user_error_message));
            } else {
                mInteractor.addUserToDatabase(userName, accountId, password, context);
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