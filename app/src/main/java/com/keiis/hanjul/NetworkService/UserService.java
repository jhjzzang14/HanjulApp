package com.keiis.hanjul.NetworkService;

import com.keiis.hanjul.NetworkModel.DataResult;
import com.keiis.hanjul.NetworkModel.Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by hojun on 2017-02-19.
 */

public interface UserService {
    //login
    @FormUrlEncoded
    @POST("index.jsp")
    Call<DataResult<Login>> postLogin(@Query("beanName")String beanName, @Field("id") String id, @Field("password") String password, @Field("type")String type);

    //loginOpen
    @GET("index.jsp")
    Call<DataResult> getLoginOpen(@Query("beanName")String beanName);
}