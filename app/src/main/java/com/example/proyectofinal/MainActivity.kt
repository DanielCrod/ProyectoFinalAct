package com.example.proyectofinal


import Usuario.Usuario
import Usuario.UsuarioService
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.romainpiel.shimmer.ShimmerTextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var switch : Switch
    private lateinit var layout : RelativeLayout
    private lateinit var title : ShimmerTextView
    private lateinit var forgot : TextView
    private lateinit var account : TextView
    private lateinit var email : EditText
    private lateinit var btn : Button
    private lateinit var username : EditText
    private lateinit var password : EditText
    val serviceUsuario = UsuarioService()
    private lateinit var list : ArrayList<Usuario>


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btn = findViewById(R.id.btn)
        title = findViewById(R.id.title)
        layout = findViewById(R.id.layout)
        switch = findViewById(R.id.switch1)
        forgot = findViewById(R.id.forgot)
        account = findViewById(R.id.registertxt)
        email = findViewById(R.id.email)
        username = findViewById(R.id.user)
        password = findViewById(R.id.password)
        list = ArrayList<Usuario>()

        email.setOnClickListener(this)
        username.setOnClickListener(this)
        password.setOnClickListener(this)


        btn.setOnClickListener(this)

        switch.setOnCheckedChangeListener {
                buttonView, isChecked ->
            if (isChecked) {
                layout.setBackgroundResource(R.drawable.bb)
                title.setText("Register")
                btn.setText("Register")
                forgot.setText("")
                account.setText("")
                email.isVisible = true
            } else {
                layout.setBackgroundResource(R.drawable.aa)
                title.setText("Login")
                btn.setText("Login")
                forgot.setText("Forgot your password?")
                account.setText("Don't have an account yet?")
                email.isVisible = false
            }
        }

        //Llamamos al método para rellenar el arraylist
        getLibros()


    }

    override fun onClick(v: View?) {
        val user = username.text.toString().trim()
        val pass = password.text.toString().trim()
        if(user.isEmpty()) {
            username.error = "Username required"
        } else if (pass.isEmpty()){
            password.error = "Password required"
        } else {
            if (btn.text == "Login") {
                try {
                    if (list.any { it.nombre == username.text.toString() && it.contraseña == password.text.toString() }) {
                        //Condición si ese usuario está en el arrayList
                        Toast.makeText(this, "Usuario encontrado", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, Sesiones::class.java)
                        startActivity(intent)
                    } else {
                        //Condición si ese usuario no está en el arrayList
                        Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
                    }
                } catch (E : java.lang.Exception) {
                    Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_LONG).show()
                }
            } else if (password.text.toString().length < 6){
                    password.error = "Mínimo 6 caracteres"
            } else {
                try {
                    val usuario = Usuario(email.text.toString(),
                        username.text.toString(),
                        password.text.toString(),
                        false)
                    list.add(usuario)
                    serviceUsuario.createUsuario(usuario)
                } catch (e: java.lang.Exception) {
                    Toast.makeText(this, "Error de registro", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun getLibros() {
        serviceUsuario.getUsuarios().enqueue(object: Callback<List<Usuario>> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(call: Call<List<Usuario>>, response: Response<List<Usuario>>) {
                if (response.isSuccessful)
                {
                    for (usuario in response.body()!!)
                        list.add(usuario)
                        Log.d("a", list.toString())
                } else
                {
                    Log.d("TAG", "Error")
                }
            }

            override fun onFailure(call: Call<List<Usuario>> , t: Throwable) {
                // something went completely south (like no internet connection)
                t.message?.let { Log.d("Error", it) }
            }
        })
    }


}


