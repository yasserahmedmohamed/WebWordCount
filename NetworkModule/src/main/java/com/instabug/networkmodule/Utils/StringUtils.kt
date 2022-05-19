package com.instabug.networkmodule.Utils

import java.util.regex.Matcher
import java.util.regex.Pattern

internal fun String.isValidWord():Boolean{
    val p: Pattern = Pattern.compile("[^A-Za-z]", Pattern.CASE_INSENSITIVE)
    val m: Matcher = p.matcher(this)
   return !m.find()
}