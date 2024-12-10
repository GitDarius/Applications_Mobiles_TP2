package com.example.lifesimulator.model.firebase

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lifesimulator.model.Compte
import com.example.lifesimulator.model.Genre
import com.example.lifesimulator.model.Personne
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


data class PersonneEntite(
    var idPersonne: Int = 0,
    var compteLie: String = "",
    var enVie: Boolean = true,
    var nom: String = "",
    var image: String = "",
    var conjointId: Int? = null,
    var genre: Genre = Genre.M,
    var age: Int = 0,
    var pereId: Int? = null,
    var famille: String? = null
)

//Recupere toutes les personnes liées à un compte
suspend fun getPersonne(personneId: Int, compteLie: String): PersonneEntite? {
    val db = Firebase.firestore
    return try {
        val personneSnapshot = db.collection("personnes")
            .whereEqualTo("idPersonne", personneId)
            .whereEqualTo("compteLie", compteLie)
            .limit(1)
            .get()
            .await()
        personneSnapshot.documents.firstOrNull()?.toObject(PersonneEntite::class.java)
    } catch (e: Exception) {
        println("Erreur en cherchant user avec ID $personneId et compte $compteLie: ${e.message}")
        null
    }
}

//Recupere toutes les Comptes liées à un compte
suspend fun getPersonnesCompte(nomUser: String): List<Personne> {
    val db = Firebase.firestore
    Log.i("TAG", "On essaie de get les personnes de $nomUser:")
    return try {
        val personneSnapshot = db.collection("Personnes")
            .whereEqualTo("compteLie", nomUser)
            .get()
            .await()
        personneSnapshot.toObjects(Personne::class.java)
    } catch (e: Exception) {
        Log.i("TAG", "Erreur en recuperant les Comptes du compte $nomUser: ${e.message}")
        emptyList() // Return an empty list
    }
}

//Ajoute une nouvelle personne à la base de données
suspend fun ajouterPersonne(personne: PersonneEntite): String? {
    val db = Firebase.firestore
    return try {
        // Add the PersonneEntite object and get the auto-generated document ID
        val docRef = db.collection("personnes").add(personne).await()
        Log.i("Firestore", "Added personne with ID: ${docRef.id}")
        docRef.id // Return the auto-generated document ID
    } catch (e: Exception) {
        Log.e("Firestore", "Erreur en ajoutant une personne: ${e.message}")
        null
    }
}


fun enleverPersonne(id: String) {
    val db = Firebase.firestore

    db.collection("personnes").document(id).delete()
}

fun mettreAJourPersonne(personne: PersonneEntite) {
    val db = Firebase.firestore
    db.collection("personnes").document(personne.idPersonne.toString()).set(personne)
}
