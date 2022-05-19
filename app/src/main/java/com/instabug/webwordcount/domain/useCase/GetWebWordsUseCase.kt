package com.instabug.webwordcount.domain.useCase

import com.instabug.networkmodule.WebWordsRepository
import com.instabug.webwordcount.domain.model.WordWithCount

class GetWebWordsUseCase {

    fun getData():ArrayList<WordWithCount>{
        val data = WebWordsRepository.getData()
        val mappedWordList = ArrayList<WordWithCount>()
        data.forEach { word->
            val indexOfWordInMappedList = mappedWordList.indexOfFirst { wordWithCount ->
            word.lowercase().equals(wordWithCount.name.lowercase())
            }
            if (indexOfWordInMappedList!=-1){
                mappedWordList.get(indexOfWordInMappedList).count++
            }else{
                mappedWordList.add(WordWithCount(word,1))
            }
        }
        return mappedWordList
    }
}