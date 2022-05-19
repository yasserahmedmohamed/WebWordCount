package com.instabug.webwordcount.presentation.feature_words_count

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.instabug.webwordcount.R
import com.instabug.webwordcount.databinding.ItemWordBinding
import com.instabug.webwordcount.domain.model.WordWithCount

class WordsAdapter: RecyclerView.Adapter<WordsAdapter.ViewHolder>() {

    private val myList = ArrayList<WordWithCount>()

    fun setList(data: List<WordWithCount>) {
        myList.clear()
        myList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = DataBindingUtil.inflate<ItemWordBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_word,
            parent,
            false
        )

        return ViewHolder(item)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myList.get(position))
    }

    override fun getItemCount() = myList.size


    inner class ViewHolder(val viewItem: ItemWordBinding) : RecyclerView.ViewHolder(viewItem.root) {
        fun bind(data: WordWithCount) {
            viewItem.nameTxt.text = data.name
            viewItem.countTxt.text = data.count.toString()
        }
    }

}