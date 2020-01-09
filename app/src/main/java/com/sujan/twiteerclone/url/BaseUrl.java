package com.sujan.twiteerclone.url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseUrl {
        public static final String Base_Url ="http://10.0.2.2:3112/api/v1/";

        public static Retrofit getInstance(){
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            return retrofit;
        }


}
