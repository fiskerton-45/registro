package com.registro

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.registro.databinding.FragmentEBMateriaBinding
import com.registro.databinding.FragmentInicioBinding

class FragmentEBMateria : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val db = Firebase.firestore

        val binding= FragmentEBMateriaBinding.inflate(layoutInflater)

        val claveId:String=arguments?.getString("clave").toString()

        binding.clave.setText(arguments?.getString("clave") )
        binding.mater.setText(arguments?.getString("materias") )
        binding.hors.setText(arguments?.getString("horas") )
        binding.button.setOnClickListener {

            db.collection("materias").document(claveId).set(
                Materias(
                    binding.clave.text.toString(),
                    binding.mater.text.toString(),
                    binding.hors.text.toString()
                )
            )
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(binding.root.context, "materia actualizada", Toast.LENGTH_LONG)
                        .show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(binding.root.context, "error al guardar $e", Toast.LENGTH_LONG)
                        .show()
                }
        }
        binding.button2.setOnClickListener {
            db.collection("materias").document(claveId)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(binding.root.context, "se borro"+claveId, Toast.LENGTH_LONG).show()
                    startActivity(Intent(binding.root.context, MainActivity::class.java))
                }
                .addOnFailureListener { e ->
                    Toast.makeText(binding.root.context, "error al guardar $e", Toast.LENGTH_LONG)
                        .show()
                }
        }
        return binding.root
    }

}