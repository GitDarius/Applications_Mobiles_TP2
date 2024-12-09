package com.example.lifesimulator

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.activityViewModels
import com.example.lifesimulator.model.Model
import com.example.lifesimulator.view_model.ViewModelAffArbre

class FragmentChoisirFamille : Fragment() {

    private lateinit var menuDeroulant : Spinner
    val viewModel : ViewModelAffArbre by activityViewModels()
    private lateinit var familles : Array<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choisir_famille, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuDeroulant = view.findViewById(R.id.menuDeroulantFamilles)
        familles = toutesFamilles()
        actualiser()

        menuDeroulant.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(viewModel.famille.value != familles[position]){
                    Log.i("TAG", "Afficher arbre svp")
                    viewModel.famille.value = familles[position]
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //Rien à faire ¯\_(ツ)_/¯
            }

        }
    }

    private fun toutesFamilles(): Array<String>{
        val listeFamilles = mutableListOf<String>()
        for(personne in Model.listeToutesPersonnes){
            if(personne.famille != null && personne.famille !in listeFamilles){
                listeFamilles.add(personne.famille!!)
            }
        }
        return listeFamilles.toTypedArray()
    }

    fun actualiser(){
        familles = toutesFamilles()
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, familles)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        menuDeroulant.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        Log.i("TAG", "On resume appelé")
        actualiser()
    }
}