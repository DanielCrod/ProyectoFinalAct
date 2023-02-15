package Sesion

import Usuario.Usuario
import retrofit2.http.GET

interface SesionDAO {
    // Método para obtener todos las sesiones
    @GET("sesiones")
    fun getSesiones(): retrofit2.Call<List<Sesion>>
}