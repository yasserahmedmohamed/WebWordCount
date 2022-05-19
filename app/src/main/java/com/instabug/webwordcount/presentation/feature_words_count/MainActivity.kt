package com.instabug.webwordcount.presentation.feature_words_count

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.instabug.webwordcount.R
import com.instabug.webwordcount.Utils.ResponseDataState
import com.instabug.webwordcount.Utils.isOnline
import com.instabug.webwordcount.databinding.ActivityMainBinding
import com.instabug.webwordcount.presentation.base.BaseActivity

class MainActivity : BaseActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this, MainViewModelFactory(this.application)).get(
            MainViewModel::class.java)
    }

    var isCachedDataIsShowed = false

    override fun networkStatus(isConnected: Boolean) {
        super.networkStatus(isConnected)
        if (isCachedDataIsShowed&&isConnected){
            viewModel.getWords(isConnected)
            isCachedDataIsShowed = false
        }

    }
    lateinit var searchView: SearchView
    lateinit var wordsAdapter: WordsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpRecyclerView()
        if (isOnline()){
            viewModel.getWords(true)
        }else{
            isCachedDataIsShowed = true
            viewModel.getWords(false)
        }

    }

    private fun setUpRecyclerView(){
        wordsAdapter = WordsAdapter()
        binding.wordsRecycleView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL,false)
            adapter = wordsAdapter
        }
    }


    override fun onResume() {
        super.onResume()
        viewModel.wordsObserver.observe(this) {
            when (it) {
                is ResponseDataState.Loading -> {
                    showLoading()
                }
                is ResponseDataState.NewData -> {
                    hideLoading()
                    wordsAdapter.setList(it.data)
                }
                is ResponseDataState.FailData -> {
                    hideLoading()

                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.main_menu, menu)
        menu?.let {
            searchView = it.findItem(R.id.search).actionView as SearchView
            setSearchViewCallBacks()
        }


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.sort){
            viewModel.sortList()
        }

        return super.onOptionsItemSelected(item)
    }


   private fun setSearchViewCallBacks() {
        if (this::searchView.isInitialized) {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    p0?.let { viewModel.searchInWords(it) }
                    return false
                }

            })

            searchView.setOnCloseListener {
              viewModel.showAllWords()
                false
            }
        }
    }






}