package com.example.homeworke_3_android3.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostService {
    private static PostService mInstance;
    private static final String BASE_URL = "https://android-3-mocker.herokuapp.com/";
    private Retrofit mRetrofit;

    private PostService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static PostService getInstance() {
        if (mInstance == null) {
            mInstance = new PostService();
        }
        return mInstance;
    }

    public PostApi getPostApi() {
        return mRetrofit.create(PostApi.class);
    }
}






