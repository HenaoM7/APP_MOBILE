package com.bedoya.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // 0 = nada, 1 = suma, 2 = resta, 3 = multiplicación, 4 = división
    var operacion: Int = 0
    var primerNumero: Double = 0.0

    lateinit var textoArriba: TextView  // muestra la operación anterior (ej: 5+)
    lateinit var textoAbajo: TextView   // muestra el número que se está escribiendo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        textoAbajo  = findViewById(R.id.textoAbajo)
        textoArriba = findViewById(R.id.textoArriba)

        val botonBorrar : Button = findViewById(R.id.botonBorrar)
        val botonIgual  : Button = findViewById(R.id.botonIgual)

        // Al presionar "=" calcula el resultado
        botonIgual.setOnClickListener {
            val segundoNumero: Double = textoAbajo.text.toString().toDouble()
            var resultado: Double = 0.0

            if (operacion != 0) {
                when (operacion) {
                    1 -> resultado = primerNumero + segundoNumero
                    2 -> resultado = primerNumero - segundoNumero
                    3 -> resultado = primerNumero * segundoNumero
                    4 -> resultado = primerNumero / segundoNumero
                }
                textoAbajo.text  = resultado.toString()
                textoArriba.text = ""
            }
        }

        // Al presionar "C" limpia todo
        botonBorrar.setOnClickListener {
            textoArriba.text = ""
            textoAbajo.text  = ""
            primerNumero = 0.0
            operacion    = 0
        }
    }

    // Se llama cuando el usuario toca un número o el punto
    fun presionarDigito(view: View) {
        val numeroActual: String = textoAbajo.text.toString()

        when (view.id) {
            R.id.boton0 -> textoAbajo.text = numeroActual + "0"
            R.id.boton1 -> textoAbajo.text = numeroActual + "1"
            R.id.boton2 -> textoAbajo.text = numeroActual + "2"
            R.id.boton3 -> textoAbajo.text = numeroActual + "3"
            R.id.boton4 -> textoAbajo.text = numeroActual + "4"
            R.id.boton5 -> textoAbajo.text = numeroActual + "5"
            R.id.boton6 -> textoAbajo.text = numeroActual + "6"
            R.id.boton7 -> textoAbajo.text = numeroActual + "7"
            R.id.boton8 -> textoAbajo.text = numeroActual + "8"
            R.id.boton9 -> textoAbajo.text = numeroActual + "9"
            R.id.botonPunto -> textoAbajo.text = numeroActual + "."
        }
    }

    // Se llama cuando el usuario toca +  -  x  /
    fun clickOperacion(view: View) {
        primerNumero = textoAbajo.text.toString().toDouble()
        val numeroMostrado: String = textoAbajo.text.toString()
        textoAbajo.setText("")

        when (view.id) {
            R.id.botonSumar       -> { textoArriba.setText(numeroMostrado + "+"); operacion = 1 }
            R.id.botonRestar      -> { textoArriba.setText(numeroMostrado + "-"); operacion = 2 }
            R.id.botonMultiplicar -> { textoArriba.setText(numeroMostrado + "x"); operacion = 3 }
            R.id.botonDividir     -> { textoArriba.setText(numeroMostrado + "/"); operacion = 4 }
        }
    }
}