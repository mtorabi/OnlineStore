package com.razanpardazesh.mtglibrary.tools;

import android.content.Context;
import android.text.TextUtils;

import com.razanpardazesh.mtglibrary.R;
import com.razanpardazesh.mtglibrary.network.NetworkTools;
import com.razanpardazesh.mtglibrary.network.ServerConnectionException;

/**
 * Created by Torabi on 9/13/2016.
 */

public class NetworkAsyncWrapper extends AsyncWrapper {

    private String connectionErrorMessage = null;

    @Override
    public void run(Context context, Object... params) {
        if (!NetworkTools.isNetworkAvailable(context))
        {
            riseError(new ServerConnectionException(getConnectionErrorMessage(context)));
        }
        super.run(context, params);
    }

    public String getConnectionErrorMessage(Context context) {
        if (TextUtils.isEmpty(connectionErrorMessage))
            connectionErrorMessage = context.getString(R.string.connecting_to_server_error);
        return connectionErrorMessage;
    }

    public void setConnectionErrorMessage(String connectionErrorMessage) {
        this.connectionErrorMessage = connectionErrorMessage;
    }
}
