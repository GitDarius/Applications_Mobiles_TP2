package com.example.lifesimulator.view

import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.lifesimulator.R
import com.example.lifesimulator.model.Genre
import com.example.lifesimulator.model.Outils.obtenirImage
import com.example.lifesimulator.view_model.ViewModelInfos
import com.example.lifesimulator.view_model.ViewModelPagePrincipale

class FragmentInfosPersonne : Fragment() {

    private val viewModel : ViewModelInfos by activityViewModels()

    private lateinit var saisieNom: EditText
    private lateinit var imageView: ImageView
    private lateinit var ageView: TextView
    private lateinit var familyView: TextView
    private lateinit var genreView: TextView

    private lateinit var boutonSauvegarder: Button
    private lateinit var boutonPartir: Button
    private lateinit var imagePartir: ImageView
    private lateinit var imageAleatoire: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_infos_personne, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fragmentInfos = this

        saisieNom = view.findViewById(R.id.infosNom)
        imageView = view.findViewById(R.id.infosImage)
        ageView = view.findViewById(R.id.infosAge)
        familyView = view.findViewById(R.id.infosFamille)
        genreView = view.findViewById(R.id.infosGenre)
        boutonSauvegarder = view.findViewById(R.id.infosBoutonSauvegarder)
        boutonPartir = view.findViewById(R.id.infosBoutonPartir)
        imagePartir = view.findViewById(R.id.infosImagePartir)
        imageAleatoire = view.findViewById(R.id.infosImageAleatoire)

        viewModel.personneOriginale.observe(viewLifecycleOwner){ _ ->
            miseAJour()
        }

        boutonSauvegarder.setOnClickListener { viewModel.sauvegarder(saisieNom.text.toString()) }
        boutonPartir.setOnClickListener { viewModel.partir() }
        imagePartir.setOnClickListener { viewModel.partir() }

        view.visibility = View.GONE

    }

    fun miseAJour(){
        if(viewModel.personneOriginale.value != null){
            val personne = viewModel.personneOriginale.value!!
            saisieNom.setText(personne.nom)
            imageView.setImageDrawable(obtenirImage(requireContext(), personne.image))
            ageView.text = "Age: ${personne.age}"
            familyView.text = "Family: ${personne.famille}"
            if(personne.genre == Genre.M){
                genreView.text = "Genre: Male"
            }else{
                genreView.text = "Genre: Female"
            }

        }
    }


}