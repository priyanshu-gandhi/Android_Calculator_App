package com.example.calculator_application

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculator_application.ui.theme.Calculator_ApplicationTheme
import java.lang.ArithmeticException




class MainActivity : ComponentActivity() {
    private var tvInput :TextView?= null
    var lastNumeric :Boolean= true
    var lastDot : Boolean=false


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput =findViewById(R.id.tvInput)
    }


    fun onDigit(view: View)
    {
           tvInput?.append((view as Button).text)
           lastNumeric=true
           lastDot=false
    }

    fun onClear(view :View)
    {
        tvInput?.text=""
    }

    fun onDecimalPoint(view: View)
    {
        if(lastNumeric  && !lastDot)
        {
            tvInput?.append(".")
            lastNumeric=false
            lastDot=true
        }
    }

    fun onEqual(view: View)
    {
        var tvValue = tvInput?.text.toString()
        var prefix=""
        try {

            if (lastNumeric) {
               if(tvValue.startsWith("-"))
               {
                   prefix="-"
                   tvValue=tvValue.substring(1)
               }
                if(tvValue.contains("-"))
                {
                    var splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty())
                    {
                        one= prefix+one
                    }
                    var result = one.toDouble() - two.toDouble()
                    tvInput?.text = removeZeroAfterDot(result.toString())

                }

                else if (tvValue.contains("*"))
                    {
                        var splitValue = tvValue.split("*")
                        var one = splitValue[0]
                        var two = splitValue[1]


                        var result = one.toDouble() * two.toDouble()
                        tvInput?.text = removeZeroAfterDot(result.toString())

                    }
                else if (tvValue.contains("+"))
                {
                    var splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]


                    var result = one.toDouble() + two.toDouble()
                    tvInput?.text = removeZeroAfterDot(result.toString())

                }
                else if (tvValue.contains("/"))
                {
                    var splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]


                    var result = one.toDouble() / two.toDouble()
                    tvInput?.text = removeZeroAfterDot(result.toString())

                }

                }


            }
        catch (exception :ArithmeticException)
        {
             exception.printStackTrace()
        }
    }
     var sol:String=""
    private fun removeZeroAfterDot(value :String): String{
        if(value.contains(".0")) {
            sol = value.substring(0, value.length - 2)
        }
            return sol


    }

    fun onOperator(view: View)
    {
        if(lastNumeric && !isOperatorAdded(tvInput?.text.toString()))
        {
            tvInput?.append((view as Button).text)
            lastNumeric=false
            lastDot=false
        }
    }

    fun isOperatorAdded(value :String): Boolean{
        return  if(value.startsWith("-"))
        {
            false
        }
        else
        {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}



