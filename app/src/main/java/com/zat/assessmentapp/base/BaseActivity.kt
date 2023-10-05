package com.zat.assessmentapp.base

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.kaopiz.kprogresshud.KProgressHUD
import com.zat.assessmentapp.R

abstract class BaseActivity : AppCompatActivity() {

    var progressBar: KProgressHUD? = null

    private lateinit var options: NavOptions


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        progressBar = KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setCancellable(true)
            .setBackgroundColor(Color.TRANSPARENT)
            .setAnimationSpeed(1)
            .setDimAmount(0.5f)


        options = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setEnterAnim(R.anim.enter_from_right)
            .setExitAnim(R.anim.exit_to_left)
            .setPopEnterAnim(R.anim.enter_from_left)
            .setPopExitAnim(R.anim.exit_to_right)
            .build()

    }


    fun showLoadingBar() {
        if (progressBar != null && !progressBar!!.isShowing)
            progressBar?.show()
    }

    fun hideLoadingBar() {
        if (progressBar != null && progressBar!!.isShowing)
            progressBar?.dismiss()
    }

    abstract fun attachViewMode()



    fun replaceFragment(action: Int) {          // fragment id not nav grpah
        Navigation.findNavController(this, R.id.fragmentMain).navigate(action, null, options)
    }

    fun replaceFragment(action: Int, bundle: Bundle) {
        Navigation.findNavController(this, R.id.fragmentMain).navigate(action, bundle, options)
    }

    fun showToast(message: String?) {
        message?.let {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } ?: kotlin.run {
            Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT)
                .show()
        }

    }

    fun showSnackbar(view: View, message: String?) {
        message?.let {
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
        } ?: kotlin.run {
            Snackbar.make(view, getString(R.string.something_went_wrong), Snackbar.LENGTH_SHORT)
                .show()
        }

    }


}