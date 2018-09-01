package com.example.luissancar.calculadora

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var operacionTipo : String="" // 1+, 2-, 3/, 4*
    var operando : Boolean=false
    var cambioNumero : Boolean=false
    var resultadoIntermedio : Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewResultado.text="0"
        textViewBotton.text=""
        ponerBotonesOperacion(false)
    }



    fun ponerBotonesOperacion(estado : Boolean){

        buttonAdd.isEnabled=estado
        buttonSub.isEnabled=estado
        buttonMul.isEnabled=estado
        buttonDiv.isEnabled=estado
        buttonEqu.isEnabled=estado
    }
    fun numero(v : View){
        ponerBotonesOperacion(true)
        val numero=findViewById<Button>(v.id)
        if (cambioNumero){
            textViewResultado.text= ""
            cambioNumero=false
        }

        if (textViewResultado.text.toString().equals("0"))
            textViewResultado.text=numero.text.toString()
        else
            textViewResultado.text=textViewResultado.text.toString()+ numero.text.toString()
        textViewBotton.text=""
    }


    fun ce(v : View){
        operando=false
        cambioNumero=false
        resultadoIntermedio=0
        operacionTipo=""
        textViewResultado.text="0"
        ponerBotonesOperacion(false)
    }
    fun operacion(v:View){


         if (operando){
             when (operacionTipo) {
                 "+" -> resultadoIntermedio+=textViewResultado.text.toString().toInt()
                 "-" -> resultadoIntermedio-=textViewResultado.text.toString().toInt()
                 "/" -> {
                     if (textViewResultado.text.toString().toInt()==0) {
                         ce(v)
                         textViewBotton.text=resources.getText(R.string.string_error_division)
                     }
                     else
                         resultadoIntermedio /= textViewResultado.text.toString().toInt()
                 }

                 "*" -> resultadoIntermedio*=textViewResultado.text.toString().toInt()
             }
         }
         else{
             operando=true
             resultadoIntermedio=textViewResultado.text.toString().toInt()
         }

         operacionTipo=findViewById<Button>(v.id).text.toString()
         cambioNumero=true

         textViewResultado.text=resultadoIntermedio.toString()



    }
}
