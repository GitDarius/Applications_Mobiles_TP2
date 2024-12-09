package com.example.lifesimulator.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.TextUtils.TruncateAt
import android.util.Log
import android.view.Display.Mode
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
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

    fun creerId(): Int {
        val idExistants = Model.listeToutesPersonnes.map { it.id }.toSet()
        var i = 0
        while (i in idExistants) {
            i++
        }
        Log.i("TAG", "Nouvel id est: $i")
        return i
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

    fun drawableVersUri(drawable: Drawable, context: Context): Uri? {
        return try {
            // Convert the drawable to a bitmap
            val bitmap = if (drawable is BitmapDrawable) {
                drawable.bitmap
            } else {
                val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(bitmap)
                drawable.setBounds(0, 0, canvas.width, canvas.height)
                drawable.draw(canvas)
                bitmap
            }

            // Create a file with a unique name in the cache directory
            val file = File(context.cacheDir, "drawable_image_${System.currentTimeMillis()}.png")

            // Write the bitmap to the file
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()

            // Get the Uri for the file using the updated FileProvider
            FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }



}