package com.example.lifesimulator.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.lifesimulator.R

class ItemPersonneHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val layout: ConstraintLayout = itemView as ConstraintLayout
    val fond: ImageView = itemView.findViewById(R.id.itemPersonneFondGris)
    val image: ImageView = itemView.findViewById(R.id.itemPersonneImage)
    val nom: TextView = itemView.findViewById(R.id.itemPersonneNom)
    val coeur: ImageView = itemView.findViewById(R.id.itemPersonneCoeur)
    val nomConjoint: TextView = itemView.findViewById(R.id.itemPersonneNomConjoint)
    val age: TextView = itemView.findViewById(R.id.itemPersonneAge)
}