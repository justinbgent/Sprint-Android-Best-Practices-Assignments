package com.schoolwork.rxjava

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ForexService {

    @GET("{currency}")
    fun getRatesFor(@Path("currency")currency: String): Single<Rates>
}