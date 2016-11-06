package com.razanpardazesh.mtglibrary.tools;

import android.content.Context;
import android.os.AsyncTask;

import com.razanpardazesh.mtglibrary.CustomView.ProgressDialog;

/**
 * Created by Torabi on 9/13/2016.
 */

public class AsyncWrapper {

    private Object answer;

    private ProgressDialog progressDialog;

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
                e.printStackTrace();
                return e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Throwable throwable) {

            super.onPostExecute(throwable);
            innerAsyncTask = null;

            if (progressDialog != null)
                progressDialog.dismiss();


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

    public AsyncWrapper initDefaultProgressDialog(Context context, String title,Boolean isCancelable)
    {
        progressDialog = new ProgressDialog(context);
        return this;
    }

    public void setProgressDialog(ProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
    }

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    public void cancel()
    {
        this.isCanceled = true;
        if (innerAsyncTask != null)
            innerAsyncTask.cancel(true);
    }

    public AsyncWrapper setDoOnBackground(Callback onBackground) {
        if (innerAsyncTask != null)
            return this;

        this.onBackground = onBackground;
        return this;
    }

    public AsyncWrapper setDoOnAnswer(Callback onAnswer) {

        if (innerAsyncTask != null)
            return this;

        this.onAnswer = onAnswer;
        return this;
    }

    public AsyncWrapper setDoOnError(Callback onError) {

        if (innerAsyncTask != null)
            return this;

        this.onError = onError;
        return this;
    }

    public AsyncWrapper setDoOnCancel(Callback onCancel) {

        if (innerAsyncTask != null)
            return this;

        this.onCancel = onCancel;
        return this;
    }

    protected void riseError(Throwable throwable)
    {
        cancel();

        if (onError == null)
            return;

        onError.call(throwable);
    }

}
