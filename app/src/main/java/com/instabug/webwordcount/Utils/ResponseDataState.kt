package com.instabug.webwordcount.Utils


sealed class ResponseDataState<T> {

    data class Loading<T>(val data:T?=null): ResponseDataState<T>()
    data class NewData<T>(val data: T) : ResponseDataState<T>()
    data class FailData<T>(val error:String) : ResponseDataState<T>()

}