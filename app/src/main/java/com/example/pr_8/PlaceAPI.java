package com.example.pr_8;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PlaceAPI {

    @GET("posts")
    Call<List> getPosts();

    @GET("comments")
    Call<List> getComments();

    @POST("posts")
    Call<MyResponse> request(@Body PlacePost placePost);
}
