package com.example.lifesimulator.view_model

import android.content.ContentResolver
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.lifesimulator.model.API.ApiReponse
import com.example.lifesimulator.model.API.RetrofitInstance
import com.example.lifesimulator.model.Genre
import com.example.lifesimulator.model.Outils.nouveauNom
import com.example.lifesimulator.model.Personne
import com.example.lifesimulator.view.AfficPersonneAdapter
import com.example.lifesimulator.view.BureauAdapter
import com.example.lifesimulator.view.FragmentInfosPersonne
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ViewModelInfos : ViewModel() {

    var fragmentInfos: FragmentInfosPersonne? = null
    var personneOriginale: MutableLiveData<Personne> = MutableLiveData(null)
    var adapteurAfficPersonne: AfficPersonneAdapter? = null
    var adapterBureau: BureauAdapter? = null
    var imageTemp: Drawable? = null

    fun changerPersonne(personne: Personne){
        personneOriginale.value = personne
        fragmentInfos!!.view?.visibility = View.VISIBLE
    }

    fun sauvegarder(nom: String){
        val personne = personneOriginale.value!!
        if(nom.isEmpty()){
            Log.i("Snack", "Nom peut pas être vide")
        }else{
            personne.nom = nom
        }
        if(imageTemp != null){
            val resources: Resources = getApplication<Application>().applicationContext.getResources()
            Uri.parse(
                ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + resources.getResourcePackageName(
                    resId
                ) + '/' + resources.getResourceTypeName(resId) + '/' + resources.getResourceEntryName(
                    resId
                )
            )
        }
        partir()
    }

    fun partir(){
        adapterBureau!!.notifyDataSetChanged()
        adapteurAfficPersonne!!.notifyDataSetChanged()
        fragmentInfos!!.view?.visibility = View.GONE
    }

    fun avoirNomAleatoire(): String{
        return nouveauNom(personneOriginale.value!!.genre)
    }

    fun avoirImageAleatoire(callback: (Drawable?) -> Unit) {
        val endpoint = if (personneOriginale.value!!.genre == Genre.M) {
            "husbando"
        } else {
            listOf("kitsune", "waifu", "neko").random()
        }
        recupererImageApi(endpoint) { url ->
            if (url != null) {
                // Use Glide to load the image and get it as a Drawable
                Glide.with(fragmentInfos!!.requireContext())
                    .load(url)
                    .into(object : CustomTarget<Drawable>() {
                        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                            imageTemp = resource
                            callback(imageTemp) // Return the Drawable through the callback
                        }
                        override fun onLoadCleared(placeholder: Drawable?) {
                            callback(null) // Optional, handle placeholder or clear callback
                        }
                    })
            } else {
                callback(null)
            }
        }
    }

    private fun recupererImageApi(endpoint: String, callback: (String?) -> Unit) {
        RetrofitInstance.apiService.getQuote(endpoint).enqueue(object : Callback<ApiReponse> {
            override fun onResponse(call: Call<ApiReponse>, response: Response<ApiReponse>) {
                if (response.isSuccessful) {
                    // Log the entire response body
                    Log.i("TAG", "Response Body: ${response.body()}")

                    // Access the URL from the first item in the results list
                    val results = response.body()?.resultats
                    Log.i("TAG", "Results: $results")

                    val quote = results?.firstOrNull()
                    quote?.let {
                        Log.i("TAG", "URL: ${it.image}")
                        callback(it.image) // Provide the image URL to the callback
                    } ?: run {
                        Log.i("TAG", "Quote is null or list is empty")
                        callback(null)
                    }
                } else {
                    Log.i("TAG", "Response not successful: ${response.errorBody()?.string()}")
                    callback(null)
                }
            }

            override fun onFailure(call: Call<ApiReponse>, t: Throwable) {
                Log.i("TAG", "Erreur lors du chargement de l'API: ${t.message}")
                callback(null)
            }
        })
    }


}