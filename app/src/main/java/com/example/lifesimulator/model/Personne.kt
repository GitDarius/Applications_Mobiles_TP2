package com.example.lifesimulator.model

import android.util.Log
import com.example.lifesimulator.R
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
    var generation: Int
){
    fun marier(conjointId: Int){
        //Le marriage va dans les 2 sens
        this.conjointId = conjointId
        val conjoint = personne(conjointId)

        if(conjoint != null){
            conjoint.conjointId = id
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
            mutableListOf(),
            this.id,
            generation = generation+1
        )
        enfants.add(bebe.id)
        Model.listePersonnes.add(bebe)
        Model.listeToutesPersonnes.add(bebe)
    }

}
