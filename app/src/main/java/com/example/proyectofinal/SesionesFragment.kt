package com.example.proyectofinal

import Sesion.Sesion
import Sesion.SesionService
import Usuario.Usuario
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SesionesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SesionesFragment : Fragment() {
    val sesionService = SesionService()
    private lateinit var arrayList: ArrayList<Sesion>
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sesiones, container, false)
        val list = view.findViewById<ListView>(R.id.list)
        getSesiones()
        val adaptador = AdaptadorPersonalizado(requireContext(), arrayList)
        list.adapter = adaptador
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SesionesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SesionesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun getSesiones() {
        sesionService.getSesiones().enqueue(object: Callback<List<Sesion>> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(call: Call<List<Sesion>>, response: Response<List<Sesion>>) {
                if (response.isSuccessful)
                {
                    for (sesion in response.body()!!)
                        arrayList.add(sesion)

                    Log.d("a", arrayList.toString())
                } else
                {
                    Log.d("TAG", "Error")
                }
            }

            override fun onFailure(call: Call<List<Sesion>>, t: Throwable) {
                // something went completely south (like no internet connection)
                t.message?.let { Log.d("Error", it) }
            }
        })
    }
}