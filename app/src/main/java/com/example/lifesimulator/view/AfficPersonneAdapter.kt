package com.example.lifesimulator.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lifesimulator.R
import com.example.lifesimulator.model.Genre
import com.example.lifesimulator.model.Outils.obtenirImage
import com.example.lifesimulator.model.Outils.personne
import com.example.lifesimulator.model.Personne

class AfficPersonneAdapter (
    private val ctx: Context,
    private val activity: FragmentAfficPersonnes,
    private var data: List<Personne>
) : RecyclerView.Adapter<ItemPersonneHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPersonneHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.item_personne, parent, false)
        return ItemPersonneHolder(view)
    }
    override fun onBindViewHolder(holder: ItemPersonneHolder, position: Int) {
        val personne = data[position]
        if(personne.genre == Genre.F){
            holder.fond.setImageResource(R.drawable.fond_rose)
        }else{
            holder.fond.setImageResource(R.drawable.fond_bleu)
        }

        holder.itemView.setOnClickListener {
            activity.allerBureau(personne)
        }

        holder.itemView.setOnLongClickListener {
            activity.afficherInfos(personne)
            true
        }

        holder.image.setImageDrawable(obtenirImage(ctx, personne.image))
        holder.nom.text = personne.nom
        if(personne.conjointId == null){
            holder.coeur.setImageResource(R.drawable.ic_coeur_brise)
            holder.nomConjoint.text = ""
        }else{
            holder.coeur.setImageResource(R.drawable.ic_coeur)
            holder.nomConjoint.text = personne(personne.conjointId!!)!!.nom
        }
        val ressourceString = if (personne.age == 1) R.string.age_singular else R.string.age_plural
        holder.age.text = activity.getString(ressourceString, personne.age)
        if(personne.enVie){
            holder.tombe.visibility = View.INVISIBLE
            holder.fondTombe.visibility = View.INVISIBLE
        }else{
            holder.tombe.visibility = View.VISIBLE
            holder.fondTombe.visibility = View.VISIBLE
        }
    }
    override fun getItemCount(): Int = data.size

}