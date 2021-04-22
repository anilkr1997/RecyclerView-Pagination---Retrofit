package com.thereza.Retrofitpagination.api;


import com.thereza.Retrofitpagination.models.Topodderss;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Pagination
 * Created by Suleiman19 on 10/27/16.
 * Copyright (c) 2016. Suleiman Ali Shakir. All rights reserved.
 */

public interface ApiService {
@FormUrlEncoded
    @POST("notificationList")
    Call<Topodderss> getTopRatedMovies(
            @Field("user_id") String id,
            @Query("page") int pageIndex
    );

}
