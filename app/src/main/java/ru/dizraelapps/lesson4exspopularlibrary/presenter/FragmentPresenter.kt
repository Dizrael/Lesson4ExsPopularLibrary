package ru.dizraelapps.lesson4exspopularlibrary.presenter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import moxy.MvpPresenter
import ru.dizraelapps.lesson4exspopularlibrary.model.PicturesStorage
import ru.dizraelapps.lesson4exspopularlibrary.view.fragments.FragmentPictureView
import ru.terrakok.cicerone.Router
import java.io.FileOutputStream

class FragmentPresenter(private val pictureStorage: PicturesStorage, private val router: Router) :
    MvpPresenter<FragmentPictureView>() {

    private val TAG = FragmentPresenter::class.java.simpleName

    @SuppressLint("SdCardPath")
    private val picturePath: String = "/sdcard/Download/GB_image.jpg"

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun convertJpg2Png(image: Bitmap?) {
        pictureStorage.convertImage(image).subscribe(
            {
                viewState.updateImage(it)
                viewState.updateTextView()
            },
            { e -> Log.e(TAG, "convertJpg2Png: " + e.message) }
        )
    }

    fun loadPicture() {
        pictureStorage.justGetPicture(picturePath).subscribe(
            { viewState.updateImage(it) },
            { e ->
                Log.e(TAG, "loadPicture: ${e.message}")
            })
    }
}