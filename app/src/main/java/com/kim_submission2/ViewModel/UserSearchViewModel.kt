package com.kim_submission2.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kim_submission2.Api.ApiConfig
import com.kim_submission2.Response.DataUser
import com.kim_submission2.Response.ResponseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserSearchViewModel : ViewModel() {

    val listDataUser = MutableLiveData<ArrayList<DataUser>>()
    fun setDataUser(query: String){
        ApiConfig.apiInstance
            .searchuser(query)
            .enqueue(object : Callback<ResponseUser> {
                override fun onResponse(
                    call: Call<ResponseUser>,
                    response: Response<ResponseUser>
                )
                {
                    if (response.isSuccessful)
                    {
                        listDataUser.postValue(response.body()?.items)
                    }
                }
                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    Log.d("Data Failure", t.message.toString())
                }
            })
    }

    fun getDataUser(): LiveData<ArrayList<DataUser>> {
        return listDataUser
    }
}