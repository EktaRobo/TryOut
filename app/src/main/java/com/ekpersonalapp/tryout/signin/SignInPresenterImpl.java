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
    Context mContext;
    SignInInteractor mInteractor;

    public SignInPresenterImpl(SignInView signInView, Context context) {
        mView = signInView;
        mContext = context;
        mInteractor = new SignInInteractorImpl(context);
    }

    @Override
    public void validateEmailAndPassword(String email, String password) {
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
            if(mInteractor.isRegisteredUser(email)) {
                mView.navigateToHomeScreen();

            } else {
                mView.hideProgress();
                mView.showEmailErrorMessage(mContext.getString(R.string
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
