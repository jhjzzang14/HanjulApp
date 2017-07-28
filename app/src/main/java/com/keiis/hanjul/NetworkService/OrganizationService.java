package com.keiis.hanjul.NetworkService;

import com.keiis.hanjul.NetworkModel.ContestGameList;
import com.keiis.hanjul.NetworkModel.ContestOrgnRetrieve;
import com.keiis.hanjul.NetworkModel.ContestRetrieve;
import com.keiis.hanjul.NetworkModel.DataContestResult;
import com.keiis.hanjul.NetworkModel.DataResult;
import com.keiis.hanjul.NetworkModel.RecordDataResult;
import com.keiis.hanjul.NetworkModel.ResultList;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by hojun on 2017-05-20.
 */

public interface OrganizationService {
    @FormUrlEncoded
    @POST("hanjul.do")
    Call<DataContestResult<ContestRetrieve>> setContestRetrieve(@Field("beanName")String beanName, @Field("baseParam") JSONObject jsonObject);

    @FormUrlEncoded
    @POST("hanjul.do")
    Call<DataResult<ContestOrgnRetrieve>> setContestOrgnRetrieve(@Field("beanName")String beanName,@Field("baseParam")JSONObject jsonObject);
    @FormUrlEncoded
    @POST("hanjul.do")
    Call<DataResult<ContestGameList>> setContestGameList(@Field("beanName")String beanName,@Field("baseParam")JSONObject jsonObject);
    @FormUrlEncoded
    @POST("hanjul.do")
    Call<RecordDataResult<ResultList>> setResultList(@Field("beanName")String beanName, @Field("baseParam")JSONObject jsonObject);
}
