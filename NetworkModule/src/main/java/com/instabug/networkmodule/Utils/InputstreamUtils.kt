package com.instabug.networkmodule.Utils

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

internal fun InputStream.convertInputStreamToString(): String {
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
