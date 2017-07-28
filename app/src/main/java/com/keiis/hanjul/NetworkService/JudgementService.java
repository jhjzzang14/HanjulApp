package com.keiis.hanjul.NetworkService;

import com.keiis.hanjul.NetworkModel.BodyExistPlayer;
import com.keiis.hanjul.NetworkModel.ContestGameList;
import com.keiis.hanjul.NetworkModel.ContestRetrieve;
import com.keiis.hanjul.NetworkModel.DataContestResult;
import com.keiis.hanjul.NetworkModel.DataResult;
import com.keiis.hanjul.NetworkModel.ExistPlayer;
import com.keiis.hanjul.NetworkModel.ExistPlayerSave;
import com.keiis.hanjul.NetworkModel.GameDegree;
import com.keiis.hanjul.NetworkModel.MessageResult;
import com.keiis.hanjul.NetworkModel.result;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by hojun on 2017-05-13.
 */

public interface JudgementService {
    @FormUrlEncoded
    @POST("hanjul.do")
    Call<DataContestResult<ContestRetrieve>> setContestRetrieve(@Field("beanName") String beanName,@Field("baseParam") JSONObject jsonObject);

    @FormUrlEncoded
    @POST("hanjul.do")
    Call<MessageResult> setContestJudgementSave(@Field("beanName") String beanName, @Field("baseParam") JSONObject jsonObject);
    @FormUrlEncoded
    @POST("hanjul.do")
    Call<DataResult<ContestGameList>> setContestGameList(@Field("beanName") String beanName, @Field("baseParam") JSONObject jsonObject);
    @FormUrlEncoded
    @POST("hanjul.do")
    Call<DataResult<GameDegree>> setGameDegree(@Field("beanName") String beanName, @Field("baseParam") JSONObject jsonObject);
    @FormUrlEncoded
    @POST("hanjul.do")
    Call<DataResult<ExistPlayer>> setExistPlayer(@Field("beanName") String beanName,@Field("baseParam") JSONObject jsonObject);

    @FormUrlEncoded
    @POST("hanjul.do")
    Call<DataResult<ExistPlayerSave>> setExistPlayerSave(@Field("beanName") String beanName, @Field("baseParam") JSONObject jsonObject);


}
