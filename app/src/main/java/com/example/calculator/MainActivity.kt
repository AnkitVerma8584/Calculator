package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private var expression = ""
    private var openBracket = false
    private var countBracket = 0
    private var isdotpresent = false
    private var isoperator = false
    private val operators="+-*÷"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_clear.setOnClickListener{
            clearAll()
        }
        backspace.setOnClickListener {
            expression= tv_expression.text.toString()
            if(expression.length>1){
                checkExpression(expression[expression.length-1])
                expression=expression.substring(0,expression.length-1)
                tv_expression.text= expression
            }
            else
                clearAll()
        }
        btn_0.setOnClickListener{
            if(expression!="0") {
                expression += "0"
                tv_expression.text = expression
            }
            isoperator=false
            if(countBracket!=0)
                openBracket=true
        }
        btn_1.setOnClickListener {
            if(expression!=""&&expression[expression.length-1]==')')
            {
                expression+="*"
            }
            expression+="1"
            tv_expression.text= expression
            isoperator=false
            if(countBracket!=0)
                openBracket=true
        }
        btn_2.setOnClickListener {
            if(expression!=""&&expression[expression.length-1]==')')
            {
                expression+="*"
            }
            expression+="2"
            tv_expression.text= expression
            isoperator=false
            if(countBracket!=0)
                openBracket=true
        }
        btn_3.setOnClickListener {
            if(expression!=""&&expression[expression.length-1]==')')
            {
                expression+="*"
            }
            expression+="3"
            tv_expression.text= expression
            isoperator=false
            if(countBracket!=0)
                openBracket=true
        }
        btn_4.setOnClickListener{
            if(expression!=""&&expression[expression.length-1]==')')
            {
                expression+="*"
            }
            expression+="4"
            tv_expression.text= expression
            isoperator=false
            if(countBracket!=0)
                openBracket=true
        }
        btn_5.setOnClickListener{

            if(expression!=""&&expression[expression.length-1]==')')
            {
                expression+="*"
            }
            expression+="5"
            tv_expression.text= expression
            isoperator=false
            if(countBracket!=0)
                openBracket=true
        }
        btn_6.setOnClickListener{
            if(expression!=""&&expression[expression.length-1]==')')
            {
                expression+="*"
            }
            expression+="6"
            tv_expression.text= expression
            isoperator=false
            if(countBracket!=0)
                openBracket=true
        }
        btn_7.setOnClickListener{
            if(expression!=""&&expression[expression.length-1]==')')
            {
                expression+="*"
            }
            expression+="7"
            tv_expression.text= expression
            isoperator=false
            if(countBracket!=0)
                openBracket=true
        }
        btn_8.setOnClickListener{
            if(expression!=""&&expression[expression.length-1]==')')
            {
                expression+="*"
            }
            expression+="8"
            tv_expression.text= expression
            isoperator=false
            if(countBracket!=0)
                openBracket=true
        }
        btn_9.setOnClickListener{
            if(expression!=""&&expression[expression.length-1]==')')
            {
                expression+="*"
            }
            expression+="9"
            tv_expression.text= expression
            isoperator=false
            if(countBracket!=0)
                openBracket=true
        }
        btn_dot.setOnClickListener{
            if (expression!=""&&!isoperator&&!isdotpresent&&expression[expression.length-1]!=')')
                    expression+="."
            else if(isoperator|| openBracket)
                expression+="0."
            tv_expression.text= expression
            isdotpresent=true

            if(countBracket!=0)
                openBracket=true
        }

        btn_add.setOnClickListener {
            if (expression != ""&& !isoperator&& expression[expression.length-1]!='('){
                expression += "+"
                isoperator=true

                if(isdotpresent)
                    isdotpresent=false
            }
            tv_expression.text = expression
        }
        btn_subtract.setOnClickListener {
            if (expression != ""&& !isoperator&& expression[expression.length-1]!='('){
                expression += "-"
                isoperator=true

                if(isdotpresent)
                    isdotpresent=false
            }
            tv_expression.text = expression
        }
        btn_multiply.setOnClickListener {
            if (expression != "" && !isoperator&& expression[expression.length-1]!='(') {
                expression += "*"
                isoperator = true

                if(isdotpresent)
                    isdotpresent=false
            }
            tv_expression.text = expression
        }
        btn_divide.setOnClickListener {
            if (expression != ""&& !isoperator&& expression[expression.length-1]!='(') {
                expression += "÷"
                isoperator = true

                if(isdotpresent)
                    isdotpresent=false
            }
            tv_expression.text = expression
        }

        btn_percentage.setOnClickListener {
            if(expression!="" && expression[expression.length-1]!='(')
            {
                expression+="%"
                tv_expression.text= expression
            }

        }
        btn_negate.setOnClickListener{
            if(expression==""){
                expression+="(-"
                openBracket=true
                countBracket++
                isoperator=true
            }
            else if(expression[expression.length-1]>='0'||expression[expression.length-1]<='9')
            {
                var num=""
                outer@for(i in expression.length-1 downTo 0)
                {
                    val ch= expression[i]
                    if(operators.indexOf(ch)>-1&&expression[i-1]!='(')
                    {
                        expression=expression.substring(0,i+1)+"(-"+num
                        countBracket++
                        openBracket=true
                        break@outer
                    }
                    else if (ch=='-'&&expression[i-1]=='(')
                    {
                        expression=expression.substring(0,i-1)+num
                        countBracket--
                        if(countBracket==0)
                            openBracket==false

                        break@outer
                    }
                    if(((ch>='0') && (ch<='9'))||ch=='.')
                    {
                        num=ch+num
                    }
                    if(i==0&&num!="")
                    {
                        expression= "(-$num"
                        countBracket++
                        openBracket=true
                    }
                }
            }
            else if(operators.indexOf(expression[expression.length-1])>-1)
            { expression+= "(-"
                countBracket++
                openBracket=true
            }
            else if(expression[expression.length-1]=='(')
            {
                expression+="-"
            }
            else if(expression[expression.length-1]==')')
            {
                expression+="*(-"
            }

            tv_expression.text=expression
        }

        btn_bracket.setOnClickListener{
            if(openBracket&&!isoperator&&countBracket!=0)
            {
                expression+=")"
                countBracket--
                if(countBracket==0)
                    openBracket=false
                tv_expression.text=expression
            }
            else if(expression!="")
            {
                expression += if(isoperator)
                    "("
                else if(expression[expression.length-1]==')'&& countBracket>0)
                    ")"
                else if(expression[expression.length-1]=='(')
                    "("
                else
                    "*("
                countBracket++
                tv_expression.text=expression
            }
            if(expression=="")
            {
                expression+="("
                countBracket++
                tv_expression.text=expression
            }
        }

        //Calculate
        btn_equal.setOnClickListener {
            calculate(expression)
        }
    }

    private fun calculate( exp : String)
    {   var result:String
        var variable=""
        for(i in exp.indices)
        {
            when (val char  = exp[i]) {
                '÷' -> variable+="/"
                '%' -> variable+="/100"
                else -> variable +=char
            }
        }
        val rhino: Context = Context.enter()
        rhino.optimizationLevel=-1
        val scriptable:Scriptable
        try {
            scriptable = rhino.initSafeStandardObjects()
            result = rhino.evaluateString(scriptable,variable,"javascript",1,null).toString()
        }catch ( exp: Exception)
        {
            result = "Invalid Format"
        }

        tv_result.text= result

    }
    private fun checkExpression(exp : Char){
        when (exp){
            '(' -> {
                countBracket--
                if(countBracket==0)
                    openBracket=false

            }
            '+','-','*','÷' -> {
                isoperator=false
            }
            '.' -> {
                isdotpresent=false
            }

        }


    }
    private fun clearAll()
    {
        tv_result.text=""
        tv_expression.text=""
        expression=""
        openBracket=false
        countBracket=0
        isdotpresent=false

    }
}


