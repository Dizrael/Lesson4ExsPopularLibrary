package ru.dizraelapps.lesson4exspopularlibrary.view

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import moxy.MvpAppCompatActivity
import moxy.MvpView
import moxy.ktx.moxyPresenter
import ru.dizraelapps.lesson4exspopularlibrary.App
import ru.dizraelapps.lesson4exspopularlibrary.R
import ru.dizraelapps.lesson4exspopularlibrary.presenter.MainPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : MvpAppCompatActivity(), MvpView {

    private val PERMISSION_WRITE = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    private val PERMISSION_READ = android.Manifest.permission.READ_EXTERNAL_STORAGE
    private val REQUEST_PERMISSION_CODE = 1

    private val presenter by moxyPresenter {
        MainPresenter(App.instance.router)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!checkPermission()) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(PERMISSION_READ, PERMISSION_WRITE),
                REQUEST_PERMISSION_CODE
            )
        }
    }

    private val navigatorHolder: NavigatorHolder = App.instance.navigatorHolder
    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    private fun checkPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(
                this,
                PERMISSION_READ
            ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                PERMISSION_WRITE
            ) == PackageManager.PERMISSION_GRANTED
        ) return true
        return false
    }
}