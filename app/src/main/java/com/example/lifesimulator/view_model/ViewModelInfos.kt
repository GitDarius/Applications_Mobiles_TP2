package com.example.lifesimulator.view_model

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lifesimulator.model.Personne
import com.example.lifesimulator.view.FragmentInfosPersonne

class ViewModelInfos : ViewModel() {

    var fragmentInfos: FragmentInfosPersonne? = null
    var personneOriginale: MutableLiveData<Personne> = MutableLiveData(null)


    fun changerPersonne(personne: Personne){
        personneOriginale.value = personne
        fragmentInfos!!.view?.visibility = View.VISIBLE
    }

    fun sauvegarder(nom: String){
        val personne = personneOriginale.value!!
        if(nom.isEmpty()){
            Log.i("Snack", "Nom peut pas être vide")
        }else{
            personne.nom = nom
        }
        fragmentInfos!!.view?.visibility = View.GONE
    }

    fun partir(){
        fragmentInfos!!.view?.visibility = View.GONE
    }



}