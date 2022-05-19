package com.instabug.webwordcount.presentation.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.instabug.webwordcount.R


class LoadingDialog {

    private lateinit var context: Context
    private lateinit var dialog: Dialog

    //..we need the context else we can not create the dialog so get context in constructor
    constructor(context: Context) {
        this.context = context
        dialog = Dialog(context, R.style.baseDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        //...set cancelable false so that it's never get hidden
        dialog.setCancelable(false)
        //...that's the layout i told you will inflate later
        dialog.setContentView(R.layout.dialog_loading)
        val window = dialog.window
        window?.setBackgroundDrawable(ContextCompat.getDrawable(context,R.color.white))

        window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        window?.setStatusBarColor(ContextCompat.getColor(context,R.color.blue))
        dialog.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent)




    }

    @SuppressLint("ResourceAsColor", "UseCompatLoadingForDrawables")
    fun showDialog() {

        dialog.show()
    }

    //..also create a method which will hide the dialog when some work is done
    fun hideDialog() {
        if (this::dialog.isInitialized && dialog.isShowing)
            dialog.dismiss()
    }

}