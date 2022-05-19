package com.instabug.webwordcount.presentation.feature_words_count

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.instabug.webwordcount.Utils.ResponseDataState
import com.instabug.webwordcount.data.repository.WordsSortRepositoryImpl
import com.instabug.webwordcount.domain.model.SortTypes
import com.instabug.webwordcount.domain.model.WordWithCount
import com.instabug.webwordcount.domain.repository.LocalWordsRepository
import com.instabug.webwordcount.domain.useCase.GetWebWordsUseCase
import kotlinx.coroutines.delay
import kotlin.concurrent.thread

class MainViewModel(
    private val webWordsUseCase: GetWebWordsUseCase,
    private val localWordsRepository: LocalWordsRepository,
    private val sortRepositoryImpl: WordsSortRepositoryImpl
) : ViewModel() {


    private val _wordsObserver =
        MutableLiveData<ResponseDataState<List<WordWithCount>>>()
    var wordsObserver: LiveData<ResponseDataState<List<WordWithCount>>> = _wordsObserver

    private var allWords = ArrayList<WordWithCount>()

    fun getWords(isNetworkAvailable: Boolean) {
        _wordsObserver.postValue(ResponseDataState.Loading())
        var response: ArrayList<WordWithCount>
        thread(start = true) {
            if (isNetworkAvailable) {

                response = webWordsUseCase.getData()
                localWordsRepository.saveWords(response)

            } else {
                response = localWordsRepository.getWords()
            }
            allWords = response
            when(sortRepositoryImpl.getSortBy()){
                SortTypes.ASCENDING ->{
                    response.sortBy { it.count }
                }
                SortTypes.DESCENDING ->{
                    response.sortByDescending { it.count }
                }
            }

            _wordsObserver.postValue(ResponseDataState.NewData(response))

        }
    }

    fun searchInWords(chars: String) {
        val filteredData = allWords.filter { it.name.lowercase().contains(chars.lowercase()) }
        _wordsObserver.postValue(ResponseDataState.NewData(filteredData))
    }

    fun showAllWords() {
        _wordsObserver.postValue(ResponseDataState.NewData(allWords))

    }

    fun sortList(){
        when(sortRepositoryImpl.getSortBy()){
            SortTypes.ASCENDING ->{
                allWords.sortByDescending { it.count }
                sortRepositoryImpl.saveSortBy(SortTypes.DESCENDING)

            }
            SortTypes.DESCENDING ->{

                sortRepositoryImpl.saveSortBy(SortTypes.ASCENDING)
                allWords.sortBy { it.count }
            }
        }
        _wordsObserver.postValue(ResponseDataState.NewData(allWords))
    }
}