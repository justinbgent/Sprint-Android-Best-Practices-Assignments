package com.schoolwork.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

/*
class MainActivity : AppCompatActivity() {

    lateinit var disposable: Disposable

    val obsJust = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val obsCreate: Observable<Int> = Observable.create { emitter ->
        for(i in 1..10){
            Thread.sleep(1000)
            emitter.onNext(i)
        }
        Thread.sleep(1000)
        emitter.onComplete()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        disposable = obsCreate.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({num->
            Log.i("MainActivity", "$num")
            text_view.text = "$num"}, {}, { text_view.text = "Completed!"})
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}
*/
/*
class MainActivity : AppCompatActivity() {

    lateinit var disposable: Disposable

    //val obsJust = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val obsCreate: Observable<Int> = Observable.create { emitter ->
        for(i in 1..10){
            Thread.sleep(1000)
            emitter.onNext(i)
        }
        emitter.onComplete()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val obsFirst = first_name.textChanges().debounce(500, TimeUnit.MILLISECONDS).filter { it.length > 2 }
        val obsLast = last_name.textChanges()

        val obsCombined = Observables.combineLatest(obsFirst, obsLast) { first, last -> "$first $last" }

        //disposable = obsCombined.subscribe{ name -> text_view.text = name }
        disposable = obsCombined.observeOn(AndroidSchedulers.mainThread()).subscribe{ name -> text_view.text = name }

    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}
*/

class MainActivity : AppCompatActivity() {

    lateinit var disposable: Disposable

    //val obsJust = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val obsCreate: Observable<Int> = Observable.create { emitter ->
        for(i in 1..10){
            Thread.sleep(1000)
            emitter.onNext(i)
        }
        emitter.onComplete()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val obsFirst = first_name.textChanges().debounce(500, TimeUnit.MILLISECONDS).filter { it.length > 2 }
        val obsLast = last_name.textChanges()

        val obsCombined = Observables.combineLatest(obsFirst, obsLast) { first, last -> "$first $last" }

        val retrofit = Retrofit.Builder().baseUrl("https://api.exchangerate-api.com/v4/latest/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //RxJava stuff
            .build()

        val service = retrofit.create(ForexService::class.java)


        disposable = service.getRatesFor("USD")
            .subscribeOn(Schedulers.io())
            //.retry(3) can retry the call a few times if wanted
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { rates -> exchange_rates.text = rates.rates.toString()}

    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}
/**/