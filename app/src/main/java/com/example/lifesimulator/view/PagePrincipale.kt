package com.example.lifesimulator.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.lifesimulator.R
import com.example.lifesimulator.model.Outils.passerAnnee
import com.example.lifesimulator.view_model.ViewModelPagePrincipale

class PagePrincipale : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activite_menu_principal, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item1 -> passerAnnee()
        }
        return super.onOptionsItemSelected(item)
    }


}