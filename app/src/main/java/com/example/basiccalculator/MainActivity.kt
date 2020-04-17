package com.example.basiccalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    enum class ACTION{
        ADD ,
        SUBS,
        MULTI,
        DIVIDE,
        EQUALS;
    }

    var value1  = 0F
    var value2  = 0F
    var value_number = 1
    var typeOfAction = 0
    var isFirstTime :Boolean = true
    var isEqualPressed :Boolean = false
    var isDotPressed :Boolean = false
    var divider = 1
    var isLastActionIsReset = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn0.setOnClickListener{addlValue(0F)}
        btn1.setOnClickListener{addlValue(1F)}
        btn2.setOnClickListener{addlValue(2F)}
        btn3.setOnClickListener{addlValue(3F)}
        btn4.setOnClickListener{addlValue(4F)}
        btn5.setOnClickListener{addlValue(5F)}
        btn6.setOnClickListener{addlValue(6F)}
        btn7.setOnClickListener{addlValue(7F)}
        btn8.setOnClickListener{addlValue(8F)}
        btn9.setOnClickListener{addlValue(9F)}
        btnDot.setOnClickListener{addlValue(-1F,true)}
        btnAdd.setOnClickListener{performAction(ACTION.ADD.ordinal)}
        btnSubs.setOnClickListener{performAction(ACTION.SUBS.ordinal)}
        btnMulti.setOnClickListener{performAction(ACTION.MULTI.ordinal)}
        btnDivide.setOnClickListener{performAction(ACTION.DIVIDE.ordinal)}
        btnDel.setOnClickListener{ResetValue()}
        btnEquals.setOnClickListener{Equalsto()}
    }


private fun addlValue(number:Float,isDot:Boolean = false){
    isLastActionIsReset =false
    if(isDot == true){
        isDotPressed = true
    }
    else {
        if (value_number == 1) {
            if(isDotPressed) {
                divider = divider*10
                value1 = value1 + number/divider
            }
            else
                value1 = value1 * 10 + number

            txtAnswer.text = value1.toString()
        } else {
            if(isDotPressed) {
                divider = divider*10
                value2 = value2 + number/divider
            }
            else
                value2 = value2 * 10 + number

            txtAnswer.text = value2.toString()
        }
    }
}
 private  fun performAction(type:Int) {
     isLastActionIsReset = false
     typeOfAction = type
     isDotPressed = false
     divider = 1
    // if(!isFirstTime  ) this.Equalsto()

     if (value_number == 1)
         value_number = 2
     else
         value_number = 1



 }
private  fun Equalsto(){
 if(isLastActionIsReset == true) {
     return
 }
    isFirstTime = false
    var histStr:String = historyVieiw.text as String
    var str2 :String =  value1.toString() + " " + getSign(typeOfAction) + " "+ value2.toString()
    var answer :Float = 0F
    when(typeOfAction){
        ACTION.ADD.ordinal -> answer = (value1+value2)
        ACTION.SUBS.ordinal-> answer = (value1-value2)
        ACTION.MULTI.ordinal ->answer = (value1*value2)
        ACTION.DIVIDE.ordinal->answer = (value1/value2)
        else -> {answer = (value1+value2)
        str2 =   value1.toString()
            }
      //  ACTION.EQUALS.ordinal->answer = (value1+value2)
    }
    value1  =0F
    value2 =  answer
    value_number = 1
    ResetValue();

    historyVieiw.text =  histStr+ str2+ "= " + answer.toString()+"\n"
    txtAnswer.text = answer.toString()
}
    private  fun ResetValue(){
        isLastActionIsReset = true
        isFirstTime = true;
        value1 = 0F
        value2= 0F
        value_number =1
        typeOfAction = -1
        txtAnswer.text = ""
        historyVieiw.text = ""
        isDotPressed = false
        divider = 1
    }
    private fun getSign(sign:Int) :String{

        when(typeOfAction){
            ACTION.ADD.ordinal ->  return  "+"
            ACTION.SUBS.ordinal->  return  "-"
            ACTION.MULTI.ordinal ->return  "*"
            ACTION.DIVIDE.ordinal->return  "/"

        }
        return ""
    }

}

