package com.instabug.webwordcount.domain.repository

import com.instabug.webwordcount.domain.model.WordWithCount

interface LocalWordsRepository {
    fun getWords():ArrayList<WordWithCount>
    fun saveWords(data:ArrayList<WordWithCount>)
}