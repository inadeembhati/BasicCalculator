package com.example.basiccalculator

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
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
    var divider = 1
    var typeOfAction = 0
    var isFirstTime :Boolean = true
    var isEqualPressed :Boolean = false
    var isDotPressed :Boolean = false
    var isLastActionIsReset = true
    var isLastActionSign = false
    var GLOBALANSWER=0F
    var isValue1Filled = false
    var isValue2Filled = false
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
      // historyVieiw.movementMethod(ScrollingMovementMethod(true))
    }


private fun addlValue(number:Float,isDot:Boolean = false){
    isLastActionIsReset =false
    isLastActionSign =false
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
            isValue1Filled = true
        } else {
            if(isDotPressed) {
                divider = divider*10
                value2 = value2 + number/divider
            }
            else
                value2 = value2 * 10 + number

            txtAnswer.text = value2.toString()
            isValue2Filled = true
        }
    }
}
 private  fun performAction(type:Int) {
     if(isLastActionSign) {
         return
     }
     isLastActionSign = true
     isLastActionIsReset = false
     typeOfAction = type
     isDotPressed = false
     divider = 1
     if (value_number == 1)
         value_number = 2
     else
         value_number = 1

     if(isValue1Filled && isValue2Filled){
         value1 = Evaluate()
         value2 = 0F
         isValue1Filled = true;
         isValue2Filled = false
         value_number  = 2
     }

 }
    private fun Evaluate():Float{
        var answer = 0F
        when(typeOfAction){
            ACTION.ADD.ordinal -> answer = (value1+value2)
            ACTION.SUBS.ordinal-> answer = (value1-value2)
            ACTION.MULTI.ordinal ->answer = (value1*value2)
            ACTION.DIVIDE.ordinal->answer = (value1/value2)
                            else -> answer = (value1+value2)
        }
        var str2 :String =  value1.toString() + " " + getSign(typeOfAction) + " "+ value2.toString()  + " = " + answer
    historyVieiw.text = historyVieiw.text as String  + str2 +"\n"
        txtAnswer.text = answer.toString()
    return answer
    }
private  fun Equalsto(){
    if(!isValue1Filled || !isValue2Filled  )
        return
 if(isLastActionIsReset == true ) {
     return
 }
    isLastActionIsReset = false
    isFirstTime = false
    var answer :Float = 0F

    var str2 :String =  value1.toString() + " " + getSign(typeOfAction) + " "+ value2.toString()
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

    GLOBALANSWER  = answer
    var histStr:String = historyVieiw.text as String
    ResetValue()
    historyVieiw.text =  histStr+ str2+ "= " + answer.toString()+" \n "
    txtAnswer.text = answer.toString()
}


    private  fun ResetValue(){
        isLastActionIsReset = true
        isValue1Filled = false
        isValue2Filled = false
        isFirstTime = true;
        isLastActionSign = false
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



