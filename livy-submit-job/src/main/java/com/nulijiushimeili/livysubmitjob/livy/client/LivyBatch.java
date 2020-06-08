package com.nulijiushimeili.livysubmitjob.livy.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.nulijiushimeili.dataservice.livy.bean.LivyBatchJob;
import com.nulijiushimeili.livysubmitjob.livy.bean.LivyBatchJob;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface LivyBatch {

    @POST("/batches")
    @Headers("Content-Type: application/json")
    Call<LivyBatchJob> createBatchJob(@Body RequestBody createBatchJobReq);

    @GET("/ws/v1/cluster/apps/{applicationId}")
    Call<JsonNode> getApps(@Path("applicationId") String id);

    @GET("/ws/v1/cluster/apps")
    Call<Object> getApps(@Query("name") String userName, @Query("state") String state, @Query("startedTimeBegin") String startedTimeBegin, @Query("startedTimeEnd") String startedTimeEnd);

    @Headers("Content-Type: application/json")
    @PUT("/ws/v1/cluster/apps/{applicationId}/state")
    Call<JsonNode> killAppJob(@Path("applicationId") String applicationId, @Body RequestBody state);

}

