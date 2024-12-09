package com.example.lifesimulator.view_model

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.lifesimulator.model.Compte
import com.example.lifesimulator.model.Model
import com.example.lifesimulator.view.FragmentConnexion

class ViewModelConnexion : ViewModel() {

    var fragmentConnexion : FragmentConnexion? = null

    fun partir(){
        fragmentConnexion!!.view?.visibility = View.GONE
    }

    fun ouvrir(){
        fragmentConnexion!!.view?.visibility = View.VISIBLE
    }

    fun connecter(nom: String){
        Model.utilisateurActuel = nom
        partir()
    }

    fun creerUtilisateur(nom: String, motDePasse: String){
        Model.listeComptes.add(Compte(nom, hacherString(motDePasse)))
    }

    fun utilisateurExistant(nom: String, motDePasse: String): Boolean {
        return Model.listeComptes.any { it.nom == nom && it.motDePasse == hacherString(motDePasse) }
    }

    fun nomValide(nom: String): Boolean{
        return nom.isNotEmpty()
    }

    fun motDePasseValide(motDePasse: String): Boolean{
        return motDePasse.isNotEmpty()
    }

    private fun hacherString(valeurInitiale: String): String{
        var valHachee = hacherEtape1(valeurInitiale)
        valHachee = hacherEtape2(valHachee)
        valHachee = hacherEtape3(valHachee)
        valHachee = hacherEtape4(valHachee)
        return valHachee
    }

    private fun dehacherString(valeurInitiale: String): String{
        var valOriginale = dehacherEtape1(valeurInitiale)
        valOriginale = dehacherEtape2(valOriginale)
        valOriginale = dehacherEtape3(valOriginale)
        valOriginale = dehacherEtape4(valOriginale)
        return valOriginale
    }

    //Transforme lettres en chiffres et chiffres + 52
    private fun hacherEtape1(valeurInitiale: String): String {
        val alphabet = arrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
        var resultat = ""
        for(lettre in valeurInitiale){
            val position = alphabet.indexOfFirst { it == lettre }
            if(position == -1 || position > 9){
                resultat += position
            }else{
                resultat += "0$position"
            }
        }
        return resultat
    }

    //Décale tous les chiffres de  +5
    private fun hacherEtape2(valeurInitiale: String): String {
        var resultat = ""
        var index = 0
        while(index < valeurInitiale.length){
            val temp = valeurInitiale.substring(index, index + 2).toInt() + 5
            if(temp < 10){
                resultat += "0$temp"
            }else{
                resultat += temp
            }
            index += 2
        }
        return resultat
    }


    //Transforme le deuxième chiffre de chaque combinaison en lettre
    private fun hacherEtape3(valeurInitiale: String): String {
        val alphabet = arrayOf("z", "u", "e", "t", "q", "c", "i", "s", "h", "n")
        var resultat = ""
        var index = 1
        while(index < valeurInitiale.length){
            resultat += valeurInitiale[index - 1]
            resultat += alphabet[valeurInitiale[index].code-48]
            index += 2
        }
        return resultat
    }

    //Sépare les lettres des chiffres sinon c'est cramé (chiffre, lettre, chiffre, lettre)
    private fun hacherEtape4(valeurInitiale: String): String {
        var resultat = ""
        var index = 0
        while(index < valeurInitiale.length){
            resultat += valeurInitiale[index]
            index += 2
        }
        index = valeurInitiale.length-1
        while(index >= 0){
            resultat += valeurInitiale[index]
            index -= 2
        }
        return resultat
    }

    fun dehacherEtape1(input: String): String {
        val resultat = StringBuilder()
        for (i in 0 until input.length / 2) {
            resultat.append(input[i])
            resultat.append(input[input.length - 1 - i])
        }
        return resultat.toString()
    }

    fun dehacherEtape2(input: String): String {
        val alphabet = arrayOf("z", "u", "e", "t", "q", "c", "i", "s", "h", "n")
        val resultat = StringBuilder()
        for (i in 1 until input.length step 2) {
            resultat.append(input[i - 1])
            val index = alphabet.indexOf(input[i].toString())
            resultat.append(index)
        }
        return resultat.toString()
    }

    fun dehacherEtape3(input: String): String {
        val resultat = StringBuilder()
        for (i in 0 until input.length step 2) {
            val temp = input.substring(i, i + 2).toInt() - 5
            if (temp < 10) {
                resultat.append("0$temp")
            } else {
                resultat.append(temp.toString())
            }
        }
        return resultat.toString()
    }

    fun dehacherEtape4(input: String): String {
        val alphabet = arrayOf(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
        )
        val resultat = StringBuilder()
        for (i in 0 until input.length step 2) {
            val index = input.substring(i, i + 2).toInt()
            resultat.append(alphabet[index])
        }
        return resultat.toString()
    }


}