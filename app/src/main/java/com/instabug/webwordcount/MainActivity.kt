package com.instabug.webwordcount

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.instabug.webwordcount.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.main_menu, menu)
        menu?.let {
            searchView = it.findItem(R.id.search).actionView as SearchView
            setSearchViewCallBacks()
        }

        return super.onCreateOptionsMenu(menu)
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
}