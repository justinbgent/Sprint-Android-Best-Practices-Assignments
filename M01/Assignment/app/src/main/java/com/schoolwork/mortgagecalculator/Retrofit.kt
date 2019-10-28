package com.schoolwork.mortgagecalculator

import com.google.gson.GsonBuilder
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class RandomNums(val data: Array<Int>)

interface RetroCalls{
    @GET("jsonI.php")
    fun getRandomNumbersArray(@Query("length")length: Int,
                              @Query("type")type: String): Single<RandomNums>

}

object InitializeRetro{
    fun startRetroCalls(): RetroCalls {
        return Retrofit.Builder()
            .baseUrl("https://qrng.anu.edu.au/API/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(RetroCalls::class.java)
    }
}