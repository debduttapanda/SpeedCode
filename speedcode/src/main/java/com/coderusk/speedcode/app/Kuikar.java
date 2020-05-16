package com.coderusk.speedcode.app;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Kuikar {
    private String result = "";
    private String url = "";
    private String query = "";
    private int code = -1;
    private StringBuilder sb = null;
    ArrayList<String> queries;
    private long startTime = 0;
    private long endTime = 0;
    private OnBadCodeListener onBadCodeListener = null;
    private OnEndListener onEndListener = null;
    private OnStartListener onStartListener = null;
    private OnErrorListener onErrorListener = null;

    private Kuikar() {
        sb = new StringBuilder();
        queries = new ArrayList<>();
    }

    /////////////////////////////////
    public static Kuikar create() {
        return new Kuikar();
    }

    private String getQuery() {
        query = android.text.TextUtils.join("&", queries);
        if(!query.isEmpty())
        {
            return "?"+query;
        }
        return query;
    }

    protected String unde(String input)
    {
        try {
            return URLEncoder.encode(input, "utf-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public Kuikar url(String url) {
        this.url = url;
        return this;
    }

    public Kuikar query(String key, String value) {
        queries.add(key + "=" + unde(value));
        return this;
    }

    public Kuikar badCode(OnBadCodeListener listener) {
        this.onBadCodeListener = listener;
        return this;
    }

    public Kuikar onEnd(OnEndListener listener) {
        this.onEndListener = listener;
        return this;
    }

    public Kuikar onStart(OnStartListener listener) {
        this.onStartListener = listener;
        return this;
    }

    public Kuikar onError(OnErrorListener listener) {
        this.onErrorListener = listener;
        return this;
    }

    public void execute() {
        new GetData().execute();
    }

    private void onBadCode() {
        if (onBadCodeListener != null) {
            onBadCodeListener.onBadCode(code);
        }
    }

    private void onEndEvent() {
        long timeLapse = endTime - startTime;
        if (onEndListener != null) {
            onEndListener.onEnd(result,timeLapse);
        }
    }

    private void onStart() {
        if (onStartListener != null) {
            onStartListener.onStart();
        }
    }

    private void onError(Exception e) {
        if (onErrorListener != null) {
            onErrorListener.onError(e);
        }
    }
    /////////////////////////////////

    public interface OnBadCodeListener {
        void onBadCode(int code);
    }

    public interface OnEndListener {
        void onEnd(String result, long timeLapse);
    }

    public interface OnStartListener {
        void onStart();
    }

    public interface OnErrorListener {
        void onError(Exception e);
    }

    class GetData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            startTime = System.currentTimeMillis();
            onStart();
            HttpURLConnection urlConnection = null;
            try {
                String getUrl = url + getQuery();
                URL url = new URL(getUrl);
                urlConnection = (HttpURLConnection) url.openConnection();

                code = urlConnection.getResponseCode();

                if (code == 200) {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    if (in != null) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                        String line = "";

                        while ((line = bufferedReader.readLine()) != null)
                            result += line;
                    }
                    in.close();
                } else {
                    onBadCode();
                }

                return result;
            } catch (Exception e) {
                onError(e);
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            endTime = System.currentTimeMillis();
            onEndEvent();
            super.onPostExecute(result);
        }
    }
    ///////////////////

}
