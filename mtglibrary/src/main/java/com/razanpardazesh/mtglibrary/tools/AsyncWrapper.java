package com.razanpardazesh.mtglibrary.tools;

import android.content.Context;
import android.os.AsyncTask;

import com.razanpardazesh.mtglibrary.CustomView.ProgressDialogBuilder;

/**
 * Created by Torabi on 9/13/2016.
 */

public class AsyncWrapper {

    private Object answer;

    private ProgressDialogBuilder progressDialog;

    private Callback onBackground = null;
    private Callback onAnswer = null;
    private Callback onError = null;
    private Callback onCancel = null;
    private InnerAsyncTask innerAsyncTask = null;
    private Boolean isCanceled = false;

    private class InnerAsyncTask extends AsyncTask<Object, Integer, Throwable> {

        @Override
        protected Throwable doInBackground(Object... params) {
            try {
                if (onBackground == null || isCancelled() || isCanceled)
                    return null;

                answer = onBackground.call(params);

            } catch (Throwable e) {
                return e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Throwable throwable) {

            super.onPostExecute(throwable);
            innerAsyncTask = null;

            if (isCanceled || isCancelled())
                return;

            if (throwable != null && onError != null) {
                onError.call(throwable);
                return;
            }

            if (onAnswer != null)
                onAnswer.call(answer);
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (progressDialog != null)
                progressDialog.showInfinit();
        }

        @Override
        protected void onCancelled(Throwable throwable) {
            super.onCancelled(throwable);
            innerAsyncTask = null;

            if (onCancel != null)
                onCancel.call(throwable);
        }
    }

    public interface Callback {
        public Object call(Object object);
    }

    public void run(Context context, Object... params)
    {
        if (innerAsyncTask != null)
            return;

        innerAsyncTask = new InnerAsyncTask();
        innerAsyncTask.execute(params);
        return;
    }

    public AsyncWrapper initDefaultProgressDialog(String title,Boolean isCancelable)
    {
        //TODO MTG
        progressDialog = new ProgressDialogBuilder();
        return this;
    }

    public void setProgressDialog(ProgressDialogBuilder progressDialog) {
        this.progressDialog = progressDialog;
    }

    public ProgressDialogBuilder getProgressDialog() {
        return progressDialog;
    }

    public void cancel()
    {
        this.isCanceled = true;
        if (innerAsyncTask != null)
            innerAsyncTask.cancel(true);
    }

    public AsyncWrapper setDoOnBackground(Callback onBackground) {
        this.onBackground = onBackground;
        return this;
    }

    public AsyncWrapper setDoOnAnswer(Callback onAnswer) {
        this.onAnswer = onAnswer;
        return this;
    }

    public AsyncWrapper setDoOnError(Callback onError) {
        this.onError = onError;
        return this;
    }

    public AsyncWrapper setDoOnCancel(Callback onCancel) {
        this.onCancel = onCancel;
        return this;
    }

}
