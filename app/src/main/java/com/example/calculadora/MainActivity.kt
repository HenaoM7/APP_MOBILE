package com.example.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var txtResultado: TextView
    private lateinit var txtOperacion: TextView

    private var num1: Double = 0.0
    private var operacion: String = ""
    private var nuevaOperacion: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)   // ← carga el XML visual

        txtResultado = findViewById(R.id.txtResultado)
        txtOperacion = findViewById(R.id.txtOperacion)

        // Números
        findViewById<Button>(R.id.btn0).setOnClickListener { ingresarNumero("0") }
        findViewById<Button>(R.id.btn1).setOnClickListener { ingresarNumero("1") }
        findViewById<Button>(R.id.btn2).setOnClickListener { ingresarNumero("2") }
        findViewById<Button>(R.id.btn3).setOnClickListener { ingresarNumero("3") }
        findViewById<Button>(R.id.btn4).setOnClickListener { ingresarNumero("4") }
        findViewById<Button>(R.id.btn5).setOnClickListener { ingresarNumero("5") }
        findViewById<Button>(R.id.btn6).setOnClickListener { ingresarNumero("6") }
        findViewById<Button>(R.id.btn7).setOnClickListener { ingresarNumero("7") }
        findViewById<Button>(R.id.btn8).setOnClickListener { ingresarNumero("8") }
        findViewById<Button>(R.id.btn9).setOnClickListener { ingresarNumero("9") }
        findViewById<Button>(R.id.btnDecimal).setOnClickListener { ingresarDecimal() }
        findViewById<Button>(R.id.btnSumar).setOnClickListener    { ingresarOperacion("+") }
        findViewById<Button>(R.id.btnRestar).setOnClickListener   { ingresarOperacion("-") }
        findViewById<Button>(R.id.btnMultiplicar).setOnClickListener { ingresarOperacion("×") }
        findViewById<Button>(R.id.btnDividir).setOnClickListener  { ingresarOperacion("÷") }
        findViewById<Button>(R.id.btnSigno).setOnClickListener   { cambiarSigno() }
        findViewById<Button>(R.id.btnIgual).setOnClickListener   { calcularResultado() }
        findViewById<Button>(R.id.btnLimpiar).setOnClickListener { limpiar() }
    }

    private fun ingresarNumero(numero: String) {
        if (nuevaOperacion) {
            txtResultado.text = numero
            nuevaOperacion = false
        } else {
            val actual = txtResultado.text.toString()
            txtResultado.text = if (actual == "0") numero else actual + numero
        }
    }

    private fun ingresarDecimal() {
        if (nuevaOperacion) {
            txtResultado.text = "0."
            nuevaOperacion = false
        } else {
            val actual = txtResultado.text.toString()
            if (!actual.contains(".")) txtResultado.text = "$actual."
        }
    }

    private fun ingresarOperacion(op: String) {
        num1 = txtResultado.text.toString().toDoubleOrNull() ?: 0.0
        operacion = op
        txtOperacion.text = "${formatear(num1)} $op"
        nuevaOperacion = true
    }

    private fun cambiarSigno() {
        val valor = (txtResultado.text.toString().toDoubleOrNull() ?: 0.0) * -1
        txtResultado.text = formatear(valor)
    }

    private fun calcularResultado() {
        if (operacion.isEmpty()) return

        val num2 = txtResultado.text.toString().toDoubleOrNull() ?: 0.0

        if (operacion == "÷" && num2 == 0.0) {
            txtResultado.text = "Error"
            txtOperacion.text = ""
            operacion = ""
            nuevaOperacion = true
            return
        }

        val resultado = when (operacion) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "×" -> num1 * num2
            "÷" -> num1 / num2
            else -> num2
        }

        txtOperacion.text = "${formatear(num1)} $operacion ${formatear(num2)} ="
        txtResultado.text = formatear(resultado)
        operacion = ""
        nuevaOperacion = true
    }

    private fun limpiar() {
        txtResultado.text = "0"
        txtOperacion.text = ""
        num1 = 0.0
        operacion = ""
        nuevaOperacion = true
    }
    private fun formatear(valor: Double): String =
        if (valor == valor.toLong().toDouble()) valor.toLong().toString()
        else valor.toString()
}