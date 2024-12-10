package com.example.lifesimulator.model

import com.example.lifesimulator.R

object Model {

    var utilisateurActuel: String? = null

    var listeToutesPersonnes = mutableListOf(
        Personne(0, true, "Adam", R.drawable.test.toString(), null, Genre.M, 18, mutableListOf(), null, null),
        Personne(1, true, "Eve", R.drawable.test.toString(), null, Genre.F, 18, mutableListOf(), null, null),
    )

    var listePersonnes = listeToutesPersonnes.filter { it.enVie }.toMutableList()

    val listeComptes = mutableListOf<Compte>()

    var anneeActuelle = 0

}