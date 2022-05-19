package com.instabug.webwordcount.data.repository

import android.content.Context
import com.instabug.localdatamodule.SharedPrefManager
import com.instabug.webwordcount.domain.model.SortTypes
import com.instabug.webwordcount.domain.repository.WordsSortRepository

class WordsSortRepositoryImpl(private val context: Context):WordsSortRepository {
    override fun saveSortBy(type: SortTypes) {
      SharedPrefManager.saveSortType(context,type.ID)
    }

    override fun getSortBy(): SortTypes {
        val data = SharedPrefManager.getSortType(context)
        return if (data == SortTypes.ASCENDING.ID) return  SortTypes.ASCENDING else  SortTypes.DESCENDING
    }
}