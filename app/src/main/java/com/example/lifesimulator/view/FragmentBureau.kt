package com.example.lifesimulator.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lifesimulator.R
import com.example.lifesimulator.model.Personne
import com.example.lifesimulator.view_model.ViewModelInfos
import com.example.lifesimulator.view_model.ViewModelPagePrincipale

class FragmentBureau : Fragment() {

    private val viewModel : ViewModelPagePrincipale by activityViewModels()
    private val viewModelInfos : ViewModelInfos by activityViewModels()

    private val listePersonnes get() = viewModel.listePersonnesBureau.value!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BureauAdapter
    private lateinit var boutonMarier: Button
    private lateinit var boutonProcreer: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bureau, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Recycler View
        recyclerView = view.findViewById(R.id.bureauRecyclerView)
        adapter = BureauAdapter(requireContext(), this, listePersonnes)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
        viewModel.adapteurBureau = adapter

        //Boutons
        boutonMarier = view.findViewById(R.id.bureauBoutonMarier)
        boutonMarier.setOnClickListener {
            viewModel.marier()
        }

        boutonProcreer = view.findViewById(R.id.bureauBoutonBebe)
        boutonProcreer.setOnClickListener {
            viewModel.procreer()
        }

    }

    fun partirBureau(personne: Personne){
        viewModel.partirBureau(personne)
    }

    fun afficherInfos(personne: Personne){
        viewModelInfos.changerPersonne(personne)
    }

}