package com.example.lifesimulator.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.lifesimulator.Activite_Arbre_Genealogique
import com.example.lifesimulator.R
import com.example.lifesimulator.view_model.ViewModelAjoutPersonne
import com.example.lifesimulator.view_model.ViewModelConnexion
import com.example.lifesimulator.view_model.ViewModelPagePrincipale

class PagePrincipale : AppCompatActivity() {

    private val viewModel : ViewModelPagePrincipale by viewModels()
    private val viewModelConnexion : ViewModelConnexion by viewModels()
    private val viewModelAjoutPersonne : ViewModelAjoutPersonne by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activite_menu_principal, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.passerAnnee -> viewModel.passerAnnee()
            R.id.connexion -> viewModelConnexion.ouvrir()
            R.id.versArbre ->{
                val intent = Intent(this, Activite_Arbre_Genealogique::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)
            }
            R.id.ajouterPersonne ->{
                viewModelAjoutPersonne.ouvrir()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}