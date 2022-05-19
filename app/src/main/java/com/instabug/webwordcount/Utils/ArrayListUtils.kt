package com.instabug.webwordcount.Utils

import com.instabug.webwordcount.domain.model.WordWithCount
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

fun ArrayList<WordWithCount>.toHashMap():HashMap<String,Int>{
    val hash = HashMap<String,Int>()

    this.forEach {
        hash[it.name] = it.count
    }
    return hash
}

fun MutableMap<String,Int>.toWordSArray():ArrayList<WordWithCount>{
    val arr = ArrayList<WordWithCount>()

    this.onEachIndexed { index, entry ->
        arr.add(WordWithCount(entry.key,entry.value))
    }

    return arr
}

@Throws(JSONException::class)
fun jsonToMap(json: JSONObject): MutableMap<String, Int> {
    var retMap: Map<String, Int> = HashMap()
    if (json !== JSONObject.NULL) {
        retMap = toMap(json)
    }
    return retMap.toMutableMap()
}



@Throws(JSONException::class)
fun toMap(`object`: JSONObject): MutableMap<String, Int> {
    val map: MutableMap<String, Int> = HashMap()
    val keysItr = `object`.keys()
    while (keysItr.hasNext()) {
        val key = keysItr.next()
        var value = `object`[key]
        if (value is JSONArray) {
            value = toList(value)
        } else if (value is JSONObject) {
            value = toMap(value)
        }
        map[key] = value.toString().toInt()
    }
    return map.toMutableMap()
}



@Throws(JSONException::class)
fun toList(array: JSONArray): List<Any> {
    val list: MutableList<Any> = ArrayList()
    for (i in 0 until array.length()) {
        var value = array[i]
        if (value is JSONArray) {
            value = toList(value)
        } else if (value is JSONObject) {
            value = toMap(value)
        }
        list.add(value)
    }
    return list
}