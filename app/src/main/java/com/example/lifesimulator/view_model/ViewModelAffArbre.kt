package com.example.lifesimulator.view_model

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lifesimulator.R
import com.example.lifesimulator.databinding.ArbreFeuilleBinding
import com.example.lifesimulator.model.Genre
import com.example.lifesimulator.model.Model
import com.example.lifesimulator.model.Outils
import com.example.lifesimulator.model.Outils.obtenirImage
import com.example.lifesimulator.model.Outils.personne
import com.example.lifesimulator.model.Personne

class ViewModelAffArbre : ViewModel(){

    public var famille : MutableLiveData<String> = MutableLiveData("")

    fun trouverMembreRacine() : Personne?{
        if(famille.value != ""){
            val temp = Model.listeToutesPersonnes.firstOrNull { it.famille == famille.value && it.pereId == null && it.genre == Genre.M }
            if(temp == null){
                Log.i("Snack", "Membre à la racine pas trouvé")
            }else{
                return temp
            }
        }else{
            Log.i("Snack", "Aucun nom de famille")
        }
        return null
    }


}