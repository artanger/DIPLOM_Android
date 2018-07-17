package com.example.arthur.easysendler.api;

import com.example.arthur.easysendler.entities.Recipient;
import com.example.arthur.easysendler.entities.RunResponse;
import com.example.arthur.easysendler.entities.Setting;
import com.example.arthur.easysendler.entities.Template;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by student on 05.07.2018.
 */

public interface MailApi {
    @GET("api/rl/get")
    Observable<List<Recipient>> getRecipientList(@Query("limit") int limit, @Query("start") int start);

    @GET("api/s/get")
    Observable<List<Setting>> getSetting(@Query("limit") int limit, @Query("start") int start);

    @GET("api/t/get")
    Observable<List<Template>> getTemplate(@Query("limit") int limit, @Query("start") int start);

    @GET("api/run/get")
    Observable<List<RunResponse>> run(@Query("rid") String recipientId,@Query("tid") String templateId,@Query("sid") String settingId);

}
