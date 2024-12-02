package com.example.lifesimulator.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import com.example.lifesimulator.R
import com.example.lifesimulator.view_model.ViewModelInfos
import com.example.lifesimulator.view_model.ViewModelPagePrincipale

class PagePrincipale : AppCompatActivity() {

    private val viewModel : ViewModelPagePrincipale by viewModels()

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
            R.id.item1 -> viewModel.passerAnnee()
        }
        return super.onOptionsItemSelected(item)
    }

}