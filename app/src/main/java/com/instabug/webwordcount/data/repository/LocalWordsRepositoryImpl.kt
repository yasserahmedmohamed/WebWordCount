package com.instabug.webwordcount.data.repository

import android.content.Context
import com.instabug.localdatamodule.SharedPrefManager
import com.instabug.webwordcount.Utils.jsonToMap
import com.instabug.webwordcount.Utils.toHashMap
import com.instabug.webwordcount.Utils.toWordSArray
import com.instabug.webwordcount.domain.model.WordWithCount
import com.instabug.webwordcount.domain.repository.LocalWordsRepository
import org.json.JSONObject

class LocalWordsRepositoryImpl(val context: Context): LocalWordsRepository {
    override fun getWords(): ArrayList<WordWithCount> {
     val data = SharedPrefManager.getWords(context)
      val json =  JSONObject(data)
        val dataMap = jsonToMap(json)

        return dataMap.toWordSArray()
    }

    override fun saveWords(data: ArrayList<WordWithCount>) {
        val hasMap:HashMap<String,Int> = data.toHashMap()
        val jsonObject = JSONObject(hasMap.toMap())
        val jsonString: String = jsonObject.toString()
        SharedPrefManager.saveWords(context,jsonString)

    }
}