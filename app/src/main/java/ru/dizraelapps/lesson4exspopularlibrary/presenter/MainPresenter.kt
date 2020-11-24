package ru.dizraelapps.lesson4exspopularlibrary.presenter

import android.view.View
import moxy.MvpPresenter
import ru.dizraelapps.lesson4exspopularlibrary.view.MainView
import ru.dizraelapps.lesson4exspopularlibrary.view.navigation.Screens
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router): MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.navigateTo(Screens.FragmentImage())
    }
}