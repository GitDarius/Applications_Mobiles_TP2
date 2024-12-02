package com.example.lifesimulator.model

import android.graphics.drawable.Drawable
import android.util.Log
import com.example.lifesimulator.R
import com.example.lifesimulator.model.Outils.exp
import com.example.lifesimulator.model.Outils.nouveauNom
import com.example.lifesimulator.model.Outils.personne
import kotlin.random.Random

data class Personne(
    val id: Int,
    var enVie: Boolean,
    var nom: String,
    var image: String,
    var conjointId: Int?,
    var genre: Genre,
    var age: Int,
    val enfants: MutableList<Int>,
    var pereId: Int?,
    var famille: String?
){
    fun marier(conjoint: Personne?){
        //Le marriage va dans les 2 sens
        if(conjoint != null){
            this.conjointId = conjoint.id
            conjoint.conjointId = id
            if(famille != null){
                conjoint.famille = famille
            }else{
                val nouvelleFamille = "${nom}-${conjoint.nom}"
                famille = nouvelleFamille
                conjoint.famille = nouvelleFamille
            }
        }else{
            Log.i("TAG", "Conjoint pas trouvé")
        }

    }

    fun procreer(){
        val genre = Genre.entries.random()
        val bebe = Personne(
            id = Outils.creerId(),
            enVie = true,
            nom = nouveauNom(genre),
            image = R.drawable.test.toString(),
            conjointId = null,
            genre = genre,
            age = 0,
            enfants = mutableListOf(),
            pereId = this.id,
            famille = this.famille

        )
        enfants.add(bebe.id)
        Model.listePersonnes.add(bebe)
        Model.listeToutesPersonnes.add(bebe)
    }

    fun passerAnnee(){
        //Théoriquement, le max serait 190 ans, la moyenne est d'environ 90 ans
        if(exp(age, 4.0) > Random.nextInt(0, exp(101, 4.55))){
            enVie = false
        }
        age += 1
    }

}
