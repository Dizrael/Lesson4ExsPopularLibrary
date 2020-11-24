package ru.dizraelapps.lesson4exspopularlibrary.view.navigation

import androidx.fragment.app.Fragment
import ru.dizraelapps.lesson4exspopularlibrary.view.fragments.MainFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class FragmentImage : SupportAppScreen() {
        override fun getFragment(): Fragment = MainFragment.newInstance()
    }
}