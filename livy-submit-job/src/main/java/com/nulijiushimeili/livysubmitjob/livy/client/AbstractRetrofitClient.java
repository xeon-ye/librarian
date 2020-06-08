//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.nulijiushimeili.livysubmitjob.livy.client;


import com.nulijiushimeili.dataservice.config.PropertiesGetter;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.codec.binary.Base64;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract class AbstractRetrofitClient {





    protected final MediaType jsonReq = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient httpClient=new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)       //设置连接超时
            .readTimeout(60, TimeUnit.SECONDS)          //设置读超时
            .writeTimeout(60,TimeUnit.SECONDS)          //设置写超时
            .retryOnConnectionFailure(true)             //是否自动重连
            .build();                                   //构建OkHttpClient对象

    //ConverterFactory用来统一解析ResponseBody返回数据
    private  static Builder builder = new Builder().baseUrl(PropertiesGetter.getLivyServiceUrl() + "/" + PropertiesGetter.getLivyServiceName() + "/")
            .addConverterFactory(JacksonConverterFactory.create());

    //ConverterFactory用来统一解析ResponseBody返回数据
    private  static Builder yarnBuilder = new Builder().baseUrl(PropertiesGetter.getYarnUrl())
            .addConverterFactory(JacksonConverterFactory.create());

    protected <T> T executeCall(Call<T> call) {
        try {
            //每个请求都被包装成一个Call对象,发送同步请求还是异步请求都是在Call对象
            Response<T> response = call.execute();
            //System.out.println("==response=="+response);
            // code >= 200 && code < 300
            if(response.isSuccessful()) {
                return response.body();
            } else {
                System.out.println("execute error:" + response.code() + ":" + response.errorBody().string());
                throw new RuntimeException("execute error:" + response.code() + ":" + response.errorBody().string());
            }
        } catch (IOException var3) {
            throw new RuntimeException("execute error:" + var3.getMessage());
        }
    }

    /*
	 * Creates a Retrofit <S> class service for basic authentication
	 * 为基本身份验证创建一个Retrofit <S>类服务
	 */
    public static <S> S createService(Class<S> serviceClass, String username, String password) {
        if (username != null && password != null) {
            String credentials = username + ":" + password;
            final String basic = "Basic " + Base64.encodeBase64String(credentials.getBytes());
            httpClient.interceptors().clear();
            httpClient.interceptors().add(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder().header("Authorization", basic)
                            .header("Content-Type", "applicaton/json").method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        Retrofit retrofit = new Builder().client(httpClient).build();
        return retrofit.create(serviceClass);
    }

    public static <S> S createYarnService(Class<S> serviceClass) {
        return yarnBuilder.build().create(serviceClass);
    }
    /*
     * Creates a Retrofit <S> class service without interceptors
     * 创建无拦截器的Retrofit <S>类服务
     */
    public static <S> S createService(Class<S> serviceClass) {
        return builder.build().create(serviceClass);
    }




}
