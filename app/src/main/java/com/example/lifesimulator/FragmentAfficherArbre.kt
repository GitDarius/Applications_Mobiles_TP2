package com.example.lifesimulator

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.lifesimulator.model.Outils.obtenirImage
import com.example.lifesimulator.model.Outils.personne
import com.example.lifesimulator.model.Personne
import com.example.lifesimulator.view_model.ViewModelAffArbre
import com.google.android.material.snackbar.Snackbar

class FragmentAfficherArbre : Fragment() {

    val viewModel : ViewModelAffArbre by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_afficher_arbre, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.famille.observe(viewLifecycleOwner) { nouvelleFamille ->
            if(nouvelleFamille.isNotEmpty()){
                Log.i("TAG", "Famille changée, lancer generation!")
                commencerGenerationArbre()
            }
        }
        viewModel.snackbarMessage.observe(viewLifecycleOwner) { message ->
            message?.let {
                Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
                viewModel.showSnackbar(null)
            }
        }
    }

    private fun commencerGenerationArbre(){
        val pereRacine = viewModel.trouverMembreRacine()
        if (pereRacine != null) {
            val viewRacine = view?.findViewById<LinearLayout>(R.id.aff_arbre_racine)
            viewRacine?.removeAllViews()
            viewRacine?.let { genererFeuille(pereRacine, it) }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i("TAG", "Page relancée, lancer generation!")
        commencerGenerationArbre()
    }

    private fun genererFeuille(personne: Personne, racine: View){
        // Inflate the new layout
        val feuille = LayoutInflater.from(racine.context).inflate(R.layout.arbre_feuille, racine as ViewGroup, false)

        // Update the layout content if needed (e.g., set text or images)
        val nomPere = feuille.findViewById<TextView>(R.id.feuilleNomPere)
        nomPere.text = personne.nom
        val imagePere = feuille.findViewById<ImageView>(R.id.feuilleImagePere)
        imagePere.setImageDrawable(obtenirImage(racine.context, personne.image))
        if(personne.conjointId != null){
            val mere = personne(personne.conjointId!!)!!
            val nomMere = feuille.findViewById<TextView>(R.id.feuilleNomMere)
            nomMere.text = mere.nom
            val imageMere = feuille.findViewById<ImageView>(R.id.feuilleImageMere)
            imageMere.setImageDrawable(obtenirImage(racine.context, mere.image))
        }else{
            val nomMere = feuille.findViewById<TextView>(R.id.feuilleNomMere)
            nomMere.visibility = View.GONE
            val imageMere = feuille.findViewById<ImageView>(R.id.feuilleImageMere)
            imageMere.visibility = View.GONE
        }

        if(personne.pereId == null){
            val ligneHaut = feuille.findViewById<ImageView>(R.id.feuille_ligne_haut)
            ligneHaut.visibility = View.GONE
        }

        if(personne.enfants.isEmpty()){
            val ligneBas1 = feuille.findViewById<ImageView>(R.id.feuille_ligne_bas)
            val ligneBas2 = feuille.findViewById<ImageView>(R.id.feuille_ligne_bas_horizontale)
            ligneBas1.visibility = View.GONE
            ligneBas2.visibility = View.GONE
        }


        (racine as LinearLayout).addView(feuille)

        for(enfant in personne.enfants){
            Log.i("TAG", "TAMERE ${personne(enfant)!!.nom}")
            genererFeuille(personne(enfant)!!, feuille.findViewById(R.id.feuille_enfants))
        }
    }

}