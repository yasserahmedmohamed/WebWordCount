package com.instabug.webwordcount.presentation.feature_words_count

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.instabug.webwordcount.data.repository.LocalWordsRepositoryImpl
import com.instabug.webwordcount.data.repository.WordsSortRepositoryImpl
import com.instabug.webwordcount.domain.repository.LocalWordsRepository
import com.instabug.webwordcount.domain.useCase.GetWebWordsUseCase

class MainViewModelFactory (private val activity: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(GetWebWordsUseCase(), LocalWordsRepositoryImpl(activity),
                WordsSortRepositoryImpl(activity)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
