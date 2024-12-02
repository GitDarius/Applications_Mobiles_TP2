package com.example.lifesimulator.model

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.TextUtils.TruncateAt
import android.util.Log
import android.view.Display.Mode
import androidx.core.content.ContextCompat
import kotlin.math.pow


object Outils {

    fun obtenirImage(context: Context, image: String): Drawable? {
        return if (image.startsWith("content://")) {

            val uri = Uri.parse(image)
            context.contentResolver.openInputStream(uri)?.use {
                Drawable.createFromStream(it, uri.toString())
            }
        } else {
            Log.i("TAG", "Image peut-être pas supportée: $image")
            val resourceId = context.resources.getIdentifier(image, "drawable", context.packageName)
            ContextCompat.getDrawable(context, resourceId)
        }
    }

    fun personne(id: Int): Personne?{
        return Model.listeToutesPersonnes.find{it.id == id}
    }

    fun creerId(): Int{
        var i = 0
        while(true){
            for(personne in Model.listePersonnes){
                if(personne.id == i){
                    i++
                    continue
                }
            }
            return i
        }
    }

    fun nouveauNom(genre: Genre): String{
        val noms: Array<String>
        if(genre == Genre.M){
            noms = arrayOf(
                "Brimstone", "Phoenix", "Sova", "Cypher", "Breach", "Omen", "Yoru", "Kayo", "Chamber", "Harbor",
                "Gekko", "Iso",
                "Ezreal", "Garen", "Lucian", "Malzahar", "Nasus", "Ryze", "Shen", "Thresh", "Viktor", "Yone",
                "Ziggs", "Braum", "Jax", "Kayn", "Kennen", "Olaf", "Talon", "Sylas", "Udyr", "Zed",
                "Tanjiro", "Rengoku", "Giyu", "Sanemi", "Zenitsu", "Inosuke", "Muzan", "Muichiro", "Tengen",
                "RaisinSec", "Ru", "U", "Gustave", "Hypolite", "Mamadou", "Rantanplan", "Idéfix", "Donald", "Barrack"
            )
        }else{
            noms = arrayOf(
                "Sage", "Viper", "Reyna", "Killjoy", "Jett", "Raze", "Skye", "Astra", "Neon", "Fade",
                "Deadlock", "Clove", "Vyse",
                "Ahri", "Akali", "Annie", "Caitlyn", "Fiora", "Irelia", "Janna", "Karma", "Leona", "Lux",
                "Nami", "Qiyana", "Riven", "Seraphine", "Sivir", "Vayne", "Vi", "Yuumi", "Zyra", "Taliyah",
                "Shinobu", "Nezuko", "Mitsuri", "Daki", "Tamayo",
                "Pomme", "Anorac", "Alaska", "Pauline", "Merveille", "Désirée", "Espérance", "Chantal"
            )
        }
        return noms.random()
    }


    fun exp(nombre: Int, exposant: Double): Int{
        return nombre.toDouble().pow(exposant).toInt()
    }

}