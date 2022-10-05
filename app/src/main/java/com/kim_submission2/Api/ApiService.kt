package com.kim_submission2.Api

import com.kim_submission2.Response.DataUser
import com.kim_submission2.Response.DetailUserResponse
import com.kim_submission2.Response.ResponseUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorization:token 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")
    @GET("search/users")
    fun searchuser(
        @Query("q") query: String
    ): Call<ResponseUser>

    @Headers("Authorization:token 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")
    @GET("users/{username}")
    fun detailuser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @Headers("Authorization:token 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")
    @GET("users/{username}/followers")
    fun getfollowers(
        @Path("username") username: String
    ): Call<ArrayList<DataUser>>

    @Headers("Authorization:token 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")
    @GET("users/{username}/following")
    fun getfollowing(
        @Path("username") username: String
    ): Call<ArrayList<DataUser>>
}