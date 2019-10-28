package com.schoolwork.mortgagecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var retrofitDisposable: Disposable
    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val obsPurchasePrice = edit_purchase_price.textChanges()
        val obsDownPayment = edit_downpay.textChanges()
        val obsInterestRate = edit_interest_rate.textChanges()
        val obsLoanLength = edit_loan_length.textChanges()

        val obsCombined = Observables.combineLatest(obsPurchasePrice, obsDownPayment, obsInterestRate, obsLoanLength){_,_,_,_ -> getAmortizedMortgagePayment()}


        retrofitDisposable = InitializeRetro.startRetroCalls().getRandomNumbersArray(2, "uint8")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn { RandomNums(arrayOf(1)) }
            .subscribe { randomNums -> randomNums.data[0].toString()}

        disposable = obsCombined.observeOn(AndroidSchedulers.mainThread()).subscribe{result -> txt_answer.text = result.toString()}
    }

    override fun onDestroy() {
        retrofitDisposable.dispose()
        disposable.dispose()
        super.onDestroy()
    }

    fun getAmortizedMortgagePayment(): Double{
        val editPurchase = edit_purchase_price.text.toString()
        val editDown = edit_downpay.text.toString()
        val editInterest = edit_interest_rate.text.toString()
        val editLength = edit_loan_length.text.toString()

        return if (editPurchase.isEmpty() ||
            editDown.isEmpty() ||
            editInterest.isEmpty() ||
            editLength.isEmpty()){
            0.0
        }else{
            val interestPerMonth = editInterest.toDouble()/12
            val payPeriods = editLength.toInt()
            val loanAmount = editPurchase.toDouble() - editDown.toDouble()

            val top: Double = (1+interestPerMonth).pow(payPeriods)*interestPerMonth*loanAmount
            val bot: Double = (1+interestPerMonth).pow(payPeriods)-1
            top/bot
        }
    }
}
