package com.instabug.localdatamodule

import android.content.Context

object SharedPrefManager {

    private val WORDS_KEY = "wordsSaved"
    private val SORT_KEY="SortType"
    private val dataName = "DATA"

    fun saveWords(context: Context, value:String){
        val sharedPref = context.getSharedPreferences(dataName, Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(WORDS_KEY, value)
            apply()
        }
    }

    fun getWords(context: Context):String{
        val sharedPref = context.getSharedPreferences(dataName, Context.MODE_PRIVATE)
        return sharedPref.getString(WORDS_KEY, "")?:""
    }

    fun saveSortType(context: Context, value:Int){
        val sharedPref = context.getSharedPreferences(dataName, Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putInt(SORT_KEY, value)
            commit()
        }
    }

    fun getSortType(context: Context):Int{
        val sharedPref = context.getSharedPreferences(dataName, Context.MODE_PRIVATE)
        return sharedPref.getInt(SORT_KEY,0)
    }

}