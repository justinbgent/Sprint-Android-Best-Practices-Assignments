package com.schoolwork.mortgagecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var retrofitDisposable: Disposable
    private lateinit var bindingDisposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setBindingDisposable()
        setRetroDisposable()

        btn_1.setOnClickListener {
            edit_interest_rate.setText(btn_1.text)
        }

        btn_2.setOnClickListener {
            edit_interest_rate.setText(btn_2.text)
        }

        btn_generate.setOnClickListener {
            progress_bar.visibility = View.VISIBLE
            setRetroDisposable()
        }
    }

    override fun onDestroy() {
        retrofitDisposable.dispose()
        bindingDisposable.dispose()
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

    fun setRetroDisposable(){
        retrofitDisposable = InitializeRetro.startRetroCalls().getRandomNumbersArray(2, "uint8")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn { RandomNums(arrayOf(1, 2)) }
            .subscribe { randomNums ->
                for(i in randomNums.data.indices) {
                    when(i){
                        0 ->{
                            if (randomNums.data[i] > 99){
                                btn_1.text = (randomNums.data[i]/10000.0).toString()
                            }else if (randomNums.data[i] in 10..99){
                                btn_1.text = (randomNums.data[i]/1000.0).toString()
                            }else{
                                btn_1.text = (randomNums.data[i]/100.0).toString()
                            }
                        }
                        1 ->{
                            if (randomNums.data[i] > 99){
                                btn_2.text = (randomNums.data[i]/10000.0).toString()
                            }else if (randomNums.data[i] in 10..99){
                                btn_2.text = (randomNums.data[i]/1000.0).toString()
                            }else{
                                btn_2.text = (randomNums.data[i]/100.0).toString()
                            }
                        }
                    }
                }
                progress_bar.visibility = View.INVISIBLE
            }
    }

    fun setBindingDisposable(){
        val obsPurchasePrice = edit_purchase_price.textChanges()
        val obsDownPayment = edit_downpay.textChanges()
        val obsInterestRate = edit_interest_rate.textChanges()
        val obsLoanLength = edit_loan_length.textChanges()

        val obsCombined = Observables.combineLatest(obsPurchasePrice, obsDownPayment, obsInterestRate, obsLoanLength){_,_,_,_ -> getAmortizedMortgagePayment()}
        //result down below is the result of the above Lambda that notes each change in EditText fields.
        bindingDisposable = obsCombined.observeOn(AndroidSchedulers.mainThread()).subscribe{ result -> txt_answer.text = result.toString()}
    }
}
