package com.example.lifesimulator.view_model

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lifesimulator.R
import com.example.lifesimulator.model.Genre
import com.example.lifesimulator.model.Model
import com.example.lifesimulator.model.Outils
import com.example.lifesimulator.model.Outils.obtenirImage
import com.example.lifesimulator.model.Outils.personne
import com.example.lifesimulator.model.Personne

class ViewModelAffArbre : ViewModel(){

    public var famille : MutableLiveData<String> = MutableLiveData("")

    private val _snackbarMessage = MutableLiveData<String>()
    val snackbarMessage: LiveData<String> get() = _snackbarMessage

    fun showSnackbar(message: String?) {
        if(message != null){
            _snackbarMessage.value = message!!
        }
    }

    fun trouverMembreRacine() : Personne?{
        if(famille.value != ""){
            val temp = Model.listeToutesPersonnes.firstOrNull { it.famille == famille.value && it.pereId == null && it.genre == Genre.M }
            if(temp == null){
                showSnackbar("Membre à la racine pas trouvé")
            }else{
                return temp
            }
        }else{
            showSnackbar("Membre à la racine pas trouvé")
        }
        return null
    }


}