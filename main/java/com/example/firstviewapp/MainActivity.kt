package com.example.firstviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.time.times


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val math_operation = findViewById<TextView>(R.id.ResultText)
        val hystoryTextView = findViewById<TextView>(R.id.HistoryTextView)
        val bt0 = findViewById<TextView>(R.id.num0)
        val bt1 = findViewById<TextView>(R.id.num1)
        val bt2 = findViewById<TextView>(R.id.num2)
        val bt3 = findViewById<TextView>(R.id.num3)
        val bt4 = findViewById<TextView>(R.id.num4)
        val bt5 = findViewById<TextView>(R.id.num5)
        val bt6 = findViewById<TextView>(R.id.num6)
        val bt7 = findViewById<TextView>(R.id.num7)
        val bt8 = findViewById<TextView>(R.id.num8)
        val bt9 = findViewById<TextView>(R.id.num9)
        val open_scob = findViewById<TextView>(R.id.OpenScob)
        val close_scob = findViewById<TextView>(R.id.CloseScob)
        val clearall = findViewById<TextView>(R.id.ClearAll)
        val clearlastsymbol = findViewById<TextView>(R.id.ClearLastSymbol)
        val tochka = findViewById<TextView>(R.id.point)
        val ravno = findViewById<TextView>(R.id.result)
        val plus = findViewById<TextView>(R.id.plus)
        val minus = findViewById<TextView>(R.id.minus)
        val umn = findViewById<TextView>(R.id.umn)
        val del = findViewById<TextView>(R.id.del)
        bt0.setOnClickListener { put_first_number(math_operation, "0") }
        bt1.setOnClickListener { put_first_number(math_operation, "1") }
        bt2.setOnClickListener { put_first_number(math_operation, "2") }
        bt3.setOnClickListener { put_first_number(math_operation, "3") }
        bt4.setOnClickListener { put_first_number(math_operation, "4") }
        bt5.setOnClickListener { put_first_number(math_operation, "5") }
        bt6.setOnClickListener { put_first_number(math_operation, "6") }
        bt7.setOnClickListener { put_first_number(math_operation, "7") }
        bt8.setOnClickListener { put_first_number(math_operation, "8") }
        bt9.setOnClickListener { put_first_number(math_operation, "9") }
        open_scob.setOnClickListener { put_first_number(math_operation, "(") }
        close_scob.setOnClickListener { put_first_number(math_operation, ")") }

        plus.setOnClickListener { replacelastoperator(math_operation, "+") }
        minus.setOnClickListener { replacelastoperator(math_operation, "-") }
        umn.setOnClickListener { replacelastoperator(math_operation, "*") }
        del.setOnClickListener { replacelastoperator(math_operation, "/") }

        tochka.setOnClickListener { replacelastoperator(math_operation, ".") }
        clearall.setOnClickListener { math_operation.setText("0") }
        clearlastsymbol.setOnClickListener {
            var txt = math_operation.text.toString()
            math_operation.text = txt.dropLast(1)
        }
        ravno.setOnClickListener {
            val txt = math_operation.text.toString()
            try {
                var result = eval(txt)
                if (result % 10 != 0.0) {
                    math_operation.setText(result.toString().dropLast(2))
                    hystoryTextView.append("$txt=${result.toString().dropLast(2)}\n")
                } else {
                    math_operation.setText(result.toString())
                    hystoryTextView.append("$txt=$result\n")
                }
            } catch (e: Exception) {
                math_operation.setText("Ошибка")
            }
        }
    }

    fun isoperator(char: Char): Boolean {
        return char == '+' || char == '-' || char == '*' || char == '/'
    }

    fun replacelastoperator(textView: TextView, operator: String) {
        val txt = textView.text.toString()
        if (isoperator(txt.last()) || txt.last() == '.') {
            textView.text = txt.dropLast(1) + operator
        } else {
            textView.append(operator)
        }
    }

    fun put_first_number(textView: TextView, number: String) {
        val txt = textView.text.toString()
        if (txt == "0" || txt == "Ошибка" || txt == "Infinity") {
            textView.text = number
        } else textView.append(number)
    }

    fun eval(txt1: String): Double {
        val txt = txt1.replace("[^\\d+\\-*/.]".toRegex(), "")
        val numbers = txt.split("""[+\-*/]""".toRegex()).map { it.toDouble() }
        val operators = txt1.filter { it in setOf('+', '-', '*', '/') }

        var result = numbers[0]
        var index = 1

        for (operator in operators) {
            val nextNumber = numbers[index]
            when (operator) {
                '+' -> result += nextNumber
                '-' -> result -= nextNumber
                '*' -> result *= nextNumber
                '/' -> result /= nextNumber
            }
            index++
        }
        return result
    }

}



