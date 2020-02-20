package com.example.test.github_info_loader.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.github_info_loader.DEFAULT_PAGE_SIZE
import com.example.test.github_info_loader.R
import com.example.test.github_info_loader.core.model.SimpleRepositoryInfo
import kotlinx.android.synthetic.main.repository_info.view.*

class InfoRecyclerViewAdapter(
    private val callback: () -> Unit,
    private val textCallBack: (smp: SimpleRepositoryInfo) -> Unit
) : RecyclerView.Adapter<InfoRecyclerViewAdapter.InfoListHolder>() {

    private var info: ArrayList<SimpleRepositoryInfo> = ArrayList()
    private var isLoading = false
    private var isAllContentLoad = false

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = InfoListHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.repository_info,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = info.size

    override fun onBindViewHolder(holder: InfoListHolder, position: Int) {
        if(isPaginate(position)) {
            isLoading = true
            callback.invoke()
        }
        holder.bind(info[position])
    }

    fun clearData() {
        info.clear()
    }

    private fun isPaginate(position: Int) = position != 0 && position == info.lastIndex && !isLoading && !isAllContentLoad

    fun updateInfo(updatedInfo: List<SimpleRepositoryInfo>) {
        isAllContentLoad = DEFAULT_PAGE_SIZE > updatedInfo.size
        val currentListSize = info.size
        info.addAll(updatedInfo)
        if(currentListSize == 0) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeInserted(currentListSize, updatedInfo.size)
        }
        isLoading = false
    }

    inner class InfoListHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var simpleRepositoryInfo: SimpleRepositoryInfo
        private val repositoryName = view.repositoryName
        private val repositoryURL = view.repositoryURL


        fun bind(simpleInfoRepositoryInfo: SimpleRepositoryInfo) {
            this.simpleRepositoryInfo = simpleInfoRepositoryInfo
            repositoryName.text = simpleInfoRepositoryInfo.repositoryName
            repositoryName.setOnClickListener {
                textCallBack.invoke(simpleRepositoryInfo)
            }
            repositoryURL.text = simpleInfoRepositoryInfo.repositoryURL
        }
    }
}