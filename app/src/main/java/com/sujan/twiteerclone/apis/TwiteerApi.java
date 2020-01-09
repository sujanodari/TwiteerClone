package com.sujan.twiteerclone.apis;

import com.sujan.twiteerclone.model.CreateUser;
import com.sujan.twiteerclone.responses.ImageResponse;
import com.sujan.twiteerclone.responses.LoginResponses;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface TwiteerApi {
    //Create user
    @Headers("Content-Type: application/json")
    @POST("users/signup")
    Call<LoginResponses> registerUser(@Body CreateUser usr);

    //login user
    @Headers("Content-Type: application/json")
    @POST("users/signin")
    Call<LoginResponses> login(@Body CreateUser user);

    @Multipart
    @POST("users/profile")
    Call<ImageResponse>uploadImage(@Part MultipartBody.Part image);
}
