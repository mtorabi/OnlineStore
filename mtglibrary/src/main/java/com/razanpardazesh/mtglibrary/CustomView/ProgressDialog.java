package com.razanpardazesh.mtglibrary.CustomView;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.razanpardazesh.mtglibrary.R;

/**
 * Created by Torabi on 10/13/2016.
 */

public class ProgressDialog {

    Context context;
    String title = "";
    String message = "";
    Boolean isCanclable = false;
    Boolean isIndeterminate = false;
    android.app.ProgressDialog dialog;
    DialogInterface.OnCancelListener cancelListener;

    public ProgressDialog(Context context) {
        this.context = context;
        title = context.getString(R.string.wait);
        message = context.getString(R.string.connecting_to_server);
    }

    public void showInfinit() {
        isIndeterminate = true;
        show();
    }

    public void show() {
        dialog = android.app.ProgressDialog.show(context, title, message, isIndeterminate, isCanclable, cancelListener);
    }

    public String getTitle() {
        return title;
    }

    public ProgressDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public Boolean getCanclable() {
        return isCanclable;
    }

    public ProgressDialog setCanclable(Boolean canclable) {
        isCanclable = canclable;
        return this;
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public static ProgressDialog builder(Context context) {
        return new ProgressDialog(context);
    }
}
