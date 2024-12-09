package com.example.lifesimulator

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.activityViewModels
import com.example.lifesimulator.view_model.ViewModelAjoutPersonne
import com.example.lifesimulator.view_model.ViewModelPagePrincipale
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class FragmentAjouterPersonne : Fragment() {

    private val viewModel : ViewModelAjoutPersonne by activityViewModels()
    private val viewModelPagePrincipale : ViewModelPagePrincipale by activityViewModels()

    private lateinit var nomView : EditText
    private lateinit var ageView : EditText
    private lateinit var genreSpinner : Spinner
    private lateinit var boutonPartir : Button
    private lateinit var boutonSauvegarder : Button
    private lateinit var imagePartir : ImageView
    private lateinit var imageAleatoire : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ajouter_personne, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fragmentAjouterPersonne = this

        viewModel.partir()

        genreSpinner = view.findViewById(R.id.ajoutPersonneGenreDropdown)
        nomView = view.findViewById(R.id.ajoutPersonneNom)
        ageView = view.findViewById(R.id.ajoutPersonneAgeInput)
        boutonPartir = view.findViewById(R.id.ajoutPersonneBoutonPartir)
        boutonSauvegarder = view.findViewById(R.id.ajoutPersonneBoutonSauvegarder)
        imagePartir = view.findViewById(R.id.ajoutPersonneImagePartir)
        imageAleatoire = view.findViewById(R.id.ajoutPersonneImageAleatoire)

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.spinner_item,
            listOf("M", "F")
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        genreSpinner.adapter = adapter

        boutonPartir.setOnClickListener { viewModel.partir() }
        imagePartir.setOnClickListener { viewModel.partir() }
        imageAleatoire.setOnClickListener {
            genreSpinner.setSelection(Random.nextInt(0, adapter.count))
            nomView.setText(viewModel.nomAleatoire(genreSpinner.toString()))
            ageView.setText(viewModel.ageAleatoire())
        }
        boutonSauvegarder.setOnClickListener {
            if(!viewModel.nomValide(nomView.text.toString())){
                Snackbar.make(requireView(), "Nom invalide", Snackbar.LENGTH_SHORT).show()
            }else if(!viewModel.ageValide(ageView.text.toString())){
                Snackbar.make(requireView(), "Age invalide", Snackbar.LENGTH_SHORT).show()
            }else{
                viewModel.sauvegarder(nomView.text.toString(), ageView.text.toString(), genreSpinner.selectedItem.toString(), R.drawable.test.toString())
                viewModelPagePrincipale.adapterListePersonnes!!.notifyItemInserted(viewModelPagePrincipale.adapterListePersonnes!!.itemCount-1)
                viewModel.partir()
            }
        }




    }

    override fun onResume() {
        super.onResume()
        nomView.setText("")
        ageView.setText("")
    }
}