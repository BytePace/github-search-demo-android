package com.example.test.github_info_loader.core.fragments

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.github_info_loader.R
import com.example.test.github_info_loader.core.RootApplication
import com.example.test.github_info_loader.core.adapter.InfoRecyclerViewAdapter
import com.example.test.github_info_loader.core.model.SimpleRepositoryInfo
import com.example.test.github_info_loader.core.presenters.ListInfoPresenter
import com.example.test.github_info_loader.core.presenters.CheckInfoDataPresenter
import com.example.test.github_info_loader.core.util.TimeLockTextWatcher
import com.example.test.github_info_loader.core.view.CheckInfoDataView
import com.example.test.github_info_loader.core.view.ListInfoView
import kotlinx.android.synthetic.main.search_info_layout.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class SearchInfoFragment : MvpAppCompatFragment(R.layout.search_info_layout), CheckInfoDataView, ListInfoView {

    @Inject
    @InjectPresenter
    lateinit var listInfoPresenter: ListInfoPresenter

    @Inject
    @InjectPresenter
    lateinit var checkInfoPresenter: CheckInfoDataPresenter

    private lateinit var mInputField: EditText
    private lateinit var infoRecyclerView: RecyclerView
    private lateinit var infoRecyclerViewAdapter: InfoRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        infoRecyclerView = info_recycler_view
        infoRecyclerViewAdapter =
            InfoRecyclerViewAdapter(listInfoPresenter::pageLoad, checkInfoPresenter::toDetailInfo)
        infoRecyclerView.adapter = infoRecyclerViewAdapter
        infoRecyclerView.layoutManager = LinearLayoutManager(this.activity)
        mInputField = inputKeyWords
        mInputField.addTextChangedListener(TimeLockTextWatcher(listInfoPresenter::infoLoad))
    }

    override fun drawInfo(toDraw: List<SimpleRepositoryInfo>) {
        infoRecyclerView.post {
            infoRecyclerViewAdapter.updateInfo(toDraw)
        }
    }

    override fun clearInfo() {
        infoRecyclerViewAdapter.clearData()
    }

    @ProvidePresenter
    fun provideListInfoPresenter() : ListInfoPresenter {
        val listInfoPresenter = ListInfoPresenter()
        RootApplication.appComponent.inject(listInfoPresenter)
        return listInfoPresenter
    }

    @ProvidePresenter
    fun provideCheckInfoDataPresenter() : CheckInfoDataPresenter {
        val checkInfoDataPresenter = CheckInfoDataPresenter()
        RootApplication.appComponent.inject(checkInfoDataPresenter)
        return checkInfoDataPresenter
    }
}