package com.instabug.networkmodule

import androidx.core.text.HtmlCompat
import com.instabug.networkmodule.Utils.convertInputStreamToString
import com.instabug.networkmodule.Utils.isValidWord
import com.instabug.networkmodule.constants.webSiteUrl
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future


object WebWordsRepository {


    fun getData():ArrayList<String>{
        val wordList = ArrayList<String>()
        val pool = Executors.newCachedThreadPool()
        val future: Future<String> = pool.submit(TestTask())
        val allWords =  future.get().split(" ")
        allWords.forEach { strWord->
            if (strWord.isValidWord()){
                wordList.add(strWord)
            }

        }

        return wordList
    }


   private class TestTask : Callable<String> {
        override fun call(): String {
            val x =  HtmlCompat.fromHtml(
                fetchSiteData(),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            ).toString()

            return x
        }

    }

   private fun fetchSiteData():String{
        var res =""
        run {
            try {
                val inputStream: InputStream
                val url = URL(webSiteUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.connect()
                inputStream = connection.inputStream
                res = (inputStream?.convertInputStreamToString())?:""
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return res
    }



}


