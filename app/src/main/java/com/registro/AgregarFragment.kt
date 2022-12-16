package com.registro

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.registro.databinding.FragmentAgregarBinding


class AgregarFragment : Fragment() {

    val db = Firebase.firestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=FragmentAgregarBinding.inflate(layoutInflater)

        binding.button.setOnClickListener {
            val user = Materias(
                binding.clave.text.toString(),
                binding.mater.text.toString(),
                binding.hors.text.toString()
            )
            db.collection("materias").document(binding.clave.text.toString()).set(user).addOnSuccessListener {
                Toast.makeText(binding.root.context, "materia guardada con exito", Toast.LENGTH_SHORT)
                    .show()
                binding.clave.setText("")
                binding.mater.setText("")
                binding.hors.setText("")
            }.addOnFailureListener {
                Toast.makeText(binding.root.context, "la materia no se guardo", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        return binding.root
    }

}