package com.example.submissionreal3.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submissionreal3.api.RetrofitClient
import com.example.submissionreal3.data.model.DetailUserResponse
import retrofit2.Call
import retrofit2.Response

class DetailUserViewModel: ViewModel() {

    val user = MutableLiveData<DetailUserResponse>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun setUserDetail(username: String){
        _isLoading.postValue(true)
            RetrofitClient.apiInstance
                .getDetail(username)
                .enqueue(object :retrofit2.Callback<DetailUserResponse>{
                    override fun onResponse(
                        call: Call<DetailUserResponse>,
                        response: Response<DetailUserResponse>
                    ) {
                        if(response.isSuccessful){
                            user.postValue(response.body())
                        }
                        _isLoading.postValue(false)
                    }

                    override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                        t.message?.let { Log.d("failure", it) }
                        _isLoading.postValue(false)
                    }
                })
        }

        fun getUserDetail(): LiveData<DetailUserResponse>{
            return user
        }
}