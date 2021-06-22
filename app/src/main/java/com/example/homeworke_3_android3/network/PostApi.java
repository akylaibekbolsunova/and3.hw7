package com.example.homeworke_3_android3.network;

import com.example.homeworke_3_android3.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PostApi {
    @GET("posts")
    Call<List<PostModel>> getPosts();

    @POST("posts")
    Call<PostModel> createPost(
            @Body PostModel model
    );

    @PUT("posts/{id}")
    Call<PostModel> updatePost(
            @Path("id") Integer id,
            @Body PostModel model
    );

    @DELETE("posts/{id}")
    Call<PostModel> deletePostById(
            @Path("id") Integer id
    );
}


