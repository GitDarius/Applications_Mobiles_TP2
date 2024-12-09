package com.example.lifesimulator.view_model

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.lifesimulator.FragmentAjouterPersonne
import com.example.lifesimulator.R
import com.example.lifesimulator.model.Genre
import com.example.lifesimulator.model.Model
import com.example.lifesimulator.model.Outils
import com.example.lifesimulator.model.Outils.nouveauNom
import com.example.lifesimulator.model.Outils.obtenirImage
import com.example.lifesimulator.model.Personne
import kotlin.random.Random

class ViewModelAjoutPersonne: ViewModel() {

    var fragmentAjouterPersonne: FragmentAjouterPersonne? = null

    fun partir(){
        fragmentAjouterPersonne!!.view?.visibility = View.GONE
    }

    fun ouvrir(){
        fragmentAjouterPersonne!!.view?.visibility = View.VISIBLE
    }

    fun nomValide(nom:String):Boolean{
        if(nom.isEmpty()){
            return false
        }
        return true
    }

    fun ageValide(age:String):Boolean{
        if(age.isEmpty()){
            return false
        }
        val ageInt = age.toInt()
        if(ageInt < 0){
            return false
        }
        return true
    }

    fun sauvegarder(nom:String, age:String, genre: String, image:String){
        val genreTemp = Genre.valueOf(genre)

        val bebe = Personne(
            id = Outils.creerId(),
            enVie = true,
            nom = nom,
            image = image,
            conjointId = null,
            genre = genreTemp,
            age = age.toInt(),
            enfants = mutableListOf(),
            pereId = null,
            famille = null
        )
        Model.listePersonnes.add(bebe)
        Model.listeToutesPersonnes.add(bebe)


        partir()
    }

    fun nomAleatoire(genre: String?):String{
        return if(genre == null){
            nouveauNom(Genre.entries.toTypedArray().random())
        }else{
            nouveauNom(Genre.valueOf(genre))
        }
    }

    fun ageAleatoire():String{
        return Random.nextInt(0, 190).toString()
    }

}