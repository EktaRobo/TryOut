package com.ekpersonalapp.tryout.utilities;

/**
 * Created by ekta on 19/2/16.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.ekpersonalapp.tryout.fragments.LoaderDialogFragment;


/**
 * Utility class for showing/dismissing loader.
 */
public class LoaderDialogUtil {

    static LoaderDialogUtil sDialogUtil;

    private LoaderDialogUtil() {

    }


    public static LoaderDialogUtil getInstance() {

        if (sDialogUtil == null)
            sDialogUtil = new LoaderDialogUtil();

        return sDialogUtil;
    }


    public void showLoader(FragmentActivity activity) {
        if (activity == null)
            return;

        try {
            if (findFragmentByTag(activity.getSupportFragmentManager(), Constants
                    .LOADER_FRAGMNET) == null) {
                LoaderDialogFragment dialogFragment = new LoaderDialogFragment();
                dialogFragment.setCancelable(false);
                dialogFragment.show(activity.getSupportFragmentManager(), Constants
                        .LOADER_FRAGMNET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dismissLoader(FragmentActivity activity) {
        if (activity == null)
            return;
        try {
            if (findFragmentByTag(activity.getSupportFragmentManager(), Constants
                    .LOADER_FRAGMNET) != null) {
                LoaderDialogFragment dialogFragment = (LoaderDialogFragment) findFragmentByTag
                        (activity.getSupportFragmentManager(), Constants
                                .LOADER_FRAGMNET);
                dialogFragment.dismissAllowingStateLoss();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Fragment findFragmentByTag(FragmentManager fragmentManager, String tag) {
        if (fragmentManager != null) {
            return fragmentManager.findFragmentByTag(tag);
        }

        return null;
    }

}