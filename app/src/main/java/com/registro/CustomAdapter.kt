package com.registro

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CustomAdapter(private var context: Context, var usuaioss: ArrayList<Materias>?) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private val db = Firebase.firestore
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.item_materias, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        viewHolder: ViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        viewHolder.textView.text = "clave: " + usuaioss!![position].clave
        viewHolder.textView2.text = "materias: ${usuaioss!![position].nombreMat}"
        viewHolder.textView3.text = "horas teoricas: " + usuaioss!![position].horasT
        viewHolder.card.setOnClickListener {
            val bundle = bundleOf(
                "clave" to usuaioss!![position].clave,
                "materias" to usuaioss!![position].nombreMat,
                "horas" to usuaioss!![position].horasT
            )

            it.findNavController().navigate(R.id.action_fragmentInicio_to_fragmentEBMateria, bundle)
        }
        /*
            viewHolder.borrar.setOnClickListener {

                db.collection("users").document(usuaioss!![position].id.toString())
                    .delete()
                    .addOnSuccessListener {
                        Toast.makeText(context, "se borro", Toast.LENGTH_LONG).show()
                        context.startActivity(Intent(context, Inicio::class.java))

                        notifyDataSetChanged()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "error al guardar $e", Toast.LENGTH_LONG).show()
                    }
            }
            viewHolder.actualizar.setOnClickListener {
                val intent = Intent(context, EditarUsuario::class.java)
                intent.putExtra("nombre", usuaioss!![position].nombre)
                intent.putExtra("telefono", usuaioss!![position].telefono)
                intent.putExtra("correo", usuaioss!![position].correo)
                intent.putExtra("domicilio", usuaioss!![position].domicilio)
                intent.putExtra("id", usuaioss!![position].id)
                context.startActivity(intent)
                //(context as Inicio::class.java).finish()

            }*/
    }

    override fun getItemCount(): Int {
        return usuaioss!!.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val textView2: TextView
        val textView3: TextView
        var card: CardView


        init {
            // Define click listener for the ViewHolder's View
            textView = view.findViewById<View>(R.id.tclave) as TextView
            textView2 = view.findViewById<View>(R.id.tmateria) as TextView
            textView3 = view.findViewById<View>(R.id.thoras) as TextView
            card = view.findViewById(R.id.card) as CardView
        }
    }
}
