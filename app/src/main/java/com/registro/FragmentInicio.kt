package com.registro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.registro.databinding.FragmentInicioBinding

class FragmentInicio : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val db = Firebase.firestore

        val binding= FragmentInicioBinding.inflate(layoutInflater)

        db.collection("materias").get().addOnSuccessListener { it ->
            var materias = arrayListOf<Materias>()
            it.documents.forEach { its ->
                materias.add(
                    Materias(
                        its.get("clave").toString(),
                        its.get("nombreMat").toString(),
                        its.get("horasT").toString()
                    )
                )
            }
            var customAdapter=CustomAdapter(binding.root.context, materias)
            binding.recycler.adapter=customAdapter
            val layoutManager = GridLayoutManager(binding.root.context, 2)
            binding.recycler.layoutManager=layoutManager

        }
            .addOnFailureListener {
                Toast.makeText(binding.root.context, "no se pudo", Toast.LENGTH_SHORT).show()
            }
        binding.fab.setOnClickListener {
            it.findNavController().navigate(R.id.action_fragmentInicio_to_agregarFragment)
        }
        return binding.root
    }

}