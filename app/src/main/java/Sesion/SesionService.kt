package Sesion

import Usuario.Usuario
import Usuario.UsuarioDAO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SesionService {
    // Instancia de Retrofit
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.43.159:8080/ServidorREST/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //Método para ver todos las sesiones
    fun getSesiones() : Call<List<Sesion>> {
        return getRetrofit().create(SesionDAO::class.java).getSesiones()
    }
}