package com.ekpersonalapp.tryout.signin;

import android.content.Context;
import android.text.TextUtils;

import com.ekpersonalapp.tryout.R;
import com.ekpersonalapp.tryout.utilities.EmailUtils;

/**
 * Created by ekta on 17/2/16.
 */
public class SignInPresenterImpl implements SignInPresenter{
    SignInView mView;
    SignInInteractor mInteractor;

    public SignInPresenterImpl(SignInView signInView) {
        mView = signInView;
        mInteractor = new SignInInteractorImpl();
    }

    @Override
    public void validateEmailAndPassword(String email, String password, Context context) {
        if (mView == null || mInteractor == null)
            return;
        String emailAddress = email.trim();
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
            if (mInteractor.isRegisteredUser(email, context)) {
                mView.navigateToHomeScreen();

            } else {
                mView.hideProgress();
                mView.showEmailErrorMessage(context.getString(R.string
                                        .unregistered_user_error_message));
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
