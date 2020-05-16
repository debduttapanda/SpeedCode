package com.coderusk.speedcode.app;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseWebs {
    protected static String BASE_URL = "";
    private static Retrofit retrofit = null;
    private Context context;

    public BaseWebs(Context context) {
        this.context = context;
    }


    private static Retrofit getAltClient() {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .cache(null)
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request.Builder builder = request.newBuilder().addHeader("Cache-Control", "no-cache");
                        request = builder.build();
                        return chain.proceed(request);
                    }
                })
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    public interface OnFetchEvent<T> {
        void onWaitStart();

        void onWaitEnd();

        void onEnd(String situation, Throwable t, T value);
    }

    private <T> void fetch(Call<T> call, final OnFetchEvent onFetchEvent) {

        Log.d("fetch_api", call.toString());
        if (onFetchEvent == null) {
            return;
        }
        onFetchEvent.onWaitStart();
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                onFetchEvent.onWaitEnd();
                if (response == null) {
                    onFetchEvent.onEnd("root_response_null", null, null);
                    return;
                }
                if (!response.isSuccessful()) {
                    onFetchEvent.onEnd("root_response_failed", null, null);
                    return;
                }
                T value = response.body();
                if (value == null) {
                    onFetchEvent.onEnd("root_response_body_null", null, null);
                    return;
                }
                onFetchEvent.onEnd("value", null, value);
                return;
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                call.cancel();
                onFetchEvent.onWaitEnd();
                onFetchEvent.onEnd("failed", t, null);
            }
        });
    }

    private void fetchd(Call<ResponseBody> call, final OnFetchEvent onFetchEvent) {

        Log.d("fetch_api", call.toString());
        if (onFetchEvent == null) {
            return;
        }
        onFetchEvent.onWaitStart();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                onFetchEvent.onEnd("value", null, response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                call.cancel();
            }
        });
    }

}