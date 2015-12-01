package com.johnson.commonlibs.common_utils.api;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.johnson.commonlibs.common_utils.annotations.Host;
import com.johnson.commonlibs.common_utils.contants.DateStyle;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Network Utils <br/>
 * Created by shefenfei on 15/9/11.
 *
 * @version 1.0
 */
public final class ServiceGenerator {

    private Retrofit.Builder mRetrofit;
    private OkHttpClient okHttpClient = new OkHttpClient();
    private HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    private static Gson gson;

    private ServiceGenerator() {
        gson = new GsonBuilder().setDateFormat(DateStyle.YYYY_MM_DD_HH_MM_SS).create();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.interceptors().add(logging);
        mRetrofit = new Retrofit.Builder();
        mRetrofit.addConverterFactory(GsonConverterFactory.create(gson));
        mRetrofit.client(okHttpClient);
    }

    private static class RestClientFactory {
        static ServiceGenerator Instance = new ServiceGenerator();
    }

    /**
     * singleInstance
     * @return
     */
    public static ServiceGenerator generate() {//此类做法效率最高并为线程安全的
        return RestClientFactory.Instance;
    }

    /**
     * getApiService
     *
     * @param service Service
     * @param <T>
     * @return service
     */
    public <T> T getApiService(Class<T> service) {
        return mRetrofit.build().create(service);
    }

    /**
     * setting host url
     *
     * @param url 标识有@Host的类
     */
    public ServiceGenerator setEndpoint(Class<?> url) {
        if (url.isAnnotationPresent(Host.class)) {
            Host host = url.getAnnotation(Host.class);
            String hostUrl = host.value();
            mRetrofit.baseUrl(hostUrl).build();
            return generate();
        } else {
            Log.e("Error", "unknow host address : " + url + " , try to add @Host(url)");
        }
        return null;
    }
}
