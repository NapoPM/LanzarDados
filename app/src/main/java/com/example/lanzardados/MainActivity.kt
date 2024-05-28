package com.example.lanzardados


import android.media.Image
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnLanzar: Button = findViewById(R.id.btnLanzar)

        btnLanzar.setOnClickListener{
            tiempo()

            btnLanzar.animate().apply{
                duration = 1000
                rotationXBy(360f)
            }.start() 
        }
        rollDice()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    private fun rollDice() {
        val numero: Int = lanzar(6)
        val txtResult: TextView = findViewById(R.id.txtResultado)
        txtResult.text = numero.toString()


        val drawbleResourse = when(numero){
            1 -> R.drawable.cara1
            2 -> R.drawable.cara2
            3 -> R.drawable.cara3
            4 -> R.drawable.cara4
            5 -> R.drawable.cara5
            else -> R.drawable.cara6
        }

        val imgDado: ImageView = findViewById(R.id.imgDado)
        imgDado.setImageResource(drawbleResourse)


    }

    private fun lanzar(max: Int): Int {
        return (1..max).random()
    }

    private fun tiempo(){
        object: CountDownTimer(3000,200){
            override fun onTick(millisUntilFinished: Long) {
                rollDice()
            }

            override fun onFinish() {

            }

        }.start()
    }
}
