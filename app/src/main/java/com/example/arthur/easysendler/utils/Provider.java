package com.example.arthur.easysendler.utils;

import com.example.arthur.easysendler.api.MailApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by student on 05.07.2018.
 */

public class Provider {

    static Retrofit retrofit;

    public static Retrofit getRetrofit(){

        if(retrofit==null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://easy-sendler.somee.com/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public static class Api{
        public static MailApi getMailApi(){
            return getRetrofit().create(MailApi.class);
        }

    }




}
