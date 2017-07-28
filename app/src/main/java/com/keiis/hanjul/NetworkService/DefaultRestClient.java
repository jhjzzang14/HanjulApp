package com.keiis.hanjul.NetworkService;

import com.keiis.hanjul.login.LoginActivity;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hojun on 2017-02-18.
 */

public class DefaultRestClient<T> {
    private T service;
    private String baseUrl = "http://www.keiis.co.kr/hanjulApp/";

    public T getClient(Class<? extends T> type){
        if (service == null){
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    Request original = chain.request();

                    Request request = original.newBuilder()
                            .header("ex-header","sample")
                            .method(original.method(),original.body())
                            .build();
                    return chain.proceed(request);
                }
            }).build();

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = client.create(type);
        }

        return service;
    }
}
