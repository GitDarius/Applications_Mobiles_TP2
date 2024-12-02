package com.example.lifesimulator.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lifesimulator.R
import com.example.lifesimulator.model.Personne
import com.example.lifesimulator.view_model.ViewModelInfos
import com.example.lifesimulator.view_model.ViewModelPagePrincipale

class FragmentAfficPersonnes : Fragment() {

    private lateinit var view: View

    private val viewModel : ViewModelPagePrincipale by activityViewModels()
    private val viewModelInfos : ViewModelInfos by activityViewModels()

    private val listePersonnes get() = viewModel.listePersonnes.value!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AfficPersonneAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_affic_personnes, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.afficPersonnesRecyclerView)

        //Calcule le nombre d'éléments à afficher sur la largeur
        recyclerView.post {
            val recyclerViewWidth = recyclerView.width
            val itemWidth = resources.getDimension(R.dimen.item_largeur)

            val nbObjetsLigne = (recyclerViewWidth / (itemWidth)).toInt().coerceAtLeast(1)

            recyclerView.layoutManager = GridLayoutManager(requireContext(), nbObjetsLigne)

            adapter = AfficPersonneAdapter(requireContext(), this, listePersonnes)
            recyclerView.adapter = adapter
            viewModel.adapterListePersonnes = adapter
            viewModelInfos.adapteurAfficPersonne = adapter
        }
    }

    fun allerBureau(personne: Personne){
        viewModel.ajouterBureau(personne)
    }

    fun afficherInfos(personne: Personne){
        viewModelInfos.changerPersonne(personne)
    }

}