package com.instabug.webwordcount.presentation.base

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.instabug.webwordcount.R
import com.instabug.webwordcount.Utils.isOnline
import com.instabug.webwordcount.presentation.dialog.LoadingDialog

abstract class BaseActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         snackbar =  Snackbar.make(findViewById(android.R.id.content), getString(R.string.no_internt_connection), Snackbar.LENGTH_INDEFINITE)
            .setBackgroundTint(Color.RED)
        if (!isOnline()){
            snackbar.show()
        }
    }

    override fun onResume() {
        super.onResume()
        setConnectionUpdates()
    }

    private lateinit var loadingDialog: LoadingDialog


    private lateinit var snackbar: Snackbar

    @Override
    open fun networkStatus(isConnected:Boolean){
        if (isConnected){
            snackbar.dismiss()
        }else{
            snackbar.show()
        }
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        // network is available for use
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            networkStatus(true)

        }

        // Network capabilities have changed for the network
        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
        }

        // lost network connection
        override fun onLost(network: Network) {
            super.onLost(network)
            networkStatus(false)
        }
    }

    val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()


    private fun setConnectionUpdates(){
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }


    fun showSnackbar(){
        if (this::snackbar.isInitialized){
            snackbar.show()
        }
    }


    fun showLoading() {
        if (!this::loadingDialog.isInitialized)
            loadingDialog = LoadingDialog(this)
        loadingDialog.showDialog()
    }

    fun hideLoading() {
        if (this::loadingDialog.isInitialized) {
            loadingDialog.hideDialog()
        }
    }



}