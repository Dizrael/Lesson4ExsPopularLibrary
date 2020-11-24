package ru.dizraelapps.lesson4exspopularlibrary.view.fragments

import android.graphics.Bitmap
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface FragmentPictureView: MvpView {
    fun init()
    fun updateImage(image: Bitmap)
    fun updateTextView()
}