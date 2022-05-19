package com.instabug.webwordcount

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.instabug.webwordcount.Utils.isOnline
import com.instabug.webwordcount.databinding.ActivityMainBinding
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future


class MainActivity : AppCompatActivity() {


    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        // network is available for use
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
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
        }
    }
    val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()


    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        val data = getData()

//        Log.e("tEST_DATA",data)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.main_menu, menu)
        menu?.let {
            searchView = it.findItem(R.id.search).actionView as SearchView
            setSearchViewCallBacks()
        }


        return super.onCreateOptionsMenu(menu)
    }

    private fun setConnectionUpdates(){
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }

   private fun setSearchViewCallBacks() {
        if (this::searchView.isInitialized) {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    Toast.makeText(this@MainActivity, p0, Toast.LENGTH_SHORT).show()
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    Toast.makeText(this@MainActivity, p0, Toast.LENGTH_SHORT).show()
                    return false
                }

            })

            searchView.setOnCloseListener {
                // return to view model to return all words
                true
            }
        }
    }





//    fun getData():String{
//        val pool = Executors.newCachedThreadPool()
//        val future: Future<String> = pool.submit(TestTask())
//        return future.get()
//    }
//
//
//    class TestTask : Callable<String> {
//        override fun call(): String {
//            val url = "https://instabug.com/"
//            return HtmlCompat.fromHtml(
//                NetworkCalls.fetchSiteData(url).split("<body>")[1].split("</body>")[0],
//                HtmlCompat.FROM_HTML_MODE_LEGACY
//            ).toString()
//        }
//
//    }
}