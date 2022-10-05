package com.kim_submission2.Response

data class DetailUserResponse(
    val login : String,
    val id : Int,
    val name : String,
    val company : String,
    val avatar_url : String,
    val follower_url : String,
    val following_url : String,
    val following : Int,
    val followers : Int,
    val location : String,
)
