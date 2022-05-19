package com.instabug.networkdatamodule

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object NetworkCalls {


    fun fetchSiteData(site: String):String{
        var res =""
        run {
            try {
                val inputStream: InputStream
                val url = URL(site)
                val connection = url.openConnection() as HttpURLConnection
                connection.connect()
                inputStream = connection.inputStream
                res = (inputStream?.convertInputStreamToString())?:"---"
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return res
    }

}



fun InputStream.convertInputStreamToString(): String {
    val bufferedReader = BufferedReader(InputStreamReader(this))

    var line:String? = bufferedReader.readLine()
    var result = ""

    while (line != null) {
        result += line
        line = bufferedReader.readLine()
    }

    this.close()
    return result
}