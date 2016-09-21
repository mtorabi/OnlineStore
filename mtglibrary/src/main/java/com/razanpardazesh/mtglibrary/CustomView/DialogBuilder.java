package com.razanpardazesh.mtglibrary.CustomView;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.razanpardazesh.mtglibrary.R;

/**
 * Created by Torabi on 9/17/2016.
 */

public class DialogBuilder {

    public AlertDialog showYesNOAlert(Context context, String title, String question, DialogInterface.OnClickListener yesAction, DialogInterface.OnClickListener noAction) {
        if (TextUtils.isEmpty(title))
            title = context.getString(R.string.attention);

        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(question)
                .setPositiveButton(context.getString(R.string.yes), yesAction)
                .setNegativeButton(context.getString(R.string.no), noAction)
                .show();
    }
}
