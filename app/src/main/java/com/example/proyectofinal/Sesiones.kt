package com.example.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Sesiones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sesiones)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        var fragment = SesionesFragment()

        fragmentTransaction.replace(R.id.frameSesiones, fragment)
        fragmentTransaction.commit()
    }
}