package com.yidian.chengshengda.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;


import com.yidian.chengshengda.base.App;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetUtils {
    private Apis apis;
    private StringBuffer baf;

    private NetUtils(){
        initOkHttp();
    }
    private static class SingleInstance{
        private static NetUtils utils = new NetUtils();
    }
    public static NetUtils getInstance(){
        return SingleInstance.utils;
    }
    private void initOkHttp() {
        //添加日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //设置缓存时间
        OkHttpClient build = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new HeaderInterceptor())
                .build();

        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.client(build).baseUrl("http://81.71.121.177:8081/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apis = retrofit.create(Apis.class);
    }

    public Apis getApis() {
        return apis;
    }


    public class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
           String token = SPUtil.getInstance().getData(App.getContext(), SPUtil.FILE_NAME, SPUtil.KEY_TOKEN);
            if(TextUtils.isEmpty(token)){
             return chain.proceed(request);
           }
            Request requestNew = request.newBuilder().addHeader("Content-Type","application/json;charset=UTF-8")
                    .build();;

            return chain.proceed(requestNew);
        }
    }
    //xiangji
    public RequestBody getRequsetBody(List<File> files, HashMap<String,String> map){
//        if (map.size() < 1){
//            return null;
//        }
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        for (Map.Entry<String,String> entry:map.entrySet()){
            Log.i("xxx","key = "+entry.getKey()+"value = "+entry.getValue());
            builder.addFormDataPart(entry.getKey(),entry.getValue()+"");
        }


        builder.addFormDataPart("doorPrint",files.get(0).getName(),RequestBody.create(MediaType.parse("image/jepg"),files.get(0)));
        builder.addFormDataPart("licensePrint",files.get(1).getName(),RequestBody.create(MediaType.parse("image/jepg"),files.get(1)));

        return builder.build();
    }
    public StringBuffer getImg(String url){
        try {
            URL uri = new URL(url);//注意，这里的URL地址必须为网络地址，
            //URL uri = new URL("http://localhost:8080/my/poem.txt");
            //本地地址http://localhost:8080/my/poem.txt会报Connection Refused的异常
            URLConnection ucon = uri.openConnection();
            InputStream is = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            baf = new StringBuffer();
            int current = 0;
            while((current = bis.read()) != -1) {
                baf.append(current);
            }
            //myString = EncodingUtils.getString(baf.toByteArray(), "GBK");
            //myString = new String(baf.toByteArray());这个出现乱码，要在txt文件保存时选中utf-8
        } catch(Exception e) {
           String myString = e.getMessage();
        }
        return baf;
    }
}
