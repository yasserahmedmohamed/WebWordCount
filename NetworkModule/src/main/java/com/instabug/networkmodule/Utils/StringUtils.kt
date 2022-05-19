package com.instabug.networkmodule.Utils

import java.util.regex.Matcher
import java.util.regex.Pattern

internal fun String.isValidWord():Boolean{
    if (this.length==0)
        return false
    val p: Pattern = Pattern.compile("[^A-Za-z]", Pattern.CASE_INSENSITIVE)
    val m: Matcher = p.matcher(this)
   return !m.find()
}