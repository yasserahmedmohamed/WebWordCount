package com.instabug.webwordcount.presentation.feature_words_count

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.instabug.webwordcount.Utils.ResponseDataState
import com.instabug.webwordcount.domain.model.WordWithCount
import com.instabug.webwordcount.domain.useCase.GetWebWordsUseCase
import kotlinx.coroutines.delay
import kotlin.concurrent.thread

class MainViewModel(private val webWordsUseCase: GetWebWordsUseCase):ViewModel() {


    private val _wordsObserver =
        MutableLiveData<ResponseDataState<ArrayList<WordWithCount>>>()
    var wordsObserver:LiveData<ResponseDataState<ArrayList<WordWithCount>>> = _wordsObserver


    fun getWords(isNetworkAvailable:Boolean){
        _wordsObserver.postValue(ResponseDataState.Loading())
        thread(start = true) {
            _wordsObserver.postValue(ResponseDataState.NewData( webWordsUseCase.getData()))
        }


    }

}