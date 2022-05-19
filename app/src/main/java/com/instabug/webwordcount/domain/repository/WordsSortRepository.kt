package com.instabug.webwordcount.domain.repository

import com.instabug.webwordcount.domain.model.SortTypes

interface WordsSortRepository {
    fun saveSortBy(type:SortTypes)
    fun getSortBy():SortTypes
}