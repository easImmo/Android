package com.projet.easimmo.service.generator;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by victor on 28/04/2016.
 */
public final class ServiceGenerator {

    private static Retrofit sRetrofit;

    private ServiceGenerator(){
    }

    //Ajouter base url node.js webservices
    private static Retrofit getRetrofit() {
        if (sRetrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY));

            sRetrofit = new Retrofit.Builder()
                    .baseUrl("urlNico")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return sRetrofit;
    }

    public static <S> S createService(Class<S> serviceClass) {
        return getRetrofit().create(serviceClass);
    }
}
