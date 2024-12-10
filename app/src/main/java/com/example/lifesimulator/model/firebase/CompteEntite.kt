package com.example.lifesimulator.model.firebase

import android.util.Log
import com.example.lifesimulator.model.Compte
import com.example.lifesimulator.model.Genre
import com.example.lifesimulator.model.Model
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

//Recupere une Compte selon son Id
suspend fun getCompte(nomUser: String, motDePasse: String): Compte? {
    val db = Firebase.firestore
    return try {
        val compteSnapshot = db.collection("Comptes")
            .whereEqualTo("nom", nomUser)
            .whereEqualTo("motDePasse", motDePasse)
            .limit(1) // Ensure only one result is retrieved
            .get()
            .await()

        compteSnapshot.documents.firstOrNull()?.toObject(Compte::class.java)
    } catch (e: Exception) {
        Log.e("TAG", "Erreur en recuperant le Compte $nomUser avec mot de passe fourni: ${e.message}")
        null
    }
}


//Ajoute une nouvelle Compte à la base de données
suspend fun ajouterCompte(compte: Compte): String? {
    val db = Firebase.firestore
    return try {
        val docRef = db.collection("Comptes").add(compte).await()
        docRef.id
    } catch (e: Exception) {
        Log.i("TAG", "Erreur en ajoutant un Compte: ${e.message}")
        null
    }
}

fun enleverCompte(id: String) {
    val db = Firebase.firestore

    db.collection("Comptes").document(id).delete()
}

fun mettreAJourCompte(compte: Compte) {
    val db = Firebase.firestore
    db.collection("Comptes").document(compte.nom).set(compte)
}