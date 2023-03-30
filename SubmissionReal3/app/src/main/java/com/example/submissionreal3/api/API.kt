package com.example.submissionreal3.api

import com.example.submissionreal3.data.model.DetailUserResponse
import com.example.submissionreal3.data.model.User
import com.example.submissionreal3.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface API {

    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUsers(@Query("q") query: String): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetail(@Path ("username") username: String): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(@Path ("username") username: String): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(@Path ("username") username: String): Call<ArrayList<User>>
}