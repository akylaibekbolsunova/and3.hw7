package com.example.homeworke_3_android3.network;

import com.example.homeworke_3_android3.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostRepasitory {
    public static void getPosts(PostsCallBack callBack) {
        PostService.getInstance().getPostApi().getPosts().enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                callBack.onFailure(t.getMessage());
            }
        });
    }

    public static void updatePost(Integer id, PostModel model) {
        PostService.getInstance().getPostApi().updatePost(id, model).enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {

            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {

            }
        });
    }

    public static void createPost(PostModel model) {
        PostService.getInstance().getPostApi().createPost(model).enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {

            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {

            }
        });
    }

    public static void deleteById(Integer id, PostDeleteByIdCallback callback) {
        PostService.getInstance().getPostApi().deletePostById(id).enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }


    public interface PostsCallBack {
        void onSuccess(List<PostModel> list);

        void onFailure(String message);
    }

    public interface PostDeleteByIdCallback {
        void onSuccess(PostModel model);

        void onFailure(String message);
    }


}
