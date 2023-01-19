package com.example.lecturaescrituraficheros

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {

    var textoMultiple : EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       textoMultiple = findViewById(R.id.editTextTextMultiLine)
        if (existeArchivo(fileList(), nombreArchivo = "archivo.txt")) {
            var contenido = ""
            var nombreArchivo = InputStreamReader(openFileInput("archivo.txt"))
            var buffer = BufferedReader(nombreArchivo)
            var line = buffer.readLine()
            while (line != null) {
                contenido = contenido + line + "\n"
                line = buffer.readLine()
            }
            textoMultiple?.setText(contenido)
        }


    }
    fun existeArchivo(archivos: Array<String>, nombreArchivo: String): Boolean {
        archivos.forEach {
            if (nombreArchivo == it) {
                return true
            }
        }
        return false
    }
    fun guardarArchivo(vista: View) {
        val nombreArchivo = OutputStreamWriter(openFileOutput("archivo.txt", Activity.MODE_PRIVATE))
        nombreArchivo.write(textoMultiple?.text.toString())
        //limipa cache
        nombreArchivo.flush()
        nombreArchivo.close()
        finish()
    }

}