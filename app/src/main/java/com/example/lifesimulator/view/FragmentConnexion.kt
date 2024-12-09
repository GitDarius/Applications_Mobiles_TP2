package com.example.lifesimulator.view

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
import com.example.lifesimulator.model.Model
import com.example.lifesimulator.view_model.ViewModelConnexion
import com.example.lifesimulator.view_model.ViewModelPagePrincipale

class FragmentConnexion : Fragment() {

    private val viewModel : ViewModelConnexion by activityViewModels()

    private lateinit var nomView: EditText
    private lateinit var motDePasseView: EditText
    private lateinit var boutonConnexion: Button
    private lateinit var boutonEnregistrer: Button
    private lateinit var imagePartir: ImageView
    private lateinit var message: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_connexion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fragmentConnexion = this
        viewModel.partir()
        nomView = view.findViewById(R.id.connexionNom)
        motDePasseView = view.findViewById(R.id.connexionMotDePasse)
        boutonConnexion = view.findViewById(R.id.connexionBoutonConnexion)
        boutonEnregistrer = view.findViewById(R.id.connexionBoutonCreerCompte)
        imagePartir = view.findViewById(R.id.connexionImagePartir)
        message = view.findViewById(R.id.connexionMessage)
        if(Model.utilisateurActuel != null){
            message.text = "Connected as ${Model.utilisateurActuel}"
        }else{
            message.text = ""
        }

        boutonConnexion.setOnClickListener {
            val nom = nomView.text.toString()
            val motDePasse = motDePasseView.text.toString()

            if(!viewModel.nomValide(nom)){
                Log.i("Snack", "Nom invalide")
            }else if(!viewModel.motDePasseValide(motDePasse)){
                Log.i("Snack", "Mot de passe invalide")
            }else if(!viewModel.utilisateurExistant(nom, motDePasse)){
                Log.i("Snack", "Utilisateur inexistant")
            }else{
                viewModel.connecter(nom)
                message.text = "Connected as ${Model.utilisateurActuel}"
            }
        }

        boutonEnregistrer.setOnClickListener {
            val nom = nomView.text.toString()
            val motDePasse = motDePasseView.text.toString()
            if(!viewModel.nomValide(nom)){
                Log.i("Snack", "Nom invalide")
            }else if(!viewModel.motDePasseValide(motDePasse)){
                Log.i("Snack", "Mot de passe invalide")
            }else if(viewModel.utilisateurExistant(nom, motDePasse)){
                Log.i("Snack", "Utilisateur deja existant")
            }else{
                viewModel.creerUtilisateur(nom, motDePasse)
                viewModel.connecter(nom)
                message.text = "Connected as ${Model.utilisateurActuel}"
            }
        }

        imagePartir.setOnClickListener { viewModel.partir() }

    }
}