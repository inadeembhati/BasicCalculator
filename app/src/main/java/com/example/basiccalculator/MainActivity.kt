package com.example.basiccalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    enum class ACTION{
        ADD ,
        SUBS,
        MULTI,
        DIVIDE;
    }

    var value1  = 0F
    var value2  = 0F
    var value_number = 1
    var typeOfAction = -1
    var isFirstTime :Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn0.setOnClickListener{addlValue(0)}
        btn1.setOnClickListener{addlValue(1)}
        btn2.setOnClickListener{addlValue(2)}
        btn3.setOnClickListener{addlValue(3)}
        btn4.setOnClickListener{addlValue(4)}
        btn5.setOnClickListener{addlValue(5)}
        btn6.setOnClickListener{addlValue(6)}
        btn7.setOnClickListener{addlValue(7)}
        btn8.setOnClickListener{addlValue(8)}
        btn9.setOnClickListener{addlValue(9)}
        btnAdd.setOnClickListener{performAction(ACTION.ADD.ordinal)}
        btnSubs.setOnClickListener{performAction(ACTION.SUBS.ordinal)}
        btnMulti.setOnClickListener{performAction(ACTION.MULTI.ordinal)}
        btnDivide.setOnClickListener{performAction(ACTION.DIVIDE.ordinal)}
        btnDel.setOnClickListener{ResetValue()}
        btnEquals.setOnClickListener{Equalsto()}
    }


private fun addlValue(number:Int){
    if(value_number==1){
        value1 = value1*10 +number
        txtAnswer.text = value1.toString()
    }else{
        value2 = value2*10+number
        txtAnswer.text = value2.toString()
    }
}
 private  fun performAction(type:Int) {
     typeOfAction = type
     if(!isFirstTime) {
         var answer = 0F
         when (typeOfAction) {
             ACTION.ADD.ordinal -> answer = value1 + value2

             ACTION.SUBS.ordinal -> answer = value1 - value2
             ACTION.MULTI.ordinal -> answer = value1 * value2
             ACTION.DIVIDE.ordinal -> answer = value1 / value2
         }
         value1 = answer
        // value2 = 0F
         txtAnswer.text =answer.toString()
     }else
         txtAnswer.text =""

     if (value_number == 1)
         value_number = 2
     else
         value_number = 1

 }
private  fun Equalsto(){
    isFirstTime = true
    var answer :Float = 0F
    when(typeOfAction){
        ACTION.ADD.ordinal -> answer = (value1+value2)
        ACTION.SUBS.ordinal-> answer = (value1-value2)
        ACTION.MULTI.ordinal ->answer = (value1*value2)
        ACTION.DIVIDE.ordinal->answer = (value1/value2)
    }
    value1  = answer
    value2 = 0F
    value_number = 2
    txtAnswer.text = answer.toString()
}
    private  fun ResetValue(){
        isFirstTime = true;
        value1 = 0F
        value2= 0F
        value_number =1
        typeOfAction = -1
        txtAnswer.text = ""
    }
}
