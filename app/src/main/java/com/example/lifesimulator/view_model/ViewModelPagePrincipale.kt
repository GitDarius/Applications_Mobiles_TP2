package com.example.lifesimulator.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lifesimulator.view.AfficPersonneAdapter
import com.example.lifesimulator.view.BureauAdapter
import com.example.lifesimulator.model.Constantes
import com.example.lifesimulator.model.Genre
import com.example.lifesimulator.model.Model
import com.example.lifesimulator.model.Outils.personne
import com.example.lifesimulator.model.Personne
import com.google.android.material.snackbar.Snackbar

class ViewModelPagePrincipale: ViewModel() {

    var listePersonnes = MutableLiveData(Model.listePersonnes)
    var listePersonnesBureau = MutableLiveData(mutableListOf<Personne>())

    //Adapteurs des fragments pour facilement dire ce qui a changé
    var adapteurBureau: BureauAdapter? = null
    var adapterListePersonnes: AfficPersonneAdapter? = null

    private val _snackbarMessage = MutableLiveData<String>()
    val snackbarMessage: LiveData<String> get() = _snackbarMessage

    fun showSnackbar(message: String?) {
        if(message != null){
            _snackbarMessage.value = message!!
        }
    }

    fun ajouterBureau(personne: Personne){
        if(listePersonnesBureau.value!!.size >= Constantes.LIMITE__PERSONNES_BUREAU){
            showSnackbar("Bureau Plein")
        }else{
            //On la rajoute au bureau
            if(adapteurBureau != null){
                listePersonnesBureau.value!!.add(personne)
                adapteurBureau!!.notifyItemInserted(listePersonnesBureau.value!!.size-1)
                showSnackbar("${personne.nom} ajoutée au bureau")
            }else{
                Log.i("Tag", "Adapteur bureau pas reconnu")
            }
            //On l'enlève de la liste des personnes
            if(adapterListePersonnes != null){
                val index = listePersonnes.value!!.indexOf(personne)
                listePersonnes.value!!.removeAt(index)
                adapterListePersonnes!!.notifyItemRemoved(index)
            }

        }
    }

    fun partirBureau(personne: Personne){
        val index = listePersonnesBureau.value!!.indexOf(personne)
        listePersonnesBureau.value!!.removeAt(index)
        adapteurBureau!!.notifyItemRemoved(index)
        listePersonnes.value!!.add(personne)
        adapterListePersonnes!!.notifyItemInserted(listePersonnes.value!!.size-1)
    }

    fun marier(){
        //Je gère de multiples exceptions

        //Il faut 2 personnes
        if(listePersonnesBureau.value!!.size != 2){
            showSnackbar("Besoin de 2 personnes pour marier")
            return
        }

        val personne1 = listePersonnesBureau.value!![0]
        val personne2 = listePersonnesBureau.value!![1]

        //Si au moins l'un des deux est marié, je creuse un peu pour un message plus concret
        when {
            personne1.conjointId != null && personne2.conjointId != null -> {
                if (personne1.conjointId == personne2.id) {
                    showSnackbar("Les 2 forment déjà un heureux couple")
                } else {
                    showSnackbar("Les 2 sont déjà mariés à d'autres personnes")
                }
                return
            }
            personne1.conjointId != null -> {
                showSnackbar("${personne1.nom} est déjà marié à ${personne(personne1.conjointId!!)!!.nom}")
                return
            }
            personne2.conjointId != null -> {
                showSnackbar("${personne2.nom} est déjà marié à ${personne(personne2.conjointId!!)!!.nom}")
                return
            }
        }

        //Pour éviter qu'un fils se marie avec sa tante ou sa soeur
        if(personne1.famille != null && personne1.famille == personne2.famille){
            showSnackbar("Les 2 doivent ne peuvent pas être de la même famille")
            return
        }

        //Au moins 18 ans pour se marier
        when{
            personne1.age < 15 && personne2.age < 15 -> {
                showSnackbar("${personne1.nom} et ${personne2.nom} sont trop jeunes")
                return
            }
            personne1.age < 15 -> {
                showSnackbar("${personne1.nom} est trop jeune")
                return
            }
            personne2.age < 15 -> {
                showSnackbar("${personne1.nom} est trop jeune")
                return
            }
        }

        //S'ils ont le même genre, ils ne peuvent se marier
        if(personne1.genre == personne2.genre){
            showSnackbar("Autoriser celle liaison serait un danger pour la survie de la population (selon Kant)")
        }

        val homme = if (personne1.genre == Genre.F) personne2 else personne1
        val femme = if (personne1.genre == Genre.M) personne2 else personne1

        homme.marier(femme)

        showSnackbar("Le mariage fût un succès!")
        adapteurBureau!!.notifyItemRangeChanged(0, 2)
    }

    fun procreer(){
        //Je gère de multiples exceptions

        //Il faut 2 personnes
        if(listePersonnesBureau.value!!.size != 2){
            showSnackbar("Besoin de 2 personnes pour marier")
            return
        }

        val personne1 = listePersonnesBureau.value!![0]
        val personne2 = listePersonnesBureau.value!![1]


        //Il faut qu'elles soient mariées.
        if(personne1.conjointId != personne2.id){
            showSnackbar("${personne1.nom} et ${personne2.nom} ne sont pas en couple")
            return
        }

        //Il faut une femme et un homme
        if(personne1.genre == personne2.genre){
            showSnackbar("Naturellement impossible vu le genre")
            return
        }

        val mere = if (personne1.genre == Genre.F) personne1 else personne2
        val pere = if (personne1.genre == Genre.M) personne1 else personne2

        //Je vérifie l'âge
        when{
            mere.age < 18 -> {
                showSnackbar("La mère est trop jeune")
                return
            }
            mere.age > 55 -> {
                showSnackbar("La mère est trop vielle")
                return
            }
        }

        pere.procreer()
        adapterListePersonnes!!.notifyItemInserted(listePersonnes.value!!.size-1)

    }

    fun passerAnnee(){
        Model.anneeActuelle += 1
        for(personne in Model.listeToutesPersonnes){
            if(personne.enVie){
                personne.passerAnnee()
            }
        }
        adapteurBureau!!.notifyItemRangeChanged(0, listePersonnesBureau.value!!.size)
        adapterListePersonnes!!.notifyItemRangeChanged(0, listePersonnes.value!!.size)
    }


}