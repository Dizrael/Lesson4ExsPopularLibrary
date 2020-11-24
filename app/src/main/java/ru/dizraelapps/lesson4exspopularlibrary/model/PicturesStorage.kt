package ru.dizraelapps.lesson4exspopularlibrary.model

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream

class PicturesStorage {

    @SuppressLint("SdCardPath")
    private val path = "/sdcard/Download"

    fun justGetPicture(path: String): Observable<Bitmap> {
        val pictureFile = File(path)
        return Observable.just(BitmapFactory.decodeFile(pictureFile.path))
            .subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
    }

    fun convertImage(image: Bitmap?): Observable<Bitmap>{
        val newFilePath = "$path/converted_GB.png"
        val newFile = File(newFilePath)
        newFile.createNewFile()
        val out = FileOutputStream(newFile)
        return Observable.create<Bitmap>{
            image?.compress(Bitmap.CompressFormat.PNG, 100, out)
            out.use { fos ->
                fos.write(image!!.rowBytes)
            }
            out.flush()
            out.close()
            val convertedImg = File(path + "converted_GB.png")
            it.onNext(BitmapFactory.decodeFile(convertedImg.path))
            it.onComplete()
        }.subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
    }

}