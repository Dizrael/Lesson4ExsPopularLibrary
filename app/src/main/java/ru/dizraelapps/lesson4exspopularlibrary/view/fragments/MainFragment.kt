package ru.dizraelapps.lesson4exspopularlibrary.view.fragments

import android.app.Activity
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.graphics.drawable.toBitmap
import kotlinx.android.synthetic.main.fragment_main.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.dizraelapps.lesson4exspopularlibrary.App
import ru.dizraelapps.lesson4exspopularlibrary.R
import ru.dizraelapps.lesson4exspopularlibrary.model.PicturesStorage
import ru.dizraelapps.lesson4exspopularlibrary.presenter.FragmentPresenter
import java.io.File
import java.io.FileInputStream

class MainFragment : MvpAppCompatFragment(), FragmentPictureView {

    private val TAG = MainFragment::class.java.simpleName

    companion object{
        fun newInstance() = MainFragment()
    }

    private val presenter: FragmentPresenter by moxyPresenter {
        FragmentPresenter(PicturesStorage(), App.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = View.inflate(context, R.layout.fragment_main, null)

    override fun init() {
        button_load_image.setOnClickListener {
            presenter.loadPicture()
        }
        button_convert_image.setOnClickListener {
            val jpgPicture = imageView.drawable
            presenter.convertJpg2Png(jpgPicture.toBitmap())
        }
    }

    override fun updateImage(image: Bitmap) {
        imageView.setImageBitmap(image)
    }

    override fun updateTextView() {
        text_view_format.text = "PNG"
    }


}