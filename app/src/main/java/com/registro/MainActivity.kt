package com.registro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.registro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
/*        val mat = Materias(
            "casd",
            "asd",
            "2"
        )

// Add a new document with a generated ID
        db.collection("materias")
            .add(mat)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(applicationContext, "gg", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
            }*/
    }

    fun onClick(view: View) {}
}